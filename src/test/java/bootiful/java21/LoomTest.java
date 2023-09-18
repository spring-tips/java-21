package bootiful.java21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

class LoomTest {

    @Test
    void threads() throws Exception {
        var t = Thread.ofVirtual().unstarted(() -> System.out.println("hello, virtual world!"));
        Assertions.assertTrue(t.isVirtual());
    }

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


    public static void main(String[] args) throws Exception {

        var executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try (var ss = new ServerSocket(9090)) {
            while (true) {
                var client = ss.accept();
                executor.submit(() -> {
                    try {
                        var request = handleRequest(client);
                        System.out.println("got " + request);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }

    static String handleRequest(Socket socket) throws Exception {
        try (var out = new ByteArrayOutputStream()) {
            var next = -1;
            try (var in = socket.getInputStream()) {
                while ((next = in.read()) != -1) {
                    out.write(next);
                }
            }
            return out.toString().trim();
        }
    }

}
