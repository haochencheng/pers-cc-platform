package pers.platform.core.auth.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ApiUserAuthId implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name = "api_user_auth_Id")
    private long ApiUserAuthId; //外键 和pond做联合主键

    @Column(name = "pond")
    private int pond; //池子，就是用来随机用的


    public long getApiUserAuthId() {
        return ApiUserAuthId;
    }

    public void setApiUserAuthId(long apiUserAuthId) {
        ApiUserAuthId = apiUserAuthId;
    }

    public int getPond() {
        return pond;
    }

    public void setPond(int pond) {
        this.pond = pond;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApiUserAuthId)) return false;
        ApiUserAuthId that = (ApiUserAuthId) o;
        return getApiUserAuthId() == that.getApiUserAuthId() &&
                getPond() == that.getPond();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getApiUserAuthId(), getPond());
    }
}
