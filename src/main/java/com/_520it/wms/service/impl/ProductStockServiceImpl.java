package com._520it.wms.service.impl;

import com._520it.wms.domain.Depot;
import com._520it.wms.domain.Product;
import com._520it.wms.domain.ProductStock;
import com._520it.wms.mapper.ProductStockMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.ProductStockQueryObject;
import com._520it.wms.service.IProductStockService;
import com._520it.wms.vo.ProductStockChartVO;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class ProductStockServiceImpl implements IProductStockService {
	@Setter
	private ProductStockMapper productStockMapper;
	
	public void  delete(Long id) {
		  productStockMapper.delete(id);
	}

	public void save(ProductStock entity) {
		  productStockMapper.save(entity);
	}

	public ProductStock get(Long id) {
		return productStockMapper.get(id);
	}

	public List<ProductStock> listAll() {
		return productStockMapper.listAll();
	}

	public void update(ProductStock entity) {
		  productStockMapper.update(entity);
	}

	@Override
	public PageResult queryByConditionPage(ProductStockQueryObject qo) {
		Long count = productStockMapper.queryByConditionCount(qo);
		if(count<=0){
			return new PageResult(1, qo.getPageSize(), 0, Collections.EMPTY_LIST);
		}
		List<ProductStock> data = productStockMapper.queryByCondition(qo);
		PageResult pageResult = new PageResult(qo.getCurrentPage(),qo.getPageSize(), count.intValue(), data);
		return pageResult;
	}

	//该方法主要用于判断库存中是否有已经存在该类明细,若存在,则修改数据,然后更新数据库中的库存对象,若不存在,则新增数据,新增数据库的库存对象
	@Override
	public void income(Depot depot, Product product, BigDecimal number, BigDecimal amount) {
		//获取产品id
		Long productId = product.getId();
		//根据仓库id和产品id查询库存信息
		ProductStock ps = productStockMapper.queryBydepotAndProduct(depot.getId(), productId);
		if (ps == null) {//库存表中没有相应的信息,,新增记录
			ps = new ProductStock();
			ps.setDepot(depot);//设置仓库
			ps.setProduct(product);//设置产品
			ps.setPrice(product.getCostPrice());//设置库存价格
			ps.setStoreNumber(number);//设置库存数量
			ps.setAmount(amount);//设置总金额
			productStockMapper.save(ps);
		} else {//已经存在该类库存,修改原来的记录
			//库存数量在原来的基础上增加
			ps.setStoreNumber(ps.getStoreNumber().add(number));
			//总金额也在原来的基础上增加
			ps.setAmount(ps.getAmount().add(amount));
			//单价更新为当前总金额除以总数量(移动加权平均法)
			ps.setPrice(ps.getAmount().divide(ps.getStoreNumber(), 2, BigDecimal.ROUND_HALF_UP));
			productStockMapper.update(ps);
		}
  	}
	//用于判断库存中是否存在该商品,判断条件是仓库id和明细对应的商品id,如果不存在或则库存不足,则抛出相应异常,若存在,则扣减相应库存数量
	@Override
	public BigDecimal outcome(Depot depot, Product product, BigDecimal number) {
		//获取产品id
		Long productId = product.getId();
		//根据仓库id和产品id查询库存信息
		ProductStock ps = productStockMapper.queryBydepotAndProduct(depot.getId(), productId);
		if (ps == null) {//库存表中没有相应的信息,,新增记录
			//抛出相应异常,action会处理该异常,将异常信息转成JSon对象发送ajax相应
			throw new RuntimeException(depot.getName()+"仓库没有"+product.getName()+"商品的库存");
		}
        //调用compareTo方法判断库存是否足够
		if(ps.getStoreNumber().compareTo(number) < 0){
			//若不足,则抛出异常
			throw new RuntimeException(depot.getName()+"中"+product.getName()+"商品的库存不足,仅剩"+ps.getStoreNumber());
		}
		//正常出库
		//减少库存数量
		ps.setStoreNumber(ps.getStoreNumber().subtract(number));
		//计算库存总价
		ps.setAmount(ps.getPrice().multiply(ps.getStoreNumber()).setScale(2,BigDecimal.ROUND_HALF_UP));
		productStockMapper.update(ps);
		return ps.getPrice();
	}

	@Override
	public List<ProductStockChartVO> CastType(List<ProductStock> data) {
		List<ProductStockChartVO> list = new ArrayList<>();
		for (ProductStock productStock : data) {
			ProductStockChartVO vo = new ProductStockChartVO();
			vo.setId(productStock.getId());
			vo.setProductName(productStock.getProduct().getName());
			vo.setDepotName(productStock.getDepot().getName());
			vo.setBrandName(productStock.getProduct().getBrand().getName());
			vo.setPrice(productStock.getPrice());
			vo.setNumber(productStock.getStoreNumber());
			vo.setAmount(productStock.getAmount());
			list.add(vo);
		}
		return list;
	}
}
