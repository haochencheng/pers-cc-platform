package pers.platform.core.auth.model;


import org.hibernate.validator.constraints.NotEmpty;
import pers.platform.common.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * api用户权限表
 * @author cc
 * @date 2018-01-16
 */
@Entity
@Table(name="api_user_auth")
public class ApiUserAuth extends BaseModel {

    @Id
    private long  id;

    @NotEmpty(message = "apiUserId不可为空")
    private long  userId;  //用户id，外键
    @NotEmpty(message = "apiListId不可为空")
    private long apiListId; //访问api，外键
    @NotEmpty(message = "apiKey不可为空")
    private String apiKey; //apikey
    @NotEmpty(message = "apiSecret不可为空")
    private String apiSecret; //api访问密匙

    private Date apiBeginTime;  //生效时间

    private Date apiExpireTime; //过期时间
    @NotEmpty(message = "apiListId不可为空")
    private int apiUserType; //是否vip 0不是，1是。不是vip只能访问免费接口
    @NotEmpty(message = "apiListId不可为空")
    private int apiRequestType; //api请求类型 0时间，1次数 。


    private Integer locked = 0; //0锁定1解锁

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public boolean isLocked() {
        return locked==0?true:false;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }



    public Date getApiBeginTime() {
        return apiBeginTime;
    }

    public void setApiBeginTime(Date apiBeginTime) {
        this.apiBeginTime = apiBeginTime;
    }

    public Date getApiExpireTime() {
        return apiExpireTime;
    }

    public void setApiExpireTime(Date apiExpireTime) {
        this.apiExpireTime = apiExpireTime;
    }

    public int getApiUserType() {
        return apiUserType;
    }

    public void setApiUserType(int apiUserType) {
        this.apiUserType = apiUserType;
    }

    public int getApiRequestType() {
        return apiRequestType;
    }

    public void setApiRequestType(int apiRequestType) {
        this.apiRequestType = apiRequestType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getApiListId() {
        return apiListId;
    }

    public void setApiListId(long apiListId) {
        this.apiListId = apiListId;
    }

    public Integer getLocked() {
        return locked;
    }
}
