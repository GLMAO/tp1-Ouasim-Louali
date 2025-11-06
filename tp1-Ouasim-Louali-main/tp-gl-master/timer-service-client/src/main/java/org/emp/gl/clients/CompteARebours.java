package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;
import java.beans.PropertyChangeEvent;

public class CompteARebours implements TimerChangeListener {

    private String name;
    private int compteur;
    private TimerService timerService;

    public CompteARebours(String name, int valeurInitiale, TimerService timerService) {
        this.name = name;
        this.compteur = valeurInitiale;
        this.timerService = timerService;

        this.timerService.addTimeChangeListener(this);

        System.out.println("CompteARebours " + name + " initialisé avec la valeur : " + valeurInitiale);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // evt.getPropertyName() retourne le nom de la propriété
        if (SECONDE_PROP.equals(evt.getPropertyName())) {
            if (compteur > 0) {
                System.out.println(name + " : " + compteur);
                compteur--;

                if (compteur == 0) {
                    System.out.println(name + " : " + compteur);
                    System.out.println(name + " : *** TERMINÉ ! Se désinscrit... ***");
                    timerService.removeTimeChangeListener(this);
                }
            }
        }
    }
}