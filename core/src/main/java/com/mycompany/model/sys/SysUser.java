package com.mycompany.model.sys;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.mycompany.model.BaseObject;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author william
 */

@Entity
@Table(name = "sys_user")
@Indexed
public class SysUser extends BaseObject implements Serializable, UserDetails  {

    private Long id;
    private String usrName;
    private String usrPassword;
    private Integer usrType;
    private String usrDepartment;
    private Integer usrFlag = 1;
    private String name;
    private String gender;
    private String usrUrl;
    private String usrPhone;
    private String usrEmail;
    private String[] roles;
    private Date createTime;

    private SysRole sysRole;
    private Set<SysRole> sysRoles = new HashSet<SysRole>();
    private boolean enabled = Boolean.TRUE.booleanValue();
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @DocumentId
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @OneToOne
    @JoinColumn(name = "usr_role_id", insertable = true, unique = true)
    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }
    @Column(name = "usr_name", nullable = false)
    @Field
    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }
    @Column(name = "usr_password", nullable = true)
    @Field
    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    @Transient
    public String getUsername() {
        return usrName;
    }

    public void setUsername(String usrName) {
        this.usrName = usrName;
    }

    @Transient
    public String getPassword() {
        return usrPassword;
    }

    public void setPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    @Column(name = "usr_type", nullable = true)
    @Field
    public Integer getUsrType() {
        return usrType;
    }

    public void setUsrType(Integer usrType) {
        this.usrType = usrType;
    }

    @Column(name = "usr_department", nullable = true)
    @Field
    public String getUsrDepartment() {
        return usrDepartment;
    }

    public void setUsrDepartment(String usrDepartment) {
        this.usrDepartment = usrDepartment;
    }

    @Column(name = "usr_flag", nullable = false)
    @Field
    public Integer getUsrFlag() {
        return usrFlag;
    }

    public void setUsrFlag(Integer usrFlag) {
        this.usrFlag = usrFlag;
    }
    @Column(name = "usr_url")
    @Field
    public String getUsrUrl() {
        return usrUrl;
    }

    public void setUsrUrl(String usrUrl) {
        this.usrUrl = usrUrl;
    }
    @Column(name = "usr_phone")
    @Field
    public String getUsrPhone() {
        return usrPhone;
    }

    public void setUsrPhone(String usrPhone) {
        this.usrPhone = usrPhone;
    }
    @Column(name = "usr_email")
    @Field
    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }
    @Column(name = "create_time")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Field
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Field
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Field
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Transient
    public String[] getRoles() {
        String[] roleIds ;
        if(roles != null) {
            return roles;
        }else{
            roleIds = new String[sysRoles.size()];
            int i = 0;
            for(SysRole role : sysRoles){
                roleIds[i++] = role.getId().toString();
            }
            return roleIds;
        }
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }




    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(
            name = "sys_user_role",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    public Set<SysRole> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(Set<SysRole> sysRoles) {
        this.sysRoles = sysRoles;
    }

    @Transient
    public boolean isEnabled() {
        return Boolean.TRUE;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Transient
    public boolean isAccountExpired() {
        return accountExpired;
    }
    @Transient
    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }
    @Transient
    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    /**
     * @return GrantedAuthority[] an array of roles.
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */

    @Transient
    @JsonIgnore // needed for UserApiITest in appfuse-ws archetype
    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new LinkedHashSet<>();
        for(SysRole role : sysRoles) {
            authorities.add(role);
        }
        return authorities;
    }

    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }


    public void setAccountNonLocked(boolean accountLocked) {

    }

    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }
    public void setAccountNonExpired(boolean accountExpired) {

    }


    public void setAccountExpired(boolean accountExpired) {

    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
     * @return true if credentials haven't expired
     */
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysUser)) {
            return false;
        }

        final SysUser sysUser = (SysUser) o;

        return !(usrName != null ? !usrName.equals(sysUser.usrName) : sysUser.usrName != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (usrName != null ? usrName.hashCode() : 0);
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(this.usrName)
                .toString();
    }

}
