package pers.platform.common.utils.convert;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.zip.GZIPOutputStream;

public class BlobToStringUtil {

    /**
     * 将图片转换为字节数组
     */
    public static byte[] loadBlob(Blob blob) {
        if (blob == null) return null;
        InputStream fin = null;
        try {
            fin = blob.getBinaryStream();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        // 用于返回的字节数组
        byte[] data = null;
        // 打开字节输出流
        ByteArrayOutputStream bout;
        try {
            if (fin == null) {
                return null;
            }
            // 输出流定义缓冲区大小
            bout = new ByteArrayOutputStream(fin.available());
            // 定义字节数组，用于读取文件流
            byte[] buffer = new byte[1024];
            // 用于表示读取的位置
            int len;
            // 开始读取文件
            while ((len = fin.read(buffer)) != -1) {
                // 从buffer的第0位置开始，读取至第len位置，结果写入bout
                bout.write(buffer, 0, len);
            }
            // 将输出流转为字节数组
            data = bout.toByteArray();
            // 关闭输入输出流
            fin.close();
            bout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 加载本地文件,并转换为byte数组
     */
    public static byte[] loadFile(String path) {
        File file = new File(path);
        FileInputStream fis = null;
        BufferedInputStream bufferedInputStream;
        ByteArrayOutputStream byteArrayOutputStream = null;
        byte[] data = null;
        try {
            fis = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fis);
            byteArrayOutputStream = new ByteArrayOutputStream((int) file.length());
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bufferedInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            data = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    /**
     * 对byte[]进行压缩
     *
     * @param data 要压缩的数据
     * @return newData 压缩后的数据
     */
    public static byte[] compress(byte[] data) {
        if (data == null) return null;
        GZIPOutputStream gzip = null;
        byte[] newData = null;
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        try {
            gzip = new GZIPOutputStream(byteOutputStream);
            gzip.write(data);
            gzip.finish();
            newData = byteOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (gzip != null) {
                    gzip.close();
                }
                byteOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return newData;
    }
}
