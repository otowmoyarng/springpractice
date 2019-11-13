package jp.co.product.system.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BeforeController {

	@RequestMapping(value = "/before1", method = RequestMethod.GET)
    public String before1(Model model) {
        return "before1";
    }
	
	@RequestMapping(value = "/before2", method = RequestMethod.GET)
    public String before2(Model model) {
        return "before2";
    }
}
