package br.com.idolink.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.WallpaperColor;

@Repository
public interface WallpaperColorRepository extends JpaRepository<WallpaperColor, Long>{

}
