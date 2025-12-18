/*Universo_DB – Catalogo Strutturato dell’Universo

Introduzione e idea del progetto

Il progetto "Universo_DB" nasce dall’idea di realizzare un database relazionale di grandi dimensioni che rappresenti l’Universo non come un semplice insieme di pianeti e stelle, ma come un **sistema complesso, gerarchico e interconnesso**, simile a un vero catalogo scientifico utilizzato in ambito astronomico.

Siamo partiti dall’osservazione che l’astronomia moderna non studia gli oggetti celesti in modo isolato, ma li analizza in relazione tra loro, nel tempo e nello spazio, attraverso osservazioni, missioni scientifiche e modelli cosmologici. Da qui nasce la volontà di costruire un database che rifletta questa complessità.

Il progetto è stato pensato seguendo rigorosamente il **modello a tre strati** visto in classe:

* **Strato di presentazione**: applicazione Java con interfaccia a riga di comando
* **Strato logico**: classi Java che gestiscono le operazioni sul database
* **Strato dati**: database MySQL realizzato con MySQL Workbench

---

## Obiettivo del progetto

L’obiettivo principale è creare un database che permetta di:

* rappresentare la **struttura dell’Universo** su più livelli (superammassi, galassie, sistemi stellari);
* descrivere in modo dettagliato i **corpi celesti** (stelle, pianeti, buchi neri, nebulose, asteroidi);
* modellare **fenomeni ed eventi cosmici** nel tempo;
* collegare gli oggetti alle **osservazioni scientifiche**, agli strumenti e alle missioni spaziali;
* utilizzare correttamente **relazioni, chiavi primarie e chiavi esterne**, tipi **ENUM** e **date**.

Il database non è quindi solo descrittivo, ma anche **relazionale e dinamico**, in grado di collegare oggetti, eventi e attività umane legate allo studio dello spazio.

---

## Idea di fondo e approccio progettuale

L’idea di fondo è quella di costruire una **gerarchia cosmica**, partendo dalle strutture più grandi fino ad arrivare ai singoli oggetti e alle osservazioni:

* L’Universo è organizzato in **superammassi** e **ammassi di galassie**;
* Ogni galassia contiene diversi **oggetti cosmici**;
* Gli oggetti cosmici possono essere stelle, pianeti, buchi neri, nebulose, ecc.;
* Le stelle possono avere sistemi planetari;
* Nel tempo avvengono **eventi cosmici** rilevanti (supernove, fusioni, esplosioni);
* Gli oggetti vengono studiati tramite **osservazioni scientifiche**, strumenti e missioni spaziali.

Questo approccio consente di ottenere un modello coerente con la realtà scientifica e facilmente estendibile.

---

## Motivazione della scelta del tema

La scelta del tema “Spazio e Universo” è motivata da:

* l’elevato numero di entità e relazioni modellabili;
* la possibilità di utilizzare concetti avanzati di database (ENUM, relazioni N–N, date, gerarchie);
* l’interesse scientifico e culturale del tema;
* la possibilità di realizzare un progetto ampio e strutturato, adatto a dimostrare le competenze acquisite.

---

## Struttura generale del progetto

Il progetto verrà sviluppato progressivamente, suddividendolo in più livelli:

1. Struttura dell’Universo (superammassi, galassie)
2. Oggetti cosmici
3. Sistemi stellari e strutture
4. Eventi ed evoluzione temporale
5. Osservazioni scientifiche e strumenti
6. Missioni spaziali e contributo umano

Ogni livello sarà integrato nel database e collegato all’applicazione Java.

---

## Conclusione

Universe_DB rappresenta un tentativo di applicare in modo concreto e avanzato le conoscenze sui database relazionali e sulla programmazione Java, realizzando un progetto complesso, coerente e strutturato, ispirato al funzionamento dei veri archivi astronomici scientifici.
# Universe_DB – Schema concettuale e interrogazioni di esempio

## SCHEMA DEL DATABASE (modello concettuale-logico)

GALASSIA (id_galassia, nome, tipo, redshift, massa)

STELLA (id_stella, nome, tipo_spettrale, temperatura, fase_evolutiva, id_galassia*)

SISTEMA_STELLARE (id_sistema, nome, id_stella_centrale*)

PIANETA (id_pianeta, nome, tipo, abitabile, atmosfera, id_sistema*)

SATELLITE (id_satellite, nome, raggio, id_pianeta*)

BUCO_NERO (id_buco_nero, nome, tipo, massa, rotazione, id_galassia*)

EVENTO_COSMICO (id_evento, tipo, data_evento, energia_rilasciata, id_stella*)

STRUMENTO (id_strumento, nome, tipo, lunghezza_onda)

OSSERVAZIONE (id_osservazione, data_osservazione, risultati, confermata, id_oggetto, id_strumento*)

MISSIONE (id_missione, nome, agenzia, data_lancio, data_fine, obiettivo)

ASTRONAUTA (id_astronauta, nome, cognome, nazionalita, data_nascita)

PARTECIPAZIONE (id_missione*, id_astronauta*, ruolo)

(NB) assumiamo che una stessa osservazione possa riguardare un solo oggetto cosmico e che uno strumento possa effettuare più osservazioni nello stesso giorno.

---

## QUERY DI ESEMPIO (ridotte ma complesse)

Le seguenti interrogazioni sono state selezionate perché **strutturalmente complesse** e rappresentative delle principali funzionalità SQL avanzate (JOIN multipli, GROUP BY, HAVING, sottoquery, funzioni di aggregazione e ordinamenti). Il modello dati consente molte altre interrogazioni analoghe.

1. Visualizzare, per ogni galassia, il nome e il **numero totale di stelle**, mostrando solo le galassie che hanno un numero di stelle superiore alla media globale.

2. Visualizzare i **pianeti abitabili** indicando il nome del pianeta, della stella centrale e della galassia di appartenenza, ordinando il risultato per distanza gerarchica (galassia → stella → pianeta).

3. Calcolare il **numero medio di pianeti per sistema stellare** e visualizzare solo i sistemi che hanno un numero di pianeti superiore a tale valore.

4. Visualizzare le galassie che **contengono almeno un buco nero supermassiccio** con massa superiore alla massa media di tutti i buchi neri presenti nel database.

5. Visualizzare, per ogni agenzia spaziale, il **numero totale di missioni** e la **durata media delle missioni**, considerando solo le agenzie con almeno due missioni registrate.

6. Trovare lo **strumento scientifico** che ha effettuato il maggior numero di osservazioni confermate, mostrando anche il numero totale di osservazioni effettuate.

7. Visualizzare gli astronauti che hanno partecipato a **più di una missione**, indicando per ciascuno il numero di missioni e ordinando il risultato in ordine decrescente.

8. Visualizzare le stelle che hanno generato **più eventi cosmici** rispetto alla media degli eventi associati alle stelle, indicando il tipo di evento più frequente.

9. Per ogni galassia, visualizzare il **numero di pianeti abitabili** presenti e mostrare solo le galassie che ne possiedono almeno uno.

10. Trovare la galassia che presenta il **rapporto più alto tra numero di pianeti abitabili e numero totale di stelle**, mostrando entrambi i valori.
*/
