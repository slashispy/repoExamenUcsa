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
@Table(name = "departamentos")
@NamedQueries({
	@NamedQuery(name="Departamento.findAll", query="SELECT d FROM Departamento d"),
	@NamedQuery(name="Departamento.findByCodigo", query="SELECT d FROM Departamento d WHERE d.codigo = :codigo")
	})
public class Departamento implements Serializable {
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
	
	public Departamento() {}

	public Integer getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
