package com.pbl4.model.bean;

import java.sql.Date;
import java.util.ArrayList;

public class AbstractModel<T> {

	private long id;
	private Date createDate;
	private String createBy;
	private Date modifiedDate;
	private String modifiedBy;
	private ArrayList<T> listModel;
	
	public ArrayList<T> getListModel() {
		return listModel;
	}
	public void setListModel(ArrayList<T> listModel) {
		this.listModel = listModel;
	}
	public AbstractModel(long id, Date createDate, String createBy, Date modifiedDate, String modifiedBy) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.createBy = createBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
	}
	public AbstractModel() {
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
}
