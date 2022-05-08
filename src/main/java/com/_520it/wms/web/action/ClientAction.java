package com._520it.wms.web.action;

import com._520it.wms.domain.Client;
import com._520it.wms.query.ClientQueryObject;
import com._520it.wms.service.IClientService;
import com._520it.wms.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

public class ClientAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IClientService clientService;

	@Getter
	private ClientQueryObject qo=new ClientQueryObject();

	@Getter
	private Client client = new Client();
	
	@RequiredPermission("客户管理列表")
	public String execute(){
		try {
			putContext("pageResult", clientService.queryByConditionPage(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}

	@RequiredPermission("客户管理编辑")
	public String input() {
		try {
			if (client.getId() != null) {
                client = clientService.get(client.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "input";
	}

	@RequiredPermission("客户管理保存/更新")
	public String saveOrUpdate() {
		try {
			if (client.getId() == null) {
                clientService.save(client);
				addActionMessage("增加成功");
            } else {
                clientService.update(client);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("客户管理删除")
	public String delete()  throws  Exception {
		try {
			if (client.getId() != null) {
                clientService.delete(client.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}

}
