import java.util.*;
import java.io.*;

// - 알고리즘: 순열(백트래킹)
// - 문제 Tip:
// 브루트포스로 문제풀이 시, 시간복잡도가 O(10! = 3628800)로 1초 이내에 충분히 연산 가능함.
// => 백트래킹 활용할것. => 순열

public class BOJ_2529 {
    public static int k = 0;
    public static char[] chArr = new char[10];
    public static boolean[] visited = new boolean[10];  // num visited
    public static StringBuilder selectedStb = new StringBuilder();
    public static List<String> answerList = new ArrayList<>();

    public static boolean isTrue(char numCh1, char ch, int num2) {
        int num1 = numCh1 - '0';
        if(ch == '<') return num1 < num2;
        else return num1 > num2;
    }

    public static void perm(int cnt) {
        if(cnt == k+1) {
            answerList.add(selectedStb.toString());
            return;
        }

        for(int i=0; i<10; i++) {  // num
            if(visited[i] == true) continue;
            
            if(selectedStb.length() > 0) {  // 백준의 java11 컴파일러에서는 'if(!selectedStb.isEmpty())' 사용 시, 컴파일 에러가 남.
                char lastCh = selectedStb.charAt(selectedStb.length()-1);
                if(isTrue(lastCh, chArr[cnt-1], i) == false) continue;
            }

            selectedStb.append(i);
            visited[i] = true;
            perm(cnt+1);

            selectedStb.deleteCharAt(selectedStb.length()-1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        k = Integer.parseInt(br.readLine());
        StringTokenizer stt = new StringTokenizer(br.readLine());
        for(int i=0; i<k; i++) {
            chArr[i] = stt.nextToken().charAt(0);
        }

        perm(0);
        
        stb.append(answerList.get(answerList.size()-1)).append("\n").append(answerList.get(0));
        System.out.print(stb.toString());
    }
}
