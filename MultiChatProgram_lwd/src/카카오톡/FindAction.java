package īī����;


public class FindAction {
	int first; //ã�� ������ �ε��� ù��° ��
	int last; //ã�� ������ �ε��� ������ ��
	
	public FindAction(String text, MultiChatUI v, String findword){
		first = text.indexOf(findword);
		//������ ������ ��Ʈ���� indexof (�ؽ�Ʈ���� ���ڰ�) //��ġ�˷���
		last = first + findword.length(); //ù��° �������� �ؽ�Ʈ ���� �����ֱ�
		System.out.println(first);//���
		System.out.println(last);
		
		block(first, last, v); //��ϼ������ִ� �żҵ�
	}
	void block(int first, int last, MultiChatUI v) { //��� �������ִ� �޼ҵ�
		v.msgOut.requestFocus(); //��Ŀ�� ��û
		v.msgOut.select(first, last);
	}
}
