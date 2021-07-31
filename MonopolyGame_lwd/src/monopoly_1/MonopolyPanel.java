package monopoly_1;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MonopolyPanel extends JPanel{ // �����ǰ� �������̼� â�� �ö�
	int landWidth, landHeight; // ������ ũ��
	int infoWidth, infoHeight; // �������̼� â ũ��
	int eventLen, landLen; // �̺�Ʈ ĭ�� �׳� ĭ ũ��
	private LandPanel[] landPanel; // �� ��ü �迭//private LandPanel[] landPanel; // �� ��ü �迭


	private JPanel infoPanel; // �����ʿ� ǥ�õǴ� ��� �����г�
	private JLabel lbloptionImage1; // īī�� �̹�������� ���̺�
	private JLabel lblPlayer1Image, lblPlayer2Image; //���÷��̾�ĳ���� �̹������巹�̺�
	private int image; // �������� ĳ���͸� �����Ҷ� ������ ������ �����ϴ� ����
	private JLabel lblName; //monopoly���ڸ� ����ϱ����� ���̺�

	private JLabel[] lblPlayerMoney; //�÷��̾���� ���� �����ִ� ���̺�
	private JLabel lblNowPlayer1, lblNowPlayer2;  // ���÷��̾��� �̸��� �����ϴ� ���̺�
	private GameListener gameL; // ���� ���Ӱ� ���õ� ����� ������ �׼Ǹ�����
	private MenuMouseListener	menuMouseL; //��ư�� ȿ�����ִ� ���콺������
	private JButton btnRoll, btnReset; // �������ư �ϳѱ���ư
	private Dice dice; // �ֻ��� ��ü
	private int playMax; // �ִ��÷��̾��

	public static int playTurn; //���� �÷��̾����� ���


	private Player[] player; //�÷��̾���� �����ϴ� Ŭ������ü
	private String[] playerIconName; //�÷��̾��� ���� �����ϴµ� ���Ǵ� �̸��� �����ϴµ� ���� ���ڿ�����

	private JLabel lblTestPlayerPos,lblTestPlayerNum; // ���� �÷��̾���ġ��, �Ϻ����� ������� ����
	private JLabel lblDie1,lblDie2; // �ֻ����� 1,2 ǥ�÷��̺�

	private SmallPanel smallP; // ����â�̾ƴ� ���� ����â�� ����ϴµ� ���
	
	private LuckyTestPanel LuckyGamePanel; //���Һ����� 
	private OddEvenPanel OddEvenGamePanel; //Ȧ¦����
	private EventPanel Event; //�̺�Ʈ��
	private InitialPanel Ini; //���� (�ʿ������ ���ٰ���)
	private AdCard ad; //����ī��
	private PCard p; //�г�Ƽī��
	private int num;

	public MonopolyPanel(int order) {
		playMax=2;//2�ο�
		landWidth = landHeight = infoHeight = 960; // ������ ũ��
		eventLen = 130; landLen = 100;			   // event�� ũ�� // �� ũ��
		infoWidth = 400;	// info â ���� ũ��

		landPanel = new LandPanel[32]; 

		LuckyGamePanel = new LuckyTestPanel();
		LuckyGamePanel.setBounds(250, 250, 430, 430);
		LuckyGamePanel.setVisible(false);
		add(LuckyGamePanel); // �̴ϰ���1 �г�
		
		OddEvenGamePanel = new OddEvenPanel();
		OddEvenGamePanel.setBounds(250, 250, 430, 430);
		OddEvenGamePanel.setVisible(false);
		add(OddEvenGamePanel); // �̴ϰ���2 �г�
		
		Event = new EventPanel();
		Event.setBounds(250,250,480,480);
		Event.setVisible(false);
		add(Event);//�̺�Ʈ �г�
		
		ad = new AdCard();
		ad.setBounds(250,250,480,480);
		ad.setVisible(false);
		add(ad);//���� ī�� �г�
		
		p = new PCard();
		p.setBounds(250,250,480,480);
		p.setVisible(false);
		add(p);//���� ī�� �г�

		infoPanel = new JPanel(); //�����
		infoPanel.setPreferredSize(new Dimension(infoWidth, infoHeight));
		infoPanel.setBounds(landWidth, 0, infoWidth, infoHeight);//960,0,400,960
		infoPanel.setBackground(new Color(255,224,49));
		infoPanel.setLayout(null);
		// �׼Ǹ����� ,���콺�����ʻ���
		gameL = new GameListener();
		menuMouseL = new MenuMouseListener();
		playerIconName = new String[8]; //�÷��̾�������� ã���� ���Ǵ� ���ڿ�
		CharInit();
		PlayerCharInit();

		/*
		 * �ֻ����������ư ����,�ʱ�ȭ�κ�, ��������� ���õ� �׼Ǹ����� ���콺�����ʸ� �߰��Ѵ�
		 */
		ImageIcon RollIcon = new ImageIcon("diebtn_1.png");	 
		btnRoll = new JButton(RollIcon); //�ֻ��� �̹�������  
		btnRoll.setBounds(landWidth+130, 85, 130, 130);
		btnRoll.setEnabled(true);
		btnRoll.addActionListener(gameL);
		btnRoll.addMouseListener(menuMouseL);
		add(btnRoll);

		
		//��ư ������ ����
		btnRoll.setBorderPainted(false); // ��ư ��輱 ����
		//btnRoll.setFocusPainted(false); //��Ŀ��(�����ߴ� ��ư ǥ��) ����
		btnRoll.setContentAreaFilled(false);//��ư���� ��� ����

		/*
		 * �ϳѱ���ư ����,�ʱ�ȭ�κ�, ��������� ���õ� �׼Ǹ����� ���콺�����ʸ� �߰��Ѵ�
		 */
		ImageIcon ResetIcon2 = new ImageIcon("resetbtn.png");  
		btnReset = new JButton(ResetIcon2); 
		btnReset.setBounds(landWidth+130, 225, 130, 130);
		btnReset.addActionListener(gameL);
		btnReset.addMouseListener(menuMouseL);
		add(btnReset);
		btnReset.setBorderPainted(false); // ��ư ��輱 ����
		//btnReset.setFocusPainted(false); //��Ŀ��(�����ߴ� ��ư ǥ��) ����
		btnReset.setContentAreaFilled(false);//��ư���� ��� ����
		btnReset.setEnabled(false);

		
		//optionPanel�� ���ԵǴ� ĳ�����̹������̺������� �̹��������ܻ���, ���̺�ü����, ��ġ����, ����, �г��߰�
		ImageIcon icon1= new ImageIcon("kakao.png");
		lbloptionImage1=new JLabel("",icon1,SwingConstants.CENTER);
		lbloptionImage1.setBounds(landWidth+100,800,210,76);
		lbloptionImage1.setHorizontalAlignment(SwingConstants.CENTER);
		lbloptionImage1.setVerticalAlignment(SwingConstants.CENTER);
		add(lbloptionImage1);
		
		//monopoly�����̹��� ��·��̺��� ���� �̹��������ܻ���, ���̺�ü����, ��ġ����, ����, �г��߰�
		ImageIcon GameName = new ImageIcon("monopoly.png");	
		lblName=new JLabel("",GameName,SwingConstants.CENTER);
		lblName.setBounds(landWidth+107,860,190,76);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setVerticalAlignment(SwingConstants.CENTER);
		add(lblName);

		
		lblPlayerMoney = new JLabel[2];

		for(int i=0, j=0 ; i< playMax ; i++,j+=200) {
			lblPlayerMoney[i] = new JLabel("Money : 2000000");
			lblPlayerMoney[i].setForeground(Color.black);
			lblPlayerMoney[i].setFont(new Font("Cooper Std Black",Font.PLAIN,23));
			lblPlayerMoney[i].setBounds(landWidth+30+j, 640, 250, 100);
			add(lblPlayerMoney[i]);
		}
		add(infoPanel);

		setPreferredSize(new Dimension(landWidth+infoWidth, landHeight));
		setBackground(Color.white);
		setLayout(null);

		// �����ǿ� ���� �� �����гλ���, ������ ��ġ�� ũ�⸦ �Ѱ��ش�
		landPanel[0] = new LandPanel(0,0,landHeight-eventLen,eventLen,eventLen);

		for(int y=landHeight-eventLen-landLen, i=1; y>0 && i<8; y-=landLen, i++) {
			landPanel[i] = new LandPanel(i,0,y,eventLen,landLen);
		}

		landPanel[8] = new LandPanel(8,0,0,eventLen,eventLen);

		for(int x=eventLen, i=9; x<landWidth-eventLen && i<16; x+=landLen, i++) {
			landPanel[i] = new LandPanel(i,x,0,landLen,eventLen);
		}

		landPanel[16] = new LandPanel(16,landWidth-eventLen,0,eventLen,eventLen);

		for(int y=eventLen, i=17; y<landHeight-landLen && i<24; y+=landLen, i++) {
			landPanel[i] = new LandPanel(i,landWidth-eventLen,y,eventLen,landLen);
		}

		landPanel[24] = new LandPanel(24,landWidth-eventLen,landHeight-eventLen,eventLen,eventLen);

		for(int x=landWidth-eventLen-landLen, i=25; x>0 && i<32; x-=landLen, i++) {
			landPanel[i] = new LandPanel(i,x,landHeight-eventLen,landLen,eventLen);
		}

		for(int i=0; i<32; i++) {
			JLabel lbl = new JLabel(LandConstants.NAME[i]);
			landPanel[i].add(lbl);
		}

		// MonopolyPanel�� �����г��� �ø���
		for(int i=0; i<32; i++) add(landPanel[i]);

	

		dice = new Dice(); //�ֻ�����ü����
		playTurn = order; // ī��̱⿡�� �¸��� �÷��̾���� ����
		
		player = new Player[playMax]; //player 0, player 1
		for(int i=0 ; i<playMax ; i++ ) { //�÷��̾ü�� �����ϰ� ���Ӹ� �̹����� �ִ´�
			player[i] = new Player(i);
			player[i].setPlayerImage( playerIconName[i] ); //���Ӹ�
		}

		//�ֻ���1,2 ���� ǥ���� ���̺��� ����� ��ġ����, �гο� �߰�
		this.lblDie1=new JLabel("die1");
		lblDie1.setBounds(400, 400, 30, 30);
		add(lblDie1);
		lblDie2=new JLabel("die2");
		lblDie2.setBounds(400, 420, 30, 30);
		add(lblDie2);

		this.lblTestPlayerPos = new JLabel("�޷�"); //���̺� ����
		lblTestPlayerPos.setBounds(400, 440, 30, 30);//��ġ����
		add(lblTestPlayerPos); //���̺� �߰�

		int play = playTurn+1; // �����÷��̾ ǥ���ϴµ� ���. �÷��̾�迭 0�� �÷��̾�1�� ����
		lblTestPlayerNum = new JLabel("Player : "+play);
		lblTestPlayerNum.setBounds(400, 460, 70, 30);
		add(lblTestPlayerNum);

		smallP = new SmallPanel(this); // ����â�� �ö� SmallPanel��ü ����

	} // MonopolyPanel
	///////////////////////////////////////////////////////////////////////////////////////////////////////


	// �ٸ�Ŭ�������� MonopolyPanel�� ������ �����Ë� ���� get
	public LandPanel[] getLandPanel() { return landPanel; }
	public Player[] getPlayer() { return player; }
	public int getPlayTurn() { return playTurn; }



	/*
	 * �÷��̾��� �ܰ� üũ�ϴ� �Լ�, ���� 0�Ʒ��� ��������� �޼����� ����� ���α׷��� �����Ų��
	 */
	public void chkBalance() {
		for( int i=0 ; i<playMax ; i++ )
			if( player[i].getPlayerMoney() < 0 ) { // ���̾������ ��ư�� ����
				btnRoll.setEnabled(false);
				btnReset.setEnabled(false);
				JOptionPane.showMessageDialog(null, "player : "+(i+1)+ " �� �Ļ��߽��ϴ�");
				System.exit(0);
			}
	}
	
	/*
	 * Roll��ư�� Reset��ư�� ���������� �����Բ��ϴ� �Լ��� setEnable���¸� �ݴ�ǰ� �����
	 */
	public void toggleButton() {
		if(btnReset.isEnabled()==false) {
			btnRoll.setEnabled(false);
			btnReset.setEnabled(true);
		}else {
			btnReset.setEnabled(false);
			btnRoll.setEnabled(true);
		}
	}



	/* 
	 * ���ӳ��� ������ ��ư��� ����Ǿ� �����Ǵ� ��ɵ��� ȣ���ϴ� �׼Ǹ�����
	 * �� ��ư�� ��������� �������� ��ư����Լ��� ȣ���Ͽ� Roll��ư�� Reset��ư�� ���������� �����Բ� ����
	 */
	private class GameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object obj = event.getSource();

			if(obj == btnRoll)// ���ӳ��� ������ Roll��ư�� �����Ѵ�
			{

				/*
				 *  �÷��̾ ������ź������ frozenTurn�� �������� ó���ϴ� ��Ʈ, �ֻ����� ������ �δ��� �������� Ż���Ű��
				 *  �׷��� ������� frozenTurn�� 1�� ���ҽ��Ѽ� 0�� �ɰ�� �ڿ��� �������Ѵ�
				 */
				if(player[playTurn].getFrozenTurn()!=0) {
					dice.rollDice();
					if( dice.getDice1() == dice.getDice2() ) { //Ż����
						JOptionPane.showMessageDialog(null,"!!!!!");
						player[playTurn].setFrozenTurn(0);
						gamePlayAction();
					} else {
						player[playTurn].setFrozenTurn( player[playTurn].getFrozenTurn()-1  );
						JOptionPane.showMessageDialog(null,(player[playTurn].getFrozenTurn()+1)+"�����Ҵ٤Ф�");
					}
				} else { // �÷��̾��� frozenTurn�� 0 �϶� ��������Ǵ� ��Ʈ
					dice.rollDice();
					gamePlayAction();
				}

				//playTurn++; //������������
				toggleButton();
			} 
			else if(obj==btnReset) { // ���ӳ��� Ǫ���� Reset��ư�� ����, ���� �������� �÷��̾��� ���� �ѱ��
				playTurn++;
				if(playTurn==playMax) playTurn=0; // ���ε����� �ִ��÷��̾�� ������ �÷��̾�1�ε��ƿ�
				// �����ѱ��� ��ư�� ����ϰ� ȭ��� ��������
				toggleButton();
				updateInfo();
				repaint();
			}

		}// actionPerformed

	} // gameListener
	
	//  roll��ư�� reset��ư�� ���콺�� �ö����� ���Ǵ� ���콺������
	private class MenuMouseListener implements MouseListener
	{
		public void mouseClicked(MouseEvent event) {}
		public void mousePressed(MouseEvent event) {}
		public void mouseReleased(MouseEvent event) {}

		// mouseEntered�� mouseExited �ΰ�쿡�� ���콺�� ��ư���ö����� ��ư�̹�������
		public void mouseEntered(MouseEvent event) {
			if(btnRoll.isEnabled() == true ) {
				ImageIcon RollIcon = new ImageIcon("diebtn_2.png");
				btnRoll.setIcon(RollIcon);
			} else {
				ImageIcon ResetIcon2 = new ImageIcon("resetbtn2.png");
				btnReset.setIcon(ResetIcon2);
			}
		} // mouseEntered()
		public void mouseExited(MouseEvent event) {
			ImageIcon RollIcon = new ImageIcon("diebtn_1.png");
			btnRoll.setIcon(RollIcon);
			ImageIcon ResetIcon2 = new ImageIcon("resetbtn.png");
			btnReset.setIcon(ResetIcon2);
		} // mouseExited()
	} // MenuMouseListener class


	/*
	 * �� �÷��̾ �����Ǵ� ĳ���͸� ��Ÿ�������� ���̺� ����, �ʱ�ȭ
	 */
	private void CharInit() 
	{
		//player1�̸� 
		lblNowPlayer1 = new JLabel("Player1:");
		lblNowPlayer1.setFont(new Font("Cooper Std Black",Font.PLAIN,20));
		lblNowPlayer1.setBounds(landWidth+15,5,200,100);
		add(lblNowPlayer1);

		///Player2 �̸� 
		lblNowPlayer2 = new JLabel("Player2: ");
		lblNowPlayer2.setFont(new Font("Cooper Std Black",Font.PLAIN,20));
		lblNowPlayer2.setBounds(landWidth+215,5,200,100);
		add(lblNowPlayer2);
	}
	
	/*
	 * ������ �����гο� ���õ� �÷��̾� �̹����� �ø��µ� ����
	 * random���� �������� ���� �����Ǹ� �ش���ڿ� �����ϴ� ĳ���Ͱ� �������
	 */
	private void PlayerCharInit()
	{
		//player1 �̹��� ����
		image=(int)(Math.random()*6)+1;
		if(image==1){
			 // �÷��̾� �������� ����� ���̺� �ְ� ��ġ����, ����, �гο��߰�, �÷��̾� �������� �ش��ɸ��� �̸����� ����
			ImageIcon playerImage=new ImageIcon("muji.png");
			lblPlayer1Image=new JLabel("",playerImage,SwingConstants.CENTER);
			lblPlayer1Image.setBounds(landWidth+15,410,180,240);
			lblPlayer1Image.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer1Image.setVerticalAlignment(SwingConstants.CENTER);
			lblNowPlayer1.setText("Player1: MUJI");
			add(lblPlayer1Image);
			playerIconName[0]="muji2.png"; //���Ӹ��� ���� �̹���

		}
		else if(image==2){
			 // �÷��̾� �������� ����� ���̺� �ְ� ��ġ����, ����, �гο��߰�, �÷��̾� �������� �ش��ɸ��� �̸����� ����
			ImageIcon playerImage=new ImageIcon("jaz.png");
			lblPlayer1Image=new JLabel("",playerImage,SwingConstants.CENTER);
			lblPlayer1Image.setBounds(landWidth+15,410,180,240);
			lblPlayer1Image.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer1Image.setVerticalAlignment(SwingConstants.CENTER);
			lblNowPlayer1.setText("Player1: JAZ");
			add(lblPlayer1Image);
			playerIconName[0]="jaz2.png";//���Ӹ��� ���� �̹���
		}
		else if(image==3){
			 // �÷��̾� �������� ����� ���̺� �ְ� ��ġ����, ����, �гο��߰�, �÷��̾� �������� �ش��ɸ��� �̸����� ����
			ImageIcon playerImage=new ImageIcon("neo.png");
			lblPlayer1Image=new JLabel("",playerImage,SwingConstants.CENTER);
			lblPlayer1Image.setBounds(landWidth+15,410,180,240);
			lblPlayer1Image.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer1Image.setVerticalAlignment(SwingConstants.CENTER);
			lblNowPlayer1.setText("Player1: NEO");
			add(lblPlayer1Image);
			playerIconName[0]="neo2.png";//���Ӹ��� ���� �̹���
		}
		else if(image==4){
			 // �÷��̾� �������� ����� ���̺� �ְ� ��ġ����, ����, �гο��߰�, �÷��̾� �������� �ش��ɸ��� �̸����� ����
			ImageIcon playerImage=new ImageIcon("apeach.png");
			lblPlayer1Image=new JLabel("",playerImage,SwingConstants.CENTER);
			lblPlayer1Image.setBounds(landWidth+15,410,180,240);
			lblPlayer1Image.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer1Image.setVerticalAlignment(SwingConstants.CENTER);
			lblNowPlayer1.setText("Player1: APEACH");
			add(lblPlayer1Image);
			playerIconName[0]="apeach2.png";
		}
		else if(image==5){
			 // �÷��̾� �������� ����� ���̺� �ְ� ��ġ����, ����, �гο��߰�, �÷��̾� �������� �ش��ɸ��� �̸����� ����
			ImageIcon playerImage=new ImageIcon("prodo.png");
			lblPlayer1Image=new JLabel("",playerImage,SwingConstants.CENTER);
			lblPlayer1Image.setBounds(landWidth+15,410,170,240);
			lblPlayer1Image.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer1Image.setVerticalAlignment(SwingConstants.CENTER);
			lblNowPlayer1.setText("Player1: PRODO");
			add(lblPlayer1Image);
			playerIconName[0]="prodo2.png";
		}
		else{
			 // �÷��̾� �������� ����� ���̺� �ְ� ��ġ����, ����, �гο��߰�, �÷��̾� �������� �ش��ɸ��� �̸����� ����
			ImageIcon playerImage=new ImageIcon("tube.png");
			lblPlayer1Image=new JLabel("",playerImage,SwingConstants.CENTER);
			lblPlayer1Image.setBounds(landWidth+15,410,180,240);
			lblPlayer1Image.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer1Image.setVerticalAlignment(SwingConstants.CENTER);
			lblNowPlayer1.setText("Player1: TUBE");
			add(lblPlayer1Image);
			playerIconName[0]="tube2.png";//���Ӹ��� ���� �̹���
		}

		//player2 �̹����� ���� 
		image=(int)(Math.random()*6)+1;
		if(image==1){
			ImageIcon playerImage=new ImageIcon("muji.png");
			lblPlayer2Image=new JLabel("",playerImage,SwingConstants.CENTER);
			lblPlayer2Image.setBounds(landWidth+215,400,180,240);
			lblPlayer2Image.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer2Image.setVerticalAlignment(SwingConstants.CENTER);
			lblNowPlayer2.setText("Player2: MUJI");
			add(lblPlayer2Image);
			playerIconName[1]="muji2.png";//���Ӹ��� ���� �̹���
		}
		else if(image==2){
			ImageIcon playerImage=new ImageIcon("jaz.png");
			lblPlayer2Image=new JLabel("",playerImage,SwingConstants.CENTER);
			lblPlayer2Image.setBounds(landWidth+215,410,170,240);
			lblPlayer2Image.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer2Image.setVerticalAlignment(SwingConstants.CENTER);
			lblNowPlayer2.setText("Player2: JAZ");
			add(lblPlayer2Image);
			playerIconName[1]="jaz2.png";//���Ӹ��� ���� �̹���
		}
		else if(image==3){
			ImageIcon playerImage=new ImageIcon("neo.png");
			lblPlayer2Image=new JLabel("",playerImage,SwingConstants.CENTER);
			lblPlayer2Image.setBounds(landWidth+215,410,180,240);
			lblPlayer2Image.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer2Image.setVerticalAlignment(SwingConstants.CENTER);
			lblNowPlayer2.setText("Player2: NEO");
			add(lblPlayer2Image);
			playerIconName[1]="neo2.png";//���Ӹ��� ���� �̹���
		}
		else if(image==4){
			ImageIcon playerImage=new ImageIcon("apeach.png");
			lblPlayer2Image=new JLabel("",playerImage,SwingConstants.CENTER);
			lblPlayer2Image.setBounds(landWidth+215,410,180,240);
			lblPlayer2Image.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer2Image.setVerticalAlignment(SwingConstants.CENTER);
			lblNowPlayer2.setText("Player2: APEACH");
			add(lblPlayer2Image);
			playerIconName[1]="apeach2.png";//���Ӹ��� ���� �̹���
		}
		else if(image==5){
			ImageIcon playerImage=new ImageIcon("prodo.png");
			lblPlayer2Image=new JLabel("",playerImage,SwingConstants.CENTER);
			lblPlayer2Image.setBounds(landWidth+215,410,180,240);
			lblPlayer2Image.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer2Image.setVerticalAlignment(SwingConstants.CENTER);
			lblNowPlayer2.setText("Player2: PRODO");
			add(lblPlayer2Image);
			playerIconName[1]="prodo2.png";//���Ӹ��� ���� �̹���
		}
		else{
			ImageIcon playerImage=new ImageIcon("tube.png");
			lblPlayer2Image=new JLabel("",playerImage,SwingConstants.CENTER);
			lblPlayer2Image.setBounds(landWidth+215,410,180,240);
			lblPlayer2Image.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer2Image.setVerticalAlignment(SwingConstants.CENTER);
			lblNowPlayer2.setText("Player2: TUBE");
			add(lblPlayer2Image);
			playerIconName[1]="tube2.png";//���Ӹ��� ���� �̹���
		}
	} // PlayerCharInit()

	/*
	 * ȭ��󿡼� �������� �������� ����, �Ϻ����� �����÷��̾�� �÷��̾�� �����ִ� ���̺���ġ�� �����ϰ� ����üũ���Ѵ�
	 */
	public void updateInfo() {

		lblTestPlayerNum.setText("Player : "+(playTurn+1) ); // �����÷��̾ �������� ������
		for(int i=0 ; i<playMax ;i++) {
			this.lblPlayerMoney[i].setText("Money : "+ player[i].getPlayerMoney()  ); //�÷��̾ ���簡������ ������
		}

		//������ġ���� ���ο���ġ�� ����
		player[playTurn].getPlayerPiece().setVisible(false);
		player[playTurn].getPlayerPiece().setVisible(true);
		
		chkBalance(); // ����üũ
	}
	
	public void aEventMoney(int num){
		player[playTurn].setPlayerMoney(player[playTurn].getPlayerMoney()+200);
	} //�̺�Ʈ ȿ��
	public void mEventMoney(int num){
		player[playTurn].setPlayerMoney(player[playTurn].getPlayerMoney()-200);
	} //�̺�Ʈ ȿ��

	/*
	 * ����Ḧ �����ϴ� ��Ȳ���� ȣ��
	 */
	public void collectToll(int n) {
		String landOwner = landPanel[ n ].getOwner(); //�÷��̾ ���� ������ ����

		//���� �÷��̾ ���������� ����ġ�� 20%�� ���Ѵ�
		player[playTurn].setPlayerMoney( // ����� =  (����*�ǹ�����)�� 20%
				(int)(player[playTurn].getPlayerMoney() 
						-  
						(LandConstants.LAND_PRICE[ n  ] 
								* landPanel[player[playTurn].getPlayerPosition()].getBuildingLvl())
						*0.2  )	);

		for(int i=0 ; i<playMax ; i++) { //�÷��̾� �迭�� ���Ƽ� ���������� ã�´�
			if(player[i].getPlayerNum() == landOwner) {
				player[i].setPlayerMoney( // �������ο��� 20%�� ����Ḧ �Ѱ��ش�
						(int)( player[i].getPlayerMoney()
								+
								(LandConstants.LAND_PRICE[ n ] 
										* landPanel[player[playTurn].getPlayerPosition()].getBuildingLvl() )
								*0.2  )  );

			} //if
		} //for


	} //collectToll

	/*
	 * ���Ű��� ���� ������� ������ ����â
	 */
	public void showSmallFrame(int n) {

		if(n!=0 && n!=4 && n!=8 && n!=12 && n!=16 && n!=20 && n!=24 && n!=28 ) //�̺�Ʈ�г��� �ƴҶ��� �ش�
		{
			// ������ ���Ű��� â�� ��������� �������� �����.
			JFrame frame = new JFrame("��ȣȫ ���Ϳ�");
			// �����̸� �ľ��ؼ� dimension�� ����, ������������ ��ġ�� �����Ѵ�
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x,y);
			frame.setResizable(false);

			smallP.updateSign(); // ���Ű��� �����̸� ���ݷ��̺� ������Ʈ
			frame.getContentPane().add(smallP);
			frame.pack();
			frame.setVisible(true);
			smallP.setFrame(frame); // SmallPanel�ʿ��� ������������� ȣ��
			
		}
		// ���� �̺�Ʈ �г��� ��������� ó��
		else if(n==4) {
			JOptionPane.showMessageDialog(null, LandConstants.NAME[n]+"�� �����մϴ�.");
			LuckyGamePanel.setVisible(true);
			LuckyGamePanel.start();
			if(LuckyGamePanel.resultNumber==1){
				player[playTurn].setPlayerMoney(player[playTurn].getPlayerMoney()+200);
			}
			
		} // �̴ϰ���1
		else if(n==20){
			JOptionPane.showMessageDialog(null, LandConstants.NAME[n]+"�� �����մϴ�.");
			OddEvenGamePanel.setVisible(true);
			if(OddEvenGamePanel.getResult() ==1){
				player[playTurn].setPlayerMoney(player[playTurn].getPlayerMoney()+200);
			}
		} //�̴ϰ���2
		else if(n==8){  //8,16,24 �̺�Ʈ��
			Event.setVisible(true);
			Event.hwResult();
			mEventMoney(num);
			} 
		else if(n==16) {
				Event.setVisible(true);
				Event.festResult();
				aEventMoney(num);
			} 	
		else if(n==24){
				Event.setVisible(true);
				Event.freeResult();
				aEventMoney(num);
			}
			// �̺�Ʈ �г�
		else if(n==12 ) { //ī���̺�Ʈ
			ad.setVisible(true);
			ad.start();
			String result = "����ī��!";
			JOptionPane.showMessageDialog(null,result);
			aEventMoney(num);
			}
		else if(n==28 ) {// ī���̺�Ʈ
			p.setVisible(true);
			p.start();
			String result = "�г�Ƽī��!";
			JOptionPane.showMessageDialog(null,result);
			mEventMoney(num);
		} // ����ī��, ����ī��




	}



	/*
	 * GameListener������ ȣ��Ǵ� �Լ�. ���ӳ����� �̷������ ���̵�, ���Ű��� â���, �̺�Ʈ�г�ó���� ���ӳ��� �������� ����� ����Ѵ�
	 */
	public void gamePlayAction() {
		// �÷��̾� ��ġ�� �����ϰ�, ����ġ�� ������Ʈ�ϴ� �κ�
		player[playTurn].updatePlayerPosition( dice.getBigDice() ); // ������ġ+�ֻ��������� ��ġ����

		if(playTurn == 0) {
			player[playTurn].getPlayerPiece().setBounds(10,15,53,72); //��ǥ��
		} else {
			player[playTurn].getPlayerPiece().setBounds(50,15,53,72); //��ǥ��
		}

		//��ǥ��
		int testNum = player[playTurn].getPlayerPosition(); //����ġ���庯��
		landPanel[testNum].lblLand[testNum].add(player[playTurn].getPlayerPiece() ); // ���綥���� �÷��̸Ӹ��� �ø���

		//�����ֻ����� ��ġ��ǥ���ϴ� ���̺�
		lblDie1.setText(""+dice.getDice1() );
		lblDie2.setText(""+dice.getDice2() );
		lblTestPlayerPos.setText(""+player[playTurn].getPlayerPosition());
		repaint();

		// ���Ű��� �г��� ������� ó���Ĵ� ��Ʈ. �������� ������� ���� ����â�� ����� ó��
		if( landPanel[player[playTurn].getPlayerPosition()].getOwner()=="" ) {
	
			showSmallFrame(player[playTurn].getPlayerPosition());

		} else if(landPanel[player[playTurn].getPlayerPosition()].getOwner() == player[playTurn].getPlayerNum() ) {
			//�߰��Ұ� �ڱⶥ�� ������ �ǹ��ø���
			//buildDialog( player[playTurn].getPlayerPosition() );
			//repaint();
		} else { // �ٸ��÷��̾��� ���� �������. ����� �޼����� �ش��������� 20%�� ���濡�� �����Ѵ�
			collectToll(player[playTurn].getPlayerPosition());
			JOptionPane.showMessageDialog(null, LandConstants.NAME[player[playTurn].getPlayerPosition()]+"���� ����Ḧ �´�.\n"+
					(int)( LandConstants.LAND_PRICE[ player[playTurn].getPlayerPosition()  ] 
							* landPanel[player[playTurn].getPlayerPosition()].getBuildingLvl() 
							*0.2 ) +"�� ");
			repaint();
		}

		// �÷��̾ ���԰����������ƴ� �̺�Ʈ�г��� ������� ó���ϴ� ��Ʈ, �ش� �̺�Ʈ���´� �̹����� ���
		if(player[playTurn].getPlayerPosition() == 8) {
			JOptionPane.showMessageDialog(null,"������ ����ּ���̤�");
			player[playTurn].setFrozenTurn(2); // ������ź�϶� �̵�����
			Event.setVisible(false);
			Event.Reset();
		} //�̺�Ʈ�� ȿ��
		
		if(player[playTurn].getPlayerPosition() == 16) {
			JOptionPane.showMessageDialog(null,"������!");
			Event.setVisible(false);
			Event.Reset();
		} //�̺�Ʈ�� ȿ��
		if(player[playTurn].getPlayerPosition() == 24) {
			JOptionPane.showMessageDialog(null,"�����ð�!");
			Event.setVisible(false);
			Event.Reset();
		} //�̺�Ʈ�� ȿ��
		updateInfo();
		repaint();

	} //gamePlayAction




}
