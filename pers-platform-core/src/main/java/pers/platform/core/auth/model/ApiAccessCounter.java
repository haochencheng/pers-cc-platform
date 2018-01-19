package pers.platform.core.auth.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * api访问量表
 * @author cc
 * @date 2018-01-18
 */
@Entity
@Table(name="api_access_counter")
public class ApiAccessCounter {

    @EmbeddedId
    private ApiUserAuthId apiUserAuthId;

    @Column(name = "api_request_count")
    private int apiRequestCount;  //请求次数


    public ApiUserAuthId getApiUserAuthId() {
        return apiUserAuthId;
    }

    public void setApiUserAuthId(ApiUserAuthId apiUserAuthId) {
        this.apiUserAuthId = apiUserAuthId;
    }

    public int getApiRequestCount() {
        return apiRequestCount;
    }

    public void setApiRequestCount(int apiRequestCount) {
        this.apiRequestCount = apiRequestCount;
    }



    /*另一种比较好的办法是对每一个文章的计数器不是一行，而是多行，比如吧，一百行。每次随机更新其中一行，该文章的浏览数就是所有行的和。
复制代码 代码如下:

CREATE TABLE `article_view`(
`article_id` int(11) NOT NULL,
`pond` tinyint(4) NOT NULL COMMENT '池子，就是用来随机用的',
`view` int(11) NOT NULL,
PRIMARY KEY (`article_id`,`pond`)
)ENGINE=InnoDB;
小访问量的随机池子100个肯定多了，三五个足矣。每次访问的时候，随机一个数字（1-100）作为pond，如何该pond存在则更新view+1，否则插入，view=1。借助DUPLICATE KEY，不然在程序里是实现得先SELECT，判断一下再INSERT或者UPDATE。
复制代码 代码如下:

INSERT INTO `article_view` (`article_id`, `pond`, `view`) VALUES (`123`, RAND()*100, 1) ON DUPLICATE KEY UPDATE `view`=`view`+1
获取指定文章的总访问量的时候：
复制代码 代码如下:

SELECT SUM(`view`) FROM `article_view` WHERE `article_id`='123'*/
}
