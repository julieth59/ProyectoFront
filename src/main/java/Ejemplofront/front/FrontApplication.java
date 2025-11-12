package Ejemplofront.front;

import Ejemplofront.front.window.MainFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import javax.swing.*;

@SpringBootApplication
@EnableFeignClients
public class FrontApplication {
    // Spring inyecta el componente MainFrame
    @Autowired
    private MainFrame mainFrame;

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(FrontApplication.class, args);
    }

    // A veces no carga porque es llamado sin que termine de inicializar todo el contexto de spring boot
    public void run(String... args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            // Llamar a la inicialización de componentes (que usa los servicios inyectados)
            mainFrame.initializeUI();
            mainFrame.setVisible(true);
        });
    }

    // Define un CommandLineRunner para mostrar la ventana principal de Swing si no logra cargar con el run
    @Bean
    @Order(1) // Asegura que se ejecute después de la inicialización principal
    public CommandLineRunner showMainFrame(MainFrame mainFrame) {
        return args -> {
            // Es crucial ejecutar la inicialización y visualización de Swing
            // en el Event Dispatch Thread (EDT) de Swing.
            SwingUtilities.invokeLater(() -> {
                // Aquí usamos el bean de la ventana principal de Swing
                mainFrame.initializeUI();
                mainFrame.setVisible(true);
            });
        };
    }
}


