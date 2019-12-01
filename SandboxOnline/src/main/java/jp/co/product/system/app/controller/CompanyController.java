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
	 * ���K�[�C���X�^���X�擾
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
	 * �����\��
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
	 * ��������
	 * @param	form	��������
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
	 * �ڍ׃y�[�W�֑J�ڂ���B
	 * 
	 * @param	companykbn	��Ћ敪
	 * @param	companyno	��Дԍ�
	 * @param	companybno	��Ў}�ԍ�
	 * @param	form		��������
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
		
		// �����������Z�b�V��������폜���Ă���ǉ�����
		sessionStatus.setComplete();
		model.addAttribute("CompanySessionKey", convertFormToSessionDTO(form));
		request.setAttribute("companykbn", companykbn);
		request.setAttribute("companyno", companyno);
		request.setAttribute("companybno", companybno);
		return "forward:/companyitem/init?init";
	}
	
 	/**
	 * ��������
	 * @param	form	��������
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
	 * SearchForm��CompanySessionDTO�ɕϊ�����
	 * 
	 * @param	src	��������
	 * @return
	 */
	private CompanySessionDTO convertFormToSessionDTO(SearchForm src) {
		
		CompanySessionDTO trg = new CompanySessionDTO();
		
		trg.setCompanykbn(src.getCompanykbn());
		trg.setCompanyno(src.getCompanyno());
		
		return trg;
	}
	
	/**
	 * SearchForm��CompanySessionDTO�ɕϊ�����
	 * 
	 * @param	src	��������
	 * @return
	 */
	private SearchForm convertSessionDTOToForm(CompanySessionDTO src) {
		
		SearchForm trg = new SearchForm();
		
		trg.setCompanykbn(src.getCompanykbn());
		trg.setCompanyno(src.getCompanyno());
		
		return trg;
	}
}