# Sistemi-Operativi
# Problema Produttore-Consumatore con Semafori.
Questo progetto Java illustra il classico problema del Produttore-Consumatore utilizzando semafori per la sincronizzazione. Il pattern produttore-consumatore coinvolge due tipi di thread: i produttori che producono elementi e li inseriscono in un buffer condiviso, e i consumatori che rimuovono elementi dal buffer e li consumano.

Caratteristiche:
- Semafori: Utilizzati per controllare l'accesso alle risorse condivise (mutex per la sezione critica, empty per tracciare gli slot vuoti nel buffer, full per tracciare gli slot pieni).
- Gestione dei Thread: Gestione di più thread produttori e consumatori utilizzando la classe Thread di Java.
- Input da File: Gli elementi da produrre vengono caricati da un file CSV specificato come argomento da riga di comando.
- Logging: Registrazione dettagliata delle azioni eseguite dai produttori e consumatori, sincronizzata per la sicurezza dei thread.
Componenti:
- ProducerConsumer.java: Classe principale che inizializza semafori, buffer e gestisce i thread produttori e consumatori.
- Producer.java: Classe Runnable che definisce il comportamento del produttore.
- Consumer.java: Classe Runnable che definisce il comportamento del consumatore.
- Item.java: Classe semplice che rappresenta gli elementi da produrre/consumare.
