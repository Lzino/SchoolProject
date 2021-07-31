	package 카카오톡;

	import java.net.*;
	import java.util.*;
	import java.util.logging.Logger;
	import com.google.gson.Gson;

	import java.io.*;


	public class MultiChatServer {
		
		//서버소켓 및 클라이언트 연결소켓
		private ServerSocket ss = null;
		private Socket s = null;
		
		//연결된 클라이언트 스레드를 관리하는 ArrayList .ChatThread 구현해야함.
		ArrayList<ChatThread> chatThreads = new ArrayList<ChatThread>();
		
		//로거 객체
		Logger logger;
		
		//멀티 채팅 메인 프로그램 부분
		public void start(){
			logger = Logger.getLogger(this.getClass().getName());
			
			try{
				//서버소켓 생성
				ss = new ServerSocket(8888);
				logger.info("MultiChatServer start");
				
				//무한 루프를 돌면서 클라이언트 연결을 기다린다.
				while(true){
					s = ss.accept();
					//연결된 클라이언트에 대해 스레드 클래스 생성
					ChatThread chat = new ChatThread();
					//클라이언트 리스트 추가
					chatThreads.add(chat);
					//스레드 시작
					chat.start();
				} //try()
			} catch(Exception e) {
				logger.info("[MultiChatServer]start() Exception 발생!!");
				e.printStackTrace();
			}
			
		}//start()
		
		public static void main(String[] args){
			MultiChatServer server = new MultiChatServer();
			server.start();
			
		}//main()
		class ChatThread extends Thread {
			//수신메세지 및 파싱 메세지 처리를 위한 변수선언
			String msg;
			
			//메세지 객체 생성
			Message m = new Message();
			
			//Json 파서 초기화
			Gson gson = new Gson();
			
			//입출력 스트림
			private BufferedReader inMsg = null;
			private PrintWriter outMsg = null;
			@Override
			public void run() { //스레드 핵심 메서드. 메세지 매핑 및 처리
				Boolean status = true; 
				
				try{
				//상태 정보가 true이면 루프를 돌면서 사용자에게서 수신된 메세지 처리
					//입출력 스트림 생성
					inMsg = new BufferedReader(new InputStreamReader(s.getInputStream()));
					outMsg = new PrintWriter(s.getOutputStream(), true);	
					
				while(status){
					//수신된 메세지를 msg 변수에 저장
					msg = inMsg.readLine();
					//Json메세지를 Message 객체로 매핑
					m = gson.fromJson(msg, Message.class);
				
				//파싱된 문자열 배열의 두번째 요소값에 따라 처리
				//로그아웃 메세지일때
				if(m.getType().equals("logout")){
					chatThreads.remove(this);
					msgSendAll(gson.toJson(new Message(m.getId(),"", "님이 종료했습니다.", "server")));
					//해당 클라이언트 스레드 종료로 status를 false로 설정
					status = false;
				} 
				//로그인 메세지일때
				else if (m.getType().equals("login")){
					
					msgSendAll(gson.toJson(new Message(m.getId(),"","님이 로그인했습니다.","server")));
				}
				//일반메세지일때
				else { msgSendAll(msg);}
			}
				this.interrupt();
				logger.info(this.getName() + "종료됨!!");
		}
				catch(Exception e){e.printStackTrace();}}//run ()
		
			void msgSendAll(String msg){ //연결된 모든 사용자에게 메세지 전달 , 중계
				for(ChatThread ct : chatThreads){//뿌려주고
					ct.outMsg.println(msg);  //출력합니다.
				}//for()
			}//msgSendAll()
		

		}//ChatThread
		}

	

	


