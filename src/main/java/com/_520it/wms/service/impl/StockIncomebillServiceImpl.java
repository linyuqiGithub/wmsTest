package com._520it.wms.service.impl;

import com._520it.wms.domain.StockIncomebill;
import com._520it.wms.domain.StockIncomebillItem;
import com._520it.wms.mapper.StockIncomebillMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.StockIncomebillQueryObject;
import com._520it.wms.service.IProductStockService;
import com._520it.wms.service.IStockIncomebillService;
import com._520it.wms.util.UserContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class StockIncomebillServiceImpl implements IStockIncomebillService {
    @Setter
    private StockIncomebillMapper stockIncomebillMapper;

    @Setter
    IProductStockService productStockService;

    public void delete(Long id) {
        //先删除采购入库订单明细
        stockIncomebillMapper.deleteItemBybillId(id);
        //再删除采购入库订单
        stockIncomebillMapper.delete(id);
    }

    public void save(StockIncomebill entity) {
        //1设置入库人和入库时间
        entity.setInputUser(UserContext.getCurrentUser());
        entity.setInputTime(new Date());
        //2设置单据状态为未审核状态
        entity.setStatus(StockIncomebill.NORMAL);
        //3通过订单明细计算出采购总金额和总数量
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;
        List<StockIncomebillItem> items = entity.getItems();
        //遍历订单明细的目的就是通过累加明细的数量和总金额算出订单的总金额和总数量
        for (StockIncomebillItem item : items) {
            //获取明细货品的单价
            BigDecimal costPrice = item.getCostPrice();
            //获取明细货品的数量
            BigDecimal number = item.getNumber();
            //计算金额小计:单价乘以数量
            //两个参数,设置精度保留两位小数和设置四舍五入
            BigDecimal amount = costPrice.multiply(number).setScale(2, BigDecimal.ROUND_HALF_UP);
            //将金额小计设置到每一个明细中
            item.setAmount(amount);
            //统计该订单的总金额和总数量,遍历累加每一个明细的总金额和货品数量
            totalAmount = totalAmount.add(amount);
            totalNumber = totalNumber.add(number);
        }
        //将该订单的总金额和总数量设置进入库订单对象中
        entity.setTotalAmount(totalAmount);
        entity.setTotalNumber(totalNumber);
        //将订单保存在数据库中
        stockIncomebillMapper.save(entity);
        //4保存订单明细,将订单明细与订单关联起来
        for (StockIncomebillItem item : items) {
            //设置订单明细对应的订单
            //注意必须先保存订单对象,然后在设置订单明细和订单的关联关系,否则订单明细中的订单对象没有id
            item.setBill(entity);
            //将每一个订单明细保存到数据库中
            stockIncomebillMapper.saveItem(item);
        }
    }

    //在数据库中获取的订单对象不存在与订单明细的关联,所有在返回从数据库中拿出的订单对象之前,必须先设置订单与订单明细的关系
    //所以获取订单对象的同时需要根据订单对象的id获取相对应的订单明细集合
    public StockIncomebill get(Long id) {
        //根据billId查询订单明细数据
        List<StockIncomebillItem> items = stockIncomebillMapper.selectItemsBybillId(id);
        StockIncomebill bill = stockIncomebillMapper.get(id);
        //建立关联关系,用于跳转到编辑页面时订单明细的回显
        bill.setItems(items);
        return bill;
    }

    public List<StockIncomebill> listAll() {
        return stockIncomebillMapper.listAll();
    }

    //保存和更新的区别在于:
    //保存是在遍历明细后,获取总金额和总数量后,设置进订单中,然后保存订单生成订单id,然后再遍历明细,将订单设置进明细中,用于保存明细中的订单id,然后再保存每一个明细获取明细id
    //而更新是在遍历前,先删除所有订单明细,累加总金额和总数量的同时,保存明细,因为此时的订单已经有id,可以直接设置关联关系后保存明细,处理循环后再订单设置总金额和总数量,然后保存订单
    public void update(StockIncomebill entity) {
        //重新计算总金额和总数量
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;

        //删除原来的订单明细数据
        stockIncomebillMapper.deleteItemBybillId(entity.getId());
        //保存所有的订单明细数据
        for (StockIncomebillItem item : entity.getItems()) {
            BigDecimal costPrice = item.getCostPrice();
            BigDecimal number = item.getNumber();
            //计算金额小计
            //设置精度,四舍五入
            BigDecimal amount = costPrice.multiply(number).setScale(2, BigDecimal.ROUND_HALF_UP);
            item.setAmount(amount);
            //统计该订单的总金额和总数量,遍历累加
            totalAmount = totalAmount.add(amount);
            totalNumber = totalNumber.add(number);
            item.setBill(entity);//设置关联关系,保存每一个订单明细之前必须设置关联关系,才能在订单明细中保存到订单id
            stockIncomebillMapper.saveItem(item);//保存订单明细
        }
        entity.setTotalAmount(totalAmount);
        entity.setTotalNumber(totalNumber);
        stockIncomebillMapper.update(entity);
    }

    @Override
    public PageResult queryByConditionPage(StockIncomebillQueryObject qo) {
        Long count = stockIncomebillMapper.queryByConditionCount(qo);
        if (count <= 0) {
            return new PageResult(1, qo.getPageSize(), 0, Collections.EMPTY_LIST);
        }
        List<StockIncomebill> data = stockIncomebillMapper.queryByCondition(qo);
        PageResult pageResult = new PageResult(qo.getCurrentPage(), qo.getPageSize(), count.intValue(), data);
        return pageResult;
    }

    //入库订单的审核主要做两件事:
    //1.修改入库订单的审核人和审核时间
    //2.遍历入库订单明细,根据货品id和仓库id查询库存中是否存在该类库存明细,如果不存在,则新增库存记录,如果已经存在,则按照更新库存信息
    //注意:最后记得调用入库订单的审核方法对入库订单对象进行数据库的更新
    @Override
    public void audit(StockIncomebill entity) {
        //获取数据库的对象
        entity = stockIncomebillMapper.get(entity.getId());
        //设置审核人和审核时间
        entity.setAuditor(UserContext.getCurrentUser());
        entity.setAuditTime(new Date());

        //获取入库单的明细数据
        List<StockIncomebillItem> items = stockIncomebillMapper.selectItemsBybillId(entity.getId());

        for (StockIncomebillItem item : items) {
            //调用入库方法
            //该方法主要用于判断库存中是否有已经存在该类明细,若存在,则修改数据,若不存在,则新增数据
            //判断规则是根据库存id和货品id来进行判断,所以需要传入仓库和每一个明细对应的货品对象,同时需要传入每一个明细的数量和每一个明细的采购总金额
            productStockService.income(entity.getDepot(),item.getProduct(),item.getNumber(),item.getAmount());
        }
        //更新入库订单的审核状态,审核人和审核时间
        stockIncomebillMapper.audit(entity);
    }
}
