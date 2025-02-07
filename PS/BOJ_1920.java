// < 성공 방법 1 >
// - 알고리즘: Set (이 문제의 경우, 이분탐색보다 Set 방식이 더 빠름.)

import java.util.*;
import java.io.*;

public class BOJ_1920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();
        Set<Integer> s = new HashSet<>();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer stt = new StringTokenizer(br.readLine());
        while(n-- > 0) {
            s.add(Integer.parseInt(stt.nextToken()));
        }

        int m = Integer.parseInt(br.readLine());
        stt = new StringTokenizer(br.readLine());
        while(m-- > 0) {
            stb.append(s.contains(Integer.parseInt(stt.nextToken())) ? 1 : 0).append("\n");
        }

        System.out.print(stb.toString());
    }
}

/*
// < 성공 방법 2 >
// - 알고리즘: 이분 탐색

import java.util.*;
import java.io.*;

public class BOJ_1920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();
        List<Integer> list = new ArrayList<>();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer stt = new StringTokenizer(br.readLine());
        while(n-- > 0) {
            list.add(Integer.parseInt(stt.nextToken()));
        }
        Collections.sort(list);

        int m = Integer.parseInt(br.readLine());
        stt = new StringTokenizer(br.readLine());
        while(m-- > 0) {
            int findNum = Integer.parseInt(stt.nextToken());
            boolean isExist = (Collections.binarySearch(list, findNum) >= 0) ? true : false;
            stb.append(isExist ? 1 : 0).append("\n");
        }

        System.out.print(stb.toString());
    }
}
*/