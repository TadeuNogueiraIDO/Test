package br.com.idolink.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.idolink.api.model.Language;

public interface LanguageRepository extends JpaRepository<Language, Long>{

}
