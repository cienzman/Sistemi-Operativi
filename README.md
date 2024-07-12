# Sistemi-Operativi
Questo programma implementa il classico problema del produttore-consumatore utilizzando semafori per la sincronizzazione. Il programma prevede due produttori e cinque consumatori che interagiscono con un buffer di dimensione 100. Gli elementi da produrre vengono caricati da un file di input.

Struttura del Progetto
Il progetto è suddiviso nelle seguenti classi:

ProducerConsumer: La classe principale che gestisce il buffer, i semafori, e carica gli elementi da un file.
Producer: Classe che implementa Runnable e contiene la logica per produrre gli elementi.
Consumer: Classe che implementa Runnable e contiene la logica per consumare gli elementi.
Item: Classe che rappresenta un elemento con un ID e un nome.
Funzionalità
Carica gli elementi da un file di input.
Due produttori producono elementi e li inseriscono nel buffer.
Cinque consumatori consumano gli elementi dal buffer.
Utilizza semafori per gestire l'accesso concorrente al buffer.
Log degli eventi di produzione e consumo mostrati in console e in un popup alla fine dell'esecuzione.
