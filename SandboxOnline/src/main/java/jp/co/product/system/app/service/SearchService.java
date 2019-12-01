package jp.co.product.system.app.service;

import java.util.List;

import jp.co.product.system.app.bean.SearchResultBean;
import jp.co.product.system.app.bean.SearchResultContainer;
import jp.co.product.system.app.form.SearchForm;

public interface SearchService {

	// 検索処理
	SearchResultContainer<SearchResultBean> search(SearchForm form);
	
	// 検索処理
	SearchResultBean searchitem(String companykbn, String companyno, String companybno);
	
	// ファイル出力
	List<String> output(List<SearchResultBean> list);
}
