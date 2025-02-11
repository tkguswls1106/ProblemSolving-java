import java.util.*;
import java.io.*;

// - 알고리즘: 투 포인터

public class BOJ_2230 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stt.nextToken());
        int m = Integer.parseInt(stt.nextToken());
        int[] arr = new int[n];  // 투포인터의 구간 내 누적합 방식이 아닌, 두 수를 뽑아 계산하는것이므로 n+1 길이까지는 필요 X.

        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);  // 뽑을 수를 크기별로 탐색하기위해 정렬시킴.

        int start = 0, end = 0;
        int minDiff = 2000000000;  // 문제에 명시된 범위로 인해, 1e9로 기재시 범위보다 작음.
        while(start<=end && end<arr.length) {  // 뽑기 중복 가능하므로, 기존 투포인터의 누적합 방식처럼 start'<='end 활용할것.
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
