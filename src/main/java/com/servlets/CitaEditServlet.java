/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.CitaJpaController;
import com.dao.PersonaJpaController;
import com.dao.PrendaJpaController;
import com.dao.TipoDePrendaJpaController;
import com.dao.VehiculoJpaController;
import com.dto.Cita;
import com.dto.Persona;
import com.dto.Prenda;
import com.dto.TipoDePrenda;
import com.dto.Vehiculo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author desti
 */
@WebServlet(name = "CitaEditServlet", urlPatterns = {"/CitaEditServlet"})
public class CitaEditServlet extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");

    System.out.println("Entrando a Cita Edit Servlet");
    System.out.println(request.getParameter("editId"));
    System.out.println(request.getParameter("editFecha"));
    System.out.println(request.getParameter("editHora"));
    System.out.println(request.getParameter("editPlacaId"));
    System.out.println(request.getParameter("editNombreId"));
    System.out.println(request.getParameter("editEstado"));
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU");
    try {
//      Inicialización de objetos
      CitaJpaController jpac_obj_cita = new CitaJpaController(emf);
      VehiculoJpaController jpac_obj_vehiculo = new VehiculoJpaController(emf);
      PersonaJpaController jpac_obj_persona = new PersonaJpaController(emf);
      Cita oldObject_cita;
      Vehiculo mi_vehiculo;
      Persona mi_persona;

//      Lo relacionado a la fecha
      SimpleDateFormat sdf_fecha = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat sdf_hora = new SimpleDateFormat("HH:mm");
      
      Date fecha = sdf_fecha.parse(String.valueOf(request.getParameter("editFecha")));
      Date hora = sdf_hora.parse(String.valueOf(request.getParameter("editHora")));
      
      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);

//      Necesitamos una lista de los Direcciones
//      DireccionJpaController jpac_xa_lista_de_Direcciones = new DireccionJpaController();
//      List<Direccion> mi_lista_de_Direcciones = new ArrayList<>();
//      mi_lista_de_Direcciones = jpac_xa_lista_de_Direcciones.findDireccionEntities();
//      Obteniendo el objeto con foreign key en base al Id que nos da la vista
      mi_vehiculo = jpac_obj_vehiculo.findVehiculo(Long.valueOf(request.getParameter("editPlacaId")));
      mi_persona = jpac_obj_persona.findPersona(Long.valueOf(request.getParameter("editNombreId")));

      //  Ahora necesitamos obtener el objeto a editar para chancar los nuevos valores encima
      oldObject_cita = jpac_obj_cita.findCita(Long.valueOf(request.getParameter("editId")));
      System.out.println("La Cita obtenida es: " + oldObject_cita);

//      Comparando y asignando nuevos valores al objeto
      if (oldObject_cita.getFecha() == null || oldObject_cita.getFecha().compareTo(fecha) != 0) {
        oldObject_cita.setFecha(fecha);
      }
      if (oldObject_cita.getHora() == null || oldObject_cita.getHora().compareTo(hora) != 0) {
        oldObject_cita.setHora(hora);
      }
      if (!oldObject_cita.getEstado().equals(request.getParameter("editEstado"))) {
        oldObject_cita.setEstado(request.getParameter("editEstado"));
      }

      if (!oldObject_cita.getVehiculoId().equals(mi_vehiculo)) {
        oldObject_cita.setVehiculoId(mi_vehiculo);
      }
      if (!oldObject_cita.getPersonaId().equals(mi_persona)) {
        oldObject_cita.setPersonaId(mi_persona);
      }
      oldObject_cita.setUpdatedAt(ts);
//      oldObject_distrito.setDireccionCollection(mi_lista_de_Direcciones);

      System.out.println("La Cita actualizada es: "
          + oldObject_cita.getId() + " - " + oldObject_cita.getFecha()+ " - "
          + oldObject_cita.getEstado() + " - " + oldObject_cita.getPersonaId().getNombres()+ " - "
          + oldObject_cita.getCreatedAt() + " - " + oldObject_cita.getUpdatedAt());

      jpac_obj_cita.edit(oldObject_cita);

      PrendaListServlet call = new PrendaListServlet();
      call.processRequest(request, response);

    } catch (Exception theException) {
      System.out.println(theException);
    }
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
