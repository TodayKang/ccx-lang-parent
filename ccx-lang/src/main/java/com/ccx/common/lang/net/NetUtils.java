package com.ccx.common.lang.net;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NetUtils {

    /**
     * 获取本机 IP
     *
     * @return 本机IP
     */
    public static String getLocalIP() {
        InetAddress inetAddress = getLocalInetAddress();
        log.info("当前本机IP地址:{}", (inetAddress == null) ? null : inetAddress.getHostAddress());
        return (inetAddress == null) ? null : inetAddress.getHostAddress();
    }

    /**
     * 获取本机 InetAddress
     *
     * @return 本机 InetAddress
     */
    public static InetAddress getLocalInetAddress() {
        List<InetAddress> addressList = getLocalInetAddresses();
        return CollectionUtils.isEmpty(addressList) ? null : addressList.get(0);
    }

    /**
     * 获取本机网卡的 InetAddress（优先取site-local地址）
     *
     * @return 本机网卡的InetAddress列表
     */
    public static List<InetAddress> getLocalInetAddresses() {
        List<InetAddress> addressList = new ArrayList<>();
        try {
            // 获取本机所有网卡接口
            Enumeration<NetworkInterface> enumerationNetworkInterface = NetworkInterface.getNetworkInterfaces();
            if (enumerationNetworkInterface == null) {
                // 最原始的方法（在Windows环境获取本机IP地址）
                addressList.add(InetAddress.getLocalHost());
                return addressList;
            }

            while (enumerationNetworkInterface.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) enumerationNetworkInterface.nextElement();

                if (isValidNetworkInterface(networkInterface)) {
                    Enumeration<InetAddress> enumerationInetAddress = networkInterface.getInetAddresses();

                    while (enumerationInetAddress.hasMoreElements()) {
                        InetAddress inetAddress = (InetAddress) enumerationInetAddress.nextElement();

                        // 判断是否是IPv4，并且内网地址并过滤回环地址
                        // 先排除loop-back地址
                        if (inetAddress != null && (inetAddress instanceof Inet4Address) && !inetAddress.isLoopbackAddress()) {
                            // site-local 地址未被发现，记录不为空的候选地址
                            addressList.add(inetAddress);

                            // 优先取site-local地址
                            if (inetAddress.isSiteLocalAddress()) {
                                addressList.clear();
                                addressList.add(inetAddress);
                                return addressList;
                            }
                        }
                    }
                }
            }
        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException(e);
        }

        return addressList;
    }

    /**
     * 过滤回环网卡、点对点网卡、非活动网卡、虚拟网卡并要求网卡名字是eth或ens开头
     *
     * @param networkInterface 网卡信息
     * @return 是否可解析当前网卡地址
     * @throws SocketException
     */
    private static boolean isValidNetworkInterface(NetworkInterface networkInterface) throws SocketException {
        return (networkInterface != null) && !networkInterface.isLoopback() && !networkInterface.isPointToPoint() && networkInterface.isUp()
                && !networkInterface.isVirtual()
                && (StringUtils.startsWith(networkInterface.getName(), "eth") || StringUtils.startsWith(networkInterface.getName(), "ens"));
    }

}
