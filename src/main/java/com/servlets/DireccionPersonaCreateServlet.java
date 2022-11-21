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
@WebServlet(name = "DireccionPersonaCreateServlet", urlPatterns = {"/DireccionPersonaCreateServlet"})
public class DireccionPersonaCreateServlet extends HttpServlet {

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
    
    System.out.println("Bandera servlet create DireccionPersona");
    try {
      DireccionPersonaJpaController jpac_obj_dirPer = new DireccionPersonaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      DireccionJpaController jpac_obj_direccion = new DireccionJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      PersonaJpaController jpac_obj_persona = new PersonaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      DireccionPersona mi_objeto_dirPer = new DireccionPersona();
      Direccion mi_objeto_direccion;
      Persona mi_objeto_persona;

      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);
      
//      Obteniendo el objeto en base al Id obtenido de la vista
      mi_objeto_direccion = jpac_obj_direccion.findDireccion(Long.valueOf(request.getParameter("addDireccionId")));
      mi_objeto_persona = jpac_obj_persona.findPersona(Long.valueOf(request.getParameter("addPersonaId")));
      System.out.println("La Dirección obtenida fue: " + mi_objeto_direccion.getDescripcion() +" - "+ mi_objeto_direccion.getId());
      System.out.println("La Persona obtenida fue: " + mi_objeto_persona.getNombres()+" - "+ mi_objeto_persona.getId());

//      Llenando los parámetros del distrito obtenidos de la vista
//            mi_distrito.setIdTelefono(566);                        //No necesario, tiene auto_increment
      mi_objeto_dirPer.setUniqueId(String.valueOf(java.util.UUID.randomUUID()));
      mi_objeto_dirPer.setEstado("activo");
      mi_objeto_dirPer.setCreatedAt(ts);
      mi_objeto_dirPer.setUpdatedAt(ts);
      mi_objeto_dirPer.setDireccionId(mi_objeto_direccion);
      mi_objeto_dirPer.setPersonaId(mi_objeto_persona);

//      Llamando al método crear del controlador y pasándole el objeto Distrito
      jpac_obj_dirPer.create(mi_objeto_dirPer);

//      Llamando al listALGO.jsp
      DireccionPersonaListServlet call = new DireccionPersonaListServlet();
      call.processRequest(request, response);
//      response.sendRedirect("Distrito/List.jsp").forward(request, response);

    } catch (IOException | ServletException theException) {
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
