package com.mycompany.model.sys;

import com.mycompany.model.BaseObject;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wsd on 2016/5/26.
 */

@Entity
@Table(name = "sys_dictionary")
public class Dictionary extends BaseObject implements Serializable {

    private Long id;
    private String dict_key;
    private String dict_value;
    private String dict_describe;
    private String dict_remark;
    private String dict_parent;
    private Integer dict_type;
    private Integer delabled;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDict_key() {
        return dict_key;
    }

    public void setDict_key(String dict_key) {
        this.dict_key = dict_key;
    }

    public String getDict_value() {
        return dict_value;
    }

    public void setDict_value(String dict_value) {
        this.dict_value = dict_value;
    }

    public String getDict_describe() {
        return dict_describe;
    }

    public void setDict_describe(String dict_describe) {
        this.dict_describe = dict_describe;
    }

    public String getDict_remark() {
        return dict_remark;
    }

    public void setDict_remark(String dict_remark) {
        this.dict_remark = dict_remark;
    }

    public String getDict_parent() {
        return dict_parent;
    }

    public void setDict_parent(String dict_parent) {
        this.dict_parent = dict_parent;
    }

    public Integer getDict_type() {
        return dict_type;
    }

    public void setDict_type(Integer dict_type) {
        this.dict_type = dict_type;
    }

    public Integer getDelabled() {
        return delabled;
    }

    public void setDelabled(Integer delabled) {
        this.delabled = delabled;
    }


    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dictionary)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        return result;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(this.dict_remark).toString();
    }

}
