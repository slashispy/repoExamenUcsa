package py.edu.ucsa.rest.api.web.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import py.edu.ucsa.rest.api.core.model.Departamento;
import py.edu.ucsa.rest.api.core.services.DepartamentoService;
import py.edu.ucsa.rest.api.web.dto.ErrorDTO;

@CrossOrigin
@RestController
@RequestMapping("/departamento")
public class DepartamentoController {
	
	public static final Logger logger = LoggerFactory.getLogger(DepartamentoController.class);
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> crearDepartamento(@RequestBody Departamento dto, UriComponentsBuilder ucBuilder){
		logger.info("Creando el Departamento {}", dto);
		if(departamentoService.isExisteDepartamento(dto)) {
			logger.error("Insercion Fallida. Ya existe un registro con el codigo {}.", dto.getCodigo());
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Insercion fallida. Ya existe un registro con el codigo "+dto.getCodigo()), HttpStatus.CONFLICT);
		}
		if(!("A".equals(dto.getEstado()) || "I".equals(dto.getEstado()))) {
			logger.error("Insercion Fallida. Valor no valido para el campo Estado {}. ", dto.getEstado());
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Insercion Fallida. Valor no valido para el campo Estado {}. "+ dto.getEstado()), HttpStatus.CONFLICT);
		}
		departamentoService.guardarDepartamento(dto);
		dto = departamentoService.getByCodigo(dto.getCodigo());
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/deparmtamento/{id}").buildAndExpand(dto.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);	
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> listarDepartamentos(){
		List<Departamento> dptos = departamentoService.listarDepartamentos();
		if(dptos.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Departamento>>(dptos,HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getDepartamentoById(@PathVariable("id") Integer id ){
		logger.info("Vamos obtener el departamento con id {}.", id);
		Departamento dpto = departamentoService.getById(id);
		if(dpto == null) {
			logger.error("No se encontro ningun Usuario con id {}.",id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No se encontro ningun Departamento con id " + id), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Departamento>(dpto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarDepartamento(@RequestBody Departamento dpto, @PathVariable("id") Integer id){
		logger.info("Actualizando el departamento con id {}", id);
		Departamento DepartamentoBD = departamentoService.getById(id);
		if(DepartamentoBD == null) {
			logger.error("Actualizacion Fallida. No existe el departamento con id {}.", id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. No existe el departamento con id " +id), HttpStatus.NOT_FOUND);
		}
		if(!("A".equals(dpto.getEstado()) || "I".equals(dpto.getEstado()))) {
			logger.error("Actualizacion Fallida. Valor no valido para el campo Estado {}. ", dpto.getEstado());
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. Valor no valido para el campo Estado {}. "+ dpto.getEstado()), HttpStatus.CONFLICT);
		}
		DepartamentoBD.setCodigo(dpto.getCodigo());
		DepartamentoBD.setDescripcion(dpto.getDescripcion());
		DepartamentoBD.setEstado(dpto.getEstado());
		departamentoService.guardarDepartamento(DepartamentoBD);
		return new ResponseEntity<Departamento>(DepartamentoBD, HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarDepartamento(@PathVariable("id") Integer id){
		logger.info("Obteniendo y eliminando Departamento con id {}", id);
		Departamento dpto = departamentoService.getById(id);
		if(dpto == null) {
			logger.error("Eliminacion Fallida, No existe Usuario con el id {}",id );
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Eliminacion Fallida, No existe Departamento con el id "+ id), HttpStatus.NOT_FOUND);
		}
		departamentoService.eliminarById(id);
		return new ResponseEntity<Departamento>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarTodos(){
		logger.info("Borrando todos los departamentos");
		departamentoService.eliminarTodos();
		return new ResponseEntity<Departamento>(HttpStatus.NO_CONTENT);
	}

}
