package pers.platfrom.common.utils.file;

import java.io.*;

/**
 * Created by cc on  2017/9/14
 */
public class StoreFileObjectUtil {

    public static final String PATH_STORE=StoreFileObjectUtil.class.getResource("/").toString().replaceAll("file:/","")+"store.txt";

    public static void main(String[] args) {
        System.out.println(PATH_STORE);
    }

    //储存序列化文件到txt
    public static void writeObject(Object map){
        File file=new File(PATH_STORE);
        FileOutputStream out ;
        ObjectOutputStream oos = null;
        try {
             out=new FileOutputStream(file);
            try {
                oos=new ObjectOutputStream(out);
                oos.writeObject(map);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    oos.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // read the object from the file
    public static Object readObject() {
        Object map= null;
        try {
            FileInputStream in=new FileInputStream(PATH_STORE);
            ObjectInputStream objread=new ObjectInputStream(in);
            map = objread.readObject();
            objread.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }

}
