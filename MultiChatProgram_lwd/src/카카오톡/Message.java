package īī����;

public class Message { //�޼����� �ڹٰ�ü�� ��ȯ���ִ� ����
	
	private String id; // ���̵�
	private String passwd; //���
	private String msg;//ä�� �޼���
	private String type; // �޽��� ����
	
	public Message() {
		id = null;
		passwd = null;
		msg = null;
		type = null;
	} //����Ʈ ������
	
	
	public Message(String i, String p, String m, String t) {
		id = i;
		passwd = p;
		msg = m;
		type = t;
	} //�Է� ���� ������


	public String getId(){ return id;}
	public String getPasswd() {return passwd;}
	public String getMsg() {return msg;}
	public String getType() {return type;}
	
	public void setId(String s){id = s;}
	public void setPasswd(String s){passwd = s;}
	public void setMsg(String s){msg = s;}
	public void setType(String s) {type = s;}

}
