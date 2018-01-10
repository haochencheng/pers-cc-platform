package pers.platform.common.base;

/**
 * 统一返回结果类
 * Created by cc on  2018/1/10
 */
public class BaseResult {


    private int status;    //状态码 1成功 0失败

    private String message;   //消息

    private Object object; //数据结果集

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
