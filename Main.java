import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String[] events = { "BUY book 10", "BUY pen 10", "CHANGE book 3", "QUERY", "SELL pen 5", "CHANGE pen -4", "QUERY" };
        int[] result = getNetProfit(events);

        System.out.println("Result:");
        for (int res : result) {
            System.out.println(res);
        }
    }

    public static int[] getNetProfit(String[] events) {
        Map<String, Integer> stocks = new HashMap<>();
        List<Integer> queries = new ArrayList<>();
        int totalProfit = 0;

        for (String e : events) {
            String[] parts = e.split(" ");

            switch (parts[0]) {
                case "BUY" -> {
                    String stock = parts[1];
                    int quantity = Integer.parseInt(parts[2]);
                    stocks.put(stock, stocks.getOrDefault(stock, 0) + quantity);
                }
                case "SELL" -> {
                    String stock = parts[1];
                    int quantity = Integer.parseInt(parts[2]);
                    stocks.put(stock, stocks.get(stock) - quantity);
                }
                case "CHANGE" -> {
                    String stock = parts[1];
                    int priceChange = Integer.parseInt(parts[2]);
                    if (stocks.containsKey(stock)) {
                        totalProfit += stocks.get(stock) * priceChange;
                    }
                }
                case "QUERY" -> queries.add(totalProfit);
            }
        }

        int[] result = new int[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            result[i] = queries.get(i);
        }

        return result;
    }
}

