package com._520it.wms.web.action;

import com._520it.wms.domain.Brand;
import com._520it.wms.query.BrandQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

public class BrandAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IBrandService brandService;

	@Getter
	private BrandQueryObject qo=new BrandQueryObject();

	@Getter
	private Brand brand = new Brand();
	
	@RequiredPermission("品牌列表")
	public String execute(){
		try {
			putContext("pageResult", brandService.queryByConditionPage(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}

	@RequiredPermission("品牌编辑")
	public String input() {
		try {
			if (brand.getId() != null) {
                brand = brandService.get(brand.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "input";
	}

	@RequiredPermission("品牌保存/更新")
	public String saveOrUpdate() {
		try {
			if (brand.getId() == null) {
                brandService.save(brand);
				addActionMessage("增加成功");
            } else {
                brandService.update(brand);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("品牌删除")
	public String delete()  throws  Exception {
		try {
			if (brand.getId() != null) {
                brandService.delete(brand.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}

}
