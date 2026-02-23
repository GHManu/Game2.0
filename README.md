# Game2.0
*Gioco della [Repository](https://github.com/GHManu/Game) (tipologia:Pokemon-like -> un RPG action adventure), riscritto con le regole del corso di Ingegneria Del Software; ovviamente non è completo, ma ho cercato di farlo con quello che c'era*


# Analisi Dei Requisiti
## Scopo
L'obiettivo è sconfiggere il nemico muovendoti (WASD o frecce, shift per lo sprint temporaneo con ricarica) in una mappa piccola e chiusa e attaccando con il tasto sinistro del
mouse. Il nemico si muove in due direzioni e attacca con un proiettile alla volta, quando uno esplode ne parte un altro.


## Requisiti Funzionali
   1. Movimento del giocatore
      RF1.1 – Il sistema deve permettere al giocatore di muovere il personaggio nelle quattro direzioni tramite tasti WASD o frecce direzionali.
      RF1.2 – Il sistema deve permettere al giocatore di attivare uno sprint temporaneo tramite il tasto Shift.
      RF1.3 – Il sistema deve gestire un tempo di ricarica (cooldown) dello sprint, impedendone l’uso finché non è ricaricato.
      RF1.4 – Il sistema deve impedire al giocatore di uscire dai confini della mappa.

   2. Attacco del giocatore
      RF2.1 – Il sistema deve permettere al giocatore di attaccare tramite il tasto sinistro del mouse.
      RF2.2 – Il sistema deve generare un proiettile o un colpo ogni volta che il giocatore attacca.
      RF2.3 – Il sistema deve rilevare le collisioni tra l’attacco del giocatore e il nemico.
      RF2.4 – Il sistema deve applicare danno al nemico quando viene colpito.

   3. Comportamento del nemico
      RF3.1 – Il sistema deve generare almeno un nemico all’interno della mappa.
      RF3.2 – Il nemico deve muoversi automaticamente in due direzioni predefinite (es. avanti/indietro, sinistra/destra).
      RF3.3 – Il nemico deve attaccare il giocatore tramite un proiettile singolo.
      RF3.4 – Il nemico deve poter sparare un nuovo proiettile solo dopo che il precedente è esploso o scomparso.
      RF3.5 – Il sistema deve rilevare le collisioni tra il proiettile nemico e il giocatore.
      RF3.6 – Il sistema deve applicare danno al giocatore quando viene colpito.

   4. Gestione della mappa
      RF4.1 – Il sistema deve caricare una mappa chiusa e di dimensioni limitate.
      RF4.2 – Il sistema deve impedire a giocatore e nemico di attraversare i muri o gli ostacoli.
      RF4.3 – Il sistema deve gestire la posizione iniziale del giocatore e del nemico.

   5. Sistema di salute e condizioni di vittoria/sconfitta
      RF5.1 – Il sistema deve gestire i punti vita del giocatore.
      RF5.2 – Il sistema deve gestire i punti vita del nemico.
      RF5.3 – Il sistema deve dichiarare la vittoria quando il nemico viene sconfitto.
      RF5.4 – Il sistema deve dichiarare la sconfitta quando i punti vita del giocatore raggiungono zero.
      RF5.5 – Il sistema deve mostrare un messaggio o una schermata di fine partita.

   6. Interfaccia utente
      RF6.1 – Il sistema deve mostrare la barra della vita del giocatore.
      RF6.2 – Il sistema deve mostrare la barra della vita del nemico (se previsto).
      RF6.3 – Il sistema deve mostrare lo stato dello sprint (attivo, in ricarica, disponibile).
      RF6.4 – Il sistema deve permettere di riavviare la partita dopo la fine.

   7. Gestione degli effetti e delle animazioni
      RF7.1 – Il sistema deve mostrare un’animazione o effetto quando un proiettile esplode.
      RF7.2 – Il sistema deve mostrare un’animazione o effetto quando il nemico o il giocatore subiscono danno.

## Requisiti Non Funzionali
   1. Prestazioni
      RNF1.1 – Il sistema deve mantenere un framerate stabile (es. 60 FPS) su hardware di riferimento.
      RNF1.2 – Il tempo di risposta ai comandi del giocatore deve essere immediato, con una latenza percepita inferiore a 100 ms.
      RNF1.3 – Il caricamento della mappa deve avvenire in meno di 3 secondi.

   2. Usabilità
      RNF2.1 – I comandi devono essere intuitivi e facilmente accessibili (WASD/frecce, mouse, Shift).
      RNF2.2 – L’interfaccia deve essere chiara e mostrare informazioni essenziali (vita, sprint, eventuale vita del nemico).
      RNF2.3 – Il gioco deve essere comprensibile senza necessità di un tutorial complesso.

   3. Affidabilità
      RNF3.1 – Il sistema non deve andare in crash durante una sessione di gioco standard.
      RNF3.2 – Le collisioni devono essere rilevate in modo coerente e ripetibile.
      RNF3.3 – Il comportamento del nemico deve essere deterministico o comunque privo di errori logici (es. non deve sparare due proiettili contemporaneamente).

   4. Manutenibilità
      RNF4.1 – Il codice deve essere strutturato in moduli separati (movimento, attacchi, UI, nemico, mappa).
      RNF4.2 – Il sistema deve permettere l’aggiunta di nuovi nemici o nuove abilità senza modificare pesantemente il codice esistente.
      RNF4.3 – Il progetto deve includere documentazione minima per facilitare modifiche future.

   5. Portabilità
      RNF5.1 – Il gioco deve essere eseguibile su sistemi Windows (o su altri sistemi previsti dal progetto).
      RNF5.2 – Il sistema non deve dipendere da librerie non portabili o non supportate.

   6. Sicurezza
      RNF6.1 – Il sistema deve impedire input non validi o fuori dai limiti (es. coordinate negative, velocità infinita).
      RNF6.2 – Il sistema deve evitare exploit che permettano al giocatore di uscire dalla mappa o attaccare più velocemente del previsto.

   7. Qualità grafica e audio
      RNF7.1 – Le animazioni devono essere fluide e coerenti con lo stile del gioco.
      RNF7.2 – Gli effetti sonori devono essere sincronizzati con gli eventi (attacco, danno, esplosione).
      RNF7.3 – Gli asset grafici devono essere leggibili e non confondere il giocatore.

   8. Scalabilità (anche minima)
      RNF8.1 – Il sistema deve poter gestire l’aggiunta di ulteriori elementi (nuovi proiettili, nuovi nemici, ostacoli) senza degradare significativamente le prestazioni.
      RNF8.2 – La mappa deve poter essere sostituita o ampliata senza riscrivere il motore di gioco.

## Diagrammi
### Use Case Diagram
### Class Diagram
### Sequence Diagram
### Activity Diagram
### State Diagram

## Design Pattern


## Modifiche Apportate
- Le classi astratte le chiamerò con una A iniziale, le interfacce con I e le enumerazoni con E; 
scrivo i metodi con il camelCase mentre le variabili tutte minuscole con _, e le costanti tutte in maiuscolo
- In generale applicherò un principio per avere un po' di clean code, quello usato nel Design Pattern Facade, ovvero Principle of Least Knowledge
- Ho creato una Camera mobile

- Farei la distinzione tra personaggi giocabili, personaggi bot, con i quali non posso giocarci ma con cui ci posso interagire e i nemici, personaggi non giocabili ma contro cui i personaggi giocabili si devono scontrare e ci possono essere tipi diversi di tutti questi; nello specifico ad esempio ci possono essere diversi enemy in un posto;
 quindi ho scelto di il Desingn Pattern Strategy, poichè ad esempio tra i personaggi giocabili, il movimento o gli attacchi potrebbero cambiare 
 --> quindi farò due interfacce/classe astratte per l'attacco e per il movimento di tutti i character

- Per gestire le immagini ho creato un Enum con dentro le immagini e ho attuato un po' la strategia del Singleton (Costruttore privato e metodo pubblico) e ho modificato anche il metodo `changeImage()` 
- Per l'Input ho creato un InputManager e per i tasti ho creato una classe a parte, dalla quale l'InputManager dipende(poichè chiamo metodi che richiamano le var dentro a quella classe)

- Applico il FactoryMethod/AbstractFactoryMethod per la creazione di armi
- Applico il FactoryMethod/AbstractFactoryMethod per la creazione di nemici e personaggi giocabili
- Dato che voglio fare in modo che ogni personaggio possa avere più armi e quindi avere più attacchi, creo solo un Enemy e un Player, che però modificano
il movimento e l'attacco in base al movimento e all'arma e cerco di implementare le armi e il movimento affinchè siano il più generiche possibile per poterle usare anche per il player

- Ovviamente le armi hanno un loro attacco standard, però l'implementazione del Player e dell'Enemy sono diverse, perciò creerò due classi per implementare l'attacco; 

- Uso il design patter MVC(Model View Controller) per separare logica, UI e interazione per quanto riguarda il menù e l'UI
- Ho creato una Mappa con erba, muri e acqua
- Uso un HashMap per la mappa di gioco così riesco a riempire anche coordinate negative
- Uso lo State per le fasi del game loop e per l'UI
- Applico il Singleton a HUD
- Applico un Command per i vari tasti di UI



### UML (Class Diagram)
```mermaid

```
