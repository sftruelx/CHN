package com.mycompany.model.common;

import com.mycompany.model.BaseObject;
import com.mycompany.model.sys.Role;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liaoxiang on 2016/4/15.
 */

@Entity
@Table(name = "biz_patient")
public class Patient extends BaseObject implements Serializable {

    private Long id;
    private String name;
    private String no;
    private Integer gender;
    private Integer age;
    private Date birthday;
    private String department;
    private Integer status;
    private Date createDate;
    private String birthdayStr;
    private String createDateStr;
    private String genderStr;
    private String statusStr;
    private String mobile;
    private String openID;

    public Patient() {
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column
    @Field
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column
    @Field
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Column
    @Field
    public Date getCreateDate() {

        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column
    @Field
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }


    @Column
    @Field
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Column
    @Field
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column
    @Field
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column
    @Field
    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    @Transient
    public String getBirthdayStr() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (birthday != null) {
            birthdayStr = format.format(birthday);
        }
        return birthdayStr;
    }

    public void setBirthdayStr(String birthdayStr) {
        this.birthdayStr = birthdayStr;
    }

    @Transient
    public String getGenderStr() {
        if (gender != null) {
            if (gender == 1) {
                return "男";
            } else {
                return "女";
            }
        }
        return "";
    }

    public void setGenderStr(String genderStr) {
        this.genderStr = genderStr;
    }

    @Transient
    public String getStatusStr() {
        String statusStr = "";
        if (status != null) {
            switch (status) {
                case 1:
                    statusStr = "已挂号";
                    break;
                case 2:
                    statusStr = "已开单";
                    break;
                case 3:
                    statusStr = "已分诊";
                    break;
                case 4:
                    statusStr = "已完成";
                    break;
            }
        }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    @Transient
    public String getCreateDateStr() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (createDate != null) {
            createDateStr = format.format(createDate);
        }
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }

        final Patient role = (Patient) o;

        return !(name != null ? !name.equals(role.name) : role.name != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (name != null ? name.hashCode() : 0);
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(this.name)
                .toString();
    }
}
