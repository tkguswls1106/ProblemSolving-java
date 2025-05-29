import java.util.*;
import java.io.*;
import java.awt.Point;

// - 알고리즘: 매개변수 탐색 (with 이분 탐색) + BFS
// - 이해 Tip: 각 중간 비행장에서 급유받는 리터는 모두 동일한 수치씩 진행한다는 의미임. (장착한 연료통 크기가 정해져있기 때문.)

public class BOJ_2585 {
    public static int n, k;
    public static Point[] board;
    public static Point lastP = new Point(10000, 10000);

    public static boolean isValid(int maxLiter) {
        int[] cnt = new int[n+1];

        // BFS
        Queue<Integer> idxQu = new LinkedList<>();
        idxQu.offer(0);
        cnt[0] = 1;

        while(!idxQu.isEmpty()) {
            int curIdx = idxQu.poll();
            Point curP = board[curIdx];
            int curLastDist = getLiter(curP, lastP);
            if(curLastDist <= maxLiter) {
                return cnt[curIdx]-1 <= k;  // cnt를 1부터 시작했으므로, 빼주고 사용.
            }

            for(int i=0; i<n; i++) {
                int nextIdx = i;
                if(cnt[nextIdx] > 0) continue;

                Point nextP = board[nextIdx];
                int curNextDist = getLiter(curP, nextP);
                if(!(curNextDist <= maxLiter)) continue;

                idxQu.offer(nextIdx);
                cnt[nextIdx] = cnt[curIdx] + 1;
            }
        }

        return false;
    }

    public static int getLiter(Point p1, Point p2) {
        double dist = Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
        int distCeil = (int) Math.ceil(dist);  // 올림 처리. 예시로 53.7 -> 54, 59.7 -> 60
        if(distCeil%10 != 0) {
            return distCeil/10 + 1;
        }
        else {
            return distCeil/10;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        k = Integer.parseInt(stt.nextToken());

        board = new Point[n+1];
        Point firstP = new Point(0, 0);
        board[0] = firstP;
        for(int i=1; i<=n; i++) {
            stt = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stt.nextToken());
            int y = Integer.parseInt(stt.nextToken());
            board[i] = new Point(x, y);
        }

        int left = 0;
        int right = getLiter(firstP, lastP);
        int answer = 0;
        while(left <= right) {
            int mid = (left + right) / 2;

            if(isValid(mid) == true) {
                answer = mid;
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        
        System.out.print(answer);
    }
}
