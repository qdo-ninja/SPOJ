package practice;

import java.util.*;

public class _4{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner in = new Scanner(System.in);
		int test = in.nextInt(); in.nextLine();
		while(test-- > 0){
			String exp = in.nextLine();
			
			Stack<Character> op = new Stack<Character>();
			Stack<Character> var = new Stack<Character>(); 
			String output = "";
			
			for(int i = 0; i < exp.length(); i++){
				char c = exp.charAt(i);
	
				if((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')){	
					var.push(c);
					if(i == exp.length() - 1){
						if(!var.empty())
							output += var.pop();
						
						while(!op.empty())
							output += op.pop();
					}
				}
				else if(c == '('){
					op.push(c);
				}
				else if(c == ')'){
					if(!var.empty())
						output += var.pop();
					
					while(op.peek() != '(')
						output += op.pop();
					
					op.pop();
				}
				else{	
					if(!op.empty()){
						if(op.peek() == '('){
							if(!var.empty())
								output += var.pop();
						}
						else if(getPriority(c) - getPriority(op.peek()) <= 0){
							output += var.pop();
							
							do	output += op.pop();
							while(!op.empty() && getPriority(c) - getPriority(op.peek()) <= 0);
				
						}
						else{ // current op larger than previous op
							if(!var.empty())
								output += var.pop();
						}
					}
					else // place first character
						output += var.pop();
					
					op.push(c);
				}
			}
			System.out.println(output);
		}
		in.close();
	}
	
	public static int getPriority(char c){
		if(c == '^') return 2;
		else if(c == '*' || c == '/') return 1;
		else if(c == '+' || c == '-') return 0;
		else return -1;
	}
}
