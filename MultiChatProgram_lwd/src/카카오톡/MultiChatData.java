package īī����;

import javax.swing.*;


public class MultiChatData { //������Ʈ
	
	private JTextArea msgOut; 

	public void addObj(JComponent comp) { //(�ϴ���) ���� �޼����� ����
		msgOut = (JTextArea) comp;

	}
	public void refreshData(String msg){ //�������� ���ִ� ����
		msgOut.append(msg); //������ �ؽ�Ʈ�� �߰�����
	}
	
	public JTextArea getMsg() {return msgOut;}
	public void setMsg(JTextArea s){ msgOut = s ;}

}
