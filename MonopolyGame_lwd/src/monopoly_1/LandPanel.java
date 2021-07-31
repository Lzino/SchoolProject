package monopoly_1;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class LandPanel extends JPanel{

	private int mX, mY, width, height; // ���̺��� ��ġ�� ũ�⸦ ����
	private ImageIcon landImg;	// ���� �̹���

	public JLabel[] lblLand; 
	private String owner; // ������
	private int buildingLvl; //0 ����, 1 ���������� , 2 �ǹ�+1, 3 �ǹ���+2, 4�ǹ���+3  
	
	// �г��� ��ġn�� ũ�⸦ �޾ƿ� ����, ��ġ����, ��ġ���̾ƿ�����
	public LandPanel(int n, int x, int y, int w, int h) {
		mX = x; mY = y; width = w; height = h;
		setPreferredSize(new Dimension(width, height));
		setBounds(mX, mY, width, height);
		setBackground(Color.white);
		setLayout(null);
		
		lblLand = new JLabel[32];
		owner="";  //�ʱ⿡ �����̾���
		buildingLvl=0;
	
		// ��輱 �׸���
		if(n>0 && n<9) setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.BLACK));
		if(n>=9 && n<17) setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.BLACK));
		if(n>=17 && n<25) setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
		if(n>=25 && n<32) setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.BLACK));
		if(n==0) setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.BLACK));
		
		// �гο� �ø� �̹����� �޾ƿ� ���̺� ����, 
		landImg = new ImageIcon("D:\\workspace\\monopoly_1124 revi7\\"+LandConstants.NAME[n]+".png");
		lblLand[n] = new JLabel(landImg);
		
		// �޾ƿ� ��ġ n�� �ľ��ؼ� �����Ǵ°��� ��ġ����
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
	
	// ���� ������ ��ȯ�ϰ� �����ϴ� get set
	public int getBuildingLvl() { return buildingLvl; }
	public String getOwner() { return owner; }
	public void setOwner(String name) { owner=name; } 
	public void setBuildingLvl(int value) { buildingLvl = value; }
	
	
}
