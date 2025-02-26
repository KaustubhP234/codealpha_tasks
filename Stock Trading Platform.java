import java.util.*;

public class StockTradingPlatform {

    static class Stock {
        String symbol;
        double price;

        Stock(String symbol, double price) {
            this.symbol = symbol;
            this.price = price;
        }

        // Update stock price (simulating market changes)
        void updatePrice(double newPrice) {
            this.price = newPrice;
        }
    }

    static class Portfolio {
        private Map<String, Integer> holdings; // Stock symbol -> number of shares owned

        Portfolio() {
            holdings = new HashMap<>();
        }

        void buyStock(String symbol, int quantity) {
            holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);
        }

        void sellStock(String symbol, int quantity) {
            int currentQuantity = holdings.getOrDefault(symbol, 0);
            if (currentQuantity >= quantity) {
                holdings.put(symbol, currentQuantity - quantity);
            } else {
                System.out.println("Insufficient shares to sell.");
            }
        }

        double calculatePortfolioValue(Map<String, Stock> market) {
            double totalValue = 0;
            for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
                String symbol = entry.getKey();
                int quantity = entry.getValue();
                Stock stock = market.get(symbol);
                if (stock != null) {
                    totalValue += stock.price * quantity;
                }
            }
            return totalValue;
        }

        void printPortfolio() {
            System.out.println("\nPortfolio Summary:");
            if (holdings.isEmpty()) {
                System.out.println("No stocks owned.");
            } else {
                for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue() + " shares");
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Simulated market with some stocks and initial prices
        Map<String, Stock> market = new HashMap<>();
        market.put("AAPL", new Stock("AAPL", 145.0));  // Apple stock
        market.put("GOOG", new Stock("GOOG", 2800.0)); // Google stock
        market.put("AMZN", new Stock("AMZN", 3300.0)); // Amazon stock

        // User portfolio
        Portfolio portfolio = new Portfolio();

        // Main loop to interact with the platform
        while (true) {
            System.out.println("\n--- Stock Trading Platform ---");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Portfolio Value");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // View Market Data
                    System.out.println("\n--- Market Data ---");
                    for (Stock stock : market.values()) {
                        System.out.println(stock.symbol + ": $" + stock.price);
                    }
                    break;

                case 2: // Buy Stock
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = scanner.next().toUpperCase();
                    System.out.print("Enter number of shares to buy: ");
                    int buyQuantity = scanner.nextInt();

                    if (market.containsKey(buySymbol)) {
                        portfolio.buyStock(buySymbol, buyQuantity);
                        System.out.println("Bought " + buyQuantity + " shares of " + buySymbol);
                    } else {
                        System.out.println("Stock symbol not found.");
                    }
                    break;

                case 3: // Sell Stock
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = scanner.next().toUpperCase();
                    System.out.print("Enter number of shares to sell: ");
                    int sellQuantity = scanner.nextInt();

                    if (market.containsKey(sellSymbol)) {
                        portfolio.sellStock(sellSymbol, sellQuantity);
                        System.out.println("Sold " + sellQuantity + " shares of " + sellSymbol);
                    } else {
                        System.out.println("Stock symbol not found.");
                    }
                    break;

                case 4: // View Portfolio
                    portfolio.printPortfolio();
                    break;

                case 5: // View Portfolio Value
                    double portfolioValue = portfolio.calculatePortfolioValue(market);
                    System.out.println("\nPortfolio Value: $" + portfolioValue);
                    break;

                case 6: // Exit
                    System.out.println("Exiting platform. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}


OUTPUT:


--- Stock Trading Platform ---
1. View Market Data
2. Buy Stock
3. Sell Stock
4. View Portfolio
5. View Portfolio Value
6. Exit
Choose an option: 1

--- Market Data ---
GOOG: $2800.0
AAPL: $145.0
AMZN: $3300.0

--- Stock Trading Platform ---
1. View Market Data
2. Buy Stock
3. Sell Stock
4. View Portfolio
5. View Portfolio Value
6. Exit
Choose an option: 2
Enter stock symbol to buy: AAPL
Enter number of shares to buy: 10
Bought 10 shares of AAPL

--- Stock Trading Platform ---
1. View Market Data
2. Buy Stock
3. Sell Stock
4. View Portfolio
5. View Portfolio Value
6. Exit
Choose an option: 4

Portfolio Summary:
AAPL: 10 shares

--- Stock Trading Platform ---
1. View Market Data
2. Buy Stock
3. Sell Stock
4. View Portfolio
5. View Portfolio Value
6. Exit
Choose an option: 5

Portfolio Value: $1450.0

--- Stock Trading Platform ---
1. View Market Data
2. Buy Stock
3. Sell Stock
4. View Portfolio
5. View Portfolio Value
6. Exit
Choose an option: 3
Enter stock symbol to sell: AAPL
Enter number of shares to sell: 5
Sold 5 shares of AAPL

--- Stock Trading Platform ---
1. View Market Data
2. Buy Stock
3. Sell Stock
4. View Portfolio
5. View Portfolio Value
6. Exit
Choose an option: 6
Exiting platform. Goodbye!
