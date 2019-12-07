package jp.co.product.system.app.mapper;

import java.util.List;

import jp.co.product.system.app.entity.CompanyCondition;
import jp.co.product.system.app.entity.CompanyEntity;

public interface SearchMapper {

	// ŒŸõŒ”
	int getCompanyListCount(CompanyCondition con);
	// ŒŸõˆ—
	List<CompanyEntity> getCompanyList(CompanyCondition con);
}
