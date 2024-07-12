# Sistemi-Operativi
# Problema Produttore-Consumatore con Semafori.
Tale progetto affronta il classico problema del Produttore-Consumatore utilizzando semafori per la sincronizzazione. Il pattern produttore-consumatore coinvolge due tipi di thread: i produttori che producono elementi e li inseriscono in un buffer condiviso, e i consumatori che rimuovono elementi dal buffer e li consumano "offline".

**Caratteristiche**:
- **Semafori**: vengono usati per controllare l'accesso alle risorse condivise (mutex per la sezione critica, empty per tracciare gli slot vuoti nel buffer, full per tracciare gli slot pieni).
- **Input da File**: gli elementi da produrre vengono caricati da un file di input specificato come argomento da riga di comando.

- **ProducerConsumer.java**: Classe principale che inizializza semafori, buffer e gestisce i thread produttori e consumatori.
- **Producer.java**: classe Runnable che definisce il comportamento del produttore.
- **Consumer.java**: classe Runnable che definisce il comportamento del consumatore.
- **Item.java**: classe che rappresenta gli elementi da produrre/consumare.
