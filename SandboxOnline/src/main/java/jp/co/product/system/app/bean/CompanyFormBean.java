package jp.co.product.system.app.bean;

import jp.co.product.system.common.bean.PagenationItem;

public class CompanyFormBean implements PagenationItem {

	private String companykbn;
	private String companyno;
	private int currentpage;
	private int totalpage;
	private int searchcount;
	
	public String getCompanykbn() {
		return companykbn;
	}
	public void setCompanykbn(String companykbn) {
		this.companykbn = companykbn;
	}
	public String getCompanyno() {
		return companyno;
	}
	public void setCompanyno(String companyno) {
		this.companyno = companyno;
	}
	
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
}
