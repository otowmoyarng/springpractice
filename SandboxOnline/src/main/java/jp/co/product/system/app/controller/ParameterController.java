package jp.co.product.system.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.product.system.sys.cache.SystemParameterUtils;

@Controller
public class ParameterController {

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/param", method = RequestMethod.GET)
	public String displayParam(Model model) {
		
		System.out.println("実行メソッド：displayParam");
		
		// システムパラメータを取得する
		model.addAttribute("search_count", SystemParameterUtils.getPString("search_count"));
		model.addAttribute("csv_count", SystemParameterUtils.getPNumber("csv_count"));
		
		return "param";
	}
}