package monopoly_1;

import javax.swing.*;

public class MonopolyExam { //모노폴리 메인 클래스

	public static void main(String[] args) { //public static으로 선언
		JFrame frame = new JFrame("User Defined Panel");//프레임 생성
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //JFrame 사용시 종료 관련 메소드
		
		InitialPanel Mpanel = new InitialPanel(); //시작패널 객체를 생성
		
		frame.getContentPane().add(Mpanel); //패널을 프레임에 추가
		frame.pack(); //프레임 사이즈 설정
		frame.setVisible(true); //프레임 보이게 하기
	}
}
