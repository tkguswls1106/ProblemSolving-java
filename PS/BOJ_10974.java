import java.util.*;

// - 알고리즘: 순열

public class BOJ_10974 {
    public static int n = 0;
    public static int[] arr = new int[10];
    public static boolean[] visited = new boolean[10];
    public static List<Integer> selected = new ArrayList<>();
    public static StringBuilder answerStb = new StringBuilder();

    public static void perm(int cnt) {
        if(cnt == n) {  // nPn
            for(int num : selected) {
                answerStb.append(num).append(" ");
            }
            answerStb.append("\n");
            return;
        }

        for(int i=0; i<n; i++) {
            if(visited[i] == true) continue;

            selected.add(arr[i]);
            visited[i] = true;
            perm(cnt+1);

            selected.remove(selected.size()-1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // n만 입력받으므로, BufferedReader 사용은 불필요함.
        n = sc.nextInt();
        for(int i=0; i<n; i++) {
            arr[i] = i + 1;
        }

        perm(0);

        System.out.print(answerStb.toString());
    }
}
