import java.util.*;

// - 알고리즘: 순열 + 오름차순

public class BOJ_15650 {
    public static int n, m;
    public static List<Integer> selected = new ArrayList<>();
    public static int[] visited = new int[10];
    public static StringBuilder stb = new StringBuilder();

    public static void perm(int cnt) {
        if(cnt == m) {
            for(int num : selected) stb.append(num + " ");
            stb.append("\n");
            return;
        }

        for(int i=1; i<=n; i++) {
            if(visited[i] == 1) continue;
            if(!selected.isEmpty() && selected.get(selected.size()-1) > i) continue;  // 오름차순 수열이 아니라면 넘기기.

            selected.add(i);
            visited[i] = 1;
            perm(cnt+1);

            selected.remove(selected.size()-1);
            visited[i] = 0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        perm(0);
        System.out.print(stb.toString());
    }
}
