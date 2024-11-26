import java.util.*;

// - 알고리즘: 소수 판별 ('에라토스테네스의 체'와 유사함)

class Solution {
    public int solution(int n, int k) {
        String str = Integer.toString(n, k);
        String[] strArr = str.split("0");
        
        Map<Long, Integer> m = new HashMap<>();
        for(String strNum : strArr) {
            if(strNum.length() == 0) continue;
            long num = Long.parseLong(strNum);
            m.put(num, m.getOrDefault(num, 0) + 1);
        }
        
        int primeCnt = 0;
        for(long key : m.keySet()) {
            if(key < 2) continue;
            
            boolean isPrime = true;
            for(int i=2; i<=Math.sqrt(key); i++) {  // 소수 판별 (또는 'i*i<=key')
                if(key%i == 0) {
                    isPrime = false;
                    break;
                }
            }
            
            if(isPrime == true) {
                int value = m.get(key);
                primeCnt += value;
            }
        }
   
        return primeCnt;
    }
}

public class PGS_k진수에서소수개수구하기 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
