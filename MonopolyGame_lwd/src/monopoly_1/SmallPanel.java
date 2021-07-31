package monopoly_1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// �����Ž� ��µǴ� â�� �ö󰡴� �г�. �����ſ� ���õ� ����� ��
public class SmallPanel extends JPanel {

	private JLabel lblSign,lblPrice; // ���̸�, ������ ����� ���� ����
	private JRadioButton[] rbtn; // ����������� �ǹ��� ������������ ���ð����ϰ��ϴ� ������ư�迭
	private String[] radioText = {"�Ȼ��","��������","��üũ���ܼ�ġ x2","�ֽ���ǻ�ͼ�ġ x3","�Ű����� x4"}; // �� ��ư�� �����Ǵ� �ؽ�Ʈ
	private JButton btnSelect; // ���Ű�����ư
	private SmallPanelListener smallL; // �׼Ǹ����ʺ���
	
	/*���η�ƾ���� MonopolyPanelŬ���� ��ü�� �޾ƿ� �����ϱ����� ����
	 * 
	 */
	private MonopolyPanel copied;  
	private LandPanel[] landPanel; 
	private Player[] player;

	private int playTurn;
	
	private JFrame frame; // ����â�� ����µ� ���Ǵ������� 

	//��ü ������ ����Ǵ� �����ڷ� MonopolyPanelŬ���� ��ü�� �Ű������� ����
	public SmallPanel(MonopolyPanel parent)
	{
		/* ���η�ƾ�� MonopolyPanelŬ������ ��ü�� �޾� �����ϰ�, �ش簴ü�ȿ��� ����Ǿ��ִ�
		 * ���� ������������ �Բ� get�Է� �޾ƿ� SmallPanelŬ�������� ������ �����Ѵ�
		 */
		copied = parent;
		landPanel=copied.getLandPanel();
		player=copied.getPlayer();

		playTurn=copied.getPlayTurn();

		/*
		 * �����г��� ũ��, ����, ���̾ƿ����� ����
		 */
		setPreferredSize(new Dimension(200,300));
		setBackground(Color.pink);
		setLayout(new GridLayout(8,1)); // ������ư�� ��Ģ������ ��ġ�ϱ����� �׸��巹�̾ƿ�����
		
		/* �÷��̾ Ư�� ���� ���������� ����ġ�� �޾Ƽ� ��������� ���� ���̺��� ����, ��ġ���� + �����ݷ��̺� ����
		 * �� ���̺� �гο� �߰�
		 */
		lblSign = new JLabel( LandConstants.NAME[ player[copied.getPlayTurn()].getPlayerPosition() ]+" �췡����");
		lblSign.setBounds(30, 20, 100, 20);
		lblPrice = new JLabel(""+LandConstants.LAND_PRICE[ player[copied.getPlayTurn()].getPlayerPosition() ]);
		add(lblSign);
		add(lblPrice);
		
		/* ������ư���� ���� �׷����
		 * �ټ����� ������ư�̸��� ����� ���� ����, �迭�ε����� �����Ǵ� radioText�� �̿��Ͽ� �ʱ�ȭ�� ��ư�׷쿡 ��´�
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
		
		// ���ſϷ��ư����, �����ʿ������� �гο� �߰�
		btnSelect = new JButton("OK");
		btnSelect.addActionListener(smallL);
		add(btnSelect);
		
	} // SmallPanel


	// ���η�ƾ���� ������ �������� �޾ƿ� �����ϱ����� �Լ�
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	
	// ������ ������ �ݾ��� ���, ( ���ο�ǹ���-����ǹ��� +1 ) *���������� ������ ��ȯ
	public int calcPrice(int newLvl ) { // ���׸�ŭ ����

		int nowPos = player[MonopolyPanel.playTurn].getPlayerPosition();
		int cost;
		cost = ( newLvl-landPanel[nowPos].getBuildingLvl() +1 ) * LandConstants.LAND_PRICE[nowPos] ; 
	
		return cost;
	}
	
	// �÷��̾ �������� �̸��� ������ �����ִ� ���̺��� ������Ʈ
	public void updateSign() { //�췡����
		lblSign.setText( " < "+LandConstants.NAME[ player[MonopolyPanel.playTurn].getPlayerPosition() ]+" > "+ "�췡����" );
		lblPrice.setText("$"+LandConstants.LAND_PRICE[ player[copied.getPlayTurn()].getPlayerPosition() ]);
	}
	
	
	/* SmallPanelŬ������ �ֵ� ����� ����ϴ� �׼Ǹ�����, ���õ� ������ư�� ��� �����Ǵ� �޼����� ����
	 * 
	 */
	private class SmallPanelListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent event) {

			Object obj=event.getSource();
			if(obj==btnSelect) {
				
				// if������ ���õ� ��ư�� ã�Ƽ� �ش� ����� ����ǵ�����
				if(rbtn[0].isSelected()) {
					System.out.println("�Ȼ��");
					frame.dispose(); //����â����
				}
				if(rbtn[1].isSelected()) {
					System.out.println("��������");
					// ���ο��� �޾ƿ� Ŭ������ ���Ͽ� �÷��̾ �����Ϸ��� ���� ������ ���
					int nowPos=player[copied.getPlayTurn()].getPlayerPosition();
					
					// ���� �����ϱ����� ���� ����Ѱ��� �˻�
					if( player[playTurn].getPlayerMoney() < LandConstants.LAND_PRICE[nowPos] ) {
						JOptionPane.showMessageDialog(null, "���̾���");
					} else {
						
						// ���� ����Ǿ��ִ� ���� �̹������ϵ��� ã�Ƽ� �����ܿ� ����
						ImageIcon icon = new ImageIcon(
								LandConstants.NAME[player[MonopolyPanel.playTurn].getPlayerPosition()]
										+ "("
										+ ( MonopolyPanel.playTurn + 1)+"_"+0 
										+ ")"
										+ ".png" );
						/*
						 * 1. �÷��̾ �����Ѷ��� ���ο� �̹����� ��ü
						 * 2. �����ݸ�ŭ�� ���� ����
						 * 3. �����г��� �����ںκ��� �÷��̾��̸� P1������ ��ü
						 * 4. ���������Խ� 1 ������ �ǹ������� ���Ӱ� ����
						 */
						landPanel[player[MonopolyPanel.playTurn].getPlayerPosition()].lblLand[player[MonopolyPanel.playTurn].getPlayerPosition()].setIcon(icon);
						player[MonopolyPanel.playTurn].setPlayerMoney( player[MonopolyPanel.playTurn].getPlayerMoney() - LandConstants.LAND_PRICE[nowPos] );
						landPanel[player[MonopolyPanel.playTurn].getPlayerPosition()].setOwner( player[MonopolyPanel.playTurn].getPlayerNum() );
						landPanel[player[playTurn].getPlayerPosition()].setBuildingLvl(1);

						
					}
					/*
					 * �������������� ȭ��󿡼�����, ����â����
					 */
					copied.updateInfo();
					repaint();
					frame.dispose();
				}

				// if������ ���õ� ��ư�� ã�Ƽ� �ش� ����� ����ǵ�����
				if(rbtn[2].isSelected()) {
					System.out.println("�ǹ�+1");
					int nowPos=player[copied.getPlayTurn()].getPlayerPosition();
					// ���� �����ϱ����� ���� ����Ѱ��� �˻�
					if( player[playTurn].getPlayerMoney() < LandConstants.LAND_PRICE[nowPos] ) {
						JOptionPane.showMessageDialog(null, "���̾���");
					} else {
						// ���� ����Ǿ��ִ� ���� �̹������ϵ��� ã�Ƽ� �����ܿ� ����
						ImageIcon icon = new ImageIcon(
								LandConstants.NAME[player[MonopolyPanel.playTurn].getPlayerPosition()]
										+ "("
										+ ( MonopolyPanel.playTurn + 1)+"_"+ 1
										+ ")"
										+ ".png" );
						/*
						 * 1. �÷��̾ �����Ѷ��� ���ο� �̹����� ��ü
						 * 2. �����ݸ�ŭ�� ���� ����
						 * 3. �����г��� �����ںκ��� �÷��̾��̸� P1������ ��ü
						 * 4. ���������Խ� 1 ������ �ǹ������� ���Ӱ� ����
						 */
						landPanel[player[MonopolyPanel.playTurn].getPlayerPosition()].lblLand[player[MonopolyPanel.playTurn].getPlayerPosition()].setIcon(icon);
						player[MonopolyPanel.playTurn].setPlayerMoney( player[MonopolyPanel.playTurn].getPlayerMoney() - calcPrice(2) );
						landPanel[player[MonopolyPanel.playTurn].getPlayerPosition()].setOwner( player[MonopolyPanel.playTurn].getPlayerNum() );
						landPanel[player[playTurn].getPlayerPosition()].setBuildingLvl(2);
						
					
						
					}
					/*
					 * �������������� ȭ��󿡼�����, ����â����
					 */
					copied.updateInfo();
					repaint();
					frame.dispose();
				}

				if(rbtn[3].isSelected()) {
					System.out.println("�ǹ�+2");
					// if������ ���õ� ��ư�� ã�Ƽ� �ش� ����� ����ǵ�����
					int nowPos=player[copied.getPlayTurn()].getPlayerPosition();
					// ���� �����ϱ����� ���� ����Ѱ��� �˻�
					if( player[playTurn].getPlayerMoney() < LandConstants.LAND_PRICE[nowPos] ) {
						JOptionPane.showMessageDialog(null, "���̾���");
					} else {
						

						// ���� ����Ǿ��ִ� ���� �̹������ϵ��� ã�Ƽ� �����ܿ� ����
						ImageIcon icon = new ImageIcon(
								LandConstants.NAME[player[MonopolyPanel.playTurn].getPlayerPosition()]
										+ "("
										+ ( MonopolyPanel.playTurn + 1)+"_"+ 2
										+ ")"
										+ ".png" );
						/*
						 * 1. �÷��̾ �����Ѷ��� ���ο� �̹����� ��ü
						 * 2. �����ݸ�ŭ�� ���� ����
						 * 3. �����г��� �����ںκ��� �÷��̾��̸� P1������ ��ü
						 * 4. ���������Խ� 1 ������ �ǹ������� ���Ӱ� ����
						 */
						landPanel[player[MonopolyPanel.playTurn].getPlayerPosition()].lblLand[player[MonopolyPanel.playTurn].getPlayerPosition()].setIcon(icon);
						player[MonopolyPanel.playTurn].setPlayerMoney( player[MonopolyPanel.playTurn].getPlayerMoney() - calcPrice(2) );
						landPanel[player[MonopolyPanel.playTurn].getPlayerPosition()].setOwner( player[MonopolyPanel.playTurn].getPlayerNum() );
						landPanel[player[playTurn].getPlayerPosition()].setBuildingLvl(3);
						
						
					}
					/*
					 * �������������� ȭ��󿡼�����, ����â����
					 */
					copied.updateInfo();
					repaint();
					frame.dispose();
				}

				if(rbtn[4].isSelected()) {
					System.out.println("�ǹ�+3");
					// if������ ���õ� ��ư�� ã�Ƽ� �ش� ����� ����ǵ�����
					int nowPos=player[copied.getPlayTurn()].getPlayerPosition();
					// ���� �����ϱ����� ���� ����Ѱ��� �˻�
					if( player[playTurn].getPlayerMoney() < LandConstants.LAND_PRICE[nowPos] ) {
						JOptionPane.showMessageDialog(null, "���̾���");
					} else {


						// ���� ����Ǿ��ִ� ���� �̹������ϵ��� ã�Ƽ� �����ܿ� ����
						ImageIcon icon = new ImageIcon(
								LandConstants.NAME[player[MonopolyPanel.playTurn].getPlayerPosition()]
										+ "("
										+ ( MonopolyPanel.playTurn + 1)+"_"+ 3
										+ ")"
										+ ".png" );
						/*
						 * 1. �÷��̾ �����Ѷ��� ���ο� �̹����� ��ü
						 * 2. �����ݸ�ŭ�� ���� ����
						 * 3. �����г��� �����ںκ��� �÷��̾��̸� P1������ ��ü
						 * 4. ���������Խ� 1 ������ �ǹ������� ���Ӱ� ����
						 */
						landPanel[player[MonopolyPanel.playTurn].getPlayerPosition()].lblLand[player[MonopolyPanel.playTurn].getPlayerPosition()].setIcon(icon);
						player[MonopolyPanel.playTurn].setPlayerMoney( player[MonopolyPanel.playTurn].getPlayerMoney() - calcPrice(2) );
						landPanel[player[MonopolyPanel.playTurn].getPlayerPosition()].setOwner( player[MonopolyPanel.playTurn].getPlayerNum() );
						landPanel[player[playTurn].getPlayerPosition()].setBuildingLvl(4);

					}
					/*
					 * �������������� ȭ��󿡼�����, ����â����
					 */
					copied.updateInfo();
					repaint();
					frame.dispose();
				}



			}


		} //actionPerformed


	} //SmallPanelListeneesfsaf





} //smallPanel class
