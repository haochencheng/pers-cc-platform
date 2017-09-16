package pers.platfrom.common.utils.file;

import java.io.*;

/**
 * Created by cc on  2017/9/14
 */
public class TextFileUtil {

    /**
     * 读取文件
     *
     * @param fileName
     *            文件路径
     * @return sb 读取的文件内容
     * @Description:
     */
    public static String read(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(
                    new FileReader(new File(fileName).getAbsolutePath()));
            String s;
            try {
                while ((s = in.readLine()) != null) {
                    sb.append(s).append("\n");
                }
            } catch (IOException e) {
                // TODO: handle exception
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("打开文件错误!");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String path = getJavaPathByClass(TextFileUtil.class);
        System.out.println(path);
        String text = read(path);
        System.out.println(text);
        write("C:/Users/Administrator/Desktop/write.txt", text);
    }

    /**
     * @param fileName
     *            输出文件路径
     * @param text
     *            输出文件内容
     * @Description:
     */
    public static void write(String fileName, String text) {
        try {
            PrintWriter out = new PrintWriter(
                    new File(fileName).getAbsolutePath());
            try {
                out.print(text);
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                out.close();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 通过class获取文件相对路径
     *
     * @param cls
     *            class类
     * @return 相对路径
     * @Description:
     */
    public static String getJavaPathByClass(Class<?> cls) {
        return "src/main/java/" + cls.getCanonicalName().replace(".", "/")
                + ".java";
    }
}
