import java.util.*;
import java.io.*;

// - 알고리즘: 투 포인터
// - 문제 Tip: 최대 시간복잡도 = 코드 주석의 '첫번째 O(N)' x '두번째 O(N)' = O(N^2) = O(4000000) = 1초 이내로 연산 가능.

public class BOJ_1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(stt.nextToken());
        }
        Arrays.sort(arr);

        int answer = 0;
        Set<Integer> s = new HashSet<>();
        for(int i=0; i<n; i++) {  // 첫번째 O(N)
            int findSum = arr[i];
            if(s.contains(findSum) == true) {
                answer++;  // 성능 최적화
                continue;
            }

            int start = 0, end = arr.length-1;  // 두번째 O(N)
            while(start<end && end<arr.length) {
                if(start == i) {
                    start++;
                    continue;
                }
                if(end == i) {
                    end--;
                    continue;
                }

                int sum = arr[start] + arr[end];
                if(sum == findSum) {
                    answer++;
                    s.add(findSum);
                    break;
                }
                else if(sum > findSum) {
                    end--;
                }
                else {  // (sum < findSum)
                    start++;
                }
            }
        }
        
        System.out.print(answer);
    }
}
