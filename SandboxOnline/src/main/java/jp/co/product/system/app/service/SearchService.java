package jp.co.product.system.app.service;

import java.util.List;

import jp.co.product.system.app.bean.SearchFormBean;
import jp.co.product.system.app.bean.SearchResultBean;
import jp.co.product.system.app.bean.SearchResultContainer;
import jp.co.product.system.app.form.SearchForm;
import jp.co.product.system.common.enums.Mode;

public interface SearchService {

	// �����\��
	SearchForm init();
	
	// ��������
	SearchResultContainer<SearchResultBean> search(SearchFormBean form, Mode mode);
	
	// ��������
	SearchResultBean searchitem(String companykbn, String companyno, String companybno);
	
	// �t�@�C���o��
	List<String> output(List<SearchResultBean> list);
}
