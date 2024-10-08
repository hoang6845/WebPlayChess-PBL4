package com.pbl4.model.bean;

import java.sql.Date;

public class RoleModel extends AbstractModel<RoleModel> {
	private String codeRole;
	private String nameRole;
	public RoleModel(long id, Date createDate, String createBy, Date modifiedDate, String modifiedBy, String codeRole,
			String nameRole) {
		super(id, createDate, createBy, modifiedDate, modifiedBy);
		this.codeRole = codeRole;
		this.nameRole = nameRole;
	}
	public RoleModel() {
		super();
	}
	public String getCodeRole() {
		return codeRole;
	}
	public void setCodeRole(String codeRole) {
		this.codeRole = codeRole;
	}
	public String getNameRole() {
		return nameRole;
	}
	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}
	
}
