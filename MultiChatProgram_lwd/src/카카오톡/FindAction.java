package 카카오톡;


public class FindAction {
	int first; //찾는 문자의 인덱스 첫번째 값
	int last; //찾는 문자의 인덱스 마지막 값
	
	public FindAction(String text, MultiChatUI v, String findword){
		first = text.indexOf(findword);
		//정수형 변수에 스트링의 indexof (텍스트파일 인자값) //위치알려줌
		last = first + findword.length(); //첫번째 순서에서 텍스트 길이 더해주기
		System.out.println(first);//출력
		System.out.println(last);
		
		block(first, last, v); //블록설정해주는 매소드
	}
	void block(int first, int last, MultiChatUI v) { //블록 설정해주는 메소드
		v.msgOut.requestFocus(); //포커스 요청
		v.msgOut.select(first, last);
	}
}
