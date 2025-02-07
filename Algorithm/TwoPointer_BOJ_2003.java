import java.util.*;
import java.io.*;

// [ 투 포인터 (Two Pointer) ]
// - 개념 특징 :
// 배열이나 리스트에서 '두 개의 포인터'를 사용하여 '특정 조건을 만족하는 부분 구간'을 효율적으로 탐색하는 알고리즘이다.
// 이로 인해 2중for문과는 달리, O(N) 시간복잡도로 선형 탐색이 가능하다.
// - 코드 특징 :
// 이 코드에서는 투포인터를 사용하여 누적합(덧셈값)을 활용하는 문제를 다루고 있다.
// 하지만 만약 차이값을 활용하는 문제의 경우, 덧셈이 아니므로 배열 끝에 0을 추가해서는 안되며, 배열도 정렬하고 시작해야한다.
// 문제 상황에 따라, 'start=0 end=0'처럼 포인터를 한쪽이 아닌 'start=0 end=n-1'처럼 양쪽으로 배치할 수도 있고,
// 'start++ end++'가 아닌 'start++ end--'처럼 안쪽 방향으로 이동할 수도 있으며, 고정 크기라면 포인터 둘을 동시에 이동시킬 경우도 있다.

public class TwoPointer_BOJ_2003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stt.nextToken());
        int m = Integer.parseInt(stt.nextToken());
        int[] arr = new int[n+1];

        stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(stt.nextToken());
        }
        arr[n] = 0;  // 끝 인덱스 확인을 위함. 반드시 맨뒤에 넣을것!!!

        int start = 0, end = 0;
        int sum = 0, cnt = 0;
        while(start<=end && end<arr.length) {
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
