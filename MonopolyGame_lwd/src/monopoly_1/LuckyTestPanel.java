package monopoly_1;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LuckyTestPanel extends JPanel {
	private JPanel setPanel; // �̴ϰ��� �г� ����
	private JLabel lblTitle; // Ÿ��Ʋ ���� ���̺� ����
	private JButton btnMiddle, btnLeft, btnRight; // ī�� ��ư�� ����
	private JButton btnMiddle2,btnLeft2, btnRight2; // ������ ī�� ��ư�� ����
	private int nRandom; // ������ ���� ���� ����
	public int resultNumber =1; //??
	
	private GameListener gameL; //���� ������ ����
	
	public LuckyTestPanel() {
		setPreferredSize(new Dimension(430,430)); //������ 430X430
		setBackground(Color.white); //������ �Ͼ��
		setLayout(null); //Layout disable
		
		gameL = new GameListener();// ���� ������ ����
		
		setPanel = new JPanel(); //�г� ����
		setPanel.setBounds(10,10,400,400);//location
		setPanel.setBackground(Color.white);//����
		setPanel.setLayout(null); //Layout disable
		add(setPanel); // �г� �߰�
		
		ImageIcon mb_icon = new ImageIcon("Minigame.png"); //����̹��� ����
		lblTitle = new JLabel(mb_icon); //���̺� ���� �� �̹��� ����
		lblTitle.setBounds(0,0,400,400); // ��ġ ����
		lblTitle.setFont(new Font("Verdana",Font.BOLD,30)); //��Ʈ����
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER); //��ġ����
		lblTitle.setVerticalAlignment(SwingConstants.CENTER); //��ġ����
		lblTitle.setVisible(true); //���̰� �ϱ�
		setPanel.add(lblTitle); //�гο� ���̺� �߰�
		
	
		Font fntButton = new Font("Verdana",Font.PLAIN,12); //��Ʈ ����
		ImageIcon m_Card1 = new ImageIcon("Card1.jpg");	//ī�� �̹���
		ImageIcon m_Card2 = new ImageIcon("Card2.jpg");	//������ ī�� �̹���
		
		btnLeft = new JButton(m_Card1); //ù��° ��ư ���� �� �̹��� ����
		btnLeft.setBounds(40,200,100,150); //��ġ ����
		btnLeft.setFont(fntButton); // ��Ʈ����
		btnLeft.setVisible(true);// ���̰� �ϱ�
		btnLeft.addActionListener(gameL); //�׼Ǹ����� �߰�
		btnLeft.setBorderPainted(false); //��輱 ����
		lblTitle.add(btnLeft); //���̺� ��ư �߰�
		
		btnLeft2 = new JButton(m_Card2); //������ ù��° ��ư ���� �� �̹��� ����
		btnLeft2.setBounds(40,200,100,150); //��ġ ����
		btnLeft2.setFont(fntButton); //��Ʈ����
		btnLeft2.setVisible(false); //������ �ʰ� �ϱ�
		btnLeft2.addActionListener(gameL);//�׼Ǹ����� �߰�
		btnLeft2.setBorderPainted(false);//��輱 ����
		lblTitle.add(btnLeft2); //���̺� ��ư �߰�
		
		
		btnMiddle = new JButton(m_Card1);//����° �߰� ���� �� �̹��� ����
		btnMiddle.setBounds(160,200,100,150);//��ġ ����
		btnMiddle.setFont(fntButton);//��Ʈ����
		btnMiddle.setVisible(true);//���̰� �ϱ�
		btnMiddle.addActionListener(gameL);//�׼Ǹ����� �߰�
		btnMiddle.setBorderPainted(false);//��輱 ����
		lblTitle.add(btnMiddle);//���̺� ��ư �߰�
		
		btnMiddle2 = new JButton(m_Card2); //������ �߰� ��ư ���� �� �̹��� ����
		btnMiddle2.setBounds(160,200,100,150);//��ġ ����
		btnMiddle2.setFont(fntButton);//��Ʈ����
		btnMiddle2.setVisible(false);//������ �ʰ� �ϱ�
		btnMiddle2.addActionListener(gameL);//�׼Ǹ����� �߰�
		btnMiddle2.setBorderPainted(false);//��輱 ����
		lblTitle.add(btnMiddle2);//���̺� ��ư �߰�
		
		btnRight = new JButton(m_Card1);//������ ��ư ���� �� �̹��� ����
		btnRight.setBounds(280,200,100,150);//��ġ ����
		btnRight.setFont(fntButton);//��Ʈ����
		btnRight.setVisible(true);//���̰��ϱ�
		btnRight.addActionListener(gameL);//�׼Ǹ����� �߰�
		btnRight.setBorderPainted(false);//��輱 ����
		lblTitle.add(btnRight);	//���̺� ��ư �߰�
		
		btnRight2 = new JButton(m_Card2); //������ ������ ��ư ���� �� �̹��� ����
		btnRight2.setBounds(280,200,100,150); //��ġ����
		btnRight2.setFont(fntButton); //��Ʈ����
		btnRight2.setVisible(false); //������ �ʰ� �ϱ�
		btnRight2.addActionListener(gameL);//�׼� ������ �߰�
		btnRight2.setBorderPainted(false); //��輱 ����
		lblTitle.add(btnRight2);	 //���̺� ��ư �߰�
		
		nRandom = 0; //�������� ������ 0���� �ʱ�ȭ
		
	}//constructor
	
	public int getResult() {return resultNumber;}
	public void setReusult(int value) { resultNumber=value; }

	
	public void rightResult(){ //��÷�� ���� ���
		String result = "You are Lucky Guy!"; //��Ʈ�� ���� ���� �� �ʱ�ȭ
		JOptionPane.showMessageDialog(null,result); // //showMessageDialog �޼ҵ� �����Ͽ� result ���
		resultNumber = 1;
	}//rightResult()
	
	public void wrongResult(){ //���� ���� ���
		String result = "You are Unlucky Guy!";//��Ʈ�� ���� ���� �� �ʱ�ȭ
		JOptionPane.showMessageDialog(null,result);////showMessageDialog �޼ҵ� �����Ͽ� result ���
		resultNumber = 0;
	}//wrongResult()
	
	public void start(){ //���۽� ���� �ʱ�ȭ
		lblTitle.setVisible(true);
		btnMiddle.setVisible(true);
		btnLeft.setVisible(true);
		btnRight.setVisible(true);
	} //start()
	
	public void Reset(){ //����� ��� ���̺� �ð�ȭ ����
		lblTitle.setVisible(false);
		btnMiddle.setVisible(false);
		btnLeft.setVisible(false);
		btnRight.setVisible(false);
		btnMiddle2.setVisible(false);
		btnLeft2.setVisible(false);
		btnRight2.setVisible(false);	
	}
	
	private class GameListener implements ActionListener{ //������Ŭ���� ����
		
		public void actionPerformed(ActionEvent event){ //actionPerformed �޼ҵ� ����
		
		Object obj = event.getSource(); //�̺�Ʈ ��ü ����
		nRandom = (int)(Math.random()*50)+1; // ���� ����
		
		if(obj == btnLeft||obj == btnMiddle||obj == btnRight){ //��ư�� ��������
			
			
			if(obj == btnLeft){//���ʹ�ư�� ������
				btnLeft.setVisible(false); //ù��° ī�� ��������
				btnLeft2.setVisible(true);} //������ ī�� �����ֱ�
			if(obj == btnRight){ //������ ��ư ������
				btnRight.setVisible(false);//ù��° ī�� ��������
				btnRight2.setVisible(true);}//������ ī�� �����ֱ�
			if(obj == btnMiddle){ //��� ��ư ������
				btnMiddle.setVisible(false);//ù��° ī�� ��������
				btnMiddle2.setVisible(true);}//������ ī�� �����ֱ�
			if(nRandom%2==1){ //������ Ȧ���� ���
				rightResult(); //��÷ �޼ҵ� �ߵ�
				//resultNumber = 1;
				Reset(); //���¸޼ҵ� �ߵ�
			}else{ //������ ¦���� ���
				wrongResult(); //�� �޼ҵ� �ߵ�
				//resultNumber = 0;
				Reset(); //���� �޼ҵ� �ߵ�
			}//else
			
		} //second elseif
		
	}//GameListener Class
		
	}//LuckyTestPanel()
	
}//class LuckyTestPanel