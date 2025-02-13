import java.util.*;
import java.io.*;

// [ 슬라이딩 윈도우 (Sliding Window) with 투 포인터 (Two Pointer) ]
// 슬라이딩 윈도우의 기초 문제.

// [ 누적합 vs 슬라이딩윈도우 vs 안쪽방향문제 (+ 특징) ]
// - 누적 합 : 누적합 덧셈 O -> new int[n+1] / 포인터들은 모두 앞에서 뒤 방향으로 이동 -> start<=end / end++ 사용.
// - 슬라이딩 윈도우 : 누적합 덧셈 O -> new int[n+1] / 포인터들은 모두 앞에서 뒤 방향으로 이동 -> start<=end / 구간 길이는 고정 -> end++ 대신 nextEnd++ 사용.
// - 안쪽방향 문제 : 누적합 덧셈 X -> new int[n] / 포인터들은 서로 반대쪽에서 안쪽 방향으로 이동 -> start<end / end-- 사용.
// etc-1) 비교변수의 초기값에는 아직 현재 구간을 적용하지 말고, while문 내부의 첫 과정에서 현재 구간 값을 확인 후 적용하여 진행할것.
// etc-2) 누적합 문제에서, 만약 maxSum = Integer.MIN_VALUE 말고 -1로 선언한다면, 음수 합계인 sum값이 -1보다 작아 maxSum값이 갱신되지않을수있어 추천하지않음.

public class TwoPointer_SlidingWindow_BOJ_21921 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stt.nextToken());
        int day = Integer.parseInt(stt.nextToken());
        int[] arr = new int[n+1];  // 투포인터의 구간 내 누적합 문제이므로, n이 아닌 n+1 크기로 설정.

        int sum = 0;
        stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(stt.nextToken());
            if(i < day) sum += arr[i];  // 'start=0 ~ end=day-1' 현재 구간의 누적합
        }
        arr[n] = 0;  // 맨뒤에 배열길이를 더 늘려서, 덧셈시 '미리 ++end된 값인 arr[nextEnd]'의 인덱스 초과를 방지. (사실 arr[추가]=0은 안적어줘도되는 명시적 선언임.)

        int start = 0, nextEnd = day;  // nextEnd = (nowEnd: day - 1) + 1 = day
        int maxSum = Integer.MIN_VALUE, cnt = 0;  // 비교변수값에는 현재 구간을 아직 적용 X.
        while(start<=nextEnd && nextEnd<arr.length) {  // 누적합이므로 웬만해서는 start'<='nextEnd 활용을 추천.
            // 현재 구간부터 확인 및 적용.
            if(sum > maxSum) {
                cnt = 1;  // 새로운 maxSum 값이 등장했으므로, cnt 재초기화
                maxSum = sum;
            }
            else if(sum == maxSum) {
                cnt++;
            }

            // [ 슬라이딩 윈도우 : 다른 투포인터 방식과는 달리, if~else 모든 조건에서 포인터(둘다)를 이동해야함. ]
            // 현재 구간의 앞을 제거. (첫 요소)
            sum -= arr[start];
            start++;
            // 다음예정 구간의 뒤를 추가. (새롭게 나타나는 요소)
            sum += arr[nextEnd];
            nextEnd++;  // 미리 end가 아닌 nextEnd로 시작했으므로, ++end 말고 안전하게 nextEnd++를 사용.
        }

        // - 다른 버전 -
        // int nowStart = 0, nextEnd = day;  // nextEnd = (nowEnd: day - 1) + 1 = day
        // int maxSum = sum, cnt = 1;  // 변수값에 현재 구간을 적용 O.
        // while(nextEnd < n) {  // 반복문에서는 다음 구간을 확인.
        //     // [ 슬라이딩 윈도우 : 다른 투포인터 방식과는 달리, if~else 모든 조건에서 포인터(둘다)를 이동해야함. ]
        //     // 현재 구간의 앞을 제거. (첫 요소)
        //     sum -= arr[nowStart];
        //     nowStart++;
        //     // 다음예정 구간의 뒤를 추가. (새롭게 나타나는 요소)
        //     sum += arr[nextEnd];
        //     nextEnd++;

        //     if(sum > maxSum) {
        //         cnt = 1;  // 새로운 maxSum 값이 등장했으므로, cnt 재초기화
        //         maxSum = sum;
        //     }
        //     else if(sum == maxSum) {
        //         cnt++;
        //     }
        // }

        if(maxSum == 0) System.out.print("SAD");
        else System.out.printf("%d\n%d", maxSum, cnt);
    }
}
