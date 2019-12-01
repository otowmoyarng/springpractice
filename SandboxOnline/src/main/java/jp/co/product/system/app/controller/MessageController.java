package jp.co.product.system.app.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/message")
public class MessageController extends ProductBaseConroller {

	@Override
	protected Class<?> getInnertype() {
		return this.getClass();
	}

	@RequestMapping(value = "/")
	public String init() {
		return "message";
	}
	
	@RequestMapping(value = "show/{errorflg}", method = RequestMethod.GET)
	public String message(Model model, @PathVariable("errorflg") String errorflg, Map<String,String> map) {
		logger.info("errorflg:" + errorflg);
		model.addAttribute("errorflg", errorflg == null ? "" : errorflg);
		//map.put("errorflg", errorflg == null ? "" : errorflg);
		return "message";
	}
}
