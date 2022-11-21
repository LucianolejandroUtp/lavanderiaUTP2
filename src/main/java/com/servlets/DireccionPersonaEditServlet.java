/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.DireccionJpaController;
import com.dao.DireccionPersonaJpaController;
import com.dao.PersonaJpaController;
import com.dao.PrendaJpaController;
import com.dao.TipoDePrendaJpaController;
import com.dto.Direccion;
import com.dto.DireccionPersona;
import com.dto.Persona;
import com.dto.Prenda;
import com.dto.TipoDePrenda;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
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
@WebServlet(name = "DireccionPersonaEditServlet", urlPatterns = {"/DireccionPersonaEditServlet"})
public class DireccionPersonaEditServlet extends HttpServlet {

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

    System.out.println("Entrando a DireccionPersona Edit Servlet");
    System.out.println(request.getParameter("editId"));
    System.out.println(request.getParameter("editEstado"));
    System.out.println(request.getParameter("editDireccionId"));
    System.out.println(request.getParameter("editPersonaId"));
    try {
//      Inicialización de objetos
      DireccionPersonaJpaController jpac_obj_dirPer = new DireccionPersonaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      DireccionJpaController jpac_obj_direccion = new DireccionJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      PersonaJpaController jpac_obj_persona = new PersonaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      DireccionPersona oldObject_dirPer;
      Direccion mi_direccion;
      Persona mi_persona;

//      Lo relacionado a la fecha
      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);

      mi_direccion = jpac_obj_direccion.findDireccion(Long.valueOf(request.getParameter("editDireccionId")));
      mi_persona = jpac_obj_persona.findPersona(Long.valueOf(request.getParameter("editPersonaId")));

      //  Ahora necesitamos obtener el objeto a editar para chancar los nuevos valores encima
      oldObject_dirPer = jpac_obj_dirPer.findDireccionPersona(Long.valueOf(request.getParameter("editId")));
      System.out.println("La DireccionPersona obtenida es: " + oldObject_dirPer);

      if (!oldObject_dirPer.getEstado().equals(request.getParameter("editEstado"))) {
        oldObject_dirPer.setEstado(request.getParameter("editEstado"));
      }

      if (!oldObject_dirPer.getDireccionId().equals(mi_direccion)) {
        oldObject_dirPer.setDireccionId(mi_direccion);
      }
      if (!oldObject_dirPer.getPersonaId().equals(mi_persona)) {
        oldObject_dirPer.setPersonaId(mi_persona);
      }
      oldObject_dirPer.setUpdatedAt(ts);
//      oldObject_distrito.setDireccionCollection(mi_lista_de_Direcciones);

      System.out.println("La prenda actualizada es: "
          + oldObject_dirPer.getId() +" - "+ oldObject_dirPer.getUniqueId() + " - "+ oldObject_dirPer.getEstado() + " - "
          + oldObject_dirPer.getDireccionId().getDescripcion() +" - "
          + oldObject_dirPer.getPersonaId().getNombres()+" - "
          + oldObject_dirPer.getCreatedAt() + " - " + oldObject_dirPer.getUpdatedAt());

      jpac_obj_dirPer.edit(oldObject_dirPer);

      DireccionPersonaListServlet call = new DireccionPersonaListServlet();
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
