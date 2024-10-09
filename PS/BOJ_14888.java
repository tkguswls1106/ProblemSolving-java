import java.util.*;
import java.io.*;

// - 알고리즘: 순열
// - 문제 Tip: 비록 중복되는 연산자가 있지만, 어차피 계산결과의 최댓값과 최솟값을 구하는것이므로, 다 계산해보아도 결과에는 지장없음.
// - 또다른 풀이법: https://st-lab.tistory.com/121

public class BOJ_14888 {
    public static int n, m;
    public static int[] numArr, opArr;  // 전체 숫자 배열, 전체 연산자 배열 (0:+, 1:-, 2:x, 3:/)
    public static int[] opVisited;  // 방문(선택)여부 연산자 배열
    public static List<Integer> opSelected = new ArrayList<>();  // 선택 연산자 리스트

    public static int maxResult = -(int) 1e9;
    public static int minResult = (int) 1e9;

    public static int carculate(int num1, int num2, int op) {
        if(op == 0) return num1+num2;
        else if(op == 1) return num1-num2;
        else if(op == 2) return num1*num2;
        else return num1/num2;
    }

    public static void perm(int cnt) {
        if(cnt == m) {
            int result = numArr[0];
            int numIdx = 1;
            for(int opIdx=0; opIdx<m; opIdx++) {
                result = carculate(result, numArr[numIdx++], opSelected.get(opIdx));
            }

            maxResult = Math.max(maxResult, result);
            minResult = Math.min(minResult, result);
            return;
        }

        for(int i=0; i<m; i++) {
            if(opVisited[i] == 1) continue;

            opSelected.add(opArr[i]);
            opVisited[i] = 1;
            perm(cnt+1);

            opSelected.remove(opSelected.size()-1);
            opVisited[i] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = n - 1;
        numArr = new int[n];
        opArr = new int[m];
        opVisited = new int[m];
        
        StringTokenizer stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            numArr[i] = Integer.parseInt(stt.nextToken());
        }

        stt = new StringTokenizer(br.readLine());
        int idx = 0;
        for(int i=0; i<4; i++) {
            int opCnt = Integer.parseInt(stt.nextToken());
            while(opCnt-- > 0) {
                opArr[idx++] = i;
            }
        }

        perm(0);
        System.out.println(maxResult);
        System.out.println(minResult);
    }
}
