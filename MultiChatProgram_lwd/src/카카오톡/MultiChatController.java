	package 카카오톡;

	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.io.*;
	import java.awt.*;
	import java.net.Socket;
	import java.util.logging.Level;
	import java.util.logging.Logger;

import javax.swing.JPanel;

import com.google.gson.Gson;

import 카카오톡.FindAction;

	public class MultiChatController implements Runnable {
		private Socket socket = null;
		BufferedReader inMsg; // 입력스트림, 입력 메시지. 버퍼에서 읽음. 
		PrintWriter outMsg ;// 출력스트림, 출력 메시지. 
		private String ip = "127.0.0.1";//ip를 받을 스트링 변수
		Gson gson; //Json
		private Message m; //메시지 변수
		private Boolean status ;//상태에 대한 변수. Run()에서 사용됨.
		Thread thread;//메시지 수신, 발신을 처리하는 스레드.
		FindAction fa;
		public String find; 
		

		Logger logger; //로거 객체 
		//뷰 클래스 참조 객체
		private final MultiChatUI v;
		//데이터클래스 참조 객체
		private final MultiChatData chatData; 
		
		public MultiChatController(MultiChatData chatData, MultiChatUI v){ //모델과 뷰 객체를 파라미터로 하는 생성자.
			//로거 객체 초기화
			logger = Logger.getLogger(this.getClass().getName());
			
			//모델과 뷰 클래스 참조
			this.chatData = chatData;
			this.v = v;
			gson = new Gson();
			
			
		} //생성자
		
		public void appMain() { //UI이벤트를 호출하여  처리
			//데이터 객체에서 데이터 변화를 처리할 UI 객체 추가
			chatData.addObj(v.msgOut);
			
			v.addButtonActionListener(new ActionListener() { //UI의 버튼리스너 호출
				@Override
				public void actionPerformed(ActionEvent e){
					Object obj = e.getSource();
					//종료버튼 처리
					if(obj == v.exitButton){
						System.exit(0);
					}
					//로그인 상태전환
					else if(obj == v.loginButton){
						v.id = v.idInput.getText();
						v.outLabel.setText("대화명 : " + v.id);
						v.cardLayout.show(v.tab, "logout");
						connectServer();
					}
					else if(obj == v.logoutButton){ //로그아웃 눌렀을때
						
						//로그아숫 메세지 전송
						outMsg.println(gson.toJson(new Message(v.id,"","","logout"))); //고민
						//대화창 클리어
						v.msgOut.setText("");
						//로그인 패널로 전환
						v.cardLayout.show(v.tab, "login");
						outMsg.close();
						try{
							inMsg.close();
							socket.close();
						} catch (IOException ex) {
							ex.printStackTrace();
						}
						status = false;
					} else if (obj == v.msgInput){ //일반메세지
						//메세지 전송
						outMsg.println(gson.toJson(new Message(v.id, "", v.msgInput.getText(),"msg")));
						//입력 창 클리어
						v.msgInput.setText("");
					} else if (obj == v.searchButton){//찾기 버튼 누를때
						fa = new FindAction(v.msgOut.getText(),v,v.msgInput.getText());
						//찾는 액션 발동.msgOut의 인덱스를 찾아서 msgInput의 길이만큼 블록설정해줌.
					}
					
				} //actionPerformed()
			});//v.addButtonActionListener()
		}//appMain()
		
		
		public void connectServer() { //서버접속을 위한 메소드
			try {
				//소켓 생성
				socket = new Socket(ip,8888);
				logger.log(Level.INFO, "[Client]Server 연결 성공");
				
				//입출력 스트림 생성
				inMsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				outMsg = new PrintWriter(socket.getOutputStream(), true);
				System.out.println("Client >> 1");
				//서버에 로그인 메세지 전달
				m = new Message(v.id, "", "", "login");
				outMsg.println(gson.toJson(m));
				
				//메세지 수신을 위한 스레드 생성
				thread = new Thread(this); //this?
				thread.start();
				
			}
			catch (Exception e){
				logger.log(Level.WARNING, "[MultiChatUI]connectServer() Exception 발생!!");
				e.printStackTrace();
			}
			
		}
		
		public static void main(String[] args){ //메인메소드
			MultiChatController app = new MultiChatController(new MultiChatData(), new MultiChatUI());
			app.appMain();
		}

		@Override
		public void run() { //스레드를 돌리기위한 메소드
			String msg;
			status = true;
			
			while(status){ //계속 루프 돌리면서 연결 기다림
				try{
					//메세지 수신 및 파싱
					msg = inMsg.readLine();
					m = gson.fromJson(msg, Message.class);
					
					//MulticChatData 객체로 데이터 갱신
					chatData.refreshData(m.getId() + ">" + m.getMsg() + "\n");
					
					//커서를 현재 대화 메세지에 표시
					v.msgOut.setCaretPosition(v.msgOut.getDocument().getLength());
				} catch(IOException e){
					logger.log(Level.WARNING, "[MultiChatUI]메세지 스트림 종료!!");
				}
			}
			logger.info("[MultiChatUI]"+thread.getName()+"메시지 수신 스레드 종료됨!!");
		} //스레드 종료
	}

	


