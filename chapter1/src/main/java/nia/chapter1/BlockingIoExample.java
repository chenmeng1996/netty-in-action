package nia.chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kerr.
 *
 * Listing 1.1 Blocking I/O example
 *
 * 阻塞IO
 * 缺点：
 * 1. 一个线程只能处理一个连接，当连接很多时会造成线程切换开销大
 * 2. 当连接没有数据时，线程会阻塞，浪费资源
 */
public class BlockingIoExample {

    /**
     * Listing 1.1 Blocking I/O example
     * */
    public void serve(int portNumber) throws IOException {
        //创建服务端socket，监听指定端口上的连接请求
        ServerSocket serverSocket = new ServerSocket(portNumber);
        //对accept方法的调用将阻塞，直到一个连接建立。该连接是已连接socket。
        Socket clientSocket = serverSocket.accept();
        //已连接socket的输入流（即客户端发送的消息流）
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        //已连接socket的输出流（即服务端发送的消息流）
        PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
        String request, response;
        //循环处理客户端发送的消息
        while ((request = in.readLine()) != null) {
            //客户端发送 Done 时停止
            if ("Done".equals(request)) {
                break;
            }
            response = processRequest(request);
            out.println(response);
        }
    }

    private String processRequest(String request){
        return "Processed";
    }
}
