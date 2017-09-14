package pers.platfrom.common.utils;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by cc on  2017/9/5
 */
public class CodeConvert {

    public static void main(String[] args)throws Exception {
        convertBomtoUtf8("D:\\java\\eclispe-workspace\\t5-mes\\thsoft-mes\\thsoft-mes-material-web\\src\\main\\java\\com\\thsoft\\mes\\materialcategory\\controller");
    }

    public  static void convertBomtoUtf8(String path) throws  Exception{
        // 遍历CD目录下的所有文件和子目录
        Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {
            // 访问文件时候触发该方法
            @Override
            public FileVisitResult visitFile(Path file,
                                             BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(".java")){
                    System.out.println(file.getFileName());
                    //将BOM转化为UTF-8
                    convertToUtf8NoBOM(file.toFile());
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * 将文件转化为UTF8无BOM
     *
     * @param file
     * @throws IOException
     */
    private static void convertToUtf8NoBOM(File file) throws IOException {
        String strText = null;

        // 读取文件
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            byte[] buf = new byte[(int) file.length()];
            in.read(buf);
            if (buf.length > 3 && (byte) 0XEF == buf[0] && (byte) 0XBB == buf[1] && (byte) 0XBF == buf[2]) {
                strText = new String(buf, 3, buf.length - 3, "UTF-8");
            } else {
                strText = new String(buf);
            }
            strText.replace("\r\n", "\n");
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null != in) {
                in.close();
            }
        }

        // 写文件
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
            out.write(strText.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
    }
}
