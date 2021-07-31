	package īī����;

	import java.net.*;
	import java.util.*;
	import java.util.logging.Logger;
	import com.google.gson.Gson;

	import java.io.*;


	public class MultiChatServer {
		
		//�������� �� Ŭ���̾�Ʈ �������
		private ServerSocket ss = null;
		private Socket s = null;
		
		//����� Ŭ���̾�Ʈ �����带 �����ϴ� ArrayList .ChatThread �����ؾ���.
		ArrayList<ChatThread> chatThreads = new ArrayList<ChatThread>();
		
		//�ΰ� ��ü
		Logger logger;
		
		//��Ƽ ä�� ���� ���α׷� �κ�
		public void start(){
			logger = Logger.getLogger(this.getClass().getName());
			
			try{
				//�������� ����
				ss = new ServerSocket(8888);
				logger.info("MultiChatServer start");
				
				//���� ������ ���鼭 Ŭ���̾�Ʈ ������ ��ٸ���.
				while(true){
					s = ss.accept();
					//����� Ŭ���̾�Ʈ�� ���� ������ Ŭ���� ����
					ChatThread chat = new ChatThread();
					//Ŭ���̾�Ʈ ����Ʈ �߰�
					chatThreads.add(chat);
					//������ ����
					chat.start();
				} //try()
			} catch(Exception e) {
				logger.info("[MultiChatServer]start() Exception �߻�!!");
				e.printStackTrace();
			}
			
		}//start()
		
		public static void main(String[] args){
			MultiChatServer server = new MultiChatServer();
			server.start();
			
		}//main()
		class ChatThread extends Thread {
			//���Ÿ޼��� �� �Ľ� �޼��� ó���� ���� ��������
			String msg;
			
			//�޼��� ��ü ����
			Message m = new Message();
			
			//Json �ļ� �ʱ�ȭ
			Gson gson = new Gson();
			
			//����� ��Ʈ��
			private BufferedReader inMsg = null;
			private PrintWriter outMsg = null;
			@Override
			public void run() { //������ �ٽ� �޼���. �޼��� ���� �� ó��
				Boolean status = true; 
				
				try{
				//���� ������ true�̸� ������ ���鼭 ����ڿ��Լ� ���ŵ� �޼��� ó��
					//����� ��Ʈ�� ����
					inMsg = new BufferedReader(new InputStreamReader(s.getInputStream()));
					outMsg = new PrintWriter(s.getOutputStream(), true);	
					
				while(status){
					//���ŵ� �޼����� msg ������ ����
					msg = inMsg.readLine();
					//Json�޼����� Message ��ü�� ����
					m = gson.fromJson(msg, Message.class);
				
				//�Ľ̵� ���ڿ� �迭�� �ι�° ��Ұ��� ���� ó��
				//�α׾ƿ� �޼����϶�
				if(m.getType().equals("logout")){
					chatThreads.remove(this);
					msgSendAll(gson.toJson(new Message(m.getId(),"", "���� �����߽��ϴ�.", "server")));
					//�ش� Ŭ���̾�Ʈ ������ ����� status�� false�� ����
					status = false;
				} 
				//�α��� �޼����϶�
				else if (m.getType().equals("login")){
					
					msgSendAll(gson.toJson(new Message(m.getId(),"","���� �α����߽��ϴ�.","server")));
				}
				//�Ϲݸ޼����϶�
				else { msgSendAll(msg);}
			}
				this.interrupt();
				logger.info(this.getName() + "�����!!");
		}
				catch(Exception e){e.printStackTrace();}}//run ()
		
			void msgSendAll(String msg){ //����� ��� ����ڿ��� �޼��� ���� , �߰�
				for(ChatThread ct : chatThreads){//�ѷ��ְ�
					ct.outMsg.println(msg);  //����մϴ�.
				}//for()
			}//msgSendAll()
		

		}//ChatThread
		}

	

	


