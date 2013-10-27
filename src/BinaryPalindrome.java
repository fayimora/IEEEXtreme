/**
 * Author: Fayimora Femi-Balogun
 * Date: 27/10/2013
 * Time: 09:15
 */
import java.util.*;
class BinaryPalindrome{
  public static Map<String, Boolean> cache = new HashMap<String, Boolean>();

  public static void main (String[] args) {
    cache.put("", true);
    cache.put("0", true);
    cache.put("1", true);

    Scanner cin = new Scanner(System.in);
    while ( cin.hasNextLine() ){
      String[] nums = cin.nextLine().trim().split(",");
      int start = Integer.parseInt(nums[0]);
      int finish = Integer.parseInt(nums[1]);
      int tmpStart = start;

      int counter = 0;
      while(tmpStart <= finish){
        String binary = Integer.toBinaryString(tmpStart);
        if(isPalindrome(binary)) counter++;
        tmpStart++;
      }
      System.out.println(counter);
    }
  }

  static boolean isPalindrome(String word){
    if(cache.containsKey(word)){
      if(cache.get(word)) return true;
      else return false;
    } else {
      //StringBuilder sb = new StringBuilder(word).reverse();
      /* if(sb.toString().equals(word)){ */
      if(isPalin(word)){
        cache.put(word, true);
        return true;
      }
      else{
        cache.put(word, false);
        return false;
      }
    }
  }

  /* This gave us a few milli seconds advantage */
  static boolean isPalin(String word){
    if(cache.containsKey(word)){
      if(cache.get(word)) return true;
      else return false;
    }
    else if(!(word.charAt(0) == word.charAt(word.length()-1))){
      return false;
    } else {
      String sub = word.substring(1, word.length()-1);
      boolean b =  isPalin(sub);
      if(b) cache.put(sub, true);
      else cache.put(sub, false);
      return b;
    }
  }
}

