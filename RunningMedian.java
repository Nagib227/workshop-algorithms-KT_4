import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// Задача 3. Медиана после каждого нового числа.
// Нижняя половина в max-куче, верхняя в min-куче.
public class RunningMedian {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(in.readLine().trim());
        StringTokenizer st = new StringTokenizer(in.readLine());

        PriorityQueue<Integer> low = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> high = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(st.nextToken());
            if (low.isEmpty() || x <= low.peek())
                low.add(x);
            else
                high.add(x);

            if (low.size() > high.size() + 1)
                high.add(low.poll());
            else if (high.size() > low.size())
                low.add(high.poll());

            if (sb.length() > 0)
                sb.append(' ');

            if (low.size() > high.size())
                appendValue(sb, low.peek());
            else
                appendAverage(sb, low.peek(), high.peek());
        }

        out.println(sb);
        out.flush();
    }

    private static void appendValue(StringBuilder sb, long value) {
        sb.append(value).append(".0");
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
}
