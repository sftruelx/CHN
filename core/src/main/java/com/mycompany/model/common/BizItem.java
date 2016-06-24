package com.mycompany.model.common;

import com.mycompany.model.BaseObject;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wsd on 2016/6/14. 检查项目维护表
 */
@Entity
@Table(name = "biz_item")
public class BizItem  extends BaseObject implements Serializable {

    private Long id;
    private String itemCode;
    private String itemName;
    private String feeCode;
    private BigDecimal feePrice;
    private String itemAddress;
    private String itemGuide;
    private Integer itemType;
    private String typeItems;
    private String itemClass;
    private String remark;
    private String suggestion;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "item_code",nullable = false)
    @Field
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @Column(name = "item_name",nullable = false)
    @Field
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Column(name = "fee_code")
    @Field
    public String getFeeCode() {
        return feeCode;
    }

    public void setFeeCode(String feeCode) {
        this.feeCode = feeCode;
    }

    @Column(name = "fee_price")
    @Field
    public BigDecimal getFeePrice() {
        return feePrice;
    }

    public void setFeePrice(BigDecimal feePrice) {
        this.feePrice = feePrice;
    }

    @Column(name = "item_address")
    @Field
    public String getItemAddress() {
        return itemAddress;
    }

    public void setItemAddress(String itemAddress) {
        this.itemAddress = itemAddress;
    }

    @Column(name = "item_guide")
    @Field
    public String getItemGuide() {
        return itemGuide;
    }

    public void setItemGuide(String itemGuide) {
        this.itemGuide = itemGuide;
    }

    @Column(name = "item_type")
    @Field
    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    @Column(name = "type_items")
    @Field
    public String getTypeItems() {
        return typeItems;
    }

    public void setTypeItems(String typeItems) {
        this.typeItems = typeItems;
    }

    @Column(name = "item_class")
    @Field
    public String getItemClass() {
        return itemClass;
    }

    public void setItemClass(String itemClass) {
        this.itemClass = itemClass;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
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
