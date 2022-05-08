package com._520it.wms.domain;

import com._520it.wms.util.FileUploadUtil;
import com.alibaba.fastjson.JSON;
import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/2.
 */
@Setter@Getter@ObjectProp("货品")@ToString
public class Product extends BaseDomain{
    @ObjectProp("编号")
    private Long id;
    @ObjectProp("货品名称")
    private String name;
    @ObjectProp("货品编号")
    private String sn;
    @ObjectProp("成本价")
    private BigDecimal costPrice;
    @ObjectProp("销售价")
    private BigDecimal salePrice;
    @ObjectProp("货品图片")
    private String imagePath;
    @ObjectProp("货品介绍")
    private String intro;
    @ObjectProp("货品品牌")
    private Brand brand;

    //提供一个smallImagePath属性,用于获取压缩后的图片路径
    public String getSmallImagePath(){
        if(StringUtils.isEmpty(imagePath)){
            return "";
        }
        int index = imagePath.lastIndexOf(".");
        //返回压缩文件的文件路径由:/upload/该文件的文件名+_small+.jpg
        return imagePath.substring(0,index)+FileUploadUtil.suffix+imagePath.substring(index);
    }
    //iframeTools貌似一定要求传递的参数是JSon对象
    public String getProductJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",getId());
        map.put("name",getName());
        map.put("brandName",getBrand()==null?"":getBrand().getName());
        map.put("costPrice",getCostPrice());
        map.put("salePrice",getSalePrice());
        return JSON.toJSONString(map);
    }
}
