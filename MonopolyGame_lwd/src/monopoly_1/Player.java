package monopoly_1;

import java.awt.*;

import javax.swing.*;
// 각가의 플레이어 객체를 만드는데 사용되는 클래스로 위치, 돈정보등을 명시
public class Player extends JPanel {
	private JLabel playerPiece; //플레이어의 말이되는 레이블
	private Point playerPos; //패널상위치
	private int playerMoney; //플레이어
	private int playerPosition; //배열인덱스
	private String playerNum; // 플레이어의 이름으로 P1,P2 등으로 저장됨
	private int frozenTurn; // 과제폭탄에 걸렸을때 멈춰있는턴
	
	
	public Player(int i) { 
		/* 플레이어의 말이되는 레이블을 생성 초기화하고, 크기설정, 플레이어 이름을 설정한다 */
		playerPiece = new JLabel();
		playerPiece.setPreferredSize(new Dimension(53,72));  //30,30
		playerNum="P"+(i+1); // 플레이어이름을 P1, P2등으로 설정한다
		
		// 초기 시작돈과 플레이어 레이블의 위치를 나타내는 좌표객체 생성,초기화, 
		playerMoney=2000000;//1250;
		playerPos = new Point();
		playerPos.x=65;
		playerPos.y=65;
		frozenTurn = 0; // 플레이어가 시작할때는 0으로 시작
		//setLayout(null);
	}
	
	
	// Player 클래스에있는 각종 변수들의 값을 받아오거나 설정하는 get set함수들
	public Point getPoint() { return playerPos; }
	public int getPointX() { return playerPos.x; }
	public int getPointY() { return playerPos.y; }
	public int getPlayerMoney() { return playerMoney; }

	public int getPlayerPosition() { return playerPosition; }
	public JLabel getPlayerPiece() { return playerPiece; }
	public String getPlayerNum() { return playerNum; }
	public int getFrozenTurn() { return frozenTurn; }
	
	public void setPoint(Point ptr) { playerPos=ptr; }
	public void setPointX(int x) { playerPos.x=x; }
	public void setPointY(int y) { playerPos.y=y; }
	public void setPlayerMoney(int value) { playerMoney=value; }

	public void setPlayerPosition(int value) { playerPosition = value; }
	public void setPlayerNum(String str) { playerNum=str; }
	public void setFrozenTurn(int value) { frozenTurn = value; }

	
	
	// 플레이어의 위치를 갱신하는 함수. 매개변수로 주사위눈을 받아서 현재의 플레이어위치에 더한다. 32이상의 값이 될경우 -32를빼서 위치를 갱신
	public void updatePlayerPosition(int value) {
		int newPosition=playerPosition+value;
		if(newPosition>31) {
			newPosition-=32;
			playerMoney+=320000; //출발지통과시 돈을준다
		}
		playerPosition=newPosition;
		
	}//updatePlayerPosition
	
	
	// 플레이어말 레이블의 이미지를 설정하는 함수, 매개변수의 문자열로 png파일의 이름을 받아와 생성한 아이콘을 레이블에 넣어준다
	public void setPlayerImage(String str) {
		ImageIcon pieceIcon=new ImageIcon(str);
		playerPiece = new JLabel("",pieceIcon,SwingConstants.CENTER);
		playerPiece.setPreferredSize(new Dimension(53,72));  //30,30
	} 
	
} // Player class




