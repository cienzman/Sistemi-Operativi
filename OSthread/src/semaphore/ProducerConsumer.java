package semaphore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;


//TODO complicare la classe item così che file è più complesso.
//TODO chiedere bene cosa fa codice


public class ProducerConsumer {
    static final int N = 100; // Numero di posizioni nel buffer
    private final Semaphore mutex = new Semaphore(1); // Controlla l'accesso in sezione critica
    private final Semaphore empty = new Semaphore(N); // Conta il numero di posizioni vuote nel buffer
    private final Semaphore full = new Semaphore(0); // Conta il numero di elementi nel buffer
    private final Queue<Item> buffer = new LinkedList<>(); // Buffer circolare
    private final Queue<Item> itemsToProduce = new LinkedList<>(); // Coda degli elementi da produrre
    private final StringBuilder log = new StringBuilder(); // Log degli output

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java ProducerConsumer <input_file_path>");
            JOptionPane.showMessageDialog(null,"Usage: java ProducerConsumer <input_file_path>", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        String filePath = args[0];
        ProducerConsumer pc = new ProducerConsumer();
        pc.loadItemsFromFile(filePath); // Carica gli elementi dal file

        int numProducers = 2; // Numero di produttori
        int numConsumers = 5; // Numero di consumatori

        // Creare e avviare i thread produttori e consumatori
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numProducers; i++) {
            Thread producerThread = new Thread(new Producer(pc), "Producer-" + i);
            threads.add(producerThread);
            producerThread.start();
        }

        for (int i = 0; i < numConsumers; i++) {
            Thread consumerThread = new Thread(new Consumer(pc), "Consumer-" + i);
            threads.add(consumerThread);
            consumerThread.start();
        }

        // Usa un ScheduledExecutorService per fermare i thread dopo un certo tempo
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            for (Thread thread : threads) {
                thread.interrupt();
            }
        }, 5, TimeUnit.SECONDS); // Timeout di 10 secondi

        // Attendere la terminazione di tutti i thread
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        

            JTextArea textArea = new JTextArea(pc.getLogContent());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(700, 500));
            JOptionPane.showMessageDialog(null, scrollPane, "Log Output", JOptionPane.INFORMATION_MESSAGE);
        


        // Chiudere il scheduler
        scheduler.shutdown();
    }


    public Semaphore getMutex() {
        return mutex;
    }

    public Semaphore getEmpty() {
        return empty;
    }

    public Semaphore getFull() {
        return full;
    }

    public Queue<Item> getBuffer() {
        return buffer;
    }

    public Queue<Item> getItemsToProduce() {
        return itemsToProduce;
    }

    public StringBuilder getLog() {
        return log;
    }

    public void loadItemsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) { // Verifica che la riga sia formattata correttamente
                    try {
                        int id = Integer.parseInt(parts[0]);
                        String name = parts[1];
                        itemsToProduce.add(new Item(id, name));
                    } catch (NumberFormatException e) {
                        logMessage("Errore di formattazione nell'ID: " + parts[0]);
                    }
                } else {
                    logMessage("Riga formattata in modo errato: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logMessage(String message) {
        System.out.println(message);
        synchronized (log) {
            log.append(message).append("\n");
        }
    }

    public String getLogContent() {
        synchronized (log) {
            return log.toString();
        }
    }
}


