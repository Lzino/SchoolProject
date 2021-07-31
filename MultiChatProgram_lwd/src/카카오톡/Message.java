package 카카오톡;

public class Message { //메세지를 자바객체로 변환해주는 역할
	
	private String id; // 아이디
	private String passwd; //비번
	private String msg;//채팅 메서지
	private String type; // 메시지 유형
	
	public Message() {
		id = null;
		passwd = null;
		msg = null;
		type = null;
	} //디폴트 생성자
	
	
	public Message(String i, String p, String m, String t) {
		id = i;
		passwd = p;
		msg = m;
		type = t;
	} //입력 받은 생성자


	public String getId(){ return id;}
	public String getPasswd() {return passwd;}
	public String getMsg() {return msg;}
	public String getType() {return type;}
	
	public void setId(String s){id = s;}
	public void setPasswd(String s){passwd = s;}
	public void setMsg(String s){msg = s;}
	public void setType(String s) {type = s;}

}
