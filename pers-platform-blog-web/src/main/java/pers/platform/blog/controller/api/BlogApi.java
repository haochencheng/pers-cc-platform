package pers.platform.blog.controller.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pers.platform.blog.model.Blog;
import pers.platform.blog.service.BlogService;

@RestController
public class BlogApi {

    @Resource
    private BlogService blogService;

    @RequestMapping(value = "/api/blog/filter/{id}", method = RequestMethod.GET)
    public String filter(@PathVariable @NotNull String id) {
        Blog blog = blogService.findById(id);
        String content = blog.getContent();
        return filterStyle("style", content);
    }

    /**
     * 使用正则表达式过滤标签样式:style,class.比如
     * <p style="width:100px">
     * 或
     * <p class="myclass">
     * </p>
     * 
     * @param element
     *            替换的元素标签
     * @param content
     *            替换的文本内容
     * @return
     * @Description:
     */
    private String filterStyle(String element, String content) {
        Pattern pattern = Pattern
                .compile(element + "\\s?=\\s?(['\"][^'\"]*?['\"]|[^'\"]\\S*)");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            content = matcher.replaceAll("");
        }
        return content;
    }

}
