package semaphore;

public class Producer implements Runnable {
    private final ProducerConsumer pc;

    public Producer(ProducerConsumer pc) {
        this.pc = pc;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Item item = produceItem();
                if (item == null) {
                    break; // Esce se non ci sono più elementi da produrre
                }
                pc.getEmpty().acquire(); // (empty.down()) --> Decrementa il numero delle posizioni vuote
                pc.getMutex().acquire(); // (mutex.down()) --> Entrata in sezione critica 
                enterItem(item); // Viene messo un nuovo elemento nel buffer
                pc.logMessage(Thread.currentThread().getName() + " produced " + item);
                pc.getMutex().release(); // (mutex.up()) --> Abbandono della sezione critica 
                pc.getFull().release(); // (full.up()) --> Incrementa il numero delle posizioni piene 
                Thread.sleep((int) (Math.random() * 1000)); // Simulazione del tempo di produzione
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private Item produceItem() {
        synchronized (pc.getItemsToProduce()) {
            return pc.getItemsToProduce().poll(); // Estrae un elemento dalla coda degli elementi da produrre
        }
    }

    private void enterItem(Item item) {
        pc.getBuffer().add(item);
    }
}
