/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dto.Persona;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "NivelUsuarioServlet", urlPatterns = {"/NivelUsuarioServlet"})
public class NivelUsuarioServlet extends HttpServlet {

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

    System.out.println("Entrando a NivelUsuarioServlet");

    Persona miPersonaObtenida = new Persona();
    HttpSession sesion = request.getSession();

    miPersonaObtenida = (Persona)sesion.getAttribute("miPersonaObtenida");
    System.out.println("Persona: "+miPersonaObtenida.getNombres()+miPersonaObtenida.getTipoPersonaId().getDescripcion());
    
//    if(miPersonaObtenida.getTipoPersonaId().getId() == 1){
//      
//        System.out.println("La persona obtenida es un Admin");
//        request.getRequestDispatcher("auth/login.jsp").forward(request, response);
////        response.sendRedirect("auth/login.jsp");
//    }
//    
    switch (miPersonaObtenida.getTipoPersonaId().getDescripcion()) {
      case "Administrador":
        System.out.println("La persona obtenida es un Admin");
        request.getRequestDispatcher("userLevel/sidebarAdmin.jsp").forward(request, response);
        break;
      case "Cliente":
        System.out.println("La persona obtenida es un Cliente");
        request.getRequestDispatcher("userLevel/sidebarCliente.jsp").forward(request, response);
        break;
      default:
        System.out.println("Algo salió mal...");
        request.getRequestDispatcher("auth/login.jsp").forward(request, response);
        throw new AssertionError();
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
