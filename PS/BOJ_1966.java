import java.util.*;
import java.io.*;

// - 알고리즘: 우선순위큐

public class BOJ_1966 {
    public static class Pair {
        public int idx;
        public int rank;

        public Pair(int idx, int rank) {
            this.idx = idx;
            this.rank = rank;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        StringTokenizer stt;
        int n, m;
        while(tc-- > 0) {
            stt = new StringTokenizer(br.readLine());
            n = Integer.parseInt(stt.nextToken());
            m = Integer.parseInt(stt.nextToken());

            stt = new StringTokenizer(br.readLine());
            PriorityQueue<Integer> rankPq = new PriorityQueue<>(Collections.reverseOrder());
            Queue<Pair> qu = new LinkedList<>();
            for(int i=0; i<n; i++) {
                int input = Integer.parseInt(stt.nextToken());
                rankPq.offer(input);
                qu.offer(new Pair(i, input));
            }

            int cnt = 0;
            while(!qu.isEmpty()) {
                Pair p = qu.poll();

                int maxRank = rankPq.peek();
                if(p.rank == maxRank) {
                    rankPq.poll();
                    cnt++;
                    if(p.idx == m) {
                        stb.append(cnt).append("\n");
                    }
                }
                else {
                    qu.offer(p);
                }
            }
        }

        System.out.print(stb.toString());
    }
}
