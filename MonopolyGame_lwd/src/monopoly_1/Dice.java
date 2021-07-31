package monopoly_1;
// 주사위를 만드는 클래스
public class Dice {
	
	private int Dice1,Dice2,bigDice; // 주사위1, 주사위2, 두주사위의 합
	public Dice() {
		Dice1=Dice2=bigDice=0; //처음눈은 0
	}
	// 각주사위눈을 반환하는 get함수
	public int getDice1() { return Dice1; }
	public int getDice2() { return Dice2; }
	public int getBigDice() { return bigDice; }
	
	//주사위 1과 주사위2에 무작위눈을 생성후 합을 반환
	public int rollDice() {
		Dice1 = (int)(Math.random()*6)+1;
		Dice2 = (int)(Math.random()*6)+1;
		bigDice=Dice1+Dice2;
		return bigDice;
	}
	
	
}
