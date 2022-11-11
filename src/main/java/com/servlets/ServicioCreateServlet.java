/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.CategoriaJpaController;
import com.dao.DepartamentoJpaController;
import com.dao.DistritoJpaController;
import com.dao.ServicioJpaController;
import com.dto.Categoria;
import com.dto.Departamento;
import com.dto.Distrito;
import com.dto.Servicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author desti
 */
@WebServlet(name = "ServicioCreateServlet", urlPatterns = {"/ServicioCreateServlet"})
public class ServicioCreateServlet extends HttpServlet {

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
    
    System.out.println("Bandera servlet create Servicio");
    try {
      ServicioJpaController jpac_object_servicio = new ServicioJpaController();
      CategoriaJpaController jpac_object_categoria = new CategoriaJpaController();
      Servicio mi_objeto_servicio = new Servicio();
      Categoria mi_objeto_categoria = new Categoria();

      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);
      
//      Obteniendo el departamento en base al Id obtenido de la vista
      mi_objeto_categoria = jpac_object_categoria.findCategoria(Long.valueOf(request.getParameter("addCategoriaId")));
      System.out.println("La Categoria obtenida fue: " + mi_objeto_categoria.getDescripcion() +" - "+ mi_objeto_categoria.getId());

//      Llenando los parámetros del distrito obtenidos de la vista
//            mi_distrito.setIdTelefono(566);                        //No necesario, tiene auto_increment
      mi_objeto_servicio.setDescripcion(request.getParameter("addDescripcion"));
      mi_objeto_servicio.setDetalles(request.getParameter("addDetalles"));
      mi_objeto_servicio.setPrecio(Double.valueOf(request.getParameter("addPrecio")));
      mi_objeto_servicio.setEstado("activo");
      mi_objeto_servicio.setCategoriaId(mi_objeto_categoria);
      mi_objeto_servicio.setCreatedAt(ts);
      mi_objeto_servicio.setUpdatedAt(ts);

//      Llamando al método crear del controlador y pasándole el objeto Distrito
      jpac_object_servicio.create(mi_objeto_servicio);

//      Llamando al listALGO.jsp
      ServicioListServlet call = new ServicioListServlet();
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
