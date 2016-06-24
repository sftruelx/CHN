package com.mycompany.model.sys;

import com.mycompany.model.BaseObject;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by liaoxiang on 2016/4/13.
 */


@Entity
@Table(name = "sys_role")
public class RoleCopy extends BaseObject implements Serializable, GrantedAuthority {

    private static final long serialVersionUID = 3690197650654049848L;
    private Long id;
    private String name;
    private String description;
    private Set<SysRight> sysRights = new HashSet<SysRight>();
    /**
     * Default constructor - creates a new instance with no values set.
     */
    public RoleCopy() {
    }

    /**
     * Create a new instance and set the name.
     *
     * @param name name of the RoleCopy.
     */
    public RoleCopy(final String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    /**
     * @return the name property (getAuthority required by Acegi's GrantedAuthority interface)
     * @see org.springframework.security.core.GrantedAuthority#getAuthority()
     */
    @Transient
    public String getAuthority() {
        return getName();
    }

    @Column(name ="role_name", length = 20)
    public String getName() {
        return this.name;
    }

    @Column(name = "role_desc", length = 64)
    public String getDescription() {
        return this.description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
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
    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleCopy)) {
            return false;
        }

        final RoleCopy roleCopy = (RoleCopy) o;

        return !(name != null ? !name.equals(roleCopy.name) : roleCopy.name != null);

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
