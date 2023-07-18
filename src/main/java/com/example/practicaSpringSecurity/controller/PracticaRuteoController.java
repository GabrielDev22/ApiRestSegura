package com.example.practicaSpringSecurity.controller;

import com.example.practicaSpringSecurity.model.Usuario;
import com.example.practicaSpringSecurity.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("test")
public class PracticaRuteoController {

    @Autowired
    private SessionRegistry sessionRegistry;
    private final UsuarioService usuarioService;

    public PracticaRuteoController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("/pruebaOne")
    public String testOne(){
        return "Hello World One";
    }

    @GetMapping("/pruebaTwo")
    public String testTwo(){
        return "Hello World Two";
    }

    @GetMapping("/pruebaThree")
    public String testThree(){
        return "Hola mundo y bienvenidos a la prueba tres de este testeo de spring security";
    }

    @GetMapping("/session")
    public ResponseEntity<?> getDetailsSession(){

        String sessionId = "";
        User userObject = null;

        List<Object> sessions =  sessionRegistry.getAllPrincipals();

        for(Object session : sessions) {
            if (session instanceof User) {
                userObject = (User) session;
            }

            List<SessionInformation> sessionInformations = sessionRegistry.getAllSessions(session, false);

            for (SessionInformation sessionInformation : sessionInformations) {
                sessionId = sessionInformation.getSessionId();
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("response", "Hello World");
        response.put("sessionId" , sessionId);
        response.put("sessionUser", userObject);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAll")
    public List<Usuario> getAllUsuarios(){
        if(usuarioService.getAllUsuarios() == null){
            return null;
        }
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/get/{id}")
    public Usuario getUsuarioById(@PathVariable Integer id){
        if(usuarioService.getUsuarioById(id) == null){
            return null;
        }
        return usuarioService.getUsuarioById(id);
    }

    @PostMapping("/create")
    public Usuario createUsuario(@RequestBody Usuario usuario){
        if(usuarioService.createUsuario(usuario) == null){
            return null;
        }
        return usuarioService.createUsuario(usuario);
    }

    @PutMapping("/update")
    public Usuario updateUsuario(@RequestBody Usuario usuario){
        if(usuarioService.updateUsuario(usuario) == null){
            return null;
        }
        return usuarioService.updateUsuario(usuario);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUsuarioById(@PathVariable Integer id) {
        usuarioService.deleteUsuarioById(id);
    }


}
