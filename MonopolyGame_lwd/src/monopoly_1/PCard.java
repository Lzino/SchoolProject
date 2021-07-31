package monopoly_1;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PCard extends JPanel {
	private JPanel setPanel; //�г�Ƽ ī�� �г� ����
	private JButton pickCard; // ī��̱� ��ư ����
	private JLabel bg; // ���̺� �ߺ��� ���ϱ� ���� ��� ���̺� ����
	private JLabel pCard1,pCard2,pCard3; // �г�Ƽī�� ��������� ���̺� ���� ����
	private int nRandom; //���� ���� ����
	private int Result; //����� ����
	
	private GameListener gameL; //������ ��ü ����
	
	public PCard(){
		setPreferredSize(new Dimension(480,480));//������ ����
		setBackground(Color.white);//����
		setLayout(null); //layout disable
		
		gameL = new GameListener(); //������ ��ü ����
		
		setPanel = new JPanel(); //�г� ����
		setPanel.setBounds(10,10,460,460);
		setPanel.setBackground(Color.white); 
		setPanel.setLayout(null); 
		add(setPanel); 
		
		bg = new JLabel();//��淹�̺� ����
		bg.setBounds(0,0,460,460);
		bg.setBackground(Color.white); 
		bg.setVisible(true); 
		setPanel.add(bg);
		
		Font fntButton = new Font("Verdana",Font.PLAIN,12); //��Ʈ ����
		
		pickCard = new JButton("Pick Card");//ī��̱� ��ư ����
		pickCard.setBounds(100,100,200,200); 
		pickCard.setVisible(true);
		pickCard.setFont(fntButton); 
		pickCard.addActionListener(gameL); 
		bg.add(pickCard);
		
		ImageIcon p_icon = new ImageIcon("MT.gif"); //'MT' �̹�������
		pCard1 = new JLabel(p_icon); 
		pCard1.setBounds(0,0,440,440); 
		pCard1.setHorizontalAlignment(SwingConstants.CENTER); 
		pCard1.setVerticalAlignment(SwingConstants.CENTER); 
		pCard1.setVisible(false);
		setPanel.add(pCard1); 
		
		ImageIcon p_icon2 = new ImageIcon("FGrade.gif"); //'F����ǥ' �̹��� ����
		pCard2 = new JLabel(p_icon2); 
		pCard2.setBounds(0,0,440,440); 
		pCard2.setHorizontalAlignment(SwingConstants.CENTER); 
		pCard2.setVerticalAlignment(SwingConstants.CENTER); 
		pCard2.setVisible(false); 
		setPanel.add(pCard2); 
		
		ImageIcon p_icon3 = new ImageIcon("extra.gif"); //'����' �̹��� ����
		pCard3 = new JLabel(p_icon3); 
		pCard3.setBounds(0,0,440,440);
		pCard3.setHorizontalAlignment(SwingConstants.CENTER); 
		pCard3.setVerticalAlignment(SwingConstants.CENTER);
		pCard3.setVisible(false);
		setPanel.add(pCard3);
		
		nRandom = 0; // ���� ���� 0���� �ʱ�ȭ 
	}
	
	public void Result1(){ //ù��° �̺�Ʈ �޼ҵ�
		pCard1.setVisible(true); 
		String result = "MT next!"; 
		JOptionPane.showMessageDialog(null,result);
		
	}
	public void Result2(){ //�ι�° �̺�Ʈ �޼ҵ�
		pCard2.setVisible(true); 
		String result = "F Grade!"; 
		JOptionPane.showMessageDialog(null,result); 
	}
	public void Result3(){ // ����° �̺�Ʈ �޼ҵ�
		pCard3.setVisible(true); 
		String result = "Extra Lecture!"; 
		JOptionPane.showMessageDialog(null,result);
	}
	
	public void start(){ //���� �޼ҵ� (������� ����)
		setPanel.setVisible(true); 
		pickCard.setVisible(true); 
	}
	public void Reset(){ //���� �޼ҵ�
		pCard1.setVisible(false); 
		pCard2.setVisible(false); 
		pCard3.setVisible(false);
	}
	
	private class GameListener implements ActionListener{

		public void actionPerformed(ActionEvent event){ 
		
		Object obj = event.getSource(); // �̺�Ʈ ��ü ����
		
		int a = 0; //������ ���� ���� �� �ʱ�ȭ
		nRandom = (int)(Math.random()*3)+1; //���� �߻�
		a = nRandom; //������ �ʱ�ȭ
		
		if(obj == pickCard){ //��ư�� ��������
			pickCard.setVisible(false); //��ư �����
		if(a%3==2) {//������ 3���� �������� 2�� ������
			Result1(); 
			Reset(); 
			}//if
		
		else if(a%3==1){//������ 3���� �������� 1�� ������
			Result2(); 
			Reset(); 
			} //else if
		
		else {//3�� ����̸�
			Result3(); 
			Reset();
			} //else
	
		}//if(obj == pickCard)
	}//actionPerformed
  }//GameListener class
}//PCard class