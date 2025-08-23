// < 성공 방법 1 >
// - 알고리즘: 중복조합

import java.util.*;
import java.io.*;

public class BOJ_15657 {
    public static int n, m;
    public static List<Integer> allList = new ArrayList<>();
    public static List<Integer> selected = new ArrayList<>();
    public static StringBuilder stb = new StringBuilder();

    public static void comb(int start, int cnt) {
        if(cnt == m) {
            for(int num : selected) {
                stb.append(num).append(" ");
            }
            stb.append("\n");
            return;
        }
        
        for(int i=start; i<n; i++) {
            selected.add(allList.get(i));
            comb(i, cnt+1);

            selected.remove(selected.size()-1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());

        stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            allList.add(Integer.parseInt(stt.nextToken()));
        }
        // 주의: 이 문제는 중복조합일뿐더러, 조건에 '입력될 N개의 자연수는 모두 다른 수'라고 명시되어 있으므로, 배열 내 중복제거는 필요 없음.
        //      만약 필요하게 된다면 'Combination_BOJ_15664' 문제의 풀이처럼 처리하면 됨.
        Collections.sort(allList);

        comb(0, 0);

        System.out.print(stb.toString());
    }
}

/*
// < 성공 방법 2 >
// - 알고리즘: 중복순열 + 오름차순

import java.util.*;
import java.io.*;

public class BOJ_15657 {
    public static int n, m;
    public static List<Integer> allList = new ArrayList<>();
    public static List<Integer> selected = new ArrayList<>();
    public static StringBuilder stb = new StringBuilder();

    public static void perm(int cnt) {
        if(cnt == m) {
            for(int num : selected) {
                stb.append(num).append(" ");
            }
            stb.append("\n");
            return;
        }
        
        for(int i=0; i<n; i++) {
            int num = allList.get(i);
            if(!selected.isEmpty() && selected.get(selected.size()-1) > num) continue;

            selected.add(num);
            perm(cnt+1);

            selected.remove(selected.size()-1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());

        stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            allList.add(Integer.parseInt(stt.nextToken()));
        }
        // 주의: 이 문제는 중복조합일뿐더러, 조건에 '입력될 N개의 자연수는 모두 다른 수'라고 명시되어 있으므로, 배열 내 중복제거는 필요 없음.
        //      만약 필요하게 된다면 'Combination_BOJ_15664' 문제의 풀이처럼 처리하면 됨.
        Collections.sort(allList);

        perm(0);

        System.out.print(stb.toString());
    }
}
*/