package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by asia on 2016/7/5.
 * 需求：功能三的第一个虚拟机客户端
 */
public class Client6 {
    public static void main(String[] args) throws IOException, InterruptedException {
        //监听端口3006，即监听client3端
        ServerSocket ss = new ServerSocket(3006);
        Socket s = ss.accept();         //accept返回值就是一个socket
        while (true){
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream())); //读取输入socket的内容,buffer是字符流,stream是字节流,该句括号作用是转换
            String line = br.readLine();
            System.out.println(line);
            if (!s.isConnected()) {
                break;
            }
            //br.close();
            //s.close();
            Thread.sleep(500);
            //睡眠0.5秒后发送给下一个功能
            Socket s6 = new Socket("",3009);//TODO 没写下个功能的地址，发给server端
            if(s6.isConnected()) {
                System.out.print("connected.\r\n");
            }
            PrintStream ps1 = new PrintStream(s6.getOutputStream());
            ps1.print(s.getInputStream());    //TODO 输出是字节流，line是字符流，估计这会出错
        }
    }
}
