// < 실패 방법 1 >
// - 답은 맞으나, 효율성 테스트 3/5로 실패.
// - 알고리즘: HashMap (실패방법2 보다 속도 빠름.)

import java.util.*;

class Solution {
    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        Map<String, Integer> m = new HashMap<>();  // <word, count>
        
        for(String word : words) {
            int len = word.length();
            String str = "?".repeat(len);
            m.put(str, m.getOrDefault(str, 0) + 1);
            
            StringBuilder stb = new StringBuilder(word);
            if(len == 1) continue;
            for(int i=0; i<=len-2; i++) {
                stb.setCharAt(i, '?');
                str = stb.toString();
                m.put(str, m.getOrDefault(str, 0) + 1);
            }
            
            stb = new StringBuilder(word);
            for(int i=len-1; i>=1; i--) {
                stb.setCharAt(i, '?');
                str = stb.toString();
                m.put(str, m.getOrDefault(str, 0) + 1);
            }
        }
        
        int idx = 0;
        for(String quer : queries) {
            int cnt = m.getOrDefault(quer, 0);
            answer[idx++] = cnt;
        }
        
        return answer;
    }
}

/*
// < 실패 방법 2 >
// - 답은 맞으나, 효율성 테스트 3/5로 실패.
// - 알고리즘: HashMap + Pair 클래스 (실패방법1 보다 속도 느림.)

import java.util.*;

class Solution {
    public static class Pair {
        public int len;  // 전체?길이:+양수, 앞부분?길이:+양수, 뒷부분?길이:-음수
        public String str;  // 중간의 ?가 아닌 문자열
        
        public Pair(int len, String str) {
            this.len = len;
            this.str = str;
        }
        
        @Override
        public boolean equals(Object obj) {
            // 객체 및 클래스 비교
            if(this == obj) {  // 동일 객체 체크 (메모리 주소)
                return true;
            }
            if(obj == null || this.getClass() != obj.getClass()) {  // null 체크 & 클래스 비교
                return false;
            }
            
            // 내부 필드 비교
            Pair b = (Pair) obj;  // this를 a로, b를 b로 보면됨.
            if(this.len == b.len && this.str.equals(b.str)) {
                return true;
            }
            else {
                return false;
            }
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(len, str);  // 해시 코드 생성 (HashSet,HashMap 등의 key 중복방지.)
        }
    }
    
    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        Map<Pair, Integer> m = new HashMap<>();  // <word, count>
        
        for(String word : words) {
            int len = word.length();
            String str = "";
            Pair p = new Pair(len, str);
            m.put(p, m.getOrDefault(p, 0) + 1);
            
            StringBuilder stb = new StringBuilder(word);
            if(len == 1) continue;
            for(int i=0; i<=len-2; i++) {
                stb.deleteCharAt(0);
                str = stb.toString();
                p = new Pair(len-str.length(), str);
                m.put(p, m.getOrDefault(p, 0) + 1);
            }
            
            stb = new StringBuilder(word);
            for(int i=len-1; i>=1; i--) {
                stb.deleteCharAt(stb.length()-1);
                str = stb.toString();
                p = new Pair(-(len-str.length()), str);
                m.put(p, m.getOrDefault(p, 0) + 1);
            }
        }
        
        int idx = 0;
        for(String quer : queries) {
            int originLen = quer.length();
            int len = (quer.charAt(0) == '?') ? 1 : -1;
            String afterQuer = quer.replace("?", "");
            len *= (originLen-afterQuer.length());
            
            Pair p = new Pair(len, afterQuer);
            int cnt = m.getOrDefault(p, 0);
            answer[idx++] = cnt;
        }
        
        return answer;
    }
}
*/

public class PGS_가사검색_Fail {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
