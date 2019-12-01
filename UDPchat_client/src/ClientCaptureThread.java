import constants.Global;
import models.ChatMessage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ClientCaptureThread extends Thread{
    private DatagramPacket packet = null;
    private ChatMessage chatMessage;

    @Override
    public void run() {
        super.run();
        DatagramSocket socket = Global.clientSocket;
        while(true){
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ClientHandleThread clientHandleThread = new ClientHandleThread(packet);
            clientHandleThread.start();
        }

    }
}
