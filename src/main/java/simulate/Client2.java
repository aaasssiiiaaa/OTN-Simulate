import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by asia on 16/6/26.
 * 需求:同client一样,功能一解帧
 *//*
public class client.Client2 {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(3001);   //监听端口连接请求
        while (true) {
            Socket s = ss.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line = br.readLine();
            System.out.println(line);
            br.close();
            s.close();
            }
        }
}
*/
