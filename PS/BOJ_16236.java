import java.util.*;
import java.io.*;

// - 알고리즘 : BFS
// - 문제 Tip :
// 밑의 입력 예시를 기준으로 문제를 올바르게 푼다면, 좌표(0,0)을 먼저 방문해야하지만,
// 만약 단순하게 생각하여 BFS의 방문순서를 '북,서,남,동'으로 설정한다면, 좌표(1,5)를 먼저 방문해 틀리게되니 주의해야함.
// 1 6 6 6 6 6
// 0 6 0 0 0 1
// 0 6 0 6 6 6
// 0 0 9 6 6 6
// 6 6 6 6 6 6
// 6 6 6 6 6 6
// - 또다른 풀이법 :
// 방식 1. 내 코드처럼 'int[][] dist' 배열을 운용하는 풀이방식 외에도,
// 방식 2. Shark 커스텀 클래스에 'int dist' 변수를 포함시켜, 각 상태를 객체로 관리하고
//        이를 'boolean[][] visited' 배열과 함께 사용하는 방식으로도 풀이할 수 있음.

public class BOJ_16236 {
    public static int n;
    public static int[][] board = new int[22][22];
    public static int[][] dist;
    public static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    public static int sharkSize = 2, eatCnt = 0;
    public static int nextDist = 0, answer = 0;

    public static class Shark implements Comparable<Shark> {
        public int x;
        public int y;

        public Shark(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Shark b) {
            int aDist = dist[this.x][this.y];
            int bDist = dist[b.x][b.y];

            if(aDist != bDist) {
                return aDist - bDist;
            }
            else if(this.x != b.x) {
                return this.x - b.x;
            }
            else {
                return this.y - b.y;
            }
        }
    }

    public static Shark bfsForFindPath(int x, int y) {  // 다음 이동할 좌표와 거리를 찾는 메소드
        dist = new int[n][n];
        PriorityQueue<Shark> pq = new PriorityQueue<>();

        Queue<Shark> qu = new LinkedList<>();
        Shark shark = new Shark(x, y);
        qu.offer(shark);
        dist[x][y] = 1;
        
        while(!qu.isEmpty()) {
            Shark cur = qu.poll();

            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
                if(board[nx][ny] > sharkSize || dist[nx][ny] > 0) continue;

                shark = new Shark(nx, ny);
                qu.offer(shark);
                dist[nx][ny] = dist[cur.x][cur.y] + 1;

                if(board[nx][ny] != 0 && board[nx][ny] < sharkSize) {  // 0보다 커야지 먹을 수 있는 물고기임.
                    pq.offer(shark);  // 이동 가능한(먹을 수 있는 물고기) 경로 발견.
                }
            }
        }

        Shark nextShark = pq.poll();
        if(nextShark != null) {
            nextDist = dist[nextShark.x][nextShark.y] - 1;  // 거리 시작을 1부터 시작했으므로 빼주고 할당.
        }
        return nextShark;
    }

    public static void bfsForEatAndMove(int x, int y) {  // 찾은 경로를 바탕으로, 물고기를 먹고 이동하는 반복 메소드
        while(true) {
            // first eat
            if(board[x][y] == 9) {
                board[x][y] = 0;
            }

            Shark nextShark = bfsForFindPath(x, y);
            if(nextShark != null) {  // 우선순위큐가 비어있지 않았던 경우 (즉, 다음 먹을 물고기 경로가 존재한다면)
                // next eat
                board[nextShark.x][nextShark.y] = 0;
                eatCnt++;
                if(sharkSize == eatCnt) {
                    sharkSize++;
                    eatCnt = 0;
                }
                answer += nextDist;

                // move
                x = nextShark.x;
                y = nextShark.y;
            }
            else {
                break;
            }
        }        
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer stt;
        int firstX = 0, firstY = 0;
        for(int i=0; i<n; i++) {
            stt = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                int inputNum = Integer.parseInt(stt.nextToken());
                board[i][j] = inputNum;
                if(inputNum == 9) {
                    firstX = i;
                    firstY = j;
                }
            }
        }

        bfsForEatAndMove(firstX, firstY);

        System.out.print(answer);
    }
}
