package genertor;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.MessageFormat;

public class CodeGenerator {
	private static Configuration conf = new Configuration(Configuration.VERSION_2_3_0);
	static{
		try {
			//设置文件模板目录在什么地方
			conf.setDirectoryForTemplateLoading(new File("templates"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception{
		System.out.println("执行开始.......");
		//根据domain类生成代码
		//codeGenerator(StockIncomebillItem.class);
		System.out.println("执行完毕。。。。。。");
	}
	private static void codeGenerator(Class clazz) throws Exception {
		//生成相应的数据模型
		ClassInfo classInfo = new ClassInfo(clazz);
		//生成Mapper
		createFile("Mapper.java","src/main/java/{0}/mapper/{1}Mapper.java",classInfo);
		createFile("Mapper.xml","src/main/resources/{0}/mapper/{1}Mapper.xml",classInfo);
		//生成Service
		createFile("IService.java","src/main/java/{0}/service/I{1}Service.java",classInfo);
		createFile("ServiceImpl.java","src/main/java/{0}/service/impl/{1}ServiceImpl.java",classInfo);
		//生成QueryObject
		createFile("QueryObject.java","src/main/java/{0}/query/{1}QueryObject.java",classInfo);
		/*//生成Controller
		createFile("Action.java","src/main/java/{0}/web/action/{1}Action.java",classInfo);
		//生成JSP页面和js
		createFile("input.jsp","src/main/webapp/WEB-INF/views/{2}/input.jsp",classInfo);
		createFile("list.jsp","src/main/webapp/WEB-INF/views/{2}/list.jsp",classInfo);
		//生成配置文件
		appendFile(classInfo,"dao.xml","src/main/resources/applicationContext-mapper.xml");
		appendFile(classInfo,"service.xml","src/main/resources/applicationContext-service.xml");
		appendFile(classInfo,"action.xml","src/main/resources/applicationContext-action.xml");*/
	}
	//把创建好的bean配置 追加到xml中
	public static  void appendFile(ClassInfo classInfo,String templateName,String filePath) throws  Exception{
		//根据模板名称获取到模板对象
		Template template = conf.getTemplate(templateName);
		//合并模板和数据模型
		//创建一个字符输出流
		StringWriter stringWriter = new StringWriter();
		//把数据输出到字符输出流中
		template.process(classInfo,stringWriter);
		//把字符输出流中的字符串追加到对应的配置文件中去
		XmlUtil.mergeXML(new File(filePath),stringWriter.toString());
	}

	/**
	 * 
	 * @param templateName  模板文件名称
	 * @param outPath       文件输出路径
	 * @param classInfo     数据模型
	 */
	private static void createFile(String templateName,String outPath,ClassInfo classInfo) throws Exception{
		//获取模板对象
		Template temp = conf.getTemplate(templateName);
		/*String msg = "今天天气不错，{0}去{1}";
		                           模板对象    填充到占位符的值
		msg = MessageFormat.format( msg,      "小红", "上学");
		System.out.println(msg);//今天天气不错，小红去上学*/
		//填充src/main/java/{0}/mapper/{1}Mapper.java上的参数值，最终生成完整的输出目录
		//                                           填充第{0}个占位符                           填充第{1}个占位符           填充第{2}个占位符
		outPath = MessageFormat.format(outPath, classInfo.getPackageName().replace(".", "/"),classInfo.getClassName(),classInfo.getObjectName());
		//创建文件
		File targetFile = new File(outPath);
		//创建目录
		if(!targetFile.getParentFile().exists()){
			//如果文件的上级目录不存在，则创建该上级目录
			targetFile.getParentFile().mkdirs();
		}
		//将数据模型输出到目标文件中去
		temp.process(classInfo, new FileWriter(targetFile));
	}
}
