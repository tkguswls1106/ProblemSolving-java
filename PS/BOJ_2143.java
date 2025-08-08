import java.util.*;
import java.io.*;

// - 알고리즘: 이분 탐색

public class BOJ_2143 {
    public static int t, n, m;
    public static int[] arrA, arrB;
    public static List<Integer> subA = new ArrayList<>();
    public static List<Integer> subB = new ArrayList<>();

    public static int lowerBound(int findNum) {  // 이상값 (>=)
        int left = 0;
        int right = subB.size();
        while(left < right) {
            int mid = (left + right) / 2;
            if(subB.get(mid) >= findNum) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }

        return right;
    }

    public static int upperBound(int findNum) {  // 초과값 (>)
        int left = 0;
        int right = subB.size();
        while(left < right) {
            int mid = (left + right) / 2;
            if(subB.get(mid) > findNum) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }

        return right;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        // arrA 배열 로직
        n = Integer.parseInt(br.readLine());
        arrA = new int[n];
        StringTokenizer stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            int num = Integer.parseInt(stt.nextToken());
            arrA[i] = num;
        }
        // arrB 배열 로직
        m = Integer.parseInt(br.readLine());
        arrB = new int[m];
        stt = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++) {
            int num = Integer.parseInt(stt.nextToken());
            arrB[i] = num;
        }

        // subA 리스트 로직
        for(int i=0; i<n; i++) {
            int sum = 0;  // 가능한 최댓값 = 1000000 x 1000 = 1000000000 (10억) 이므로, int 배열 자료형 사용 가능.
            for(int j=i; j<n; j++) {
                sum += arrA[j];
                subA.add(sum);
            }
        }
        // subB 리스트 로직
        for(int i=0; i<m; i++) {
            int sum = 0;  // 가능한 최댓값 = 1000000 x 1000 = 1000000000 (10억) 이므로, int 배열 자료형 사용 가능.
            for(int j=i; j<m; j++) {
                sum += arrB[j];
                subB.add(sum);
            }
        }
        Collections.sort(subB);

        long answer = 0;  // 가능한 최대 가짓수 = (1000x1000) x (1000x1000) = 1000000000000 (1조) 이므로, int 자료형 사용 불가능.
        for(int sum : subA) {
            int findNum = t - sum;
            int findCnt = upperBound(findNum) - lowerBound(findNum);
            answer += findCnt;
        }

        System.out.print(answer);
    }
}
