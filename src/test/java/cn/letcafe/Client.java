package cn.letcafe;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8888;
    private static Socket socket;

    public static void main(String[] args) {
        try {
            if (socket == null) {
                socket = new Socket(HOST, PORT);
                socket.setSoTimeout(3000);
            }
            System.out.println("在控制台输入文本：");
            // 获取控制台的输入
            Scanner scanner = new Scanner(System.in);
            // 初始化写入服务器的配置
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
            while (true) {
                // 每隔（1秒），执行下面所有的代码（把控制台的数据通过Socket传给服务器）
                Thread.sleep(1000);
                // 获取到控制台的一行的文本（字符串）
                String str = scanner.nextLine();
                // 开始写入本地缓冲区
                bufferedOutputStream.write(str.getBytes());
                // 把缓冲区数据写入给服务端
                bufferedOutputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
