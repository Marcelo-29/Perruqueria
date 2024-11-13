package com.mycompany.peluqueriacanina.logica;

import com.mycompany.peluqueriacanina.persistencia.ControladorPersistencia;
import java.util.List;

public class Controladora {

    ControladorPersistencia controlPersis;
    public Controladora(){
        controlPersis = new ControladorPersistencia();
    }

    public void guardar(String nombreMasco, String raza, String color, String nomDuenio, String celDuenio, String observaciones, String alergico, String atEspecial) {
        Duenio duenio = new Duenio();

        duenio.setNombre(nomDuenio);
        duenio.setCelDuenio(celDuenio);

        Mascota mascota = new Mascota();

        mascota.setNombre(nombreMasco);
        mascota.setColor(color);
        mascota.setRaza(raza);
        mascota.setAtencionEspecial(atEspecial);
        mascota.setAlergico(alergico);
        mascota.setObservaciones(observaciones);
        mascota.setDuenio(duenio);

        controlPersis.guardar(duenio, mascota);

    }

    public List<Mascota> traerMascotas() {

        return controlPersis.traerMascotas();
    }

    public void borrarMascota(int numCliente) {
        controlPersis.borrarMascota(numCliente);
    }

    public Mascota traerMascota(int numCliente) {
        return controlPersis.traerMascota(numCliente);
    }

    public void modificarMascota(Mascota mascota, String nombreMasco,
            String raza, String color, String observaciones,
            String alergico, String atEspecial, String nomDuenio,
            String celDuenio) {
        
        mascota.setNombre(nombreMasco);
        mascota.setRaza(raza);
        mascota.setColor(color);
        mascota.setObservaciones(observaciones);
        mascota.setAlergico(alergico);
        mascota.setAtencionEspecial(atEspecial);
        
        //modificar mascota
        controlPersis.modificarMascota(mascota);
        
        Duenio duenio = this.bucarDuenio(mascota.getDuenio().getIdDuenio());
        duenio.setCelDuenio(celDuenio);
        duenio.setNombre(nomDuenio);
        
        this.modificarDuenio(duenio);
    }

    private Duenio bucarDuenio(int idDuenio) {
       return controlPersis.traerDuenio(idDuenio);
    }

    private void modificarDuenio(Duenio duenio) {
        controlPersis.modificarDuenio(duenio);
    }

    public boolean validarUsuario(String usuario, String contrasenia) {
        List<Usuario> listaUsuario = controlPersis.traerUsuarios();
        for(Usuario usu: listaUsuario){
            if(usu.getNombre().equals(usuario) && usu.getContrasenia().equals(contrasenia)){
                return true;
            }  
        }
        return false;
    }

}
