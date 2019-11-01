package com.ccx.common.lang.net;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientWebUtils {

    /**
     * 获取客户端真实IP地址(如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值)<br>
     * 例如: <br>
     * request.getHeader("x-forwarded-for"): 10.47.103.13,4.2.2.2,10.96.112.230 <br>
     * request.getHeader("X-Real-IP"): 10.47.103.13 <br>
     * request.getRemoteAddr(): 10.96.112.230 <br>
     * 客户端访问经过转发，IP将会追加在其后并以逗号隔开，最终准确的客户端信息为:<br>
     * x-forwarded-for 不为空，则为逗号前第一个IP<br>
     * X-Real-IP不为空，则为该IP <br>
     * 否则为getRemoteAddr() <br>
     *
     * @param request javax.servlet.http.HttpServletRequest
     * @return 客户端真实IP地址
     */
    public static String getClientIP(@NonNull HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        log.debug("getClientIP header X-Forwarded-For:{}", ip);

        if (StringUtils.isNotBlank(ip) && !StringUtils.equalsIgnoreCase("unknown", ip)) {
            String[] ips = StringUtils.split(ip, ",");
            for (String proxyIp : ips) {
                proxyIp = StringUtils.trimToEmpty(proxyIp);
                if (StringUtils.isNotBlank(proxyIp) && !StringUtils.equalsIgnoreCase("unknown", proxyIp)) {
                    ip = proxyIp;
                    break;
                }
            }
        }

        if (StringUtils.isBlank(ip) || StringUtils.equalsIgnoreCase("unknown", ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            log.debug("getClientIP header Proxy-Client-IP:{}", ip);
        }
        if (StringUtils.isBlank(ip) || StringUtils.equalsIgnoreCase("unknown", ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            log.debug("getClientIP header WL-Proxy-Client-IP:{}", ip);
        }
        if (StringUtils.isBlank(ip) || StringUtils.equalsIgnoreCase("unknown", ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            log.debug("getClientIP header HTTP_CLIENT_IP:{}", ip);
        }
        if (StringUtils.isBlank(ip) || StringUtils.equalsIgnoreCase("unknown", ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            log.debug("getClientIP header HTTP_X_FORWARDED_FOR:{}", ip);
        }
        if (StringUtils.isBlank(ip) || StringUtils.equalsIgnoreCase("unknown", ip)) {
            ip = request.getHeader("X-Real-IP");
            log.debug("getClientIP header X-Real-IP:{}", ip);
        }
        if (StringUtils.isBlank(ip) || StringUtils.equalsIgnoreCase("unknown", ip)) {
            ip = request.getRemoteAddr();
            log.debug("getRemoteAddr IP:{}", ip);
        }

        // 当前是局域网本机IP地址
        if (StringUtils.equalsIgnoreCase("127.0.0.1", ip) || StringUtils.equalsIgnoreCase("localhost", ip) || StringUtils.startsWith(ip, "0:0:0:0")) {
            ip = NetUtils.getLocalIP();
            log.debug("NetUtils.getLocalIP IP:{}", ip);
        }

        return ip;
    }

}
