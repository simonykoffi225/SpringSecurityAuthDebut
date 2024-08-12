package com.security.SpringSecurityAuth.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.security.SpringSecurityAuth.models.Livre;
import com.security.SpringSecurityAuth.repository.LivreRepository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@AllArgsConstructor
@Data
public class LivreServiceImplement implements LivreService {
    private final LivreRepository livreRepository;
    

    @Override
    public Livre creer(Livre livre) {
        if (livre.getQuantiteInitiale() <= 0) {
            throw new IllegalArgumentException("La quantité initiale doit être positive.");
        }
        livre.setQuantite(livre.getQuantiteInitiale()); 
        return livreRepository.save(livre);
    }

    @Override
    public List<Livre> lire() {
        return livreRepository.findAll();
    }

    @Override
    public Livre lireParId(Long id) {
        return livreRepository.findById(id).orElse(null);
    }

    @Override
    public Livre modifier(Long id, Livre livre) {
        return livreRepository.findById(id)
                .map(l -> {
                    l.setTitre(livre.getTitre());
                    l.setAuteur(livre.getAuteur());
                    l.setResume(livre.getResume());
                    l.setQuantite(livre.getQuantite());
                    l.setQuantiteInitiale(livre.getQuantiteInitiale()); // Mise à jour de la quantité initiale
                    return livreRepository.save(l);
                }).orElseThrow(() -> new RuntimeException("Livre non trouvé !!"));
    }

    @Override
    public String supprimer(Long id) {
        livreRepository.deleteById(id);
        return "Livre supprimé";
    }

    @Override
    public String emprunter(Long id) {
        Livre livre = livreRepository.findById(id).orElse(null);
        if (livre != null && livre.getQuantite() > 0) {
            livre.setQuantite(livre.getQuantite() - 1);
            livreRepository.save(livre);
            return "Titre : " + livre.getTitre() + "\nAuteur : " + livre.getAuteur() +
                    "\nRésumé : " + livre.getResume() + "\nQuantité restante : " + livre.getQuantite();
        }
        return "Échec de l'emprunt du livre.";
    }

    public String retourner(Long id) {
        Livre livre = livreRepository.findById(id).orElse(null);
        if (livre != null) {
            if (livre.getQuantite() >= livre.getQuantiteInitiale()) {
                return "La quantité initiale a été atteinte, le livre ne peut pas être retourné.";
            }
            livre.setQuantite(livre.getQuantite() + 1);
            livreRepository.save(livre);
            return "Titre : " + livre.getTitre() + "\nAuteur : " + livre.getAuteur() +
                    "\nRésumé : " + livre.getResume() + "\nQuantité disponible : " + livre.getQuantite();
        }
        return "Échec du retour du livre.";
    }
}
