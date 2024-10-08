// < 실패 방법 >
// - 답은 맞는듯하나, 메모리 초과로 실패.
// - 알고리즘: 백트래킹

import java.util.*;
import java.io.*;
import java.awt.Point;

public class BOJ_9663_Fail {
    public static int n;
    public static List<Point> queenList = new ArrayList<>();
    public static int answer = 0;

    public static boolean isCanSet(int row, int col) {
        boolean isCan = true;
        for(Point p : queenList) {
            int x = p.x;
            int y = p.y;
            double gradient = (double)(col-y)/(row-x);  // 기울기
 
            if(x == row || y == col  // 가로&세로 검사: 사실 가로 검사는, row 넘어가게 하는걸로 대체가 가능하긴함.
            || gradient == 1.0 || gradient == -1.0 ) {  // 대각선 검사: 기울기가 1 or -1 이면 같은 대각선임. (또는 abs(x1 - x2) == abs(y1 - y2) 인지)
                isCan = false;
                break;
            }
        }
        return isCan;
    }

    public static void dfs(int row) {  // 백트래킹
        if(row == n) {
            answer++;
            return;
        }

        for(int col=0; col<n; col++) {
            if(isCanSet(row,col) == false) continue;

            queenList.add(new Point(row,col));
            dfs(row+1);

            int size = queenList.size();
            if(size > 0) queenList.remove(size-1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dfs(0);
        System.out.print(answer);
    }
}
