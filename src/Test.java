import java.util.*;

/**
 * Author: Fayimora Femi-Balogun
 * Date: 26/10/2013
 * Time: 04:27
 */
public class Test {
    public static void main(String[] args){

      ArrayList<String> strs = new ArrayList<String>(); //[, , , , , , ]
      strs.add("FGHIK");
      strs.add("FGHJK");
      strs.add("FGIHJK");
      strs.add("FGIK");
      strs.add("FHIK");
      strs.add("FHJK");
      strs.add("FHGIK");
      SortedSet<String> ss = new TreeSet<String>(strs);

      int minLen = strs.get(0).length();
      String minStr = strs.get(0);
      for (int i = 0; i < strs.size(); i++) {
        int currLen = strs.get(i).length();
        String currStr = strs.get(i);
        if (currLen < minLen){
          minLen = currLen;
          minStr = currStr;
        }
      }

      for (int i=0; i<strs.size(); i++){
        if(strs.get(i).length() > minLen) strs.remove(i--);
        else minStr = minStr.compareTo(strs.get(i)) < 0 ? minStr : strs.get(i);
      }
      System.out.println(minLen);
      System.out.println(minStr);

                               /*
      Collections.sort(strs, new MyStringComparator<String>(){
        public int compare(String s1, String s2){
          if(s1.length() != s2.length()) return s1.length() - s2.length();
          else return s1.compareTo(s2);
        }
      });
      System.out.println(ss);
      System.out.println(strs);

                                 */



      /*StringBuilder sb = new StringBuilder("");
      int counter =0;
      try{
        for (int i=1; i<=1000000; i++){
          counter++;
          String str = Integer.toBinaryString(i);
          if(isPalindrome(str)) sb.append(String.format("cache.put(\"%s\", true);", str));
          else sb.append(String.format("cache.put(\"%s\", false);", str));
        }
        System.out.println(sb.toString());
      }catch(Exception e){
        System.out.println(counter);
      }         */
    }

    static boolean isPalindrome(String word){
      StringBuilder sb = new StringBuilder(word);
      sb.reverse();
      return sb.toString().equals(word);
    }
}
