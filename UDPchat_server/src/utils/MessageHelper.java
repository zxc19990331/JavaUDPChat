package utils;

import constants.Constants;
import constants.Global;
import models.ChatMessage;
import models.UserInfo;
import com.alibaba.fastjson.*;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;

public class MessageHelper {

    public static void sendMsg(ChatMessage chatMessage, InetAddress client_address,int client_port){
        chatMessage.setDate(DateHelper.getCurrentDate());
        sendMsg(chatMessage.toJsonString().getBytes(),client_address,client_port);
    }

    public static void sendMsg(byte[] bytes, InetAddress client_address,int client_port){
        int server_port = Constants.SERVER_PORT;
        String server_address = Constants.SERVER_ADDRESS;
        try {
            DatagramSocket serverSocket = Global.serverSocket;
            DatagramPacket packet = new DatagramPacket(bytes,bytes.length, client_address,client_port);
            serverSocket.send(packet);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //给每个用户发送一条广播
    public static void sendBoardcastMsg(ChatMessage chatMessage){
        chatMessage.setDate(DateHelper.getCurrentDate());
        sendBoardcastMsg(chatMessage.toJsonString().getBytes());
    }

    //给每个用户发送一条广播
    public static void sendBoardcastMsg(byte[] bytes){
        for(UserInfo user : Global.userList){
            InetAddress address = user.getAddress();
            int port = user.getPort();
            //同步阻塞 可以异步？
            System.out.print("send board");
            sendMsg(bytes,address,port);
        }
    }

    public static void sendLoginFailMsg(InetAddress client_address,int client_port){
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMsgType(Constants.MSG_TYPE_LOGIN_FAIL);
        sendMsg(chatMessage,client_address,client_port);
    }

    public static void sendLoginSuccessMsg(InetAddress client_address,int client_port){
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMsgType(Constants.MSG_TYPE_LOGIN_SUCCESS);
        sendMsg(chatMessage,client_address,client_port);
    }

    public static void sendNewLoginMsg(String username){
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMsgType(Constants.MSG_TYPE_NEW_USER_LOGIN);
        chatMessage.setContent(username);
        sendBoardcastMsg(chatMessage);
    }

    public static void sendNewLoginOutMsg(String username){
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMsgType(Constants.MSG_TYPE_NEW_USER_LOGOUT);
        chatMessage.setContent(username);
        sendBoardcastMsg(chatMessage);
    }
    public static List<String> getUser(){
        List<String> userNameList=new ArrayList<String>();
        for(UserInfo user:Global.userList){
            userNameList.add(user.getUserName());
        }
        return userNameList;
    }
    public static void sendUserListMsg(InetAddress client_address,int client_port){
        ChatMessage chatMessage=new ChatMessage();
        chatMessage.setMsgType(Constants.MSG_TYPE_USER_LIST);
        chatMessage.setContent(JSON.toJSONString(getUser()));
        System.out.print("json:"+JSON.toJSONString(getUser()));
        sendMsg(chatMessage,client_address,client_port);
    }
}
