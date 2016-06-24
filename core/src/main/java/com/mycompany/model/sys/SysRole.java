package com.mycompany.model.sys;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycompany.model.BaseObject;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



/**
 * @author william
 */
@Entity
@Table(name = "sys_role")
public class SysRole extends BaseObject implements Serializable, GrantedAuthority {
    private Long id;
    private String roleName;
    private String roleDesc;
    //springSecurity角色名ROLE_xxx
    private String roleSecurity;

    private Set<SysRight> sysRights = new HashSet<>();

    private Integer roleFlag = 1;



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "role_name", nullable = false)
    @Field
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Column(name = "role_desc", nullable = false)
    @Field
    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Column(name = "role_flag", nullable = false)
    @Field
    public Integer getRoleFlag() {
        return roleFlag;
    }

    public void setRoleFlag(Integer roleFlag) {
        this.roleFlag = roleFlag;
    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(
            name = "sys_role_right",
            joinColumns = { @JoinColumn(name = "role_id") },
            inverseJoinColumns = @JoinColumn(name = "right_id")
    )
    public Set<SysRight> getSysRights() {
        return sysRights;
    }

    public void setSysRights(Set<SysRight> sysRights) {
        this.sysRights = sysRights;
    }




    @Column(name = "role_security", nullable = false)
    @Field
    public String getRoleSecurity() {
        return roleSecurity;
    }

    @Transient
    public String getAuthority() {
        return getRoleSecurity();
    }
    
    public void setRoleSecurity(String roleSecurity) {
        this.roleSecurity = roleSecurity;
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysRole)) {
            return false;
        }

        final SysRole sysRole = (SysRole) o;

        return !(roleName != null ? !roleName.equals(sysRole.roleName) : sysRole.roleName != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (roleName != null ? roleName.hashCode() : 0);
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(this.roleName)
                .append(this.roleDesc)
                .toString();
    }



}
