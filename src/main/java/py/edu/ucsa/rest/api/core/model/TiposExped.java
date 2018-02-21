package py.edu.ucsa.rest.api.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tipos_exped")
@NamedQueries({
	@NamedQuery(name="TiposExped.findAll", query="SELECT t FROM TiposExped t"),
	@NamedQuery(name="TiposExped.findByCodigo", query="SELECT t FROM TiposExped t WHERE t.codigo = :codigo")
})
public class TiposExped implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(length=20)
	private String codigo;
	@Column(length=200)
	private String descripcion;
	@Column(length=1, nullable = false) // 'A': ACTIVO, 'I': INACTIVO
	private String estado;
	
	public TiposExped() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
		
}
