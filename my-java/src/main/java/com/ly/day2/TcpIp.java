package com.ly.day2;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * ClassName: TcpIp
 * Package: com.ly.day2
 * Description:
 *
 * @Author ly
 * @Create 2023/11/2 16:12
 * @Version 1.0
 */
public class TcpIp {
   public static void main(String[] args) throws UnknownHostException {
      InetAddress name = InetAddress.getByName("127.0.0.1");
      //InetAddress name1 = InetAddress.getByName("www.baidu.com");
      //System.out.println(name1);

   }
}
