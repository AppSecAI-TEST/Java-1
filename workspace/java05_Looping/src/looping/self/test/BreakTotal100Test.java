package looping.self.test;

public class BreakTotal100Test {
	public static void main(String[] args) {
		int i = 0;
		int sum = 0;
		
		while(true){
			if(i >= 100) break;
			i++;
			sum += i;
			System.out.println("Cuurent i : " + i + "\nCurrent Sum : " + sum);
		}
		//while 문// 1씩 증가
		// 합산 값이 100 넘어서면 반복문 빠져나옴
	
	}
}

















