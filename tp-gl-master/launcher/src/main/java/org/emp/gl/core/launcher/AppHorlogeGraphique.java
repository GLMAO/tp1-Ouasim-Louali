package org.emp.gl.core.launcher;

import org.emp.gl.clients.HorlogeGraphique;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerService;

import javax.swing.*;

/**
 * Classe principale pour lancer l'application d'horloge graphique
 * Question BONUS du TP1
 *
 * @author Ouasim
 */
public class AppHorlogeGraphique {

    public static void main(String[] args) {
        testHorlogeGraphiqueSimple();
        // Décommenter pour tester plusieurs horloges:
        // testMultiplesHorlogesGraphiques();
    }

    /**
     * Test avec une seule horloge graphique
     */
    private static void testHorlogeGraphiqueSimple() {
        // Créer l'instance du TimerService (déjà démarrée dans le constructeur)
        TimerService timerService = new DummyTimeServiceImpl();

        // Utiliser SwingUtilities.invokeLater pour créer l'interface sur l'EDT
        SwingUtilities.invokeLater(() -> {
            // Créer et afficher l'horloge graphique
            HorlogeGraphique horloge = new HorlogeGraphique("Alger", timerService);
            horloge.setVisible(true);
        });

        System.out.println("\n=== Horloge Graphique d'Alger démarrée ===\n");
    }

    /**
     * Test avec plusieurs horloges graphiques (optionnel)
     * Simule des horloges dans différentes villes
     */
    private static void testMultiplesHorlogesGraphiques() {
        // Créer l'instance du TimerService (partagée entre toutes les horloges)
        TimerService timerService = new DummyTimeServiceImpl();

        // Noms des horloges
        String[] villes = {"Alger", "Paris", "Londres", "New York"};

        SwingUtilities.invokeLater(() -> {
            // Créer plusieurs horloges avec des positions différentes
            for (int i = 0; i < villes.length; i++) {
                HorlogeGraphique horloge = new HorlogeGraphique(villes[i], timerService);

                // Positionner les fenêtres en cascade
                horloge.setLocation(100 + (i * 50), 100 + (i * 50));
                horloge.setVisible(true);
            }
        });

        System.out.println("\n=== " + villes.length + " Horloges Graphiques démarrées ===\n");
    }
}