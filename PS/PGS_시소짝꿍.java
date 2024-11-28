import java.util.*;

// - 문제 Tip:
// 예시로, a x 2 = b x 3
// b = (a x 2) / 3
// ==>
// a == a
// a == (a x 2) / 3 (오름차순 정렬했으므로, 필요 X)
// a == (a x 2) / 4 (오름차순 정렬했으므로, 필요 X)
// a == (a x 3) / 2
// a == (a x 3) / 4 (오름차순 정렬했으므로, 필요 X)
// a == (a x 4) / 2
// a == (a x 4) / 3

class Solution {
    public static boolean isInt(double dNum) {
        return (dNum % 1 == 0);
    }
    
    public long solution(int[] weights) {
        Map<Integer, Integer> m = new HashMap<>();
        for(int num : weights) {
            m.put(num, m.getOrDefault(num, 0) + 1);
        }
        weights = Arrays.stream(weights)
                    .distinct()  // 중복제거
                    .sorted()  // 오름차순 정렬
                    .toArray();
        
        double num2;
        Integer numCnt;
        long cnt = 0, sumCnt = 0;
        for(int num : weights) {
            cnt = 0;
            
            // a == (a x 3) / 2
            num2 = num * 3.0 / 2;
            numCnt = m.get((int) num2);
            if(isInt(num2) && numCnt != null) {
                cnt += numCnt;
            }
            
            // a == (a x 4) / 2
            num2 = num * 4.0 / 2;
            numCnt = m.get((int) num2);
            if(isInt(num2) && numCnt != null) {
                cnt += numCnt;
            }
            
            // a == (a x 4) / 3
            num2 = num * 4.0 / 3;
            numCnt = m.get((int) num2);
            if(isInt(num2) && numCnt != null) {
                cnt += numCnt;
            }
            
            numCnt = m.get(num);
            cnt *= numCnt;
            
            // a == a
            numCnt--;
            while(numCnt > 0) {
                cnt += numCnt;
                numCnt--;
            }
            
            sumCnt += cnt;
        }

        return sumCnt;
    }
}

public class PGS_시소짝꿍 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
