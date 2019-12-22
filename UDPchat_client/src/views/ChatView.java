package views;

import constants.Global;
import models.UserInfo;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static constants.Global.myUserInfo;

public class ChatView extends JFrame{
    private JFrame jFrame = new JFrame("");
    private JTextArea mainArea;
    private JTextArea sendArea;
    private JButton sendButton;
    //滚动条
    private JScrollPane mainAreaP;
    private  JScrollPane sendAreaP;
    private JPanel panel ;//布局
  /*  public ChatView(){
        //聊天窗口关闭时直接隐藏
        jFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }*/
    public void init(String userName){
        jFrame.setTitle(userName);
        Container contain=getContentPane();
        contain.setLayout(new BorderLayout());
        mainArea=new JTextArea();
        mainArea.setEditable(false);
        mainAreaP=new JScrollPane(mainArea);
        mainArea.setBorder(BorderFactory.createTitledBorder("聊天记录"));//文本框Title
        panel=new JPanel();
        panel.setLayout(new BorderLayout());
        sendArea=new JTextArea(3,8);
        sendAreaP=new JScrollPane(sendArea);
        sendButton=new JButton("发送");

        panel.add(sendButton,BorderLayout.EAST);
        panel.add(sendArea,BorderLayout.CENTER);
        contain.add(mainArea,BorderLayout.CENTER);
        contain.add(panel,BorderLayout.SOUTH);
        jFrame.add(contain);
        jFrame.setSize(500,300);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }
    public String getSendAreaMsg(){
        return sendArea.getText().trim();
    }
    public void setChatName(String chatname){
        jFrame.setTitle(chatname);
    }
    public void setSendButtonListener(ActionListener actionListener){sendButton.addActionListener(actionListener);}
    public void setMainAreaContent(String sendContent){mainArea.append(Global.myUserInfo.getUserName()+"(我):"+"\n"+sendContent+"\n");}
    public void setSendAreaEmpty(){sendArea.setText("");}
    public void setVisible(boolean b){
        jFrame.setVisible(b);
    }
    public void addNewMessage(String message,String fromID){mainArea.append(fromID+":"+"\n"+message+"\n");}
    public void addReciveNewMessage(String message,String toID){mainArea.append(toID+":"+"\n"+message+"\n");}
   /* public void setWindowClose(){
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //super.windowClosing(e);
                jFrame.setVisible(false);
            }
        });
    }*/
}
