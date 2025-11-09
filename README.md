# Game2.0
*Gioco della [Repository](https://github.com/GHManu/Game), riscritto con le regole del corso di Ingegneria Del Software*


## Analisi Dei Requisiti






## Differenze
- Le classi astratte le chimerò con una A iniziale e le interfacce con I.
- Farei la distinzione tra personaggi giocabili, personaggi bot che non posso giocarci ma con cui ci posso interagire e i nemici personaggi non giocabili ma contro cui i personaggi giocabili si devono scontrare e ci possono essere tipi diversi di tutti questi; nello specifico ad esempio ci possono essere diversi enemyFireWeaponType in un posto;
 quindi dovrò attuare il Desingn Pattern Strategy, poichè ad esempio tra i personaggi giocabili, il movimento o gli attacchi potrebbero cambiare 
 --> quindi farò due interfacce/classe astratte per l'attacco e per il movimento di tutti i character

- In generale applicherò un principio per avere un po' di clean code, quello usato nel Design Pattern Facade, ovvero Principle of Least Knowledge

- Per gestire le immagini ho creato un Enum con dentro le immagini e ho attuato un po' la strategia del Singleton (Costruttore privato e metodo pubblico) e ho modificato anche il metodo `changeImage()` 
-Applico il Singleton al Game Update, con la tecnica del doppio check

### UML (Class Diagram)
```mermaid

```
