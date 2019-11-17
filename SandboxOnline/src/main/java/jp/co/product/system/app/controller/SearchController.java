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
	 * ���K�[�C���X�^���X�擾
	 */
	@Override
	protected Class<?> getInnertype() {
		return getClass();
	}
	
	/**
	 * �����\��
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
	 * ��������
	 * @param	form	��������
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
	 * �t�@�C���_�E�����[�h
	 * @param	form	��������
	 * @return
	 */
	@RequestMapping(value = "/output", method = RequestMethod.GET)
	public String output(@ModelAttribute("searchform") SearchForm form, HttpServletResponse response) {
		
		// HTTP�w�b�_�[�ݒ�
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + "�ꗗ_" + currentDateTime() +".csv");
		
		// �o�͓��e
		SearchResultContainer<SearchResultBean> container = this.service.search(form);
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
	 * ���݂̓��t�Ǝ������擾����B
	 * @return	��������(yyyyMMddHHmmss�`��)
	 */
	private String currentDateTime() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
}