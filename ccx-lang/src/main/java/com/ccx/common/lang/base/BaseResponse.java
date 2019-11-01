package com.ccx.common.lang.base;

import java.io.Serializable;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = -13096982410780581L;

    /**
     * 返回状态，默认false表示失败
     */
    private Boolean status = Boolean.FALSE;

    /**
     * 返回码，默认-1表示失败
     */
    private String code = "-1";

    /**
     * 返回码信息说明
     */
    private String message;

    /**
     * 返回内容
     */
    private T attributes;

}
