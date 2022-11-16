/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.DepartamentoJpaController;
import com.dao.DistritoJpaController;
import com.dto.Departamento;
import com.dto.Distrito;
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
@WebServlet(name = "DistritoListServlet", urlPatterns = {"/DistritoListServlet"})
public class DistritoListServlet extends HttpServlet {

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
    System.out.println("Entrando al List Servlet");
    try {
      DistritoJpaController jpac_object_distrito = new DistritoJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      DepartamentoJpaController jpac_object_departamento = new DepartamentoJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      List<Distrito> mi_lista_de_distritos = new ArrayList<>();
      List<Departamento> mi_lista_de_depas = new ArrayList<>();

//      System.out.println(jpacontroller_object.findDistritoEntities());
      mi_lista_de_distritos = jpac_object_distrito.findDistritoEntities();
      mi_lista_de_depas = jpac_object_departamento.findDepartamentoEntities();

      for (Distrito dis : mi_lista_de_distritos) {
        System.out.println(dis.getId() + " - " + dis.getDescripcion()+ " - " + dis.getDepartamentoId().getDescripcion());
      }
      for (Departamento dep : mi_lista_de_depas) {
        System.out.println(dep.getId() + " - " + dep.getDescripcion());
      }

      
      request.setAttribute("mi_lista_de_distritos", mi_lista_de_distritos);
      request.setAttribute("mi_lista_de_departamentos", mi_lista_de_depas);
      request.getRequestDispatcher("Distrito.jsp").forward(request, response);

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
