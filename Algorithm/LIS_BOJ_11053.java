import java.util.*;
import java.io.*;

// [ 최장 증가 부분 수열 (LIS, Longest Increasing Subsequence) with DP ]
// - 개념 특징 :
// 주어진 수열의 앞에서부터 순차적으로(연속적일 필요 X) 요소를 선택하여, 오름차순의 가장 긴 부분 수열을 찾는 알고리즘이다.
// - 코드 특징 :
// LIS 알고리즘의 풀이 방식은 2가지 종류가 있다.
// * DP 방식 : 시간복잡도 O(n^2), 구현이 간단하지만 느림.
// * BinarySearch 방식 : 시간복잡도 O(nlog(n)), 구현이 복잡하지만 빠름.
// ==> DP 방식은 상대적으로 느리지만, 문제 풀이에 충분히 적합하여 이 방식을 사용함.

public class LIS_BOJ_11053 {
    // - dp 테이블 정의 :
    // dp[i] = 인덱스i번째 수를 마지막 요소로 가지는 부분수열 길이의 최댓값
    // 즉, arr[i]가 마지막 요소인 부분수열의 LIS 길이를 의미.
    public static int[] dp = new int[1002];
    public static int[] arr = new int[1002];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(stt.nextToken());
            
            // LIS 길이는 최소 1 이상이므로, dp값들을 모두 1로 초기화하고 시작.
            // 만약 초기화하지 않으면, if(arr[prev] < arr[i]) 조건을 만족하지 않을때 dp[i]=0로 유지되는 문제 발생.
            dp[i] = 1;
        }

        // - 점화식 찾기 :
        // arr[i] 요소보다 작은 숫자의 arr[0 ~ i-1] 요소들 중에서, 가장 긴 길이를 가진 DP값에 +1을 dp[i]로 할당함.
        // ==> 앞에서 작은 요소들 중에서, dp[i] = max(dp[0 ~ i-1]) + 1;

        // - 초기값 할당 :
        // dp[0] = 1;

        // - dp값 세팅 :
        int maxLen = 1;  // LIS
        for(int i=1; i<n; i++) {
            // 점화식 활용.
            for(int prev=0; prev<i; prev++) {
                if(arr[prev] < arr[i]) {  // 현재 요소가 이전 요소보다 커야함. (오름차순 선택을 위함)
                    dp[i] = Math.max(dp[i], dp[prev]+1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }
        
        System.out.print(maxLen);
    }
}
