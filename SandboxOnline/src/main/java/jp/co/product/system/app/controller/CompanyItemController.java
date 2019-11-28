package jp.co.product.system.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.product.system.app.bean.SearchResultBean;
import jp.co.product.system.app.bean.SearchResultContainer;
import jp.co.product.system.app.form.SearchForm;
import jp.co.product.system.app.service.SearchService;

@Controller
@RequestMapping("/companyitem")
public class CompanyItemController extends ProductBaseConroller {

	@Autowired
	private SearchService service;

	/**
	 * ロガーインスタンス取得
	 */
	@Override
	protected Class<?> getInnertype() {
		return getClass();
	}
	
	/**
	 * 初期表示
	 * @param	model	Mode
	 * @return
	 */
	@RequestMapping(value = "/", params = "init")
	public String init(Model model,
						HttpServletRequest request) {
		String companykbn = request.getAttribute("companykbn").toString();
		String companyno = request.getAttribute("companyno").toString();
		String companybno = request.getAttribute("companybno").toString();
		
		model.addAttribute("searchform", this.service.searchitem(companykbn, companyno, companybno));
		return "companyitem";
	}
	
}
