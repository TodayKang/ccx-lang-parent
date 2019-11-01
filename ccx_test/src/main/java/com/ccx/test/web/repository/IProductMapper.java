package com.ccx.test.web.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccx.test.web.entiry.ProductVO;

@Repository
public interface IProductMapper {

    List<ProductVO> getByPage(Map<String, Object> map);

    long getTotal(Map<String, Object> map);

}
