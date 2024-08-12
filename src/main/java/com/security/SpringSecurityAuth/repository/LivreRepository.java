package com.security.SpringSecurityAuth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.SpringSecurityAuth.models.Livre;



@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {

}
