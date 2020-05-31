package cn.letcafe;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 8888;
    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        try {
            if (serverSocket == null) {
                serverSocket = new ServerSocket(PORT);
                serverSocket.setSoTimeout(60000);
            }
            System.out.println("服务器开始监听！");
            Socket accept = serverSocket.accept();
            while (true) {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(accept.getInputStream());
                if (bufferedInputStream.available() > 0) {
                    // 创建临时存储字节数组
                    byte[] receive = new byte[bufferedInputStream.available()];
                    // 将Socket传过来的字节流写入数组
                    bufferedInputStream.read(receive);
                    // clientStr 是客户端传递过来的文本，你可以拿来在服务器里面写到文件txt或者存到数据库
                    String clientStr = new String(receive);
                    // 打印到控制台，方便观看
                    System.out.println("服务端收到消息：" + clientStr);
                } else {
                    Thread.sleep(50);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}