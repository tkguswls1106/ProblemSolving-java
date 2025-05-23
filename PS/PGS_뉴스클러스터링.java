import java.util.*;

// - 알고리즘: 구현

class Solution {
    public static boolean isEng(char ch) {
        if('A' <= ch && ch <= 'Z') return true;
        else return false;
    }
    
    public static int checkLen(int len1, int len2) {  // -1 일때만 정상 반환.
        if(len1 == 0) {
            if(len2 == 0) return 65536;
            else return 0;
        }
        else if(len2 == 0) {  // && len1 != 0
            return 0;
        }
        return -1;
    }
    
    public static int getAnswer(Map<String, Integer> m1, Map<String, Integer> m2) {
        int gyo = 0;
        int hap = 0;
        for(Map.Entry<String, Integer> entry1 : m1.entrySet()) {
            String str1 = entry1.getKey();
            int cnt1 = entry1.getValue();
            Integer cnt2 = m2.get(str1);
            
            if(cnt2 == null) {
                hap += cnt1;
            }
            else if(cnt1 < cnt2) {
                gyo += cnt1;
                hap += cnt2;
                m2.remove(str1);
            }
            else {
                gyo += cnt2;
                hap += cnt1;
                m2.remove(str1);
            }
        }
        
        for(int cnt2 : m2.values()) {
            hap += cnt2;
        }
        
        // 자카드 유사도 = gyo / hap * 65536 / 1 = gyo * 65536 / hap
        return gyo * 65536 / hap;  // return (int) ((double) gyo / hap * 65536);
    }
    
    public int solution(String str1, String str2) {
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();

        Map<String, Integer> m1 = new HashMap<>();
        int len1 = 0;
        for(int i=1; i<str1.length(); i++) {
            char ch1 = str1.charAt(i-1);
            char ch2 = str1.charAt(i);
            if(isEng(ch2) == false) {
                i++;
                continue;
            }
            else if(isEng(ch1) == false) {
                continue;
            }
            
            String str = String.valueOf(ch1) + ch2;
            m1.put(str, m1.getOrDefault(str, 0) + 1);
            len1++;
        }
        
        Map<String, Integer> m2 = new HashMap<>();
        int len2 = 0;
        for(int i=1; i<str2.length(); i++) {
            char ch1 = str2.charAt(i-1);
            char ch2 = str2.charAt(i);
            if(isEng(ch2) == false) {
                i++;
                continue;
            }
            else if(isEng(ch1) == false) {
                continue;
            }
            
            String str = String.valueOf(ch1) + ch2;
            m2.put(str, m2.getOrDefault(str, 0) + 1);
            len2++;
        }
        
        int checkResult = checkLen(len1, len2);
        if(checkResult != -1) return checkResult;
        
        int answer = getAnswer(m1, m2);
        return answer;
    }
}

public class PGS_뉴스클러스터링 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
