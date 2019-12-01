package views;

import javax.swing.*;

public class ChatView {
    private JFrame jFrame = new JFrame("");

    public ChatView(){
        //聊天窗口关闭时直接隐藏
        jFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    public void setChatName(String chatname){
        jFrame.setTitle(chatname);
    }

    public void getInput(){

    }

    public void setVisible(boolean b){
        jFrame.setVisible(b);
    }

}