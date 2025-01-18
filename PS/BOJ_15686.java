import java.util.*;
import java.io.*;
import java.awt.Point;  // import java.awt.*; 사용시 List 라이브러리 중복 오류 발생있음.

// - 알고리즘: 조합

public class BOJ_15686 {
    public static int n, m;
    public static List<Point> houseList = new ArrayList<>();
    public static List<Point> chickenList = new ArrayList<>();
    public static List<Point> selectedChickenList = new ArrayList<>();
    public static int minSumDist = (int) 1e9;

    public static int getDist(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    public static void comb(int start, int cnt) {
        if(cnt == m) {
            int sumDist = 0;
            for(Point house : houseList) {
                int minDist = (int) 1e9;
                for(Point selectedChicken : selectedChickenList) {
                    minDist = Math.min(minDist, getDist(house, selectedChicken));
                }
                sumDist += minDist;
            }

            minSumDist = Math.min(minSumDist, sumDist);
            return;
        }

        for(int i=start; i<chickenList.size(); i++) {
            selectedChickenList.add(chickenList.get(i));
            comb(i+1, cnt+1);
            
            selectedChickenList.remove(selectedChickenList.size()-1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());

        for(int i=0; i<n; i++) {
            stt = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                int input = Integer.parseInt(stt.nextToken());
                if(input == 1) houseList.add(new Point(i,j));
                else if(input == 2) chickenList.add(new Point(i,j));
            }
        }

        comb(0, 0);

        System.out.print(minSumDist);
    }
}
