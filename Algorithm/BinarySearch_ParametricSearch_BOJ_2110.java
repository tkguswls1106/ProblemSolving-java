import java.util.*;
import java.io.*;

// [ 매개변수 탐색 (Parametric Search) with 이분 탐색 - 상세과정 및 응용 ]
// - 이해 Tip :
// 문제 의도를 '가장 짧은 거리가, 경우의수들 중에서 그나마 가장 길 때를 구하라'로 이해하면 수월함.
// 문제 설명) "가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램"
// => '가장 인접한' : 만약 공유기들을 불균일하게 막 배치한다면, 가장 짧은 거리가 1에 수렴할 수 있음을 주의해야함.
// => '사이의 거리를 최대로' : 그러므로 공유기들을 최대한 동일거리로 띄워 배치해야지, 가장 짧은 거리가 최대로 수렴할 수 있음.
// - 문제 Tip :
// 과정 1. 거리를 웬만하면 동등하게하되, 이를 최대한 멀게 배치할것.
// 과정 2. 사이의 거리를 최대로 ~~ -> 매개변수 탐색 알고리즘 적용.
// 과정 3. 연속된 자연수 범위 내에서 공유기 거리를 선정하고, 이를 매개변수로 설정한 결정함수 만들기.
// 과정 4. 매개변수 거리값을 결정함수의 if범위 제한값으로 활용할것. -> 공유기 간의 최소 거리를 매개변수로 설정.
// => 결정함수 : boolean isValidDist(int minDist)
// => minDist : 공유기 간격이 최소 이 거리 이상은 되어야함을 의미.
// => isValidDist : 주어진 최소거리 이상으로 공유기를 띄워 배치한다면, 문제에 요구된 공유기 개수만큼 설치되는가? (true = 배치 가능. 거리를 더 늘려볼 확장성 보유.)

public class BinarySearch_ParametricSearch_BOJ_2110 {
    public static int n, c;
    public static int[] arr;

    // 결정 함수 : 주어진 최소거리(minDist) 이상으로 공유기를 배치할 수 있는가? (아직 거리를 더 늘릴수 있는 확장성이 있는가?)
    public static boolean isValidDist(int minDist) {  // minDist : 설치 시, 공유기 간격이 최소 이 거리 이상은 되어야함을 의미.
        int prevHouseIdx = 0;  // 첫번째 집에는 반드시 공유기를 설치하고 시작.
        int cnt = 1;  // 설치한 공유기의 총 개수 (첫번째 집 포함)

        for(int i=1; i<n; i++) {  // i = curHouseIdx
            int nextDist = arr[i] - arr[prevHouseIdx];

            if(nextDist >= minDist) {  // 거리 충족. 공유기 설치 가능.
                prevHouseIdx = i;
                cnt++;
                if(cnt >= c) break;  // 성능 최적화
            }
        }

        return cnt >= c;  // 설치 후, 문제 명시된 공유기 개수에 충족되는지 확인 (거리를 더 늘려 확인해볼 여지를 줌.)
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        c = Integer.parseInt(stt.nextToken());

        arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);  // 좌표를 오름차순 정렬해두어야, 사이 간격(거리)을 구할 수 있음.

        // 연속된 자연수 범위에서 거리값을 선택해 확인. (left,right,mid = 설치할 간격이 최소 이 거리 이상은 되어야함을 의미.)
        int left = 1;  // 범위 최솟값 = 집이 최소 한칸씩은 떨어져있으므로, 공유기 거리도 최소 1 이상임.
        int right = arr[n-1] - arr[0];  // 범위 최댓값 = 집 사이의 가능한 최대거리 (첫번째집과 마지막집 사이의 간격)
        while(left <= right) {
            int mid = (left + right) / 2;  // 이분 탐색

            if(isValidDist(mid) == true) {
                left = mid + 1;  // 현재의 최소거리로 공유기 배치가 가능하다면, 더 큰 거리도 가능한지 확인.
                // 참고 : 이는 투포인터처럼 배열의 인덱스 범위를 좁히는게 아닌, 연속된 자연수 범위에서 선택할 실제값 minDist 자체를 늘리는 것을 의미.
                // => 즉, 연속된 자연수 범위에서 '실제값을 직접 조절'해 늘려가며, 더 큰 제한(최소)거리로도 가능성을 탐색함. (인덱스 범위 X)
            }
            else {
                right = mid - 1;
            }
        }

        // left와 right 범위가 안쪽 방향으로 모이며, 최종적으로 밑처럼 while문이 종료됨.
        // - left : 범위를 넘으며, 결국 조건을 만족하지 않는 지점에서 종료됨.
        // - right : 조건을 만족했던 가장 큰 값에서 종료됨.
        // ==> 그러므로, 답은 left가 아닌 right으로 출력해야 옳음.
        System.out.print(right);
    }
}
