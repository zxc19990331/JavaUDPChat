package views;

import models.ChatMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import static constants.Global.myUserInfo;

public class HomeView extends JFrame{
private JTextArea userArea,userNameArea;
private String userMsgString;
private JPanel topPanel;
private JPanel mainPanel;
private Container contain;
private JScrollPane mainPanelJs;
private JButton groupButton;
    public HomeView(){
        contain=getContentPane();
        contain.setLayout(new BorderLayout());
        topPanel=new JPanel();
        topPanel.setBorder(BorderFactory.createEtchedBorder());
        topPanel.setPreferredSize(new Dimension(300,100));

        userArea=new JTextArea(3,3);
        userNameArea=new JTextArea();
        userArea.setEditable(false);
        userMsgString="IP:"+myUserInfo.getAddress()+"\n"+"Port:"+myUserInfo.getPort();
        userArea.append(userMsgString);
        userArea.setFont(new Font("楷体",1,10));
        userNameArea.append("用户名:"+myUserInfo.getUserName());
        userNameArea.setFont(new Font("楷体",1,20));

        topPanel.add(userArea,BorderLayout.WEST);
        topPanel.add(userNameArea,BorderLayout.EAST);

        mainPanel=new JPanel();
        mainPanel.setBorder(BorderFactory.createTitledBorder("在线用户"));

        mainPanelJs=new JScrollPane(mainPanel);

        groupButton=new JButton("群聊天室");
        groupButton.setPreferredSize(new Dimension(300,50));
        mainPanel.add(groupButton);

        setSize(300,800);
        setVisible(true);
        contain.add(topPanel,BorderLayout.NORTH);
        contain.add(mainPanel);

    }
   public void addNewUserButton(JButton button){
        button.setPreferredSize(new Dimension(300,50));
        mainPanel.add(button);
    }

    public void setWindowClose(WindowListener windowListener){
       this.addWindowListener(windowListener);
    }
    public void removeLoginOutButton(Button button){
        mainPanel.remove(button);
    }
    public JPanel getMainPanel(){return this.mainPanel;}
    public void setGroupButtonListener(ActionListener actionListener){groupButton.addActionListener(actionListener);}
}
