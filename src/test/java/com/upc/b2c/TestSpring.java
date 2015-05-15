package com.upc.b2c;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.upc.b2c.controller.UsuarioURIConstants;
import com.upc.b2c.model.Usuario;

public class TestSpring {

	public static final String SERVER_URI = "http://localhost:8080/b2c";
    
	public static void main(String args[]){
        
		System.out.println("*****Dummy");
        testGetDummyUsuario();
        System.out.println("*****Crear Usuario");
        testCreateUsuario();
        System.out.println("*****Get all usuarios");
        testGetAllUsuario();
        System.out.println("*****Get Usuario");
        testGetUsuario();
        
    }
	
	private static void testGetAllUsuario() {
        RestTemplate restTemplate = new RestTemplate();
        //we can't get List<Employee> because JSON convertor doesn't know the type of
        //object in the list and hence convert it to default JSON object type LinkedHashMap
        List<LinkedHashMap> lista = restTemplate.getForObject(SERVER_URI+UsuarioURIConstants.GET_ALL_USER, List.class);
        System.out.println(lista.size());
        for(LinkedHashMap map : lista){
            System.out.println("ID="+map.get("idUsuario")+",Name="+map.get("nombre")+",CreatedDate=");
        }
    }
	
	private static void testCreateUsuario() {
        RestTemplate restTemplate = new RestTemplate();
        Usuario u = new Usuario();
        u.setIdUsuario(1);
        u.setNombre("Vj Floyd");
        Usuario response = restTemplate.postForObject(SERVER_URI+UsuarioURIConstants.CREATE_USER, u, Usuario.class);
        printEmpData(response);
    }
	
	private static void testGetUsuario() {
        RestTemplate restTemplate = new RestTemplate();
        Usuario u = restTemplate.getForObject(SERVER_URI+ "/user/1", Usuario.class);
        printEmpData(u);
    }
	
	private static void testGetDummyUsuario() {
        RestTemplate restTemplate = new RestTemplate();
        Usuario u = restTemplate.getForObject(SERVER_URI+UsuarioURIConstants.DUMMY_USER, Usuario.class);
        printEmpData(u);
    }
	
	public static void printEmpData(Usuario u){
        System.out.println("ID="+u.getIdUsuario()+",Name="+u.getNombre());
    }
	
	
}
