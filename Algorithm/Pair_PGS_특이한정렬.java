import java.util.*;
import java.util.stream.*;  // Collectors

// [ Pair 클래스 구현 및 커스텀 정렬 ]

// - 주의사항 :
// Pair 클래스 작성 시, 만약 정렬이 필요없어 '@Override compareTo()'를 미구현한다면, 'implements Comparable<Pair>'도 함께 제거해야만 함.
// 참고로 이 문제의 경우, 사실 equals()와 hashCode() 메소드의 오버라이딩은 필요없음. 단지 사용법을 명세하여 익히고자 표기하였음.

// - Set,Map 컬렉션에서 객체의 중복검사를 위해, equals()와 함께 hashCode()도 재정의해야 하는 이유 :
// hashCode() 메소드는 객체의 필드'값'을 기반으로 해시코드를 부여하기에, 값이 모두 같다면 동일한 해시코드가 부여됨.
// 객체들의 비교 과정으로, 서로의 hashCode() 반환값이 일치한 이후 equals() 반환값이 참일때만 같은 객체라고 판단함.
// 따라서 equals() 재정의로 필드값 비교에 성공해도, 막상 hashCode()도 함께 재정의하지 않으면, 컬렉션에서 중복검사에 걸리지않아 또다시 중복 add 될 수 있음.

// - equals() 및 hashCode() 특이사항 :
// 만약 Set,Map 등 컬렉션에서의 중복검사 용도가 아닌, 단순히 두 객체의 필드값 비교를 목적으로 한다면,
// hashCode() 없이 equals()만 재정의해도 'pairObj1.equals(pairObj2) == true'처럼 성공적인 '값'비교가 가능함.

class Solution {
    public static class Pair implements Comparable<Pair> {  // !!! 주의사항: 주의사항을 위에 기재해두었음. !!!
        public int num;
        public int dist;
        
        public Pair(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }
        
        public int getNum() {  // 사실 이거 구현없이도, 그저 p.num으로도 조회 가능함.
            return num;
        }

        // - equals 오버라이딩 경우: HashSet, HashMap (!!! 사실 이 문제에선 필요 X !!!)
        @Override
        public boolean equals(Object obj) {
            // 객체 및 클래스 비교
            if(this == obj) {  // 동일 객체 체크 (메모리 주소)
                return true;
            }
            if(obj == null || this.getClass() != obj.getClass()) {  // null 체크 & 클래스 비교
                return false;
            }
            
            // 내부 필드값 비교
            Pair b = (Pair) obj;
            if(this.num == b.num && this.dist == b.dist) {  // this를 a로, b를 b로 보면됨.
                return true;
            }
            else {
                return false;
            }
        }
        
        // - hashCode 오버라이딩 경우: HashSet, HashMap (!!! 사실 이 문제에선 필요 X !!!)
        @Override
        public int hashCode() {
            return Objects.hash(num, dist);  // 해시 코드 생성 (필드'값'을 기준으로 부여. HashSet,HashMap 등의 key 중복방지.)
        }
        
        // - compareTo 오버라이딩 경우: 커스텀 정렬, TreeSet, TreeMap
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