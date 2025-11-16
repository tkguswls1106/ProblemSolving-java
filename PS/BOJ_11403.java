import java.util.*;
import java.io.*;

// - 알고리즘: 플로이드 워셜 (단, 가중치 없이 모든 정점끼리의 이동 가능여부만 판단.)

public class BOJ_11403 {
    public static int[][] board = new int[102][102];  // dp

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
