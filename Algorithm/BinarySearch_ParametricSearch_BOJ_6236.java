import java.util.*;
import java.io.*;

// [ 매개변수 탐색 (Parametric Search) with 이분 탐색 - min 아닌 max 결정함수 방식 응용 ]

public class BinarySearch_ParametricSearch_BOJ_6236 {
    public static int n, m;
    public static int[] arr = new int[100002];

    public static boolean isValid(int maxPrice) {  // 주의: maxPrice 변수는 합계값을 의미하는것이 아니니, 헷갈리지 말것.
        int restPrice = maxPrice;  // 첫번째는 반드시 인출하고 시작.
        int cnt = 1;  // maxPrice를 인출한 횟수 (첫번째 포함)
        for(int cost : arr) {
            if(restPrice < cost) {
                restPrice = maxPrice;  // 남은 금액을 넣고 다시 새롭게 인출.
                cnt++;
            }
            restPrice -= cost;
            // 주의: 이 문제에서는 여기서 평소처럼 'if(cnt <= m) break;'로 성능 최적화 코드를 넣어주면 안됨.
            //      부등호 방향이 기존과 다를뿐더러, 아직 모든 날짜의 비용을 처리하지 않았는데도 인출 횟수가 적다고 중단하므로 틀리게됨.
        }

        return cnt <= m;  // 주의: 부등호 방향을 평소처럼 'cnt >= m'로 하면 틀리니 조심할것.
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());

        int maxCost = -1;
        for(int i=0; i<n; i++) {
            int cost = Integer.parseInt(br.readLine());
            arr[i] = cost;
            maxCost = Math.max(maxCost, cost);
        }

        int left = maxCost;  // 인출해서 바로 사용이 가능하긴해야함.
        int right = maxCost * n;  // 주의: left,right 변수들은 합계값을 의미하는것이 아니니, 헷갈리지 말것.
        int answer = 0;
        while(left <= right) {
            int mid = (left + right) / 2;

            if(isValid(mid) == true) {
                answer = mid;
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        System.out.print(answer);
    }
}
