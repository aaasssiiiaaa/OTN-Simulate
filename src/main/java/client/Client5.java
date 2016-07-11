package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by asia on 2016/7/5.
 * 需求：功能二的第三个虚拟机客户端
 */
public class Client5 {
    public static void main(String[] args) throws IOException, InterruptedException {
        //监听端口3005，即监听client2端
        ServerSocket ss = new ServerSocket(3005);
        Socket s = ss.accept();         //accept返回值就是一个socket
        if(s.isConnected()) {
            System.out.print("client5 and client2 connected.\r\n");
        }
        while (true){
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream())); //读取输入socket的内容,buffer是字符流,stream是字节流,该句括号作用是转换
            String line = br.readLine();
            System.out.println(line);
            if (!s.isConnected()) {
                break;
            }
            //br.close();
            //s.close();
            Thread.sleep(200);
            //睡眠0.2秒后发送给下一个功能
            Socket s8 = new Socket("10.108.70.140",3009);//TODO 没写下个功能的地址
            if(s8.isConnected()) {
                System.out.print("client5 and server connected.\r\n");
            }
            PrintStream ps1 = new PrintStream(s8.getOutputStream());
            ps1.print("Data from client5:"+s.getInputStream());    //TODO 输出是字节流，line是字符流，估计这会出错
        }
    }
}
