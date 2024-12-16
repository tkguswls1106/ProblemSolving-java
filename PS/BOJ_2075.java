// < 성공 방법 1 >
// - 알고리즘: 우선순위큐

import java.util.*;
import java.io.*;

public class BOJ_2075 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();  // 최소힙
        StringTokenizer stt = new StringTokenizer(br.readLine());
        while(stt.hasMoreTokens()) {
            pq.offer(Integer.parseInt(stt.nextToken()));
        }
        n--;

        while(n-- > 0) {
            stt = new StringTokenizer(br.readLine());
            while(stt.hasMoreTokens()) {
                pq.offer(Integer.parseInt(stt.nextToken()));
                pq.poll();  // 메모리 추가 절약.
            }
        }

        System.out.print(pq.poll());
    }
}

/*
// < 성공 방법 2 >
// - 알고리즘: 정렬
// - 메모리 초과 계산:
// int형은 4바이트이므로, 2차원 리스트 공간복잡도인 O(n^2)
// = 1500x1500x4바이트 = 900만 바이트 (900만 B)
// = 900만/1024 킬로바이트 = 약 900만/1000 킬로바이트 = 약 9000 킬로바이트 (9000 KB)
// = 9000/1024 메가바이트 = 약 9000/1000 메가바이트 = 약 9메가바이트(9 MB)
// 9MB이므로 그렇다면 사실상 문제에서 주어진 메모리 제한 12MB를 넘지않는다.
// ==> 그저 배열 및 정렬만으로도 이 문제를 풀 수 있다는 의미.

import java.util.*;
import java.io.*;

public class BOJ_2075 {
    public static List<Integer> list = new ArrayList<>();  // 크기가 너무 큰 리스트이므로, 전역으로 선언.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int originN = n;

        StringTokenizer stt;
        while(n-- > 0) {
            stt = new StringTokenizer(br.readLine());
            while(stt.hasMoreTokens()) {
                list.add(Integer.parseInt(stt.nextToken()));
            }
        }
        Collections.sort(list, Collections.reverseOrder());

        System.out.print(list.get(originN-1));
    }
}
*/