package Contacts;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//�������ѵ�MyPanel������ʵ�ֻ�ͼ
class MyPanelone extends JPanel {
    private String ss;
    private int x;
    private int y;
    private int size;

    public MyPanelone(String ss, int x, int y, int size) {
        this.ss = ss;
        this.x = x;
        this.y = y;
        this.size = size;
    }
    //����JPanel��paint����
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("����", Font.BOLD, size));
        g.drawString(ss, x, y);
    }
}
public class MyContacts extends JFrame{
    private MyPanelone myPaneone;
    private JPanel[] jPanels = new JPanel[7];
    private JButton[] jButtons = new JButton[4];
    private JTextField[] jTextFields = new JTextField[6];
    private JLabel[] jLabels = new JLabel[6];
    private String[] texts = new String[6];
    private class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            boolean flag = true;
            StringBuilder s = new StringBuilder();
            String actionCommand = e.getActionCommand();
            if(actionCommand == "���") {
                for (int i = 0; i < 6; i++) {
                    texts[i] = new String();
                    texts[i] = jTextFields[i].getText();
                    if(texts[i].equals("") || texts[i] == null) {
                        flag = false;
                        break;
                    }
                    if(i == 0) {
                        s.append(texts[i]);
                    }
                    else {
                        s.append(",").append(texts[i]);
                    }
                }
                if(flag) {
                    s.append("\n");
                    //���ı����е�����д��һ���ַ���
                    String ss = s.toString();
                    //���ַ���д���ļ�
                    FileRW.fileWrite(ss);
                    for(int i=0;i<6;i++) {
                        jTextFields[i].setText("");
                    }
                    JFrame jFrame = new JFrame();
                    jFrame.setBounds(500, 300, 300, 300);
                    MyPanelone myPanelone = new MyPanelone("��ӳɹ�", 100, 100, 20);
                    jFrame.add(myPanelone);
                    jFrame.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent e) {
                            e.getWindow().dispose();
                        }
                    });
                    jFrame.setVisible(true);
                }
                else {
                    JFrame jFrame = new JFrame();
                    jFrame.setBounds(500, 300, 300, 300);
                    MyPanelone myPanelone = new MyPanelone("����������ݶ���д����", 60, 100, 15);
                    jFrame.add(myPanelone);
                    jFrame.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent e) {
                            e.getWindow().dispose();
                        }
                    });
                    jFrame.setVisible(true);
                }
            }
            else if(actionCommand == "���") {
                for(int i=0;i<6;i++) {
                    jTextFields[i].setText("");
                }
            }
            else if(actionCommand == "�˳�") {
                System.exit(0);
            }
            else if(actionCommand == "����") {
                JFrame frame = new JFrame("����");
                JPanel jPanel = new JPanel();
                JPanel jPanel1 = new JPanel();
                JLabel jLabel = new JLabel("��������˵�����");
                JButton jButton = new JButton("ȷ��");
                JTextField jTextField = new JTextField(30);
                jPanel.add(jLabel);
                jPanel.add(jTextField);
                jButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //String actionCommand1 = e.getActionCommand();
                        String dest = jTextField.getText();
                        String findresult = FileRW.fileRead(dest);
                        if(findresult == null) {
                            for(int i=0;i<6;i++) {
                                jTextFields[i].setText("");
                            }
                            JFrame jFrame = new JFrame();
                            jFrame.setBounds(500, 300, 300, 300);
                            MyPanelone myPanelone = new MyPanelone("δ�ҵ����û�", 100, 100, 20);
                            jFrame.add(myPanelone);
                            jFrame.addWindowListener(new WindowAdapter() {                            
                                public void windowClosing(WindowEvent e) {
                                    e.getWindow().dispose();
                                }                        
                            });                    
                            jFrame.setVisible(true);
                            frame.dispose();     
                        }
                        else {
                            String[] tempdest = findresult.split(",");
                            for(int i=0;i<6;i++) {
                                jTextFields[i].setText(tempdest[i]);
                            }
                            frame.dispose();
                        }
                    }
                });
                jPanel1.add(jButton);
                frame.add(jPanel, BorderLayout.CENTER);
                frame.add(jPanel1, BorderLayout.SOUTH);
                frame.setBounds(500, 300, 400, 300);
                frame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        e.getWindow().dispose();
                    }
                });
                frame.setVisible(true);
            }
        }
    }

    MyContacts() {
        myPaneone = new MyPanelone("ͨѶ¼", 400, 60, 60);
        this.add(myPaneone);
        for(int i=0;i<7;i++) {
            jPanels[i] = new JPanel();
        }

        jLabels[0] = new JLabel("����");
        jLabels[1] = new JLabel("��������");
        jLabels[2] = new JLabel("ͨ�ŵ�ַ");
        jLabels[3] = new JLabel("�绰");
        jLabels[4] = new JLabel("�ֻ�");
        jLabels[5] = new JLabel("�����ʼ�");

        jButtons[0] = new JButton("���");
        jButtons[1] = new JButton("����");
        jButtons[2] = new JButton("���");
        jButtons[3] = new JButton("�˳�");
        
        for(int i=0;i<6;i++) {
            jTextFields[i] = new JTextField(50);
        }
        //���ò��ֹ���
        this.setLayout(new GridLayout(8, 1));

        //����������
        for(int i=0;i<6;i++) {
            jPanels[i].add(jLabels[i]);
            jPanels[i].add(jTextFields[i]);
            this.add(jPanels[i]);
        }
        for(int i=0;i<4;i++) {
            jButtons[i].addActionListener(new MyActionListener());
            jPanels[6].add(jButtons[i]);
        }
        this.add(jPanels[6]);
    }

    public static void main(String[] args) {
        JFrame f = new MyContacts();
        f.setTitle(f.getClass().getSimpleName());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setBounds(400, 200, 1000, 600);
        f.setVisible(true);
    }
}
