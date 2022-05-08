package com._520it.wms.web.action;

import com._520it.wms.domain.Product;
import com._520it.wms.query.ProductQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IProductService;
import com._520it.wms.util.FileUploadUtil;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class ProductAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Setter
    private IProductService productService;

    @Setter
    private IBrandService brandService;

    @Getter
    private ProductQueryObject qo = new ProductQueryObject();

    @Getter
    private Product product = new Product();

    @Setter
    private File pic;
    @Setter
    private String picFileName;

    @InputConfig(methodName = "input")
    @RequiredPermission("货品列表")
    public String execute() {
        try {
            putContext("pageResult", productService.queryByConditionPage(qo));
            putContext("brands", brandService.listAll());

        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return "list";
    }

    public String selectProduct() {
        try {
            putContext("pageResult", productService.queryByConditionPage(qo));
            putContext("brands", brandService.listAll());
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return "selectProduct";
    }

    @RequiredPermission("货品编辑")
    public String input() {
        try {
            putContext("brands", brandService.listAll());
            if (product.getId() != null) {
                product = productService.get(product.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return "input";
    }

    @RequiredPermission("货品保存/更新")
    public String saveOrUpdate() {
        try {
            //新增货品
            if (product.getId() == null) {
                if (pic != null) {
                    //该工具类上传图片后会自动将原图和压缩后的图片一起保存到目标目录中,并返回生成的原图的图片路径
                    String filePath = FileUploadUtil.uploadFile(pic, picFileName);
                    //设置保存的路径
                    product.setImagePath(filePath);
                }
                productService.save(product);
                addActionMessage("增加成功");
            } else {
                //更新货品
                //先查询原有的商品记录
                Product temp = productService.get(product.getId());
                //如果编辑的时候上传了新的图片
                if (pic != null) {
                    //删除原来的记录
                    if (StringUtils.isNoneBlank(temp.getImagePath())) {
                        //该工具类删除时会将图片的原图和压缩后的图片一起删除
                        FileUploadUtil.deleteFile(temp.getImagePath());
                    }
                    //保存上传的图片
                    String filePath = FileUploadUtil.uploadFile(pic, picFileName);
                    //设置保存的路径
                    product.setImagePath(filePath);
                } else {
                    //如果没有上传新的图片,把原来的图片地址重新赋值
                    product.setImagePath(temp.getImagePath());
                }
                productService.update(product);
                addActionMessage("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("货品删除")
    public String delete() throws Exception {
        try {
            if (product.getId() != null) {
                product = productService.get(product.getId());
                //如果该商品在/upload中存在图片
                if(StringUtils.isNoneBlank(product.getImagePath())){
                    //删除存储在/upload中的图片
                    FileUploadUtil.deleteFile(product.getImagePath());
                }
                //删除数据库中的记录
                productService.delete(product.getId());
                putMsg("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            putMsg(e.getMessage());
        }
        return NONE;
    }

}
