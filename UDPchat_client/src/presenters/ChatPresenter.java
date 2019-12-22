package presenters;

import constants.Constants;
import models.ChatMessage;
import views.ChatView;
import constants.Global;
import utils.MessageHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import static constants.Global.myUserInfo;

public class ChatPresenter {
    private String chatname;
    private ChatView chatView;
    private ChatMessage chatMsg;
    public String getChatName(){
        return chatname;
    }
    public void init(String chatname){
        this.chatname=chatname;
        chatView = new ChatView();
        chatView.init(chatname);
        chatMsg=new ChatMessage();
        chatView.setChatName(chatname);
    }
    public void setSingleSendButtonListener(){
        ActionListener actionListener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatMsg.setContent(chatView.getSendAreaMsg());
                chatMsg.setFromID(myUserInfo.getUserName());
                chatMsg.setToID(chatname);
                chatMsg.setMsgType(Constants.MSG_TYPE_SINGLE_CHAT);

                byte []sendMeg=chatMsg.toJsonString().getBytes();
                MessageHelper msgHelp=new MessageHelper();
                msgHelp.sendMsg(sendMeg);
                chatView.setMainAreaContent(chatView.getSendAreaMsg());
                chatView.setSendAreaEmpty();
            }
        };
        chatView.setSendButtonListener(actionListener);
    }
    public void setGroupSendButtonListener(){
        chatView.setSendButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatMsg.setContent(chatView.getSendAreaMsg());
                chatMsg.setFromID(myUserInfo.getUserName());
                chatMsg.setMsgType(Constants.MSG_TYPE_GROUP_CHAT);
                byte[]sendMsg=chatMsg.toJsonString().getBytes();
                MessageHelper.sendMsg(sendMsg);
                chatView.setMainAreaContent(chatView.getSendAreaMsg());
                chatView.setSendAreaEmpty();
            }
        });
    }
    public void setVisible(boolean b){
        chatView.setVisible(b);
    }

    public void addNewMsg(ChatMessage chatMessage){
        chatView.addNewMessage(chatMessage.getContent(),chatMessage.getToID());
    }
    public void addReciveNewMsg(ChatMessage chatMessage){
        chatView.addReciveNewMessage(chatMessage.getContent(),chatMessage.getFromID());
    }

}
