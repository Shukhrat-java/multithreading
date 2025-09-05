import java.util.*;

public class ParallelTextProcessor {

    public static void main(String[] args) throws InterruptedException {
        String[] texts = new String[25];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = Main.generateText("aab", 30_000);
        }

        long startTs = System.currentTimeMillis(); // start time
        
        List<Thread> threads = new ArrayList<>();
        for (String text : texts) {
            Thread thread = new Thread(new TextProcessor(text));
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join(); // зависаем, ждём когда поток объект которого лежит в thread завершится
        }
        
        long endTs = System.currentTimeMillis(); // end time

        System.out.println("Time: " + (endTs - startTs) + "ms");
    }
}
