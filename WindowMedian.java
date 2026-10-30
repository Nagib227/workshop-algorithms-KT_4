import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// Задача 6. Медиана каждого окна длины k.
public class WindowMedian {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] a = new int[n];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(st.nextToken());

        int[] sorted = Arrays.copyOf(a, n);
        Arrays.sort(sorted);
        int uniqCount = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1])
                sorted[uniqCount++] = sorted[i];
        }
        int[] vals = Arrays.copyOf(sorted, uniqCount);
        int[] bit = new int[uniqCount + 1];

        for (int i = 0; i < k; i++)
            add(bit, rank(vals, a[i]), 1);

        StringBuilder sb = new StringBuilder();
        appendMedian(sb, bit, vals, k);

        for (int i = k; i < n; i++) {
            add(bit, rank(vals, a[i - k]), -1);
            add(bit, rank(vals, a[i]), 1);
            sb.append(' ');
            appendMedian(sb, bit, vals, k);
        }

        out.println(sb);
        out.flush();
    }

    private static void appendMedian(StringBuilder sb, int[] bit, int[] vals, int k) {
        if (k % 2 == 1) {
            int rank = findKth(bit, (k + 1) / 2);
            sb.append(vals[rank - 1]).append(".0");
        } else {
            long left = vals[findKth(bit, k / 2) - 1];
            long right = vals[findKth(bit, k / 2 + 1) - 1];
            appendAverage(sb, left, right);
        }
    }

    private static void appendAverage(StringBuilder sb, long a, long b) {
        long sum = a + b;
        if (sum < 0 && sum / 2 == 0 && sum % 2 != 0) {
            sb.append("-0.5");
            return;
        }
        sb.append(sum / 2);
        sb.append(sum % 2 == 0 ? ".0" : ".5");
    }

    private static int rank(int[] vals, int x) {
        int left = 0;
        int right = vals.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (vals[mid] < x)
                left = mid + 1;
            else
                right = mid;
        }
        return left + 1;
    }

    private static void add(int[] bit, int index, int delta) {
        while (index < bit.length) {
            bit[index] += delta;
            index += index & -index;
        }
    }

    // номер элемента в окне, если выписать окно по возрастанию. Нумерация с 1.
    private static int findKth(int[] bit, int k) {
        int pos = 0;
        for (int step = 1 << 18; step > 0; step >>= 1) {
            int next = pos + step;
            if (next < bit.length && bit[next] < k) {
                pos = next;
                k -= bit[next];
            }
        }
        return pos + 1;
    }
}
