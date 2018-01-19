package pers.platform.common.exception;

/**
 *  业务异常基类，所有业务异常都必须继承于此异常
 * Created by cc on  2018/1/19
 */
public class BusinessException extends RuntimeException{

    //TODO
    //数据库异常

    /**
     * 异常信息
     */
    protected String msg;

    /**
     * 具体异常码
     */
    protected int code;

    public BusinessException(int code, String msgFormat, Object... data) {
        super(String.format(msgFormat, data));
        this.code = code;
        this.msg = String.format(msgFormat, data);
    }

    public BusinessException() {
        super();
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    /**
     * 实例化异常
     *
     * @param msgFormat
     * @param data
     * @return
     */
    public BusinessException newInstance(String msgFormat, Object... data) {
        return new BusinessException(code, msgFormat, data);
    }


}
