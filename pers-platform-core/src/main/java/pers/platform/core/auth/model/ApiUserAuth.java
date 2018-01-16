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
    private String  userId;  //用户id，外键
    @NotEmpty(message = "apiListId不可为空")
    private String apiListId; //访问api，外键

    private String apiKey; //apikey

    private String apiSecret; //api访问密匙

    private Date apiBeginTime;  //生效时间

    private Date apiExpireTime; //过期时间
    @NotEmpty(message = "apiListId不可为空")
    private int apiUserType; //是否vip 0不是，1是。不是vip只能访问免费接口
    @NotEmpty(message = "apiListId不可为空")
    private int apiRequestType; //api请求类型 0时间，1次数 。

    private int apiRequestCount;  //请求次数

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getApiListId() {
        return apiListId;
    }

    public void setApiListId(String apiListId) {
        this.apiListId = apiListId;
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

    public int getApiRequestCount() {
        return apiRequestCount;
    }

    public void setApiRequestCount(int apiRequestCount) {
        this.apiRequestCount = apiRequestCount;
    }
}
