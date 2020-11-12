package com.sj56.hrs.web.basic;

import javax.swing.*;
import java.awt.*;

/**
 * Shanghai ** Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName example_1019
 * @date 2020/10/20 19:11
 */
public class example_1019 {
    public static void main(String[] args) {
        new ComponentFrame();
    }

}
class ComponentFrame extends JFrame{
    JTextField tfName;
    JButton btConfirm;
    JCheckBox chkInt1,chkInt2,chkInt3;
    JRadioButton radMan,radWomen;
    JComboBox<String> comAddress;
    String[] address={"城市1","城市2","城市3","城市4","城市5","城市6","城市7"};
    JTextArea taMemo;
    public ComponentFrame(){
        this.setLayout(new FlowLayout());
        initComp();
        this.setVisible(true);//窗口可视化
        this.setBounds(100,100,400,300);//窗口位置、大小
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//默认的退出方式
    }
    public void initComp(){
        tfName=new JTextField(10);
        btConfirm=new JButton("确认");
        chkInt1=new JCheckBox("喜欢音乐");
        chkInt2=new JCheckBox("喜欢旅游");
        chkInt3=new JCheckBox("喜欢足球");
        radMan=new JRadioButton("男",true);
        radWomen=new JRadioButton("女");
        comAddress=new JComboBox<String>(address);

        taMemo=new JTextArea(10,20);//文本框高度、宽度

        add(new JLabel("文本框："));
        add(tfName);

        add(new JLabel("按钮："));
        add(btConfirm);

        add(new JLabel("复选框："));
        add(chkInt1);
        add(chkInt2);
        add(chkInt3);

        ButtonGroup sex=new ButtonGroup();
        sex.add(radMan);
        sex.add(radWomen);

        comAddress.addItem("烟台");
        comAddress.addItem("威海");
        comAddress.addItem("潍坊");

        add(new JLabel("文本框"));
        add(tfName);

        add(new JLabel("文本框"));
        add(tfName);

        add(new JLabel("文本框"));
        add(tfName);

        add(new JLabel("文本框"));
        add(tfName);
    }
}