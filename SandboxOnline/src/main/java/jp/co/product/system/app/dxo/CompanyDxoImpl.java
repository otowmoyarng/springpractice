package jp.co.product.system.app.dxo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.product.system.app.bean.CompanyFormBean;
import jp.co.product.system.app.bean.CompanyResultBean;
import jp.co.product.system.app.entity.CompanyEntity;
import jp.co.product.system.app.form.CompanyForm;

@Component
public class CompanyDxoImpl implements CompanyDxo {

	@Autowired
	private PagenationItemDxo pagenationdxo;
	
	@Override
	public CompanyFormBean copyFormToBean(CompanyForm form, CompanyFormBean bean) {
		
		if (bean == null) {
			bean = new CompanyFormBean();
		}
		bean.setCompanykbn(form.getCompanykbn());
		bean.setCompanyno(form.getCompanyno());
		pagenationdxo.copyPagenationItemValue(form, bean);
		
		return bean;
	}

	@Override
	public CompanyResultBean copyEntityToBean(CompanyEntity entity, CompanyResultBean bean) {
		
		if (bean == null) {
			bean = new CompanyResultBean();
		}
		bean.setCompanykbn(entity.getCompanykbn());
		bean.setCompanyname(entity.getCompanyname());
		bean.setCompanyrname(entity.getCompanyrname());
		bean.setCompanyno(entity.getCompanyno());
		bean.setCompanybno(entity.getCompanybno());
		bean.setHojinno(entity.getHojinno());
		bean.setCreatedate(entity.getCreatedate());
		return bean;
	}
}
