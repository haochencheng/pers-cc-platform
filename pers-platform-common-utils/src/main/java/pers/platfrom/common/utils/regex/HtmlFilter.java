package pers.platfrom.common.utils.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlFilter {

    /**
     * 使用正则表达式过滤标签样式:style,class.比如
     * <p style="width:100px">
     * 或
     * <p class="myclass">
     * </p>
     * 
     * @param element
     *            过滤的元素属性标签
     * @param content
     *            替换的文本内容
     * @return
     * @Description:
     */
    public static String filterElementProp(String element, String content) {
        Pattern pattern = Pattern
                .compile(element + "\\s?=\\s?(['\"][^'\"]*?['\"]|[^'\"]\\S*)");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            content = matcher.replaceAll("");
        }
        return content;
    }

    /**
     * 使用正则表达式过滤html标签
     * 
     * @param element
     *            替换的元素
     * @param content
     *            替换的文本
     * @return
     * @Description:
     */
    public static String filterHtmlElement(String element, String content) {
        Pattern pattern = Pattern
                .compile("<" + element + "[^>]*>|</" + element + ">");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            content = matcher.replaceAll("");
        }
        return content;
    }

}
