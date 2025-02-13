import java.util.*;
import java.io.*;

// [ 슬라이딩 윈도우 (Sliding Window) with 투 포인터 (Two Pointer) ]
// 슬라이딩 윈도우의 응용 문제. (기초 + 알고리즘 도출과정 + while 범위초과 예외처리)

// - 알고리즘 도출 과정:
// 1. 이분탐색을 활용해야하나? [X]
// 좌표사이의 거리도 다르고, 그 거리에 놓여진 얼음양도 제각각임. 따라서 이분탐색을 통해 좌표를 좁혀간다한들, 닿을수있는 좌우 K거리의 얼음 최댓값도 천차만별임. -> 이분탐색 X.
// 또한 반경 내 얼음들의 최댓값 합을 구하는것이지, 거리의 최댓값을 산정하는 문제가 아니기에, 더더욱 이분탐색 알고리즘과는 거리가 멂. -> 이분탐색 X.
// => 이분탐색 활용 X
// 2. 투포인터를 활용해야하나? [O]
// 좌우로 K라는 고정된 거리가 있고 (슬라이딩 윈도우), 그 이동되는 구간 반경에 들어올수있는 얼음들의 합계를 구하는 문제임 (누적합) -> 슬라이딩 윈도우 & 누적합 사용.
// => 투포인터 활용 O (+ 슬라이딩 윈도우 & 누적합)

// [ 누적합 vs 슬라이딩윈도우 vs 안쪽방향문제 (+ 특징) ]
// - 누적 합 : 누적합 덧셈 O -> new int[n+1] / 포인터들은 모두 앞에서 뒤 방향으로 이동 -> start<=end / end++ 사용.
// - 슬라이딩 윈도우 : 누적합 덧셈 O -> new int[n+1] / 포인터들은 모두 앞에서 뒤 방향으로 이동 -> start<=end / 구간 길이는 고정 -> end++ 대신 nextEnd++ 사용.
// - 안쪽방향 문제 : 누적합 덧셈 X -> new int[n] / 포인터들은 서로 반대쪽에서 안쪽 방향으로 이동 -> start<end / end-- 사용.
// etc-1) 비교변수의 초기값에는 아직 현재 구간을 적용하지 말고, while문 내부의 첫 과정에서 현재 구간 값을 확인 후 적용하여 진행할것.
// etc-2) 누적합 문제에서, 만약 maxSum = Integer.MIN_VALUE 말고 -1로 선언한다면, 음수 합계인 sum값이 -1보다 작아 maxSum값이 갱신되지않을수있어 추천하지않음.

public class TwoPointer_SlidingWindow_BOJ_10025 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stt.nextToken());
        int k = Integer.parseInt(stt.nextToken());  // 좌우 K거리
        int dist = k*2 + 1;  // ex. 좌표4 거리3 => 123 좌표4 567 => 닿는 거리반경 총7만큼 (거리x2 + 1)
        int[] arr = new int[1000002];  // 가능한 좌표값 0 ~ 1000000 => start=0 부터 시작, 배열길이 1000001 => arr[nextEnd] 인덱스 초과 방지 => 배열길이 1000002 (1000001 + 1)
        // arr[1000001] = 0;

        int sum = 0;
        for(int i=0; i<n; i++) {
            stt = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(stt.nextToken());  // 얼음의 양
            int x = Integer.parseInt(stt.nextToken());  // 양동이의 좌표 (거리아님. 헷갈리지말것!)
            arr[x] = g;
            if(x < dist) sum += arr[x];  // sum += g;
        }

        // !!! 주의 !!!
        // 문제에 명시된 바로, '거리 K범위(~200만)'가 '좌표 x범위(~100만)'를 초과할 수 있음. => K로 이루어진 반경 dist값도 배열크기를 초과할 수 있음.
        // 이 경우, 투포인터 시작전에 이미 nextEnd값이 확인반경을 넘어가, 'nextEnd=dist, while(nextEnd<arr.length)'로서 while문이 실행되지 않음. => maxSum=sum 할당없이 종료될 수 있음.
        // ==> 따라서 이 경우에는 기존 sum값을 그대로 출력할것.
        if(dist >= arr.length) {
            System.out.print(sum);
            return;
        }

        int start = 0, nextEnd = dist;
        int maxSum = 0;  // int maxSum = Integer.MIN_VALUE;
        while(start<=nextEnd && nextEnd<arr.length) {
            // 현재 구간부터 확인 및 적용.
            maxSum = Math.max(maxSum, sum);

            // 현재 구간의 앞을 제거. (첫 요소)
            sum -= arr[start];
            start++;
            // 다음예정 구간의 뒤를 추가. (새롭게 나타나는 요소)
            sum += arr[nextEnd];
            nextEnd++;  // 미리 end가 아닌 nextEnd로 시작했으므로, ++end 말고 안전하게 nextEnd++를 사용.
        }

        System.out.print(maxSum);
    }
}
