# Game2.0
*Gioco della [Repository](https://github.com/GHManu/Game) (Pokemon-like -> un RPG action adventure), riscritto con le regole del corso di Ingegneria Del Software*
 # Riassunto
L'obiettivo è sconfiggere il nemico muovendoti (WASD o frecce, shift per lo sprint temporaneo con ricarica) in una mappa piccola e chiusa e attaccando con il tasto sinistro del 
mouse. Il nemico si muove in due direzioni e attacca con un proiettile alla volta, quando uno esplode ne parte un altro.


## Analisi Dei Requisiti





## Differenze
- Le classi astratte le chiamerò con una A iniziale, le interfacce con I e le enumerazoni con E; 
scrivo i metodi con il camelCase mentre le variabili tutte minuscole con _, e le costanti tutte in maiuscolo
- In generale applicherò un principio per avere un po' di clean code, quello usato nel Design Pattern Facade, ovvero Principle of Least Knowledge


- Farei la distinzione tra personaggi giocabili, personaggi bot, con i quali non posso giocarci ma con cui ci posso interagire e i nemici, personaggi non giocabili ma contro cui i personaggi giocabili si devono scontrare e ci possono essere tipi diversi di tutti questi; nello specifico ad esempio ci possono essere diversi enemy in un posto;
 quindi ho scelto di il Desingn Pattern Strategy, poichè ad esempio tra i personaggi giocabili, il movimento o gli attacchi potrebbero cambiare 
 --> quindi farò due interfacce/classe astratte per l'attacco e per il movimento di tutti i character

- Per gestire le immagini ho creato un Enum con dentro le immagini e ho attuato un po' la strategia del Singleton (Costruttore privato e metodo pubblico) e ho modificato anche il metodo `changeImage()` 
- Per l'Input ho creato un InputManager e per i tasti ho creato una classe a parte, dalla quale l'InputManager dipende(poichè chiamo metodi che richiamano le var dentro a quella classe)

- Applico il FactoryMethod/AbstractFactoryMethod per la creazione di armi
- Dato che voglio fare in modo che ogni personaggio possa avere più armi e quindi avere più attacchi, creo solo un Enemy e un Player, che però modificano
il movimento e l'attacco in base al movimento e all'arma e cerco di implementare le armi e il movimento affinchè siano il più generiche possibile per poterle usare anche per il player

- Ovviamente le armi hanno un loro attacco standard, però l'implementazione del Player e dell'Enemy sono diverse, perciò creerò due classi per implementare l'attacco; 

- Uso il design patter MVC(Model View Controller) per separare logica, UI e interazione per quanto riguarda il menù e l'UI e uso anche lo State per le diverse fasi del gioco
- Ho creato una Mappa con erba, muri e acqua e una Camera
- Uso un HashMap per la mappa di gioco così riesco a riempire anche coordinate negative
- Uso un altro state per le diverse fasi del game loop
- Applico il Singleton a HUD e a EventBus
- Applico un Observer per la gestione di eventi, per separare ad esempio la logica dell'UI, quindi ho creto HUD per gestire cose che riguardano la modifica di UI dalla logica dei calcoli dei danni e abbassamento valori (poi si può estende ad audio ecc...)
- Applico un Command per i vari tasti di UI



### UML (Class Diagram)
```mermaid

```
