package jp.co.product.system.app.dxo;

import jp.co.product.system.app.bean.CompanyFormBean;
import jp.co.product.system.app.bean.CompanyResultBean;
import jp.co.product.system.app.entity.CompanyCondition;
import jp.co.product.system.app.entity.CompanyEntity;
import jp.co.product.system.app.form.CompanyForm;

public interface CompanyDxo {

	CompanyFormBean copyFormToBean(CompanyForm form, CompanyFormBean bean);
	
	CompanyCondition copyBeanToCondition(CompanyFormBean bean, CompanyCondition con);
	
	CompanyResultBean copyEntityToBean(CompanyEntity entity, CompanyResultBean bean);
}
