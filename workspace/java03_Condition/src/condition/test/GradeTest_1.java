package condition.test;

public class GradeTest_1 {

	public static void main(String[] args) {
		int grade = Integer.parseInt(args[0]);
		
		// int grade = 89;
		if(grade > 90 && grade <= 100){
			System.out.println('A');
			
		} else if(grade > 80 && grade <= 90){
			System.out.println('B');
		} else if(grade > 70 && grade <= 80) {
			System.out.println('C');
		} else {
			System.out.println('F');
		}
	}
	

}