import constants.Constants;
import constants.Global;
import models.ChatMessage;
import models.UserInfo;
import utils.MessageHelper;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class ServerThread extends Thread {


    private DatagramPacket packet = null;
    private ChatMessage chatMessage;

    public ServerThread(DatagramPacket packet){
        this.packet = packet;
        int client_port = packet.getPort();
        System.out.println(client_port);
        System.out.println(packet.getAddress());
        this.chatMessage = ChatMessage.getInstance(new String(packet.getData()));
    }

    @Override
    public void run() {
        super.run();
        showInfo();
        String msg_type = chatMessage.getMsgType();
        String username = chatMessage.getContent();
        int client_port = packet.getPort();
        InetAddress client_address = packet.getAddress();

        switch (msg_type){
            case Constants.MSG_TYPE_NEW_USER:
                for(UserInfo user : Global.userList){
                    //有同名用户在线
                    if(username.equals(user.getUserName())){
                        MessageHelper.sendLoginFailMsg(client_address,client_port);
                        return;
                    }
                }
                //用户列表里添加新用户
                UserInfo newUser = new UserInfo(username,client_address,client_port);
                Global.userList.add(newUser);
                //给该用户发送一条登录成功消息
                MessageHelper.sendLoginSuccessMsg(client_address,client_port);
                // 用户登录时，发送已在线用户
                MessageHelper.sendUserListMsg(client_address,client_port);
                //给所有用户广播一条新用户登录消息
                MessageHelper.sendNewLoginMsg(username);


                System.out.println("增加了新用户" + username);
                break;

            //收到了用户关闭客户端后发送的登出消息
            case Constants.MSG_TYPE_NEW_USER_LOGOUT:
                String logoutname = chatMessage.getContent();
                //给所有用户广播一条用户登出消息
                for(UserInfo user:Global.userList){
                    if(user.getUserName().equals(logoutname)){
                        Global.userList.remove(user);
                        System.out.println("用户"+logoutname+"已退出");
                        break;
                    }
                }
                MessageHelper.sendNewLoginOutMsg(logoutname);
                break;

            //收到单聊消息，查找用户列表进行转发
            case Constants.MSG_TYPE_SINGLE_CHAT:
                String toID = chatMessage.getToID();
                for(UserInfo userInfo : Global.userList){
                    if(toID.equals(userInfo.getUserName())){
                        InetAddress toAddress = userInfo.getAddress();
                        int toPort = userInfo.getPort();
                        MessageHelper.sendMsg(chatMessage,toAddress,toPort);
                    }
                }
                break;
            //收到群聊消息，广播群聊
            case Constants.MSG_TYPE_GROUP_CHAT:
                MessageHelper.sendBoardcastMsg(chatMessage);
                break;
        }
    }

    public void showInfo(){
        System.out.println(chatMessage.toJsonString());
    }
}
