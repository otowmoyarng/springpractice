package jp.co.product.system.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.product.system.app.form.CompanyForm;
import jp.co.product.system.app.form.CompanyItemForm;
import jp.co.product.system.app.service.SearchService;

@Controller
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
	 * @param	companykbn	会社区分
	 * @param	companyno	会社番号
	 * @param	companybno	会社番号(枝番)
	 * @param	model		Mode
	 * @param	form		SearchForm
	 * @return
	 */
	@RequestMapping(value = "/companyitem", method = RequestMethod.GET)
	public String init(@RequestParam(value = "companykbn", required = false) String companykbn,
					   @RequestParam(value = "companyno", required = false) String companyno,
					   @RequestParam(value = "companybno", required = false) String companybno,
					   Model model,
					   RedirectAttributes redirectAttribute,
					   @ModelAttribute("searchform") CompanyForm form) {
		
		// 一覧画面で選択したリンクのパラメーターとformが設定されている
		// ログ
		this.logger.debug("Companykbn:" + form.getCompanykbn());
		this.logger.debug("Companyno:" + form.getCompanyno());
		
		model.addAttribute("companyitem", this.service.searchitem(companykbn, companyno, companybno));
//		redirectAttribute.addAttribute("companyitem", this.service.searchitem(companykbn, companyno, companybno));
		
//		return "redirect:companyitem";
		return "companyitem";
	}
	
	/**
	 * 初期表示
	 * @param	model	Mode
	 * @param	request	HTTPRequest
	 * @param	form	SearchForm
	 * @return
	 */
	@RequestMapping(value = "/companyitem/init", params = "init")
	public String init(Model model,
						HttpServletRequest request,
						@ModelAttribute("searchform") CompanyForm form) {
		
		String companykbn = request.getAttribute("companykbn").toString();
		String companyno = request.getAttribute("companyno").toString();
		String companybno = request.getAttribute("companybno").toString();
		
		// 一覧画面で選択したリンクのパラメーターとformが設定されている
		// ログ
		this.logger.debug("Companykbn:" + form.getCompanykbn());
		this.logger.debug("Companyno:" + form.getCompanyno());
		
		model.addAttribute("companyitem", this.service.searchitem(companykbn, companyno, companybno));
		
		return "companyitem";
	}
	
	/**
	 * 更新処理
	 */
	@RequestMapping(value = "/companyitem", params = "update", method = RequestMethod.POST)
	public void update(@ModelAttribute("companyitem") CompanyItemForm form) {
		System.out.print("1tuuka 1");
	}
}
