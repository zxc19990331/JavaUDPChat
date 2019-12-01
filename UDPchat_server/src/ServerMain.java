import constants.Constants;
import constants.Global;
import models.UserInfo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class ServerMain {

    public static void main(String[] args) throws SocketException {
        DatagramSocket socket= new DatagramSocket(Constants.SERVER_PORT);
        Global.serverSocket = socket;


        System.out.println(".....服务器准备启动......");

        while(true)
        {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ServerThread udpServerThread = new ServerThread(packet);
            udpServerThread.start();

        }

    }
}
