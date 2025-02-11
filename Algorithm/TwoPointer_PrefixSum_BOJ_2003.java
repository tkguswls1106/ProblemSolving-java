import java.util.*;
import java.io.*;

// [ 누적 합 with 투 포인터 (Two Pointer) - 기초 ]
// - 개념 특징 :
// 배열이나 리스트에서 '두 개의 포인터'를 사용하여 '특정 조건을 만족하는 부분 구간'을 효율적으로 탐색하는 알고리즘이다.
// 이로 인해 2중for문과는 달리, O(N) 시간복잡도로 선형 탐색이 가능하다.
// - 코드 특징 :
// 이 코드에서는 투포인터를 사용하여 누적합(덧셈값)을 활용하는 문제를 다루고 있다.
// 하지만 만약 차이값을 활용하는 문제의 경우, 덧셈이 아니므로 배열 끝에 0을 추가해서는 안되며, 배열도 정렬하고 시작해야한다.
// 문제 상황에 따라, 'start=0 end=0'처럼 포인터를 한쪽이 아닌 'start=0 end=n-1'처럼 양쪽으로 배치할 수도 있고,
// 'start++ end++'가 아닌 'start++ end--'처럼 안쪽 방향으로 이동할 수도 있으며, 고정크기(슬라이딩 윈도우)라면 포인터 둘을 동시에 이동시킬 경우도 있다.
// - DP 누적합 vs 투포인터 누적합 :
// DP의 누적합 문제는 '구간 내 합계(sum)를 구하라'를 바탕으로함.
// 반면, 투포인터의 누적합 문제는 '구간 내 합계(sum)가 num인 경우를 찾아라'를 바탕으로함.

// [ 누적합 vs 슬라이딩윈도우 vs 안쪽방향문제 (+ 특징) ]
// - 누적 합 : 누적합 덧셈 O -> new int[n+1] / 포인터들은 모두 앞에서 뒤 방향으로 이동 -> start<=end / end++ 사용.
// - 슬라이딩 윈도우 : 누적합 덧셈 O -> new int[n+1] / 포인터들은 모두 앞에서 뒤 방향으로 이동 -> start<=end / 구간 길이는 고정 -> end++ 대신 nextEnd++ 사용.
// - 안쪽방향 문제 : 누적합 덧셈 X -> new int[n] / 포인터들은 서로 반대쪽에서 안쪽 방향으로 이동 -> start<end / end-- 사용.
// etc-1) 비교변수의 초기값에는 아직 현재 구간을 적용하지 말고, while문 내부의 첫 과정에서 현재 구간 값을 확인 후 적용하여 진행할것.
// etc-2) 누적합 문제에서, 만약 maxSum = Integer.MIN_VALUE 말고 -1로 선언한다면, 음수 합계인 sum값이 -1보다 작아 maxSum값이 갱신되지않을수있어 추천하지않음.

public class TwoPointer_PrefixSum_BOJ_2003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stt.nextToken());
        int m = Integer.parseInt(stt.nextToken());
        int[] arr = new int[n+1];  // 투포인터의 구간 내 누적합 문제이므로, n이 아닌 n+1 크기로 설정.

        stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(stt.nextToken());
        }
        arr[n] = 0;  // 투포인터의 누적합 계산시, 끝 인덱스 확인을 위함. 반드시 맨뒤에 넣을것!!!

        int start = 0, end = 0;
        int sum = 0, cnt = 0;
        while(start<=end && end<arr.length) {  // 누적합이므로 웬만해서는 start'<='end 활용을 추천.
            if(sum == m) {
                cnt++;
                sum -= arr[start];
                start++;
            }
            else if(sum > m) {
                sum -= arr[start];  // 구간의 범위를 줄여, 구간 내 합계값을 감소시킴.
                start++;
            }
            else {  // (sum < m)
                sum += arr[end];  // 구간의 범위를 확장하여, 구간 내 합계값을 증가시킴.
                end++;
            }
        }

        System.out.print(cnt);
    }
}
