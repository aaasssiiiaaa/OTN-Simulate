import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by yby on 2016/7/11.
 */
public class SocketTest {

    public static int PORT = 10240;
    public static String ADDR = "127.0.0.1";

    @Before
    public void socketCloseTest() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);

        try {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            while (true) {
                int data = inputStream.read();
                if (data != -1) {
                    System.out.print(data);
                } else {
                    serverSocket.close();
                    break;
                }
            }
        } catch (InterruptedIOException e) {
            System.out.print("shutdown");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
    }

    @After
    public void clientTest() throws IOException {
        Socket client = new Socket(SocketTest.ADDR, SocketTest.PORT);
        if (client.isConnected()) {
            for (int i=0; i<5; i++) {
                client.getOutputStream().write(i);
            }
            client.getOutputStream().close();
//            client.close();
        }
    }

}
