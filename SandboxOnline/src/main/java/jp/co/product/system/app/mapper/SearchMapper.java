package jp.co.product.system.app.mapper;

import java.util.List;

import jp.co.product.system.app.bean.SearchResultBean;

public interface SearchMapper {

	// ��������
	List<SearchResultBean> getSearchList();
}
