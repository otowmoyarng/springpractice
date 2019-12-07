package jp.co.product.system.app.service;

import java.util.List;

import jp.co.product.system.app.bean.SearchFormBean;
import jp.co.product.system.app.bean.SearchResultBean;
import jp.co.product.system.app.bean.SearchResultContainer;
import jp.co.product.system.app.form.SearchForm;
import jp.co.product.system.common.enums.Mode;

public interface SearchService {

	// 初期表示
	SearchForm init();
	
	// 検索処理
	SearchResultContainer<SearchResultBean> search(SearchFormBean form, Mode mode);
	
	// 検索処理
	SearchResultBean searchitem(String companykbn, String companyno, String companybno);
	
	// ファイル出力
	List<String> output(List<SearchResultBean> list);
}
