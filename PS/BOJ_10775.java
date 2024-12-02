import java.io.*;

// - 알고리즘: 유니온 파인드
// - 문제 Tip:
// 들어온 비행기의 번호가 만약 a라면, 이 비행기는 1~a 게이트 범위에서만 도킹이 가능함.
// 그러므로 가능한한 본인 게이트와 가장 가까운 최대 게이트에 도킹(주차)해야지, 최대 cnt를 구할 수 있음.

public class BOJ_10775 {
    public static int[] parent = new int[100002];

    public static int findParent(int x) {
        if(x == parent[x]) return x;
        else return parent[x] = findParent(parent[x]);
    }

    public static void merge(int x, int y) {
        x = findParent(x);
        y = findParent(y);

        if(x == y) return;
        else parent[y] = x;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());

        for(int i=1; i<=g; i++) {
            parent[i] = i;
        }

        int cnt = 0;
        int num, root;
        while(p-- > 0) {
            num = Integer.parseInt(br.readLine());
            root = findParent(num);

            if(num == root) {  // 만약 본인인 최대게이트에 첫 도착이라면, 바로 도킹후 root의 의미를 선두로 변경.
                merge(root-1, root);  // parent[y] = x 이므로, root의 부모가 선두인 root-1이 될수있도록 파라미터를 이렇게 넣어줘야함.
                cnt++;
            }
            else {  // 이 경우, 이미 root의 의미가 선두가 되어있음에 주의할것!
                if(root == 0) {  // 선두가 0인 경우 (경계선을 넘음)
                    break;
                }
                else {
                    merge(root-1, root);  // parent[y] = x 이므로, root의 부모가 선두인 root-1이 될수있도록 파라미터를 이렇게 넣어줘야함.
                    cnt++;
                }
            }
        }

        System.out.print(cnt);
    }
}
