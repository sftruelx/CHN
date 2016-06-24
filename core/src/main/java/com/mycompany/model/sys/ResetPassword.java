package com.mycompany.model.sys;

import com.mycompany.model.BaseObject;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by liaoxiang on 2016/6/13.
 */

@Entity
public class ResetPassword extends BaseObject implements Serializable {

    private Long userId;
    private String oldPassword;
    private String newPassword;
    private String reconfirm;
    @Id
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getReconfirm() {
        return reconfirm;
    }

    public void setReconfirm(String reconfirm) {
        this.reconfirm = reconfirm;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }

        final ResetPassword role = (ResetPassword) o;

        return !(oldPassword != null ? !oldPassword.equals(role.oldPassword) : role.oldPassword != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (oldPassword != null ? oldPassword.hashCode() : 0);
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(this.oldPassword)
                .toString();
    }

}
