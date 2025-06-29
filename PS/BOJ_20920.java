import java.util.*;
import java.io.*;

// - 알고리즘: 커스텀 정렬

public class BOJ_20920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        StringTokenizer stt = new StringTokenizer(br.readLine());
        int inputN = Integer.parseInt(stt.nextToken());
        int inputM = Integer.parseInt(stt.nextToken());
        Map<String, Integer> m = new HashMap<>();
        List<String> list = new ArrayList<>();
        while(inputN-- > 0) {
            String str = br.readLine();
            if(str.length() < inputM) continue;
            
            Integer strCnt = m.get(str);
            if(strCnt == null) {
                m.put(str, 1);
                list.add(str);
            }
            else {
                m.put(str, strCnt+1);
            }
        }

        Collections.sort(list, (a, b) -> {
            int aCnt = m.get(a), bCnt = m.get(b);
            int aLen = a.length(), bLen = b.length();
            if(aCnt != bCnt) {
                return bCnt - aCnt;
            }
            else if(aLen != bLen) {
                return bLen - aLen;
            }
            else {
                return a.compareTo(b);  // 사전순 오름차순
            }
        });
        
        for(String str : list) {
            stb.append(str).append("\n");
        }

        System.out.print(stb.toString());
    }
}
