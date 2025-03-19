import java.util.*;
import java.io.*;

// - 알고리즘: 투 포인터
// - 문제 Tip: 이 문제는 구간의 누적합이 아닌, 안쪽방향 이동을 통한 두 수의 합을 활용해야함.

public class BOJ_1940 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            int num = Integer.parseInt(stt.nextToken());
            arr[i] = num;
        }
        Arrays.sort(arr);

        int start = 0, end = arr.length-1;
        int cnt = 0;
        while(start<end && end<arr.length) {
            int sum = arr[start] + arr[end];
            if(sum == m) {
                cnt++;
                start++;
            }
            else if(sum > m) {
                end--;  // 안쪽방향으로 이동.
            }
            else {
                start++;  // 안쪽방향으로 이동.
            }
        }

        System.out.print(cnt);
    }
}
