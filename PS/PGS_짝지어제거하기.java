import java.util.*;

class Solution {
    public int solution(String s) {
        Stack<Character> st = new Stack<>();
        
        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if(st.isEmpty()) st.push(ch);
            else {
                if(st.peek() == ch) st.pop();
                else st.push(ch);
            }
        }
        
        if(st.isEmpty()) return 1;
        else return 0;
    }
}

public class PGS_짝지어제거하기 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
