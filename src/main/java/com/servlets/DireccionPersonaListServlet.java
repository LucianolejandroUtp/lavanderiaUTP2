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
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "DireccionPersonaListServlet", urlPatterns = {"/DireccionPersonaListServlet"})
public class DireccionPersonaListServlet extends HttpServlet {

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
    
    
    System.out.println("Entrando a DireccionPersona List Servlet");
    try {
      DireccionPersonaJpaController jpac_obj_dirPer = new DireccionPersonaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      DireccionJpaController jpac_obj_direccion = new DireccionJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      PersonaJpaController jpac_obj_persona = new PersonaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      List<DireccionPersona> mi_lista_de_dirPer = new ArrayList<>();
      List<Direccion> mi_lista_de_direcciones = new ArrayList<>();
      List<Persona> mi_lista_de_personas = new ArrayList<>();

//      System.out.println(jpacontroller_object.findDistritoEntities());
      mi_lista_de_dirPer = jpac_obj_dirPer.findDireccionPersonaEntities();
      mi_lista_de_direcciones = jpac_obj_direccion.findDireccionEntities();
      mi_lista_de_personas =jpac_obj_persona.findPersonaEntities();

      for (DireccionPersona temp1 : mi_lista_de_dirPer) {
        System.out.println(temp1.getId() +" - "+ temp1.getUniqueId());
      }
      for (Direccion temp2 : mi_lista_de_direcciones) {
        System.out.println(temp2.getId() + " - " + temp2.getDescripcion());
      }
      for (Persona temp3 : mi_lista_de_personas) {
        System.out.println(temp3.getId() + " - " + temp3.getNombres());
      }

      request.setAttribute("mi_lista_de_dirPer", mi_lista_de_dirPer);
      request.setAttribute("mi_lista_de_direcciones", mi_lista_de_direcciones);
      request.setAttribute("mi_lista_de_personas", mi_lista_de_personas);
      request.getRequestDispatcher("DireccionPersona.jsp").forward(request, response);

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
