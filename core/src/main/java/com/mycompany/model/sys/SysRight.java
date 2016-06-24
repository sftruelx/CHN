package com.mycompany.model.sys;

import com.mycompany.model.BaseObject;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_right")
@Indexed
public class SysRight extends BaseObject implements Serializable {

	private Long id;

	private String rightText;

	private String rightUrl;

	private Long parentId;

	private SysRight parent;

	private String menuCode;

	private String rightDesc;



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "right_text",nullable = false, unique = true)
	@Field
	public String getRightText() {
		return rightText;
	}

	public void setRightText(String rightText) {
		this.rightText = rightText;
	}

	@Column(name = "right_url",nullable = false, unique = true)
	@Field
	public String getRightUrl() {
		return rightUrl;
	}

	public void setRightUrl(String rightUrl) {
		this.rightUrl = rightUrl;
	}

	@Column(name = "parent_id")
	@Field
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "menu_code")
	@Field
	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	@Column(name = "right_desc")
	@Field
	public String getRightDesc() {
		return rightDesc;
	}

	public void setRightDesc(String rightDesc) {
		this.rightDesc = rightDesc;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof SysRight)) {
			return false;
		}

		final SysRight sysRight = (SysRight) o;

		return !(rightText != null ? !rightText.equals(sysRight.rightText) : sysRight.rightText != null);

	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return (rightText != null ? rightText.hashCode() : 0);
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
				.append(this.rightText)
				.append(this.rightUrl)
				.toString();
	}
}
