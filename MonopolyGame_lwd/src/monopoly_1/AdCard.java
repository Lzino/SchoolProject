package monopoly_1;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AdCard extends JPanel {
	private JPanel setPanel; //찬스 카드 생성
	private JButton pickCard; // 카드 뽑기 버튼 생성
	private JLabel bg; // 레이블 중복을 피하기 위한 배경 레이블 생성
	private JLabel adCard1,adCard2,adCard3; // 각 카드 이벤트를 위한 3개의 레이블 생성
	private int nRandom; // 랜덤을 위한 변수 선언
	private int Result; // 결과값을 보여주는 변수 선언
	
	private GameListener gameL; //리스너 객체 선언
	
	public AdCard(){
		setPreferredSize(new Dimension(480,480)); //사이즈 480X480
		setBackground(Color.white); //배경색 하얀색
		setLayout(null); //layout disable
		
		gameL = new GameListener(); //리스너 객체 생성
		
		setPanel = new JPanel(); //패널 생성
		setPanel.setBounds(10,10,460,460);//location
		setPanel.setBackground(Color.white); //배경색
		setPanel.setLayout(null); //layout diaable
		add(setPanel); //패널 추가
		
		bg = new JLabel(); //배경레이블 생성
		bg.setBounds(0,0,460,460); 
		bg.setBackground(Color.white); 
		bg.setVisible(true); 
		setPanel.add(bg); 
		
		Font fntButton = new Font("Verdana",Font.PLAIN,12); //폰트 생성
		
		pickCard = new JButton("Pick Card"); //카드 뽑기 버튼 추가
		pickCard.setBounds(100,100,200,200);//위치 설정
		pickCard.setVisible(true);
		pickCard.setFont(fntButton); //폰트
		pickCard.addActionListener(gameL); //액션리스너 추가
		bg.add(pickCard); //레이블 위에 카드 뽑기 버튼 추가
		
		ImageIcon a_icon = new ImageIcon("Break.gif"); // 휴강 이미지 생성
		adCard1 = new JLabel(a_icon); // 레이블 생성 및 이미지 삽입
		adCard1.setBounds(0,0,440,440); 
		adCard1.setHorizontalAlignment(SwingConstants.CENTER); 
		adCard1.setVerticalAlignment(SwingConstants.CENTER);
		adCard1.setVisible(false); 
		setPanel.add(adCard1);
		
		ImageIcon a_icon2 = new ImageIcon("Grade.gif"); //A성적표 이미지 생성 
		adCard2 = new JLabel(a_icon2); 
		adCard2.setBounds(0,0,440,440); 
		adCard2.setHorizontalAlignment(SwingConstants.CENTER);
		adCard2.setVerticalAlignment(SwingConstants.CENTER);
		adCard2.setVisible(false); 
		setPanel.add(adCard2); 
		
		ImageIcon a_icon3 = new ImageIcon("Scholarship.gif"); //장학금 이미지 생성
		adCard3 = new JLabel(a_icon3);
		adCard3.setBounds(0,0,440,440);
		adCard3.setHorizontalAlignment(SwingConstants.CENTER);
		adCard3.setVerticalAlignment(SwingConstants.CENTER);
		adCard3.setVisible(false); 
		setPanel.add(adCard3);
		
		nRandom = 0; //랜덤 위한 변수를 0으로 초기화
	}
	
	public void start(){ //시작 메소드
		setPanel.setVisible(true); 
		pickCard.setVisible(true); 
	}
	public void Reset(){//리셋 메소드
		adCard1.setVisible(false); 
		adCard2.setVisible(false);
		adCard3.setVisible(false);
	}
	
	public void Result1(){ //첫번째 결과
		adCard1.setVisible(true); //휴강 이벤트 보이게 하기
		String result = "Break!"; // 스트링 변수 선언 및 초기화 
		JOptionPane.showMessageDialog(null,result);//showMessageDialog 메소드 발생하여 result 출력
	}
	public void Result2(){ //두번쨰 결과
		adCard2.setVisible(true); 
		String result = "A+ Grade!"; 
		JOptionPane.showMessageDialog(null,result);
	}
	public void Result3(){ //세번째 결과
		adCard3.setVisible(true); 
		String result = "Scholarship!"; 
		JOptionPane.showMessageDialog(null,result);
	}
	
	private class GameListener implements ActionListener{ //리스너 클래스 생성
		
		public void actionPerformed(ActionEvent event){ 
		
		Object obj = event.getSource(); //이벤트 객체 생성
		
		int a = 0; //정수형 변수 및 초기화
		nRandom = (int)(Math.random()*3)+1; //난수 생성
		a = nRandom; // 난수로 초기화
		
		if(obj == pickCard){ //버튼을 누를 때
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
	}//actionPerformed()
	}//GameListener class
}//PCard class