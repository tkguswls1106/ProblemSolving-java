import java.util.*;

// [ 조합(Combination) - 기초 정리 ]
// 조합이란 n개의 원소 중에서 r개를 뽑는 경우의 수를 의미한다.
// 이는 '순열처럼 순서가 바뀌어도 경우의수로 인정해주는것'과는 다르게 순서가 바뀌면 중복처리 되어 인정안된다.

public class Combination_Basic {
    public static int n, m;  // 전체 개수, 선택 개수
    public static int[] arr;  // 전체 요소 배열
    public static LinkedList<Integer> selected = new LinkedList<>();  // 선택 요소 리스트
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
            
            selected.removeLast();
        }
    }

    public static void main(String[] args) {
        n = 4; m = 2;  // 4C2
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

[1, 7]
[1, 8]
[1, 9]
[7, 8]
[7, 9]
[8, 9]
==> 가능한 경우의 수 : 6
 */