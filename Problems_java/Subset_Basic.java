import java.util.*;

// [ 부분집합(Subset) - 기초 정리 ]
// - 개념 특징 :
// 부분집합이란 공집합을 포함하여 한 집합에 포함될 수 있는 모든 집합들을 의미한다.
// 총 원소의 수가 n개라면, 부분집합의 수는 2^n 이다.
// 참고로 부분집합은 순열보단 조합에 가깝다.
// - 코드 특징 :
// visited 배열과 start 인덱스 모두 운용하지 않음.
// 다만 차이점으로, 재귀함수에서 for문을 활용하지 않음. 그 대신 cnt가 인덱스 역할도 하며 모든 원소를 넣고 빼봄.
// 전체 개수인 n은 활용하지만, 선택 개수인 m은 필요없음.

// [ 조합 vs 중복조합 vs 순열 vs 중복순열 vs 부분집합 ]
// - 조합 : start인덱스로 '앞에꺼 제외'해가면서 뽑기때문에, visited배열 필요없음. (순서X -> start인덱스 사용, 중복X -> start인덱스 사용)
// - 중복조합 : start인덱스로 '앞에꺼 포함'해가면서 뽑기때문에, visited배열 필요없음. (순서X -> start인덱스 사용, 중복O -> start인덱스 방식변경사용)
// - 순열 : visited배열로 뽑기여부 확인해가면서 뽑기때문에, start인덱스 필요없음. (순서O -> 그대로 사용, 중복X -> visited배열 사용)
// - 중복순열 : 중복가능이므로 visited배열을 사용하지 않으며, start인덱스도 필요없음. (순서O -> 그대로 사용, 중복O -> visited배열 미사용)
// - 부분집합 : visited배열과 start인덱스를 모두 운용하지 않으며, 재귀함수 내에서 for문을 사용하지않음. (모든원소를 넣고 빼봄 -> 재귀for문 미사용)

public class Subset_Basic {
    public static int n;  // 전체 개수
    public static int[] arr;  // 전체 요소 배열
    public static LinkedList<Integer> selected = new LinkedList<>();  // 선택 요소 리스트
    public static int answer = 0;  // 가능한 경우의 수

    public static void sub(int cnt) {
        if(cnt == n) {  // m이 아닌 n을 사용함.
            if(selected.isEmpty()) System.out.println("[X]");  // 공집합을 의미.
            else System.out.println(selected.toString());
            answer++;
            return;
        }
        
        // 재귀함수에서 for문을 활용하지 않음.
        selected.add(arr[cnt]);  // 부분집합 코드에서는 cnt가 인덱스 역할도 하며 모든 원소를 넣고 빼봄.
        sub(cnt+1);

        selected.removeLast();
        sub(cnt+1);  // 부분집합은 여기서 한번더 호출함.
    }

    public static void main(String[] args) {
        n = 4;  // 선택개수인 m은 사용하지않음.
        arr = new int[n];

        // 입력
        int[] inputArr = {1,7,8,9};  // 정렬되어있지않아도 상관없음.
        for(int i=0; i<n; i++) {
            int inputNum = inputArr[i];  // n번만큼 숫자를 입력받았다고 가정하고자 작성함.
            arr[i] = inputNum;
        }

        sub(0);
        System.out.printf("==> 가능한 경우의 수 : %d\n", answer);  // 2^n 값 출력됨.
    }
}

/*
< 출력 결과 >

[1, 7, 8, 9]
[1, 7, 8]
[1, 7, 9]
[1, 7]
[1, 8, 9]
[1, 8]
[1, 9]
[1]
[7, 8, 9]
[7, 8]
[7, 9]
[7]
[8, 9]
[8]
[9]
[X]
==> 가능한 경우의 수 : 16
 */