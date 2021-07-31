package monopoly_1;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AdCard extends JPanel {
	private JPanel setPanel; //���� ī�� ����
	private JButton pickCard; // ī�� �̱� ��ư ����
	private JLabel bg; // ���̺� �ߺ��� ���ϱ� ���� ��� ���̺� ����
	private JLabel adCard1,adCard2,adCard3; // �� ī�� �̺�Ʈ�� ���� 3���� ���̺� ����
	private int nRandom; // ������ ���� ���� ����
	private int Result; // ������� �����ִ� ���� ����
	
	private GameListener gameL; //������ ��ü ����
	
	public AdCard(){
		setPreferredSize(new Dimension(480,480)); //������ 480X480
		setBackground(Color.white); //���� �Ͼ��
		setLayout(null); //layout disable
		
		gameL = new GameListener(); //������ ��ü ����
		
		setPanel = new JPanel(); //�г� ����
		setPanel.setBounds(10,10,460,460);//location
		setPanel.setBackground(Color.white); //����
		setPanel.setLayout(null); //layout diaable
		add(setPanel); //�г� �߰�
		
		bg = new JLabel(); //��淹�̺� ����
		bg.setBounds(0,0,460,460); 
		bg.setBackground(Color.white); 
		bg.setVisible(true); 
		setPanel.add(bg); 
		
		Font fntButton = new Font("Verdana",Font.PLAIN,12); //��Ʈ ����
		
		pickCard = new JButton("Pick Card"); //ī�� �̱� ��ư �߰�
		pickCard.setBounds(100,100,200,200);//��ġ ����
		pickCard.setVisible(true);
		pickCard.setFont(fntButton); //��Ʈ
		pickCard.addActionListener(gameL); //�׼Ǹ����� �߰�
		bg.add(pickCard); //���̺� ���� ī�� �̱� ��ư �߰�
		
		ImageIcon a_icon = new ImageIcon("Break.gif"); // �ް� �̹��� ����
		adCard1 = new JLabel(a_icon); // ���̺� ���� �� �̹��� ����
		adCard1.setBounds(0,0,440,440); 
		adCard1.setHorizontalAlignment(SwingConstants.CENTER); 
		adCard1.setVerticalAlignment(SwingConstants.CENTER);
		adCard1.setVisible(false); 
		setPanel.add(adCard1);
		
		ImageIcon a_icon2 = new ImageIcon("Grade.gif"); //A����ǥ �̹��� ���� 
		adCard2 = new JLabel(a_icon2); 
		adCard2.setBounds(0,0,440,440); 
		adCard2.setHorizontalAlignment(SwingConstants.CENTER);
		adCard2.setVerticalAlignment(SwingConstants.CENTER);
		adCard2.setVisible(false); 
		setPanel.add(adCard2); 
		
		ImageIcon a_icon3 = new ImageIcon("Scholarship.gif"); //���б� �̹��� ����
		adCard3 = new JLabel(a_icon3);
		adCard3.setBounds(0,0,440,440);
		adCard3.setHorizontalAlignment(SwingConstants.CENTER);
		adCard3.setVerticalAlignment(SwingConstants.CENTER);
		adCard3.setVisible(false); 
		setPanel.add(adCard3);
		
		nRandom = 0; //���� ���� ������ 0���� �ʱ�ȭ
	}
	
	public void start(){ //���� �޼ҵ�
		setPanel.setVisible(true); 
		pickCard.setVisible(true); 
	}
	public void Reset(){//���� �޼ҵ�
		adCard1.setVisible(false); 
		adCard2.setVisible(false);
		adCard3.setVisible(false);
	}
	
	public void Result1(){ //ù��° ���
		adCard1.setVisible(true); //�ް� �̺�Ʈ ���̰� �ϱ�
		String result = "Break!"; // ��Ʈ�� ���� ���� �� �ʱ�ȭ 
		JOptionPane.showMessageDialog(null,result);//showMessageDialog �޼ҵ� �߻��Ͽ� result ���
	}
	public void Result2(){ //�ι��� ���
		adCard2.setVisible(true); 
		String result = "A+ Grade!"; 
		JOptionPane.showMessageDialog(null,result);
	}
	public void Result3(){ //����° ���
		adCard3.setVisible(true); 
		String result = "Scholarship!"; 
		JOptionPane.showMessageDialog(null,result);
	}
	
	private class GameListener implements ActionListener{ //������ Ŭ���� ����
		
		public void actionPerformed(ActionEvent event){ 
		
		Object obj = event.getSource(); //�̺�Ʈ ��ü ����
		
		int a = 0; //������ ���� �� �ʱ�ȭ
		nRandom = (int)(Math.random()*3)+1; //���� ����
		a = nRandom; // ������ �ʱ�ȭ
		
		if(obj == pickCard){ //��ư�� ���� ��
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
	}//actionPerformed()
	}//GameListener class
}//PCard class