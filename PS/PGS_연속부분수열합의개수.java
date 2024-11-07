import java.util.*;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> s = new HashSet<>();
        int len = elements.length;
        
        for(int i=0; i<len; i++) {
            int sum = 0;
            for(int j=0; j<len; j++) {
                int idx = i + j;
                if(idx >= len) idx %= len;
                sum += elements[idx];
                s.add(sum);
            }
        }
        
        return s.size();
    }
}

public class PGS_연속부분수열합의개수 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
