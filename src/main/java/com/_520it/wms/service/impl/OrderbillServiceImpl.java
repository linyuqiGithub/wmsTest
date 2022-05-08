package com._520it.wms.service.impl;

import com._520it.wms.domain.Orderbill;
import com._520it.wms.domain.OrderbillItem;
import com._520it.wms.mapper.OrderbillMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.OrderbillQueryObject;
import com._520it.wms.service.IOrderbillService;
import com._520it.wms.util.UserContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class OrderbillServiceImpl implements IOrderbillService {
    @Setter
    private OrderbillMapper orderbillMapper;

    public void delete(Long id) {
        //先删除数据库中订单的所有订单明细
        orderbillMapper.deleteItemBybillId(id);
        //再删除数据库中的订单
        orderbillMapper.delete(id);
    }

    public void save(Orderbill entity) {
        //1保存录入人和录入时间
        entity.setInputUser(UserContext.getCurrentUser());
        entity.setInputTime(new Date());
        //2设置单据状态为未审核状态
        entity.setStatus(Orderbill.NORMAL);
        //3通过订单明细计算出采购总金额和总数量
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;
        List<OrderbillItem> items = entity.getItems();
        for (OrderbillItem item : items) {
            BigDecimal costPrice = item.getCostPrice();
            BigDecimal number = item.getNumber();
            //计算金额小计
            //设置精度,四舍五入
            BigDecimal amount = costPrice.multiply(number).setScale(2, BigDecimal.ROUND_HALF_UP);
            item.setAmount(amount);
            //统计该订单的总金额和总数量,遍历累加
            totalAmount = totalAmount.add(amount);
            totalNumber = totalNumber.add(number);
        }
        entity.setTotalAmount(totalAmount);
        entity.setTotalNumber(totalNumber);
        //将订单保存在数据库中
        orderbillMapper.save(entity);
        //4保存订单明细,将订单明细与订单关联起来
        for (OrderbillItem item : items) {
            //设置订单明细对应的订单
            item.setOrderbill(entity);
            //将订单明细保存到数据库中
            orderbillMapper.saveItem(item);
        }
    }

    public Orderbill get(Long id) {
        //根据billId查询订单明细数据
        List<OrderbillItem> items = orderbillMapper.selectItemsBybillId(id);
        Orderbill orderbill = orderbillMapper.get(id);
        //建立关联关系
        orderbill.setItems(items);
        return orderbill;
    }

    public List<Orderbill> listAll() {
        return orderbillMapper.listAll();
    }

    public void update(Orderbill entity) {
        //重新计算总金额和总数量
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;

        //删除原来的订单明细数据
        orderbillMapper.deleteItemBybillId(entity.getId());
        //保存所有的订单明细数据
        //点击保存按钮后,调用更新方法之前,orderbill对象已经有orderbillItem集合,struts的参数拦截器已经帮我们封装好了
        //for循环就是为了计算orderbill对象的每一个orderbillItem明细的总价,同时将订单总金额和总数量进行累加
        //最后保存订单对象
        for (OrderbillItem item : entity.getItems()) {
            BigDecimal costPrice = item.getCostPrice();
            BigDecimal number = item.getNumber();
            //计算金额小计
            //设置精度,四舍五入
            BigDecimal amount = costPrice.multiply(number).setScale(2, BigDecimal.ROUND_HALF_UP);
            item.setAmount(amount);
            //统计该订单的总金额和总数量,遍历累加
            totalAmount = totalAmount.add(amount);
            totalNumber = totalNumber.add(number);
            item.setOrderbill(entity);//设置关联关系
            orderbillMapper.saveItem(item);//保存订单明细
        }
        //设置订单的总金额和总数量
        entity.setTotalAmount(totalAmount);
        entity.setTotalNumber(totalNumber);
        //更新订单对象
        orderbillMapper.update(entity);
    }
    @Override
    public PageResult queryByConditionPage(OrderbillQueryObject qo) {
        Long count = orderbillMapper.queryByConditionCount(qo);
        if (count <= 0) {
            return new PageResult(1, qo.getPageSize(), 0, Collections.EMPTY_LIST);
        }
        List<Orderbill> data = orderbillMapper.queryByCondition(qo);
        PageResult pageResult = new PageResult(qo.getCurrentPage(), qo.getPageSize(), count.intValue(), data);
        return pageResult;
    }

    @Override
    public void audit(Orderbill entity) {
        //设置审核人和审核时间
        entity.setAuditor(UserContext.getCurrentUser());
        entity.setAuditTime(new Date());
        orderbillMapper.audit(entity);
    }
}
