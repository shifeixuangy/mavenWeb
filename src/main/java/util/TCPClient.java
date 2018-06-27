package util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by shifeixuan on 2018/6/15.
 */
public class TCPClient {
    public static void main(String[] args) throws Exception {
        String socketAddress ="csjs.gzjs.gov.cn";
        InetAddress netAddress = InetAddress.getByName(socketAddress);
        System.out.println(netAddress);
        System.out.println(netAddress.getHostAddress());
        String [] ngd =new String[]{"haha","hehe"};
        for (String a :
                ngd) {
            System.out.println(a);
        }
        Socket s = new Socket(netAddress.getHostAddress(),60009);
        OutputStream out = (OutputStream) s.getOutputStream();
        out.write("12 78 12 30 7F 3C 20 6D 3C 7F 17 24 30 6A 1F 78 30 7F 3C 17 7C 7F 3D 7F 17 29 20 30 55 17 17 20 17 17 17 17 55 3D 17 35 55 35 17 3C 5D 7F 34 15 3C 6D 17 17 30 7F 3B 3C 3D 17 30 E0 30 17 17 7F 17 38 35 5D 0F 0A ".getBytes());
        //获取socket流中的输入流。将服务端反馈的数据获取到，并打印
        InputStream in = s.getInputStream();
        byte[] buf = new byte[1024];
        int len = in.read(buf);
        System.out.println(new String(buf,0,len));
        s.close();
    }
}
