package br.com.idolink.api.model.enums;

import org.springframework.http.HttpStatus;

import br.com.idolink.api.execption.BaseFullException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileType {
	
	ANIMATED_GIF(1, "Imagem Animada","gif"),
	STATIC_JPG(2, "Imagem statica JPG","jpg"),
	STATIC_PNG(3, "Imagem statica PNG","png"),
	VECTOR_SVG(4, "imagem vetorizada", "svg"),
	DOC_PDF(5, "documento PDF", "pdf");
	
	private Integer id;
	
	private String name;
	
	private String extension;
	
	public static FileType getByExtension(String extension) {
		for (FileType f : FileType.values()) {
			if(f.getExtension().equals(extension)) {
				return f;
			}
		}
		throw new BaseFullException(HttpStatus.BAD_REQUEST,"File" ,"api.error.file.type.conflict");
	}
}
