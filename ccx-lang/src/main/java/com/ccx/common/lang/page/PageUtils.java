package com.ccx.common.lang.page;

import com.alibaba.fastjson.JSON;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * 这是一个 Page Helper, 可以帮助你简化页面数据.<br>
 * 为避免过多开销，应该避免频繁创建对象。<br>
 * <p>
 * format0(Object... object) 第一页第一行从0计算<br>
 * format1(Object... object) 第一页第一行从1计算<br>
 */
@Slf4j
public class PageUtils {

	public static <T> PageVO<T> format0() {
		return setPage(null, null, null, null, Boolean.TRUE);
	}

	public static <T> PageVO<T> format1() {
		return setPage(null, null, null, null, Boolean.FALSE);
	}

	public static <T> PageVO<T> format0(Long rowTotal) {
		return setPage(null, null, null, rowTotal, Boolean.TRUE);
	}

	public static <T> PageVO<T> format1(Long rowTotal) {
		return setPage(null, null, null, rowTotal, Boolean.FALSE);
	}

	public static <T> PageVO<T> format0(Long currentPage, Long pageSize) {
		return setPage(null, currentPage, pageSize, null, Boolean.TRUE);
	}

	public static <T> PageVO<T> format1(Long currentPage, Long pageSize) {
		return setPage(null, currentPage, pageSize, null, Boolean.FALSE);
	}

	public static <T> PageVO<T> format0(Long currentPage, Long pageSize, Long rowTotal) {
		return setPage(null, currentPage, pageSize, rowTotal, Boolean.TRUE);
	}

	public static <T> PageVO<T> format1(Long currentPage, Long pageSize, Long rowTotal) {
		return setPage(null, currentPage, pageSize, rowTotal, Boolean.FALSE);
	}

	public static <T> PageVO<T> format0(PageVO<T> pageVO) {
		return setPage(pageVO, null, null, null, Boolean.TRUE);
	}

	public static <T> PageVO<T> format1(PageVO<T> pageVO) {
		return setPage(pageVO, null, null, null, Boolean.FALSE);
	}

	public static <T> PageVO<T> format0(PageVO<T> pageVO, Long rowTotal) {
		return setPage(null, null, null, rowTotal, Boolean.TRUE);
	}

	public static <T> PageVO<T> format1(PageVO<T> pageVO, Long rowTotal) {
		return setPage(pageVO, null, null, rowTotal, Boolean.FALSE);
	}

	public static <T> PageVO<T> format0(PageVO<T> pageVO, Long currentPage, Long pageSize) {
		return setPage(pageVO, currentPage, pageSize, null, Boolean.TRUE);
	}

	public static <T> PageVO<T> format1(PageVO<T> pageVO, Long currentPage, Long pageSize) {
		return setPage(pageVO, currentPage, pageSize, null, Boolean.FALSE);
	}

	public static <T> PageVO<T> format0(PageVO<T> pageVO, Long currentPage, Long pageSize, Long rowTotal) {
		return setPage(pageVO, currentPage, pageSize, rowTotal, Boolean.TRUE);
	}

	public static <T> PageVO<T> format1(PageVO<T> pageVO, Long currentPage, Long pageSize, Long rowTotal) {
		return setPage(pageVO, currentPage, pageSize, rowTotal, Boolean.FALSE);
	}

	/**
	 * 设置当前页面对象
	 * 
	 * @param <T>
	 * @param pageVO    当前页面对象
	 * @param arg1      当前页
	 * @param arg2      每页大小
	 * @param arg3      总条数
	 * @param beginZero 第一页第一行是否从0开始
	 * @return 格式化后的页面对象
	 */
	private static <T> PageVO<T> setPage(PageVO<T> pageVO, Long arg1, Long arg2, Long arg3, @NonNull Boolean beginZero) {
		pageVO = (pageVO == null) ? new PageVO<T>() : pageVO;

		pageVO.setBeginZero(beginZero);
		if (arg1 != null) {
			pageVO.setArg1(arg1);
		}
		if (arg2 != null) {
			pageVO.setArg2(arg2);
		}
		if (arg3 != null) {
			pageVO.setArg3(arg3);
		}

		PageVO<T> formatPageVO = formatPage(new PageVO<T>(pageVO.getArg1(), pageVO.getArg2(), pageVO.getArg3(), pageVO.getBeginZero()));
		log.info("当前格式化页面的数据:{}", JSON.toJSONString(formatPageVO));
		formatPageVO.setTData(pageVO.getTData());
		formatPageVO.setTList(pageVO.getTList());
		return formatPageVO;
	}

	/**
	 * 格式化当前页面对象
	 * 
	 * @param <T>
	 * @param pageVO 当前页面对象
	 * @return 格式化后的页面对象
	 */
	private static <T> PageVO<T> formatPage(@NonNull PageVO<T> pageVO) {
		if (pageVO.getArg1() == null || pageVO.getArg1() < 1) {
			pageVO.setArg1(PageVO.defaultArg1);
		}
		if (pageVO.getArg2() == null || pageVO.getArg2() < 1) {
			pageVO.setArg2(PageVO.defaultArg2);
		}
		if (pageVO.getArg3() == null || pageVO.getArg3() < 1) {
			pageVO.setArg3(null);
		}

		// 起始行先按照第一页第一条为1算
		pageVO.setRowStart((pageVO.getArg1() - 1) * pageVO.getArg2() + 1);
		pageVO.setRowEnd(pageVO.getArg1() * pageVO.getArg2());

		// 是否为首尾页先按照 false 算
		pageVO.setPageIsFirst(Boolean.FALSE);
		pageVO.setPageIsLast(Boolean.FALSE);

		// 前一页后一页页码先按照非首页，总条数非空算
		pageVO.setPagePrev(pageVO.getArg1() - 1);
		pageVO.setPageNext(pageVO.getArg1() + 1);

		// 如果当前是第一页
		if (pageVO.getArg1() == 1) {
			pageVO.setPageIsFirst(Boolean.TRUE);
			pageVO.setPagePrev(null);
		}

		// 如果总条数存在
		if (pageVO.getArg3() != null) {
			pageVO.setPageTotal((pageVO.getArg3() % pageVO.getArg2() == 0) ? //
					(pageVO.getArg3() / pageVO.getArg2()) : (pageVO.getArg3() / pageVO.getArg2() + 1));

			// 校验当前页是否合法
			if (pageVO.getArg1() > pageVO.getPageTotal()) {
				log.warn("当前格式化数据非法: 当前页>总页数");
			}

			// 重置是否为尾页
			pageVO.setPageIsLast((pageVO.getArg1() < pageVO.getPageTotal()) ? Boolean.FALSE : Boolean.TRUE);

			pageVO.setPageNext((pageVO.getArg1() < pageVO.getPageTotal()) ? pageVO.getPageNext() : null);

			// 重置查询结束行
			pageVO.setRowEnd((pageVO.getArg1() == pageVO.getPageTotal()) ? pageVO.getArg3() : pageVO.getRowEnd());
		}

		// 如果第一页第一行是否从0开始
		if (pageVO.getBeginZero()) {
			pageVO.setRowStart(pageVO.getRowStart() - 1);
			pageVO.setRowEnd(pageVO.getRowEnd() - 1);
		}

		return pageVO;
	}

}
