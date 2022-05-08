package ${packageName}.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${packageName}.domain.${className};
import ${packageName}.mapper.${className}Mapper;
import ${packageName}.page.PageResult;
import ${packageName}.query.${className}QueryObject;
import ${packageName}.service.I${className}Service;
import lombok.Setter;
public class ${className}ServiceImpl implements I${className}Service {
	@Setter
	private ${className}Mapper ${objectName}Mapper;
	
	public void  delete(Long id) {
		  ${objectName}Mapper.delete(id);
	}

	public void save(${className} entity) {
		  ${objectName}Mapper.save(entity);
	}

	public ${className} get(Long id) {
		return ${objectName}Mapper.get(id);
	}

	public List<${className}> listAll() {
		return ${objectName}Mapper.listAll();
	}

	public void update(${className} entity) {
		  ${objectName}Mapper.update(entity);
	}

	@Override
	public PageResult queryByConditionPage(${className}QueryObject qo) {
		Long count = ${objectName}Mapper.queryByConditionCount(qo);
		if(count<=0){
			return new PageResult(1, qo.getPageSize(), 0, Collections.EMPTY_LIST);
		}
		List<${className}> data = ${objectName}Mapper.queryByCondition(qo);
		PageResult pageResult = new PageResult(qo.getCurrentPage(),qo.getPageSize(), count.intValue(), data);
		return pageResult;
	}
}
