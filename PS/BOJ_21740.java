import java.util.*;
import java.io.*;

public class BOJ_21740 {
    public static class Pair {
        public String originStr;
        public String convertStr;

        public Pair(String originStr) {
            this.originStr = originStr;
            this.convertStr = getConvertStr(originStr);
        }
    }

    public static String getConvertStr(String str) {  // 문제의 180도 회전 규칙 : 뒤집기 & 변환
        StringBuilder stb = new StringBuilder(str);
        stb.reverse();

        for(int i=0; i<stb.length(); i++) {
            char ch = stb.charAt(i);
            if(ch == '6') stb.setCharAt(i, '9');
            else if(ch == '9') stb.setCharAt(i, '6');
        }
        
        return stb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer stt = new StringTokenizer(br.readLine());
        List<Pair> list = new ArrayList<>();
        for(int i=0; i<n; i++) {
            String str = stt.nextToken();
            list.add(new Pair(str));
        }

        Collections.sort(list, (a, b) -> {
            int lenA = a.originStr.length();
            int lenB = b.originStr.length();
            if(lenA != lenB) {  // 변환 후 이어붙일때, 원본 문자열 길이가 더 큰것이 유리함.
                return lenB - lenA;
            }
            else {
                return b.convertStr.compareTo(a.convertStr);
            }
        });
        Pair maxPair = list.get(0);  // 한번 더 추가 용도.

        Collections.sort(list, (a, b) -> {
            String ab = a.convertStr + b.convertStr;
            String ba = b.convertStr + a.convertStr;
            return ba.compareTo(ab);
        });

        // 예시로 입력이 1 6 8 9 이라면, 현재 여기서 리스트의 originStr들은 6 8 9 1이 되어있음.
        // 따라서, 문제의 출력처럼 1986(6)이 되려면 리스트를 역순으로 조회해야함.
        StringBuilder answerStb = new StringBuilder();
        for(int i=list.size()-1; i>=0; i--) {
            Pair pair = list.get(i);
            answerStb.append(pair.originStr);
            if(pair == maxPair) {  // 이전에 list.get한것을 maxPair에 할당했기에, equals() 오버라이딩 없이도 동일성 비교가 가능.
                answerStb.append(pair.originStr);
            }
        }

        System.out.print(answerStb.toString());
    }
}
