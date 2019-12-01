package jp.co.product.system.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import jp.co.product.system.app.bean.SearchResultBean;
import jp.co.product.system.app.bean.SearchResultContainer;
import jp.co.product.system.app.bean.CompanySessionDTO;
import jp.co.product.system.app.form.SearchForm;
import jp.co.product.system.app.service.SearchService;

@Controller
@RequestMapping("/company")
//@SessionAttributes(types = CompanySessionDTO.class, value = "CompanySessionKey")
public class CompanyController extends ProductBaseConroller {

	@Autowired
	private SearchService service;
	
	/**
	 * ロガーインスタンス取得
	 */
	@Override
	protected Class<?> getInnertype() {
		return getClass();
	}
	
	@ModelAttribute("searchform")
	SearchForm setupSearchForm() {
		return new SearchForm();
	}
	
	@ModelAttribute("searchresult")
	SearchResultContainer<SearchResultBean> setupSearchResultContainer() {
		return new SearchResultContainer<SearchResultBean>();
	}
	
	/**
	 * 初期表示
	 * @param	model	Mode
	 * @return
	 */
	@RequestMapping(value = "/", params = "init")
	public String init(Model model) {
//		model.addAttribute("searchform", new SearchForm());
//		model.addAttribute("searchresult", new SearchResultContainer<SearchResultBean>());
		return "companylist";
	}
	
 	/**
	 * 検索処理
	 * @param	form	検索条件
	 * @param	model	Mode
	 * @return
	 */
	@RequestMapping(value = "/search", params = "search", method = RequestMethod.POST)
	public String search(@ModelAttribute("searchform") SearchForm form, Model model) {
		
		model.addAttribute("searchform", form);
		model.addAttribute("searchresult", this.service.search(form));
		return "companylist";
	}
	
	/**
	 * 詳細ページへ遷移する。
	 * 
	 * @param	companykbn	会社区分
	 * @param	companyno	会社番号
	 * @param	companybno	会社枝番号
	 * @param	form		検索条件
	 * @param	model	Mode
	 * @return
	 */
//	@RequestMapping(value = "/item", method = RequestMethod.GET)
	public String item(@RequestParam(value = "companykbn", required = false) String companykbn,
					   @RequestParam(value = "companyno", required = false) String companyno,
					   @RequestParam(value = "companybno", required = false) String companybno,
					   @ModelAttribute("searchform") SearchForm form,
					   Model model,
					   SessionStatus sessionStatus,
					   HttpServletRequest request) {
		
		// 検索条件をセッションから削除してから追加する
		sessionStatus.setComplete();
		model.addAttribute("CompanySessionKey", convertFormToSessionDTO(form));
		request.setAttribute("companykbn", companykbn);
		request.setAttribute("companyno", companyno);
		request.setAttribute("companybno", companybno);
		return "forward:/companyitem/init?init";
	}
	
 	/**
	 * 検索処理
	 * @param	form	検索条件
	 * @param	model	Mode
	 * @return
	 */
	@RequestMapping(value = "/research")
	public String research(CompanySessionDTO sessiondto, Model model) {
		
		SearchForm form = convertSessionDTOToForm(sessiondto);
		model.addAttribute("searchform", form);
		model.addAttribute("searchresult", this.service.search(form));
		return "companylist";
	}
	
	/**
	 * SearchFormをCompanySessionDTOに変換する
	 * 
	 * @param	src	検索条件
	 * @return
	 */
	private CompanySessionDTO convertFormToSessionDTO(SearchForm src) {
		
		CompanySessionDTO trg = new CompanySessionDTO();
		
		trg.setCompanykbn(src.getCompanykbn());
		trg.setCompanyno(src.getCompanyno());
		
		return trg;
	}
	
	/**
	 * SearchFormをCompanySessionDTOに変換する
	 * 
	 * @param	src	検索条件
	 * @return
	 */
	private SearchForm convertSessionDTOToForm(CompanySessionDTO src) {
		
		SearchForm trg = new SearchForm();
		
		trg.setCompanykbn(src.getCompanykbn());
		trg.setCompanyno(src.getCompanyno());
		
		return trg;
	}
}