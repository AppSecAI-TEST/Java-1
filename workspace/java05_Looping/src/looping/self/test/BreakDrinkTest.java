package looping.self.test;

public class BreakDrinkTest {
	public static void main(String[] args) {
		for(int beer = 0; beer < 50; beer++){
			if(beer == 10) {
				System.out.println("Bye");
				break;
			} else System.out.println("Current : " + beer + "beers !");
		}
		// for 문 beer --> 50잔 까지 반복 마심
		//치사량 : 10잔
		// 10잔째 --> break;
		// 집 감
	}//main
}//class
















