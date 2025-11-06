package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe HorlogeGraphique : Affiche l'heure dans une interface graphique Swing
 * Utilise le pattern Observer via PropertyChangeListener
 *
 * @author Ouasim
 */
public class HorlogeGraphique extends JFrame implements TimerChangeListener {

    private JLabel labelHeure;
    private JLabel labelDate;
    private TimerService timerService;
    private DateTimeFormatter formatHeure = DateTimeFormatter.ofPattern("HH:mm:ss");
    private DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Constructeur de l'horloge graphique
     * @param name Nom de l'horloge
     * @param timerService Le service de timer à observer
     */
    public HorlogeGraphique(String name, TimerService timerService) {
        this.timerService = timerService;

        // Configuration de la fenêtre
        setTitle("Horloge Digitale - " + name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 220);
        setLocationRelativeTo(null); // Centre la fenêtre

        // Création de l'interface
        initUI();

        // S'inscrire comme observer du TimerService
        this.timerService.addTimeChangeListener(this);

        // Afficher l'heure initiale
        updateTime();

        System.out.println("HorlogeGraphique " + name + " initialized!");
    }

    /**
     * Initialise l'interface utilisateur
     */
    private void initUI() {
        // Panel principal avec fond sombre
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(40, 44, 52));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Label pour l'heure
        labelHeure = new JLabel("00:00:00", SwingConstants.CENTER);
        labelHeure.setFont(new Font("Monospaced", Font.BOLD, 56));
        labelHeure.setForeground(new Color(97, 218, 251));

        // Label pour la date
        labelDate = new JLabel("01/01/2024", SwingConstants.CENTER);
        labelDate.setFont(new Font("Arial", Font.PLAIN, 22));
        labelDate.setForeground(new Color(171, 178, 191));

        // Panel pour le titre
        JLabel titre = new JLabel("⏰ Horloge Digitale ⏰", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 18));
        titre.setForeground(new Color(152, 195, 121));

        // Ajout des composants
        mainPanel.add(titre, BorderLayout.NORTH);
        mainPanel.add(labelHeure, BorderLayout.CENTER);
        mainPanel.add(labelDate, BorderLayout.SOUTH);

        add(mainPanel);
    }

    /**
     * Méthode appelée à chaque tick du timer
     * Implémentation de PropertyChangeListener
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Écouter les changements de seconde
        if (SECONDE_PROP.equals(evt.getPropertyName())) {
            updateTime();
        }
    }

    /**
     * Met à jour l'affichage de l'heure et de la date
     */
    private void updateTime() {
        // Utiliser SwingUtilities pour mettre à jour l'interface depuis l'EDT
        SwingUtilities.invokeLater(() -> {
            // Utiliser le TimerService pour obtenir l'heure
            String heureFormatee = String.format("%02d:%02d:%02d",
                    timerService.getHeures(),
                    timerService.getMinutes(),
                    timerService.getSecondes()
            );

            labelHeure.setText(heureFormatee);
            labelDate.setText(java.time.LocalDate.now().format(formatDate));
        });
    }

    /**
     * Méthode de nettoyage : se désinscrire du service
     */
    public void cleanup() {
        if (timerService != null) {
            timerService.removeTimeChangeListener(this);
        }
    }

    @Override
    public void dispose() {
        cleanup();
        super.dispose();
    }
}