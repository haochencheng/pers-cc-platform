package pers.cc.blog.controller.cc;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;
import pers.cc.blog.model.Blog;
import pers.cc.blog.model.BlogType;
import pers.cc.blog.model.Blogger;
import pers.cc.blog.model.Link;
import pers.cc.blog.service.BlogService;
import pers.cc.blog.service.BlogTypeService;
import pers.cc.blog.service.BloggerService;
import pers.cc.blog.service.LinkService;
import pers.cc.common.utils.ResponseUtil;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/cc/system")
public class SystemCcController {

    @Resource
    private BloggerService bloggerService;

    @Resource
    private LinkService linkService;

    @Resource
    private BlogTypeService blogTypeService;

    @Resource
    private BlogService blogService;

    /**
     * 刷新系统缓存
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("refreshSystem.html")
    public String refreshSystem(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ServletContext application = RequestContextUtils
                .findWebApplicationContext(request).getServletContext();
        Blogger blogger = bloggerService.find();
        blogger.setPassword(null);
        application.setAttribute("blogger", blogger);

        List<Link> linkList = linkService.list(null); // 查询所有友情链接
        application.setAttribute("linkList", linkList);

        List<BlogType> blogTypeCountList = blogTypeService.countList();
        application.setAttribute("blogTypeCountList", blogTypeCountList); // 查询博客类别及博客数量

        List<Blog> blogCountList = blogService.countList();
        application.setAttribute("blogCountList", blogCountList); // 根据日期分组查询博客

        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }

}
