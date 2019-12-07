package jp.co.product.system.app.mapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.product.system.app.bean.SearchResultBean;

@Service
public class SearchMapperImpl implements SearchMapper {

	@Override
	public List<SearchResultBean> getSearchList() {
		List<SearchResultBean> searchlist = new ArrayList<SearchResultBean>();
		final SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd HH24:mm:ss");
		for (int count = 0; count < 10; count++) {
			final String kbn = Integer.toString(count + 1);
			
			//if (Objects.equals(form.getCompanykbn(), kbn)) {
				
				SearchResultBean bean = new SearchResultBean();
				bean.setCompanykbn(kbn);
				bean.setCompanyname("‰ïŽÐ–¼" + kbn);
				bean.setCompanyname("‰ïŽÐƒJƒi" + kbn);
				bean.setCompanyno("100" + kbn + "00");
				bean.setCompanybno("000001");
				bean.setHojinno("123456789000" + kbn);
				bean.setCreatedate(dateformat.format(new Date()));
				
				searchlist.add(bean);
			//}
		}
		return searchlist;
	}
}
