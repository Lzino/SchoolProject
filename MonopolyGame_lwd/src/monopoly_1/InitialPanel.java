package monopoly_1;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class InitialPanel extends JPanel {
	private JPanel setPanel; // 시작화면 패널 생성
	private JLabel lblTitle, lblMark,MainImage; 
	private JButton btnOdd, btnEven; //첫번째 버튼과 두번째 버튼
	private int nRandom; //랜덤변수 선언
	private MonopolyPanel Game; //모노폴리패널에서 객체 선언
	private int Order; //순서변수 선언
	
	

	private InitialListener initialL; //난수리스너  선언
	
	public InitialPanel() {
		setPreferredSize(new Dimension(800,800)); //사이즈 800x800
		setBackground(Color.yellow); //배경색 노랑
		setLayout(null); //Layout disable
		
		initialL = new InitialListener();//난수 리스너 생성
		
		setPanel = new JPanel(); //패널 생성
		setPanel.setBounds(0,0,800,800);//location
		setPanel.setBackground(new Color(255,224,49)); //배경색
		setPanel.setLayout(null);//Layout disable
		setPanel.setVisible(true);
		add(setPanel); //패널 추가
		
		ImageIcon Title = new ImageIcon("Maintitle.png"); //시작화면 타이틀 이미지 생성
		lblTitle=new JLabel("",Title,SwingConstants.CENTER); 
		lblTitle.setBounds(105,10,615,100); 
		lblTitle.setLayout(null); 
		lblTitle.setVisible(true); 
		setPanel.add(lblTitle);
		
		ImageIcon SubTitle = new ImageIcon("Diechoicetxt.png");//카드위 문구 이미지 생성
		lblMark = new JLabel("",SubTitle,SwingConstants.CENTER); 
		lblMark.setBounds(150,90,500,200); 
		lblMark.setFont(new Font("Verdana",Font.BOLD,30)); 
		lblMark.setVisible(true); 
		setPanel.add(lblMark);
		
		Font fntButton = new Font("Verdana",Font.PLAIN,15);
		
		ImageIcon Diechoice1=new ImageIcon("Diechoice1_1.png"); //버튼 1 이미지 생성
		btnOdd = new JButton(Diechoice1); 
		btnOdd.setBounds(30,265,320,350); 
		btnOdd.addActionListener(initialL); 
		btnOdd.setBorderPainted(false); // 버튼 경계선 제거
	    btnOdd.setFocusPainted(false); //포커스(선택했던 버튼 표시) 제거
		btnOdd.setContentAreaFilled(false);//버튼영역 배경 제거
		btnOdd.setVisible(true); 
		setPanel.add(btnOdd);
		
		ImageIcon Diechoice2=new ImageIcon("Diechoice2_1.png"); //버튼 2 이미지 생성
		btnEven = new JButton(Diechoice2);// 버튼2 생성
		btnEven.setBounds(430,265,320,350);
		btnEven.setBorderPainted(false); 
	    btnEven.setFocusPainted(false); 
		btnEven.setContentAreaFilled(false);
		btnEven.addActionListener(initialL);
		btnEven.setVisible(true);
		setPanel.add(btnEven);	
		
		ImageIcon TitleImage= new ImageIcon("MainCharacter.png"); //하단 이미지 생성
		MainImage=new JLabel("",TitleImage,SwingConstants.CENTER); 
		MainImage.setBounds(240,620,300,180); 
		MainImage.setHorizontalAlignment(SwingConstants.CENTER);
		MainImage.setVerticalAlignment(SwingConstants.CENTER);
		MainImage.setVisible(true); 
		setPanel.add(MainImage); 
	
		
		nRandom = 0;//랜덤 변수 0으로 초기화
	}//constructor
	
	public int getOrder(){return Order;}  //순서 메소드
	
	public void FirstResult(){ //첫번째 결과 메소드
		String result = "Player 1 is First!";  //스트링 변수로 선언 및 초기화
		JOptionPane.showMessageDialog(null,result); // showMessageDialog 메소드를 사용하여 메세지창 띄움.
		lblTitle.setVisible(false); //안보이게 하기 (이하 게임전환을 위한 reset)
		lblMark.setVisible(false); 
		MainImage.setVisible(false);
		btnOdd.setVisible(false);
		btnEven.setVisible(false);
		setPanel.setVisible(false);
		Order = 0; //순서 변수 0으로 초기화
		Game = new MonopolyPanel(Order); // 객체 생성
		Game.setBounds(0,0,1400,1400); //객체의 위치 범위 지정.
		add(Game);
	}//FirstResult()
	
	public void SecondResult(){ //두번째 결과 메소드
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
	
	
	private class InitialListener implements ActionListener{ //이벤트 리스너 클래스 생성
		
		public void actionPerformed(ActionEvent event){ //actionPerformed 메소드 생성
		
		Object obj = event.getSource(); //오브젝트 생성
		
		if (obj == btnOdd || obj == btnEven ){ //어떤 버튼을 누르면
			nRandom=(int)(Math.random()*50)+1; //난수 생성
			if(nRandom%2==0){ //난수가 짝수면
				FirstResult(); //첫번째 결과 메소드 호출
			}else{ //난수가 홀수면 
				SecondResult();//두번째 결과 메소드 호출
			}		
		} //second elseif
		
	}//actionPerformed()
		
	}
}//GameListener Class




