package semaphore;

public class Consumer implements Runnable {
    private final ProducerConsumer pc;

    public Consumer(ProducerConsumer pc) {
        this.pc = pc;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                pc.getFull().acquire(); // Decrementa il numero delle posizioni piene (full.down())
                pc.getMutex().acquire(); // Entra in sezione critica (mutex.down())
                Item item = removeItem(); // Estrae un elemento dal buffer
                pc.logMessage(Thread.currentThread().getName() + " removed " + item);
                pc.getMutex().release(); // Abbandona la sezione critica (mutex.up())
                pc.getEmpty().release(); // Incrementa il numero delle posizioni vuote (empty.up())
                consumeItem(item);
                Thread.sleep((int) (Math.random() * 1000)); // Simula il tempo di consumo
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private Item removeItem() {
        return pc.getBuffer().poll();
    }

    private void consumeItem(Item item) {
        // Simula il consumo di un elemento
        pc.logMessage(Thread.currentThread().getName() + " consuming " + item);
    }
}