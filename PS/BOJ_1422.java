import java.util.*;
import java.io.*;

// - 시간복잡도 :
// O(20 * nlog(n)) = 최대 약 6000번 연산 <= 2억번(제한시간 2초) 이내로 충족.
// 상세 분석은 코드 내 주석에 기재하였음.
// 참고로 10억 숫자는 문자열 기준 10자리이므로, StringBuilder.append 시 O(10억)이 아닌 O(10)씩 적용됨.

public class BOJ_1422 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        StringTokenizer stt = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(stt.nextToken());
        int n = Integer.parseInt(stt.nextToken());
        
        List<String> list = new ArrayList<>();
        int maxNum = -1;
        for(int i=0; i<k; i++) {
            String str = br.readLine();
            list.add(str);
            maxNum = Math.max(maxNum, Integer.parseInt(str));
        }
        for (int i=0; i<n-k; i++) {
            list.add(String.valueOf(maxNum));
        }
        // ==> O(n)
        
        Collections.sort(list, new Comparator<String>() {  // O(nlog(n))
            @Override
            public int compare(String a, String b) {
                String ab = a + b;
                String ba = b + a;
                return ba.compareTo(ab);  // 10자리 + 10자리 = 최대 20자 = O(20)
            }
        });
        // ==> O(20 * nlog(n))

        for(int i=0; i<list.size(); i++) {
            stb.append(list.get(i));  // 문자열 append 시, 10억 숫자 = 10자리 = O(10)씩 적용.
        }
        // ==> O(n * 10자리) = O(n)

        System.out.print(stb.toString());
        // ==> O(stb길이) = O(n * 10자리) = O(n)

        // ====>
        // O(n) + O(n) + O(n) + O(20 * nlog(n))
        // = 'O(n) < O(nlog(n))' 이므로, 'O(20 * nlog(n))'으로 책정
        // = O(20 * nlog(n))
        // = 20 * (50 * log50)
        // = 20 * (50 * 5~6)
        // = 최대 약 6000번 연산
        // 이는 2억번(제한시간 2초)보다 훨씬 적으므로, 문제 조건을 충분히 만족.
    }
}
