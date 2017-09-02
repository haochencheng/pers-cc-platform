package pers.platform.blog.controller.cc;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import pers.platform.blog.lucene.BlogIndex;
import pers.platform.blog.model.Blog;
import pers.platform.blog.model.PageBean;
import pers.platform.blog.service.BlogService;
import pers.platform.common.service.IdService;
import pers.platfrom.common.utils.ResponseUtil;
import pers.platfrom.common.utils.StringUtil;

@RestController
@RequestMapping("/cc/blog")
public class BlogCcController {

    @Resource
    private BlogService blogService;

    @Resource
    private IdService idService;

    @Resource
    private BlogIndex blogIndex;

    @RequestMapping("/modifyBlog.html")
    public ModelAndView modifyBlog(String id, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id", id);
        modelAndView.setViewName("/cc/blog");
        return modelAndView;
    }

    @RequestMapping("/save.html")
    public String save(Blog blog, HttpServletResponse response)
            throws Exception {
        int resultTotal = 0;
        if (blog.getId() == null) {
            blog.setId(idService.getId());
            blog.setClickHit(1);
            blog.setReleaseDate(new Date());
            blog.setReplyHit(0);
            resultTotal = blogService.add(blog) != null ? 1 : 0;
            blogIndex.addIndex(blog);
        } else {
            // 先查询,发生改变的 设置 然后更新.
            Blog oldBlog = blogService.findById(blog.getId());
            oldBlog.setTitle(blog.getTitle());
            oldBlog.setClickHit(oldBlog.getClickHit() + 1);
            oldBlog.setContent(blog.getContent());
            oldBlog.setBlogType(blog.getBlogType());
            oldBlog.setKeyWord(blog.getKeyWord());
            resultTotal = blogService.update(oldBlog) != null ? 1 : 0;
            blogIndex.updateIndex(blog);
        }
        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 分页查询博客信息
     * 
     * @param page
     * @param rows
     * @param s_bBlog
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/list.html")
    public String list(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "rows", required = false) String rows,
            Blog s_bBlog, HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page),
                Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<>();
        map.put("title", StringUtil.formatLike(s_bBlog.getTitle()));
        map.put("start", pageBean.getCurrentPage());
        map.put("size", pageBean.getPageSize());
        List<Blog> blogList = blogService.list(map);
        Long total = blogService.getTotal(map);
        JSONObject result = new JSONObject();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class,
                new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        JSONArray jsonArray = JSONArray.fromObject(blogList, jsonConfig);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 博客信息删除
     * 
     * @param ids
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete.html")
    public String delete(
            @RequestParam(value = "ids", required = false) String ids,
            HttpServletResponse response) throws Exception {
        String[] idStr = ids.split(",");
        for (int i = 0; i < idStr.length; i++) {
            blogService.delete(idStr[i]);
            blogIndex.deleteindex(idStr[i]);
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 通过id查找博客
     * 
     * @param id
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById.html")
    public String findById(@RequestParam(value = "id") String id,
            HttpServletResponse response) throws Exception {
        Blog blog = blogService.findById(id);
        JSONObject result = JSONObject.fromObject(blog);
        ResponseUtil.write(response, result);
        return null;
    }

}
