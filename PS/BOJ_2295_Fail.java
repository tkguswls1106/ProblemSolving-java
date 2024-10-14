// < 실패 방법 >
// - 답은 맞는듯하나, 메모리 초과로 실패.
// - 알고리즘: 중복조합

import java.util.*;
import java.io.*;

public class BOJ_2295_Fail {
    public static int n;
    public static int m = 4;
    public static int[] arr;
    public static List<Integer> selected = new ArrayList<>();
    public static long answer = -((int) 1e9);

    public static void comb(int start, int cnt) {
        if(cnt == m) {
            long sum = selected.get(0) + selected.get(1) + selected.get(2);
            if(sum == selected.get(3)) {
                answer = Math.max(answer, sum);
            }
            return;
        }

        for(int i=start; i<arr.length; i++) {
            selected.add(arr[i]);
            comb(i, cnt+1);  // 만약 중복이 아닌 일반적인 조합이었다면, i+1 이어야함.

            selected.remove(selected.size()-1);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        Set<Integer> s = new TreeSet<>();
        for(int i=0; i<n; i++) {
            s.add(Integer.parseInt(br.readLine()));
        }
        arr = s.stream().mapToInt(i->i).toArray();

        comb(0, 0);
        System.out.print(answer);
    }
}
