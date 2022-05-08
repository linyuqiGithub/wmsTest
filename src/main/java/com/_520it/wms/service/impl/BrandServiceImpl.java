package com._520it.wms.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.wms.domain.Brand;
import com._520it.wms.mapper.BrandMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.BrandQueryObject;
import com._520it.wms.service.IBrandService;
import lombok.Setter;

public class BrandServiceImpl implements IBrandService {
    @Setter
    private BrandMapper brandMapper;

    public void delete(Long id) {
        brandMapper.delete(id);
    }

    public void save(Brand entity) {
        brandMapper.save(entity);
    }

    public Brand get(Long id) {
        return brandMapper.get(id);
    }

    public List<Brand> listAll() {
        return brandMapper.listAll();
    }

    public void update(Brand entity) {
        brandMapper.update(entity);
    }

    @Override
    public PageResult queryByConditionPage(BrandQueryObject qo) {
        Long count = brandMapper.queryByConditionCount(qo);
        if (count <= 0) {
            return new PageResult(1, qo.getPageSize(), 0, Collections.EMPTY_LIST);
        }
        List<Brand> data = brandMapper.queryByCondition(qo);
        PageResult pageResult = new PageResult(qo.getCurrentPage(), qo.getPageSize(), count.intValue(), data);
        return pageResult;
    }
}
