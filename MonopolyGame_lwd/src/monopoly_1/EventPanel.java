package monopoly_1;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class EventPanel extends JPanel {
	private JPanel setPanel; //이벤트존 패널 생성
	private JLabel hw,fest,free; //이벤트존 '과제','축제','자유시간'레이블 생성
	
	public EventPanel(){ 
		setPreferredSize(new Dimension(480,480)); //사이즈 생성
		setBackground(Color.white); //배경색
		setLayout(null); //Layout disable
		
		setPanel = new JPanel(); //패널 생성
		setPanel.setBounds(10,10,460,460);//location
		setPanel.setBackground(Color.white); //배경색
		setPanel.setLayout(null); //Layout disable
		add(setPanel); // 패널 추가
		
		ImageIcon hw_icon = new ImageIcon("hw.gif"); //'과제' 이미지 생성
		hw = new JLabel(hw_icon); 
		hw.setBounds(0,0,440,440); 
		hw.setHorizontalAlignment(SwingConstants.CENTER);
		hw.setVerticalAlignment(SwingConstants.CENTER); 
		hw.setVisible(false); // 숨겨놓기
		setPanel.add(hw); // 패널 위에 레이블 추가
		
		ImageIcon fest_icon = new ImageIcon("fest.gif");//'축제'이미지 생성
		fest = new JLabel(fest_icon);
		fest.setBounds(0,0,440,440); 
		fest.setHorizontalAlignment(SwingConstants.CENTER); 
		fest.setVerticalAlignment(SwingConstants.CENTER); 
		fest.setVisible(false); 
		setPanel.add(fest); 
		
		ImageIcon free_icon = new ImageIcon("free.gif");//'자유시간' 이미지 생성
		free = new JLabel(free_icon);
		free.setBounds(0,0,440,440); 
		free.setHorizontalAlignment(SwingConstants.CENTER);
		free.setVerticalAlignment(SwingConstants.CENTER); 
		free.setVisible(false);
		setPanel.add(free); 
	}
	
	public void hwResult(){ //과제 메소드
		hw.setVisible(true);
	}//hwResult()
	
	public void festResult(){ //축제 메소드
		fest.setVisible(true);
	}//festResult()
	
	public void freeResult(){//자유시간 메소드
		free.setVisible(true);
	}//freeResult()
	
	public void Reset(){ //리셋 메소드
		free.setVisible(false);
		fest.setVisible(false);
		hw.setVisible(false);
	}//Reset()
	
}