package test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bstek.uflo.env.AdditionalInfoProvider;
@Component
public class AdditionalInfoProviderImpl implements AdditionalInfoProvider{

	private List<String> cateList = new ArrayList<String>();
	public List<String> categories() {
		cateList.clear();
		cateList.add("财务类");
		cateList.add("合同类");
		cateList.add("HR类");
		return cateList;
	}

}
