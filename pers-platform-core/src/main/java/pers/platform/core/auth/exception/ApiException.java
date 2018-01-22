package pers.platform.core.auth.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.platform.common.exception.BusinessException;

/**
 * Created by cc on  2018/1/19
 */
public class ApiException extends BusinessException{

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ApiException.class);

    public static final ApiException AUTHENTICATION_ERROR = new ApiException(1001, "api_key和api_secret不匹配!");

    public static final ApiException AUTHORIZATION_ERROR = new ApiException(1002, "api_key没有调用本API的权限，具体原因为：用户自己禁止该api_key调用、管理员禁止该api_key调用、由于账户余额不足禁止调用！");

    public static final ApiException CONCURRENCY_LIMIT_EXCEEDED = new ApiException(1003, "api_key没有调用本API的权限，具体原因为：用户自己禁止该api_key调用、管理员禁止该api_key调用、由于账户余额不足禁止调用！");

    public static final ApiException MISSING_ARGUMENTS = new ApiException(1004, "缺少某个必选参数！");

    public static final ApiException BAD_ARGUMENTS = new ApiException(1005, "某个参数解析出错（比如必须是数字，但是输入的是非数字字符串; 或者长度过长）");

    public static final ApiException API_NOT_FOUND = new ApiException(1006, "所调用的API不存在！");


    public static final ApiException INTERNAL_ERROR = new ApiException(1007, "服务器内部错误，如果持续出现此类错误，请联系管理员！");


    public ApiException() {
    }

    public ApiException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public ApiException(int code, String msg) {
        super(code, msg);
    }

    /**
     * 实例化异常
     *
     * @param msgFormat
     * @param args
     * @return
     */
    public ApiException newInstance(String msgFormat, Object... args) {
        return new ApiException(this.code, msgFormat, args);
    }

    public ApiException print() {
        logger.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }
    
}
