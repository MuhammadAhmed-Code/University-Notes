import java.util.LinkedHashMap;
import java.util.Map;

class PkChange {
    int[] denominations;
    Map<Integer, Integer> count;
    int amount;

    PkChange(int amount){
        this.amount = amount;
        denominations = new int[]{5000, 1000, 500, 100, 50, 20, 10, 5, 2, 1};
        count  = new LinkedHashMap<>();
    }

    public void coinchange(){
        for (int coin : denominations){
            if (amount >= coin) {
                int num = amount/coin;
                count.put(coin, num);
                amount %= coin;
            }
        }

        System.out.println("For Rs. " + (1988 + amount) + ":");
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if(entry.getValue() > 0) {
                System.out.println(entry.getValue() + " x Rs. " + entry.getKey());
            }
        }
    }
}

public class Question02Answer {
    public static void main (String[] args) {
        PkChange coins = new PkChange(1988);
        coins.coinchange();
    }
}