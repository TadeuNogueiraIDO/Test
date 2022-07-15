package br.com.idolink.api.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;

import br.com.idolink.api.dto.projection.GeneralLeadsProjection;
import br.com.idolink.api.dto.response.GeneralLeadsResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.model.AttachedPdfLeads;
import br.com.idolink.api.model.FormContactUs;
import br.com.idolink.api.model.NewsletterForm;
import br.com.idolink.api.model.enums.StatusLeads;
import br.com.idolink.api.model.enums.TypeLeads;
import br.com.idolink.api.repository.AttachedPdfLeadsRepository;
import br.com.idolink.api.repository.FormContactUsRepository;
import br.com.idolink.api.repository.GeneralLeadsRepository;
import br.com.idolink.api.repository.NewsletterFormRepository;
import br.com.idolink.api.service.GeneralLeadsService;
import br.com.idolink.api.utils.IdoStringUtils;

@Service
public class GeneralLeadsServiceImpl implements GeneralLeadsService {

	@Autowired
	private AttachedPdfLeadsRepository attachedPdfLeadsRepository;

	@Autowired
	private NewsletterFormRepository newsletterFormRepository;

	@Autowired
	private FormContactUsRepository formContactUsRepository;

	@Autowired
	private GeneralLeadsRepository repository;

	@Override
	@Transactional
	public List<GeneralLeadsResponse> listByUser(Long idUser) {

		List<GeneralLeadsProjection> response = repository.findByLeadsByUser(idUser);
		List<GeneralLeadsResponse> rest = new ArrayList<>();
		response.forEach(r -> {

			GeneralLeadsResponse resp = new GeneralLeadsResponse();
			resp.setId(r.getId());
			resp.setCreationDate(r.getDt_created());
			resp.setEmail(r.getEmail());
			resp.setPhone(r.getPhone());
			resp.setStatusLeads(r.getStatus_leads());
			resp.setTitleTool(r.getTitle_tool());
			resp.setTools(r.getTools());
			resp.setMessage(r.getText_box());
			if (resp.getStatusLeads() != null) {
				rest.add(resp);
			}
		});
		return rest;

	}


	@Override
	@Transactional
	public byte[] generateLeads(Long idUser, Boolean send) {
		
		if(send == null) {
			send = false;
		}
		
		if(!send) {

			List<String[]> data = new ArrayList<>();
			String[] headers = { "Title","Type Leads", "Creation Date", "Email", "Message", "Phone" };
			data.add(headers);

			List<GeneralLeadsResponse> response = listByUser(idUser);

			for (GeneralLeadsResponse r : response) {
				GeneralLeadsResponse resp = new GeneralLeadsResponse();
				resp.setId(r.getId());
				resp.setCreationDate(r.getCreationDate());
				resp.setEmail(r.getEmail());
				resp.setPhone(r.getPhone());
				resp.setTitleTool(r.getTitleTool());
				resp.setMessage(r.getMessage());
				resp.setTools(r.getTools());
	      
				String[] item2 = { resp.getTitleTool(), resp.getTools(), resp.getCreationDate().toString(), resp.getEmail(),resp.getMessage() , resp.getPhone()};
				data.add(item2);
			}

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			OutputStreamWriter w = new OutputStreamWriter(baos);
			CSVWriter csvWriter = new CSVWriter(w);

			csvWriter.writeAll(data);

			try {
				csvWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
			 return baos.toByteArray();

		}
		
		if(send) {
			List<String[]> data = new ArrayList<>();
			String[] headers = { "Title", "Status Leads","Type Leads", "Creation Date", "Email", "Message", "User", "Phone" };
			data.add(headers);

			List<GeneralLeadsResponse> response = listByUser(idUser);

			for (GeneralLeadsResponse r : response) {
				GeneralLeadsResponse resp = new GeneralLeadsResponse();
				resp.setId(r.getId());
				resp.setCreationDate(r.getCreationDate());
				resp.setEmail(r.getEmail());
				resp.setPhone(r.getPhone());
				resp.setStatusLeads(StatusLeads.OPEN.getName());
				resp.setTitleTool(r.getTitleTool());
				resp.setMessage(r.getMessage());
				resp.setTools(r.getTools());
	      
				String[] item2 = { resp.getTitleTool(), resp.getStatusLeads(), resp.getTools(), resp.getCreationDate().toString(), resp.getEmail(),resp.getMessage() , idUser.toString(), resp.getPhone()};
				data.add(item2);
			}

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			OutputStreamWriter w = new OutputStreamWriter(baos);
			CSVWriter csvWriter = new CSVWriter(w);

			csvWriter.writeAll(data);

			try {
				csvWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
			 return baos.toByteArray();
			 
			} 
		
		return null;
		

	}
	
	@Override
	@Transactional
	public List<GeneralLeadsResponse> update(Long idUser, String idTools, TypeLeads type, StatusLeads status) {
		
		List<GeneralLeadsResponse> response = listByUser(idUser);
		
		String formatString = idTools;
		
			List<GeneralLeadsResponse> resp = new ArrayList<>();
			
		List<Long> idToolsLong = IdoStringUtils.convertStringLong(formatString);
		
		for(int k = 0; k<idToolsLong.size(); k++) {
		
	

		int i = 0;
		for (GeneralLeadsResponse valid : response) {

			if (valid.getId().equals(idToolsLong.get(k))) {
				i = 1;
			}
		}
		if (i == 0) {

			throw new BaseFullException(HttpStatus.NOT_FOUND, "Leads", "api.error.leads.not.found");
		}
		}
		
		for(int z = 0; z<idToolsLong.size(); z++) {

		if (type.equals(TypeLeads.CONTACT)) {

			Optional<FormContactUs> model = formContactUsRepository.findById(idToolsLong.get(z));

			if (model.isEmpty()) {
				throw new BaseFullException(HttpStatus.NOT_FOUND, "Leads", "api.error.leads.not.found");
			}

			model.get().setStatusLeads(status);
			formContactUsRepository.save(model.get());

			GeneralLeadsResponse save = GeneralLeadsResponse.builder().creationDate(model.get().dateModel.dt_created)
					.email(model.get().getEmail()).tools(TypeLeads.CONTACT.getName()).id(idToolsLong.get(z))
					.message(model.get().getTextBox()).phone(model.get().getTelephone()).statusLeads(status.getName()).titleTool(model.get().getName())
					.build();

			resp.add(save);
		}
		if (type.equals(TypeLeads.NEWSLETTER)) {

			Optional<NewsletterForm> model = newsletterFormRepository.findById(idToolsLong.get(z));

			if (model.isEmpty()) {
				throw new BaseFullException(HttpStatus.NOT_FOUND, "Leads", "api.error.leads.not.found");
			}
			model.get().setStatusLeads(status);

			newsletterFormRepository.save(model.get());


			GeneralLeadsResponse save = GeneralLeadsResponse.builder().creationDate(model.get().dateModel.dt_created)
					.email(model.get().getEmail()).tools(TypeLeads.NEWSLETTER.getName()).id(idToolsLong.get(z))
					.phone(model.get().getTelephone()).statusLeads(status.getName()).titleTool(model.get().getName())
					.build();

			resp.add(save);

		}
		if (type.equals(TypeLeads.PDF)) {

			Optional<AttachedPdfLeads> model = attachedPdfLeadsRepository.findById(idToolsLong.get(z));
			if (model.isEmpty()) {
				throw new BaseFullException(HttpStatus.NOT_FOUND, "Leads", "api.error.leads.not.found");
			}

			model.get().setStatusLeads(status);

			attachedPdfLeadsRepository.save(model.get());

			GeneralLeadsResponse save = GeneralLeadsResponse.builder().creationDate(model.get().dateModel.dt_created)
					.email(model.get().getEmail()).tools(TypeLeads.PDF.getName()).id(idToolsLong.get(z))
					.phone(model.get().getPhone()).statusLeads(status.getName()).titleTool(model.get().getName())
					.build();

			resp.add(save);

		}
		}
		

		return resp;

	}

	@Override
	@Transactional
	public void deleteLeads(Long idUser, TypeLeads type, Long idTool) {

		List<GeneralLeadsResponse> response = listByUser(idUser);

		int i = 0;
		for (GeneralLeadsResponse valid : response) {


			if (idTool.equals(valid.getId())) {

				i = 1;
			}
		}
		if (i == 0) {

			throw new BaseFullException(HttpStatus.NOT_FOUND, "Leads", "api.error.leads.not.found");
		}

		if (type.equals(TypeLeads.CONTACT)) {

			Optional<FormContactUs> model = formContactUsRepository.findById(idTool);

			if (model.isEmpty()) {
				throw new BaseFullException(HttpStatus.NOT_FOUND, "Leads", "api.error.leads.not.found");
			}
			
			formContactUsRepository.delete(model.get());

		}
		if (type.equals(TypeLeads.NEWSLETTER)) {

			Optional<NewsletterForm> model = newsletterFormRepository.findById(idTool);

			if (model.isEmpty()) {
				throw new BaseFullException(HttpStatus.NOT_FOUND, "Leads", "api.error.leads.not.found");
			}
			
			newsletterFormRepository.delete(model.get());

		}
		if (type.equals(TypeLeads.PDF)) {

			Optional<AttachedPdfLeads> model = attachedPdfLeadsRepository.findById(idTool);
			
			if (model.isEmpty()) {
				throw new BaseFullException(HttpStatus.NOT_FOUND, "Leads", "api.error.leads.not.found");
			}
			attachedPdfLeadsRepository.delete(model.get());

		}

	}
	

	@SuppressWarnings("unused")
	private List<GeneralLeadsResponse> listAll(Long idUser) {

		List<GeneralLeadsProjection> response = repository.findByLeadsByUser(idUser);
		List<GeneralLeadsResponse> rest = new ArrayList<>();
		response.forEach(r -> {

			GeneralLeadsResponse resp = new GeneralLeadsResponse();
			resp.setId(r.getId());
			resp.setCreationDate(r.getDt_created());
			resp.setEmail(r.getEmail());
			resp.setPhone(r.getPhone());
			resp.setStatusLeads(r.getStatus_leads());
			resp.setTitleTool(r.getTitle_tool());
			resp.setTools(r.getTools());
			rest.add(resp);

		});
		return rest;
	}

}
