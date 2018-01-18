package pers.platform.core.auth.model;

import org.hibernate.validator.constraints.NotEmpty;
import pers.platform.common.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * api列表
 * @author cc
 * @date 2018-01-16
 */
@Entity
@Table(name="api_white_list")
public class ApiList extends BaseModel{

    @Id
    @NotEmpty(message = "id不可为空")
    private long  id;
    @NotEmpty(message = "apiName不可为空")
    private String apiName; //api名称
    @NotEmpty(message = "apiPath不可为空")
    private String apiPath; //api请求路径
    @NotEmpty(message = "apiStatus不可为空")
    private String apiStatus; //api状态 是否可用 0不可用1可用
    private String appName; //应用名称
    private int totalCount; //请求次数

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

    public String getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(String apiStatus) {
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

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }


}
