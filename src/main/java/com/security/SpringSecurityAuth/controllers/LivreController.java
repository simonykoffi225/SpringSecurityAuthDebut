package com.security.SpringSecurityAuth.controllers;

import org.springframework.web.bind.annotation.*;

import com.security.SpringSecurityAuth.models.Livre;
import com.security.SpringSecurityAuth.service.LivreService;
import java.util.List;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/livre")
@AllArgsConstructor
public class LivreController {

    private final LivreService livreService;

    @PostMapping("/create")
    public Livre create(@RequestBody Livre livre) {
        return livreService.creer(livre);
    }

    @GetMapping("/read")
    public List<Livre> read() {
        return livreService.lire();
    }

    @GetMapping("/read/{id}")
    public Livre readLivre(@PathVariable Long id) {
        return livreService.lireParId(id);
    }

    @PutMapping("/update/{id}")
    public Livre update(@PathVariable Long id, @RequestBody Livre livre) {
        return livreService.modifier(id, livre);
    }

    @DeleteMapping("/delete/{id}")
   public String delete (@PathVariable Long id){
    return livreService.supprimer(id);
   }

    @PutMapping("/borrow/{id}")
    public String borrow(@PathVariable Long id) {
        return livreService.emprunter(id);
    }

    @PutMapping("/return/{id}")
    public String returnBook(@PathVariable Long id) {
        return livreService.retourner(id);
    }
}

