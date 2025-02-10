import java.util.*;
import java.io.*;

// [ 슬라이딩 윈도우 (Sliding Window) with 투 포인터 ]

public class TwoPointer_SlidingWindow_BOJ_21921 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stt.nextToken());
        int x = Integer.parseInt(stt.nextToken());
        int[] arr = new int[n];

        stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(stt.nextToken());
        }

        int sum = 0;
        for(int i=0; i<x; i++) {
            sum += arr[i];
        }

        int start = 0, end = x;
        int maxSum = sum, cnt = 1;
        while(start<=end && end<arr.length) {
            sum -= arr[start];
            start++;
            sum += arr[end];
            end++;

            if(sum > maxSum) {
                cnt = 1;  // 새로운 maxSum 값이 등장했으므로, cnt 재초기화
                maxSum = sum;
            }
            else if(sum == maxSum) {
                cnt++;
            }
        }

        if(maxSum == 0) System.out.print("SAD");
        else System.out.printf("%d\n%d", maxSum, cnt);
    }
}
