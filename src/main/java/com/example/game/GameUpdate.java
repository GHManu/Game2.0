package com.example.game;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

import java.io.Console;
import java.util.*;

//circa 89 righe

//implementa l'interfaccia per far si che il processo sia eseguibile
public class GameUpdate implements Runnable{
    private Player plr;
    Thread currentThread;   //per tenere conto del tempo del processo
    GameScene gameScene;
    final float FPS = 60; //Frame Per Second
    Enemy enemy;


    public GameUpdate(Group root){
        currentThread = new Thread(this);
        plr = new Player();
        enemy = new Enemy(new NormalPistol(), new NormalPistol());
    }


    //inizializzare le varie cose
    public void startGameLoop(GameScene gameScene, Group root){
        plr.setRoot(root);

        root.getChildren().addAll(plr.vBox,plr.cld.ret, plr.imgView); //per far si che l'immagine stia sopra al rettangolo
        this.gameScene = gameScene;
        root.getChildren().addAll(enemy.vBox, enemy.cld.ret, enemy.imgView);
        currentThread.start(); //chiama implicitamente run, eseguo il processo
        plr.setEnemy(enemy);

    }

    @Override
    public void run() { //processo in esecuzione
        /*
        Il deltaTime rappresenta il tempo trascorso tra due frame consecutivi, e serve a rendere il movimento indipendente
        dal frame rate. In parole semplici: se il gioco va a 30 FPS o 60 FPS, il player si muoverà alla stessa velocità percepita.
        * */
        double deltatime = 0;
        long currentTime;    //tempo corrente del processo
        long lastUpdate = System.currentTimeMillis();
        //creo un Set di KeyCode, per salvare tutti gli eventi relativi all'input da tastiera
        Set<KeyCode> keysPressed = new HashSet<>();
        //Set<MouseButton> mouseButtons = new HashSet<>();



        //gestisco gli eventi e aggiungo/rimuovo il codice del tasto premuto nel mio Set
        //event -> è la dichiarazione di un parametro che la lambda riceve (in questo caso, è un oggetto KeyEvent).
        //keysPressed.add(event.getCode()); è il corpo della lambda.
        //Non c'è bisogno di dichiarare esplicitamente event da parte tua, perché è fornito automaticamente dal metodo setOnKeyPressed
        //EventHandler<KeyEvent> è l'interfaccia che viene implementata per gestire l'evento,
        // ma l'oggetto che viene passato alla lambda è di tipo KeyEvent (che è l'oggetto che contiene i dettagli dell'evento di tastiera
        //quindi tutto questo: event -> keysPressed.add(event.getCode()) è un EventHandler<KeyEvent>
        if(plr.progressBar.getProgress() > 0.1) gameScene.setOnKeyPressed((event) -> keysPressed.add(event.getCode()));
        if(plr.progressBar.getProgress() > 0.1) gameScene.setOnKeyReleased(event -> keysPressed.remove(event.getCode()));


//        gameScene.setOnMousePressed(event -> {mouseButtons.add(event.getButton()); double getSceneX = event.getSceneX(); double getSceneY = event.getSceneY(); plr.getDestinationAttack(getSceneX, getSceneY);});
//        gameScene.setOnMouseReleased(event -> mouseButtons.remove(event.getButton()));

        /*
La ragione è che setOnKeyPressed e setOnKeyReleased sono già dei metodi di registrazione degli eventi, che
si attivano automaticamente quando l'evento specifico (come premere o rilasciare un tasto) si verifica.

Quindi:

Quando registri questi eventi, non c'è bisogno di monitorarli continuamente (non serve un loop), perché sono già gestiti
* in modo asincrono dal framework (JavaFX, in questo caso).
L'evento viene "sparato" in risposta a un'azione dell'utente, quindi non devi fare altro che impostare
* le azioni da eseguire quando l'evento si verifica (come aggiungere o rimuovere un tasto dalla lista).
In altre parole, il framework JavaFX gestisce per te il ciclo di vita degli eventi (quando viene premuto o rilasciato un tasto),
* quindi tu non devi scrivere manualmente un loop per monitorare la tastiera.
* */


        /*deltatime è il tempo trascorso tra un fotogramma (frame) e l’altro durante l’esecuzione di un gioco.
         Serve a rendere il comportamento del gioco indipendente dal frame rate. Ti faccio un esempio concreto:
        Mettiamo che un oggetto debba muoversi a 100 pixel al secondo. Se il tuo gioco gira a 60 fotogrammi al secondo,
        ogni frame durerà circa 0.016 secondi (1 diviso 60). Quindi in ogni frame sposterai l’oggetto di 100 * deltaTime → 100 * 0.016 ≈ 1.6 pixel.
        Se il framerate scende, deltaTime diventa più grande, e il movimento si adatta automaticamente: l'oggetto si muove alla stessa velocità percepita.
        Questo evita che il gioco vada troppo veloce o troppo lento in base a quanto è potente il computer.*/
        //gameLoop
        while(currentThread.isAlive()){
            currentTime = System.currentTimeMillis();
            //(currentTime - lastUpdate) è il tempo passato tra due frame
            //1000 / FPS → è la durata ideale di un frame, sempre in millisecondi (es: 1000 / 60 = 16.67ms).
            /*Quindi stai dicendo:
                “Quanto tempo è passato rispetto a quanto dovrebbe passare?”
               È un valore relativo, utile per capire quando è il momento di aggiornare il gioco.
            * */
            //System.out.println(currentTime);
            deltatime += (currentTime - lastUpdate) / (1000.0 / FPS);   //se uso FPS allora metto l'if dopo, sennò no
            lastUpdate = currentTime;
            //System.out.println(lastUpdate);
            if(deltatime >= 1){ //un secondo
                //System.out.println(deltatime);

                if(plr.progressBar.getProgress() > 0.1) gameMethodMovementHandler(deltatime, this.gameScene, keysPressed);
                if(plr.progressBar.getProgress() > 5.551115123125783E-17) gameMethodAttackHandler(deltatime);
                if(enemy != null && enemy.progressBar.getProgress() <= 0.1) {   kill_Enemy(); }//perchè è 1.1368683772161603E-13

                if(plr != null && plr.progressBar.getProgress() <= 0.1)    player_Died();
                //System.out.println(plr.progressBar.getProgress());

                if (enemy != null) {
                    //System.out.println("Attacco Nemico");
                    enemy.attack(deltatime, plr, enemy);
                    if(!enemy.attack_flag) enemy.getWeapon().shot(deltatime, plr, enemy.getWeapon().p, enemy);

                }
                //if(!enemy.attack_flag)  enemy.shot(deltatime,plr, enemy.p);
                deltatime--;
            }
        }

    }

    private void kill_Enemy(){
        if(this.enemy != null && this.enemy.cld.ret != null) {
            Platform.runLater(() -> {
                plr.root.getChildren().remove(enemy.vBox);
                enemy.vBox = null;
            });
            Platform.runLater(() -> {
                plr.root.getChildren().remove(enemy.cld.ret);
                enemy.cld.ret = null;
            });
           Platform.runLater(() -> {
                plr.root.getChildren().remove(enemy.imgView);
                enemy.imgView = null;
            });
           System.gc(); //richiama il garbage collector
        }

//        enemy.cld.ret = null;
//        enemy.cld = null;
//        enemy.img= null;
//        enemy.imgView = null;
//        enemy.progressBar = null;
//        enemy.vBox = null;

//        this.enemy = null;

    }

    private void player_Died(){
        if(this.plr != null && this.plr.cld.ret != null) {
            Platform.runLater(() -> {
                plr.root.getChildren().remove(plr.vBox);
                plr.vBox = null;
            });
            Platform.runLater(() -> {
                plr.root.getChildren().remove(plr.cld.ret);
                plr.cld.ret = null;
            });
            Platform.runLater(() -> {
                plr.root.getChildren().remove(plr.imgView);
                plr.imgView = null;
            });
            System.gc(); //richiama il garbage collector
        }
    }


    private void gameMethodMovementHandler(double deltaTime, GameScene gameScene, Set<KeyCode> keysPressed) {

            if(enemy != null && plr != null && plr.cld != null && enemy.cld != null)   plr.cld.collision_Detected(enemy.cld.ret, true);
            //gestisco l'evento, la penultima condizione degli if è per non far andare fuori mappa, l'ultima condizione è per la collisione
            if ( (keysPressed.contains(plr.forward) || keysPressed.contains(plr.forwardArrow)) && plr.y >0
                    ) {
                plr.dir_forward = true;

                plr.changeImage("Images/Back_Pg.png");
                if(plr.cld.fr)   plr.moveUp(deltaTime);
            }
            else{
                plr.dir_forward = false;
            }

            if ( (keysPressed.contains(plr.backward) || keysPressed.contains(plr.backwardArrow)) && plr.y < (IScreenSettings.screenHeight- IScreenSettings.sizeTile)
                     ) {
                plr.dir_backward = true;

                plr.changeImage("Images/Front_Pg.png");
                if(plr.cld.br) plr.moveDown(deltaTime);
            }
            else{
                plr.dir_backward = false;
            }

            if ( (keysPressed.contains(plr.leftward) || keysPressed.contains(plr.leftwardArrow)) && plr.x > 0
                    ) {
                plr.dir_leftward = true;


                plr.changeImage("Images/Left_Side_Pg.png");
                if(plr.cld.dx) plr.moveLeft(deltaTime);
            }
            else{
                plr.dir_leftward = false;
            }

            if ( (keysPressed.contains(plr.rightward) || keysPressed.contains(plr.rightwardArrow)) && plr.x < (IScreenSettings.screenWidth- IScreenSettings.sizeTile)
                    ) {
                plr.dir_rightward = true;

                plr.changeImage("Images/Right_Side_Pg.png");
                if(plr.cld.sx)    plr.moveRight(deltaTime);
            }
            else{
                plr.dir_rightward = false;
            }

            // Sprint
            // Sprint attivato quando il tasto è tenuto premuto
            if (keysPressed.contains(plr.sprint)) {
                plr.sprintStatus(deltaTime);  // Attiva o continua lo sprint
                plr.sprint(deltaTime);
            } else {
                plr.walk(deltaTime);
            }


        //     !!!!!!     PROBLEMA: NON è FLUIDO COSì !!!!!
//        gameScene.setOnKeyPressed(new GameKeyEventHandler() {
//            //ci possiamo aggiungere anche altri metodi
//            @Override
//            public void handle(KeyEvent keyEvent) {
//                Player.walk(keyEvent, deltaTime);
//            }
//
//        });

        //System.out.println("Delta time: " + deltaTime);

    }
//    private void gameMethodDirectionObliqueHandler(double deltaTime, GameScene gameScene, Set<KeyCode> keysPressed){
//
//        if (keysPressed.contains(plr.forward) && keysPressed.contains(plr.rightward)) {
//            plr.dir_forward_oblq_right = true;
//        }
//        else{
//            plr.dir_forward_oblq_right = false;
//        }
//
//        if (keysPressed.contains(plr.forward) && keysPressed.contains(plr.leftward)) {
//            plr.dir_forward_oblq_left = true;
//        }
//        else{
//            plr.dir_forward_oblq_left = false;
//        }
//
//        if (keysPressed.contains(plr.backward) && keysPressed.contains(plr.rightward)) {
//            plr.dir_backward_oblq_right = true;
//        }
//        else{
//            plr.dir_backward_oblq_right = false;
//        }
//
//        if (keysPressed.contains(plr.backward) && keysPressed.contains(plr.leftward)) {
//            plr.dir_backward_oblq_left = true;
//        }
//        else{
//            plr.dir_backward_oblq_left = false;
//        }
//    }
    private void gameMethodAttackHandler(double deltatime){

//             if(mouseButtons.contains(MouseButton.PRIMARY) && !plr.attack_flag){
//                 plr.normal_attack(deltatime);
//
//             }

             if(plr.progressBar.getProgress() > 5.551115123125783E-17) {
                 gameScene.setOnMouseClicked(new EventHandler<MouseEvent>() {
                     @Override
                     public void handle(MouseEvent mouseEvent) {
                         double getSceneX = mouseEvent.getSceneX();
                         double getSceneY = mouseEvent.getSceneY();
                         plr.setDestinationAttack(getSceneX, getSceneY);
                         plr.normal_attack(deltatime);
                     }
                 });
             }

        if(plr.attack_flag && plr.progressBar.getProgress() > 0.1){
            plr.shot(deltatime);
        }

    }
    /*
    perchè se io facessi un loop sullo stage ad esempio: faccio una funzione dove stampa loop su stdout e come parametro ha uno Stage
    e dentro faccio un while, che lo esegue finchè io non chiudo la finestra; questo però mi fa crashare l'esecuzione,
    perchè questo mi fa crashare e quello con animation timer no?
    Il problema che incontri deriva da un concetto fondamentale: JavaFX utilizza un Thread della GUI principale,
    chiamato JavaFX Application Thread. Questo thread gestisce il rendering dell'interfaccia grafica e gli eventi utente.
    Quando usi un ciclo while bloccante, come quello che hai descritto, il thread della GUI principale viene "occupato" e
     non può più eseguire altre operazioni, come aggiornare lo schermo o rispondere agli input dell'utente. Questo porta
      al crash o al blocco dell'applicazione.

    AnimationTimer è progettato specificamente per lavorare con il thread principale di JavaFX in modo non bloccante. Ecco cosa fa:

        Ogni frame, AnimationTimer delega al metodo handle(long now) un compito da eseguire.
        Questo metodo viene chiamato dal thread JavaFX quando ha completato altre operazioni come il rendering della GUI.

        Grazie a questa gestione interna, il thread principale rimane libero di aggiornare l'interfaccia e gestire
        gli eventi utente tra un frame e l'altro

    Oltre ad AnimationTimer si poteva fare così: Utilizzare un thread separato per le operazioni che richiedono un loop,
     ma mai aggiornare direttamente la GUI da un thread secondario (JavaFX non supporta il rendering multithread). Esempio:

     new Thread(() -> {
        while (true) {
            System.out.println("Loop in un thread separato!");
            try {
                Thread.sleep(100); // Evita il blocco costante
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }).start();

    Per aggiornare la GUI dal thread secondario, usa il metodo Platform.runLater():

    Platform.runLater(() -> {
        // Aggiornamenti della GUI qui
    });


Quando crei un'istanza di AnimationTimer, il metodo handle(long now) viene eseguito automaticamente a ogni frame.
Il parametro now fornisce il timestamp corrente in nanosecondi.
Puoi usarlo per calcolare il tempo trascorso tra un frame e l'altro (delta time), utile per sincronizzare movimenti e animazioni.
AnimationTimer tenta di eseguire il ciclo alla velocità massima supportata dalla CPU e GPU.
Se hai bisogno di un framerate specifico (es. 60 FPS), dovrai implementare un meccanismo per limitare il numero di aggiornamenti.

Allora quell'hanlde lo implemento io, ma c'è un metodo "ascoltatore" che ha una sua implementazione di handle, nel quale fa
una chiamata ricorsiva si chiama --> AnimationTimerReceiver

Poi c'è AccessControlContext che permette di avere il controllo del contesto corrente, ovvero del processo, decidere i privilegi,
gestire gli eventi dei processi, ecc...

Valore iniziale di now: Il valore di now è fornito automaticamente da AnimationTimer quando chiama il metodo handle(long now).
Questo valore rappresenta un timestamp in nanosecondi a partire da un'epoca fissa (di solito, l'istante in cui la JVM è stata avviata). Quindi, il valore iniziale di now è un grande numero in nanosecondi, e cambierà a ogni frame.

Incremento di lastUpdate:
Quando handle(long now) viene chiamato per la prima volta, il valore di lastUpdate è inizialmente 0 (poiché è stato inizializzato a 0).

Nella prima esecuzione del metodo, lastUpdate viene aggiornato per la prima volta con il valore di now,
sincronizzandolo con il timestamp iniziale.

Ogni volta che handle(long now) viene richiamato successivamente (a ogni frame), il valore di lastUpdate viene aggiornato
con l'ultimo valore di now.

Delta time (deltaTime):
Il deltaTime calcola la differenza temporale tra l'attuale now e l'ultima volta che handle(long now) è stato eseguito (lastUpdate).

Questo valore rappresenta il tempo trascorso tra due frame consecutivi, ed è utile per sincronizzare il movimento o le
animazioni nel tuo gioco.

Alla prima esecuzione del metodo handle(long now), il valore di lastUpdate è 0, dato che non è stato ancora aggiornato. In quel momento specifico:

if (lastUpdate == 0) {
    lastUpdate = now;
}
Il valore di lastUpdate viene impostato uguale a now, quindi non c'è alcun calcolo del delta in questo giro del ciclo.

Nelle successive esecuzioni del metodo handle(long now):
Il valore di lastUpdate contiene il timestamp dell'ultima volta che handle(long now) è stato eseguito.
now, invece, rappresenta il timestamp del momento corrente (fornito dalla JVM e aggiornato ogni volta che handle viene chiamato).
Poiché il tempo è trascorso tra due chiamate consecutive del metodo handle, il valore di now sarà sempre maggiore rispetto a lastUpdate.
Di conseguenza, now - lastUpdate sarà un valore positivo e rappresenta il tempo (in nanosecondi) trascorso dal frame precedente.

Esempio pratico:
Supponiamo che il valore di now alla prima chiamata di handle sia 1.000.000.000 nanosecondi (1 secondo).
In questo caso, lastUpdate viene impostato a 1.000.000.000, quindi non c'è calcolo di deltaTime.
Alla seconda chiamata di handle, supponiamo che il valore di now sia 1.016.000.000 nanosecondi (16 millisecondi dopo,
 circa 1/60 di secondo per un frame a 60 FPS).
lastUpdate contiene ancora il valore precedente: 1.000.000.000.
Il calcolo sarà quindi:
double deltaTime = (1.016.000.000 - 1.000.000.000) / 1e9;
// deltaTime = 16.000.000 / 1.000.000.000 = 0.016 secondi
Dopo il calcolo, lastUpdate viene aggiornato a 1.016.000.000.
Il flusso continuo: Ogni chiamata successiva aggiorna lastUpdate con il nuovo valore di now, garantendo che now - lastUpdate
rappresenti sempre il tempo trascorso tra i due frame consecutivi
* */
//    public static void startGameLoop(Scene gameScene, Group root, Stage stage) {
//        plr = new Player();
//        root.getChildren().add(plr.playerView);
//
//
//        AnimationTimer gameLoop = new AnimationTimer() {    //consente di eseguire un metodo ogni frame
//            @Override
//            public void handle(long now) {  //implemento io, questo metodo è astratto; now = System.nanoTime(); implicito
//                if (lastUpdate == 0) {
//                    lastUpdate = now;
//                }
//
//                // Calcolo delta time
//                double deltaTime = (now - lastUpdate) / 1e9; // Converti nanosecondi in secondi
//                deltaTime += deltaTime; //così è più fluido
//                lastUpdate = now;
//
//                // loop
//                gameLoop(deltaTime, gameScene);
//            }
//        };
//
//        gameLoop.start();
//    }

    //corpo del gioco, questo è il loop
//    private static void gameLoop(double deltaTime, Scene gameScene) {
//        //gestisco l'evento
//
//        gameScene.setOnKeyPressed(new GameKeyEventHandler() {
//            //ci possiamo aggiungere anche altri metodi
//            @Override
//            public void handle(KeyEvent keyEvent) {
//                Player.walk(keyEvent, deltaTime);
//            }
//
//        });
//
//        //System.out.println("Delta time: " + deltaTime);
//
//
//
//    }



}
