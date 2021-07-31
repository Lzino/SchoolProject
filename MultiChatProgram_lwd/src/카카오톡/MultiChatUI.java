package īī����;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class MultiChatUI extends JFrame{
	private JPanel loginPanel; //�α��� �г�
	JButton loginButton; //�α��� ��ư
	JLabel inLabel, outLabel, msName; //����·��̺�
	JTextField idInput; //���̵��Է�
	private JPanel logoutPanel; //�α׾ƿ� �г�
	JButton logoutButton; //�α׾ƿ� ��ư 
	private JPanel msgPanel; //�ż����г�
	protected JTextField msgInput; //�޼��� �Է�
	protected JButton exitButton; // �����ư
	protected JTextArea msgOut; //�޼��� â
	JButton searchButton;

	
	//ȭ�� ������ȯ�� ���� ī�巹�̾ƿ�
	Container tab;
	CardLayout cardLayout;
	String id;
	JScrollPane scroll;
	
	
	public MultiChatUI() {

		
		//���� ������ ����
		super("::��Ƽê::");
		
		setBackground(Color.white); //����
		setPreferredSize(new Dimension(700,500)); //������ ����
		
		
		//�α��� �г� ȭ�� ����
		loginPanel = new JPanel();
		
		//�α��� �г� ���̾ƿ� ����
		loginPanel.setLayout(new BorderLayout());
		
		//�α��� �Է� �ʵ�/��ư ����
		idInput = new JTextField(15);
		loginButton = new JButton("�α���");
		
		//�α��� �гο� ���� ����
		inLabel = new JLabel("��ȭ�� ");
		loginPanel.add(inLabel, BorderLayout.WEST);
		loginPanel.add(idInput, BorderLayout.CENTER);
		loginPanel.add(loginButton, BorderLayout.EAST);
		
		
		//�α׾ƿ� �г� ����
		logoutPanel = new JPanel();
		
		//�α׾ƿ� �г� ���̾ƿ� ����
		logoutPanel.setLayout(new BorderLayout());
		
		//�α׾ƿ� �Է� �ʵ�/��ư ����
		outLabel = new JLabel();
		logoutButton = new JButton("�α׾ƿ�");
		
		//�α׾ƿ� �гο� ���� ����
		logoutPanel.add(outLabel, BorderLayout.CENTER);
		logoutPanel.add(logoutButton, BorderLayout.EAST);
		
		
		//�޼��� �Է� �г� ����
		msgPanel = new JPanel();
		
		//�޼��� �Է� �ʵ�/��ư ����
		msgPanel.setLayout(new BorderLayout());
		exitButton = new JButton("����");
		searchButton = new JButton("ã��");
		msgInput = new JTextField(15);
		
		//�޼��� ���� ����
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
		//ī�巹�̾ƿ� �����̳�
		add(tab,BorderLayout.NORTH);

		
		//�޼��� ��¿��� �ʱ�ȭ
		msgOut = new JTextArea("",10,30);
		msgOut.setEditable(false);
		//��ũ��
		scroll = new JScrollPane(msgOut);
		add(scroll);
		
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);   
	      scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	      
	      
		pack(); //������ ��������� �����Ӿ��� �� ���� !!
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //���� ���
	} //MultiChatUI()

	
	public void addButtonActionListener(ActionListener actionListener){
		//�̺�Ʈ ������ ���
		loginButton.addActionListener(actionListener);
		logoutButton.addActionListener(actionListener);
		exitButton.addActionListener(actionListener);
		msgInput.addActionListener(actionListener);
		searchButton.addActionListener(actionListener);
	} //addButtonActionListener()
	
	

}
