/**
 * Author: Fayimora Femi-Balogun
 * Date: 27/10/2013
 * Time: 09:14
 */
import java.util.*;

class Acadox{
  public static void main (String[] args) {
    Scanner cin = new Scanner(System.in);
    while(cin.hasNextLine()){
      String expression = cin.nextLine().trim();
      try{
        int x = Integer.parseInt(evaluate(expression));
        if(x>0){
          String ans = addPadding(Integer.toHexString(x));
          System.out.println(ans);
        }else{
          System.out.println("0000");
        }
      }catch(Exception ex){
        System.out.println("ERROR");
      }
    }
  }

  static String addPadding(String hex){
    String res = "";
    if (hex.length() > 4) res = "FFFF";
    else if (hex.length() == 1) res = "000"+hex;
    else if (hex.length() == 2) res = "00"+hex;
    else if (hex.length() == 3) res = "0"+hex;
    else res= hex;

    return res.toUpperCase();
  }

  static String evaluate(String expr) throws Exception{
    Stack<String> stack = new Stack<String>();
    String[] chars = expr.split(" ");
    for (int i=0; i<chars.length; i++){
      if(chars[i].equals("+")){
        int v1 = Integer.parseInt(stack.pop(), 16);
        int v2 = Integer.parseInt(stack.pop(), 16);
        stack.push(Integer.toHexString(v2 + v1));
      }
      if(chars[i].equals("-")){
        int v1 = Integer.parseInt(stack.pop(), 16);
        int v2 = Integer.parseInt(stack.pop(), 16);
        stack.push(Integer.toHexString(v2 - v1));
      }
      if(chars[i].equals("&")){
        int v1 = Integer.parseInt(stack.pop(), 16);
        int v2 = Integer.parseInt(stack.pop(), 16);
        stack.push(Integer.toHexString(v2 & v1));
      }
      if(chars[i].equals("|")){
        int v1 = Integer.parseInt(stack.pop(), 16);
        int v2 = Integer.parseInt(stack.pop(), 16);
        stack.push(Integer.toHexString(v2 | v1));
      }
      if(chars[i].equals("~")){
        int v1 = Integer.parseInt(stack.pop(), 16);
        stack.push(Integer.toHexString(~ v1));
      }
      if(chars[i].equals("X")){
        int v1 = Integer.parseInt(stack.pop(), 16);
        int v2 = Integer.parseInt(stack.pop(), 16);
        stack.push(Integer.toHexString(v2 ^ v1));
      }

      while((i < chars.length) && (!chars[i].equals("+") && !chars[i].equals("-") && !chars[i].equals("&") &&
              !chars[i].equals("|") && !chars[i].equals("~") && !chars[i].equals("X"))){
        stack.push(chars[i++]);
        if ((chars[i].equals("+") || chars[i].equals("-") || chars[i].equals("&") ||
                chars[i].equals("|") || chars[i].equals("~") || chars[i].equals("X")))
        {
          i--;
          break;
        }
      }
    }

    String returnRes = "";
    try{returnRes = Integer.parseInt(stack.pop(), 16)+"";}catch(Exception e ){returnRes = "-10";}
    return returnRes;
    /* return Integer.parseInt(stack.pop(), 16)+""; */
  }
}

