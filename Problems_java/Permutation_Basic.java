import java.util.*;

// [ 순열(Permutation) - 기초 정리 ]
// - 개념 특징 :
// 순열이란 서로 다른 n개의 원소에서 r개를 뽑아 한 줄로 세우는 부분수열 경우의 수를 의미한다.
// 이는 '조합처럼 순서가 바뀌면 경우의수로 인정안해주는것'과는 다르게 순서가 바뀌어도 인정된다.
// - 코드 특징 :
// start 인덱스 대신 visited 배열을 운용함.

public class Permutation_Basic {
    public static int n, m;  // 전체 개수, 선택 개수
    public static int[] arr, visited;  // 전체 요소 배열, 방문여부 배열
    public static LinkedList<Integer> selected = new LinkedList<>();  // 선택 요소 리스트
    public static int answer = 0;  // 가능한 경우의 수

    public static void perm(int cnt) {
        if(cnt == m) {
            System.out.println(selected.toString());
            answer++;
            return;
        }

        for(int i=0; i<n; i++) {
            if(visited[i] == 1) continue;
            
            selected.add(arr[i]);
            visited[i] = 1;
            perm(cnt+1);
            
            selected.removeLast();
            visited[i] = 0;
        }
    }

    public static void main(String[] args) {
        n = 4; m = 2;  // 4P2
        arr = new int[n];
        visited = new int[n];  // 자동으로 0으로 초기화.

        // 입력
        int[] inputArr = {1,7,8,9};  // 정렬되어있지않아도 상관없음.
        for(int i=0; i<n; i++) {
            int inputNum = inputArr[i];  // n번만큼 숫자를 입력받았다고 가정하고자 작성함.
            arr[i] = inputNum;
        }

        perm(0);
        System.out.printf("==> 가능한 경우의 수 : %d\n", answer);
    }
}

/*
< 출력 결과 >

[1, 7]
[1, 8]
[1, 9]
[7, 1]
[7, 8]
[7, 9]
[8, 1]
[8, 7]
[8, 9]
[9, 1]
[9, 7]
[9, 8]
==> 가능한 경우의 수 : 12
 */