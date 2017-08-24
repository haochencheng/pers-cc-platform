package pers.cc.blog.controller.cc;

import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pers.cc.blog.model.Blogger;
import pers.cc.blog.service.BloggerService;
import pers.cc.common.utils.CryptographyUtil;
import pers.cc.common.utils.DateUtil;
import pers.cc.common.utils.ResponseUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * 管理员博主Controller
 * 
 * @author cc
 *
 */
@Controller
@RequestMapping("/cc/blogger")
public class BloggerCcController {

    @Resource
    private BloggerService bloggerService;

    /**
     * 查询博主信息
     * 
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/find.html")
    public String find(HttpServletResponse response) throws Exception {
        Blogger blogger = bloggerService.find();
        JSONObject jsonObject = JSONObject.fromObject(blogger.toString());
        ResponseUtil.write(response, jsonObject);
        return null;
    }

    /**
     * 修改博主信息
     * 
     * @param imageFile
     * @param blogger
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.html")
    public String save(@RequestParam("imageFile") MultipartFile imageFile,
            Blogger blogger, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (!imageFile.isEmpty()) {
            String filePath = request.getServletContext().getRealPath("/");
            String imageName = DateUtil.getCurrentDateStr() + "."
                    + imageFile.getOriginalFilename().split("\\.")[1];
            imageFile.transferTo(
                    new File(filePath + "static/userImages/" + imageName));
            blogger.setImageName(imageName);
        }
        int resultTotal = bloggerService.update(blogger);
        StringBuffer result = new StringBuffer();
        if (resultTotal > 0) {
            result.append(
                    "<script language='javascript'>alert('修改成功！');</script>");
        } else {
            result.append(
                    "<script language='javascript'>alert('修改失败！');</script>");
        }
        ResponseUtil.write(response, result);
        return null;
    }

    @RequestMapping("/modifyPassword.html")
    public String modifyPassword(String newPassword,
            HttpServletResponse response) throws Exception {
        Blogger blogger = new Blogger();
        blogger.setPassword(CryptographyUtil.md5(newPassword, "cc"));
        int resultTotal = bloggerService.update(blogger);
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
     * 退出系统
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/logout.html")
    public String logout() throws Exception {
        SecurityUtils.getSubject().logout();
        return "redirect:cc/login.jsp";
    }

}
