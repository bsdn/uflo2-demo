package test.assignee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.assign.AssigneeProvider;
import com.bstek.uflo.process.assign.Entity;
import com.bstek.uflo.process.assign.PageQuery;

@Component
public class UsersProvider implements AssigneeProvider {

	public boolean disable() {
		return false;
	}

	public String getName() {
		return "指定用户";
	}

	public Collection<String> getUsers(String arg0, Context arg1, ProcessInstance arg2) {
		List<String> users = new ArrayList<String>();
		users.add("user1");
		users.add("user2");
		return users;
	}

	public boolean isTree() {
		return false;
	}

	public void queryEntities(PageQuery<Entity> arg0, String arg1) {
	}

}
