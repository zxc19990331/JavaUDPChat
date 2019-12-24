package thread;

import constants.Constants;
import constants.Global;
import models.ChatMessage;
import presenters.ChatPresenter;
import presenters.HomePresenter;
import utils.MessageHelper;

import java.net.DatagramPacket;


public class ClientHandleThread extends Thread {
    private DatagramPacket packet = null;
    private ChatMessage chatMessage = new ChatMessage();

    public ClientHandleThread(DatagramPacket packet){
        this.packet = packet;
        this.chatMessage = ChatMessage.getInstance(new String(packet.getData()));
        System.out.println("收到消息：" + chatMessage.toJsonString());
    }


    @Override
    public void run() {
        super.run();
        switch (chatMessage.getMsgType()){
            //收到用户列表消息，列表个更新
            case Constants.MSG_TYPE_USER_LIST:
                for(String userName: MessageHelper.getUserList(chatMessage.getContent())){
                    System.out.print("user:"+userName);
                    if(!userName.equals(Global.myUserInfo.getUserName()))
                         Global.homePresenter.addNewUserButton(userName);
                }
                break;
            //收到有新用户登录的消息，home列表更新
            case Constants.MSG_TYPE_NEW_USER_LOGIN:

                if(!chatMessage.getContent().equals(Global.myUserInfo.getUserName()))
                    Global.homePresenter.addNewUserButton(chatMessage.getContent());

                break;
            //收到用户登出的消息，home页列表更新
            case Constants.MSG_TYPE_NEW_USER_LOGOUT:
                Global.homePresenter.removeLoginOutButton(chatMessage.getContent());
                break;
            //收到单聊消息
            case Constants.MSG_TYPE_SINGLE_CHAT:
                System.out.println("Message:"+chatMessage.toString());
                String fromName = chatMessage.getFromID();
                for(ChatPresenter chatPresenter : Global.chatPresenters){
                    String chatname = chatPresenter.getChatName();
                    System.out.print("charName:"+chatname);
                    System.out.print("fromName:"+fromName);
                    if(chatname.equals(fromName)){
                        chatPresenter.setVisible(true);
                        chatPresenter.addReciveNewMsg(chatMessage);
                        //break;
                        return ;
                    }
                }
                //创建新聊天对话界面
                ChatPresenter newChatPresenter = HomePresenter.createChat(fromName);
                Global.chatPresenters.add(newChatPresenter);
                newChatPresenter.addReciveNewMsg(chatMessage);
                break;
                //收到群聊消息
            case Constants.MSG_TYPE_GROUP_CHAT:
                if(!Global.myUserInfo.getUserName().equals(chatMessage.getFromID()))
                    Global.groupChatPresenter.addReciveNewMsg(chatMessage);
                break;
        }

    }
}
