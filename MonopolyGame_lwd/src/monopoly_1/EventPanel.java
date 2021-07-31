package monopoly_1;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class EventPanel extends JPanel {
	private JPanel setPanel; //�̺�Ʈ�� �г� ����
	private JLabel hw,fest,free; //�̺�Ʈ�� '����','����','�����ð�'���̺� ����
	
	public EventPanel(){ 
		setPreferredSize(new Dimension(480,480)); //������ ����
		setBackground(Color.white); //����
		setLayout(null); //Layout disable
		
		setPanel = new JPanel(); //�г� ����
		setPanel.setBounds(10,10,460,460);//location
		setPanel.setBackground(Color.white); //����
		setPanel.setLayout(null); //Layout disable
		add(setPanel); // �г� �߰�
		
		ImageIcon hw_icon = new ImageIcon("hw.gif"); //'����' �̹��� ����
		hw = new JLabel(hw_icon); 
		hw.setBounds(0,0,440,440); 
		hw.setHorizontalAlignment(SwingConstants.CENTER);
		hw.setVerticalAlignment(SwingConstants.CENTER); 
		hw.setVisible(false); // ���ܳ���
		setPanel.add(hw); // �г� ���� ���̺� �߰�
		
		ImageIcon fest_icon = new ImageIcon("fest.gif");//'����'�̹��� ����
		fest = new JLabel(fest_icon);
		fest.setBounds(0,0,440,440); 
		fest.setHorizontalAlignment(SwingConstants.CENTER); 
		fest.setVerticalAlignment(SwingConstants.CENTER); 
		fest.setVisible(false); 
		setPanel.add(fest); 
		
		ImageIcon free_icon = new ImageIcon("free.gif");//'�����ð�' �̹��� ����
		free = new JLabel(free_icon);
		free.setBounds(0,0,440,440); 
		free.setHorizontalAlignment(SwingConstants.CENTER);
		free.setVerticalAlignment(SwingConstants.CENTER); 
		free.setVisible(false);
		setPanel.add(free); 
	}
	
	public void hwResult(){ //���� �޼ҵ�
		hw.setVisible(true);
	}//hwResult()
	
	public void festResult(){ //���� �޼ҵ�
		fest.setVisible(true);
	}//festResult()
	
	public void freeResult(){//�����ð� �޼ҵ�
		free.setVisible(true);
	}//freeResult()
	
	public void Reset(){ //���� �޼ҵ�
		free.setVisible(false);
		fest.setVisible(false);
		hw.setVisible(false);
	}//Reset()
	
}