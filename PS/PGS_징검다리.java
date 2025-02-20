import java.util.*;

// - 알고리즘: 매개변수 탐색 (with 이분 탐색)

class Solution {
    public static boolean isValidMinDist(int minDist, int[] rocks, int n) {  // 결정함수
        int prev = rocks[0];
        int pollCnt = 0;
        
        for(int i=1; i<rocks.length; i++) {  // rock[i]를 제거해도될지 테스트.
            int dist = rocks[i] - prev;
            if(dist < minDist) {  // 최소거리보다 작으므로, 바위를 제거하여 사이간격을 더 늘려야함.
                // 마지막 바위(distance)는 제거하면 안됨.
                // 그러나 간격 충족여부를 확인해야 반환조건에 반영되므로, 바위 제거처럼 처리.
                pollCnt++;  // 바위 제거.
            }
            else {
                prev = rocks[i];
            }
        }
        
        // '제거한 바위의 수 <= 필요 제거 개수' : 바위를 더 제거해도됨 = 거리를 더 늘려도됨.
        return pollCnt <= n;  // 바위 최소 사이거리를 더 늘려서 테스트해도되는지? (true는 확장성 보유)
    }
    
    public int solution(int distance, int[] rocks, int n) {
        int[] rocksExpend = new int[rocks.length+2];
        rocksExpend[1] = distance;
        for(int i=2; i<rocksExpend.length; i++) {
            rocksExpend[i] = rocks[i-2];
        }
        Arrays.sort(rocksExpend);
        
        int left = 1;
        int right = distance;
        while(left <= right) {
            int mid = (left + right) / 2;  // 최소 이정도 거리 이상은 되어야함을 의미.
            
            if(isValidMinDist(mid, rocksExpend, n) == true) {
                left = mid + 1;  // 최소거리 확장
            }
            else {
                right = mid - 1;  // 최소거리 축소
            }
        }
        
        return right;
    }
}

public class PGS_징검다리 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
