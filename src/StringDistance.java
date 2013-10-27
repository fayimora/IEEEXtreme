/**
 * Author: Fayimora Femi-Balogun
 * Date: 27/10/2013
 * Time: 08:51
 */
import java.util.*;

public class StringDistance {

  private static List<String> a = Arrays.asList(new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"});

  public static void main (String[] args) {
    Scanner in = new Scanner(System.in);

    int nDic = Integer.parseInt(in.nextLine().trim());
    List<String> dic = new ArrayList<String>();
    for (int i = 0; i < nDic; i++)
      dic.add(in.nextLine().trim());

    in.nextLine();

    List<String> cipher = Arrays.asList(in.nextLine().trim().split("\\s"));

    List<String> output = solve(cipher, dic);

    for (int i = 0; i < output.size(); i++) {
      if (i != 0) System.out.print(" ");
      System.out.print(output.get(i).toUpperCase());
    }
  }

  public static List<String> solve (List<String> cipher, List<String> dic) {
    List<String> output = new ArrayList<String>();

    //k = cipher, v = plaintext
    Map<String, String> mapping = new HashMap<String, String>();
    for (int i = 0; i < cipher.size(); i++) {
      String cWord = cipher.get(i);
      List<String> dWords = new ArrayList<String>();
      for (int j = 0; j < dic.size(); j++) {
        String dWord = dic.get(j);
        if (dWord.length() == cWord.length())
          dWords.add(dWord);
      }

      if (dWords.size() == 1) {
        String dWord = dWords.get(0);
        for (int j = 0; j < dWord.length(); j++) {
          mapping.put(cWord.substring(j, j+1).toUpperCase(), dWord.substring(j, j+1));
        }
      }
    }

    String cWord = cipher.toString().replaceAll("[\\[\\],]+", "");
    //System.out.println("1: cipher: " + cWord);

    for (int i = 0; i < a.size(); i++) {
      String cLetter = a.get(i).toUpperCase();
      String pLetter = mapping.get(cLetter);
      if (pLetter != null) {
        //System.out.printf("%s --> %s\n", cLetter, pLetter);
        cWord = cWord.replaceAll(cLetter, pLetter);
      }
    }

    //System.out.println("2: cipher: " + cWord);

    String[] cWords = cWord.split("\\s");
    for (int i = 0; i < cWords.length; i++) {
      if (cWords[i].matches(".*[A-Z]{1,}.*")) {
        String bestMatch = dic.get(0);
        int bestDistance = LevenshteinDistance.computeLevenshteinDistance(cWords[i], bestMatch);
        for (int j = 1; j < dic.size(); j++) {
          int currentDistance = LevenshteinDistance.computeLevenshteinDistance(cWords[i], dic.get(j));
          if (currentDistance < bestDistance) {
            bestMatch = dic.get(j);
            bestDistance = currentDistance;
          }
        }
        cWords[i] = bestMatch;
      }
    }

    for (int i = 0; i < cWords.length; i++)
      output.add(cWords[i]);

    return output;
  }

  private static class LevenshteinDistance {
    private static int minimum(int a, int b, int c) {
      return Math.min(Math.min(a, b), c);
    }

    public static int computeLevenshteinDistance(CharSequence str1, CharSequence str2) {
      int[][] distance = new int[str1.length() + 1][str2.length() + 1];

      for (int i = 0; i <= str1.length(); i++)
        distance[i][0] = i;
      for (int j = 1; j <= str2.length(); j++)
        distance[0][j] = j;

      for (int i = 1; i <= str1.length(); i++)
        for (int j = 1; j <= str2.length(); j++)
          distance[i][j] = minimum(
                  distance[i - 1][j] + 1,
                  distance[i][j - 1] + 1,
                  distance[i - 1][j - 1]
                          + ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0
                          : 1));

      return distance[str1.length()][str2.length()];
    }
  }
}