	package īī����;

	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.io.*;
	import java.awt.*;
	import java.net.Socket;
	import java.util.logging.Level;
	import java.util.logging.Logger;

import javax.swing.JPanel;

import com.google.gson.Gson;

import īī����.FindAction;

	public class MultiChatController implements Runnable {
		private Socket socket = null;
		BufferedReader inMsg; // �Է½�Ʈ��, �Է� �޽���. ���ۿ��� ����. 
		PrintWriter outMsg ;// ��½�Ʈ��, ��� �޽���. 
		private String ip = "127.0.0.1";//ip�� ���� ��Ʈ�� ����
		Gson gson; //Json
		private Message m; //�޽��� ����
		private Boolean status ;//���¿� ���� ����. Run()���� ����.
		Thread thread;//�޽��� ����, �߽��� ó���ϴ� ������.
		FindAction fa;
		public String find; 
		

		Logger logger; //�ΰ� ��ü 
		//�� Ŭ���� ���� ��ü
		private final MultiChatUI v;
		//������Ŭ���� ���� ��ü
		private final MultiChatData chatData; 
		
		public MultiChatController(MultiChatData chatData, MultiChatUI v){ //�𵨰� �� ��ü�� �Ķ���ͷ� �ϴ� ������.
			//�ΰ� ��ü �ʱ�ȭ
			logger = Logger.getLogger(this.getClass().getName());
			
			//�𵨰� �� Ŭ���� ����
			this.chatData = chatData;
			this.v = v;
			gson = new Gson();
			
			
		} //������
		
		public void appMain() { //UI�̺�Ʈ�� ȣ���Ͽ�  ó��
			//������ ��ü���� ������ ��ȭ�� ó���� UI ��ü �߰�
			chatData.addObj(v.msgOut);
			
			v.addButtonActionListener(new ActionListener() { //UI�� ��ư������ ȣ��
				@Override
				public void actionPerformed(ActionEvent e){
					Object obj = e.getSource();
					//�����ư ó��
					if(obj == v.exitButton){
						System.exit(0);
					}
					//�α��� ������ȯ
					else if(obj == v.loginButton){
						v.id = v.idInput.getText();
						v.outLabel.setText("��ȭ�� : " + v.id);
						v.cardLayout.show(v.tab, "logout");
						connectServer();
					}
					else if(obj == v.logoutButton){ //�α׾ƿ� ��������
						
						//�α׾Ƽ� �޼��� ����
						outMsg.println(gson.toJson(new Message(v.id,"","","logout"))); //���
						//��ȭâ Ŭ����
						v.msgOut.setText("");
						//�α��� �гη� ��ȯ
						v.cardLayout.show(v.tab, "login");
						outMsg.close();
						try{
							inMsg.close();
							socket.close();
						} catch (IOException ex) {
							ex.printStackTrace();
						}
						status = false;
					} else if (obj == v.msgInput){ //�Ϲݸ޼���
						//�޼��� ����
						outMsg.println(gson.toJson(new Message(v.id, "", v.msgInput.getText(),"msg")));
						//�Է� â Ŭ����
						v.msgInput.setText("");
					} else if (obj == v.searchButton){//ã�� ��ư ������
						fa = new FindAction(v.msgOut.getText(),v,v.msgInput.getText());
						//ã�� �׼� �ߵ�.msgOut�� �ε����� ã�Ƽ� msgInput�� ���̸�ŭ ��ϼ�������.
					}
					
				} //actionPerformed()
			});//v.addButtonActionListener()
		}//appMain()
		
		
		public void connectServer() { //���������� ���� �޼ҵ�
			try {
				//���� ����
				socket = new Socket(ip,8888);
				logger.log(Level.INFO, "[Client]Server ���� ����");
				
				//����� ��Ʈ�� ����
				inMsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				outMsg = new PrintWriter(socket.getOutputStream(), true);
				System.out.println("Client >> 1");
				//������ �α��� �޼��� ����
				m = new Message(v.id, "", "", "login");
				outMsg.println(gson.toJson(m));
				
				//�޼��� ������ ���� ������ ����
				thread = new Thread(this); //this?
				thread.start();
				
			}
			catch (Exception e){
				logger.log(Level.WARNING, "[MultiChatUI]connectServer() Exception �߻�!!");
				e.printStackTrace();
			}
			
		}
		
		public static void main(String[] args){ //���θ޼ҵ�
			MultiChatController app = new MultiChatController(new MultiChatData(), new MultiChatUI());
			app.appMain();
		}

		@Override
		public void run() { //�����带 ���������� �޼ҵ�
			String msg;
			status = true;
			
			while(status){ //��� ���� �����鼭 ���� ��ٸ�
				try{
					//�޼��� ���� �� �Ľ�
					msg = inMsg.readLine();
					m = gson.fromJson(msg, Message.class);
					
					//MulticChatData ��ü�� ������ ����
					chatData.refreshData(m.getId() + ">" + m.getMsg() + "\n");
					
					//Ŀ���� ���� ��ȭ �޼����� ǥ��
					v.msgOut.setCaretPosition(v.msgOut.getDocument().getLength());
				} catch(IOException e){
					logger.log(Level.WARNING, "[MultiChatUI]�޼��� ��Ʈ�� ����!!");
				}
			}
			logger.info("[MultiChatUI]"+thread.getName()+"�޽��� ���� ������ �����!!");
		} //������ ����
	}

	


