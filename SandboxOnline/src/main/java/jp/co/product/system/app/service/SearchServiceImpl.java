package jp.co.product.system.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.product.system.app.bean.SearchFormBean;
import jp.co.product.system.app.bean.SearchResultBean;
import jp.co.product.system.app.bean.SearchResultContainer;
import jp.co.product.system.app.dxo.PagenationItemDxo;
import jp.co.product.system.app.form.SearchForm;
import jp.co.product.system.app.mapper.SearchMapper;
import jp.co.product.system.common.enums.Mode;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private PagenationItemDxo pagenationdxo;
	@Autowired
	private SearchMapper mapper;
	
	/**
	 * 初期表示処理
	 * @return	SearchForm
	 */
	@Override
	public SearchForm init() {
		SearchForm form = new SearchForm();
		form.setCompanykbn("1");
		return form;
	}
	
	/**
	 * 検索処理
	 * @param	form	検索条件
	 * @return	検索結果
	 */
	@Override
	public SearchResultContainer<SearchResultBean> search(SearchFormBean form, Mode mode) {
		
		SearchResultContainer<SearchResultBean> container = new SearchResultContainer<SearchResultBean>();
		container.setSearchlist(mapper.getSearchList());
		
		switch(mode) {
			case SEARCH:
				container.setCurrentpage(1);
				container.setSearchcount(container.getSearchlist().size());
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
	public SearchResultBean searchitem(String companykbn, String companyno, String companybno) {
	
		List<SearchResultBean> searchlist = mapper.getSearchList();
		SearchResultBean result = null;
		for (SearchResultBean entity : searchlist) {
			if (Objects.equals(companykbn, entity.getCompanykbn())
			 && Objects.equals(companyno, entity.getCompanyno())
			 && Objects.equals(companybno, entity.getCompanybno())) {
				result = entity;
				break;
			}
		}
		return result == null ? new SearchResultBean() : result;
	}
	
	/**
	 * ファイル出力
	 * @param	検索結果
	 * @return	ファイル出力データリスト
	 */
	@Override
	public List<String> output(List<SearchResultBean> list) {
		
		List<String> outputlist = new ArrayList<String>();
		
		// ヘッダー行
		outputlist.add("会社区分,会社名,会社カナ名,会社番号");
		
		// 明細行出力
		StringBuilder sb = null;
		for (SearchResultBean row : list) {
			sb = new StringBuilder();
			sb.append(row.getCompanykbn() + ",");
			sb.append(row.getCompanyname() + ",");
			sb.append(row.getCompanyrname() + ",");
			sb.append(row.getCompanyno());
			outputlist.add(sb.toString());
		}
		
		return outputlist;
	}
}
