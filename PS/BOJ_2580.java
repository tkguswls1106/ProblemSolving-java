import java.util.*;
import java.io.*;
import java.awt.Point;

// - 알고리즘: 백트래킹

public class BOJ_2580 {
    public static int[][] board = new int[9][9];
    public static List<Point> emptyList = new ArrayList<>();
    public static StringBuilder stb = new StringBuilder();
    public static boolean isEnd = false;

    public static boolean isCanSet(int x, int y, int num) {
        for(int i=0; i<9; i++) {  // 가로 탐색
            if(board[x][i] == num) return false;
        }
        for(int i=0; i<9; i++) {  // 세로 탐색
            if(board[i][y] == num) return false;
        }

        int xBoxStart = (x/3) * 3;
        int yBoxStart = (y/3) * 3;
        for(int i=xBoxStart; i<xBoxStart+3; i++) {  // 3x3박스 탐색
            for(int j=yBoxStart; j<yBoxStart+3; j++) {
                if(board[i][j] == num) return false;
            }
        }

        return true;
    }

    public static void bt(int cnt) {
        if(isEnd) return;
        if(cnt == emptyList.size()) {
            for(int i=0; i<9; i++) {
                for(int j=0; j<9; j++) {
                    stb.append(board[i][j]).append(" ");
                }
                stb.append("\n");
            }
            isEnd = true;
            return;
        }

        for(int num=1; num<=9; num++) {
            Point cur = emptyList.get(cnt);
            if(!isCanSet(cur.x, cur.y, num)) continue;

            board[cur.x][cur.y] = num;
            bt(cnt+1);

            board[cur.x][cur.y] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt;
        for(int i=0; i<9; i++) {
            stt = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++) {
                board[i][j] = Integer.parseInt(stt.nextToken());
                if(board[i][j] == 0) emptyList.add(new Point(i, j));
            }
        }

        bt(0);
        System.out.print(stb.toString());
    }
}
