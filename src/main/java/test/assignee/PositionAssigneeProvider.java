package test.assignee;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.assign.AssigneeProvider;
import com.bstek.uflo.process.assign.Entity;
import com.bstek.uflo.process.assign.PageQuery;
import com.bstek.uflo.service.IdentityService;

@Component
public class PositionAssigneeProvider implements AssigneeProvider {
	@Autowired
	@Qualifier("identityServiceImpl")
	private IdentityService identityService;
	private boolean disabledDeptAssigneeProvider;
	public boolean isTree() {
		return true;
	}
	
	public String getName() {
		return "指定岗位";
	}
	
	public void queryEntities(PageQuery<Entity> pageQuery, String parentId) {
		identityService.positionPageQuery(pageQuery,parentId);
	}
	
	public Collection<String> getUsers(String entityId,Context context,ProcessInstance processInstance) {
		return identityService.getUsersByDept(entityId);
	}

	public boolean disable() {
		return disabledDeptAssigneeProvider;
	}
	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}

	public boolean isDisabledDeptAssigneeProvider() {
		return disabledDeptAssigneeProvider;
	}

	public void setDisabledDeptAssigneeProvider(boolean disabledDeptAssigneeProvider) {
		this.disabledDeptAssigneeProvider = disabledDeptAssigneeProvider;
	}

}
