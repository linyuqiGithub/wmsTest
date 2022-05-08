package com._520it.wms.web.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class BaseAction extends ActionSupport  {
	private static final long serialVersionUID = 1L;
	
	public void putContext(String key,Object value) {
		ActionContext.getContext().put(key, value);
	}

    public void putMsg(String msg){
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void putJson(Object obj) throws Exception {
    	ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
    	ServletActionContext.getResponse().getWriter().print(JSON.toJSONString(obj));
	}
}
