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
import java.math.BigDecimal;
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
@WebServlet(name = "ServicioEditServlet", urlPatterns = {"/ServicioEditServlet"})
public class ServicioEditServlet extends HttpServlet {

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
    System.out.println("Entrando a Servicio Edit Servlet");
    System.out.println(request.getParameter("editId"));
    System.out.println(request.getParameter("editDescripcion"));
    System.out.println(request.getParameter("editDetalles"));
    System.out.println(request.getParameter("editPrecio"));
    System.out.println(request.getParameter("editEstado"));
    System.out.println(request.getParameter("editCategoriaId"));
    try {
//      Inicialización de objetos
      ServicioJpaController jpac_object_servicio = new ServicioJpaController();
      CategoriaJpaController jpac_object_categoria = new CategoriaJpaController();
      Servicio oldObject_servicio;
      Categoria categoria;

//      Lo relacionado a la fecha
      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);

//      Necesitamos una lista de los Direcciones
//      DireccionJpaController jpac_xa_lista_de_Direcciones = new DireccionJpaController();
//      List<Direccion> mi_lista_de_Direcciones = new ArrayList<>();
//      mi_lista_de_Direcciones = jpac_xa_lista_de_Direcciones.findDireccionEntities();

//      Obteniendo el objeto con foreign key en base al Id que nos da la vista
      categoria = jpac_object_categoria.findCategoria(Long.valueOf(request.getParameter("editCategoriaId")));

      //  Ahora necesitamos obtener el objeto a editar para chancar los nuevos valores encima
      oldObject_servicio = jpac_object_servicio.findServicio(Long.valueOf(request.getParameter("editId")));
      System.out.println("El Servicio obtenido es: " + oldObject_servicio);

//      Comparando y asignando nuevos valores al objeto
      if (!oldObject_servicio.getDescripcion().equals(request.getParameter("editDescripcion"))) {
        oldObject_servicio.setDescripcion(request.getParameter("editDescripcion"));
      }
      if (!oldObject_servicio.getDetalles().equals(request.getParameter("editDetalles"))) {
        oldObject_servicio.setDetalles(request.getParameter("editDetalles"));
      }
      
      BigDecimal old = new BigDecimal(oldObject_servicio.getPrecio());
      BigDecimal nuevo = new BigDecimal(request.getParameter("editPrecio"));
      
      if(old.compareTo(nuevo) != 0){
        oldObject_servicio.setPrecio(Double.valueOf(request.getParameter("editPrecio")));
      }
//      if (oldObject_servicio.getPrecio().compareTo(Double.valueOf(request.getParameter("editPrecio")))!=0) {
//        oldObject_servicio.setPrecio(Double.valueOf(request.getParameter("editPrecio")));
//      }
      if (!oldObject_servicio.getEstado().equals(request.getParameter("editEstado"))) {
        oldObject_servicio.setEstado(request.getParameter("editEstado"));
      }
      if (!oldObject_servicio.getCategoriaId().equals(categoria)) {
        oldObject_servicio.setCategoriaId(categoria);
      }
      oldObject_servicio.setUpdatedAt(ts);
//      oldObject_distrito.setDireccionCollection(mi_lista_de_Direcciones);

      System.out.println("El Servicio actualizado es: "
          + oldObject_servicio.getId() + " - " + oldObject_servicio.getDescripcion() + " - "
          + oldObject_servicio.getEstado() + " - " + oldObject_servicio.getCategoriaId().getDescripcion() + " - "
          + oldObject_servicio.getCreatedAt() + " - " + oldObject_servicio.getUpdatedAt() + " - "
          + oldObject_servicio.getDetalleFacturaCollection());

      jpac_object_servicio.edit(oldObject_servicio);

      ServicioListServlet call = new ServicioListServlet();
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
