package practice;
import java.util.*;

public class _1 {
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner in = new Scanner(System.in);
		int a = 0;
		while((a = in.nextInt()) != 42)
			System.out.println(a);	
		in.close();
	}
}
