/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.DetalleFacturaJpaController;
import com.dao.FacturaJpaController;
import com.dao.PrendaJpaController;
import com.dao.ServicioJpaController;
import com.dto.DetalleFactura;
import com.dto.Factura;
import com.dto.Prenda;
import com.dto.Servicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
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
@WebServlet(name = "DetalleFacturaCreateServlet", urlPatterns = {"/DetalleFacturaCreateServlet"})
public class DetalleFacturaCreateServlet extends HttpServlet {

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

    System.out.println("Bandera servlet create DetalleFactura");
    try {
      DetalleFacturaJpaController jpac_obj_detF = new DetalleFacturaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      FacturaJpaController jpac_obj_factura = new FacturaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      ServicioJpaController jpac_obj_servicio = new ServicioJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      PrendaJpaController jpac_object_prenda = new PrendaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));

      DetalleFactura mi_objeto_detF = new DetalleFactura();
      Factura mi_objeto_factura = new Factura();
      Servicio mi_objeto_servicio = new Servicio();
      Prenda mi_objeto_prenda = new Prenda();

      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);

//      Obteniendo el objeto en base al Id obtenido de la vista
      mi_objeto_factura = jpac_obj_factura.findFactura(Long.valueOf(request.getParameter("addFacturaId")));
      mi_objeto_servicio = jpac_obj_servicio.findServicio(Long.valueOf(request.getParameter("addServicioId")));
      mi_objeto_prenda = jpac_object_prenda.findPrenda(Long.valueOf(request.getParameter("addPrendaId")));
      System.out.println("La factura obtenida fue: " + mi_objeto_factura.getNumero()+ " - " + mi_objeto_factura.getId());
      System.out.println("El servicio obtenido fue: " + mi_objeto_servicio.getDescripcion()+ " - " + mi_objeto_servicio.getId());
      System.out.println("La prenda obtenida fue: " + mi_objeto_prenda.getMarca()+" - "+ mi_objeto_prenda.getId());
      
//      Llenando los parámetros del distrito obtenidos de la vista
//            mi_distrito.setIdTelefono(566);                        //No necesario, tiene auto_increment
      mi_objeto_detF.setUniqueId(String.valueOf(java.util.UUID.randomUUID()));
      mi_objeto_detF.setCantidad(Integer.valueOf(request.getParameter("addCantidad")));
      mi_objeto_detF.setPrecio(Double.valueOf(request.getParameter("addPrecio")));
      mi_objeto_detF.setSubtotal(Double.valueOf(request.getParameter("addSubtotal")));
      mi_objeto_detF.setIgv(Double.valueOf(request.getParameter("addIgv")));
      mi_objeto_detF.setTotal(Double.valueOf(request.getParameter("addTotal")));
      mi_objeto_detF.setEstado("activo");
      mi_objeto_detF.setCreatedAt(ts);
      mi_objeto_detF.setUpdatedAt(ts);
      mi_objeto_detF.setFacturaId(mi_objeto_factura);
      mi_objeto_detF.setServicioId(mi_objeto_servicio);
      mi_objeto_detF.setPrendaId(mi_objeto_prenda);

//      Llamando al método crear del controlador y pasándole el objeto Distrito
      jpac_obj_detF.create(mi_objeto_detF);

//      Llamando al listALGO.jsp
      DetalleFacturaListServlet call = new DetalleFacturaListServlet();
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
