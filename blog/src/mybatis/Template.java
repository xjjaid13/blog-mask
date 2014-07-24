package mybatis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.util.DBHandle;
import com.util.FileHandle;

/**
 * 自动生成代码
 * @author Taylor
 * */
public class Template {

	public static void main(String[] args) throws DocumentException, IOException{
		
		String projectPath = System.getProperty("user.dir");
		
		/** 模版文件 */
		String templateFile = "C:/Users/OF-PC/git/datac2/WebContent/static/mybatis-template";
		
		/** 保存文件 */
		String saveFile = projectPath + "/";
		
		/** 数据库名 */
		String dbName = "datac";
		String dbUser = "root";
		String dbPassword = "111111";
		
		/** 是否覆盖 */
		boolean isCover = false;
		
		/** 数据库ip */
		String url = "jdbc:mysql://127.0.0.1:3306/"+dbName+"?characterEncoding=utf-8";
		
		/** 数据库用户名 */
		
		/** 数据库驱动 */
		String driver = "com.mysql.jdbc.Driver";
		
		/** 数据库密码 */
		
		DBHandle db = new DBHandle();
		db.openConnMysqlParam(driver, url, dbUser, dbPassword);
		System.out.println(templateFile);
		File parentFile = new File(templateFile); 
		File[] packageFile = parentFile.listFiles();
		
		/** 查询表 */
		String selectAllTable = "select table_name from information_schema.tables where table_schema='"+dbName+"'";
		String[][] schSelectAllTable = db.executeQuery(selectAllTable);
		
		/** 表名称数组 */
		ArrayList<String> tableArr = new ArrayList<String>();
		for(int i = 0; schSelectAllTable!= null && i < schSelectAllTable.length; i++){
			tableArr.add(schSelectAllTable[i][0]);
		}
		/** 如果是config文件，只处理一份 */
		boolean first = false;
		
		for(int t = 0; schSelectAllTable != null && t < schSelectAllTable.length; t++){
			/** 查询栏目 */
			String schSelectAllcolumn = "SELECT column_name from information_schema.columns WHERE table_schema = '"+dbName+"' AND table_name = '"+schSelectAllTable[t][0]+"';";
			String[][] schSelectAllColumn = db.executeQuery(schSelectAllcolumn);
			/** 栏目名称数组 */
			ArrayList<String> columnArr = new ArrayList<String>();
			/** 栏目类型数组 */
			ArrayList<String> columnType = new ArrayList<String>();
			for(int c = 0; schSelectAllColumn != null && c < schSelectAllColumn.length; c++){
				columnArr.add(schSelectAllColumn[c][0]);
				int fieldType = db.getColumnType(schSelectAllTable[t][0],schSelectAllColumn[c][0] );
				if(!db.isString(fieldType)){
					columnType.add("Integer");
				}else{
					columnType.add("String");
				}
			}
			for(int i = 0; packageFile != null && i < packageFile.length; i++){
				File file = packageFile[i];
				String packageName = file.getName();
				packageName = packageName.replaceAll("\\.", "/");
				
				String savePath = saveFile + packageName;
				FileHandle.createPath(savePath);
				File[] fileTemplateList = file.listFiles();
				for(int j = 0; fileTemplateList != null && j < fileTemplateList.length; j++){
					File fileTemplate = fileTemplateList[j];
					String fileName = fileTemplate.getName();
					String templateContent = FileHandle.readFile(fileTemplate.getAbsolutePath());
					StringBuffer content = new StringBuffer();
					//处理config
					if(packageName.indexOf("config")!= -1){
						if(!first){
							String result = returnTableContent(templateContent,content,tableArr);
							FileHandle.write(savePath + "/" + fileName,result,true);
							first = true;
							continue;
						}else{
							continue;
						}
					}
					String result = returnContent(templateContent,content,columnArr,schSelectAllTable[t][0],columnType);
					String saveFilePath = savePath + "/" + fileName.replaceAll("\\{name\\}", firstUppercase(transfer(schSelectAllTable[t][0])));
					if(packageName.indexOf("entity")!= -1){
						FileHandle.write(saveFilePath,result,true);
					}else if(packageName.indexOf("map")!= -1){
						File saveFileFile = new File(saveFilePath);
						if(saveFileFile.exists()){
							updateXml(saveFilePath,columnArr,schSelectAllTable[t][0],columnType);
						}else{
							FileHandle.write(saveFilePath,result,isCover);
							formatXMLFile(saveFilePath);
						}
					}else{
						FileHandle.write(saveFilePath,result,isCover);
					}
				}
			}
		}
		db.closeConn();
		
	}
	
	/** 解析栏目模板文件 */
	public static String returnContent(String content,StringBuffer result,ArrayList<String> field,String tableName,ArrayList<String> type){
		int start = content.indexOf("{for}");
		int end = content.indexOf("{endfor}");
		if(start == -1){
			
			result.append(transferContent(content,tableName,"",""));
			return result.toString();
		}
		result.append(transferContent(content.substring(0,start),tableName,"",""));
		String forContent = content.substring(start+5,end);
		
		for(int i = 0; field != null && i < field.size(); i++){
			String forTemContent  = transferContent(forContent,tableName,field.get(i),type.get(i));
			if(forTemContent.endsWith(",") && i == field.size() - 1){
				forTemContent = forTemContent.substring(0,forTemContent.length() - 1);
			}
			result.append(forTemContent);
		}
		
		return returnContent(content.substring(end + 8),result,field,tableName,type);
	}
	
	@SuppressWarnings("unchecked")
	public static void updateXml(String path,ArrayList<String> field,String tableName,ArrayList<String> type){
		try{
			StringBuffer result = new StringBuffer();
			String resultMapString = "{for}<result property=\"{columnName}\" column=\"{columnName}\" />{endfor}";
			String resultMapResult = returnContent(resultMapString,result,field,tableName,type);
			result.delete(0, result.length());
			String selectString = "select * from {tablename} where 1 = 1 "+
					"{for}<if test=\"{columnname} != null\"> and {columnname} = {#columnname}</if> {endfor}"
					+"<if test=\"condition != null\"> ${condition}</if>";
			String selectResult = returnContent(selectString,result,field,tableName,type);
			result.delete(0, result.length());
			String selectListString = "select * from {tablename} where 1 = 1 "+
				"{for}<if test=\"{columnname} != null\"> and {columnname} = {#columnname}</if> {endfor}"+
				"<if test=\"condition != null\"> ${condition}</if> "+
		        " <if test=\"startPage != -1\"> limit #{startPage},#{page}</if> ";
			String selectListResult = returnContent(selectListString,result,field,tableName,type);
			result.delete(0, result.length());
			String selectCountString = "select count({tableName}Id) from {tablename} where 1 = 1 "+
					"{for}<if test=\"{columnname} != null\"> and {columnname} = {#columnname}</if> {endfor} "+
					"<if test=\"condition != null\"> ${condition}</if> ";
			String selectCountResult = returnContent(selectCountString,result,field,tableName,type);
			result.delete(0, result.length());
			String insertString = "insert into {tablename} ("+
					"{for}{columnname},{endfor}"+
					") values ("+
					"{for}#{{columnName}},{endfor}"+
					")";
			String insertResult = returnContent(insertString,result,field,tableName,type);
			result.delete(0, result.length());
			String deleteString = "delete from {tablename} where 1 = 1 {for}<if test=\"{columnname} != null\"> and {columnname} = {#columnname} </if> {endfor} <if test=\"condition != null\"> ${condition}</if>";
			String deleteResult = returnContent(deleteString,result,field,tableName,type);
			   
			result.delete(0, result.length());
			String updateString = "update {tablename} <set> "+
				"{for}<if test=\"{columnname} != null\">{columnname} = #{{columnName}},</if>{endfor} </set>  "+
				"where 1 = 1 <if test=\"{tableName}Id != null\"> and {tableName}Id = #{{tableName}Id}</if><if test=\"condition != null\"> ${condition}</if>";
			String updateResult = returnContent(updateString,result,field,tableName,type);
			result.delete(0, result.length());
			String deleteByIdsString = "delete from {tablename} where {tableName}Id in (${ids})";
			String deleteByIdsResult = returnContent(deleteByIdsString,result,field,tableName,type);
			result.delete(0, result.length());
			String maxIdString = "select max({tableName}Id) from {tablename}";
			String maxIdResult = returnContent(maxIdString,result,field,tableName,type);
			result.delete(0, result.length());
			
			
			SAXReader reader = new SAXReader();  
			Document document = reader.read(new File(path));  
			Element rootEle = document.getRootElement();
			Element resultMapEle = rootEle.element("resultMap");
			//如果下面存在collection，则不更新
			Element collectionEle = resultMapEle.element("collection");
			if(collectionEle == null){
				resultMapEle.clearContent();
				resultMapEle.setText("#resultMapResult#");
			}
			List<Element> selectEles = rootEle.elements("select");
			for(Element e : selectEles){
				String id = e.attributeValue("id");
				if("select".equals(id)){
					e.clearContent();
					e.setText("#selectResult#");
				}else if("selectList".equals(id)){
					e.clearContent();
					e.setText("#selectListResult#");
				}else if("selectCount".equals(id)){
					e.clearContent();
					e.setText("#selectCountResult#");
				}else if("maxId".equals(id)){
					e.clearContent();
					e.setText("#maxIdResult#");
				}else{
					continue;
				}
			}
			List<Element> insertEles = rootEle.elements("insert");
			for(Element e : insertEles){
				String id = e.attributeValue("id");
				if("insert".equals(id)){
					e.clearContent();
					e.setText("#insertResult#");
				}else{
					continue;
				}
			}
			List<Element> updateEles = rootEle.elements("update");
			for(Element e : updateEles){
				String id = e.attributeValue("id");
				if("update".equals(id)){
					e.clearContent();
					e.setText("#updateResult#");
				}else{
					continue;
				}
			}
			List<Element> deleteEles = rootEle.elements("delete");
			for(Element e : deleteEles){
				String id = e.attributeValue("id");
				if("delete".equals(id)){
					e.clearContent();
					e.setText("#deleteResult#");
				}else{
					continue;
				}
			}
			List<Element> deleteByIdsEles = rootEle.elements("deleteByIds");
			for(Element e : deleteByIdsEles){
				String id = e.attributeValue("id");
				if("deleteByIds".equals(id)){
					e.clearContent();
					e.setText("#deleteByIdsResult#");
				}else{
					continue;
				}
			}
			XMLWriter writer = new XMLWriter(new FileOutputStream(new File(path)));  
	        writer.write(document);  
	        writer.close(); 
	        String templateContent = FileHandle.readFile(path);
	        templateContent = templateContent.replace("#resultMapResult#", resultMapResult);
	        templateContent = templateContent.replace("#selectResult#", selectResult);
	        templateContent = templateContent.replace("#selectListResult#", selectListResult);
	        templateContent = templateContent.replace("#selectCountResult#", selectCountResult);
	        templateContent = templateContent.replace("#maxIdResult#", maxIdResult);
	        templateContent = templateContent.replace("#insertResult#", insertResult);
	        templateContent = templateContent.replace("#updateResult#", updateResult);
	        templateContent = templateContent.replace("#deleteResult#", deleteResult);
	        templateContent = templateContent.replace("#deleteByIdsResult#", deleteByIdsResult);
	        FileHandle.write(path, templateContent);
	        formatXMLFile(path);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 解析表模板文件 */
	public static String returnTableContent(String content,StringBuffer result,ArrayList<String> tableArr){
		int start = content.indexOf("{for}");
		int end = content.indexOf("{endfor}");
		if(start == -1){
			
			result.append(transferContent(content,"","",""));
			return result.toString();
		}
		result.append(transferContent(content.substring(0,start),"","",""));
		String forContent = content.substring(start+5,end);
		for(int i = 0; tableArr != null && i < tableArr.size(); i++){
			String forTemContent = transferContent(forContent,tableArr.get(i),"","");
			if(forTemContent.endsWith(",") && i == tableArr.size() - 1){
				forTemContent = forTemContent.substring(0,forTemContent.length() - 1);
			}
			result.append(forTemContent);
		}
		
		return returnTableContent(content.substring(end + 8),result,tableArr);
	}
	
	/** 解析特定字符串 */
	public static String transferContent(String content,String tableName,String field,String type){
		if(!"".equals(tableName)){
			content = content.replaceAll("\\{TableName\\}", firstUppercase(transfer(tableName)));
			content = content.replaceAll("\\{tableName\\}", transfer(tableName));
			content = content.replaceAll("\\{tablename\\}", tableName);
		}
		if(!"".equals(field)){
			content = content.replaceAll("\\{columnName\\}", transfer(field));
			content = content.replaceAll("\\{columnname\\}", field);
			content = content.replaceAll("\\{#columnname\\}", "#{"+field+"}");
//			if("String".equals(type)){
//				content = content.replaceAll("\\{#columnname\\}", "'#{"+field+"}'");
//			}else{
//				content = content.replaceAll("\\{#columnname\\}", "#{"+field+"}");
//			}
			content = content.replaceAll("\\{Columnname\\}", firstUppercase(field));
			content = content.replaceAll("\\{ColumnName\\}", firstUppercase(transfer(field)));
			content = content.replaceAll("\\{type\\}", type);
		}
		return content;
	}
	
	/**
	 * 将数据库写法转成驼峰写法
	 * */
	public static String transfer(String content){
		int index = content.indexOf("_");
		if(index != -1){
			String temStr = content.substring(index+1,index+2);
			content = content.replaceAll("_"+temStr, temStr.toUpperCase());
		}else{
			return content;
		}
		return transfer(content);
	}
	
	/**
	 * 首字母大写
	 * */
	public static String firstUppercase(String content){
		return content.substring(0,1).toUpperCase() + content.substring(1);
	}

	public static int formatXMLFile(String filename) {
		int returnValue = 0;
		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(new File(filename));
			XMLWriter output = null;
			/** 格式化输出,类型IE浏览一样 */
			OutputFormat format = OutputFormat.createPrettyPrint();
			/** 指定XML字符集编码 */
			format.setEncoding("GBK");
			output = new XMLWriter(new FileWriter(new File(filename)), format);
			output.write(document);
			output.close();
			/** 执行成功,需返回1 */
			returnValue = 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return returnValue;
	}
}
