import java.util.*;

// - 문제 Tip: 시간복잡도 O(26 x 20) = O(최대 520회) = 1초 미만으로 연산 가능.

class Solution {
    public int solution(String skill, String[] skill_trees) {
        Set<Character> s = new HashSet<>();
        Queue<Character> originQu = new LinkedList<>();
        for(char ch : skill.toCharArray()) {
            s.add(ch);
            originQu.offer(ch);
        }
        
        int answer = 0;
        for(String skillTree : skill_trees) {
            Queue<Character> qu = new LinkedList<>(originQu);
            boolean isCanSkill = true;
            
            for(char ch : skillTree.toCharArray()) {
                if(s.contains(ch)) {
                    if(qu.peek() == ch) {
                        qu.poll();
                        if(qu.isEmpty()) break;
                    }
                    else {
                        isCanSkill = false;
                        break;
                    }
                }
            }
            
            if(isCanSkill) answer++;
        }
        
        return answer;
    }
}

public class PGS_스킬트리 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
