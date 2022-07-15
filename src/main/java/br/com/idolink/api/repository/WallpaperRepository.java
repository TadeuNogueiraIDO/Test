package br.com.idolink.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.Wallpaper;

@Repository
public interface WallpaperRepository extends JpaRepository<Wallpaper, Long>{

	Optional<Wallpaper> findByWallpaper(String uiid);
	
	@Query("SELECT i.id FROM Wallpaper i WHERE  wallpaper = :uiid")
	Long findIdByFileUiid(String uiid);
	
	@Query("FROM Wallpaper i WHERE  wallpaper = :uiid")
	Optional<Wallpaper> findByFileUiid(String uiid);
	
	
}
