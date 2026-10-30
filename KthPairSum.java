import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// Задача 5. k-я наименьшая сумма A[i] + B[j]. Одинаковые суммы от разных пар считаются отдельно.
public class KthPairSum {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] a = readArray(in, n);
        int[] b = readArray(in, m);

        // массивы отсортированы, поэтому из пары (i, j) имеет смысл смотреть (i, j + 1)
        // и только с первой колонки переходить на следующую строку (i + 1, 0)
        PriorityQueue<int[]> heap = new PriorityQueue<>((p, q) -> Long.compare(sum(a, b, p), sum(a, b, q)));
        heap.add(new int[]{0, 0});

        long ans = 0;
        for (int step = 0; step < k; step++) {
            int[] cur = heap.poll();
            int i = cur[0];
            int j = cur[1];
            ans = sum(a, b, cur);

            if (j + 1 < m)
                heap.add(new int[]{i, j + 1});
            if (j == 0 && i + 1 < n)
                heap.add(new int[]{i + 1, 0});
        }

        out.println(ans);
        out.flush();
    }

    private static long sum(int[] a, int[] b, int[] pair) {
        return (long) a[pair[0]] + b[pair[1]];
    }

    private static int[] readArray(BufferedReader in, int len) throws Exception {
        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] arr = new int[len];
        for (int i = 0; i < len; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        return arr;
    }
}
