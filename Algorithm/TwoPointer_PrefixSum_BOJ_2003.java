// < 성공 방법 1 >
// end 사용 방법. (nextEnd 말고)

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
// etc-1) 누적합 또는 슬라이딩윈도우 문제에서, 배열의 n+1 길이를 위해 추가하는 마지막 arr[n] 요소값은 단지 범위 체크용이므로, 굳이 0 말고 어떤값이든 상관없음.
// etc-2) 누적합 문제에서, 만약 maxSum = Integer.MIN_VALUE 말고 -1로 선언한다면, 음수 합계인 sum값이 -1보다 작아 maxSum값이 갱신되지않을수있어 추천하지않음.
// etc-3) 비교변수의 초기값에는 아직 현재 구간을 적용하지 말고, while문 내부의 첫 과정에서 현재 구간 값을 확인 후 적용하여 진행할것.

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
        arr[n] = 0;  // 투포인터의 누적합 계산시, 끝 인덱스 확인을 위해 맨뒤에 추가할것. (추가하지 않을시, end가 끝에 도달했을때 start의 단독sum값을 확인하지않고 while문이 종료됨.)

        int start = 0, end = 0;
        int sum = 0, cnt = 0;
        while(start<=end && end<arr.length) {  // 누적합이므로 웬만해서는 start'<='end 활용을 추천.
            if(sum == m) {
                // !!! 참고로 이때 end는 'sum += arr[end++]'로 인해 +1된 상태이므로, 구간 인덱스를 출력하는 등의 다른 문제에서는 end-1을 사용해야함. ('PGS_연속된부분수열의합'과 'BOJ_16472' 문제의 코드 참고) !!!
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

/*
// < 성공 방법 2 >
// nextEnd 사용 방법. (end 말고)

import java.util.*;
import java.io.*;

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
        arr[n] = 0;  // 맨뒤에 배열길이를 더 늘려서, 덧셈시 '미리 ++end된 값인 arr[nextEnd]'의 인덱스 초과를 방지. (사실 arr[추가]=0은 안적어줘도되는 명시적 선언임.)

        int start = 0, nextEnd = 1;
        int sum = arr[0], cnt = 0;
        while(start<=nextEnd && nextEnd<arr.length) {  // 누적합이므로 웬만해서는 start'<='end 활용을 추천.
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
                sum += arr[nextEnd];  // 구간의 범위를 확장하여, 구간 내 합계값을 증가시킴.
                nextEnd++;
            }
        }

        System.out.print(cnt);
    }
}
*/