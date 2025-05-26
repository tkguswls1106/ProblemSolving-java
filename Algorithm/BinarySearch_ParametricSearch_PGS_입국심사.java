import java.util.*;

// [ 매개변수 탐색 (Parametric Search) with 이분 탐색 - min 아닌 max 결정함수 방식 ]
// - 코드 특징 :
// 최소를 구해야하므로, 반대로 결정함수에서 minTimeSum이 아닌 maxTimeSum을 사용.
// 또한 이러한 max 방식은 while문 조건과 범위를 좁히는 과정도 기존 min 방식과는 다름.
// (참고로 이는 두 가지 방식이 있는데, 코드 내 기재한 '방식 1'을 더 권장함.
// 왜냐하면 '방식 2'의 경우, right 초기값을 +1 해주고 풀어야하는 문제도 간혹 있기 때문.)

class Solution {
    // 결정 함수 : 주어진 시간합계(maxTimeSum) 이하로 모두 심사할 수 있는가? (아직 시간합계을 더 줄일수 있는 확장성이 있는가?)
    public static boolean isValid(long maxTimeSum, int n, int[] times) {  // n: 심사완료 해야만하는 최소 인원수
        long cnt = 0;  // cnt: 심사완료 가능한 인원수
        for(int time : times) {  // 정해진 시간 내 모든 심사관이 동시에 병렬처리하므로 계산 가능.
            cnt += (maxTimeSum / time);
            if(cnt >= n) break;  // 성능 최적화
        }
        
        return cnt >= n;
    }
    
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        // 이는 만약 'BOJ_3079' 문제처럼 배열 요소들이 직접 입력으로 주어진다면,
        // 굳이 정렬할 필요없이, 입력값들 중 최솟값과 최댓값을 비교해 얻어서,
        // 'left = (long) 최솟값;', 'right = (long) 최댓값 * n;'으로 초기화하고 시작해도됨.

        // < 방식 1 >
        // left, right, mid 값 = 심사를 위해 주어지는 시간합계 범위를 의미.
        long left = 1;  // 'long left = times[0] * (long) n;' 사용시 틀림.
        long right = times[times.length-1] * (long) n;
        long answer = 0;
        while(left <= right) {
            long mid = (left + right) / 2;  // time '합계' 기준
        
            if(isValid(mid, n, times) == true) {
                answer = mid;
                right = mid - 1;  // 'left = mid + 1;' 사용시 틀림.
                // 현재의 최대시간합계로 모두 심사가 가능하다면, 더 짧은 시간합계도 가능한지 확인.
            }
            else {
                left = mid + 1;  // 'right = mid - 1;' 사용시 틀림.
            }
        }
        
        return answer;
        
        // < 방식 2 >
        // // left, right, mid 값 = 심사를 위해 주어지는 시간합계 범위를 의미.
        // long left = 1;  // 'long left = times[0] * (long) n;' 사용시 틀림.
        // long right = times[times.length-1] * (long) n;
        // while(left < right) {  // 'left <= right' 사용시 틀림.
        //     long mid = (left + right) / 2;  // time '합계' 기준
        //
        //     if(isValid(mid, n, times) == true) {
        //         right = mid;  // 'left = mid + 1;' 사용시 틀림.
        //         // 현재의 최대시간합계로 모두 심사가 가능하다면, 더 짧은 시간합계도 가능한지 확인.
        //     }
        //     else {
        //         left = mid + 1;  // 'right = mid - 1;' 사용시 틀림.
        //     }
        // }
        //
        // return right;
    }
}

public class BinarySearch_ParametricSearch_PGS_입국심사 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        long answer1 = solution.solution(6, new int[]{7,10});

        System.out.println(answer1);  // => 28 출력
    }
}
