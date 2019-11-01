package com.ccx.common.lang.page;

import java.util.Date;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.ccx.common.lang.base.BaseVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PageUtilsTest {

    Long currentPage = 2L;
    Long pageSize = 100L;
    Long rowTotal = 1000L;

    BaseVO baseVO = new BaseVO(new Date(), new Date(), null, null, null, null);

    @Test
    public void testFormat0() {
        PageVO<BaseVO> pageVO = PageUtils.format0();
        log.info("testFormat0:{}", JSON.toJSONString(pageVO));
    }

    @Test
    public void testFormat1() {
        PageVO<BaseVO> pageVO = PageUtils.format1();
        log.info("testFormat1:{}", JSON.toJSONString(pageVO));
    }

    @Test
    public void testFormat0Long() {
        PageVO<BaseVO> pageVO = PageUtils.format0(rowTotal);
        log.info("testFormat0Long:{}", JSON.toJSONString(pageVO));
    }

    @Test
    public void testFormat1Long() {
        PageVO<BaseVO> pageVO = PageUtils.format1(rowTotal);
        log.info("testFormat1Long:{}", JSON.toJSONString(pageVO));
    }

    @Test
    public void testFormat0LongLong() {
        PageVO<BaseVO> pageVO = PageUtils.format0(currentPage, pageSize);
        log.info("testFormat0LongLong:{}", JSON.toJSONString(pageVO));
    }

    @Test
    public void testFormat1LongLong() {
        PageVO<BaseVO> pageVO = PageUtils.format1(currentPage, pageSize);
        log.info("testFormat1LongLong:{}", JSON.toJSONString(pageVO));
    }

    @Test
    public void testFormat0LongLongLong() {
        PageVO<BaseVO> pageVO = PageUtils.format0(currentPage, pageSize, rowTotal);
        log.info("testFormat0LongLongLong:{}", JSON.toJSONString(pageVO));
    }

    @Test
    public void testFormat1LongLongLong() {
        PageVO<BaseVO> pageVO = PageUtils.format1(currentPage, pageSize, rowTotal);
        log.info("testFormat1LongLongLong:{}", JSON.toJSONString(pageVO));
    }

    @Test
    public void testFormat0PageVOOfT() {
        PageVO<BaseVO> vo = PageUtils.format0(currentPage, pageSize);
        log.info("testFormat0PageVOOfT:{}", JSON.toJSONString(vo));
        PageVO<BaseVO> pageVO = PageUtils.format0(vo);
        log.info("testFormat0PageVOOfT_re-format{}", JSON.toJSONString(pageVO));
    }

    @Test
    public void testFormat1PageVOOfT() {
        PageVO<BaseVO> vo = PageUtils.format1(currentPage, pageSize);
        log.info("testFormat1PageVOOfT:{}", JSON.toJSONString(vo));
        PageVO<BaseVO> pageVO = PageUtils.format1(vo);
        log.info("testFormat1PageVOOfT_re-format{}", JSON.toJSONString(pageVO));
    }

    @Test
    public void testFormat0PageVOOfTLong() {
        PageVO<BaseVO> vo = PageUtils.format0(currentPage, pageSize);
        log.info("testFormat0PageVOOfTLong:{}", JSON.toJSONString(vo));
        PageVO<BaseVO> pageVO = PageUtils.format0(vo, rowTotal);
        log.info("testFormat0PageVOOfTLong_re-format{}", JSON.toJSONString(pageVO));
    }

    @Test
    public void testFormat1PageVOOfTLong() {
        PageVO<BaseVO> vo = PageUtils.format1(currentPage, pageSize);
        log.info("testFormat0PageVOOfTLong:{}", JSON.toJSONString(vo));
        PageVO<BaseVO> pageVO = PageUtils.format1(vo, rowTotal);
        log.info("testFormat0PageVOOfTLong_re-format{}", JSON.toJSONString(pageVO));
    }

    @Test
    public void testFormat0PageVOOfTLongLong() {
        PageVO<BaseVO> vo = PageUtils.format0(currentPage, pageSize);
        log.info("testFormat0PageVOOfTLongLong:{}", JSON.toJSONString(vo));
        PageVO<BaseVO> pageVO = PageUtils.format0(vo, currentPage, pageSize);
        log.info("testFormat0PageVOOfTLongLong_re-format{}", JSON.toJSONString(pageVO));
    }

    @Test
    public void testFormat1PageVOOfTLongLong() {
        PageVO<BaseVO> vo = PageUtils.format1(currentPage, pageSize);
        log.info("testFormat1PageVOOfTLongLong:{}", JSON.toJSONString(vo));
        PageVO<BaseVO> pageVO = PageUtils.format1(vo, currentPage, pageSize);
        log.info("testFormat1PageVOOfTLongLong_re-format{}", JSON.toJSONString(pageVO));
    }

    @Test
    public void testFormat0PageVOOfTLongLongLong() {
        PageVO<BaseVO> vo = PageUtils.format0(currentPage, pageSize);
        log.info("testFormat0PageVOOfTLongLongLong:{}", JSON.toJSONString(vo));
        PageVO<BaseVO> pageVO = PageUtils.format0(vo, currentPage, pageSize, rowTotal);
        log.info("testFormat0PageVOOfTLongLongLong_re-format{}", JSON.toJSONString(pageVO));
    }

    @Test
    public void testFormat1PageVOOfTLongLongLong() {
        PageVO<BaseVO> vo = PageUtils.format1(currentPage, pageSize);
        log.info("testFormat1PageVOOfTLongLongLong:{}", JSON.toJSONString(vo));
        PageVO<BaseVO> pageVO = PageUtils.format1(vo, currentPage, pageSize, rowTotal);
        log.info("testFormat1PageVOOfTLongLongLong_re-format{}", JSON.toJSONString(pageVO));
    }

}
