package monopoly_1;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class InitialPanel extends JPanel {
	private JPanel setPanel; // ����ȭ�� �г� ����
	private JLabel lblTitle, lblMark,MainImage; 
	private JButton btnOdd, btnEven; //ù��° ��ư�� �ι�° ��ư
	private int nRandom; //�������� ����
	private MonopolyPanel Game; //��������гο��� ��ü ����
	private int Order; //�������� ����
	
	

	private InitialListener initialL; //����������  ����
	
	public InitialPanel() {
		setPreferredSize(new Dimension(800,800)); //������ 800x800
		setBackground(Color.yellow); //���� ���
		setLayout(null); //Layout disable
		
		initialL = new InitialListener();//���� ������ ����
		
		setPanel = new JPanel(); //�г� ����
		setPanel.setBounds(0,0,800,800);//location
		setPanel.setBackground(new Color(255,224,49)); //����
		setPanel.setLayout(null);//Layout disable
		setPanel.setVisible(true);
		add(setPanel); //�г� �߰�
		
		ImageIcon Title = new ImageIcon("Maintitle.png"); //����ȭ�� Ÿ��Ʋ �̹��� ����
		lblTitle=new JLabel("",Title,SwingConstants.CENTER); 
		lblTitle.setBounds(105,10,615,100); 
		lblTitle.setLayout(null); 
		lblTitle.setVisible(true); 
		setPanel.add(lblTitle);
		
		ImageIcon SubTitle = new ImageIcon("Diechoicetxt.png");//ī���� ���� �̹��� ����
		lblMark = new JLabel("",SubTitle,SwingConstants.CENTER); 
		lblMark.setBounds(150,90,500,200); 
		lblMark.setFont(new Font("Verdana",Font.BOLD,30)); 
		lblMark.setVisible(true); 
		setPanel.add(lblMark);
		
		Font fntButton = new Font("Verdana",Font.PLAIN,15);
		
		ImageIcon Diechoice1=new ImageIcon("Diechoice1_1.png"); //��ư 1 �̹��� ����
		btnOdd = new JButton(Diechoice1); 
		btnOdd.setBounds(30,265,320,350); 
		btnOdd.addActionListener(initialL); 
		btnOdd.setBorderPainted(false); // ��ư ��輱 ����
	    btnOdd.setFocusPainted(false); //��Ŀ��(�����ߴ� ��ư ǥ��) ����
		btnOdd.setContentAreaFilled(false);//��ư���� ��� ����
		btnOdd.setVisible(true); 
		setPanel.add(btnOdd);
		
		ImageIcon Diechoice2=new ImageIcon("Diechoice2_1.png"); //��ư 2 �̹��� ����
		btnEven = new JButton(Diechoice2);// ��ư2 ����
		btnEven.setBounds(430,265,320,350);
		btnEven.setBorderPainted(false); 
	    btnEven.setFocusPainted(false); 
		btnEven.setContentAreaFilled(false);
		btnEven.addActionListener(initialL);
		btnEven.setVisible(true);
		setPanel.add(btnEven);	
		
		ImageIcon TitleImage= new ImageIcon("MainCharacter.png"); //�ϴ� �̹��� ����
		MainImage=new JLabel("",TitleImage,SwingConstants.CENTER); 
		MainImage.setBounds(240,620,300,180); 
		MainImage.setHorizontalAlignment(SwingConstants.CENTER);
		MainImage.setVerticalAlignment(SwingConstants.CENTER);
		MainImage.setVisible(true); 
		setPanel.add(MainImage); 
	
		
		nRandom = 0;//���� ���� 0���� �ʱ�ȭ
	}//constructor
	
	public int getOrder(){return Order;}  //���� �޼ҵ�
	
	public void FirstResult(){ //ù��° ��� �޼ҵ�
		String result = "Player 1 is First!";  //��Ʈ�� ������ ���� �� �ʱ�ȭ
		JOptionPane.showMessageDialog(null,result); // showMessageDialog �޼ҵ带 ����Ͽ� �޼���â ���.
		lblTitle.setVisible(false); //�Ⱥ��̰� �ϱ� (���� ������ȯ�� ���� reset)
		lblMark.setVisible(false); 
		MainImage.setVisible(false);
		btnOdd.setVisible(false);
		btnEven.setVisible(false);
		setPanel.setVisible(false);
		Order = 0; //���� ���� 0���� �ʱ�ȭ
		Game = new MonopolyPanel(Order); // ��ü ����
		Game.setBounds(0,0,1400,1400); //��ü�� ��ġ ���� ����.
		add(Game);
	}//FirstResult()
	
	public void SecondResult(){ //�ι�° ��� �޼ҵ�
		String result = "Player 2 is First!";
		JOptionPane.showMessageDialog(null,result);
		lblTitle.setVisible(false); 
		lblMark.setVisible(false);
		MainImage.setVisible(false);
		btnOdd.setVisible(false);
		btnEven.setVisible(false);
		setPanel.setVisible(false);
		Order = 1;
		Game = new MonopolyPanel(Order);
		Game.setBounds(0,0,1400,1400);
		add(Game);
	}//SecondResult()
	
	
	private class InitialListener implements ActionListener{ //�̺�Ʈ ������ Ŭ���� ����
		
		public void actionPerformed(ActionEvent event){ //actionPerformed �޼ҵ� ����
		
		Object obj = event.getSource(); //������Ʈ ����
		
		if (obj == btnOdd || obj == btnEven ){ //� ��ư�� ������
			nRandom=(int)(Math.random()*50)+1; //���� ����
			if(nRandom%2==0){ //������ ¦����
				FirstResult(); //ù��° ��� �޼ҵ� ȣ��
			}else{ //������ Ȧ���� 
				SecondResult();//�ι�° ��� �޼ҵ� ȣ��
			}		
		} //second elseif
		
	}//actionPerformed()
		
	}
}//GameListener Class




