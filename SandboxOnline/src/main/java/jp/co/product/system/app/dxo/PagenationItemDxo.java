package jp.co.product.system.app.dxo;

import jp.co.product.system.common.bean.PagenationItem;

public interface PagenationItemDxo {

	// SearchResultContainerからSearchFormへ値コピー
	void copyPagenationItemValue(PagenationItem src, PagenationItem trg);
}
