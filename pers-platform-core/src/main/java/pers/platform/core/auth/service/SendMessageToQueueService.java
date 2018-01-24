package pers.platform.core.auth.service;

import pers.platform.core.constant.CoreConstant;

/**
 * Created by cc on  2018/1/24
 */
public interface SendMessageToQueueService {


    boolean send(String topic,String key,Object data) ;


}
