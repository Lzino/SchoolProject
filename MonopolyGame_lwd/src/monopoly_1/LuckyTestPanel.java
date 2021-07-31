package monopoly_1;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LuckyTestPanel extends JPanel {
	private JPanel setPanel; // 미니게임 패널 선언
	private JLabel lblTitle; // 타이틀 문구 레이블 선언
	private JButton btnMiddle, btnLeft, btnRight; // 카드 버튼들 선언
	private JButton btnMiddle2,btnLeft2, btnRight2; // 뒤집힌 카드 버튼들 선언
	private int nRandom; // 난수를 위한 변수 선언
	public int resultNumber =1; //??
	
	private GameListener gameL; //게임 리스너 선언
	
	public LuckyTestPanel() {
		setPreferredSize(new Dimension(430,430)); //사이즈 430X430
		setBackground(Color.white); //배경색은 하얀색
		setLayout(null); //Layout disable
		
		gameL = new GameListener();// 게임 리스너 생성
		
		setPanel = new JPanel(); //패널 생성
		setPanel.setBounds(10,10,400,400);//location
		setPanel.setBackground(Color.white);//배경색
		setPanel.setLayout(null); //Layout disable
		add(setPanel); // 패널 추가
		
		ImageIcon mb_icon = new ImageIcon("Minigame.png"); //배경이미지 생성
		lblTitle = new JLabel(mb_icon); //레이블 생성 및 이미지 삽입
		lblTitle.setBounds(0,0,400,400); // 위치 설정
		lblTitle.setFont(new Font("Verdana",Font.BOLD,30)); //폰트설정
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER); //위치설정
		lblTitle.setVerticalAlignment(SwingConstants.CENTER); //위치설정
		lblTitle.setVisible(true); //보이게 하기
		setPanel.add(lblTitle); //패널에 레이블 추가
		
	
		Font fntButton = new Font("Verdana",Font.PLAIN,12); //폰트 생성
		ImageIcon m_Card1 = new ImageIcon("Card1.jpg");	//카드 이미지
		ImageIcon m_Card2 = new ImageIcon("Card2.jpg");	//뒤집힌 카드 이미지
		
		btnLeft = new JButton(m_Card1); //첫번째 버튼 생성 및 이미지 삽입
		btnLeft.setBounds(40,200,100,150); //위치 설정
		btnLeft.setFont(fntButton); // 폰트설정
		btnLeft.setVisible(true);// 보이게 하기
		btnLeft.addActionListener(gameL); //액션리스너 추가
		btnLeft.setBorderPainted(false); //경계선 제거
		lblTitle.add(btnLeft); //레이블에 버튼 추가
		
		btnLeft2 = new JButton(m_Card2); //뒤집힌 첫번째 버튼 생성 및 이미지 삽입
		btnLeft2.setBounds(40,200,100,150); //위치 설정
		btnLeft2.setFont(fntButton); //폰트설정
		btnLeft2.setVisible(false); //보이지 않게 하기
		btnLeft2.addActionListener(gameL);//액션리스너 추가
		btnLeft2.setBorderPainted(false);//경계선 제거
		lblTitle.add(btnLeft2); //레이블에 버튼 추가
		
		
		btnMiddle = new JButton(m_Card1);//세번째 중간 생성 및 이미지 삽입
		btnMiddle.setBounds(160,200,100,150);//위치 설정
		btnMiddle.setFont(fntButton);//폰트설정
		btnMiddle.setVisible(true);//보이게 하기
		btnMiddle.addActionListener(gameL);//액션리스너 추가
		btnMiddle.setBorderPainted(false);//경계선 제거
		lblTitle.add(btnMiddle);//레이블에 버튼 추가
		
		btnMiddle2 = new JButton(m_Card2); //뒤집힌 중간 버튼 생성 및 이미지 삽입
		btnMiddle2.setBounds(160,200,100,150);//위치 설정
		btnMiddle2.setFont(fntButton);//폰트설정
		btnMiddle2.setVisible(false);//보이지 않게 하기
		btnMiddle2.addActionListener(gameL);//액션리스너 추가
		btnMiddle2.setBorderPainted(false);//경계선 제거
		lblTitle.add(btnMiddle2);//레이블에 버튼 추가
		
		btnRight = new JButton(m_Card1);//오른쪽 버튼 생성 및 이미지 삽입
		btnRight.setBounds(280,200,100,150);//위치 설정
		btnRight.setFont(fntButton);//폰트설정
		btnRight.setVisible(true);//보이게하기
		btnRight.addActionListener(gameL);//액션리스너 추가
		btnRight.setBorderPainted(false);//경계선 제거
		lblTitle.add(btnRight);	//레이블에 버튼 추가
		
		btnRight2 = new JButton(m_Card2); //뒤집힌 오른쪽 버튼 생성 및 이미지 삽입
		btnRight2.setBounds(280,200,100,150); //위치설정
		btnRight2.setFont(fntButton); //폰트설정
		btnRight2.setVisible(false); //보이지 않게 하기
		btnRight2.addActionListener(gameL);//액션 리스너 추가
		btnRight2.setBorderPainted(false); //경계선 제거
		lblTitle.add(btnRight2);	 //레이블에 버튼 추가
		
		nRandom = 0; //랜덤위한 변수를 0으로 초기화
		
	}//constructor
	
	public int getResult() {return resultNumber;}
	public void setReusult(int value) { resultNumber=value; }

	
	public void rightResult(){ //당첨이 나올 경우
		String result = "You are Lucky Guy!"; //스트링 변수 선언 및 초기화
		JOptionPane.showMessageDialog(null,result); // //showMessageDialog 메소드 실행하여 result 출력
		resultNumber = 1;
	}//rightResult()
	
	public void wrongResult(){ //꽝이 나올 경우
		String result = "You are Unlucky Guy!";//스트링 변수 선언 및 초기화
		JOptionPane.showMessageDialog(null,result);////showMessageDialog 메소드 실행하여 result 출력
		resultNumber = 0;
	}//wrongResult()
	
	public void start(){ //시작시 조건 초기화
		lblTitle.setVisible(true);
		btnMiddle.setVisible(true);
		btnLeft.setVisible(true);
		btnRight.setVisible(true);
	} //start()
	
	public void Reset(){ //종료시 모든 레이블 시각화 끄기
		lblTitle.setVisible(false);
		btnMiddle.setVisible(false);
		btnLeft.setVisible(false);
		btnRight.setVisible(false);
		btnMiddle2.setVisible(false);
		btnLeft2.setVisible(false);
		btnRight2.setVisible(false);	
	}
	
	private class GameListener implements ActionListener{ //리스너클래스 생성
		
		public void actionPerformed(ActionEvent event){ //actionPerformed 메소드 생성
		
		Object obj = event.getSource(); //이벤트 객체 생성
		nRandom = (int)(Math.random()*50)+1; // 난수 생성
		
		if(obj == btnLeft||obj == btnMiddle||obj == btnRight){ //버튼을 눌렀을때
			
			
			if(obj == btnLeft){//왼쪽버튼을 누르면
				btnLeft.setVisible(false); //첫번째 카드 숨겨지고
				btnLeft2.setVisible(true);} //뒤집힌 카드 보여주기
			if(obj == btnRight){ //오른쪽 버튼 누르면
				btnRight.setVisible(false);//첫번째 카드 숨겨지고
				btnRight2.setVisible(true);}//뒤집힌 카드 보여주기
			if(obj == btnMiddle){ //가운데 버튼 누르면
				btnMiddle.setVisible(false);//첫번째 카드 숨겨지고
				btnMiddle2.setVisible(true);}//뒤집힌 카드 보여주기
			if(nRandom%2==1){ //난수가 홀수일 경우
				rightResult(); //당첨 메소드 발동
				//resultNumber = 1;
				Reset(); //리셋메소드 발동
			}else{ //난수가 짝수일 경우
				wrongResult(); //꽝 메소드 발동
				//resultNumber = 0;
				Reset(); //리셋 메소드 발동
			}//else
			
		} //second elseif
		
	}//GameListener Class
		
	}//LuckyTestPanel()
	
}//class LuckyTestPanel