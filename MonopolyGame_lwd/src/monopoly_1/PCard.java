package monopoly_1;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PCard extends JPanel {
	private JPanel setPanel; //패널티 카드 패널 생성
	private JButton pickCard; // 카드뽑기 버튼 생성
	private JLabel bg; // 레이블 중복을 피하기 위한 배경 레이블 생성
	private JLabel pCard1,pCard2,pCard3; // 패널티카드 세가지경우 레이블 각각 생성
	private int nRandom; //랜덤 변수 선언
	private int Result; //결과값 선언
	
	private GameListener gameL; //리스너 객체 선언
	
	public PCard(){
		setPreferredSize(new Dimension(480,480));//사이즈 설정
		setBackground(Color.white);//배경색
		setLayout(null); //layout disable
		
		gameL = new GameListener(); //리스너 객체 생성
		
		setPanel = new JPanel(); //패널 생성
		setPanel.setBounds(10,10,460,460);
		setPanel.setBackground(Color.white); 
		setPanel.setLayout(null); 
		add(setPanel); 
		
		bg = new JLabel();//배경레이블 생성
		bg.setBounds(0,0,460,460);
		bg.setBackground(Color.white); 
		bg.setVisible(true); 
		setPanel.add(bg);
		
		Font fntButton = new Font("Verdana",Font.PLAIN,12); //폰트 생성
		
		pickCard = new JButton("Pick Card");//카드뽑기 버튼 생성
		pickCard.setBounds(100,100,200,200); 
		pickCard.setVisible(true);
		pickCard.setFont(fntButton); 
		pickCard.addActionListener(gameL); 
		bg.add(pickCard);
		
		ImageIcon p_icon = new ImageIcon("MT.gif"); //'MT' 이미지생성
		pCard1 = new JLabel(p_icon); 
		pCard1.setBounds(0,0,440,440); 
		pCard1.setHorizontalAlignment(SwingConstants.CENTER); 
		pCard1.setVerticalAlignment(SwingConstants.CENTER); 
		pCard1.setVisible(false);
		setPanel.add(pCard1); 
		
		ImageIcon p_icon2 = new ImageIcon("FGrade.gif"); //'F성적표' 이미지 생성
		pCard2 = new JLabel(p_icon2); 
		pCard2.setBounds(0,0,440,440); 
		pCard2.setHorizontalAlignment(SwingConstants.CENTER); 
		pCard2.setVerticalAlignment(SwingConstants.CENTER); 
		pCard2.setVisible(false); 
		setPanel.add(pCard2); 
		
		ImageIcon p_icon3 = new ImageIcon("extra.gif"); //'보강' 이미지 생성
		pCard3 = new JLabel(p_icon3); 
		pCard3.setBounds(0,0,440,440);
		pCard3.setHorizontalAlignment(SwingConstants.CENTER); 
		pCard3.setVerticalAlignment(SwingConstants.CENTER);
		pCard3.setVisible(false);
		setPanel.add(pCard3);
		
		nRandom = 0; // 랜덤 변수 0으로 초기화 
	}
	
	public void Result1(){ //첫번째 이벤트 메소드
		pCard1.setVisible(true); 
		String result = "MT next!"; 
		JOptionPane.showMessageDialog(null,result);
		
	}
	public void Result2(){ //두번째 이벤트 메소드
		pCard2.setVisible(true); 
		String result = "F Grade!"; 
		JOptionPane.showMessageDialog(null,result); 
	}
	public void Result3(){ // 세번째 이벤트 메소드
		pCard3.setVisible(true); 
		String result = "Extra Lecture!"; 
		JOptionPane.showMessageDialog(null,result);
	}
	
	public void start(){ //시작 메소드 (재시작을 위함)
		setPanel.setVisible(true); 
		pickCard.setVisible(true); 
	}
	public void Reset(){ //리셋 메소드
		pCard1.setVisible(false); 
		pCard2.setVisible(false); 
		pCard3.setVisible(false);
	}
	
	private class GameListener implements ActionListener{

		public void actionPerformed(ActionEvent event){ 
		
		Object obj = event.getSource(); // 이벤트 객체 생성
		
		int a = 0; //정수형 변수 선언 및 초기화
		nRandom = (int)(Math.random()*3)+1; //난수 발생
		a = nRandom; //난수로 초기화
		
		if(obj == pickCard){ //버튼을 눌렀을때
			pickCard.setVisible(false); //버튼 숨기기
		if(a%3==2) {//난수를 3으로 나눴을때 2가 남으면
			Result1(); 
			Reset(); 
			}//if
		
		else if(a%3==1){//난수를 3으로 나눴을때 1이 남으면
			Result2(); 
			Reset(); 
			} //else if
		
		else {//3의 배수이면
			Result3(); 
			Reset();
			} //else
	
		}//if(obj == pickCard)
	}//actionPerformed
  }//GameListener class
}//PCard class