package com.mycompany.model.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mycompany.model.BaseObject;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.print.attribute.standard.DateTimeAtCompleted;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by liaoxiang on 2016/6/15.
 */


@Entity
@Table(name = "sys_log")
@Indexed
public class SysLog  extends BaseObject implements Serializable {

    private Long id;
    private String url;
    private String ip;
    private String username;
    private Date createTime;
    private String role;
    private Long userId;

    private String timeStr;

    public SysLog() {

    }

    public SysLog(Long id, String url, String ip, String username, Date createTime, String role, Long userId) {
        this.id = id;
        this.url = url;
        this.ip = ip;
        this.username = username;
        this.createTime = createTime;
        this.role = role;
        this.userId = userId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column
    @Field
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @Column
    @Field
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    @Column
    @Field
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "create_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Field
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    @Transient
    public String getTimeStr() {
        SimpleDateFormat myFmt1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timeStr = myFmt1.format(createTime);
        return timeStr;
    }



    /**
     * Returns a multi-line String with key=value pairs.
     *
     * @return a String representation of this class.
     */
    @Override
    public String toString() {
        return null;
    }

    /**
     * Compares object equality. When using Hibernate, the primary key should
     * not be a part of this comparison.
     *
     * @param o object to compare to
     * @return true/false based on equality tests
     */
    @Override
    public boolean equals(Object o) {
        return false;
    }

    /**
     * When you override equals, you should override hashCode. See "Why are
     * equals() and hashCode() importation" for more information:
     * http://www.hibernate.org/109.html
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
        return 0;
    }
}
