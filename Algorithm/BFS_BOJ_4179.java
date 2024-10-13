import java.util.*;
import java.io.*;
import java.awt.*;

// [ BFS 지훈+불 문제 (Fire) ]

public class BFS_BOJ_4179 {
    public static int n, m;
    public static int[][] board = new int[1002][1002];
    public static int[][] distF = new int[1002][1002], distJ = new int[1002][1002];
    public static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    public static Queue<Point> quF = new LinkedList<>(), quJ = new LinkedList<>();
    public static String answer = "IMPOSSIBLE";

    public static void bfsFJ() {

        // 불부터 이동 (지훈은 불의 경로에 영향을 받지만, 불은 지훈의 경로에 영향을 받지 않기에 불만 먼저 전파시킴.)
        while(!quF.isEmpty()) {
            Point cur = quF.poll();

            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
                if(board[nx][ny] == 0 || distF[nx][ny] > 0) continue;

                quF.offer(new Point(nx,ny));
                distF[nx][ny] = distF[cur.x][cur.y] + 1;
            }
        }

        // 지훈 이동
        while(!quJ.isEmpty()) {
            Point cur = quJ.poll();

            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<0 || nx>=n || ny<0 || ny>=m) {  // 다음것은 경계선을 넘어감 (탈출 성공 예정)
                    answer = String.valueOf(distJ[cur.x][cur.y]);  // 시작을 1부터 했기에, +1 안해주고 바로 반환.
                    return;
                }
                if(board[nx][ny] == 0 || distJ[nx][ny] > 0) continue;
                if(distF[nx][ny] > 0 && distJ[cur.x][cur.y]+1 >= distF[nx][ny]) continue;  // 이미 불이 지나갔던 자리일때 && 지훈이 불과 같거나 먼저 도착한 경우

                quJ.offer(new Point(nx,ny));
                distJ[nx][ny] = distJ[cur.x][cur.y] + 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());

        for(int i=0; i<n; i++) {
            String inputStr = br.readLine();
            for(int j=0; j<m; j++) {
                char inputCh = inputStr.charAt(j);
                if(inputCh == '#') board[i][j] = 0;
                else {  // . J F
                    board[i][j] = 1;
                    if(inputCh == 'J') {
                        quJ.offer(new Point(i,j));  // 먼저 큐에 넣어두고 시작.
                        distJ[i][j] = 1;
                    }
                    else if(inputCh == 'F') {
                        quF.offer(new Point(i,j));  // 먼저 큐에 넣어두고 시작. (불은 1개 이상임.)
                        distF[i][j] = 1;
                    }
                }
            }
        }

        bfsFJ();
        System.out.print(answer);
    }
}
