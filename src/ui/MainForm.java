package ui;

import minecraft.actions.Chat;
import ui.abstracts.AbstractFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: Gabriel
 * Date: 11.06.13
 * Time: 11:12
 * To change this template use File | Settings | File Templates.
 */
public class MainForm extends AbstractFrame {
    private JPanel rootPanel;
    private JTextField chatInput;
    private JList userList;
    private JTextArea chatLog;

    private MainForm() {
        initUI();
    }

    @Override
    protected void initUI() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        rootPanel = new JPanel();
        rootPanel.setLayout(null);


        chatInput = new JTextField();
        userList = new JList();
        chatLog = new JTextArea();

        chatLog.setBounds(10, 10, 600, 400);
        chatLog.setEditable(false);
        chatLog.setLineWrap(true);
        rootPanel.add(chatLog);
        chatLog.repaint();

        userList.setBounds(620, 10, 180, 400);
        userList.setModel(new DefaultListModel());
        rootPanel.add(userList);
        userList.repaint();

        chatInput.setBounds(10, 420, 790, 20);
        chatInput.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Chat.sendChatMessage(chatInput.getText());
                    chatInput.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        rootPanel.add(chatInput);
        chatInput.repaint();

        this.add(rootPanel);
        this.setBounds(100, 100, 815, 475);
        this.setResizable(false);
    }

    public void appendNewMessage(String msg) {
        chatLog.append("\n" + msg);
    }

    public void updateUserSource(HashSet<String> hs) {
        ((DefaultListModel) userList.getModel()).clear();
        for(String username : hs) {
            ((DefaultListModel) userList.getModel()).addElement(username);
        }
    }

    //Singleton
    private static MainForm instance;
    public static MainForm getMainForm() {
        if(instance == null) {
            instance = new MainForm();
        }
        return instance;
    }
}
