import java.util.*;

// [ 조합 (Combination) with 백트래킹 ]
// - 개념 특징 :
// 조합이란 n개의 원소 중에서 r개를 뽑는 경우의 수를 의미한다.
// 이는 '순열처럼 순서가 바뀌어도 경우의수로 인정해주는것'과는 다르게 순서가 바뀌면 중복처리 되어 인정안된다.
// - 코드 특징 :
// visited 배열 대신 start 인덱스를 운용함.
// 재귀 파라미터에서 cnt는 cnt+1 해주며, start 인덱스는 i+1 해줌. (참고로 i+1이 아닌 start+1은 틀림.)

// [ 조합 vs 중복조합 vs 순열 vs 중복순열 vs 부분집합 ]
// - 조합 : start인덱스로 '앞에꺼 제외'해가면서 뽑기때문에, visited배열 필요없음. (순서X -> start인덱스 사용, 중복X -> start인덱스 사용)
// - 중복조합 : start인덱스로 '앞에꺼 포함'해가면서 뽑기때문에, visited배열 필요없음. (순서X -> start인덱스 사용, 중복O -> start인덱스 방식변경사용)
// - 순열 : visited배열로 뽑기여부 확인해가면서 뽑기때문에, start인덱스 필요없음. (순서O -> 그대로 사용, 중복X -> visited배열 사용)
// - 중복순열 : 중복가능이므로 visited배열을 사용하지 않으며, start인덱스도 필요없음. (순서O -> 그대로 사용, 중복O -> visited배열 미사용)
// - 부분집합 : visited배열과 start인덱스를 모두 운용하지 않으며, 재귀함수 내에서 for문을 사용하지않음. (모든원소를 넣고 빼봄 -> 재귀for문 미사용)
// etc) 예시로 출력에 '1 9'와 '9 1'이 모두 존재하면 조합이 아닌 순열임. (역순은 해당 X. 모든 순열 문제에 '1 9'와 '9 1'이 모두 존재하는것은 아님.)

public class Combination_SHJ {
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
            comb(i+1, cnt+1);  // comb(start+1, cnt+1); 로 작성하면 틀림. (선택한 현재 원소를 제외하고 뒤의 것들까지 선택 가능.)
            
            selected.remove(selected.size()-1);
        }
    }

    public static void main(String[] args) {
        n = 4; m = 2;  // 4C2
        arr = new int[n];

        // 입력
        int[] inputArr = {1,7,8,9};
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

[1, 7]
[1, 8]
[1, 9]
[7, 8]
[7, 9]
[8, 9]
==> 가능한 경우의 수 : 6
 */