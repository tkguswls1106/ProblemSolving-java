// < 성공 방법 1 >
// - 알고리즘: 이분탐색 (성공방법2 보다 속도 빠름.)

import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int cnt = 0;
        
        List<String> list = new ArrayList<>();
        for(int i=0; i<want.length; i++) {
            for(int j=0; j<number[i]; j++) {
                list.add(want[i]);
            }
        }
        Collections.sort(list);  // 이분탐색 전, 미리 정렬.
        
        List<String> list2;
        int len = discount.length;
        for(int i=0; i<len; i++) {
            int idx = i + 10;
            if(!(idx <= len)) break;
            
            list2 = new ArrayList<>(list);  // 리스트 얕은 복제
            for(int j=i; j<idx; j++) {
                int findIdx = Collections.binarySearch(list2, discount[j]);  // 이분탐색 실시.
                
                if(findIdx >= 0) list2.remove(findIdx);
                else break;
            }
            
            if(list2.isEmpty()) {
                cnt++;
            }
        }
        
        return cnt;
    }
}

/*
// < 성공 방법 2 >
// - 알고리즘: TreeMap (성공방법1 보다 속도 느림.)

import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int cnt = 0;
        
        Map<String, Integer> m = new TreeMap<>();
        for(int i=0; i<want.length; i++) {
            m.put(want[i], number[i]);
        }
        
        Map<String, Integer> m2;
        int len = discount.length;
        for(int i=0; i<len; i++) {
            int idx = i + 10;
            if(!(idx <= len)) break;
            
            m2 = new TreeMap<>();
            for(int j=i; j<idx; j++) {
                String str = discount[j];
                m2.put(str, m2.getOrDefault(str, 0) + 1);
            }
            
            boolean isSame = true;
            for(String key : m.keySet()) {
                if(m.get(key) != m2.get(key)) {
                    isSame = false;
                    break;
                }
            }
            
            if(isSame) {
                cnt++;
            }
        }
        
        return cnt;
    }
}
*/

public class PGS_할인행사 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
