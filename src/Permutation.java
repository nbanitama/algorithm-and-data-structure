import java.util.Arrays;
import java.util.Scanner;

public class Permutation {

	private static void generate(char[] data, int i) {
		for(int j=i ; j<data.length ; j++) {
			generate(swap(data, i, j), i+1);
		}
		if(i == data.length-1)
			System.out.println(data);
	}
	
	private static char[] swap (char[] data, int i, int j) {
		char[] temp = Arrays.copyOf(data, data.length);
		temp[i] = data[j];
		temp[j] = data[i];
		return temp;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		generate(sc.next().toCharArray(), 0);
	}

}
