package com.mycompany.model.nurse;

import com.mycompany.model.BaseObject;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wsd on 2016/6/14. 分诊管理主表
 */
@Entity
@Table(name = "biz_triage")
public class Triage extends BaseObject implements Serializable {

    private Long id;
    private String triageNo;
    private String outpatientNo;
    private String name;
    private Integer gender;
    private String departmentCode;
    private String departmentName;
    private Date regDate;
    private String regDateStr;
    private String operator;
    private String operatorName;
    private Date triageDate;
    private String triageDateStr;
    private String triageItem;
    private Integer status;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "triage_No",nullable = false)
    @Field
    public String getTriageNo() {
        return triageNo;
    }

    public void setTriageNo(String triageNo) {
        this.triageNo = triageNo;
    }

    @Column(name = "outpatient_no",nullable = false)
    @Field
    public String getOutpatientNo() {
        return outpatientNo;
    }

    public void setOutpatientNo(String outpatientNo) {
        this.outpatientNo = outpatientNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Column(name = "department_code")
    @Field
    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    @Column(name = "department_name")
    @Field
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Column(name = "reg_date")
    @Field
    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Column(name = "operator_name")
    @Field
    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    @Column(name = "triage_date")
    @Field
    public Date getTriageDate() {
        return triageDate;
    }

    public void setTriageDate(Date triageDate) {
        this.triageDate = triageDate;
    }

    @Column(name = "triage_item")
    @Field
    public String getTriageItem() {
        return triageItem;
    }

    public void setTriageItem(String triageItem) {
        this.triageItem = triageItem;
    }

    @Column(name = "status",nullable = false)
    @Field
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Transient
    public String getRegDateStr() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (regDate != null) {
            regDateStr = format.format(regDate);
        }
        return regDateStr;
    }

    public void setRegDateStr(String regDateStr) {
        this.regDateStr = regDateStr;
    }

    @Transient
    public String getTriageDateStr() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (triageDate != null) {
            triageDateStr = format.format(triageDate);
        }
        return triageDateStr;
    }

    public void setTriageDateStr(String triageDateStr) {
        this.triageDateStr = triageDateStr;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
