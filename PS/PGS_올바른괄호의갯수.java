import java.util.*;

// - 알고리즘: 백트래킹
// - 또다른 풀이법:
// https://happybplus.tistory.com/921
// 애초에 넣어진것들중, )는 (보다 많이 들어가면 안된다. 그럼 올바른 괄호가 아니기 때문이다.
// 그리하여, 위의 특징과 dfs를 활용하여 다른 풀이법으로도 풀 수 있다.

class Solution {
    public static int k, answer = 0;
    public static Stack<Integer> st = new Stack<>();  // (:1, ):2
    
    public static void backTracking(int cnt) {  // 백트래킹
        if(cnt == k) {
            if(st.size() == 0) answer++;
            return;
        }
        
        if(st.isEmpty()) {
            st.push(1);
            backTracking(cnt+1);
            st.pop();  // 이전 상태로 다시 되돌리기.
        }
        else {  // if(st.peek() == 1). 2는 쌓이지못하고 바로 pop되어, 남은게 2인 경우는 없음.
            // - 1 push
            st.push(1);
            backTracking(cnt+1);
            st.pop();  // 이전 상태로 다시 되돌리기.

            // - 2 push & pop pop
            st.pop();
            backTracking(cnt+1);
            st.push(1);  // 이전 상태로 다시 되돌리기.
        }
    }
    
    public int solution(int n) {
        k = n * 2;
        backTracking(0);
        
        return answer;
    }
}

public class PGS_올바른괄호의갯수 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
