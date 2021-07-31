package 카카오톡;

import javax.swing.*;


public class MultiChatData { //업데이트
	
	private JTextArea msgOut; 

	public void addObj(JComponent comp) { //(일단은) 뷰의 메세지를 받음
		msgOut = (JTextArea) comp;

	}
	public void refreshData(String msg){ //리프레쉬 해주는 역할
		msgOut.append(msg); //기존의 텍스트에 추가해줌
	}
	
	public JTextArea getMsg() {return msgOut;}
	public void setMsg(JTextArea s){ msgOut = s ;}

}
