import java.util.*;
import java.util.stream.*;  // Collectors

// [ Pair 클래스 구현 및 커스텀 정렬 ]

class Solution {
    public static class Pair implements Comparable<Pair> {
        public int num;
        public int dist;
        
        public Pair(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }
        
        public int getNum() {  // 사실 이거 구현없이도, 그저 p.num으로도 조회 가능함.
            return num;
        }
        
        @Override
        public int compareTo(Pair b) {  // 반환값이 boolean이 아닌 int임.
            if(this.dist != b.dist) {  // this를 a로, b를 b로 보면됨.
                return this.dist - b.dist;  // dist 기준 오름차순
            }
            else {
                return b.num - this.num;  // dist 같을때, num 기준 내림차순
            }
        }
    }
    
    public int[] solution(int[] numlist, int n) {
        List<Pair> pList = new ArrayList<>();
        
        for(int num : numlist) {
            int dist = Math.abs(n - num);
            Pair p = new Pair(num, dist);
            pList.add(p);
        }
        Collections.sort(pList);  // 커스텀 정렬됨.
        
        List<Integer> rankList = pList.stream()
            .map(Pair::getNum)  // 또는 .map(p -> p.num)
            .collect(Collectors.toList());
        
        int[] answer = rankList.stream()
            .mapToInt(i->i)
            .toArray();
        return answer;
    }
}

public class Pair_PGS_특이한정렬 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] answer1 = solution.solution(new int[]{1,2,3,4,5,6}, 4);
        int[] answer2 = solution.solution(new int[]{10000,20,36,47,40,6,10,7000}, 30);

        System.out.println(Arrays.toString(answer1));  // => [4, 5, 3, 6, 2, 1] 출력
        System.out.println(Arrays.toString(answer2));  // => [36, 40, 20, 47, 10, 6, 7000, 10000] 출력
    }
}