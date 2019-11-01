package com.ccx.test.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ccx.common.lang.page.PageUtils;
import com.ccx.common.lang.page.PageVO;
import com.ccx.common.lang.utils.CcxBeanUtils;
import com.ccx.test.web.entiry.ProductVO;
import com.ccx.test.web.repository.IProductMapper;
import com.ccx.test.web.service.IProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductMapper productMapper;

    @Override
    public PageVO<ProductVO> getByPage(PageVO<ProductVO> arg) throws Exception {
        log.info("getByPage arg={}", arg);
        arg = PageUtils.format0(arg);

        if (arg.getArg3() == null || arg.getArg3() < 1) {
            arg = PageUtils.format0(arg, getTotal(arg.getTData()));
            log.info("getByPage rowTotal format:{}", arg);
        }

        if (arg.getArg3() == null || arg.getArg3() < 1) {
            return arg;
        }

        Map<String, Object> map = CcxBeanUtils.toMapByApache(arg);
        map.putAll(CcxBeanUtils.toMapByApache(arg.getTData()));
        log.info("getByPage查询参数:{}", JSON.toJSONString(map));
        List<ProductVO> list = productMapper.getByPage(map);
        arg.setTList(list);
        return arg;
    }

    @Override
    public Long getTotal(ProductVO arg) throws Exception {
        Map<String, Object> map = CcxBeanUtils.toMapByApache(arg);
        log.info("getTotal查询参数:{}", JSON.toJSONString(map));
        Long value = productMapper.getTotal(map);
        log.info("getTotal rowTotal={}", value);
        return value;
    }

}
