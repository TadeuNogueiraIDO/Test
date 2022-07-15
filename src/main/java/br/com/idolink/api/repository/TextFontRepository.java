package br.com.idolink.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.TextFont;

@Repository
public interface TextFontRepository extends JpaRepository<TextFont, Long> {

	TextFont findByName(String name);
}
