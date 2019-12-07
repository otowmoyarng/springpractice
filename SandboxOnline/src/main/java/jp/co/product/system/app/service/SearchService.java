package jp.co.product.system.app.service;

import java.util.List;

import jp.co.product.system.app.bean.CompanyFormBean;
import jp.co.product.system.app.bean.CompanyResultBean;
import jp.co.product.system.app.bean.SearchResultContainer;
import jp.co.product.system.app.form.CompanyForm;
import jp.co.product.system.common.enums.Mode;

public interface SearchService {

	// �����\��
	CompanyForm init();
	
	// ��������
	SearchResultContainer<CompanyResultBean> search(CompanyFormBean form, Mode mode);
	
	// ��������
	CompanyResultBean searchitem(String companykbn, String companyno, String companybno);
	
	// �t�@�C���o��
	List<String> output(List<CompanyResultBean> list);
}
