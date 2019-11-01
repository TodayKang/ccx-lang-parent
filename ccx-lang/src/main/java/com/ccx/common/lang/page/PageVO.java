package com.ccx.common.lang.page;

import lombok.*;

import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter(value = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@ToString(callSuper = true)
public class PageVO<T> implements Serializable {
    private static final long serialVersionUID = -116560581757629327L;

    protected static final Long defaultArg1 = 1L;
    protected static final Long defaultArg2 = 1000L;

    // 当前页（必要参数，最少为1，默认1）
    private Long arg1 = defaultArg1;

    // 每页大小（必要参数，默认1000）
    private Long arg2 = defaultArg2;

    // 总条数（默认为空）
    private Long arg3;

    // 第一页第一行是否从0开始
    private Boolean beginZero = Boolean.FALSE;

    // 查询开始行（不同数据库不一样）
    private Long rowStart;

    // 查询结束行（不同数据库不一样）
    private Long rowEnd;

    // 总页数
    private Long pageTotal;

    // 是否为第一页
    private Boolean pageIsFirst = Boolean.FALSE;

    // 是否为最后一页
    private Boolean pageIsLast = Boolean.FALSE;

    // 前一页页码（如果当前页为首页，则为空；如果总条数为空则为当前页减1）
    private Long pagePrev;

    // 后一页页码（如果当前页为尾页，则为空）
    private Long pageNext;

    // 当前页查询数据
    @Setter
    private Collection<? extends T> tList;

    // 当前查询参数
    @Setter
    private T tData;

    // 当前页URL
    @Setter
    private String url;

    PageVO(Long arg1, Long arg2, Long arg3, Boolean beginZero) {
        super();
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = arg3;
        this.beginZero = beginZero;
    }

}
