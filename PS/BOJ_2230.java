import java.util.*;
import java.io.*;

// - 알고리즘: 투 포인터

public class BOJ_2230 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stt.nextToken());
        int m = Integer.parseInt(stt.nextToken());
        int[] arr = new int[n];

        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int start = 0, end = 0;
        int minDiff = 2000000000;  // 문제에 명시된 범위로 인해, 1e9로 기재시 범위보다 작음.
        while(start<=end && end<arr.length) {  // 뽑기 중복 가능하므로, 기존 방식처럼 end=0 시작 및 start<=end 활용할것. 
            int diff = arr[end] - arr[start];
            if(diff >= m) {
                minDiff = Math.min(minDiff, diff);
                start++;
            }
            else {  // (diff < m)
                end++;
            }
        }

        System.out.print(minDiff);
    }
}
