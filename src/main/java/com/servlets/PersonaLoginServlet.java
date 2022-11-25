/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.PersonaJpaController;
import com.dao.TipoPersonaJpaController;
import com.dto.Persona;
import com.dto.TipoPersona;
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
@WebServlet(name = "PersonaLoginServlet", urlPatterns = {"/PersonaLoginServlet"})
public class PersonaLoginServlet extends HttpServlet {

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

    System.out.println("Entrando a Persona Login Servlet");
    try {
      PersonaJpaController jpac_obj_persona = new PersonaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      TipoPersonaJpaController jpac_obj_TdP = new TipoPersonaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      List<Persona> mi_lista_de_personas = new ArrayList<>();
      List<TipoPersona> mi_lista_de_TdP = new ArrayList<>();
      int banderaLogin = 0;

//      System.out.println(jpacontroller_object.findDistritoEntities());
      mi_lista_de_personas = jpac_obj_persona.findPersonaEntities();
      mi_lista_de_TdP = jpac_obj_TdP.findTipoPersonaEntities();
      for (Persona per : mi_lista_de_personas) {
        System.out.println(per.getId() + " - " + per.getNombres() + " - " + per.getEmail() + " - " + per.getPassword() + " - " + per.getTipoPersonaId().getDescripcion());
        if (per.getEmail().equals(request.getParameter("loginEmail")) && per.getPassword().equals(request.getParameter("loginPassword"))) {
          banderaLogin = 1;
        }
      }
      if (banderaLogin == 1) {
        response.sendRedirect("index.jsp");
      } else {
        response.sendRedirect("auth/login.jsp");
      }
    } catch (IOException theException) {
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
