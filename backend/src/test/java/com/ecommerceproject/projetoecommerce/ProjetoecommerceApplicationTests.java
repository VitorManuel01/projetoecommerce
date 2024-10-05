package com.ecommerceproject.projetoecommerce;

import org.junit.platform.console.ConsoleLauncher;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
//MAIN DE TESTES a123 
@SpringBootTest(classes = ProjetoecommerceApplication.class)
@ActiveProfiles("test")
class ProjetoecommerceApplicationTests {

    public static void main(String[] args) {
        // Command-line arguments for ConsoleLauncher
        String[] consoleArgs = {
            "--select-package", "com.ecommerceproject.projetoecommerce",  // Replace with your package or test path
            "--reports-dir", "test-results"  // Output directory for the test reports
        };

        // Execute the ConsoleLauncher programmatically
        ConsoleLauncher.main(consoleArgs);
    }
}
