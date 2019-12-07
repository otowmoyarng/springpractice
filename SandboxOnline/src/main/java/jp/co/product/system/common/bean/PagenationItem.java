package jp.co.product.system.common.bean;

public interface PagenationItem {

	int getCurrentpage();

	void setCurrentpage(int currentpage);

	int getTotalpage();

	void setTotalpage(int totalpage);

	int getSearchcount();

	void setSearchcount(int searchcount);

}