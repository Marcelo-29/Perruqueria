package com.mycompany.peluqueriacanina.persistencia;

import com.mycompany.peluqueriacanina.logica.Duenio;
import com.mycompany.peluqueriacanina.logica.Mascota;
import com.mycompany.peluqueriacanina.logica.Usuario;
import com.mycompany.peluqueriacanina.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorPersistencia {
  DuenioJpaController duenioJpa = new DuenioJpaController();
  MascotaJpaController mascotaJpa = new MascotaJpaController();
  UsuarioJpaController usuarioJpa = new UsuarioJpaController();

    public void guardar(Duenio duenio, Mascota mascota) {
      duenioJpa.create(duenio);
      mascotaJpa.create(mascota);
    }

    public List<Mascota> traerMascotas() {
        return mascotaJpa.findMascotasEntities();
    }

    public void borrarMascota(int numCliente) {
      try {
          mascotaJpa.destroy(numCliente);
      } catch (NonexistentEntityException ex) {
          Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    public Mascota traerMascota(int numCliente) {
        return mascotaJpa.findMascotas(numCliente);
    }

    public void modificarMascota(Mascota mascota) {
      try {
          mascotaJpa.edit(mascota);
      } catch (Exception ex) {
          Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    public Duenio traerDuenio(int idDuenio) {
        return duenioJpa.findDuenio(idDuenio);
    }

    public void modificarDuenio(Duenio duenio) {
      try {
          duenioJpa.edit(duenio);
      } catch (Exception ex) {
          Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    public List<Usuario> traerUsuarios() {
        return usuarioJpa.findUsuarioEntities();
    }
}
