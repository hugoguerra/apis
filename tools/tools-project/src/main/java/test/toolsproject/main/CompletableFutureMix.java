package test.toolsproject.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.toolsproject.vo.Card;
import test.toolsproject.vo.Transaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureMix {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompletableFutureMix.class);

    public static void main(String[] args) {

        try {

            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Test")
                    .thenCompose(s -> CompletableFuture.supplyAsync(() -> "Finished"));
            LOGGER.info("future {}", future.get());

            CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
            CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Beautiful");
            CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "World");

            String combined = Stream.of(future1, future2, future3).map(CompletableFuture::join).collect(Collectors.joining(" "));
            LOGGER.info("future combined str {}", combined);

            System.out.println(LocalDateTime.now());
            Card card = new Card();
            CompletableFuture<String> process =
                    CompletableFuture.supplyAsync(() -> card.getCardByProxy("proxy"))
                            .thenComposeAsync(CompletableFutureMix::callSystem1Or2);

            LOGGER.info("process {}", process.get());
            System.out.println(LocalDateTime.now());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CompletableFuture<String> callSystem1Or2(Card card) {
        Transaction transaction = new Transaction();
        return card.getCustomerId().isEmpty()
                    ? CompletableFuture.supplyAsync(() -> transaction.doTrxTwo(card.getOtherId()))
                        : CompletableFuture.supplyAsync(() -> transaction.doTrxOne(card.getCustomerId()));
    }
}
