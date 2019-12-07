package jp.co.product.system.app.mapper;

import java.util.List;

import jp.co.product.system.app.entity.CompanyCondition;
import jp.co.product.system.app.entity.CompanyEntity;

public interface SearchMapper {

	// ��������
	int getCompanyListCount(CompanyCondition con);
	// ��������
	List<CompanyEntity> getCompanyList(CompanyCondition con);
}
