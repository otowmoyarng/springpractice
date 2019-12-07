package jp.co.product.system.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.product.system.app.bean.CompanyFormBean;
import jp.co.product.system.app.bean.CompanyResultBean;
import jp.co.product.system.app.bean.SearchResultContainer;
import jp.co.product.system.app.dxo.CompanyDxo;
import jp.co.product.system.app.dxo.PagenationItemDxo;
import jp.co.product.system.app.entity.CompanyCondition;
import jp.co.product.system.app.entity.CompanyEntity;
import jp.co.product.system.app.form.CompanyForm;
import jp.co.product.system.app.mapper.SearchMapper;
import jp.co.product.system.common.enums.Mode;
import jp.co.product.system.sys.cache.SystemParameterUtils;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private CompanyDxo companydxo;
	@Autowired
	private PagenationItemDxo pagenationdxo;
	@Autowired
	private SearchMapper mapper;
	
	/**
	 * 初期表示処理
	 * @return	SearchForm
	 */
	@Override
	public CompanyForm init() {
		CompanyForm form = new CompanyForm();
		form.setCompanykbn("1");
		return form;
	}
	
	/**
	 * 検索処理
	 * @param	form	検索条件
	 * @param	mode	Mode
	 * @return	検索結果
	 */
	@Override
	public SearchResultContainer<CompanyResultBean> search(CompanyFormBean form, Mode mode) {
		
		SearchResultContainer<CompanyResultBean> container = new SearchResultContainer<CompanyResultBean>();
		
		CompanyCondition con = companydxo.copyBeanToCondition(form, null);
		
		if (mode == Mode.OUTPUT) {
			con.setOutputmaxcount(SystemParameterUtils.getCsvMaxCount());
		} else {
			con.setSearchmaxcount(SystemParameterUtils.getSearchMaxCount());
		}
		con.setMode(mode.name());
		
		int searchcount;
		int totalpage;
		if (mode == Mode.SEARCH) {
			searchcount = mapper.getCompanyListCount(con);
			totalpage =
					(searchcount / (int)con.getSearchmaxcount())
				  + (searchcount % (int)con.getSearchmaxcount() > 0 ? 1 : 0);
		} else {
			searchcount = form.getSearchcount();
			totalpage = form.getTotalpage();
		}
		
		container.setSearchlist(getCompanyList());
		
		switch(mode) {
			case SEARCH:
				container.setCurrentpage(1);
				container.setTotalpage(totalpage);
				container.setSearchcount(searchcount);
				break;
			case FIRSTPAGE:
				pagenationdxo.copyPagenationItemValue(form, container);
				container.setCurrentpage(1);
				break;
			case PREVPAGE:
				pagenationdxo.copyPagenationItemValue(form, container);
				container.setCurrentpage(form.getCurrentpage() - 1);
				break;
			case NEXTPAGE:
				pagenationdxo.copyPagenationItemValue(form, container);
				container.setCurrentpage(form.getCurrentpage() + 1);
				break;
			case LASTPAGE:
				pagenationdxo.copyPagenationItemValue(form, container);
				container.setCurrentpage(form.getTotalpage());
				break;
			default:
				break;
		}
		
		return container;
	}

	/**
	 * 明細を取得する
	 */
	@Override
	public CompanyResultBean searchitem(String companykbn, String companyno, String companybno) {
	
		List<CompanyResultBean> searchlist = getCompanyList();
		CompanyResultBean result = null;
		for (CompanyResultBean entity : searchlist) {
			if (Objects.equals(companykbn, entity.getCompanykbn())
			 && Objects.equals(companyno, entity.getCompanyno())
			 && Objects.equals(companybno, entity.getCompanybno())) {
				result = entity;
				break;
			}
		}
		return result == null ? new CompanyResultBean() : result;
	}
	
	/**
	 * ファイル出力
	 * @param	検索結果
	 * @return	ファイル出力データリスト
	 */
	@Override
	public List<String> output(List<CompanyResultBean> list) {
		
		List<String> outputlist = new ArrayList<String>();
		
		// ヘッダー行
		outputlist.add("会社区分,会社名,会社カナ名,会社番号");
		
		// 明細行出力
		StringBuilder sb = null;
		for (CompanyResultBean row : list) {
			sb = new StringBuilder();
			sb.append(row.getCompanykbn() + ",");
			sb.append(row.getCompanyname() + ",");
			sb.append(row.getCompanyrname() + ",");
			sb.append(row.getCompanyno());
			outputlist.add(sb.toString());
		}
		
		return outputlist;
	}
	
	/**
	 * CompanyList取得
	 * @return	CompanyList
	 */
	private List<CompanyResultBean> getCompanyList() {
		return getCompanyList(null, Mode.SEARCH);
	}
	
	/**
	 * CompanyList取得
	 * @param	con	検索条件
	 * @param	mode	Mode
	 * @return	CompanyList
	 */
	private List<CompanyResultBean> getCompanyList(CompanyCondition con, Mode mode) {
		
		List<CompanyResultBean> beanlist = new ArrayList<CompanyResultBean>();
		List<CompanyEntity> entitylist = mapper.getCompanyList(con);
		for (CompanyEntity entity : entitylist) {
			beanlist.add(companydxo.copyEntityToBean(entity, null));
		}
		return beanlist;
	}
}
