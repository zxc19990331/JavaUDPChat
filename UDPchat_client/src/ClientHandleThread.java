import constants.Constants;
import constants.Global;
import models.ChatMessage;
import presenters.ChatPresenter;
import presenters.HomePresenter;

import java.net.DatagramPacket;

public class ClientHandleThread extends Thread {
    private DatagramPacket packet = null;
    private ChatMessage chatMessage = new ChatMessage();

    public ClientHandleThread(DatagramPacket packet){
        this.packet = packet;
        this.chatMessage = ChatMessage.getInstance(new String(packet.getData()));
    }


    @Override
    public void run() {
        super.run();
        switch (chatMessage.getMsgType()){
            //收到有新用户登录的消息，home列表更新
            case Constants.MSG_TYPE_NEW_USER_LOGIN:
                break;
            //收到用户登出的消息，home页列表更新
            case Constants.MSG_TYPE_NEW_USER_LOGOUT:
                break;
            //收到单聊消息
            case Constants.MSG_TYPE_SINGLE_CHAT:
                String fromName = chatMessage.getFromID();
                for(ChatPresenter chatPresenter : Global.chatPresenters){
                    String chatname = chatPresenter.getChatName();
                    if(chatname.equals(fromName)){
                        chatPresenter.setVisible(true);
                        chatPresenter.addNewMsg(chatMessage);
                        break;
                    }
                }
                //创建新聊天对话界面
                ChatPresenter newChatPresenter = HomePresenter.createChat(fromName);
                Global.chatPresenters.add(newChatPresenter);
                break;
        }

    }
}
