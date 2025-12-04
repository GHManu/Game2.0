package com.example.game;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import java.util.Set;

public class Sprint {

    protected double timeSprint;
    protected double timeReCharge;
    protected static final double SPRINT_TIME_DURATION = 400.0;
    protected static final double RECHARGE_TIME_DURATION = 400.0;
    protected boolean isSprinting;


    public void controlSprint(double deltatime, Set<KeyCode> keyCodes, ACharacter plr){
        if(keyCodes.contains(AInputCommands.sprint)){
            this.sprintStatus(deltatime);
            this.sprint(deltatime, plr);
        }else {
            this.walk(deltatime, plr);
        }
    }



    protected void sprintStatus(double deltatime){
        if (!isSprinting && this.timeReCharge <= 0) {
            // Se non stiamo sprintando e il tempo di ricarica è finito
            isSprinting = true; //si attiva sprint
            this.timeSprint = SPRINT_TIME_DURATION;
            System.out.println("Sprint attivato! Durata: " + SPRINT_TIME_DURATION + " secondi.");
        } else if (isSprinting) {
            System.out.println("Già sprintando!");
        } else if (this.timeReCharge > 0) {
            System.out.println("Non puoi sprintare, il tempo di ricarica non è finito.");
        }

    }

    protected void sprint(double deltatime, ACharacter plr){
        if (this.isSprinting) {
            Platform.runLater(() -> {
                        plr.setSpeed(4);
                        this.timeSprint -= deltatime;
                    });
            System.out.println(this.timeSprint);
            if (this.timeSprint <= 0) {
                this.walk(deltatime, plr);
                this.isSprinting = false;
                this.timeReCharge = Player.RECHARGE_TIME_DURATION;
                System.out.println("Sprint finito! Inizia la ricarica.");
            }
        }
    }


    protected void walk(double deltaTime, ACharacter plr){
        plr.setSpeed(2.5);

        if (this.timeReCharge > 0) {
            Platform.runLater(() -> { this.timeReCharge -= deltaTime;});
            System.out.println("Sto ricaricando");
            if (this.timeReCharge <= 0) {
                System.out.println("Ricarica completa. Puoi sprintare di nuovo!");
            }
        }
    }
}
