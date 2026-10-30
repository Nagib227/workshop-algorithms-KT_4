import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// Задача 1. Сумма значений на каждом уровне дерева. Корень — уровень 0.
public class LevelSum {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(in.readLine().trim());
        int[] value = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            value[i] = Integer.parseInt(st.nextToken());
            left[i] = Integer.parseInt(st.nextToken());
            right[i] = Integer.parseInt(st.nextToken());
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(0);
        StringBuilder sb = new StringBuilder();

        while (!q.isEmpty()) {
            int size = q.size();
            long sum = 0;
            for (int i = 0; i < size; i++) {
                int v = q.poll();
                sum += value[v];
                if (left[v] != -1)
                    q.add(left[v]);
                if (right[v] != -1)
                    q.add(right[v]);
            }
            if (sb.length() > 0)
                sb.append(' ');
            sb.append(sum);
        }

        out.println(sb);
        out.flush();
    }
}
