// < 성공 방법 1 >
// - 알고리즘: 조합 + 브루트포스

import java.util.*;
import java.io.*;

public class BOJ_14889 {
    public static int n, m;
    public static int[][] arr;
    public static List<Integer> selected = new ArrayList<>();  // 인덱스를 담음 (인덱스 = 선수번호-1)
    public static int minScore = (int) 1e9;

    public static int getScore(int i, int j) {  // 파라미터는 인덱스
        return arr[i][j] + arr[j][i];
    }

    public static void comb(int start, int cnt) {
        // !!! 성능개선 버전 !!!
        // : 비록 코드는 길어졌지만, 개선 이전에는 모든 n명에 대해 이중반복문으로 contains와 함께 체킹했어야했음.
        // (시간 1264ms -> 548ms)
        if(cnt == m) {
            Set<Integer> selectedSet = new HashSet<>();
            int sumScore = 0;
            for(int i=0; i<selected.size()-1; i++) {
                int iValue = selected.get(i);
                selectedSet.add(iValue);
                for(int j=i+1; j<selected.size(); j++) {
                    sumScore += getScore(iValue, selected.get(j));
                }
            }
            selectedSet.add(selected.get(selected.size()-1));
            
            int[] notSelected = new int[m];
            int idx = 0;
            for(int i=0; i<n; i++) {
                if(!selectedSet.contains(i)) {
                    notSelected[idx++] = i;
                }
            }

            for(int i=0; i<notSelected.length-1; i++) {
                for(int j=i+1; j<notSelected.length; j++) {
                    sumScore -= getScore(notSelected[i], notSelected[j]);
                }
            }
            sumScore = Math.abs(sumScore);
            
            minScore = Math.min(minScore, sumScore);
            return;
        }

        for(int i=start; i<n; i++) {
            selected.add(i);
            comb(i+1, cnt+1);

            selected.remove(selected.size()-1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = n / 2;
        arr = new int[n][n];

        StringTokenizer stt;
        for(int i=0; i<n; i++) {
            stt = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                arr[i][j] = Integer.parseInt(stt.nextToken());
            }
        }

        comb(0, 0);
        System.out.print(minScore);
    }
}

/*
// < 성공 방법 2 >
// - 알고리즘: 조합 + 브루트포스

import java.util.*;
import java.io.*;

public class BOJ_14889 {
    public static int n, m;
    public static int[][] arr;
    public static List<Integer> selected = new ArrayList<>();  // 인덱스를 담음 (인덱스 = 선수번호-1)
    public static int minScore = (int) 1e9;

    public static int getScore(int i, int j) {  // 파라미터는 인덱스
        return arr[i][j] + arr[j][i];
    }

    public static void comb(int start, int cnt) {
        if(cnt == m) {
            Set<Integer> selectedSet = new HashSet<>(selected);
            int sumScore = 0;
            for(int i=0; i<n-1; i++) {
                for(int j=i+1; j<n; j++) {
                    if(selectedSet.contains(i) && selectedSet.contains(j)) {
                        sumScore += getScore(i, j);
                    }
                    else if(!selectedSet.contains(i) && !selectedSet.contains(j)) {
                        sumScore -= getScore(i, j);
                    }
                }
            }
            sumScore = Math.abs(sumScore);
            
            minScore = Math.min(minScore, sumScore);
            return;
        }

        for(int i=start; i<n; i++) {
            selected.add(i);
            comb(i+1, cnt+1);

            selected.remove(selected.size()-1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = n / 2;
        arr = new int[n][n];

        StringTokenizer stt;
        for(int i=0; i<n; i++) {
            stt = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                arr[i][j] = Integer.parseInt(stt.nextToken());
            }
        }

        comb(0, 0);
        System.out.print(minScore);
    }
}
*/