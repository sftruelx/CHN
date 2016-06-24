package com.mycompany.model.common;

import com.mycompany.model.BaseObject;
import com.mycompany.model.sys.SysUser;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by liaoxiang on 2016/5/25.
 */

@Entity
@Table(name = "biz_dispatch")
public class Dispatch  extends BaseObject implements Serializable {
    private Long id;
    private Long userId;
    private Integer checkItem;
    private Long department;
    private Date createDate;
    private Integer state;

    public Dispatch(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "user_id", nullable = false)
    @Field
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    @Column(name = "check_item", nullable = false)
    @Field
    public Integer getCheckItem() {
        return checkItem;
    }

    public void setCheckItem(Integer checkItem) {
        this.checkItem = checkItem;
    }

    @Column(name = "department", nullable = false)
    @Field
    public Long getDepartment() {
        return department;
    }

    public void setDepartment(Long department) {
        this.department = department;
    }
    @Column(name = "create_time", nullable = false)
    @Field
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    @Column
    @Field
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        return true;

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return 0;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append("")
                .toString();
    }
}
