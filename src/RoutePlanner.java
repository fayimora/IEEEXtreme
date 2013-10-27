/**
 * Author: Fayimora Femi-Balogun
 * Date: 26/10/2013
 * Time: 19:33
 */
import java.util.*;

class RoutePlanner{

  private static class Corner{
    String name;
    public ArrayList<Road> adjacencies = new ArrayList<Road>();
    public Corner(String name){
      this.name = name;
    }

    @Override
    public String toString(){
      /* return String.format("Corner(%s)", name); */
      return name;
    }
  }

  private static class Road{
    Corner to;
    Corner from;
    public Road(Corner from, Corner to){
      this.to = to;
      this.from = from;
    }
    public String toString(){
      /* return String.format("Road(%s)", this.to); */
      return String.format("Road(%s)", this.to);
    }
  }

  public static Corner[] corners = new Corner[26];
  public static ArrayList<String> paths = new ArrayList<String>();

  public static void main (String[] args) {
    String[] corners_str = {"A","B","C", "D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    Scanner cin = new Scanner(System.in);
    char destination = cin.nextLine().trim().charAt(0);

    for (int i=0; i<26; i++) corners[i] = new Corner(corners_str[i]);

    while(cin.hasNextLine()) {
      String[] strs = cin.nextLine().split(" +");
      int f = strs[0].charAt(0) - 'A';
      int t = strs[1].charAt(0) - 'A';
      if(f == 0 && t==0) break;
      corners[f].adjacencies.add(new Road(corners[f], corners[t]));
      corners[t].adjacencies.add(new Road(corners[t], corners[f]));
    }
    Corner source = corners[5]; // starts at F
    Corner target = corners[destination - 'A'];

    findPaths(source, target);
    if(paths.size()==0) System.out.printf("No Route Available from %s to %s\n", source.name, target.name);
    else doShananigans();
  }

  public static void doShananigans(){
    int total = paths.size();
    int minLen = paths.get(0).length();
    String minStr = paths.get(0);
    for (int i = 0; i < paths.size(); i++) {
      int currLen = paths.get(i).length();
      String currStr = paths.get(i);
      if (currLen < minLen){
        minLen = currLen;
        minStr = currStr;
      }
    }

    for (int i=0; i<paths.size(); i++){
      if(paths.get(i).length() > minLen) paths.remove(i--);
      else minStr = minStr.compareTo(paths.get(i)) < 0 ? minStr : paths.get(i);
    }

    String minStr2 = "";
    for (int i = 0; i < minStr.length(); i++) {
      minStr2 += minStr.substring(i, i+1) + " ";
    }

    System.out.printf("Total Routes: %d\n", total);
    System.out.printf("Shortest Route Length: %d\n", minLen);
    System.out.printf("Shortest Route after Sorting of Routes of length %d: %s\n", minLen,  minStr2.trim());
  }

  public static void findPaths(Corner source, Corner target){
    Stack<Corner> visitedCorners = new Stack<Corner>();
    visitedCorners.push(source);
    findSimplePaths(visitedCorners, target, paths);
  }

  public static void findSimplePaths(Stack<Corner> visited, Corner target, ArrayList<String> paths){
    Corner lastVisited = visited.peek();

    // explore all adjacent roads
    ArrayList<Road> roads = corners[lastVisited.toString().charAt(0) - 'A'].adjacencies;
    for(Road road: roads){
      Corner linkedCorner = lastVisited == road.from ? road.to : road.from;
      if(visited.contains(linkedCorner)) continue;
      else if(linkedCorner.equals(target)){
        visited.push((linkedCorner));
        Object[] temp = visited.toArray();

        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < temp.length; i++) sb.append(((Corner)temp[i]).name);

        paths.add(sb.toString());
        visited.pop();
      }
      visited.push(linkedCorner);
      findSimplePaths(visited, target, paths);
      visited.pop();
    }

  }
}