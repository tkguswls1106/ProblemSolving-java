import java.util.*;

public class BOJ_2920 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer stt = new StringTokenizer(sc.nextLine());
        
        char[] arr = {'Z', 'c', 'd', 'e', 'f', 'g', 'a', 'b' ,'C'};  // 맨앞은 아무 알파벳으로 할당.
        Map<Integer, Character> m = new HashMap<>();
        for(int i=1; i<arr.length; i++) {
            m.put(i, arr[i]);
        }

        StringBuilder resultStb = new StringBuilder();
        while(stt.hasMoreTokens()) {
            int num = Integer.parseInt(stt.nextToken());
            resultStb.append(m.get(num));
        }

        String answer = "mixed";
        if(resultStb.toString().equals("cdefgabC")) answer = "ascending";
        else if(resultStb.toString().equals("Cbagfedc")) answer = "descending";

        System.out.print(answer);
    }
}
