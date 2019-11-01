package com.ccx.test.web.service;

import com.ccx.common.lang.page.PageVO;
import com.ccx.test.web.entiry.ProductVO;

public interface IProductService {

    Long getTotal(ProductVO arg) throws Exception;

    PageVO<ProductVO> getByPage(PageVO<ProductVO> arg) throws Exception;

}
