package com.mycompany.model.nurse;

import com.mycompany.model.BaseObject;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wsd on 2016/6/14. 分诊管理明细表
 */
@Entity
@Table(name = "biz_triage_detail")
public class TriageDetail extends BaseObject implements Serializable {

    private Long id;
    private String triageNo;
    private String outpatientNo;
    private String name;
    private Integer gender;
    private String itemCode;
    private String itemName;
    private Date completeDate;
    private String bizerCode;
    private String bizerName;
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

    @Column(name = "item_code")
    @Field
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @Column(name = "item_name")
    @Field
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Column(name = "complete_date")
    @Field
    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    @Column(name = "bizer_code")
    @Field
    public String getBizerCode() {
        return bizerCode;
    }

    public void setBizerCode(String bizerCode) {
        this.bizerCode = bizerCode;
    }

    @Column(name = "bizer_name")
    @Field
    public String getBizerName() {
        return bizerName;
    }

    public void setBizerName(String bizerName) {
        this.bizerName = bizerName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
