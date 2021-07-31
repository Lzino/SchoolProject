import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class testPanel extends JPanel {
	private JPanel setPanel;
	private JLabel lblTitle, lblMark,lblLogo;
	private JButton btnRandom, btnOdd, btnEven;
	private int nRandom;
	private int resultNumber;
	
	private GameListener gameL;
	
	public testPanel() {
		setPreferredSize(new Dimension(430,430));
		setBackground(Color.white);
		setLayout(null);
		
		gameL = new GameListener();
		
		setPanel = new JPanel();
		setPanel.setBounds(10,10,400,400);//location
		setPanel.setBackground(new Color(255,224,49));
		setPanel.setLayout(null);
		setPanel.setVisible(true);
		add(setPanel);
		
		
		ImageIcon MinigameTitle = new ImageIcon("Minigame-title.png");
		lblTitle=new JLabel("",MinigameTitle,SwingConstants.CENTER);
		lblTitle.setBounds(30,20,352,65);
		lblTitle.setVisible(true);
		setPanel.add(lblTitle);
		
		
		lblMark = new JLabel("?");
		lblMark.setBounds(70,70,70,70);
		lblMark.setFont(new Font("Verdana",Font.BOLD,10));
		lblMark.setHorizontalAlignment(SwingConstants.CENTER);
		lblMark.setVerticalAlignment(SwingConstants.CENTER);
		lblMark.setVisible(true);
		setPanel.add(lblMark);
		
		
		Font fntButton = new Font("Verdana",Font.PLAIN,15);
		
		btnRandom = new JButton("Coin Toss!"); //오른쪽패널에 버튼 생성합니다.
		btnRandom.setBounds(100,130,200,30);
		btnRandom.setFont(fntButton);
		btnRandom.addActionListener(gameL);
		btnRandom.setVisible(true);
		setPanel.add(btnRandom);
		
		ImageIcon Coin1=new ImageIcon("coin1.png");
		btnOdd = new JButton(Coin1);
		btnOdd.setBounds(60,200,120,120);
		btnOdd.setBorderPainted(false); // 버튼 경계선 제거
	    btnOdd.setFocusPainted(false); //포커스(선택했던 버튼 표시) 제거
		btnOdd.setContentAreaFilled(false);//버튼영역 배경 제거
		btnOdd.setEnabled(false);
		btnOdd.setVisible(true);
		btnOdd.addActionListener(gameL);
		setPanel.add(btnOdd);	
		
		ImageIcon Coin2=new ImageIcon("coin2.png");
		btnEven = new JButton(Coin2);
		btnEven.setBounds(220,200,120,120);
		btnEven.setBorderPainted(false); // 버튼 경계선 제거
	    btnEven.setFocusPainted(false); //포커스(선택했던 버튼 표시) 제거
		btnEven.setContentAreaFilled(false);
		btnEven.addActionListener(gameL);
		btnEven.setEnabled(false);
		btnEven.setVisible(true);
		setPanel.add(btnEven);	
		
		/*
		ImageIcon Cointosslogo = new ImageIcon("cointoss.gif");
		lblLogo=new JLabel("",Cointosslogo,SwingConstants.CENTER);
		lblLogo.setBounds(105,250,205,100);
		lblLogo.setLayout(null);
		setPanel.add(lblLogo);
		*/
		nRandom = 0;
		
		
	}//constructor
	
	public int getResult() {return resultNumber;}
	public void setReusult(int value) { resultNumber=value; }
	//get set
	
	public void rightResult(){
		 String result = "You are Right! (+30Money!)	"; 
      JOptionPane.showMessageDialog(null,result);
      resultNumber = 1;
      Reset();
   	}
	
	public void wrongResult(){
		 String result = "You are Wrong!"; 
      JOptionPane.showMessageDialog(null,result);
      resultNumber=0;
      Reset();
	}
	public void start(){
		setPanel.setVisible(true);
		lblTitle.setVisible(true);
		lblMark.setVisible(true);
		btnRandom.setVisible(true);
		btnOdd.setVisible(true); 
		btnEven.setVisible(true);	
		
	}
	public void Reset(){
		setPanel.setVisible(false);
		lblTitle.setVisible(false);
		lblMark.setVisible(false);
		lblLogo.setVisible(false);
		btnRandom.setVisible(false);
		btnOdd.setVisible(false); 
		btnEven.setVisible(false);	
	}
	
	
	private class GameListener implements ActionListener{
		
		public void actionPerformed(ActionEvent event){
		
		Object obj = event.getSource();
		
		if(obj == btnRandom){
			nRandom = (int)(Math.random()*50)+1;
			lblMark.setText("?");
			
			btnOdd.setEnabled(true);
			btnEven.setEnabled(true);
			btnRandom.setEnabled(false);
		} else if (obj == btnOdd ){
			if(nRandom%2==1){
				rightResult();
				//Reset();
			}else{
				wrongResult();
				//Reset();
			}
			Reset();
		} else if (obj == btnEven) {
			if(nRandom%2==0){
				rightResult();
				//Reset();
			}else{
				wrongResult();
				//Reset();
			}
			Reset();
		} //second elseif
		
	}//GameListener Class
		
	}//OddEvenPanel()
	
}//class OddEvenPanel