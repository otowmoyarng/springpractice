package jp.co.product.system.app.dxo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.product.system.app.bean.SearchFormBean;
import jp.co.product.system.app.form.SearchForm;

@Component
public class SearchFormDxoImpl implements SearchFormDxo {

	@Autowired
	private PagenationItemDxo pagenationdxo;
	
	@Override
	public SearchFormBean copyFormToBean(SearchForm form, SearchFormBean bean) {
		
		if (bean == null) {
			bean = new SearchFormBean();
		}
		bean.setCompanykbn(form.getCompanykbn());
		bean.setCompanyno(form.getCompanyno());
		pagenationdxo.copyPagenationItemValue(form, bean);
		
		return null;
	}
}
