import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Задача 4. Самый дорогой маршрут: любые две вершины, соединённые рёбрами.
public class MaxPath {
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

        // обход снизу вверх своим стеком: дерево может быть цепочкой на 200 000 вершин
        int[] stack = new int[n];
        int[] state = new int[n];
        long[] down = new long[n];
        int top = 0;
        stack[top++] = 0;
        long best = Long.MIN_VALUE;

        while (top > 0) {
            int v = stack[top - 1];
            if (state[v] == 0) {
                state[v] = 1;
                if (right[v] != -1)
                    stack[top++] = right[v];
                if (left[v] != -1)
                    stack[top++] = left[v];
            } else {
                top--;
                long leftGain = left[v] == -1 ? 0 : Math.max(0, down[left[v]]);
                long rightGain = right[v] == -1 ? 0 : Math.max(0, down[right[v]]);
                long through = value[v] + leftGain + rightGain;
                if (through > best)
                    best = through;
                down[v] = value[v] + Math.max(leftGain, rightGain);
            }
        }

        out.println(best);
        out.flush();
    }
}
