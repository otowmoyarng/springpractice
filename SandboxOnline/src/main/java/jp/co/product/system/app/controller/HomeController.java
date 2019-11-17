package jp.co.product.system.app.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.product.system.sys.cache.SystemParameterUtils;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends ProductBaseConroller {
	
	/**
	 * ���K�[�C���X�^���X�擾
	 */
	@Override
	protected Class<?> getInnertype() {
		return getClass();
	}
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * �V�X�e�����[�g�A�N�Z�X�P
	 * ����N����ʂ�\������B
	 * @return	����N�����
	 */
	@RequestMapping("/")
	public String init1() {
		return "index";
	}
	
	/**
	 * �V�X�e�����[�g�A�N�Z�X�Q
	 * ����N����ʂ�\������B
	 * @return	����N�����
	 */
	@RequestMapping("/main")
	public String init2() {
		return "main";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		//logger.info("Welcome home! The client locale is {}.", locale);
		logger.info("Welcome home! The client locale is {}." + locale.toString());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		System.out.println("���s���\�b�h�Fhome");
		
		String result = SystemParameterUtils.getPString("search_count");
		
		return "home";
	}
}
