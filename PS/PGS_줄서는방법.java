import java.util.*;

// - 문제 함정:
// 1. 일반적인 순열로 풀면, 답은 맞아도 효율성에서 시간초과로 틀리게 됨.
// 2. selected를 배열이 아닌 리스트로 넣어서 답을 구하면, 답은 맞아도 효율성에서 시간초과로 틀리게 됨.
// - 문제 Tip:
// 맨앞자리가 동일할때 가능한 가짓수는 (n-1)!,(n-2)!,.. 가지가 가능함.

class Solution {
    public int[] solution(int n, long k) {
        List<Integer> list = new ArrayList<>();
        int[] selected = new int[n];

        long factor = 1;
        for(int i=1; i<=n; i++) {
            factor *= i;
            list.add(i);
        }
        k--;  // 값 자체가 아닌, 인덱스로 활용하기 위함임.
        
        int selectedIdx = 0;
        while(n > 0) {
            factor /= n;
            
            int idx = (int) (k / factor);
            int num = list.get(idx);
            selected[selectedIdx++] = num;  // 만약 이것을 ArrayList로 구현했다면, 효율성 시간초과로 틀림.
            list.remove(idx);
            
            k %= factor;
            n--;  // 다음 아랫자리를 바라보기.
        }
        
        return selected;
    }
}

public class PGS_줄서는방법 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
