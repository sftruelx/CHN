package com.mycompany.model.sys;

import com.mycompany.model.BaseObject;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by liaoxiang on 2016/4/19.
 */

@Entity
@Table(name = "biz_trainning")
public class Trainning  extends BaseObject implements Serializable {

    private Long id;
    private Long patient_id;
    private int heightBefore;
    private int heightNow;
    private int weightBefore;
    private int weightNow;
    private String content;
    private String result;
    private String advise;
    private String operator;
    private int status;
    private Date createDate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
    }

    public int getHeightBefore() {
        return heightBefore;
    }

    public void setHeightBefore(int heightBefore) {
        this.heightBefore = heightBefore;
    }

    public int getHeightNow() {
        return heightNow;
    }

    public void setHeightNow(int heightNow) {
        this.heightNow = heightNow;
    }

    public int getWeightBefore() {
        return weightBefore;
    }

    public void setWeightBefore(int weightBefore) {
        this.weightBefore = weightBefore;
    }

    public int getWeightNow() {
        return weightNow;
    }

    public void setWeightNow(int weightNow) {
        this.weightNow = weightNow;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

        final Trainning role = (Trainning) o;

        return !(content != null ? !content.equals(role.content) : role.content != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (content != null ? content.hashCode() : 0);
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(this.content)
                .toString();
    }
}
