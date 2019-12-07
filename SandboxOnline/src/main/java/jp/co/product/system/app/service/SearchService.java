package jp.co.product.system.app.service;

import java.util.List;

import jp.co.product.system.app.bean.CompanyFormBean;
import jp.co.product.system.app.bean.CompanyResultBean;
import jp.co.product.system.app.bean.SearchResultContainer;
import jp.co.product.system.app.form.CompanyForm;
import jp.co.product.system.common.enums.Mode;

public interface SearchService {

	// 初期表示
	CompanyForm init();
	
	// 検索処理
	SearchResultContainer<CompanyResultBean> search(CompanyFormBean form, Mode mode);
	
	// 検索処理
	CompanyResultBean searchitem(String companykbn, String companyno, String companybno);
	
	// ファイル出力
	List<String> output(List<CompanyResultBean> list);
}
