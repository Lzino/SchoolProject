package monopoly_1;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MonopolyPanel extends JPanel{ // 게임판과 인포메이션 창이 올라감
	int landWidth, landHeight; // 게임판 크기
	int infoWidth, infoHeight; // 인포메이션 창 크기
	int eventLen, landLen; // 이벤트 칸과 그냥 칸 크기
	private LandPanel[] landPanel; // 땅 객체 배열//private LandPanel[] landPanel; // 땅 객체 배열


	private JPanel infoPanel; // 오른쪽에 표시되는 노란 정보패널
	private JLabel lbloptionImage1; // 카카오 이미지저장용 레이블
	private JLabel lblPlayer1Image, lblPlayer2Image; //각플레이어캐릭터 이미지저장레이블
	private int image; // 무작위로 캐릭터를 선택할때 생성된 난수를 저장하는 변수
	private JLabel lblName; //monopoly글자를 출력하기위한 레이블

	private JLabel[] lblPlayerMoney; //플레이어들의 돈을 보여주는 레이블
	private JLabel lblNowPlayer1, lblNowPlayer2;  // 각플레이어의 이름을 저장하는 레이블
	private GameListener gameL; // 각종 게임과 관련된 기능을 수행할 액션리스너
	private MenuMouseListener	menuMouseL; //버튼에 효과를주는 마우스리스너
	private JButton btnRoll, btnReset; // 굴리기버튼 턴넘기기버튼
	private Dice dice; // 주사위 객체
	private int playMax; // 최대플레이어수

	public static int playTurn; //현재 플레이어턴을 기록


	private Player[] player; //플레이어들을 구분하는 클래스객체
	private String[] playerIconName; //플레이어의 말을 선택하는데 사용되는 이름을 저장하는데 사용될 문자열변수

	private JLabel lblTestPlayerPos,lblTestPlayerNum; // 현재 플레이어위치와, 턴변수를 출력해줄 변수
	private JLabel lblDie1,lblDie2; // 주사위눈 1,2 표시레이블

	private SmallPanel smallP; // 게임창이아닌 작은 구매창을 출력하는데 사용
	
	private LuckyTestPanel LuckyGamePanel; //복불복게임 
	private OddEvenPanel OddEvenGamePanel; //홀짝게임
	private EventPanel Event; //이벤트존
	private InitialPanel Ini; //시작 (필요없으면 없앨거임)
	private AdCard ad; //찬스카드
	private PCard p; //패널티카드
	private int num;

	public MonopolyPanel(int order) {
		playMax=2;//2인용
		landWidth = landHeight = infoHeight = 960; // 게임판 크기
		eventLen = 130; landLen = 100;			   // event땅 크기 // 땅 크기
		infoWidth = 400;	// info 창 가로 크기

		landPanel = new LandPanel[32]; 

		LuckyGamePanel = new LuckyTestPanel();
		LuckyGamePanel.setBounds(250, 250, 430, 430);
		LuckyGamePanel.setVisible(false);
		add(LuckyGamePanel); // 미니게임1 패널
		
		OddEvenGamePanel = new OddEvenPanel();
		OddEvenGamePanel.setBounds(250, 250, 430, 430);
		OddEvenGamePanel.setVisible(false);
		add(OddEvenGamePanel); // 미니게임2 패널
		
		Event = new EventPanel();
		Event.setBounds(250,250,480,480);
		Event.setVisible(false);
		add(Event);//이벤트 패널
		
		ad = new AdCard();
		ad.setBounds(250,250,480,480);
		ad.setVisible(false);
		add(ad);//좋은 카드 패널
		
		p = new PCard();
		p.setBounds(250,250,480,480);
		p.setVisible(false);
		add(p);//나쁜 카드 패널

		infoPanel = new JPanel(); //노랑판
		infoPanel.setPreferredSize(new Dimension(infoWidth, infoHeight));
		infoPanel.setBounds(landWidth, 0, infoWidth, infoHeight);//960,0,400,960
		infoPanel.setBackground(new Color(255,224,49));
		infoPanel.setLayout(null);
		// 액션리스너 ,마우스리스너생성
		gameL = new GameListener();
		menuMouseL = new MenuMouseListener();
		playerIconName = new String[8]; //플레이어말아이콘을 찾을때 사용되는 문자열
		CharInit();
		PlayerCharInit();

		/*
		 * 주사위굴리기버튼 생성,초기화부분, 게임진행과 관련된 액션리스너 마우스리스너를 추가한다
		 */
		ImageIcon RollIcon = new ImageIcon("diebtn_1.png");	 
		btnRoll = new JButton(RollIcon); //주사위 이미지삽입  
		btnRoll.setBounds(landWidth+130, 85, 130, 130);
		btnRoll.setEnabled(true);
		btnRoll.addActionListener(gameL);
		btnRoll.addMouseListener(menuMouseL);
		add(btnRoll);

		
		//버튼 디자인 정리
		btnRoll.setBorderPainted(false); // 버튼 경계선 제거
		//btnRoll.setFocusPainted(false); //포커스(선택했던 버튼 표시) 제거
		btnRoll.setContentAreaFilled(false);//버튼영역 배경 제거

		/*
		 * 턴넘기기버튼 생성,초기화부분, 게임진행과 관련된 액션리스너 마우스리스너를 추가한다
		 */
		ImageIcon ResetIcon2 = new ImageIcon("resetbtn.png");  
		btnReset = new JButton(ResetIcon2); 
		btnReset.setBounds(landWidth+130, 225, 130, 130);
		btnReset.addActionListener(gameL);
		btnReset.addMouseListener(menuMouseL);
		add(btnReset);
		btnReset.setBorderPainted(false); // 버튼 경계선 제거
		//btnReset.setFocusPainted(false); //포커스(선택했던 버튼 표시) 제거
		btnReset.setContentAreaFilled(false);//버튼영역 배경 제거
		btnReset.setEnabled(false);

		
		//optionPanel에 삽입되는 캐릭터이미지레이블을위한 이미지아이콘생성, 레이블객체생성, 위치생성, 정렬, 패널추가
		ImageIcon icon1= new ImageIcon("kakao.png");
		lbloptionImage1=new JLabel("",icon1,SwingConstants.CENTER);
		lbloptionImage1.setBounds(landWidth+100,800,210,76);
		lbloptionImage1.setHorizontalAlignment(SwingConstants.CENTER);
		lbloptionImage1.setVerticalAlignment(SwingConstants.CENTER);
		add(lbloptionImage1);
		
		//monopoly글자이미지 출력레이블을 위한 이미지아이콘생성, 레이블객체생성, 위치생성, 정렬, 패널추가
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

		// 게임판에 놓일 각 토지패널생성, 생성시 위치와 크기를 넘겨준다
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

		// MonopolyPanel에 토지패널을 올린다
		for(int i=0; i<32; i++) add(landPanel[i]);

	

		dice = new Dice(); //주사위객체생성
		playTurn = order; // 카드뽑기에서 승리한 플레이어먼저 시작
		
		player = new Player[playMax]; //player 0, player 1
		for(int i=0 ; i<playMax ; i++ ) { //플레이어객체를 생성하고 게임말 이미지를 넣는다
			player[i] = new Player(i);
			player[i].setPlayerImage( playerIconName[i] ); //게임말
		}

		//주사위1,2 눈을 표시할 레이블을 만들고 위치지정, 패널에 추가
		this.lblDie1=new JLabel("die1");
		lblDie1.setBounds(400, 400, 30, 30);
		add(lblDie1);
		lblDie2=new JLabel("die2");
		lblDie2.setBounds(400, 420, 30, 30);
		add(lblDie2);

		this.lblTestPlayerPos = new JLabel("메롱"); //레이블 생성
		lblTestPlayerPos.setBounds(400, 440, 30, 30);//위치설정
		add(lblTestPlayerPos); //레이블 추가

		int play = playTurn+1; // 현재플레이어를 표시하는데 사용. 플레이어배열 0은 플레이어1에 대응
		lblTestPlayerNum = new JLabel("Player : "+play);
		lblTestPlayerNum.setBounds(400, 460, 70, 30);
		add(lblTestPlayerNum);

		smallP = new SmallPanel(this); // 구매창에 올라갈 SmallPanel객체 생성

	} // MonopolyPanel
	///////////////////////////////////////////////////////////////////////////////////////////////////////


	// 다른클레스에서 MonopolyPanel의 변수를 가져올떄 사용될 get
	public LandPanel[] getLandPanel() { return landPanel; }
	public Player[] getPlayer() { return player; }
	public int getPlayTurn() { return playTurn; }



	/*
	 * 플레이어의 잔고를 체크하는 함수, 돈이 0아래로 내려갈경우 메세지를 출력후 프로그램을 종료시킨다
	 */
	public void chkBalance() {
		for( int i=0 ; i<playMax ; i++ )
			if( player[i].getPlayerMoney() < 0 ) { // 돈이없을경우 버튼이 잠긴다
				btnRoll.setEnabled(false);
				btnReset.setEnabled(false);
				JOptionPane.showMessageDialog(null, "player : "+(i+1)+ " 가 파산했습니다");
				System.exit(0);
			}
	}
	
	/*
	 * Roll버튼과 Reset버튼을 순차적으로 누르게끔하는 함수로 setEnable상태를 반대되게 만든다
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
	 * 게임내의 구현된 버튼들과 연계되어 대응되는 기능들을 호출하는 액션리스너
	 * 각 버튼이 눌렸을경우 마지막에 버튼토글함수를 호출하여 Roll버튼과 Reset버튼이 순차적으로 누르게끔 구성
	 */
	private class GameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object obj = event.getSource();

			if(obj == btnRoll)// 개임내의 붉은색 Roll버튼에 대응한다
			{

				/*
				 *  플레이어가 과제폭탄때문에 frozenTurn이 생겼을때 처리하는 루트, 주사위를 굴려서 두눈이 같을때는 탈출시키며
				 *  그렇지 않을경우 frozenTurn을 1씩 감소시켜서 0이 될경우 자연히 나가게한다
				 */
				if(player[playTurn].getFrozenTurn()!=0) {
					dice.rollDice();
					if( dice.getDice1() == dice.getDice2() ) { //탈ㅊ출
						JOptionPane.showMessageDialog(null,"!!!!!");
						player[playTurn].setFrozenTurn(0);
						gamePlayAction();
					} else {
						player[playTurn].setFrozenTurn( player[playTurn].getFrozenTurn()-1  );
						JOptionPane.showMessageDialog(null,(player[playTurn].getFrozenTurn()+1)+"번남았다ㅠㅠ");
					}
				} else { // 플레이어의 frozenTurn이 0 일때 정상진행되는 루트
					dice.rollDice();
					gamePlayAction();
				}

				//playTurn++; //다음다음다음
				toggleButton();
			} 
			else if(obj==btnReset) { // 게임내의 푸른색 Reset버튼과 대응, 현재 진행중인 플레이어의 턴을 넘긴다
				playTurn++;
				if(playTurn==playMax) playTurn=0; // 턴인덱스가 최대플레이어와 같으면 플레이어1로돌아옴
				// 턴을넘긴후 버튼을 토글하고 화면상 정보갱신
				toggleButton();
				updateInfo();
				repaint();
			}

		}// actionPerformed

	} // gameListener
	
	//  roll버튼과 reset버튼에 마우스가 올라갔을떄 사용되는 마우스리스너
	private class MenuMouseListener implements MouseListener
	{
		public void mouseClicked(MouseEvent event) {}
		public void mousePressed(MouseEvent event) {}
		public void mouseReleased(MouseEvent event) {}

		// mouseEntered와 mouseExited 두경우에서 마우스가 버튼에올라갔을때 버튼이미지변경
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
	 * 각 플레이어에 대응되는 캐릭터를 나타내기위한 레이블 생성, 초기화
	 */
	private void CharInit() 
	{
		//player1이름 
		lblNowPlayer1 = new JLabel("Player1:");
		lblNowPlayer1.setFont(new Font("Cooper Std Black",Font.PLAIN,20));
		lblNowPlayer1.setBounds(landWidth+15,5,200,100);
		add(lblNowPlayer1);

		///Player2 이름 
		lblNowPlayer2 = new JLabel("Player2: ");
		lblNowPlayer2.setFont(new Font("Cooper Std Black",Font.PLAIN,20));
		lblNowPlayer2.setBounds(landWidth+215,5,200,100);
		add(lblNowPlayer2);
	}
	
	/*
	 * 오른쪽 정보패널에 선택된 플레이어 이미지를 올리는데 사용됨
	 * random으로 무작위의 수가 결정되면 해당숫자에 대응하는 캐릭터가 골라진다
	 */
	private void PlayerCharInit()
	{
		//player1 이미지 삽입
		image=(int)(Math.random()*6)+1;
		if(image==1){
			 // 플레이어 아이콘을 만들고 레이블에 넣고 위치지정, 정렬, 패널에추가, 플레이어 네임택을 해당케릭터 이름으로 설정
			ImageIcon playerImage=new ImageIcon("muji.png");
			lblPlayer1Image=new JLabel("",playerImage,SwingConstants.CENTER);
			lblPlayer1Image.setBounds(landWidth+15,410,180,240);
			lblPlayer1Image.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer1Image.setVerticalAlignment(SwingConstants.CENTER);
			lblNowPlayer1.setText("Player1: MUJI");
			add(lblPlayer1Image);
			playerIconName[0]="muji2.png"; //게임말에 사용될 이미지

		}
		else if(image==2){
			 // 플레이어 아이콘을 만들고 레이블에 넣고 위치지정, 정렬, 패널에추가, 플레이어 네임택을 해당케릭터 이름으로 설정
			ImageIcon playerImage=new ImageIcon("jaz.png");
			lblPlayer1Image=new JLabel("",playerImage,SwingConstants.CENTER);
			lblPlayer1Image.setBounds(landWidth+15,410,180,240);
			lblPlayer1Image.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer1Image.setVerticalAlignment(SwingConstants.CENTER);
			lblNowPlayer1.setText("Player1: JAZ");
			add(lblPlayer1Image);
			playerIconName[0]="jaz2.png";//게임말에 사용될 이미지
		}
		else if(image==3){
			 // 플레이어 아이콘을 만들고 레이블에 넣고 위치지정, 정렬, 패널에추가, 플레이어 네임택을 해당케릭터 이름으로 설정
			ImageIcon playerImage=new ImageIcon("neo.png");
			lblPlayer1Image=new JLabel("",playerImage,SwingConstants.CENTER);
			lblPlayer1Image.setBounds(landWidth+15,410,180,240);
			lblPlayer1Image.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer1Image.setVerticalAlignment(SwingConstants.CENTER);
			lblNowPlayer1.setText("Player1: NEO");
			add(lblPlayer1Image);
			playerIconName[0]="neo2.png";//게임말에 사용될 이미지
		}
		else if(image==4){
			 // 플레이어 아이콘을 만들고 레이블에 넣고 위치지정, 정렬, 패널에추가, 플레이어 네임택을 해당케릭터 이름으로 설정
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
			 // 플레이어 아이콘을 만들고 레이블에 넣고 위치지정, 정렬, 패널에추가, 플레이어 네임택을 해당케릭터 이름으로 설정
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
			 // 플레이어 아이콘을 만들고 레이블에 넣고 위치지정, 정렬, 패널에추가, 플레이어 네임택을 해당케릭터 이름으로 설정
			ImageIcon playerImage=new ImageIcon("tube.png");
			lblPlayer1Image=new JLabel("",playerImage,SwingConstants.CENTER);
			lblPlayer1Image.setBounds(landWidth+15,410,180,240);
			lblPlayer1Image.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer1Image.setVerticalAlignment(SwingConstants.CENTER);
			lblNowPlayer1.setText("Player1: TUBE");
			add(lblPlayer1Image);
			playerIconName[0]="tube2.png";//게임말에 사용될 이미지
		}

		//player2 이미지ㅣ 삽입 
		image=(int)(Math.random()*6)+1;
		if(image==1){
			ImageIcon playerImage=new ImageIcon("muji.png");
			lblPlayer2Image=new JLabel("",playerImage,SwingConstants.CENTER);
			lblPlayer2Image.setBounds(landWidth+215,400,180,240);
			lblPlayer2Image.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer2Image.setVerticalAlignment(SwingConstants.CENTER);
			lblNowPlayer2.setText("Player2: MUJI");
			add(lblPlayer2Image);
			playerIconName[1]="muji2.png";//게임말에 사용될 이미지
		}
		else if(image==2){
			ImageIcon playerImage=new ImageIcon("jaz.png");
			lblPlayer2Image=new JLabel("",playerImage,SwingConstants.CENTER);
			lblPlayer2Image.setBounds(landWidth+215,410,170,240);
			lblPlayer2Image.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer2Image.setVerticalAlignment(SwingConstants.CENTER);
			lblNowPlayer2.setText("Player2: JAZ");
			add(lblPlayer2Image);
			playerIconName[1]="jaz2.png";//게임말에 사용될 이미지
		}
		else if(image==3){
			ImageIcon playerImage=new ImageIcon("neo.png");
			lblPlayer2Image=new JLabel("",playerImage,SwingConstants.CENTER);
			lblPlayer2Image.setBounds(landWidth+215,410,180,240);
			lblPlayer2Image.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer2Image.setVerticalAlignment(SwingConstants.CENTER);
			lblNowPlayer2.setText("Player2: NEO");
			add(lblPlayer2Image);
			playerIconName[1]="neo2.png";//게임말에 사용될 이미지
		}
		else if(image==4){
			ImageIcon playerImage=new ImageIcon("apeach.png");
			lblPlayer2Image=new JLabel("",playerImage,SwingConstants.CENTER);
			lblPlayer2Image.setBounds(landWidth+215,410,180,240);
			lblPlayer2Image.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer2Image.setVerticalAlignment(SwingConstants.CENTER);
			lblNowPlayer2.setText("Player2: APEACH");
			add(lblPlayer2Image);
			playerIconName[1]="apeach2.png";//게임말에 사용될 이미지
		}
		else if(image==5){
			ImageIcon playerImage=new ImageIcon("prodo.png");
			lblPlayer2Image=new JLabel("",playerImage,SwingConstants.CENTER);
			lblPlayer2Image.setBounds(landWidth+215,410,180,240);
			lblPlayer2Image.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer2Image.setVerticalAlignment(SwingConstants.CENTER);
			lblNowPlayer2.setText("Player2: PRODO");
			add(lblPlayer2Image);
			playerIconName[1]="prodo2.png";//게임말에 사용될 이미지
		}
		else{
			ImageIcon playerImage=new ImageIcon("tube.png");
			lblPlayer2Image=new JLabel("",playerImage,SwingConstants.CENTER);
			lblPlayer2Image.setBounds(landWidth+215,410,180,240);
			lblPlayer2Image.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer2Image.setVerticalAlignment(SwingConstants.CENTER);
			lblNowPlayer2.setText("Player2: TUBE");
			add(lblPlayer2Image);
			playerIconName[1]="tube2.png";//게임말에 사용될 이미지
		}
	} // PlayerCharInit()

	/*
	 * 화면상에서 보여지는 정보들을 갱신, 턴변수로 현재플레이어와 플레이어말을 보여주는 레이블위치를 새로하고 종료체크를한다
	 */
	public void updateInfo() {

		lblTestPlayerNum.setText("Player : "+(playTurn+1) ); // 현재플레이어가 누구인지 보여줌
		for(int i=0 ; i<playMax ;i++) {
			this.lblPlayerMoney[i].setText("Money : "+ player[i].getPlayerMoney()  ); //플레이어가 현재가진돈을 보여줌
		}

		//기존위치에서 새로운위치로 갱신
		player[playTurn].getPlayerPiece().setVisible(false);
		player[playTurn].getPlayerPiece().setVisible(true);
		
		chkBalance(); // 종료체크
	}
	
	public void aEventMoney(int num){
		player[playTurn].setPlayerMoney(player[playTurn].getPlayerMoney()+200);
	} //이벤트 효과
	public void mEventMoney(int num){
		player[playTurn].setPlayerMoney(player[playTurn].getPlayerMoney()-200);
	} //이벤트 효과

	/*
	 * 통행료를 내야하는 상황에서 호출
	 */
	public void collectToll(int n) {
		String landOwner = landPanel[ n ].getOwner(); //플레이어가 밟은 토지의 주인

		//현재 플레이어가 가진돈에서 땅가치의 20%를 제한다
		player[playTurn].setPlayerMoney( // 통행료 =  (땅값*건물레벨)의 20%
				(int)(player[playTurn].getPlayerMoney() 
						-  
						(LandConstants.LAND_PRICE[ n  ] 
								* landPanel[player[playTurn].getPlayerPosition()].getBuildingLvl())
						*0.2  )	);

		for(int i=0 ; i<playMax ; i++) { //플레이어 배열을 돌아서 토지주인을 찾는다
			if(player[i].getPlayerNum() == landOwner) {
				player[i].setPlayerMoney( // 토지주인에게 20%의 통행료를 넘겨준다
						(int)( player[i].getPlayerMoney()
								+
								(LandConstants.LAND_PRICE[ n ] 
										* landPanel[player[playTurn].getPlayerPosition()].getBuildingLvl() )
								*0.2  )  );

			} //if
		} //for


	} //collectToll

	/*
	 * 구매가능 땅을 밟았을때 보여줄 작은창
	 */
	public void showSmallFrame(int n) {

		if(n!=0 && n!=4 && n!=8 && n!=12 && n!=16 && n!=20 && n!=24 && n!=28 ) //이벤트패널이 아닐때만 해당
		{
			// 토지를 구매관련 창을 만들기위한 프레임을 만든다.
			JFrame frame = new JFrame("오호홍 조와용");
			// 폭높이를 파악해서 dimension을 결정, 작은프레임의 위치를 설정한다
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x,y);
			frame.setResizable(false);

			smallP.updateSign(); // 구매관련 토지이름 가격레이블 업데이트
			frame.getContentPane().add(smallP);
			frame.pack();
			frame.setVisible(true);
			smallP.setFrame(frame); // SmallPanel쪽에서 프레임제어를위해 호출
			
		}
		// 각종 이벤트 패널을 밟았을때의 처리
		else if(n==4) {
			JOptionPane.showMessageDialog(null, LandConstants.NAME[n]+"을 시작합니다.");
			LuckyGamePanel.setVisible(true);
			LuckyGamePanel.start();
			if(LuckyGamePanel.resultNumber==1){
				player[playTurn].setPlayerMoney(player[playTurn].getPlayerMoney()+200);
			}
			
		} // 미니게임1
		else if(n==20){
			JOptionPane.showMessageDialog(null, LandConstants.NAME[n]+"을 시작합니다.");
			OddEvenGamePanel.setVisible(true);
			if(OddEvenGamePanel.getResult() ==1){
				player[playTurn].setPlayerMoney(player[playTurn].getPlayerMoney()+200);
			}
		} //미니게임2
		else if(n==8){  //8,16,24 이벤트존
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
			// 이벤트 패널
		else if(n==12 ) { //카드이벤트
			ad.setVisible(true);
			ad.start();
			String result = "찬스카드!";
			JOptionPane.showMessageDialog(null,result);
			aEventMoney(num);
			}
		else if(n==28 ) {// 카드이벤트
			p.setVisible(true);
			p.start();
			String result = "패널티카드!";
			JOptionPane.showMessageDialog(null,result);
			mEventMoney(num);
		} // 좋은카드, 나쁜카드




	}



	/*
	 * GameListener내에서 호출되는 함수. 게임내에서 이루어지는 말이동, 구매관련 창출력, 이벤트패널처리등 게임내의 전반적인 기능을 담당한다
	 */
	public void gamePlayAction() {
		// 플레이어 위치를 갱신하고, 말위치를 업데이트하는 부분
		player[playTurn].updatePlayerPosition( dice.getBigDice() ); // 기존위치+주사위눈으로 위치갱신

		if(playTurn == 0) {
			player[playTurn].getPlayerPiece().setBounds(10,15,53,72); //말표시
		} else {
			player[playTurn].getPlayerPiece().setBounds(50,15,53,72); //말표시
		}

		//말표시
		int testNum = player[playTurn].getPlayerPosition(); //현위치저장변수
		landPanel[testNum].lblLand[testNum].add(player[playTurn].getPlayerPiece() ); // 현재땅위에 플레이머말을 올린다

		//현재주사위와 위치를표시하는 레이블
		lblDie1.setText(""+dice.getDice1() );
		lblDie2.setText(""+dice.getDice2() );
		lblTestPlayerPos.setText(""+player[playTurn].getPlayerPosition());
		repaint();

		// 구매가능 패널을 밟았을때 처리파는 파트. 땅주인이 없을경우 작은 구매창을 출력해 처리
		if( landPanel[player[playTurn].getPlayerPosition()].getOwner()=="" ) {
	
			showSmallFrame(player[playTurn].getPlayerPosition());

		} else if(landPanel[player[playTurn].getPlayerPosition()].getOwner() == player[playTurn].getPlayerNum() ) {
			//추가할것 자기땅을 밟으면 건물올리기
			//buildDialog( player[playTurn].getPlayerPosition() );
			//repaint();
		} else { // 다른플레이어의 땅을 밟았을때. 통행료 메세지와 해당토지가의 20%를 상대방에게 지불한다
			collectToll(player[playTurn].getPlayerPosition());
			JOptionPane.showMessageDialog(null, LandConstants.NAME[player[playTurn].getPlayerPosition()]+"에서 통행료를 냈다.\n"+
					(int)( LandConstants.LAND_PRICE[ player[playTurn].getPlayerPosition()  ] 
							* landPanel[player[playTurn].getPlayerPosition()].getBuildingLvl() 
							*0.2 ) +"원 ");
			repaint();
		}

		// 플레이어가 구입가능토지가아닌 이벤트패널을 밟았을때 처리하는 파트, 해당 이벤트에맞는 이미지를 출력
		if(player[playTurn].getPlayerPosition() == 8) {
			JOptionPane.showMessageDialog(null,"교수님 살려주세요ㅜㅠ");
			player[playTurn].setFrozenTurn(2); // 과제폭탄일때 이동제한
			Event.setVisible(false);
			Event.Reset();
		} //이벤트존 효과
		
		if(player[playTurn].getPlayerPosition() == 16) {
			JOptionPane.showMessageDialog(null,"축제닷!");
			Event.setVisible(false);
			Event.Reset();
		} //이벤트존 효과
		if(player[playTurn].getPlayerPosition() == 24) {
			JOptionPane.showMessageDialog(null,"자유시간!");
			Event.setVisible(false);
			Event.Reset();
		} //이벤트존 효과
		updateInfo();
		repaint();

	} //gamePlayAction




}
