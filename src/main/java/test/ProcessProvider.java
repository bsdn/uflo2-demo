package test;

import java.io.InputStream;

public interface ProcessProvider {
	/**根据文件名查询模板文件
	 * @param fileName
	 * @return
	 */
	InputStream loadProcess(String fileName);
	/**查询所有流程模板文件
	 * @return
	 */
	//List<ProcessFile> loadAllProcesses();
	/**保存模板文件
	 * @param fileName
	 * @param content
	 */
	void saveProcess(String fileName,String content);
	/**删除模板文件
	 * @param fileName
	 */
	void deleteProcess(String fileName);
	/**存储方式名称
	 * @return
	 */
	String getName();
	/**存储方式前缀
	 * @return
	 */
	String getPrefix();
	/**根据前缀判断是否该存储方式
	 * @param fileName
	 * @return
	 */
	boolean support(String fileName);
	/**是否禁用该存储方式，默认false
	 * @return
	 */
	boolean isDisabled();
}