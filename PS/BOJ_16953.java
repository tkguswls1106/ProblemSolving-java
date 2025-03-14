import java.util.*;

// - 알고리즘: BFS
// - 문제 Tip: 숫자를 2배 곱하거나 1을 뒤에 추가하는 경우, 감소할 일이 없기에 visited 배열은 애초에 필요하지 않음.

public class BOJ_16953 {
    public static int answer = -1;
    public static int a, b;

    public static class Pair {
        public long x;
        public int cnt;

        public Pair(long x, int cnt) {
            this.x = x;
            this.cnt = cnt;
        }
    }

    public static void bfs(int x) {
        Queue<Pair> qu = new LinkedList<>();
        qu.offer(new Pair((long) x, 1));  // 문제 명시 : '필요한 연산의 최솟값에 1을 더한 값을 출력한다.'

        while(!qu.isEmpty()) {
            Pair cur = qu.poll();
            int cnt = cur.cnt;

            for(long nx : Arrays.asList(cur.x*2, cur.x*10 + 1)) {
                if(nx > b) continue;

                qu.offer(new Pair(nx, cnt+1));
                if(nx == b) {
                    answer = cnt+1;
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        a = sc.nextInt();
        b = sc.nextInt();
        if(a == b) {
            System.out.print(0);
            return;
        }

        bfs(a);

        System.out.print(answer);
    }
}
