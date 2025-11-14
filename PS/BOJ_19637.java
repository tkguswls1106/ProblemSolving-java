import java.util.*;
import java.io.*;

// - 알고리즘 : 이분 탐색
// - 시간복잡도 :
// 단순 연산으로는 최대 '칭호개수 O(N) x 캐릭터개수 O(M) = O(10^10) = 100억번'을 비교하므로, 1초 제한에 걸림.
// 따라서 이진탐색으로 비교한다면 'O(16 < log10^5 < 17) x O(10^5) = 1억번 미만'으로 풀이가 가능해짐.
// => 이분탐색 알고리즘을 사용할것.

public class BOJ_19637 {
    public static List<Integer> powerList = new ArrayList<>();
    public static Map<Integer, String> powerNameMap = new HashMap<>();

    public static int lowerBound(int findPower) {  // binarySearch 이상값 탐색
        int left = 0;
        int right = powerList.size();
        while(left < right) {
            int mid = (left + right) / 2;
            if(powerList.get(mid) >= findPower) {  // findPower <= powerList.get(mid)
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }

        return right;
    }

    public static String getName(int idx) {  // powerList index
        int power = powerList.get(idx);
        return powerNameMap.get(power);  // name
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stt.nextToken());
        int m = Integer.parseInt(stt.nextToken());

        String name;
        int power = -1;
        for(int i=0; i<n; i++) {
            stt = new StringTokenizer(br.readLine());
            name = stt.nextToken();
            int inputPower = Integer.parseInt(stt.nextToken());
            if(power == inputPower) continue;
            power = inputPower;

            powerList.add(power);
            powerNameMap.put(power, name);
        }

        StringBuilder stb = new StringBuilder();
        for(int i=0; i<m; i++) {
            power = Integer.parseInt(br.readLine());
            int idx = lowerBound(power);
            stb.append(getName(idx)).append("\n");
        }

        System.out.print(stb.toString());
    }
}
