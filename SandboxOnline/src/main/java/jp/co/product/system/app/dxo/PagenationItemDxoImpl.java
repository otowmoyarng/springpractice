package jp.co.product.system.app.dxo;

import org.springframework.stereotype.Component;

import jp.co.product.system.common.bean.PagenationItem;

@Component
public class PagenationItemDxoImpl implements PagenationItemDxo {

	/**
	 * SearchResultContainer����SearchForm�֒l�R�s�[
	 */
	@Override
	public void copyPagenationItemValue(PagenationItem src, PagenationItem trg) {
		trg.setCurrentpage(src.getCurrentpage());
		trg.setTotalpage(src.getTotalpage());
		trg.setSearchcount(src.getSearchcount());
	}
}
