package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.IdoToolRequest;
import br.com.idolink.api.dto.response.IdoToolResponse;
import br.com.idolink.api.dto.response.ToolStatusListResponse;
import br.com.idolink.api.filter.ToolFilter;
import br.com.idolink.api.model.IdoTool;
import br.com.idolink.api.model.enums.IdoToolStatus;
import br.com.idolink.api.model.enums.ToolCodName;

public interface IdoToolService {
	
	IdoToolResponse create(Long idoId, IdoToolRequest request);
	
	IdoToolResponse update(Long id, IdoToolStatus status);
	
	IdoToolResponse findById(Long id);
	
	void delete(Long id);

	ToolStatusListResponse findByIdoAndTool(Long idoId, Long toolId);

	/**
	 * Busca todas as ferramentas. O ido em parâmetro eh para buscar as ferramentas utilizadas no ido
	 * @param idoId
	 * @param toolId
	 * @return
	 */
	ToolStatusListResponse findAllByIdo(Long idoId, ToolFilter filter);

	/**
	 * Busca todas as ferramentas retornando a lista de associacao da ferramenta com ido
	 * @param idoId
	 * @param toolId
	 * @return
	 */
	List<IdoTool> findListIdoAndTool(Long idoId, Long toolId);

	/**
	 * cria ou atualiza um relacionamento entre a ferramenta e o ido após a ferramenta ser criada
	 */
	void saveAssociateTool(ToolCodName toolCodName, Long idoId);

	/**
	 * ferramenta volta a esta disponivel apos ser deletada do ido
	 * @param toolCodName
	 * @param idoId
	 */
	void avaliableAssociateIdoTool(ToolCodName toolCodName, Long idoId);

	/**
	 * ferramenta volta a esta disponivel apos ser deletada do ido
	 * @param toolCodName
	 * @param idoId
	 */
	void avaliableAssociateIdoTool(ToolCodName toolCodName, Long idoId, Long qtd);

	IdoTool findIdoAndTool(Long idoId, Long toolId);
	
}
