package br.com.idolink.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.Colors;

@Repository
public interface ColorsRepository extends JpaRepository<Colors, Long> {

	Colors findByHexadecimalCode(String hexadecimal);
}
