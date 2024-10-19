import java.util.*;
import java.io.*;

// [ BFS 토마토 문제 (3차원 배열) ]

public class BFS_BOJ_7569 {
    public static int h, n, m;  // z 높이, x 행, y 열
    public static int[][][] board = new int[102][102][102];
    public static int[][][] dist = new int[102][102][102];
    public static int[] dx = {1, 0, -1, 0, 0, 0};
    public static int[] dy = {0, 1, 0, -1, 0, 0};
    public static int[] dz = {0, 0, 0, 0, 1, -1};
    public static Queue<Tuple> qu = new LinkedList<>();

    public static class Tuple {  // 정렬이 필요없어, '@Override compareTo()'와 'implements Comparable<Tuple>'을 미작성함.
        public int z;
        public int x;
        public int y;

        public Tuple(int z, int x, int y) {  // !!! 주의사항: x,y,z 순으로 넣지 말것. (z,x,y 처럼 범위가 큰순서로.) !!!
            this.z = z;
            this.x = x;
            this.y = y;
        }
    }

    public static void bfs() {

        while(!qu.isEmpty()) {
            Tuple cur = qu.poll();

            for(int i=0; i<6; i++) {
                int nz = cur.z + dz[i];
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nz<0 || nz>=h || nx<0 || nx>=n || ny<0 || ny>=m) continue;
                if(dist[nz][nx][ny] != -1) continue;  // 안익은토마토가 아니라면 건너뜀.

                qu.offer(new Tuple(nz, nx, ny));
                dist[nz][nx][ny] = dist[cur.z][cur.x][cur.y] + 1;  // 참고로 어차피 기존에 dist가 -1이었어도, 0에서 +1값을 덮어씌우기에 문제없음.
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());

        m = Integer.parseInt(stt.nextToken());
        n = Integer.parseInt(stt.nextToken());
        h = Integer.parseInt(stt.nextToken());

        for(int i=0; i<h; i++) {  // z 높이
            for(int j=0; j<n; j++) {  // x 행
                stt = new StringTokenizer(br.readLine());
                for(int k=0; k<m; k++) {  // y 열
                    int num = Integer.parseInt(stt.nextToken());
                    if(num == 1) qu.offer(new Tuple(i, j, k));  // z,x,y
                    else if(num == 0) dist[i][j][k] = -1;  // 처음부터 안익은토마토를 아예 dist=-1로 지정.
                }
            }
        }

        bfs();

        int maxDist = 0;
        for(int i=0; i<h; i++) {  // z 높이
            for(int j=0; j<n; j++) {  // x 행
                for(int k=0; k<m; k++) {  // y 열
                    int distNum = dist[i][j][k];

                    if(distNum == -1) {  // 초기에 익지않은 토마토를 넣었던 자리가, 최종적으로도 그대로 안익었을경우
                        System.out.print(-1);
                        return;
                    }
                    else {
                        maxDist = Math.max(maxDist, distNum);
                    }
                }
            }
        }

        System.out.print(maxDist);
    }
 }
