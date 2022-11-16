/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.DepartamentoJpaController;
import com.dao.DistritoJpaController;
import com.dao.PersonaJpaController;
import com.dao.TipoPersonaJpaController;
import com.dto.Departamento;
import com.dto.Distrito;
import com.dto.Persona;
import com.dto.TipoPersona;
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
@WebServlet(name = "PersonaCreateServlet", urlPatterns = {"/PersonaCreateServlet"})
public class PersonaCreateServlet extends HttpServlet {

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
    
    System.out.println("Bandera servlet create Persona");
    try {
      PersonaJpaController jpac_object_persona = new PersonaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      TipoPersonaJpaController jpac_object_TdP = new TipoPersonaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      Persona mi_objeto_persona = new Persona();
      TipoPersona mi_objeto_TdP = new TipoPersona();

      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);

      
//      Obteniendo el Tipo dePpersona en base al Id obtenido de la vista
      mi_objeto_TdP = jpac_object_TdP.findTipoPersona(Long.valueOf(request.getParameter("addTdP")));
      System.out.println("El Tipo de Persona obtenido fue: " + mi_objeto_TdP.getDescripcion() +" - "+ mi_objeto_TdP.getId());

//      Llenando los parámetros del distrito obtenidos de la vista
//            mi_distrito.setIdTelefono(566);                        //No necesario, tiene auto_increment
      mi_objeto_persona.setNombres(request.getParameter("addNombres"));
      mi_objeto_persona.setApellidos(request.getParameter("addApellidos"));
      mi_objeto_persona.setDni(request.getParameter("addDni"));
      mi_objeto_persona.setEmail(request.getParameter("addEmail"));
      mi_objeto_persona.setPassword(request.getParameter("addPassword"));
      mi_objeto_persona.setEstado("activo");
      mi_objeto_persona.setTipoPersonaId(mi_objeto_TdP);
      mi_objeto_persona.setCreatedAt(ts);
      mi_objeto_persona.setUpdatedAt(ts);

//      Llamando al método crear del controlador y pasándole el objeto Distrito
      jpac_object_persona.create(mi_objeto_persona);

//      Llamando al listALGO.jsp
      PersonaListServlet call = new PersonaListServlet();
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
