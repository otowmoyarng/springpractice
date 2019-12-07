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
	 * ���K�[�C���X�^���X�擾
	 */
	@Override
	protected Class<?> getInnertype() {
		return getClass();
	}
	
	/**
	 * �����\��
	 * @param	model	Mode
	 * @return
	 */
	@RequestMapping(value = "/", params = "init")
	public String init(Model model) {
		model.addAttribute("searchform", this.service.init());
		return "companylist";
	}
	
 	/**
	 * ��������
	 * @param	form	��������
	 * @param	model	Mode
	 * @return
	 */
	@RequestMapping(value = "/search", params = "search", method = RequestMethod.POST)
	public String search(@ModelAttribute("searchform") CompanyForm form, Model model) {
		return searchmain(form, model, Mode.SEARCH);
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
	@RequestMapping(value = "/item", method = RequestMethod.GET)
	public String item(@RequestParam(value = "companykbn", required = false) String companykbn,
					   @RequestParam(value = "companyno", required = false) String companyno,
					   @RequestParam(value = "companybno", required = false) String companybno,
					   @ModelAttribute("searchform") CompanyForm form,
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
	 * �t�@�C���_�E�����[�h
	 * @param	form	��������
	 * @return
	 */
	@RequestMapping(value = "/output", method = RequestMethod.GET)
	public String output(@ModelAttribute("searchform") CompanyForm form, HttpServletResponse response) {
		
		// HTTP�w�b�_�[�ݒ�
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + "�ꗗ_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +".csv");
		
		// �o�͓��e
		CompanyFormBean formbean = this.companydxo.copyFormToBean(form, null);
		
		SearchResultContainer<CompanyResultBean> container = this.service.search(formbean, Mode.OUTPUT);
		
		if (container.getSearchlist().isEmpty()) {
			return null;
		}
		
 		// �o�͏���
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
	 * ��������
	 * @param	form	��������
	 * @param	model	Mode
	 * @return
	 */
	@RequestMapping(value = "/research")
	public String research(CompanySessionDTO sessiondto, Model model) {
		
		CompanyForm form = convertSessionDTOToForm(sessiondto);
		return searchmain(form, model, Mode.SEARCH);
	}
	
	/**
	 * ��������
	 * @param	form	��������
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
	 * SearchForm��CompanySessionDTO�ɕϊ�����
	 * 
	 * @param	src	��������
	 * @return
	 */
	private CompanySessionDTO convertFormToSessionDTO(CompanyForm src) {
		
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
	private CompanyForm convertSessionDTOToForm(CompanySessionDTO src) {
		
		CompanyForm trg = new CompanyForm();
		
		trg.setCompanykbn(src.getCompanykbn());
		trg.setCompanyno(src.getCompanyno());
		
		return trg;
	}
}