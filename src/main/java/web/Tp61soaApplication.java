package web;


import entities.compte;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.CompteRepository;

import java.util.Date;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "repositories") // Active la configuration des dépôts JPA
@EntityScan(basePackages = "entities") // Active la recherche des entités JPA

public class Tp61soaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Tp61soaApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(CompteRepository compteRepository) {
        return args -> {
            // Ajout de données de compte initiales
            compte compte1 = new compte();
            compte1.setSolde(1500.0);
            compte1.setDateCreation(new Date());
            compte1.setType(compte.typeCompte.EPARGNE);
            compte1.setEtat(compte.etatCompte.BLOQUE);

            compte compte2 = new compte();
            compte2.setSolde(2000.0);
            compte2.setDateCreation(new Date());
            compte2.setType(compte.typeCompte.EPARGNE);
            compte2.setEtat(compte.etatCompte.ACTIVE);

            compte compte3 = new compte();
            compte3.setSolde(3000.0);
            compte3.setDateCreation(new Date());
            compte3.setType(compte.typeCompte.COURANT);
            compte3.setEtat(compte.etatCompte.SUSPENDU);
            compte compte4 = new compte();
            compte4.setSolde(60000.0);
            compte4.setDateCreation(new Date());
            compte4.setType(compte.typeCompte.EPARGNE);
            compte4.setEtat(compte.etatCompte.ACTIVE);

            // Sauvegarde des comptes dans le référentiel
            compteRepository.save(compte1);
            compteRepository.save(compte2);
            compteRepository.save(compte3);

            // Affichage des soldes des comptes enregistrés dans la console
            compteRepository.findAll().forEach(compte -> System.out.println(" compte #" + compte.getId() + ": " +"  Type de compte:  "+compte.getType()+"  Etat de compte:  "+compte.getEtat() + "  Solde du compte :"+compte.getSolde()));

        };
    }
}