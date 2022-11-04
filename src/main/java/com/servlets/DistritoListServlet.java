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
      DistritoJpaController jpacontroller_object = new DistritoJpaController();
      DepartamentoJpaController jpacontroller_object2 = new DepartamentoJpaController();
      List<Distrito> mi_lista_de_objetos = new ArrayList<>();
      List<Departamento> mi_lista_de_objetos2 = new ArrayList<>();

//      System.out.println(jpacontroller_object.findDistritoEntities());
      mi_lista_de_objetos = jpacontroller_object.findDistritoEntities();
      mi_lista_de_objetos2 = jpacontroller_object2.findDepartamentoEntities();

      for (Distrito dis : mi_lista_de_objetos) {
        System.out.println(dis.getId() + " - " + dis.getDistrito()+ " - " + dis.getDepartamentoId().getDepartamento());
      }
      for (Departamento dep : mi_lista_de_objetos2) {
        System.out.println(dep.getId() + " - " + dep.getDepartamento());
      }

      
      request.setAttribute("mi_lista_de_objetos", mi_lista_de_objetos);
      request.setAttribute("mi_lista_de_objetos2", mi_lista_de_objetos2);
      request.getRequestDispatcher("listDistrito.jsp").forward(request, response);

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
