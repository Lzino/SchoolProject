package monopoly_1;

import java.awt.*;

import javax.swing.*;
// ������ �÷��̾� ��ü�� ����µ� ���Ǵ� Ŭ������ ��ġ, ���������� ���
public class Player extends JPanel {
	private JLabel playerPiece; //�÷��̾��� ���̵Ǵ� ���̺�
	private Point playerPos; //�гλ���ġ
	private int playerMoney; //�÷��̾�
	private int playerPosition; //�迭�ε���
	private String playerNum; // �÷��̾��� �̸����� P1,P2 ������ �����
	private int frozenTurn; // ������ź�� �ɷ����� �����ִ���
	
	
	public Player(int i) { 
		/* �÷��̾��� ���̵Ǵ� ���̺��� ���� �ʱ�ȭ�ϰ�, ũ�⼳��, �÷��̾� �̸��� �����Ѵ� */
		playerPiece = new JLabel();
		playerPiece.setPreferredSize(new Dimension(53,72));  //30,30
		playerNum="P"+(i+1); // �÷��̾��̸��� P1, P2������ �����Ѵ�
		
		// �ʱ� ���۵��� �÷��̾� ���̺��� ��ġ�� ��Ÿ���� ��ǥ��ü ����,�ʱ�ȭ, 
		playerMoney=2000000;//1250;
		playerPos = new Point();
		playerPos.x=65;
		playerPos.y=65;
		frozenTurn = 0; // �÷��̾ �����Ҷ��� 0���� ����
		//setLayout(null);
	}
	
	
	// Player Ŭ�������ִ� ���� �������� ���� �޾ƿ��ų� �����ϴ� get set�Լ���
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

	
	
	// �÷��̾��� ��ġ�� �����ϴ� �Լ�. �Ű������� �ֻ������� �޾Ƽ� ������ �÷��̾���ġ�� ���Ѵ�. 32�̻��� ���� �ɰ�� -32������ ��ġ�� ����
	public void updatePlayerPosition(int value) {
		int newPosition=playerPosition+value;
		if(newPosition>31) {
			newPosition-=32;
			playerMoney+=320000; //���������� �����ش�
		}
		playerPosition=newPosition;
		
	}//updatePlayerPosition
	
	
	// �÷��̾ ���̺��� �̹����� �����ϴ� �Լ�, �Ű������� ���ڿ��� png������ �̸��� �޾ƿ� ������ �������� ���̺� �־��ش�
	public void setPlayerImage(String str) {
		ImageIcon pieceIcon=new ImageIcon(str);
		playerPiece = new JLabel("",pieceIcon,SwingConstants.CENTER);
		playerPiece.setPreferredSize(new Dimension(53,72));  //30,30
	} 
	
} // Player class




