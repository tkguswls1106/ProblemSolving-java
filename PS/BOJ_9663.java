// < 통과 방법 1 >

import java.util.*;
import java.io.*;

public class BOJ_9663 {
    public static int n;
    public static List<Integer> colList = new ArrayList<>();  // row인덱스의 요소값: col
    public static int answer = 0;

    public static boolean isCanSet(int row, int col) {
        boolean isCan = true;
        for(int i=0; i<colList.size(); i++) {
            int x = i;
            int y = colList.get(i);
            double gradient = (double)(col-y)/(row-x);  // 기울기
 
            if(x == row || y == col  // 세로 검사: 가로 검사는, row 넘어가게 하는걸로 대체 가능.
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

            colList.add(col);
            dfs(row+1);

            int size = colList.size();
            if(size > 0) colList.remove(size-1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dfs(0);
        System.out.print(answer);
    }
}

/*
// < 통과 방법 2 >

import java.io.*;

public class BOJ_9663 {
    public static int n;
    public static int[] colArr = new int[17];  // row인덱스의 요소값: col
    public static int answer = 0;

    public static boolean isCanSet(int row, int col) {
        boolean isCan = true;
        for(int i=0; i<row; i++) {
            int x = i;
            int y = colArr[i];
            double gradient = (double)(col-y)/(row-x);  // 기울기
 
            if(x == row || y == col  // 세로 검사: 가로 검사는, row 넘어가게 하는걸로 대체 가능.
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

            colArr[row] = col;
            dfs(row+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dfs(0);
        System.out.print(answer);
    }
}
*/