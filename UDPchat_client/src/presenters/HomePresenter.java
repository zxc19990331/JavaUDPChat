package presenters;

import constants.Constants;
import constants.Global;
import models.ChatMessage;
import models.UserInfo;
import utils.MessageHelper;
import views.ChatView;
import views.HomeView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.*;
import java.util.List;

public class HomePresenter {
    private HomeView homeView;
    private List<String> onlineUsers;

    public void init(){
        homeView = new HomeView();
        homeView.setWindowClose(this.setWindowClose());
        homeView.setGroupButtonListener(groupButtonListener());
    }
    public ActionListener groupButtonListener(){
        ActionListener actionListener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Global.groupChatPresenter.setVisible(true);
            }
        };
        return actionListener;
    }
    public void addNewUserButton(String userName){
        JButton buttonTemp=new JButton(userName);
        buttonTemp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createChat(userName);
            }
        });
        homeView.addNewUserButton(buttonTemp);
        homeView.validate();
    }
    public void removeLoginOutButton(String outUserName){
        JPanel jPanel=homeView.getMainPanel();
        int count=jPanel.getComponentCount();
        for(int i=0;i<count;i++){
            Object object=jPanel.getComponent(i);
            if(object instanceof Button){
                Button buttonTemp=(Button)object;
                if(buttonTemp.getLabel().equals(outUserName)){
                    homeView.removeLoginOutButton(buttonTemp);
                    break;
                }
            }
        }
        homeView.validate();

    }
    /*public void setMyInfo(UserInfo userInfo){

    }*/
    public WindowListener setWindowClose(){
        WindowListener windowListener=new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                homeView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                ChatMessage chatMessage=new ChatMessage();
                chatMessage.setMsgType(Constants.MSG_TYPE_NEW_USER_LOGOUT);
                chatMessage.setContent(Global.myUserInfo.getUserName());
                byte[] sendMsg=chatMessage.toJsonString().getBytes();
                MessageHelper.sendMsg(sendMsg);
                for(ChatPresenter chatPresenter:Global.chatPresenters){
                    if(Global.myUserInfo.getUserName().equals(chatPresenter.getChatName())){
                        Global.chatPresenters.remove(chatPresenter);
                    }
                }
                homeView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        };
        return windowListener;
    }
    public void setOnlineUserList(){

    }

    public static ChatPresenter createChat(String username){
        for(ChatPresenter chatPresenter:Global.chatPresenters){
            if(username.equals(chatPresenter.getChatName())){
                chatPresenter.setVisible(true);
                return chatPresenter;
            }
        }
        ChatPresenter newchat = new ChatPresenter();
        newchat.init(username);
        newchat.setSingleSendButtonListener();
        Global.chatPresenters.add(newchat);
        return newchat;
    }
    public static ChatPresenter createGroupChat(){
        ChatPresenter groupChat=new ChatPresenter();
        groupChat.init("群聊天室");
        groupChat.setGroupSendButtonListener();
        return groupChat;
    }
}
