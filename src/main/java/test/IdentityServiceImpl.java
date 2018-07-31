package test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bstek.uflo.process.assign.Entity;
import com.bstek.uflo.process.assign.PageQuery;
import com.bstek.uflo.service.impl.DefaultIdentityService;

@Component
public class IdentityServiceImpl extends DefaultIdentityService {

	@Override
	public void positionPageQuery(PageQuery<Entity> query, String parentId) {
		if (parentId == null) {
			query.setRecordCount(10);
			List<Entity> result = new ArrayList<Entity>();
			for (int i = 0; i < 10; i++) {
				Entity e = new Entity("id" + i, "posi" + i);
				result.add(e);
			}
			query.setResult(result);
		} else {
			query.setRecordCount(5);
			List<Entity> result = new ArrayList<Entity>();
			for (int i = 0; i < 5; i++) {
				Entity e = new Entity("id" + parentId + i, "posi" + parentId + i);
				result.add(e);
			}
			query.setResult(result);
		}
	}

}
