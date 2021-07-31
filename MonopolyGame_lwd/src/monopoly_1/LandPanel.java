package monopoly_1;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class LandPanel extends JPanel{

	private int mX, mY, width, height; // 레이블의 위치와 크기를 저장
	private ImageIcon landImg;	// 토지 이미지

	public JLabel[] lblLand; 
	private String owner; // 땅주인
	private int buildingLvl; //0 없음, 1 토지만구입 , 2 건물+1, 3 건물렙+2, 4건물랩+3  
	
	// 패널의 위치n과 크기를 받아와 설정, 위치지정, 배치레이아웃설정
	public LandPanel(int n, int x, int y, int w, int h) {
		mX = x; mY = y; width = w; height = h;
		setPreferredSize(new Dimension(width, height));
		setBounds(mX, mY, width, height);
		setBackground(Color.white);
		setLayout(null);
		
		lblLand = new JLabel[32];
		owner="";  //초기에 주인이없음
		buildingLvl=0;
	
		// 경계선 그리기
		if(n>0 && n<9) setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.BLACK));
		if(n>=9 && n<17) setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.BLACK));
		if(n>=17 && n<25) setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
		if(n>=25 && n<32) setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.BLACK));
		if(n==0) setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.BLACK));
		
		// 패널에 올릴 이미지를 받아와 레이블에 저장, 
		landImg = new ImageIcon("D:\\workspace\\monopoly_1124 revi7\\"+LandConstants.NAME[n]+".png");
		lblLand[n] = new JLabel(landImg);
		
		// 받아온 위치 n을 파악해서 대응되는곳에 위치지정
		if(n==0) { lblLand[n].setBounds(1, 1, 129, 129); }
		if(n>0 && n<8 && n!=4) { lblLand[n].setBounds(0, 1, 129, 99); }
		if(n==4) { lblLand[n].setBounds(0, 1, 129, 99); }		
		if(n==8) { lblLand[n].setBounds(0, 1, 129, 129); }
		if(n>8 && n<16 && n!=12) { lblLand[n].setBounds(0, 0, 99, 129); }
		if(n==12) { lblLand[n].setBounds(0, 0, 99, 129); }
		if(n==16) { lblLand[n].setBounds(0, 0, 129, 129); }
		if(n>16 && n<24 && n!=20) { lblLand[n].setBounds(1, 0, 129, 99); }
		if(n==24) { lblLand[n].setBounds(1, 0, 128, 129); }
		if(n==20) { lblLand[n].setBounds(1, 0, 129, 99); }
		if(n>24 && n<32 && n!=28) { lblLand[n].setBounds(1, 1, 99, 129); }
		if(n==28) { lblLand[n].setBounds(1, 1, 99, 129); }
		add(lblLand[n]);
		
	}	
	
	// 각종 변수를 반환하고 설정하는 get set
	public int getBuildingLvl() { return buildingLvl; }
	public String getOwner() { return owner; }
	public void setOwner(String name) { owner=name; } 
	public void setBuildingLvl(int value) { buildingLvl = value; }
	
	
}
