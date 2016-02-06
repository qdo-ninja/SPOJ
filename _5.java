package practice;

import java.util.Scanner;

public class _5 {
	public static void main(String[] args) throws java.lang.Exception{
		
		Scanner in = new Scanner(System.in);
		int tests = in.nextInt(); in.nextLine();
		
		while(tests-- > 0){
			StringBuilder n = new StringBuilder(in.nextLine());
			int mid_index = n.length() / 2;
			boolean increase = false;
			
			for(int i = 0; i <= mid_index; i++){
				int left = i;
				int right = n.length() - i - 1;
				int left_num = n.charAt(left) - '0';
				int right_num = n.charAt(right) - '0';
				
				if(left == right){//odd length end condition
					if(!increase){
						left_num++;
						n.setCharAt(i, (char)((left_num % 10) + '0'));
						if(left_num > 9) handle10(n, i);
					}
				}
				else if(right == mid_index){//even length end condition
					if(!increase){
						if(right_num >= left_num){
							left_num++;
							n.setCharAt(left, (char)((left_num % 10) + '0'));
							n.setCharAt(right, (char)((left_num % 10) + '0'));
							
							if(left_num > 9) handle10(n, i);		
						}
						else
							n.setCharAt(right,(char)(left_num + '0'));
					}
					break;
				}
				else{
					if(right_num < left_num)
						increase = true;
					else if(right_num > left_num)
						increase = false;
					
					n.setCharAt(right, (char)(left_num + '0'));
				}
			}
			System.out.println(n);
		}
		in.close();
	}
	
	public static void handle10(StringBuilder n, int i){
		int rev_i = i;
		int left_num = 9 + 1;
		int left = i;
		int right = n.length() - i - 1;
		
		while(left_num > 9){
			rev_i--;	
			
			if(rev_i < 0){
				n.setCharAt(left, (char)(1 + '0')); // old left at 0
				n.append(1);
				break;
			}
			
			left = rev_i;
			right = n.length() - rev_i - 1;
			
			left_num = n.charAt(left) - '0';
			left_num++;
			
			n.setCharAt(left, (char)((left_num % 10) + '0'));
			n.setCharAt(right, (char)((left_num % 10) + '0'));
		}		
	}
}
/* 
 * Algorithms
 * 
 * Def: right number is opposite to left number and must be the same to create Palindrome.
 * 
 * 1. Match right number to left number. Record in a boolean variable if current right number increases or decreases.
 * 
 * 2. When reach the last two number (even length) or the middle number (odd length),
 * if the most recent right number increases, we done!
 * (It doesn's matter if previous right numbers decreases or increases)
 * 
 * 
 * 3. If the most recent right number decreases, 
 *    I. Even length
 *        a. If current right number >= left number, 
 *        we need to increments left number by one, 
 *        and then match right number to left number.
 *    
 *        b. If current right number < left number,
 *        we just need to match right number to left number.
 *        
 *    II. Odd length
 *        Increment middle number by 1.
 *        
 * 4. If after incrementing a number, the number is higher than 9, which is 10,
 * we need a special handle.
 *    a. First we modulo the number with 10 to get 0.
 *    b. We get the two numbers to the left and to the right and increment both by 1.
 *    c. We repeat the process until the number is no longer bigger than 9 or out of length.
 *    d. If out of length, we add a 1 after and replace the first digit with 1. "99" --> "00" -- >"101"
 *    
 * */
