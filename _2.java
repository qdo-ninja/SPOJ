package practice;

import java.util.*;

class _2
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner in = new Scanner(System.in);
		
		int t = in.nextInt();
		while(t-- > 0){		
			segmentedSieve(in.nextInt(), in.nextInt());
			System.out.println();
		}
		in.close();
	}
	
	public static void segmentedSieve(int lower, int upper){
		lower = lower > 1 ? lower : 2;
		int sqrt = (int)Math.sqrt(upper);
		int len = sqrt;
		boolean[] primes = primesUpTo(sqrt);
		
		int current_upper = lower + (len - 1);
		
		while(true){		
			boolean[] segmented = new boolean[current_upper - lower + 1];
			
			for(int i = 2; i < primes.length; i++){
				if(primes[i]) continue;
				int start = (int)Math.ceil((double)lower / i) * i;
				if(start == i) start += i;
				for(int j = start; j <= current_upper; j += i)
					segmented[j - lower] = true;
			}
			
			for(int i = 0; i < segmented.length; i++)
				if(!segmented[i]) System.out.println(i + lower);
			
			if(current_upper == upper) break;
			
			lower += len;		
			current_upper += upper - lower + 1 < len ? upper - lower + 1: len;
		}
	}
	
	public static boolean[] primesUpTo(int n){
		boolean[] primes = new boolean[n + 1];
		long sqrt = (int)Math.sqrt(n);
		
		for(int i = 2; i <= n; i++){
			if(i > sqrt) break;
			if(primes[i] == true) continue;
			for(int j = i; j * i <= n; j++)				
				primes[i * j] = true;
		}
				
		return primes;		
	}
}