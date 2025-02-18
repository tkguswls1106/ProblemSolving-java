import java.util.*;

// - 문제 Tip :
// 입력 s의 {}안에서는 순서가 변경될수있음. 단지 출력인 원본 result인 [] 순서는 고정되어있다는 뜻임.
// 그러므로, 단일값 {x}는 원본 순서상 가장 맨앞에 위치할 것임. 따라서 빈도수가 높을수록 원본의 앞쪽에 위치.
// => 입력 s 내부 {} 숫자들의 출현 빈도수를 내림차순으로 정렬하여 확인할것.

class Solution {
    public int[] solution(String s) {
        Map<Integer, Integer> m = new HashMap<>();
        
        s = s.replaceAll("[\\{\\}]", "");
        StringTokenizer stt = new StringTokenizer(s, ",");
        while(stt.hasMoreTokens()) {
            int num = Integer.parseInt(stt.nextToken());
            m.put(num, m.getOrDefault(num, 0) + 1);
        }
        
        List<Integer> list = new ArrayList<>(m.keySet());
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                int aCnt = m.get(a);
                int bCnt = m.get(b);
                return bCnt - aCnt;  // 빈도수 기준으로 내림차순 정렬
            }
        });
        
        int[] answer = list.stream()
                        .mapToInt(i->i)
                        .toArray();
        return answer;
    }
}

public class PGS_튜플 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
