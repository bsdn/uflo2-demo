package test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bstek.uflo.console.provider.ProcessFile;
import com.bstek.uflo.console.provider.ProcessProvider;

import test.model.ProcessDbFile;
/**
下面是一个将模板文件存储位置修改为MySQL接口实现示例，思路为先创建一张用于存储模板文件的数据库表，然后再实现接口方法完成对该表CRUD操作，接口实现如下，仅供参考：
 * @author Lucas
 *
 */
@Component
public class FileDbProcessProvider implements ProcessProvider {
	public String prefix="db:";
	private boolean disabled = false;
	@Autowired
	private SessionFactory sessionFactory;
	public InputStream loadProcess(String fileName) {
		Session session = sessionFactory.openSession();
		try{
			ProcessDbFile file = (ProcessDbFile) session.createCriteria(ProcessDbFile.class).add(Restrictions.eq("name", getRealName(fileName))).uniqueResult();
			return new ByteArrayInputStream(file.getContent().getBytes());
		} finally {
			session.close();
		}
	}

	public List<ProcessFile> loadAllProcesses() {
		Session session = sessionFactory.openSession();
		try{
			@SuppressWarnings("unchecked")
			List<ProcessDbFile> dbFileList = session.createCriteria(ProcessDbFile.class).addOrder(Order.desc("createDate")).list();
		    List<ProcessFile> fileList = new ArrayList<ProcessFile>();
			for(ProcessDbFile df : dbFileList){
		    	ProcessFile f = new ProcessFile(df.getName(), df.getCreateDate());
		    	fileList.add(f);
			}
			return fileList;
		} finally {
			session.close();
		}
	}

	public void saveProcess(String fileName, String content) {
		Session session = sessionFactory.openSession();
		try{
			@SuppressWarnings("unchecked")
			List<ProcessDbFile> dfL = session.createCriteria(ProcessDbFile.class).add(Restrictions.eq("name", getRealName(fileName))).list();
			ProcessDbFile df = null;
			if(dfL.size() > 0){
				df = dfL.get(0);
			}
			if(df == null) {
				df = new ProcessDbFile();
				df.setId(UUID.randomUUID().toString());
				df.setContent(content);
				df.setCreateDate(new Date());
				df.setName(getRealName(fileName));
				session.save(df);
			} else {
				df.setContent(content);
				df.setCreateDate(new Date());
				session.update(df);
			}
		} finally {
			session.flush();
			session.close();
		} 
	}

	public void deleteProcess(String fileName) {
		Session session = sessionFactory.openSession();
		try{
			ProcessDbFile file = (ProcessDbFile) session.createCriteria(ProcessDbFile.class).add(Restrictions.eq("name", getRealName(fileName))).uniqueResult();
			session.delete(file);
		} finally {
			session.flush();
			session.close();
		} 
	}

	public String getName() {
		return "dbstore";
	}

	public String getPrefix() {
		return prefix;
	}

	public boolean support(String fileName) {
		return fileName.startsWith(prefix);
	}

	public boolean isDisabled() {
		return disabled;
	}

	private String getRealName(String name){
		if(name.startsWith(getPrefix())){
			return name.substring(name.indexOf(getPrefix())+3);
		}
		return name;
	}
}
