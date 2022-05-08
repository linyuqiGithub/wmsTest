package ${packageName}.web.action;

import ${packageName}.domain.${className};
import ${packageName}.query.${className}QueryObject;
import ${packageName}.service.I${className}Service;
import ${packageName}.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

public class ${className}Action extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private I${className}Service ${objectName}Service;

	@Getter
	private ${className}QueryObject qo=new ${className}QueryObject();

	@Getter
	private ${className} ${objectName} = new ${className}();
	
	@RequiredPermission("${objectCNName}列表")
	public String execute(){
		try {
			putContext("pageResult", ${objectName}Service.queryByConditionPage(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}

	@RequiredPermission("${objectCNName}编辑")
	public String input() {
		try {
			if (${objectName}.getId() != null) {
                ${objectName} = ${objectName}Service.get(${objectName}.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "input";
	}

	@RequiredPermission("${objectCNName}保存/更新")
	public String saveOrUpdate() {
		try {
			if (${objectName}.getId() == null) {
                ${objectName}Service.save(${objectName});
				addActionMessage("增加成功");
            } else {
                ${objectName}Service.update(${objectName});
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("${objectCNName}删除")
	public String delete()  throws  Exception {
		try {
			if (${objectName}.getId() != null) {
                ${objectName}Service.delete(${objectName}.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}

}
