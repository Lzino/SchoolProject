package monopoly_1;

import javax.swing.*;

public class MonopolyExam { //������� ���� Ŭ����

	public static void main(String[] args) { //public static���� ����
		JFrame frame = new JFrame("User Defined Panel");//������ ����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //JFrame ���� ���� ���� �޼ҵ�
		
		InitialPanel Mpanel = new InitialPanel(); //�����г� ��ü�� ����
		
		frame.getContentPane().add(Mpanel); //�г��� �����ӿ� �߰�
		frame.pack(); //������ ������ ����
		frame.setVisible(true); //������ ���̰� �ϱ�
	}
}
