# Sistemi-Operativi
# Problema Produttore-Consumatore con Semafori.
Tale progetto affronta il classico problema del Produttore-Consumatore utilizzando semafori per la sincronizzazione. Il pattern produttore-consumatore coinvolge due tipi di thread: i produttori che producono elementi e li inseriscono in un buffer condiviso, e i consumatori che rimuovono elementi dal buffer e li consumano "offline".

## Funzionalità
- **Produttori**: producono elementi e li aggiungono al buffer condiviso.
- **Consumatori**: rimuovono elementi dal buffer e li consumano.
- **Semafori**: utilizzati per controllare l'accesso al buffer e mantenere la sincronizzazione tra i thread.
-  **Input da File**: gli elementi da produrre vengono caricati da un file di input specificato come argomento da riga di comando.
- **Logging**: necessari per registrare tutte le operazioni di produzione e consumo in un log visualizzabile in una finestra di popup.

## Struttura del progetto
Il progetto è composto dalle seguenti classi:
- `ProducerConsumer`: Classe principale che gestisce l'inizializzazione e il coordinamento dei thread produttori e consumatori.
- `Producer`: Classe che implementa il thread produttore.
- `Consumer`: Classe che implementa il thread consumatore.
- `Item`: Classe che rappresenta un elemento nel buffer da produrre e consumare.

## Come utilizzare
### Compilazione e Esecuzione
1. Compila il progetto:
   ```sh
   javac semaphore/*.java

2. Esegui il programma specificando il percorso del file di input contenente gli elementi da produrre:
  ```sh
  java semaphore.ProducerConsumer <input_file_path>

  Dove <input_file_path> è il percorso del file di testo contenente gli elementi da produrre. Il file deve avere il seguente   formato:
   ```sh
    <id1>,<name1>
    <id2>,<name2>
    ...
