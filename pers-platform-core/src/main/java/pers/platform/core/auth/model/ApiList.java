package pers.platform.core.auth.model;

import org.hibernate.validator.constraints.NotEmpty;
import pers.platform.common.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * api列表
 * @author cc
 * @date 2018-01-16
 */
@Entity
@Table(name="api_list")
public class ApiList extends BaseModel{

    @Id
    @NotEmpty(message = "id不可为空")
    private long  id;

    @NotEmpty(message = "apiName不可为空")
    @Column(name = "api_name")
    private String apiName; //api名称

    @NotEmpty(message = "apiPath不可为空")
    @Column(name = "api_path")
    private String apiPath; //api请求路径

    @NotEmpty(message = "apiStatus不可为空")
    @Column(name = "api_status")
    private int apiStatus=1; //api状态 是否可用 0不可用1可用

    @Column(name = "app_name")
    private String appName; //应用名称

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public int getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(int apiStatus) {
        this.apiStatus = apiStatus;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



}
