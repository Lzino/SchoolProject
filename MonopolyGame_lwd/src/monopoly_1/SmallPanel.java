package monopoly_1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// 땅구매시 출력되는 창에 올라가는 패널. 땅구매와 관련된 기능을 함
public class SmallPanel extends JPanel {

	private JLabel lblSign,lblPrice; // 땅이름, 땅가격 출력을 위한 변수
	private JRadioButton[] rbtn; // 땅만살것인지 건물도 지을것인지를 선택가능하게하는 라디오버튼배열
	private String[] radioText = {"안사용","토지구입","유체크비콘설치 x2","최신컴퓨터설치 x3","신관증축 x4"}; // 각 버튼에 대응되는 텍스트
	private JButton btnSelect; // 구매결정버튼
	private SmallPanelListener smallL; // 액션리스너변수
	
	/*메인루틴에서 MonopolyPanel클래스 객체를 받아와 저장하기위한 변수
	 * 
	 */
	private MonopolyPanel copied;  
	private LandPanel[] landPanel; 
	private Player[] player;

	private int playTurn;
	
	private JFrame frame; // 구매창을 만드는데 사용되는프레임 

	//객체 생성시 실행되는 생성자로 MonopolyPanel클래스 객체를 매개변수로 받음
	public SmallPanel(MonopolyPanel parent)
	{
		/* 메인루틴의 MonopolyPanel클래스의 객체를 받아 저장하고, 해당객체안에서 저장되어있는
		 * 각종 변수정보등을 함께 get함로 받아와 SmallPanel클래스안의 변수에 저장한다
		 */
		copied = parent;
		landPanel=copied.getLandPanel();
		player=copied.getPlayer();

		playTurn=copied.getPlayTurn();

		/*
		 * 작은패널의 크기, 배경색, 레이아웃등의 설정
		 */
		setPreferredSize(new Dimension(200,300));
		setBackground(Color.pink);
		setLayout(new GridLayout(8,1)); // 라디오버튼을 규칙적으로 배치하기위한 그리드레이아웃설정
		
		/* 플레이어가 특정 땅에 도착했을때 땅위치를 받아서 살것인지를 묻는 레이블변수 생성, 위치설정 + 땅가격레이블 생성
		 * 각 레이블 패널에 추가
		 */
		lblSign = new JLabel( LandConstants.NAME[ player[copied.getPlayTurn()].getPlayerPosition() ]+" 살래말래");
		lblSign.setBounds(30, 20, 100, 20);
		lblPrice = new JLabel(""+LandConstants.LAND_PRICE[ player[copied.getPlayTurn()].getPlayerPosition() ]);
		add(lblSign);
		add(lblPrice);
		
		/* 라디오버튼들을 담을 그룹생성
		 * 다섯개의 라디오버튼이름을 만들고 각각 생성, 배열인덱스에 대응되는 radioText를 이용하여 초기화후 버튼그룹에 담는다
		 */
		smallL = new SmallPanelListener();
		ButtonGroup group = new ButtonGroup(); 
		rbtn = new JRadioButton[5];
		for(int i=0;i<5;i++) {
			rbtn[i]=new JRadioButton(radioText[i]);
			group.add(rbtn[i]);
		}
		for(int i=0;i<5;i++) {
			add(rbtn[i]);
		}
		
		// 구매완료버튼생성, 리스너에연결후 패널에 추가
		btnSelect = new JButton("OK");
		btnSelect.addActionListener(smallL);
		add(btnSelect);
		
	} // SmallPanel


	// 메인루틴에서 생성한 프레임을 받아와 제어하기위한 함수
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	
	// 구매할 토지의 금액을 계산, ( 새로운건물랩-현재건물랩 +1 ) *땅가격으로 가격을 반환
	public int calcPrice(int newLvl ) { // 차액만큼 지불

		int nowPos = player[MonopolyPanel.playTurn].getPlayerPosition();
		int cost;
		cost = ( newLvl-landPanel[nowPos].getBuildingLvl() +1 ) * LandConstants.LAND_PRICE[nowPos] ; 
	
		return cost;
	}
	
	// 플레이어가 밟은땅의 이름과 가격을 보여주는 레이블을 업데이트
	public void updateSign() { //살래말래
		lblSign.setText( " < "+LandConstants.NAME[ player[MonopolyPanel.playTurn].getPlayerPosition() ]+" > "+ "살래말래" );
		lblPrice.setText("$"+LandConstants.LAND_PRICE[ player[copied.getPlayTurn()].getPlayerPosition() ]);
	}
	
	
	/* SmallPanel클래스의 주된 기능을 담당하는 액션리스너, 선택된 라디오버튼을 골라서 대응되는 메세지와 땅으
	 * 
	 */
	private class SmallPanelListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent event) {

			Object obj=event.getSource();
			if(obj==btnSelect) {
				
				// if문에서 선택된 버튼을 찾아서 해당 기능이 실행되도록함
				if(rbtn[0].isSelected()) {
					System.out.println("안사용");
					frame.dispose(); //구매창제거
				}
				if(rbtn[1].isSelected()) {
					System.out.println("토지구입");
					// 메인에서 받아온 클래스를 통하여 플레이어가 구입하려는 땅과 가격을 출력
					int nowPos=player[copied.getPlayTurn()].getPlayerPosition();
					
					// 땅을 구매하기전에 돈이 충분한가를 검사
					if( player[playTurn].getPlayerMoney() < LandConstants.LAND_PRICE[nowPos] ) {
						JOptionPane.showMessageDialog(null, "돈이업서");
					} else {
						
						// 따로 저장되어있는 토지 이미지파일들을 찾아서 아이콘에 저장
						ImageIcon icon = new ImageIcon(
								LandConstants.NAME[player[MonopolyPanel.playTurn].getPlayerPosition()]
										+ "("
										+ ( MonopolyPanel.playTurn + 1)+"_"+0 
										+ ")"
										+ ".png" );
						/*
						 * 1. 플레이어가 구입한땅을 새로운 이미지로 교체
						 * 2. 땅가격만큼의 돈을 지불
						 * 3. 랜드패널의 소유자부분을 플레이어이름 P1등으로 교체
						 * 4. 토지만구입시 1 등으로 건물레벨을 새롭게 변경
						 */
						landPanel[player[MonopolyPanel.playTurn].getPlayerPosition()].lblLand[player[MonopolyPanel.playTurn].getPlayerPosition()].setIcon(icon);
						player[MonopolyPanel.playTurn].setPlayerMoney( player[MonopolyPanel.playTurn].getPlayerMoney() - LandConstants.LAND_PRICE[nowPos] );
						landPanel[player[MonopolyPanel.playTurn].getPlayerPosition()].setOwner( player[MonopolyPanel.playTurn].getPlayerNum() );
						landPanel[player[playTurn].getPlayerPosition()].setBuildingLvl(1);

						
					}
					/*
					 * 각종정보변경후 화면상에서갱신, 구매창제거
					 */
					copied.updateInfo();
					repaint();
					frame.dispose();
				}

				// if문에서 선택된 버튼을 찾아서 해당 기능이 실행되도록함
				if(rbtn[2].isSelected()) {
					System.out.println("건물+1");
					int nowPos=player[copied.getPlayTurn()].getPlayerPosition();
					// 땅을 구매하기전에 돈이 충분한가를 검사
					if( player[playTurn].getPlayerMoney() < LandConstants.LAND_PRICE[nowPos] ) {
						JOptionPane.showMessageDialog(null, "돈이업서");
					} else {
						// 따로 저장되어있는 토지 이미지파일들을 찾아서 아이콘에 저장
						ImageIcon icon = new ImageIcon(
								LandConstants.NAME[player[MonopolyPanel.playTurn].getPlayerPosition()]
										+ "("
										+ ( MonopolyPanel.playTurn + 1)+"_"+ 1
										+ ")"
										+ ".png" );
						/*
						 * 1. 플레이어가 구입한땅을 새로운 이미지로 교체
						 * 2. 땅가격만큼의 돈을 지불
						 * 3. 랜드패널의 소유자부분을 플레이어이름 P1등으로 교체
						 * 4. 토지만구입시 1 등으로 건물레벨을 새롭게 변경
						 */
						landPanel[player[MonopolyPanel.playTurn].getPlayerPosition()].lblLand[player[MonopolyPanel.playTurn].getPlayerPosition()].setIcon(icon);
						player[MonopolyPanel.playTurn].setPlayerMoney( player[MonopolyPanel.playTurn].getPlayerMoney() - calcPrice(2) );
						landPanel[player[MonopolyPanel.playTurn].getPlayerPosition()].setOwner( player[MonopolyPanel.playTurn].getPlayerNum() );
						landPanel[player[playTurn].getPlayerPosition()].setBuildingLvl(2);
						
					
						
					}
					/*
					 * 각종정보변경후 화면상에서갱신, 구매창제거
					 */
					copied.updateInfo();
					repaint();
					frame.dispose();
				}

				if(rbtn[3].isSelected()) {
					System.out.println("건물+2");
					// if문에서 선택된 버튼을 찾아서 해당 기능이 실행되도록함
					int nowPos=player[copied.getPlayTurn()].getPlayerPosition();
					// 땅을 구매하기전에 돈이 충분한가를 검사
					if( player[playTurn].getPlayerMoney() < LandConstants.LAND_PRICE[nowPos] ) {
						JOptionPane.showMessageDialog(null, "돈이업서");
					} else {
						

						// 따로 저장되어있는 토지 이미지파일들을 찾아서 아이콘에 저장
						ImageIcon icon = new ImageIcon(
								LandConstants.NAME[player[MonopolyPanel.playTurn].getPlayerPosition()]
										+ "("
										+ ( MonopolyPanel.playTurn + 1)+"_"+ 2
										+ ")"
										+ ".png" );
						/*
						 * 1. 플레이어가 구입한땅을 새로운 이미지로 교체
						 * 2. 땅가격만큼의 돈을 지불
						 * 3. 랜드패널의 소유자부분을 플레이어이름 P1등으로 교체
						 * 4. 토지만구입시 1 등으로 건물레벨을 새롭게 변경
						 */
						landPanel[player[MonopolyPanel.playTurn].getPlayerPosition()].lblLand[player[MonopolyPanel.playTurn].getPlayerPosition()].setIcon(icon);
						player[MonopolyPanel.playTurn].setPlayerMoney( player[MonopolyPanel.playTurn].getPlayerMoney() - calcPrice(2) );
						landPanel[player[MonopolyPanel.playTurn].getPlayerPosition()].setOwner( player[MonopolyPanel.playTurn].getPlayerNum() );
						landPanel[player[playTurn].getPlayerPosition()].setBuildingLvl(3);
						
						
					}
					/*
					 * 각종정보변경후 화면상에서갱신, 구매창제거
					 */
					copied.updateInfo();
					repaint();
					frame.dispose();
				}

				if(rbtn[4].isSelected()) {
					System.out.println("건물+3");
					// if문에서 선택된 버튼을 찾아서 해당 기능이 실행되도록함
					int nowPos=player[copied.getPlayTurn()].getPlayerPosition();
					// 땅을 구매하기전에 돈이 충분한가를 검사
					if( player[playTurn].getPlayerMoney() < LandConstants.LAND_PRICE[nowPos] ) {
						JOptionPane.showMessageDialog(null, "돈이업서");
					} else {


						// 따로 저장되어있는 토지 이미지파일들을 찾아서 아이콘에 저장
						ImageIcon icon = new ImageIcon(
								LandConstants.NAME[player[MonopolyPanel.playTurn].getPlayerPosition()]
										+ "("
										+ ( MonopolyPanel.playTurn + 1)+"_"+ 3
										+ ")"
										+ ".png" );
						/*
						 * 1. 플레이어가 구입한땅을 새로운 이미지로 교체
						 * 2. 땅가격만큼의 돈을 지불
						 * 3. 랜드패널의 소유자부분을 플레이어이름 P1등으로 교체
						 * 4. 토지만구입시 1 등으로 건물레벨을 새롭게 변경
						 */
						landPanel[player[MonopolyPanel.playTurn].getPlayerPosition()].lblLand[player[MonopolyPanel.playTurn].getPlayerPosition()].setIcon(icon);
						player[MonopolyPanel.playTurn].setPlayerMoney( player[MonopolyPanel.playTurn].getPlayerMoney() - calcPrice(2) );
						landPanel[player[MonopolyPanel.playTurn].getPlayerPosition()].setOwner( player[MonopolyPanel.playTurn].getPlayerNum() );
						landPanel[player[playTurn].getPlayerPosition()].setBuildingLvl(4);

					}
					/*
					 * 각종정보변경후 화면상에서갱신, 구매창제거
					 */
					copied.updateInfo();
					repaint();
					frame.dispose();
				}



			}


		} //actionPerformed


	} //SmallPanelListeneesfsaf





} //smallPanel class
