package com.pbl4.paging;

import com.pbl4.sorter.Sorter;

public class PageRequest implements Pageble{

	Integer page;
	Integer ItemsInPage;
	Sorter sorter;
	public PageRequest(Integer page, Integer ItemsInPage, Sorter s) {
		this.page = page;
		this.ItemsInPage = ItemsInPage;
		this.sorter = s;
	}
	
	public Sorter getSorter() {
		return sorter;
	}


	@Override
	public Integer getPage() {
		// TODO Auto-generated method stub
		return this.page;
	}

	@Override
	public Integer getOffset() {
		// TODO Auto-generated method stub
		if (this.page!=null && this.ItemsInPage!=null)
			return (this.getPage()-1)*this.getLimit();
		else return null;
	}

	@Override
	public Integer getLimit() {
		// TODO Auto-generated method stub
		return this.ItemsInPage;
	}

}
