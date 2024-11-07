import java.util.*;
import java.util.stream.*;

// - 알고리즘: 조합 (주석을 코드로 재활성화시, 부분집합과 유사해짐.)

class Solution {
    public static char[] arr = {'A','E','I','O','U'};
    public static StringBuilder stb = new StringBuilder();
    public static List<String> list = new ArrayList<>();
    
    public static void comb(int cnt) {
        if(cnt == 5) {
            return;
        }
        
        for(char ch : arr) {
            stb.append(ch);
            list.add(stb.toString());
            comb(cnt+1);
            
            stb.deleteCharAt(stb.length()-1);  // 밑의 2줄(Subset방식)을 제거하면 성능 개선 가능.
            // if(stb.length() != 0) list.add(stb.toString());
            // comb(cnt+1);
        }
    }
    
    public int solution(String word) {
        comb(0);
        list = list.stream()
            .distinct()
            .sorted()
            .collect(Collectors.toList());
        
        int findIdx = list.indexOf(word);
        return findIdx+1;
    }
}

public class PGS_모음사전 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
