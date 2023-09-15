package bootiful.java21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.IntStream;

class LoomTest {

    @Test
    void loom() throws Exception {

        var observedThreadNames = new ConcurrentSkipListSet<String>();

        var threads = IntStream.range(0, 100)
                .mapToObj(index -> Thread
                        .ofVirtual()
                        .unstarted(() -> {
                            var first = index == 0;
                            if (first) {
                                observedThreadNames.add(Thread.currentThread().toString());
                            }
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (first) {
                                observedThreadNames.add(Thread.currentThread().toString());
                            }
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (first) {
                                observedThreadNames.add(Thread.currentThread().toString());
                            }
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (first) {
                                observedThreadNames.add(Thread.currentThread().getName());
                            }
                        })
                )
                .toList();

        for (var thread : threads) {
            thread.start();
        }
        for (var thread : threads) {
            thread.join();
        }

        System.out.println(observedThreadNames);
        Assertions.assertTrue(observedThreadNames.size() > 1,
                """
                        it's statistically very likely that the thread of execution was
                        moved from one thread to another
                        """);

    }
}
