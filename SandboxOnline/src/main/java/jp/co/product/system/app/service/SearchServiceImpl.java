package jp.co.product.system.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import jp.co.product.system.app.bean.SearchResultBean;
import jp.co.product.system.app.bean.SearchResultContainer;
import jp.co.product.system.app.form.SearchForm;

@Service
public class SearchServiceImpl implements SearchService {

	// TODO
//	@Autowired
//	private SearchMapper mapper;
	
	/**
	 * 検索処理
	 * @param	form	検索条件
	 * @return	検索結果
	 */
	@Override
	public SearchResultContainer<SearchResultBean> search(SearchForm form) {
		
		SearchResultContainer<SearchResultBean> container = new SearchResultContainer<SearchResultBean>();
		
		// TODO
		// サンプル実装での暫定対策
//		List<SearchResultBean> searchalllist = new ArrayList<SearchResultBean>();
//		for (int count = 0; count < 4; count++) {
//			final String kbn = Integer.toString(count + 1);
//			SearchResultBean bean = new SearchResultBean();
//			bean.setCompanykbn(kbn);
//			bean.setCompanyname("会社名" + kbn);
//			bean.setCompanyname("会社カナ" + kbn);
//			bean.setCompanyno("123456789000" + kbn);
//			searchalllist.add(bean);
//		}
		
		// 検索処理
//		List<SearchResultBean> searchlist = searchalllist.stream().filter(entity -> {
//			if (Objects.equals(form.getCompanyno(), "")) {
//				return Objects.equals(form.getCompanykbn(), entity.getCompanykbn());
//			} else {
//				return Objects.equals(form.getCompanykbn(), entity.getCompanykbn())
//					&& Objects.equals(form.getCompanyno(), entity.getCompanyno());
//			}
//		}).collect(Collectors.toList());
//		
//		if (searchlist == null)
//			searchlist = new ArrayList<SearchResultBean>();
		// TODO
		
		container.setSearchlist(getSearchList());
		
		return container;
	}

	/**
	 * 明細を取得する
	 */
	@Override
	public SearchResultBean searchitem(String companykbn, String companyno, String companybno) {
	
		List<SearchResultBean> searchlist = getSearchList();
		SearchResultBean result = searchlist.stream().filter(entity -> {
			return Objects.equals(companykbn, entity.getCompanykbn())
				 && Objects.equals(companyno, entity.getCompanyno())
				 && Objects.equals(companybno, entity.getCompanybno());
		}).findFirst().get();
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
	
	/**
	 * 検索結果を取得する
	 * @return
	 */
	List<SearchResultBean> getSearchList() {
		
		List<SearchResultBean> searchlist = new ArrayList<SearchResultBean>();
		for (int count = 0; count < 4; count++) {
			final String kbn = Integer.toString(count + 1);
			
			//if (Objects.equals(form.getCompanykbn(), kbn)) {
				
				SearchResultBean bean = new SearchResultBean();
				bean.setCompanykbn(kbn);
				bean.setCompanyname("会社名" + kbn);
				bean.setCompanyname("会社カナ" + kbn);
				bean.setCompanyno("100" + kbn + "00");
				bean.setCompanybno("000001");
				bean.setHojinno("123456789000" + kbn);
				searchlist.add(bean);
			//}
		}
		return searchlist;
	}
}
