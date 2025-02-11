import java.util.*;
import java.io.*;

// - 알고리즘: 투 포인터

public class BOJ_1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stt.nextToken());
        int s = Integer.parseInt(stt.nextToken());
        int[] arr = new int[n+1];  // 투포인터의 구간 내 누적합 문제이므로, n이 아닌 n+1 크기로 설정.

        stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(stt.nextToken());
        }
        arr[n] = 0;  // 투포인터의 누적합 계산시, 끝 인덱스 확인을 위함. 반드시 맨뒤에 넣을것!!!

        int start = 0, end = 0;
        int sum = 0, minLen = (int) 1e9;
        while(start<=end && end<arr.length) {  // 누적합이므로 웬만해서는 start'<='end 활용을 추천.
            if(sum >= s) {
                minLen = Math.min(minLen, end-start);
                sum -= arr[start];
                start++;
            }
            else {  // (sum < s)
                sum += arr[end];
                end++;
            }
        }

        System.out.print(minLen == (int) 1e9 ? 0 : minLen);
    }
}
