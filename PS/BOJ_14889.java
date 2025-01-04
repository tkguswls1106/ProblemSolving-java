import java.util.*;
import java.io.*;

// - 알고리즘: 조합 + 브루트포스

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
