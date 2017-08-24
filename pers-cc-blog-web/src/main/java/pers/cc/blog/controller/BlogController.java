package pers.cc.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pers.cc.blog.lucene.BlogIndex;
import pers.cc.blog.model.Blog;
import pers.cc.blog.service.BlogService;
import pers.cc.blog.service.CommentService;
import pers.cc.common.utils.StringUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 博主Controller层
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogService blogService;

    @Resource
    private CommentService commentService;

    @Resource
    private BlogIndex blogIndex;

    /**
     * 显示详细日志
     * 
     * @return
     */
    @RequestMapping("/articles/{id}.html")
    private ModelAndView details(@PathVariable("id") Integer id,
            HttpServletRequest request) throws Exception {
        ModelAndView mAndView = new ModelAndView();
        Blog blog = blogService.findById(id);
        String keyWords = blog.getKeyWord();
        if (StringUtil.isNotEmpty(keyWords)) {
            String arr[] = keyWords.split(" "); // aa bb aa bb
            mAndView.addObject("keyWords",
                    StringUtil.filterWhite(Arrays.asList(arr)));
        } else {
            mAndView.addObject("keyWords", null);
        }
        mAndView.addObject("blog", blog);
        blog.setClickHit(blog.getClickHit() + 1);
        blogService.update(blog);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("blogId", blog.getId());
        map.put("state", 1);
        mAndView.addObject("commentList", commentService.list(map));
        mAndView.addObject("pageCode",
                getUpandDownPageCode(blogService.getLastBlog(id),
                        blogService.getNextBlog(id),
                        request.getServletContext().getContextPath()));
        mAndView.addObject("pageTitle", blog.getTitle() + "Cc个人博客系统");
        mAndView.addObject("mainPage", "foreground/blog/view.jsp");
        mAndView.setViewName("mainTemp");
        return mAndView;
    }

    /**
     * 博客详细信息上一页和下一页
     * 
     * @param lastBlog
     * @param nextBlog
     * @param projectContext
     * @return
     * @throws Exception
     */
    private String getUpandDownPageCode(Blog lastBlog, Blog nextBlog,
            String projectContext) throws Exception {
        StringBuffer pageCode = new StringBuffer();
        if (lastBlog == null || lastBlog.getId() == null) {
            pageCode.append("<p>上一篇：没有了</p>");
        } else {
            pageCode.append("<p>上一篇：<a href='" + projectContext
                    + "/blog/articles/" + lastBlog.getId() + ".html'>"
                    + lastBlog.getTitle() + "</a></p>");
        }

        if (nextBlog == null || nextBlog.getId() == null) {
            pageCode.append("<p>下一篇：没有了</p>");
        } else {
            pageCode.append("<p>下一篇：<a href='" + projectContext
                    + "/blog/articles/" + nextBlog.getId() + ".html'>"
                    + nextBlog.getTitle() + "</a></p>");
        }
        return pageCode.toString();
    }

    /**
     * 根据关键字查询博客相关博客信息
     * 
     * @param q
     * @return
     * @throws Exception
     */
    @RequestMapping("/q")
    public ModelAndView search(
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "page", required = false) String page,
            HttpServletRequest request) throws Exception {
        int pageSize = 10;
        if (StringUtil.isEmpty(page)) {
            page = "1";
        }
        ModelAndView mAndView = new ModelAndView();
        mAndView.addObject("pageTitle", "搜索关键字" + q + "结果页面_Cc个人博客系统");
        List<Blog> blogList = blogIndex.searchBlog(q);
        Integer toIndex = blogList.size() >= Integer.parseInt(page) * pageSize
                ? Integer.parseInt(page) * pageSize : blogList.size();
        mAndView.addObject("blogList", blogList
                .subList((Integer.parseInt(page) - 1) * pageSize, toIndex));
        mAndView.addObject("pageCode",
                genUpAndDownPageCode(Integer.parseInt(page), blogList.size(), q,
                        pageSize,
                        request.getServletContext().getContextPath()));
        mAndView.addObject("q", q);
        mAndView.addObject("resultTotal", blogList.size());
        mAndView.addObject("mainPage", "foreground/blog/result.jsp");
        mAndView.setViewName("mainTemp");
        return mAndView;
    }

    /**
     * 获取上一页，下一页代码
     * 
     * @param page
     * @param totalNum
     * @param q
     * @param pageSize
     * @param projectContext
     * @return
     * @throws Exception
     */
    private String genUpAndDownPageCode(Integer page, Integer totalNum,
            String q, Integer pageSize, String projectContext)
            throws Exception {
        long totalPage = totalNum % pageSize == 0 ? totalNum / pageSize
                : totalNum / pageSize + 1;
        StringBuffer pageCode = new StringBuffer();
        if (totalPage == 0) {
            return "";
        } else {
            pageCode.append("<nav>");
            pageCode.append("<ul class='pager'>");
            if (page > 1) {
                pageCode.append(
                        "<li><a href='" + projectContext + "/blog/q.html?page="
                                + (page - 1) + "&q=" + q + "'>上一页</a></li>");
            } else {
                pageCode.append("<li class='disabled'><span>上一页</span></li>");
            }
            if (page < totalPage) {
                pageCode.append(
                        "<li><a href='" + projectContext + "/blog/q.html?page="
                                + (page + 1) + "&q=" + q + "'>下一页</a></li>");
            } else {
                pageCode.append("<li class='disabled'><span>下一页</span></li>");
            }
            pageCode.append("</ul></nav>");
        }
        return pageCode.toString();
    }

}
