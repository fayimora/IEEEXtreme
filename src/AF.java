/**
 * Author: Fayimora Femi-Balogun
 * Date: 27/10/2013
 * Time: 08:47
 */
import java.util.*;

public class AF {

  public static void main (String[] args) {
    Scanner in = new Scanner(System.in);

    int questions = in.nextInt();

    List<Integer> favNumbers = new ArrayList<Integer>();
    for (int i = 0; i < questions; i++)
      favNumbers.add(in.nextInt());

    List<Integer> output = solve(favNumbers);

    for (Integer o : output)
      System.out.println(o);
  }

  private static List<Integer> solve (List<Integer> favNumbers) {
    List<Integer> results = new ArrayList<Integer>();

    //build
    int[] array = buildArray(1337);

    for (int i = 0; i < favNumbers.size(); i++) {
      int favN = favNumbers.get(i);
      boolean dir = true; //true == clockwise == right
      int index = 0;  //start with 1
      for (int j = 1; j < favN; j++) {
        int currentPos = array[index];
        //System.out.println(currentPos);
        //if (currentPos % 7 == 0 || (currentPos+"").contains("7")) {
        if (j % 7 == 0 || (j+"").contains("7")) {
          //reverse direction
          dir = !dir;

        }
        index += dir ? 1 : -1;
        if (index < 0)
          index = 1336;
        else if (index > 1336)
          index = 0;
      }
      //System.out.println("pos: " + array[index]);
      results.add(array[index]);
    }

    return results;
  }

  private static int[] buildArray (int e) {
    int[] array = new int[1337];
    for (int i = 0; i < array.length; i++)
      array[i] = i + 1;
    return array;
  }

}

