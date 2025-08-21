import java.util.*;

// - 알고리즘 : 순열
// - 시간복잡도 :
// 최대 O(8 x 7 x 6 ... 3 x 2 x 1) 이므로, 완전탐색으로 풀이 가능.
// => 순열 알고리즘 사용.

class Solution {
    public static char[] frArr = new char[]{'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    public static String[] checkArr;
    public static Map<Character, Integer> m;  // <프렌즈, 변경된 인덱스>
    public static boolean[] visited;
    public static int answer;
    
    public static boolean check() {
        char op;
        int frIdx1, frIdx2, dist, calDist;
        for(String str : checkArr) {
            frIdx1 = m.get(str.charAt(0));
            frIdx2 = m.get(str.charAt(2));
            op = str.charAt(3);
            dist = (str.charAt(4) - '0') + 1;
            
            calDist = Math.abs(frIdx1 - frIdx2);
            if(op == '<') {
                if(!(calDist < dist)) return false;
            }
            else if(op == '>') {
                if(!(calDist > dist)) return false;
            }
            else {  // (op == '=')
                if(!(calDist == dist)) return false;
            }
        }
        
        return true;
    }
    
    public static void perm(int cnt) {
        if(cnt == 8) {            
            if(check() == true) answer++;
            return;
        }
        
        for(int i=0; i<8; i++) {
            if(visited[i] == true) continue;
            char fr = frArr[i];
            // int prevFrIfx = m.get(fr);
            
            m.put(fr, cnt);  // 주의: 'm.put(fr, i);'로 작성하면 틀리니 조심할것.
            visited[i] = true;
            perm(cnt+1);
            
            // m.put(fr, prevFrIfx);
            visited[i] = false;
        }
    }
    
    public int solution(int n, String[] data) {
        checkArr = data;
        m = new HashMap<>();
        visited = new boolean[8];
        answer = 0;
        
        // for(int i=0; i<8; i++) {
        //     m.put(frArr[i], i);
        // }

        perm(0);
        
        return answer;
    }
}

public class PGS_단체사진찍기 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
