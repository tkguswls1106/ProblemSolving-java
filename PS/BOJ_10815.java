// < 성공 방법 1 >
// - 알고리즘: Set

import java.util.*;
import java.io.*;

public class BOJ_10815 {
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
            stb.append(s.contains(Integer.parseInt(stt.nextToken())) ? 1 : 0).append(" ");
        }

        System.out.print(stb.toString());
    }
}

/*
// < 성공 방법 2 >
// - 알고리즘: 이분 탐색

import java.util.*;
import java.io.*;

public class BOJ_10815 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(stt.nextToken());
        }
        Arrays.sort(arr);

        int m = Integer.parseInt(br.readLine());
        stt = new StringTokenizer(br.readLine());
        while(m-- > 0) {
            int findNum = Integer.parseInt(stt.nextToken());
            boolean isExist = Arrays.binarySearch(arr, findNum) >= 0;
            stb.append(isExist ? 1 : 0).append(" ");
        }

        System.out.print(stb.toString());
    }
}
*/