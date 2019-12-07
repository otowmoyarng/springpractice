package jp.co.product.system.app.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import jp.co.product.system.app.bean.CompanyResultBean;
import jp.co.product.system.app.bean.SearchResultContainer;
import jp.co.product.system.app.dxo.PagenationItemDxo;
import jp.co.product.system.app.dxo.CompanyDxo;
import jp.co.product.system.app.bean.CompanySessionDTO;
import jp.co.product.system.app.bean.CompanyFormBean;
import jp.co.product.system.app.form.CompanyForm;
import jp.co.product.system.app.service.SearchService;
import jp.co.product.system.common.enums.Mode;

@Controller
@RequestMapping("/company")
//@SessionAttributes(types = CompanySessionDTO.class, value = "CompanySessionKey")
public class CompanyController extends ProductBaseConroller {

	@Autowired
	private CompanyDxo companydxo;
	@Autowired
	private PagenationItemDxo pagenationdxo;
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
	public String init(Model model) {
		model.addAttribute("searchform", this.service.init());
		return "companylist";
	}
	
 	/**
	 * 検索処理
	 * @param	form	検索条件
	 * @param	model	Mode
	 * @return
	 */
	@RequestMapping(value = "/search", params = "search", method = RequestMethod.POST)
	public String search(@ModelAttribute("searchform") CompanyForm form, Model model) {
		return searchmain(form, model, Mode.SEARCH);
	}
	
 	/**
	 * ページ送り	先頭ページ
	 * @param	form	検索条件
	 * @param	model	Mode
	 * @return
	 */
	@RequestMapping(value = "/search", params = "firstpage", method = RequestMethod.POST)
	public String firstpage(@ModelAttribute("searchform") CompanyForm form, Model model) {
		return searchmain(form, model, Mode.FIRSTPAGE);
	}
	
 	/**
	 * ページ送り	前ページ
	 * @param	form	検索条件
	 * @param	model	Mode
	 * @return
	 */
	@RequestMapping(value = "/search", params = "prevpage", method = RequestMethod.POST)
	public String prevpage(@ModelAttribute("searchform") CompanyForm form, Model model) {
		return searchmain(form, model, Mode.PREVPAGE);
	}
	
 	/**
	 * ページ送り	次ページ
	 * @param	form	検索条件
	 * @param	model	Mode
	 * @return
	 */
	@RequestMapping(value = "/search", params = "nextpage", method = RequestMethod.POST)
	public String nextpage(@ModelAttribute("searchform") CompanyForm form, Model model) {
		return searchmain(form, model, Mode.NEXTPAGE);
	}
	
 	/**
	 * ページ送り	最終ページ
	 * @param	form	検索条件
	 * @param	model	Mode
	 * @return
	 */
	@RequestMapping(value = "/search", params = "lastpage", method = RequestMethod.POST)
	public String lastpage(@ModelAttribute("searchform") CompanyForm form, Model model) {
		return searchmain(form, model, Mode.LASTPAGE);
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
	@RequestMapping(value = "/item", method = RequestMethod.GET)
	public String item(@RequestParam(value = "companykbn", required = false) String companykbn,
					   @RequestParam(value = "companyno", required = false) String companyno,
					   @RequestParam(value = "companybno", required = false) String companybno,
					   @ModelAttribute("searchform") CompanyForm form,
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
	 * ファイルダウンロード
	 * @param	form	検索条件
	 * @return
	 */
	@RequestMapping(value = "/output", method = RequestMethod.GET)
	public String output(@ModelAttribute("searchform") CompanyForm form, HttpServletResponse response) {
		
		// HTTPヘッダー設定
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + "一覧_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +".csv");
		
		// 出力内容
		CompanyFormBean formbean = this.companydxo.copyFormToBean(form, null);
		
		SearchResultContainer<CompanyResultBean> container = this.service.search(formbean, Mode.OUTPUT);
		
		if (container.getSearchlist().isEmpty()) {
			return null;
		}
		
 		// 出力処理
		final List<String> outputdata = this.service.output(container.getSearchlist());
		final byte[] b = outputdata.toString().getBytes();
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			os.write(b);
			os.flush();
		} catch (IOException e) {
			super.logger.error(e.toString());
		}
		return null;
	}
	
 	/**
	 * 検索処理
	 * @param	form	検索条件
	 * @param	model	Mode
	 * @return
	 */
	@RequestMapping(value = "/research")
	public String research(CompanySessionDTO sessiondto, Model model) {
		
		CompanyForm form = convertSessionDTOToForm(sessiondto);
		return searchmain(form, model, Mode.SEARCH);
	}
	
	/**
	 * 検索処理
	 * @param	form	検索条件
	 * @param	model	Mode
	 * @return
	 */
	private String searchmain(CompanyForm form, Model model, Mode mode) {
		
		CompanyFormBean formbean = this.companydxo.copyFormToBean(form, null);
		
		final SearchResultContainer<CompanyResultBean> container = this.service.search(formbean, mode);
		
		this.pagenationdxo.copyPagenationItemValue(container, form);
		
		model.addAttribute("searchform", form);
		model.addAttribute("searchresult", container);
		
		return "companylist";
	}
	
	/**
	 * SearchFormをCompanySessionDTOに変換する
	 * 
	 * @param	src	検索条件
	 * @return
	 */
	private CompanySessionDTO convertFormToSessionDTO(CompanyForm src) {
		
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
	private CompanyForm convertSessionDTOToForm(CompanySessionDTO src) {
		
		CompanyForm trg = new CompanyForm();
		
		trg.setCompanykbn(src.getCompanykbn());
		trg.setCompanyno(src.getCompanyno());
		
		return trg;
	}
}