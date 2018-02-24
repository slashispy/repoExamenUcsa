package py.edu.ucsa.rest.api.web.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import py.edu.ucsa.rest.api.core.model.TiposExpedDtos;
import py.edu.ucsa.rest.api.core.services.TiposExpedDtosService;
import py.edu.ucsa.rest.api.web.dto.ErrorDTO;

@CrossOrigin
@RestController
@RequestMapping("/tipoExpedDtos")
public class TiposExpedDtosController {

	public static final Logger logger = LoggerFactory.getLogger(TiposExpedDtosController.class);
	
	@Autowired
	private TiposExpedDtosService service;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> crearTipoExpedDtos(@RequestBody TiposExpedDtos tExpDtos){
		logger.info("Creando el TiposExpedDtos {}", tExpDtos);
		if(service.isExisteTipoExpedDtos(tExpDtos)) {
			logger.error("Insercion Fallida. Ya existe un registro con esta combinacion {TipoExped, Departamento, sentido}. {", tExpDtos.getTipoExped().getId()+", "+tExpDtos.getDepartamento().getId()+", "+tExpDtos.getSentido()+"}");
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Insercion Fallida. Ya existe un registro con esta combinacion {TipoExped, Departamento, sentido}. {"+ tExpDtos.getTipoExped().getId()+", "+tExpDtos.getDepartamento().getId()+", "+tExpDtos.getSentido()+"}"), HttpStatus.CONFLICT);
		}
//		Empieza el control de los caracteres permitidos
		if(!("P".equals(tExpDtos.getSentido()) || "N".equals(tExpDtos.getSentido()))) {
			logger.error("Insercion Fallida. Valor no valido para el campo Sentido {}. ", tExpDtos.getSentido());
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Insercion Fallida. Valor no valido para el campo Sentido {}. "+ tExpDtos.getSentido()), HttpStatus.CONFLICT);
		}
		if(!("A".equals(tExpDtos.getEstado()) || "I".equals(tExpDtos.getEstado()))) {
			logger.error("Insercion Fallida. Valor no valido para el campo Estado {}. ", tExpDtos.getEstado());
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Insercion Fallida. Valor no valido para el campo Estado {}. "+ tExpDtos.getEstado()), HttpStatus.CONFLICT);
		}
		service.guardarTiposExpedDtos(tExpDtos);
		return new ResponseEntity<TiposExpedDtos>(tExpDtos, HttpStatus.CREATED);	
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> listarTiposExped(){
		List<TiposExpedDtos> texps = service.listarTodos();
		if(texps.isEmpty()) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No se encontraron registros ") ,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<TiposExpedDtos>>(texps,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getTipoExpedById(@PathVariable("id") Integer id ){
		logger.info("Vamos obtener el TipoExped con id {}.", id);
		TiposExpedDtos tExp = service.getById(id);
		if(tExp == null) {
			logger.error("No se encontro ningun Usuario con id {}.",id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No se encontro ningun TipoExpedDtos con id " + id), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TiposExpedDtos>(tExp, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarTipoExped(@RequestBody TiposExpedDtos tExp, @PathVariable("id") Integer id){
		logger.info("Actualizando el tipoExpedDtos con id {}", id);
		TiposExpedDtos tExpBD = service.getById(id);
		if(tExpBD == null) {
			logger.error("Actualizacion Fallida. No existe el TiposExpedDtos con id {}.", id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. No existe el TipoExped con id " +id), HttpStatus.NOT_FOUND);
		}
		if(!("A".equals(tExp.getEstado()) || "I".equals(tExp.getEstado()))) {
			logger.error("Actualizacion Fallida. Valor no valido para el campo Estado {}. ", tExp.getEstado());
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. Valor no valido para el campo Estado {}. "+ tExp.getEstado()), HttpStatus.CONFLICT);
		}
		tExpBD.setEstado(tExp.getEstado());
		service.guardarTiposExpedDtos(tExpBD);
		return new ResponseEntity<TiposExpedDtos>(tExpBD, HttpStatus.OK);
		
		
	}
	
	
}
