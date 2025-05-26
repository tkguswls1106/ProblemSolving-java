import java.util.*;
import java.io.*;

// - 알고리즘: 매개변수 탐색 (with 이분 탐색)

public class BOJ_3079 {
    public static int n, m;
    public static int[] arr;

    public static boolean isValid(long maxTimeSum) {
        long cnt = 0;
        for(int time : arr) {
            cnt += (maxTimeSum / time);
            if(cnt >= m) break;  // return true; (성능 최적화)
        }

        return cnt >= m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());

        arr = new int[n];
        int minTime = Integer.MAX_VALUE;
        int maxTime = -1;
        for(int i=0; i<n; i++) {
            int time = Integer.parseInt(br.readLine());
            arr[i] = time;
            minTime = Math.min(minTime, time);
            maxTime = Math.max(maxTime, time);
        }

        long left = (long) minTime;
        long right = (long) maxTime * m;
        long answer = 0;
        while(left <= right) {
            long mid = (left + right) / 2;

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
