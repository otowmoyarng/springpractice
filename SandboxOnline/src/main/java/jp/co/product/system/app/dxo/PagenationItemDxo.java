package jp.co.product.system.app.dxo;

import jp.co.product.system.common.bean.PagenationItem;

public interface PagenationItemDxo {

	// SearchResultContainer����SearchForm�֒l�R�s�[
	void copyPagenationItemValue(PagenationItem src, PagenationItem trg);
}
