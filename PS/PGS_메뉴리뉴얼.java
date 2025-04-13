import java.util.*;

// - 알고리즘: 조합

class Solution {
    public static StringBuilder selectedStb = new StringBuilder();
    public static Map<String, Integer> answerM = new HashMap<>();  // <selectedStr, cnt>
    
    public static void comb(int start, int cnt, String str, int m) {
        if(cnt == m) {
            String selectedStr = selectedStb.toString();
            answerM.put(selectedStr, answerM.getOrDefault(selectedStr, 0) + 1);
            return;
        }

        for(int i=start; i<str.length(); i++) {
            selectedStb.append(str.charAt(i));
            comb(i+1, cnt+1, str, m);
            
            selectedStb.deleteCharAt(selectedStb.length()-1);
        }
    }
    
    public String[] solution(String[] orders, int[] course) {
        for(String order : orders) {
            char[] chArr = order.toCharArray();
            Arrays.sort(chArr);  // 조합 구하기 전에 문자열 정렬부터.
            String sortedOrder = String.valueOf(chArr);
            
            for(int cou : course) {
                comb(0, 0, sortedOrder, cou);
                selectedStb = new StringBuilder();
            }
        }
        
        Set<String> answerS = new TreeSet<>();
        for(int cou : course) {
            int maxCnt = -1;
            for(Map.Entry<String,Integer> entry : answerM.entrySet()) {
                if(entry.getKey().length() == cou) {
                    maxCnt = Math.max(maxCnt, entry.getValue());
                }
            }
            
            if(maxCnt >= 2) {  // 최소 2명 이상이 주문한 조합이어야함.
                for(Map.Entry<String,Integer> entry : answerM.entrySet()) {
                    if(entry.getKey().length() == cou && entry.getValue() == maxCnt) {
                        answerS.add(entry.getKey());
                    }
                }
            }
        }
        
        String[] answer = new String[answerS.size()];
        int idx = 0;
        for(String element : answerS) {
            answer[idx++] = element;
        }
        return answer;
    }
}

public class PGS_메뉴리뉴얼 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
