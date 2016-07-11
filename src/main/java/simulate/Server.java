package simulate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

/**
 * Created by asia on 2016/6/21.
 * 需求：分发数据server端，主动。
 * 思路：1. 多个随机的int数据类型
 *       2. 切割数据，长度问题
 *       3. 控制分发数据目的虚拟机
 *       4. 解帧-交叉-成帧按顺序执行
 *       5. 控制分发数据时间
 */
public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        long startTime;
        long endTime;
        //发送数据给功能一client(分部发送给不同client)
        String[] ipAddress = {"127.0.0.1","",""};//TODO 没写地址
        Socket[] socket= {new Socket(ipAddress[0],3000),    //三个虚拟机地址
                new Socket(ipAddress[1],3000),new Socket(ipAddress[2],3000)};
        if(socket[0].isConnected()&&socket[1].isConnected()&&socket[2].isConnected()) {
            System.out.print("connected.\r\n");
        }
        //连接成功，开始计时
        startTime = Calendar.getInstance().getTimeInMillis();
        //随机产生30个int型数据,StringBuffer存
        DataGenerate dg = new DataGenerate();
        //定时输出一段数据，使用timer，每隔一秒发送一段
        TimerTest tt = new TimerTest(socket,dg.daGenrate());
        tt.Run();
        //ps.close();
        //s.close();

        //监听功能三的发送端口，检测是否收到最后一个数据，是则停止计时
        ServerSocket ss = new ServerSocket(3009);   //监听来自三个client的数据，时间间隔已设好，会一个一个过来，不会同时。
        Socket s9 = ss.accept();         //accept返回值就是一个socket
        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(s9.getInputStream())); //读取输入socket的内容,buffer是字符流,stream是字节流,该句括号作用是转换
            String line = br.readLine();
            System.out.println(line);
            if (line.equals(dg.daGenrate()[5])){    //判断最后一个数据是否返回到server
                endTime=Calendar.getInstance().getTimeInMillis();
                System.out.println("整个过程耗时："+(endTime-startTime));
                break;
            }
        }
        s9.close();
        ss.close();

    }
}
