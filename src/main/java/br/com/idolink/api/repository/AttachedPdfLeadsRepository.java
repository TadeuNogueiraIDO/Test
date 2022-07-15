package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.AttachedPdf;
import br.com.idolink.api.model.AttachedPdfLeads;

@Repository
public interface AttachedPdfLeadsRepository extends JpaRepository<AttachedPdfLeads, Long> {

	List<AttachedPdfLeads> findByAttachedPdf(AttachedPdf attachedPdf);
	
}
