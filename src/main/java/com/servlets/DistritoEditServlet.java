/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.DepartamentoJpaController;
import com.dao.DireccionJpaController;
import com.dao.DistritoJpaController;
import com.dto.Departamento;
import com.dto.Direccion;
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
@WebServlet(name = "DistritoEditServlet", urlPatterns = {"/DistritoEditServlet"})
public class DistritoEditServlet extends HttpServlet {

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

    System.out.println("Entrando a Distrito Edit Servlet");
    System.out.println(request.getParameter("edit_distrito_id"));
    System.out.println(request.getParameter("edit_distrito_descripcion"));
    System.out.println(request.getParameter("edit_distrito_estado"));
    System.out.println(request.getParameter("edit_distrito_depaId"));
    try {
//      Lo relacionado a la fecha
      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);

//      Inicialización de objetos
      Distrito oldObject_distrito;
      Departamento depa;
      DistritoJpaController jpac_object_distrito = new DistritoJpaController();
      DepartamentoJpaController jpac_object_depa = new DepartamentoJpaController();

//      Necesitamos una lista de los Direcciones
//      DireccionJpaController jpac_xa_lista_de_Direcciones = new DireccionJpaController();
//      List<Direccion> mi_lista_de_Direcciones = new ArrayList<>();
//      mi_lista_de_Direcciones = jpac_xa_lista_de_Direcciones.findDireccionEntities();

//      Obteniendo el departamento en base al Id que nos da la vista
      depa = jpac_object_depa.findDepartamento(Long.valueOf(request.getParameter("edit_distrito_depaId")));
      
      //  Ahora necesitamos obtener el objeto a editar para chancar los nuevos valores encima
      oldObject_distrito = jpac_object_distrito.findDistrito(Long.valueOf(request.getParameter("edit_distrito_id")));
      System.out.println("El depa obtenido es: " + oldObject_distrito);

//      Comparando y asignando nuevos valores al Distrito
      if (!oldObject_distrito.getDescripcion().equals(request.getParameter("edit_distrito_descripcion"))) {
        oldObject_distrito.setDescripcion(request.getParameter("edit_distrito_descripcion"));
      }
      if (!oldObject_distrito.getEstado().equals(request.getParameter("edit_distrito_estado"))) {
        oldObject_distrito.setEstado(request.getParameter("edit_distrito_estado"));
      }
      if(!oldObject_distrito.getDepartamentoId().equals(depa)){
      oldObject_distrito.setDepartamentoId(depa);
      }
      oldObject_distrito.setUpdatedAt(ts);
//      oldObject_distrito.setDireccionCollection(mi_lista_de_Direcciones);

      System.out.println("El Distrito actualizado es: "
          + oldObject_distrito.getId() +" - "+ oldObject_distrito.getDescripcion() +" - "
          + oldObject_distrito.getEstado() +" - "+ oldObject_distrito.getDepartamentoId().getDescripcion()+" - "
          + oldObject_distrito.getCreatedAt() +" - "+ oldObject_distrito.getUpdatedAt() +" - "
          + oldObject_distrito.getDireccionCollection());

      jpac_object_distrito.edit(oldObject_distrito);

      DistritoListServlet call = new DistritoListServlet();
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
