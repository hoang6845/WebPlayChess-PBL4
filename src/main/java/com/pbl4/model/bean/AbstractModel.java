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
	
	private int page; //startPage
	private int totalPage;
	private int totalItems;
	private String sortName;
	private String sortBy;
	private String type;
	private int itemsInPage = 4;//tong so item 1 trang
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getItemsInPage() {
		return itemsInPage;
	}
	public void setItemsInPage(int itemsInPage) {
		this.itemsInPage = itemsInPage;
	}
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
