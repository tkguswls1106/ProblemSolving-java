import java.util.*;
import java.io.*;

// - 알고리즘: 매개변수 탐색 (with 이분 탐색)

public class BOJ_2805 {
    public static int n, m;
    public static List<Integer> treeList = new ArrayList<>();  // 정렬 필요 X.

    public static boolean isValidCut(int cutTree) {  // cutTree = 톱날을 작동시킬 높이
        long restSum = 0;
        for(int tree : treeList) {
            int rest = tree - cutTree;
            restSum += (long) (rest > 0 ? rest : 0);
        }

        return restSum >= (long) m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());

        int maxTree = -1;
        stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            int tree = Integer.parseInt(stt.nextToken());
            maxTree = Math.max(maxTree, tree);
            treeList.add(tree);
        }

        int left = 1;
        int right = maxTree;
        while(left <= right) {
            int mid = (left + right) / 2;

            if(isValidCut(mid) == true) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        System.out.print(right);
    }
}
