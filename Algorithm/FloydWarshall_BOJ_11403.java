import java.util.*;
import java.io.*;

// [ 플로이드 워셜 알고리즘 (Floyd-Warshall Algorithm) with DP - 가중치 없이 이동 가능여부만 판단하는 문제 (기초) ]
// - 문제 Tip : 이 문제에서는 int[][] 말고 boolean[][]으로도 풀이 가능함.

public class FloydWarshall_BOJ_11403 {
    public static int[][] board = new int[102][102];  // dp 의미

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int v = Integer.parseInt(br.readLine());  // 문제상으로는 n
        StringTokenizer stt;
        for(int i=1; i<=v; i++) {
            stt = new StringTokenizer(br.readLine());
            for(int j=1; j<=v; j++) {
                board[i][j] = Integer.parseInt(stt.nextToken());
            }
        }

        for(int viaNode=1; viaNode<=v; viaNode++) {  // 경유지
            for(int i=1; i<=v; i++) {  // 첫 출발지
                for(int j=1; j<=v; j++) {  // 마지막 도착지
                    boolean isStartToVia = (board[i][viaNode] == 1);
                    boolean isViaToEnd = (board[viaNode][j] == 1);
                    if(isStartToVia && isViaToEnd) {  // 갈 수 있는지를 확인.
                        board[i][j] = 1;
                    }
                }
            }
        }

        for(int i=1; i<=v; i++) {
            for(int j=1; j<=v; j++) {
                stb.append(board[i][j]).append(" ");
            }
            stb.append("\n");
        }
        
        System.out.print(stb.toString());
    }
}
