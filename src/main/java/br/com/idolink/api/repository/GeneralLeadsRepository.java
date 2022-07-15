package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.dto.projection.GeneralLeadsProjection;
import br.com.idolink.api.model.AttachedPdfLeads;



@Repository
public interface GeneralLeadsRepository extends JpaRepository<AttachedPdfLeads, Long> {

	
	@Query( value = "select 'attached_pdf_leads' as tools, apl.id, apl.\"name\" as title_tool, apl .email, apl.phone,apl.status as status_leads, null as text_box, apl .dt_created as dt_created from attached_pdf_leads apl \r\n"
			+ "inner join attached_pdf tn on apl.attached_pdf_id  = tn.id \r\n"
			+ "inner join ido i on tn.ido_id = i.id \r\n"
			+ "inner join business b on i.business_id = b.id \r\n"
			+ "where b.user_id = :userId AND apl.dt_deleted is NULL union all \r\n"
			+ "select 'contact_us' as tools, cu.id ,cu.\"name\" as title_tool, cu.email, cu.telephone, cu.status as status_leads, cu.text_box as text_box, cu.dt_created as dt_created  from contact_us cu \r\n"
			+ "inner join config_contact_us fn on cu.config_contact_us_id = fn.id \r\n"
			+ "inner join ido i on fn.ido_id = i.id \r\n"
			+ "inner join business b on i.business_id = b.id \r\n"
			+ "where b.user_id = :userId AND cu.dt_deleted is NULL  union all \r\n"
			+ "select 'newsletter_form' as tools, nf.id , nf.\"name\" as title_tool, nf.email , nf.telephone, nf.status as status_leads, null as text_box, nf.dt_created as dt_created from newsletter_form nf\r\n"
			+ "inner join config_newsletter_form cnf on nf.config_newsletter_form_id = cnf.id \r\n"
			+ "inner join ido i on cnf.ido_id = i.id \r\n"
			+ "inner join business b on i.business_id = b.id \r\n"
			+ "where nf.dt_deleted is NULL AND b.user_id = :userId ", nativeQuery = true)
	List<GeneralLeadsProjection> findByLeadsByUser(Long userId);
	
}
