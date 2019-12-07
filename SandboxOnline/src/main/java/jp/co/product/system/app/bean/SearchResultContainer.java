package jp.co.product.system.app.bean;

import java.util.List;

import jp.co.product.system.common.bean.PagenationItem;

public class SearchResultContainer<T> implements PagenationItem {
	
	private int currentpage;
	private int totalpage;
	private int searchcount;
	private List<T> searchlist;
	
	@Override
	public int getCurrentpage() {
		return currentpage;
	}
	@Override
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	@Override
	public int getTotalpage() {
		return totalpage;
	}
	@Override
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	@Override
	public int getSearchcount() {
		return searchcount;
	}
	@Override
	public void setSearchcount(int searchcount) {
		this.searchcount = searchcount;
	}
	public List<T> getSearchlist() {
		return searchlist;
	}
	public void setSearchlist(List<T> searchlist) {
		this.searchlist = searchlist;
	}
}
