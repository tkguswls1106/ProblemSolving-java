import java.util.*;
import java.io.*;

// - 알고리즘: 투 포인터
// (변형 풀이 => 슬라이딩 윈도우가 아님에도 end 대신 nextEnd 사용. start는 순차이동이 아닌 순간이동 처리.)
// - 또다른 풀이법:
// https://wonchanzoo.tistory.com/26#DP-1
// 위 링크의 DP 알고리즘 방식을 활용하여,
// 'dp[i] = Math.max(dp[i-1] + arr[i], arr[i])' 점화식으로도 풀이 가능.

public class BOJ_1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];  // 투포인터의 구간 내 누적합 문제이므로, n이 아닌 n+1 크기로 설정.

        StringTokenizer stt = new StringTokenizer(br.readLine());
        // int maxNum = -1002;
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(stt.nextToken());
            // maxNum = Math.max(maxNum, arr[i]);
        }
        arr[n] = 0;
        // if(maxNum < 0) {
        //     System.out.print(maxNum);
        //     return;
        // }

        int start = 0, nextEnd = 1;  // 비록 슬라이딩 윈도우 문제는 아니지만, end보다 nextEnd 활용이 더 적합했음.
        int sum = arr[0], maxSum = Integer.MIN_VALUE;
        while(start<=nextEnd && nextEnd<arr.length) {
            maxSum = Math.max(maxSum, sum);

            if(sum + arr[nextEnd] < arr[nextEnd]) {
                sum = arr[nextEnd];
                start = nextEnd;
                nextEnd = start + 1;
            }
            else {
                sum += arr[nextEnd];
                nextEnd++;
            }
        }

        System.out.print(maxSum);
    }
}
