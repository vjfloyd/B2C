package com.upc.b2c.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upc.b2c.model.Usuario;


@Controller
public class UsuarioController {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	Map<Integer, Usuario> listaUser = new HashMap<Integer, Usuario>();
	
	   @RequestMapping(value = UsuarioURIConstants.DUMMY_USER, method = RequestMethod.GET)
	   public @ResponseBody Usuario getDummyUsuario() {
	        logger.info("Start getDummyEmployee");
	        Usuario user = new Usuario();
	        user.setIdUsuario(777);
	        user.setNombre("vjfloyd");
	        user.setTelefono("1010");
	        listaUser.put(777, user);
	        return user;
	    }
	   @RequestMapping(value = UsuarioURIConstants.GET_USER, method = RequestMethod.GET)
	    public @ResponseBody Usuario getUsuario(@PathVariable("idUsuario") int id) {
	        logger.info("Start getUsuario. ID="+id);
	         
	        return listaUser.get(id);
	    }
	 
	   @RequestMapping(value = UsuarioURIConstants.GET_ALL_USER, method = RequestMethod.GET)
	   public @ResponseBody List<Usuario> getAllUsuarios() {
	        logger.info("Start getAllUsers.");
	        List<Usuario> users = new ArrayList<Usuario>();
	        Set<Integer> userIdKeys = listaUser.keySet();
	        for(Integer i : userIdKeys){
	        	users.add(listaUser.get(i));
	        }
	        return users;
	    }
	 
	   @RequestMapping(value = UsuarioURIConstants.CREATE_USER, method = RequestMethod.POST)
	   public @ResponseBody Usuario createUsuario(@RequestBody Usuario u) {
	        logger.info("Start createUser.");
	        
	        listaUser.put(u.getIdUsuario(), u);
	        return u;
	    }
	 
	   @RequestMapping(value = UsuarioURIConstants.DELETE_USER, method = RequestMethod.PUT)
	   public @ResponseBody Usuario deleteEmployee(@PathVariable("idUsuario") int empId) {
	        logger.info("Start deleteUsuario.");
	        Usuario u = listaUser.get(empId);
	        listaUser.remove(empId);
	        return u;
	    }
	 	 
}
