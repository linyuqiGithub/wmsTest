package genertor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

/**
 * 类的数据模型
 */
@Getter@Setter@ToString
public class ClassInfo {
	private String packageName;//包名
	private String className;//类简单名称
	private String objectName;//类名首字母小写
	private String objectCNName;//类的中文名称
	//获取类中所有的字段英文名称和中文名称
	private Map<String,String> fieldMap = new HashMap<String,String>();
	public ClassInfo(Class clazz) throws Exception {
		//获取包名--->com._520it.wms.domain --> com._520it.wms
 		this.packageName = clazz.getPackage().getName();//com._520it.wms.domain
		this.packageName = this.packageName.substring(0, this.packageName.lastIndexOf("."));//com._520it.wms
		//获取类的简单名称--->Cat
		this.className = clazz.getSimpleName();//Cat
		//获取类的首字母小写--->cat
		this.objectName = this.className.substring(0,1).toLowerCase()+this.className.substring(1);//cat
		//获取类的中文名称
		ObjectProp objProp = (ObjectProp) clazz.getAnnotation(ObjectProp.class);
		this.objectCNName = objProp==null?objectName:objProp.value();//小猫
		//获取类中所有的字段英文名称和中文名称(内省机制属性描述器)
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz,Object.class);
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for(PropertyDescriptor pd :pds){
			if("id".equals(pd.getName())){
				continue;
			}
			//根据属性英文名称获取属性对象上面的注解类
			objProp = clazz.getDeclaredField(pd.getName()).getAnnotation(ObjectProp.class);
			//将属性的英文名称放入map的key中，属性的中文名称放入map的value中
			//如果obj==null(属性没有贴注解),map的value放属性英文名，否则获取注解的值(属性的中文名称)，放入map的value中
			fieldMap.put(pd.getName(), objProp==null?pd.getName():objProp.value());
			//              key                        value
		}
	}
	
}
