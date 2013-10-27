/**
 * Author: Fayimora Femi-Balogun
 * Date: 27/10/2013
 * Time: 09:05
 */
import java.math.BigInteger;
import java.util.*;

public class SubSequence {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    in.nextLine();

    String[] arrStrNumbers = in.nextLine().trim().split("[\\s|\t]{1,}");
    BigInteger[] numbers = new BigInteger[arrStrNumbers.length];
    for (int i = 0; i < numbers.length; i++) {
      numbers[i] = new BigInteger(arrStrNumbers[i]);
    }

    System.out.println(maxSubsequenceSum(numbers).intValue());
  }

  private static BigInteger maxSubsequenceSum(BigInteger[] a) {
    BigInteger sum = new BigInteger("0");
    for (int i = 0; i < a.length; i++)
      sum = sum.add(new BigInteger(a[i]+""));

    BigInteger currentSum = sum;
    BigInteger maxSum = sum;
    for (int i = 0; i < a.length; i++) {
      currentSum = currentSum.subtract(new BigInteger(a[i]+""));
      if (currentSum.compareTo(maxSum) > 0)
        maxSum = currentSum;
    }

    return maxSum.compareTo(new BigInteger("0")) < 0 ? new BigInteger("0") : maxSum;
  }
}

