package com.security.SpringSecurityAuth.service;

import com.security.SpringSecurityAuth.models.Livre;
import java.util.List;

public interface LivreService {

    Livre creer(Livre livre);

    List<Livre> lire();

    Livre lireParId(Long id);

    Livre modifier(Long id, Livre livre);

    String supprimer(Long id);

    String emprunter(Long id);

    String retourner(Long id);
}
