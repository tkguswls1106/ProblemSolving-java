import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        List<List<String>> list = new ArrayList<>();
        Set<String> s = new HashSet<>();
        
        List<String> li = new ArrayList<>();
        int cnt = 0;
        for(int i=0; i<words.length; i++) {
            String word = words[i];
            li.add(word);
            if(cnt < n-1) cnt++;
            else {
                list.add(new ArrayList<>(li));  // 새로운 리스트로 다시 변환해서 추가해야함.
                li.clear();
                cnt = 0;
            }
        }
        if(!li.isEmpty()) list.add(new ArrayList<>(li));
        
        char lastCh = words[0].charAt(0);
        for(int i=0; i<list.size(); i++) {
            for(int j=0; j<list.get(i).size(); j++) {
                String word = list.get(i).get(j);
                if(lastCh == word.charAt(0) && !s.contains(word)) {
                    s.add(word);
                    lastCh = word.charAt(word.length()-1);
                }
                else {
                    return new int[]{j + 1, i + 1};
                }
            }
        }
        
        return new int[]{0, 0};
    }
}

public class PGS_영어끝말잇기 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
