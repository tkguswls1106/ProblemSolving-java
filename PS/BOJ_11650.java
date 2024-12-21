import java.util.*;
import java.io.*;

public class BOJ_11650 {
    public static class Pair implements Comparable<Pair> {
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair b) {
            if(this.x != b.x) {
                return this.x - b.x;
            }
            else {
                return this.y - b.y;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer stt;
        List<Pair> pList = new ArrayList<>();
        while(n-- > 0) {
            stt = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stt.nextToken());
            int y = Integer.parseInt(stt.nextToken());
            pList.add(new Pair(x, y));
        }
        Collections.sort(pList);

        for(Pair p : pList) {
            stb.append(String.format("%d %d\n", p.x, p.y));
        }
        System.out.print(stb.toString());
    }
}
