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

import jp.co.product.system.app.bean.SearchResultBean;
import jp.co.product.system.app.bean.SearchResultContainer;
import jp.co.product.system.app.form.SearchForm;
import jp.co.product.system.app.service.SearchService;

@Controller
@RequestMapping("/search")
public class SearchController extends ProductBaseConroller {

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
	@RequestMapping(value = "/dosearch", method = RequestMethod.GET)
	public ModelAndView search(@ModelAttribute("searchform") SearchForm form) {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("searchDialog");
		mv.addObject("searchform", form);
		mv.addObject("searchresult", this.service.search(form));
		return mv;
	}
	
	/**
	 * ファイルダウンロード
	 * @param	form	検索条件
	 * @return
	 */
	@RequestMapping(value = "/output", method = RequestMethod.GET)
	public String output(@ModelAttribute("searchform") SearchForm form, HttpServletResponse response) {
		
		// HTTPヘッダー設定
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + "一覧_" + currentDateTime() +".csv");
		
		// 出力内容
		SearchResultContainer<SearchResultBean> container = this.service.search(form);
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
	 * 現在の日付と時刻を取得する。
	 * @return	処理日時(yyyyMMddHHmmss形式)
	 */
	private String currentDateTime() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
}