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
                pc.getEmpty().acquire(); // Decrementa il numero delle posizioni vuote (empty.down())
                pc.getMutex().acquire(); // Entra in sezione critica (mutex.down())
                enterItem(item); // Mette un nuovo elemento nel buffer
                pc.logMessage(Thread.currentThread().getName() + " produced " + item);
                pc.getMutex().release(); // Abbandona la sezione critica (mutex.up())
                pc.getFull().release(); // Incrementa il numero delle posizioni piene (full.up())
                Thread.sleep((int) (Math.random() * 1000)); // Simula il tempo di produzione
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
