package jp.co.product.system.app.mapper;

import java.util.List;

import jp.co.product.system.app.bean.SearchResultBean;
import jp.co.product.system.app.form.SearchForm;

public interface SearchMapper {

	// ��������
	List<SearchResultBean> getSearchList(SearchForm form);
}
