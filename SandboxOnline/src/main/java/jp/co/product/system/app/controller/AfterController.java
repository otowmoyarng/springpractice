package jp.co.product.system.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AfterController {

	@RequestMapping(value = "/after/{name}", method = RequestMethod.GET)
    public String main(@PathVariable("name") String name, Model model) {
        model.addAttribute("name", name + "В≥Вс");
        return "after";
    }
}
