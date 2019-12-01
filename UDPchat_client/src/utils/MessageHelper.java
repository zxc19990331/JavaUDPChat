package utils;

import constants.Constants;
import models.ChatMessage;
import utils.PortHelper;

import javax.sound.sampled.Port;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MessageHelper {

    public static DatagramSocket sendMsg(byte[] bytes){
        int server_port = PortHelper.getServerPort();
        int client_port = PortHelper.getClientPort();
        String server_address = Constants.SERVER_ADDRESS;
        try {
            DatagramSocket clientSocket = new DatagramSocket(client_port);
            DatagramPacket packet = new DatagramPacket(bytes,bytes.length, InetAddress.getByName(server_address),server_port);
            clientSocket.send(packet);
            return clientSocket;
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static DatagramSocket sendNewUserMsg(String username){
        ChatMessage message = new ChatMessage();
        message.setMsgType(Constants.MSG_TYPE_NEW_USER);
        message.setContent(username);
        byte[] bytes = message.toJsonString().getBytes();
        return sendMsg(bytes);
    }
}
