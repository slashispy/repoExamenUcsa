package py.edu.ucsa.rest.api.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tipos_exped_dptos")
@NamedQueries({
	@NamedQuery(name="TiposExpedDtos.findAll", query="SELECT t FROM TiposExpedDtos t"),
	@NamedQuery(name="TiposExpedDtos.findByDepartamento", query="SELECT t FROM TiposExpedDtos t WHERE t.departamento = :id"),
	@NamedQuery(name="TiposExpedDtos.findByTipoExped", query="SELECT t FROM TiposExpedDtos t WHERE t.tipoExped = :id"),
	@NamedQuery(name="TiposExpedDtos.findUnique", query="SELECT t FROM TiposExpedDtos t WHERE t.tipoExped = :id_tipo_exped "
			+ "AND t.departamento = :id_departamento AND t.sentido = :sentido")
})

public class TiposExpedDtos implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="id_tipo_exped")
	private TiposExped tipoExped;
	
	@ManyToOne
	@JoinColumn(name="id_departamento")
	private Departamento departamento;
	
	@Column(length=1, nullable =false) // 'P': POSITIVO, 'N': NEGATIVO
	private String sentido;
	
	@Column(precision=3, nullable = false)
	private Integer orden;
	
	@Column(length=1, nullable = false) // 'A': ACTIVO, 'I': INACTIVO
	private String estado;
	
	public TiposExpedDtos() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TiposExped getTipoExped() {
		return tipoExped;
	}

	public void setTipoExped(TiposExped tipoExped) {
		this.tipoExped = tipoExped;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String getSentido() {
		return sentido;
	}

	public void setSentido(String sentido) {
		this.sentido = sentido;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	
}
