package jp.co.product.system.app.service;

import java.util.List;

import jp.co.product.system.app.bean.SearchResultBean;
import jp.co.product.system.app.bean.SearchResultContainer;
import jp.co.product.system.app.form.SearchForm;

public interface SearchService {

	// ��������
	SearchResultContainer<SearchResultBean> search(SearchForm form);
	
	// �t�@�C���o��
	List<String> output(List<SearchResultBean> list);
}
