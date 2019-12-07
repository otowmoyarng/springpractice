package jp.co.product.system.app.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.product.system.app.bean.SearchFormBean;
import jp.co.product.system.app.bean.SearchResultBean;
import jp.co.product.system.app.bean.SearchResultContainer;
import jp.co.product.system.app.dxo.SearchFormDxo;
import jp.co.product.system.app.form.SearchForm;
import jp.co.product.system.app.service.SearchService;
import jp.co.product.system.common.enums.Mode;

@Controller
@RequestMapping("/search")
public class SearchController extends ProductBaseConroller {

	@Autowired
	private SearchFormDxo formdxo;
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
	 * @return
	 */
	@RequestMapping("/init")
	public ModelAndView init() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("searchDialog");
		mv.addObject("searchform", new SearchForm());
		mv.addObject("searchresult", new SearchResultContainer<SearchResultBean>());
		return mv;
	}
	
 	/**
	   * 検索処理
	 * @param	form	検索条件
	 * @return
	 */
	@RequestMapping(value = "/dosearch", method = RequestMethod.POST)
	public ModelAndView search(@ModelAttribute("searchform") SearchForm form) {
		
		SearchFormBean formbean = this.formdxo.copyFormToBean(form, null);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("searchDialog");
		mv.addObject("searchform", form);
		mv.addObject("searchresult", this.service.search(formbean, Mode.SEARCH));
		return mv;
	}
}