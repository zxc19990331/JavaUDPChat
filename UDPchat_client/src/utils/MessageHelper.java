package utils;

import constants.Constants;
import constants.Global;
import models.ChatMessage;
import com.alibaba.fastjson.*;
import utils.PortHelper;

import javax.sound.sampled.Port;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;


public class MessageHelper {

    public static DatagramSocket sendMsg(byte[] bytes){
        int server_port = PortHelper.getServerPort();
        int client_port = PortHelper.getClientPort();
        String server_address = Constants.SERVER_ADDRESS;
        try {
            if(Global.clientSocket == null ){
                Global.clientSocket = new DatagramSocket(client_port);
            }
            DatagramSocket socket = Global.clientSocket;
            DatagramPacket packet = new DatagramPacket(bytes,bytes.length, InetAddress.getByName(server_address),server_port);
            socket.send(packet);
            return socket;
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
    public static List<String> getUserList(String jasonString){
        List<String> userList =new ArrayList<String>();
        userList=JSON.parseArray(jasonString,String.class);
        return userList;
    }
}
