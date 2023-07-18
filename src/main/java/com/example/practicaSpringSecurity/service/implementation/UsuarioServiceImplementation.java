package com.example.practicaSpringSecurity.service.implementation;

import com.example.practicaSpringSecurity.model.Usuario;
import com.example.practicaSpringSecurity.repository.UsersRepository;
import com.example.practicaSpringSecurity.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class UsuarioServiceImplementation implements UsuarioService {

    private final UsersRepository usersRepository;

    public UsuarioServiceImplementation(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        List<Usuario> allUsuarios = null;
        try {
            log.info("Se acaba de iniciar la operacion getAllUsuarios");
            allUsuarios = usersRepository.findAll();
            if(allUsuarios.isEmpty()){
                log.error("No se encontro ni un solo usuario, crea uno");
                return null;
            }
            log.info("Terminando el listado de todos los usuarios");
            return allUsuarios;

        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
        return allUsuarios;
    }

    @Override
    public Usuario getUsuarioById(Integer id){
        Usuario particularUsuario = null;
        try{
            log.info("Empezando getUsuarioById");
            particularUsuario = usersRepository.getReferenceById(id);
            if(particularUsuario.getId() == id){
                log.error("No se encontro ningun usuario por ese ID mencionado");
                return null;
            }
            log.info("Terminando getUsuarioById");
            return particularUsuario;

        }catch (Exception exception){
            log.error(exception.getMessage());
        }
        return particularUsuario;
    }

    @Override
    public Usuario createUsuario(Usuario usuario){
        Usuario createUsuario = null;
        try{
            log.info("Empezando la creacion del usuario");
            if(!StringUtils.hasText(usuario.getName())){
                log.info("No creo ningun usuario");
                return null;
            }
            createUsuario = usersRepository.save(usuario);
            log.info("Terminando de crear el createUsuario");
            return createUsuario;

        }catch (Exception exception){
            log.error(exception.getMessage());
        }
        return createUsuario;
    }

    @Override
    public Usuario updateUsuario (Usuario usuario){
        Usuario updateUsuario = null;
        try{
            log.info("Empezando el updateUsuario para actualizar su informacion");
            if(!StringUtils.hasText(usuario.getName()) || usuario.getCorreo() == null || usuario.getLastName() == null){
                log.error("Falto algun dato para actualizar al usuario");
                return null;
            }
            updateUsuario = usersRepository.save(usuario);
            log.info("Terminando de realizar la actualizacion del usuario");
            return updateUsuario;

        }catch (Exception exception){
            log.error(exception.getMessage());
        }
        return updateUsuario;
    }

    @Override
    public void deleteUsuarioById(Integer id){
        log.info("Empezando la eliminacion del usuario por el id");
        usersRepository.deleteById(id);
        log.info("Se logro eliminar el usuario con exito");
    }


}
