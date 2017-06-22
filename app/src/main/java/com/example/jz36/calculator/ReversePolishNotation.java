package com.example.jz36.calculator;

import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by jz36 on 19.06.17.
 */

public class ReversePolishNotation {

    private static boolean isOperator(char c){
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')';
    }

    private static byte getPriority(char c){
        switch (c){
            case '(' : return 0;
            case ')' : return 1;
            case '+' : return 2;
            case '-' : return 3;
            case '*' : return 4;
            case '/' : return 4;
            default: return 5;

        }
    }

    private static boolean isDelimeter(char c){
        return ' ' == c;
    }

    private static String getExpression(String input){
        String output = "";
        Stack<Character> operatorsStack = new Stack<Character>();

        for(int i = 0; i < input.length(); i++){
            if (isDelimeter(input.charAt(i))) continue;

            if(Character.isDigit(input.charAt(i))){
                while( !isDelimeter(input.charAt(i)) && !isOperator(input.charAt(i))){
                    output += input.charAt(i);
                    i++;

                    if (i == input.length()) break;
                }

                output += " ";
                i--;
            }

            if(isOperator(input.charAt(i))){
                if(input.charAt(i) == '('){
                    operatorsStack.push(input.charAt(i));
                }
                else if ( input.charAt(i) == ')' ){
                    Character s = operatorsStack.pop();

                    while( s != '(' ) {
                        output += s.toString() + " ";
                        s = operatorsStack.pop();
                    }
                }
                else{
                    if(!operatorsStack.isEmpty()){
                        if(getPriority(input.charAt(i)) <= getPriority(operatorsStack.peek())){
                            output += operatorsStack.pop().toString() + " ";
                        }
                    }
                    operatorsStack.push(input.charAt(i));
                }
            }
        }

        while (!operatorsStack.isEmpty()){
            output += operatorsStack.pop() + " ";
        }

        return output;
    }

    private static double Counting(String input){
        double result = 0;
        Stack<Double> temp = new Stack<Double>();

        for(int i = 0; i < input.length(); i++){
            if(Character.isDigit(input.charAt(i))) {
                String a = "";

                while(!isDelimeter(input.charAt(i)) && !isOperator(input.charAt(i))){

                    a += input.charAt(i);
                    i++;
                    if (i == input.length()) break;


                }

                temp.push(Double.parseDouble(a));
                i--;
            }
            else if(isOperator(input.charAt(i))){
                double a = temp.pop();
                double b;
                if (!temp.isEmpty())
                    b = temp.pop();
                else
                    b = 0.0;

                switch (input.charAt(i)){
                    case '+' : result = b + a; break;
                    case '-' : result = b - a; break;
                    case '*' : result = b * a; break;
                    case '/' : result = b / a; break;
                }
                temp.push(result);

            }


        }

        return temp.peek();
    }

    public static String Calculate(String input){
        String output = getExpression(input);

        Double result = Counting(output);

        return result.toString();
    }
}
