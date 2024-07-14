
package semaphore;

/**
 * Consumer class
 */
public class Consumer implements Runnable {
    private final ProducerConsumer pc;

    public Consumer(ProducerConsumer pc) {
        this.pc = pc;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                pc.getFull().acquire(); // (full.down()) --> Decrementa il numero delle posizioni piene 
                pc.getMutex().acquire(); // (mutex.down()) --> Entra in sezione critica 
                Item item = removeItem(); // Estrae un elemento dal buffer
                pc.logMessage(Thread.currentThread().getName() + " removed " + item);
                pc.getMutex().release(); // (mutex.up()) --> Abbandona la sezione critica 
                pc.getEmpty().release(); // (empty.up()) --> Incrementa il numero delle posizioni vuote 
                consumeItem(item); 
                Thread.sleep((int) (Math.random() * 1000)); // Simulazione del tempo di consumo
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private Item removeItem() {
        return pc.getBuffer().poll();
    }

    private void consumeItem(Item item) {
        // Simulazione del consumo di un elemento
        pc.logMessage(Thread.currentThread().getName() + " consuming " + item);
    }
}