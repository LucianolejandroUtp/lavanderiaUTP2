/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.CategoriaJpaController;
import com.dao.DepartamentoJpaController;
import com.dao.PrendaJpaController;
import com.dto.Categoria;
import com.dto.Departamento;
import com.dto.Prenda;
import com.dto.TipoDePrenda;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author desti
 */
@WebServlet(name = "EjemplosServlet", urlPatterns = {"/EjemplosServlet"})
public class EjemplosServlet extends HttpServlet {

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

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU");

    DepartamentoJpaController jpaDepa = new DepartamentoJpaController(emf);
    CategoriaJpaController nn = new CategoriaJpaController(emf);
    PrendaJpaController prendajpaC = new PrendaJpaController(emf);
    
    List<Departamento> mi_lista_de_depas = new ArrayList<>();
    List<Categoria> mi_lista_de_categorias = new ArrayList<>();
    List<Prenda> mi_lista_de_prendas = new ArrayList<>();

    mi_lista_de_depas = jpaDepa.findDepartamentoEntities();
    mi_lista_de_categorias = nn.findCategoriaEntities();
    mi_lista_de_prendas = prendajpaC.findPrendaEntities();

    for (Departamento temp2 : mi_lista_de_depas) {
      System.out.println(temp2.getId() + " - " + temp2.getDescripcion());
    }
    for (Categoria temp3 : mi_lista_de_categorias) {
      System.out.println(temp3.getId() + " - " + temp3.getDescripcion());
    }
    for (Prenda temp4 : mi_lista_de_prendas) {
      System.out.println(temp4.getId() + " - " + temp4.getServicioId().getCategoriaId().getDescripcion());
    }
    
    HttpSession miSesion = request.getSession();
    
    System.out.println(request.getSession());
    
    
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
