package pers.platform.common.utils.file;

import java.io.*;

/**
 * byte file转化工具类
 * Created by cc on  2019/01/15
 */
public class FileBytesConvertUtil {


    public static File bytes2file(byte[] bytes) throws Exception {
        File file = new File("");
        OutputStream output = new FileOutputStream(file);
        BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
        bufferedOutput.write(bytes);
        return file;
    }

}
