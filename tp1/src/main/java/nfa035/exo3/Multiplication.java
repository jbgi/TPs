package nfa035.exo3;

public class Multiplication {
	public static int multiplier(int a, int b) {
		int r= 0;
		int n= 0;
		while (n < b) {
			r= r + a;
			n= n++;
		}
		return r;		
	}
	
	public static void main(String[] args) {
		System.out.println("On calcule :");
		System.out.println(multiplier(3, 5));
	}
}
