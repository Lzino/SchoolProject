package 카카오톡;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class MultiChatUI extends JFrame{
	private JPanel loginPanel; //로그인 패널
	JButton loginButton; //로그인 버튼
	JLabel inLabel, outLabel, msName; //입출력레이블
	JTextField idInput; //아이디입력
	private JPanel logoutPanel; //로그아웃 패널
	JButton logoutButton; //로그아웃 버튼 
	private JPanel msgPanel; //매세지패널
	protected JTextField msgInput; //메세지 입력
	protected JButton exitButton; // 종료버튼
	protected JTextArea msgOut; //메세지 창
	JButton searchButton;

	
	//화면 구성전환을 위한 카드레이아웃
	Container tab;
	CardLayout cardLayout;
	String id;
	JScrollPane scroll;
	
	
	public MultiChatUI() {

		
		//메인 프레임 구성
		super("::멀티챗::");
		
		setBackground(Color.white); //배경색
		setPreferredSize(new Dimension(700,500)); //사이즈 설정
		
		
		//로그인 패널 화면 구성
		loginPanel = new JPanel();
		
		//로그인 패널 레이아웃 설정
		loginPanel.setLayout(new BorderLayout());
		
		//로그인 입력 필드/버튼 생성
		idInput = new JTextField(15);
		loginButton = new JButton("로그인");
		
		//로그인 패널에 위젯 구성
		inLabel = new JLabel("대화명 ");
		loginPanel.add(inLabel, BorderLayout.WEST);
		loginPanel.add(idInput, BorderLayout.CENTER);
		loginPanel.add(loginButton, BorderLayout.EAST);
		
		
		//로그아웃 패널 구성
		logoutPanel = new JPanel();
		
		//로그아웃 패널 레이아웃 설정
		logoutPanel.setLayout(new BorderLayout());
		
		//로그아웃 입력 필드/버튼 생성
		outLabel = new JLabel();
		logoutButton = new JButton("로그아웃");
		
		//로그아웃 패널에 위젯 구성
		logoutPanel.add(outLabel, BorderLayout.CENTER);
		logoutPanel.add(logoutButton, BorderLayout.EAST);
		
		
		//메세지 입력 패널 생성
		msgPanel = new JPanel();
		
		//메세지 입력 필드/버튼 생성
		msgPanel.setLayout(new BorderLayout());
		exitButton = new JButton("종료");
		searchButton = new JButton("찾기");
		msgInput = new JTextField(15);
		
		//메세지 위젯 구성
		this.add(msgPanel,BorderLayout.SOUTH);
		msgPanel.add(msgInput,BorderLayout.CENTER);
		msgPanel.add(exitButton,BorderLayout.EAST);
		msgPanel.add(searchButton, BorderLayout.WEST);
		
		
	
		tab = new JPanel();
		cardLayout = new CardLayout();
		tab.setLayout(cardLayout);
		tab.add(loginPanel, "login");
		tab.add(logoutPanel, "logout");
		cardLayout.show(tab, "login");
		//카드레이아웃 컨테이너
		add(tab,BorderLayout.NORTH);

		
		//메세지 출력영역 초기화
		msgOut = new JTextArea("",10,30);
		msgOut.setEditable(false);
		//스크롤
		scroll = new JScrollPane(msgOut);
		add(scroll);
		
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);   
	      scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	      
	      
		pack(); //앞으론 까먹지말고 프레임쓸땐 꼭 쓰기 !!
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //끄기 기능
	} //MultiChatUI()

	
	public void addButtonActionListener(ActionListener actionListener){
		//이벤트 리스너 등록
		loginButton.addActionListener(actionListener);
		logoutButton.addActionListener(actionListener);
		exitButton.addActionListener(actionListener);
		msgInput.addActionListener(actionListener);
		searchButton.addActionListener(actionListener);
	} //addButtonActionListener()
	
	

}
