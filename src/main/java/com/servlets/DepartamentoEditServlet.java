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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "DepartamentoEditServlet", urlPatterns = {"/DepartamentoEditServlet"})
public class DepartamentoEditServlet extends HttpServlet {

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
    System.out.println("Entrando a Departamento Edit Servlet");
    System.out.println(request.getParameter("edit_depa_id"));
    System.out.println(request.getParameter("edit_depa_descripcion"));
    System.out.println(request.getParameter("edit_depa_estado"));
    try {
//      Departamento nuevo_objeto = new Departamento();
      Departamento viejo_objeto_departamento;
      DepartamentoJpaController jpacontroller_object_departamento = new DepartamentoJpaController();
      
//      Lo relacionado a la fecha
      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);
      
      
      //Por alguna razón ya no lo necesita
//      Necesitamos una lista de los Distritos
//      DistritoJpaController lista_de_Distritos = new DistritoJpaController();
//      List<Distrito> mi_lista_de_Distritos = new ArrayList<>();
//      mi_lista_de_Distritos = lista_de_Distritos.findDistritoEntities();
      
    //  Ahora necesitamos obtener el objeto a editar para chancar los nuevos valores encima
      viejo_objeto_departamento = jpacontroller_object_departamento.findDepartamento(Long.valueOf(request.getParameter("edit_depa_id")));
      System.out.println("El depa obtenido es: " + viejo_objeto_departamento);
      
//      Comparando y asignando nuevos valores al departamento
      if(!viejo_objeto_departamento.getDescripcion().equals(request.getParameter("edit_depa_descripcion"))) {
        viejo_objeto_departamento.setDescripcion(request.getParameter("edit_depa_descripcion"));
      }
      if(!viejo_objeto_departamento.getEstado().equals(request.getParameter("edit_depa_estado"))){
        viejo_objeto_departamento.setEstado(request.getParameter("edit_depa_estado"));
      }
      

//      nuevo_objeto.setId(viejo_objeto.getId());
//      nuevo_objeto.setDepartamento("AQP");
//      nuevo_objeto.setEstado("INACTIVE");
      viejo_objeto_departamento.setUpdatedAt(ts);
      
      //Por alguna razón ya no lo necesita
    //  viejo_objeto_departamento.setDistritoCollection(mi_lista_de_Distritos);
      
      System.out.println("El depa actualizado es: "
          + viejo_objeto_departamento.getId() +" - "
          + viejo_objeto_departamento.getDescripcion() +" - "
          + viejo_objeto_departamento.getEstado() +" - "
          + viejo_objeto_departamento.getCreatedAt() +" - "
          + viejo_objeto_departamento.getUpdatedAt() +" - "
          + viejo_objeto_departamento.getDistritoCollection());
      
      jpacontroller_object_departamento.edit(viejo_objeto_departamento);
      
      DepartamentoListServlet call = new DepartamentoListServlet();
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
