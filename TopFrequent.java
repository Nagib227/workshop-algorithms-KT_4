import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

// Задача 2. k самых частых чисел. При равной частоте раньше идёт меньшее.
public class TopFrequent {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> freq = new HashMap<>();
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(st.nextToken());
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }

        List<Integer> nums = new ArrayList<>(freq.keySet());
        nums.sort((a, b) -> {
            int byFreq = Integer.compare(freq.get(b), freq.get(a));
            if (byFreq != 0)
                return byFreq;
            return Integer.compare(a, b);
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            if (i > 0)
                sb.append(' ');
            sb.append(nums.get(i));
        }

        out.println(sb);
        out.flush();
    }
}
