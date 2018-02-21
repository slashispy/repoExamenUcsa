package py.edu.ucsa.rest.api.web.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import py.edu.ucsa.rest.api.core.model.Departamento;
import py.edu.ucsa.rest.api.core.model.TiposExped;
import py.edu.ucsa.rest.api.core.services.TiposExpendService;
import py.edu.ucsa.rest.api.web.dto.ErrorDTO;

@RestController
@RequestMapping("/tipoExped")
public class TiposExpedController {

	public static final Logger logger = LoggerFactory.getLogger(TiposExpedController.class);
	
	@Autowired
	private TiposExpendService service;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> crearTipoExped(@RequestBody TiposExped tExp, UriComponentsBuilder ucBuilder){
		logger.info("Creando el tiposExpend {}", tExp);
		if(service.isExisteTiposExped(tExp)) {
			logger.error("Insercion Fallida. Ya existe un registro con el codigo {}.", tExp.getCodigo());
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Insercion fallida. Ya existe un registro con el codigo "+tExp.getCodigo()), HttpStatus.CONFLICT);
		}
		if(!("A".equals(tExp.getEstado()) || "I".equals(tExp.getEstado()))) {
			logger.error("Insercion Fallida. Valor no valido para el campo Estado {}. ", tExp.getEstado());
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Insercion Fallida. Valor no valido para el campo Estado {}. "+ tExp.getEstado()), HttpStatus.CONFLICT);
		}
		service.guardarTipoExped(tExp);
		tExp = service.getByCodigo(tExp.getCodigo());
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/tipoExped/{id}").buildAndExpand(tExp.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);	
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> listarTiposExped(){
		List<TiposExped> texps = service.listarTiposExped();
		if(texps.isEmpty()) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No se encontraron registros ") ,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<TiposExped>>(texps,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getTipoExpedById(@PathVariable("id") Integer id ){
		logger.info("Vamos obtener el TipoExped con id {}.", id);
		TiposExped tExp = service.getById(id);
		if(tExp == null) {
			logger.error("No se encontro ningun Usuario con id {}.",id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No se encontro ningun TipoExped con id " + id), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TiposExped>(tExp, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarTipoExped(@RequestBody TiposExped tExp, @PathVariable("id") Integer id){
		logger.info("Actualizando el departamento con id {}", id);
		TiposExped tExpBD = service.getById(id);
		if(tExpBD == null) {
			logger.error("Actualizacion Fallida. No existe el TiposExped con id {}.", id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. No existe el TipoExped con id " +id), HttpStatus.NOT_FOUND);
		}
		if(!("A".equals(tExp.getEstado()) || "I".equals(tExp.getEstado()))) {
			logger.error("Actualizacion Fallida. Valor no valido para el campo Estado {}. ", tExp.getEstado());
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. Valor no valido para el campo Estado {}. "+ tExp.getEstado()), HttpStatus.CONFLICT);
		}
		tExpBD.setCodigo(tExp.getCodigo());
		tExpBD.setDescripcion(tExp.getDescripcion());
		tExpBD.setEstado(tExp.getEstado());
		service.guardarTipoExped(tExpBD);
		return new ResponseEntity<TiposExped>(tExpBD, HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarTipoExped(@PathVariable("id") Integer id){
		logger.info("Obteniendo y eliminando TipoExped con id {}", id);
		TiposExped tExp = service.getById(id);
		if(tExp == null) {
			logger.error("Eliminacion Fallida, No existe TipoExped con el id {}",id );
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Eliminacion Fallida, No existe TipoExped con el id "+ id), HttpStatus.NOT_FOUND);
		}
		service.eliminarById(id);
		return new ResponseEntity<Departamento>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarTodos(){
		logger.info("Borrando todos los departamentos");
		service.eliminarTodos();
		return new ResponseEntity<Departamento>(HttpStatus.NO_CONTENT);
	}
}
