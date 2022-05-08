package com._520it.wms.service.impl;

import com._520it.wms.domain.SaleAccount;
import com._520it.wms.domain.StockOutcomebill;
import com._520it.wms.domain.StockOutcomebillItem;
import com._520it.wms.mapper.StockOutcomebillMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.StockOutcomebillQueryObject;
import com._520it.wms.service.IProductStockService;
import com._520it.wms.service.ISaleAccountService;
import com._520it.wms.service.IStockOutcomebillService;
import com._520it.wms.util.UserContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class StockOutcomebillServiceImpl implements IStockOutcomebillService {
    @Setter
    private StockOutcomebillMapper stockOutcomebillMapper;

    @Setter
    private IProductStockService productStockService;

    @Setter
    private ISaleAccountService saleAccountService;

    public void delete(Long id) {
        stockOutcomebillMapper.deleteItemBybillId(id);
        stockOutcomebillMapper.delete(id);
    }

    public void save(StockOutcomebill entity) {
        //1保存录入人和录入时间
        entity.setInputUser(UserContext.getCurrentUser());
        entity.setInputTime(new Date());
        //2设置单据状态为未审核状态
        entity.setStatus(StockOutcomebill.NORMAL);
        //3通过订单明细计算出采购总金额和总数量
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;
        List<StockOutcomebillItem> items = entity.getItems();
        for (StockOutcomebillItem item : items) {
            //获取明细货品的销售单价
            BigDecimal salePrice = item.getSalePrice();
            //获取明细货品的数量
            BigDecimal number = item.getNumber();
            //计算金额小计:单价乘以数量
            //两个参数,设置精度保留两位小数和设置四舍五入
            BigDecimal amount = salePrice.multiply(number).setScale(2, BigDecimal.ROUND_HALF_UP);
            //将金额小计设置到每一个明细中
            item.setAmount(amount);
            //统计该订单的销售总金额和总数量,遍历累加每一个明细的销售总金额和货品数量
            totalAmount = totalAmount.add(amount);
            totalNumber = totalNumber.add(number);
        }
        //将该订单的总金额和总数量设置进入库订单对象中
        entity.setTotalAmount(totalAmount);
        entity.setTotalNumber(totalNumber);
        //将订单保存在数据库中
        stockOutcomebillMapper.save(entity);
        //4保存订单明细,将订单明细与订单关联起来
        for (StockOutcomebillItem item : items) {
            //设置订单明细对应的订单
            item.setBill(entity);
            //将每一个订单明细保存到数据库中
            stockOutcomebillMapper.saveItem(item);
        }
    }

    public StockOutcomebill get(Long id) {
        //根据billId查询订单明细数据
        List<StockOutcomebillItem> items = stockOutcomebillMapper.selectItemsBybillId(id);
        StockOutcomebill bill = stockOutcomebillMapper.get(id);
        //建立关联关系,用于跳转到编辑页面时订单明细的回显
        bill.setItems(items);
        return bill;
    }

    public List<StockOutcomebill> listAll() {
        return stockOutcomebillMapper.listAll();
    }

    //保存和更新的区别在于:
    //保存是在遍历明细后,获取总金额和总数量后,设置进订单中,然后保存订单生成订单id,然后再遍历明细,将订单设置进明细中,用于保存明细中的订单id,然后再保存每一个明细获取明细id
    //而更新是在遍历前,先删除所有订单明细,累加总金额和总数量的同时,保存明细,因为此时的订单已经有id,可以直接设置关联关系后保存明细,处理循环后再订单设置总金额和总数量,然后保存订单
    public void update(StockOutcomebill entity) {
        //重新计算总金额和总数量
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;

        //删除原来的订单明细数据
        stockOutcomebillMapper.deleteItemBybillId(entity.getId());
        //保存所有的订单明细数据
        for (StockOutcomebillItem item : entity.getItems()) {
            BigDecimal salePrice = item.getSalePrice();
            BigDecimal number = item.getNumber();
            //计算金额小计
            //设置精度,四舍五入
            BigDecimal amount = salePrice.multiply(number).setScale(2, BigDecimal.ROUND_HALF_UP);
            item.setAmount(amount);
            //统计该订单的总金额和总数量,遍历累加
            totalAmount = totalAmount.add(amount);
            totalNumber = totalNumber.add(number);
            item.setBill(entity);//设置关联关系,保存每一个订单明细之前必须设置关联关系,才能在订单明细中保存到订单id
            stockOutcomebillMapper.saveItem(item);//保存订单明细
        }
        entity.setTotalAmount(totalAmount);
        entity.setTotalNumber(totalNumber);
        stockOutcomebillMapper.update(entity);
    }

    @Override
    public PageResult queryByConditionPage(StockOutcomebillQueryObject qo) {
        Long count = stockOutcomebillMapper.queryByConditionCount(qo);
        if (count <= 0) {
            return new PageResult(1, qo.getPageSize(), 0, Collections.EMPTY_LIST);
        }
        List<StockOutcomebill> data = stockOutcomebillMapper.queryByCondition(qo);
        PageResult pageResult = new PageResult(qo.getCurrentPage(), qo.getPageSize(), count.intValue(), data);
        return pageResult;
    }
    //出库订单的审核主要做三件事:
    //1.修改出库订单的审核人和审核时间
    //2.遍历出库订单明细,根据货品id和仓库id查询库存中是否存在该类库存明细,如果不存在,则抛出异常,如果已经存在,则扣减相应库存数量
    //3.更新销售账数据
    //注意最后需要更新销售出库订单对象的数据,改变审核状态和审核时间和审核人
    @Override
    public void audit(StockOutcomebill entity) {
        //获取数据库的对象
        entity = stockOutcomebillMapper.get(entity.getId());
        //设置审核人和审核时间
        entity.setAuditor(UserContext.getCurrentUser());
        entity.setAuditTime(new Date());

        //获取入库单的明细数据
        List<StockOutcomebillItem> items = stockOutcomebillMapper.selectItemsBybillId(entity.getId());
        //获取仓库id,用于根据仓库id和产品id对productStock库存做是否存在查询
        for (StockOutcomebillItem item : items) {
            //用于判断库存中是否存在该商品,判断条件是仓库id和明细对应的商品id,如果不存在或则库存不足,则抛出相应异常,若存在,则扣减相应库存数量
            //同时返回库存采购单价
            BigDecimal costPrice = productStockService.outcome(entity.getDepot(),item.getProduct(),item.getNumber());

            //插入销售账的流水数据
            SaleAccount sc = new SaleAccount();
            sc.setClient(entity.getClient());
            sc.setVdate(entity.getVdate());
            sc.setProduct(item.getProduct());
            sc.setNumber(item.getNumber());
            sc.setSalePrice(item.getSalePrice());
            sc.setSaleAmount(sc.getNumber().multiply(sc.getSalePrice()).setScale(2,BigDecimal.ROUND_HALF_UP));
            sc.setSaleMan(entity.getInputUser());
            //销售出库订单中没有采购入库单价,所以需要从库存中拿到对应明细的采购入库价
            sc.setCostPrice(costPrice);
            sc.setCostAmount(sc.getNumber().multiply(sc.getCostPrice()).setScale(2,BigDecimal.ROUND_HALF_UP));
            //保存销售帐对象
            saleAccountService.save(sc);
        }
        //更新入库订单的审核状态,审核人和审核时间
        stockOutcomebillMapper.audit(entity);
    }
}
