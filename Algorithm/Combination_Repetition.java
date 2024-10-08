import java.util.*;

// [ 중복조합 (Combination with Repetition) ]
// - 개념 특징 :
// 중복조합이란 중복을 허용한채로 n개의 원소 중에서 r개를 뽑는 경우의 수를 의미한다.
// 순서가 없게 뽑는 것은 일반적인 조합과 동일하지만, 같은 원소를 중복해서 뽑을 수 있다는 것에 차이가 있다.
// 즉, 111,222,333 처럼 같은걸 여러번 뽑을 수 있다.
// - 코드 특징 :
// visited 배열 대신 start 인덱스를 운용함.
// 재귀 파라미터에서 cnt를 cnt+1 해주는것은 일반적인 조합과 동일함.
// 다만 차이점으로, 중복조합은 start 인덱스를 i+1 하지않음. (선택한 현재 원소를 포함하여 뒤의 것들까지 선택 가능.)

// [ 조합 vs 중복조합 vs 순열 vs 중복순열 vs 부분집합 ]
// - 조합 : start인덱스로 '앞에꺼 제외'해가면서 뽑기때문에, visited배열 필요없음. (순서X -> start인덱스 사용, 중복X -> start인덱스 사용)
// - 중복조합 : start인덱스로 '앞에꺼 포함'해가면서 뽑기때문에, visited배열 필요없음. (순서X -> start인덱스 사용, 중복O -> start인덱스 방식변경사용)
// - 순열 : visited배열로 뽑기여부 확인해가면서 뽑기때문에, start인덱스 필요없음. (순서O -> 그대로 사용, 중복X -> visited배열 사용)
// - 중복순열 : 중복가능이므로 visited배열을 사용하지 않으며, start인덱스도 필요없음. (순서O -> 그대로 사용, 중복O -> visited배열 미사용)
// - 부분집합 : visited배열과 start인덱스를 모두 운용하지 않으며, 재귀함수 내에서 for문을 사용하지않음. (모든원소를 넣고 빼봄 -> 재귀for문 미사용)

public class Combination_Repetition {
    public static int n, m;  // 전체 개수, 선택 개수
    public static int[] arr;  // 전체 요소 배열
    public static List<Integer> selected = new ArrayList<>();  // 선택 요소 리스트
    public static int answer = 0;  // 가능한 경우의 수

    public static void comb(int start, int cnt) {
        if(cnt == m) {
            System.out.println(selected.toString());
            answer++;
            return;
        }

        for(int i=start; i<n; i++) {
            selected.add(arr[i]);
            comb(i, cnt+1);  // 일반적인 조합과는 다르게, start 인덱스를 i+1 하지않음. (선택한 현재 원소를 포함하여 뒤의 것들까지 선택 가능.)
            
            selected.remove(selected.size()-1);
        }
    }

    public static void main(String[] args) {
        n = 4; m = 2;
        arr = new int[n];

        // 입력
        int[] inputArr = {1,7,8,9};  // 정렬되어있지않아도 상관없음.
        for(int i=0; i<n; i++) {
            int inputNum = inputArr[i];  // n번만큼 숫자를 입력받았다고 가정하고자 작성함.
            arr[i] = inputNum;
        }

        comb(0, 0);
        System.out.printf("==> 가능한 경우의 수 : %d\n", answer);
    }
}

/*
< 출력 결과 >

[1, 1]
[1, 7]
[1, 8]
[1, 9]
[7, 7]
[7, 8]
[7, 9]
[8, 8]
[8, 9]
[9, 9]
==> 가능한 경우의 수 : 10
 */