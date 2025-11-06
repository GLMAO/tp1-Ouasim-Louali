package org.emp.gl.core.launcher;

import org.emp.gl.clients.Horloge;
import org.emp.gl.clients.CompteARebours;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerService;
import java.util.Random;


public class App {
/*
    public static void main(String[] args) {
        testDuTimeService();
    }

    private static void testDuTimeService() {
        // Instancier le TimerService (une seule instance partagée)
        TimerService timerService = new DummyTimeServiceImpl(); //creation de l'observable

        // Instancier plusieurs Horloges avec la même instance de TimerService
        Horloge horloge1 = new Horloge("Horloge-Alger", timerService);
        Horloge horloge2 = new Horloge("Horloge-Londres", timerService);
        Horloge horloge3 = new Horloge("Horloge-Tokyo", timerService);
        Horloge horloge4 = new Horloge("Horloge-NewYork", timerService);

        System.out.println("\n=== Toutes les horloges sont démarrées ===\n");
    }

 */
    /*
    public static void main(String[] args) {
        testCompteARebours();
    }

        private static void testCompteARebours() {
            // Instancier le TimerService
            TimerService timerService = new DummyTimeServiceImpl();

            // Instancier CompteARebours avec le paramètre 5
            CompteARebours compte = new CompteARebours("Compte-1", 5, timerService);

            System.out.println("\n=== Compte à rebours démarré ===\n");
        }
*/
    public static void main(String[] args) {
        testMultiplesComptesARebours();
    }

        private static void testMultiplesComptesARebours() {
            // Instancier le TimerService
            TimerService timerService = new DummyTimeServiceImpl();

            // Créer un générateur de nombres aléatoires
            Random random = new Random();

            // Instancier 10 CompteARebours avec des valeurs aléatoires entre 10 et 20
            for (int i = 1; i <= 10; i++) {
                int valeurAleatoire = 10 + random.nextInt(11); // 10 + [0..10] = [10..20]
                new CompteARebours("Compte-" + i, valeurAleatoire, timerService);
            }

            System.out.println("\n=== 10 Comptes à rebours démarrés avec des valeurs aléatoires ===\n");
        }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}