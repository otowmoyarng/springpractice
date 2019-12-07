package jp.co.product.system.app.dxo;

import jp.co.product.system.app.bean.CompanyFormBean;
import jp.co.product.system.app.bean.CompanyResultBean;
import jp.co.product.system.app.entity.CompanyEntity;
import jp.co.product.system.app.form.CompanyForm;

public interface CompanyDxo {

	CompanyFormBean copyFormToBean(CompanyForm form, CompanyFormBean bean);
	
	CompanyResultBean copyEntityToBean(CompanyEntity entity, CompanyResultBean bean);
}
