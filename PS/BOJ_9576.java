import java.util.*;
import java.io.*;

// - 알고리즘 : 그리디
// - 문제 Tip :
// 회의실배정 문제인 'Greedy_BOJ_1931' 문제와 유사함.
// 가장 많은 책을 나눠주려면 마치 회의를 많이 잡듯이, 빽빽하게 채워야함.
// => Pair 커스텀 정렬을 활용할것. (기준: 끝이 빠른 순서 b, 시작이 빠른 순서 a)
// => 틀린 기준) 'a b': 책세권 12 13 22 -> 학생 두명 (X)
// => 옳은 기준) 'b a': 책세권 12 22 13 -> 학생 세명 (O)

public class BOJ_9576 {
    public static class Pair implements Comparable<Pair> {
        public int a;  // start
        public int b;  // end

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Pair pairB) {
            if(this.b != pairB.b) {
                return this.b - pairB.b;  // 끝이 가장 빠른 순서 (b 오름차순)
            }
            else {
                return this.a - pairB.a;  // 시작이 가장 빠른 순서 (a 오름차순)
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());

        List<Pair> pList = new ArrayList<>();
        int n, m, a, b;
        while(tc-- > 0) {
            StringTokenizer stt = new StringTokenizer(br.readLine());
            n = Integer.parseInt(stt.nextToken());  // 책 수(번호)
            m = Integer.parseInt(stt.nextToken());  // 학생 수

            for(int i=0; i<m; i++) {
                stt = new StringTokenizer(br.readLine());
                a = Integer.parseInt(stt.nextToken());
                b = Integer.parseInt(stt.nextToken());
                pList.add(new Pair(a, b));
            }
            Collections.sort(pList);

            int cnt = 0;
            boolean[] visited = new boolean[n+1];  // 나눠준책 방문체크
            for(Pair p : pList) {
                for(int i=p.a; i<=p.b; i++) {
                    if(visited[i] == true) continue;
                    visited[i] = true;
                    cnt++;
                    break;
                }
            }

            stb.append(cnt).append("\n");
            pList.clear();
        }

        System.out.print(stb.toString());
    }
}
