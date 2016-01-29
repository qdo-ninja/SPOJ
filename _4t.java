package practice;

import java.util.Stack;

public class _4t {
	
	/* MY LOGIC
	 * 
	 * place char if see an op.
	 * if current op smaller than or equal previous op, place latest var if have. And place previvous ops until current op no longer smaller or equal previous ops.
	 * if current op larger than previous op, place latest var if have.
	 * at end, place all op.
	 * 
	 * */
	
	@SuppressWarnings("unused")
	public static void main(String[] args){
		String exp1 = "a+b-c+a-b-d+e";
		String exp2 = "a+b*c/d^y+e";
		String exp3 = "1*2+3*4+5/6/7";
		String exp4 = "a+(b-c*d)/e+y";
		String exp = "((a+t)*((b+(a+c))^(c+d)))";
		
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
	

	public static int getPriority(char c){
		if(c == '^') return 2;
		else if(c == '*' || c == '/') return 1;
		else if(c == '+' || c == '-') return 0;
		else return -1;
	}
}
