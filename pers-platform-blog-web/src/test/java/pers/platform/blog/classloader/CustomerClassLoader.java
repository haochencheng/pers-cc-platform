package pers.platform.blog.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class CustomerClassLoader extends ClassLoader {

    private static CustomerClassLoader ccl;

    public static final String PATH_LOAD_ClASS = "E:/workspace/pers-cc-platform/pers-platform-blog-web/target/test-classes/pers/platform/blog/classloader/TestClass.class";

    public static final String PATH_REF_ClASS = "E:/workspace/pers-cc-platform/pers-platform-blog-web/target/test-classes/pers/platform/blog/classloader/ReferenceClass.class";

    public static void main(String[] args) throws Exception {
        CustomerClassLoader ccl = CustomerClassLoader.getCustomerClassLoader();
        Class<?> c = Class.forName("pers.platform.blog.classloader.TestClass",
                true, ccl);
        Object o = c.newInstance();
        System.out.println("加载的类:" + o);
        System.out.println("加载测试类的类加载器:" + o.getClass().getClassLoader());
        Method method = c.getMethod("test");
        method.invoke(c.newInstance());
    }

    public static CustomerClassLoader getCustomerClassLoader() {
        if (ccl == null) {
            // 把自定义ClassLoader的父加载器设置为Extension
            // ClassLoader，这样父加载器加载不到,就交由子加载器CustomerClassLoader来加载了
            ccl = new CustomerClassLoader(
                    ClassLoader.getSystemClassLoader().getParent());
        }
        return ccl;
    }

    public CustomerClassLoader(ClassLoader parent) {
        super(parent);
    }

    public CustomerClassLoader() {
    }

    @Override
    protected Class<?> findClass(String name) {
        File file;
        if ("pers.platform.blog.classloader.ReferenceClass".equals(name)) {
            file = getClassFile(PATH_REF_ClASS);
        } else {
            file = getClassFile(PATH_LOAD_ClASS);
        }
        try {
            byte[] bytes = getClassByBytes(file);
            Class<?> c = defineClass(name, bytes, 0, bytes.length);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            return super.findClass(name);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private File getClassFile(String name) {
        File file = new File(name);
        return file;
    }

    private byte[] getClassByBytes(File file) throws Exception {
        // 这里要读入.class的字节，因此要使用字节流
        FileInputStream fis = null;
        FileChannel fc = null;
        ByteArrayOutputStream baos;
        try {
            fis = new FileInputStream(file);
            fc = fis.getChannel();
            baos = new ByteArrayOutputStream();
            WritableByteChannel wbc = Channels.newChannel(baos);
            ByteBuffer by = ByteBuffer.allocateDirect(1024);
            while (true) {
                int i = fc.read(by);
                if (i == 0 || i == -1) {
                    break;
                }
                by.flip();
                wbc.write(by);
                by.clear();
            }

        } finally {
            fc.close();
            fis.close();
        }
        return baos.toByteArray();
    }

}
