package test.toolsproject.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.toolsproject.vo.Transaction;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureSimple {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompletableFutureSimple.class);

    public static void main(String[] args) {

        try {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Test");
            LOGGER.info("future {}", future.get());

            CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> Transaction.getStrTransaction());
            LOGGER.info("future1 {}", future1.get());

            CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> Transaction.getIntTransaction());
            LOGGER.info("future2 {}", future2.get());

            CompletableFuture<Integer> future4 = future2.thenApply(future2Return -> Transaction.sum(future2Return));
            LOGGER.info("then apply future {}", future4.get());

            CompletableFuture<Void> future5 = future1.thenAccept(future1Return -> Transaction.nothingToDo(future1Return));
            LOGGER.info("then accept future {} {}", future5.get(), future5.isDone());

            CompletableFuture<Void> future6 = future.thenRun(() -> Transaction.nothingToDo("222"));
            LOGGER.info("then run future {}", future6.get());

            Transaction transaction = new Transaction();
            CompletableFuture<Transaction> future3 = CompletableFuture.supplyAsync(() -> transaction.create(1, "TX DEBITO"));
            LOGGER.info("future3 object: {} - future3 name: {}", future3.get(), future3.get().getDescription());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
