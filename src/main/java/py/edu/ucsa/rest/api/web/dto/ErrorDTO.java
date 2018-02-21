package py.edu.ucsa.rest.api.web.dto;

public class ErrorDTO {
	private String mensajeError;

	public ErrorDTO(String mensajeError) {
		super();
		this.mensajeError = mensajeError;
	}

	public String getMensajeError() {
		return mensajeError;
	}
	
	
}
