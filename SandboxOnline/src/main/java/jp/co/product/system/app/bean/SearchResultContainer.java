package jp.co.product.system.app.bean;

import java.util.List;

public class SearchResultContainer<T> {
	
	private String currentpage;
	private String totalpage;
	private String searchcount;
	private List<T> searchlist;
	
	public String getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(String currentpage) {
		this.currentpage = currentpage;
	}
	public String getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(String totalpage) {
		this.totalpage = totalpage;
	}
	public String getSearchcount() {
		return searchcount;
	}
	public void setSearchcount(String searchcount) {
		this.searchcount = searchcount;
	}
	public List<T> getSearchlist() {
		return searchlist;
	}
	public void setSearchlist(List<T> searchlist) {
		this.searchlist = searchlist;
	}
}
