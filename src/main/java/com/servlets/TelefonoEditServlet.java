/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.CategoriaJpaController;
import com.dao.PersonaJpaController;
import com.dao.ServicioJpaController;
import com.dao.TelefonoJpaController;
import com.dto.Categoria;
import com.dto.Persona;
import com.dto.Servicio;
import com.dto.Telefono;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
@WebServlet(name = "TelefonoEditServlet", urlPatterns = {"/TelefonoEditServlet"})
public class TelefonoEditServlet extends HttpServlet {

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
    
    System.out.println("Entrando a Servicio Edit Servlet");
    System.out.println(request.getParameter("editId"));
    System.out.println(request.getParameter("editDescripcion"));
    System.out.println(request.getParameter("editPersonaId"));
    try {
//      Inicialización de objetos
      TelefonoJpaController jpac_obj_telefono = new TelefonoJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      PersonaJpaController jpac_obj_persona = new PersonaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      Telefono oldObject_telefono;
      Persona persona;

//      Lo relacionado a la fecha
      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);

//      Necesitamos una lista de los Direcciones
//      DireccionJpaController jpac_xa_lista_de_Direcciones = new DireccionJpaController();
//      List<Direccion> mi_lista_de_Direcciones = new ArrayList<>();
//      mi_lista_de_Direcciones = jpac_xa_lista_de_Direcciones.findDireccionEntities();

//      Obteniendo el objeto con foreign key en base al Id que nos da la vista
      persona = jpac_obj_persona.findPersona(Long.valueOf(request.getParameter("editPersonaId")));

      //  Ahora necesitamos obtener el objeto a editar para chancar los nuevos valores encima
      oldObject_telefono = jpac_obj_telefono.findTelefono(Long.valueOf(request.getParameter("editId")));
      System.out.println("El Teléfono obtenido es: " + oldObject_telefono);

//      Comparando y asignando nuevos valores al objeto
      if (!oldObject_telefono.getDescripcion().equals(request.getParameter("editDescripcion"))) {
        oldObject_telefono.setDescripcion(request.getParameter("editDescripcion"));
      }
      if (!oldObject_telefono.getEstado().equals(request.getParameter("editEstado"))) {
        oldObject_telefono.setEstado(request.getParameter("editEstado"));
      }
      if (!oldObject_telefono.getPersonaId().equals(persona)) {
        oldObject_telefono.setPersonaId(persona);
      }
      oldObject_telefono.setUpdatedAt(ts);
//      oldObject_distrito.setDireccionCollection(mi_lista_de_Direcciones);

      System.out.println("El Teléfono actualizado es: "
          + oldObject_telefono.getId() + " - " + oldObject_telefono.getDescripcion() +" - "
          + oldObject_telefono.getEstado() + " - " + oldObject_telefono.getPersonaId().getNombres() +" - "
          + oldObject_telefono.getCreatedAt() + " - " + oldObject_telefono.getUpdatedAt());

      jpac_obj_telefono.edit(oldObject_telefono);

      TelefonoListServlet call = new TelefonoListServlet();
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
