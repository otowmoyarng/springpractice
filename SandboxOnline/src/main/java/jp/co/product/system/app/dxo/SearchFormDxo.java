package jp.co.product.system.app.dxo;

import jp.co.product.system.app.bean.SearchFormBean;
import jp.co.product.system.app.form.SearchForm;

public interface SearchFormDxo {

	SearchFormBean copyFormToBean(SearchForm form, SearchFormBean bean);
}
