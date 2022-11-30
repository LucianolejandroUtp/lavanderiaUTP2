/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.DetalleComprobanteJpaController;
import com.dao.ComprobanteJpaController;
import com.dao.PersonaJpaController;
import com.dao.PrendaJpaController;
import com.dao.ServicioJpaController;
import com.dao.TipoDePrendaJpaController;
import com.dto.DetalleComprobante;
import com.dto.Comprobante;
import com.dto.Persona;
import com.dto.Prenda;
import com.dto.Servicio;
import com.dto.TipoDePrenda;
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
@WebServlet(name = "DetalleComprobanteEditServlet", urlPatterns = {"/DetalleComprobanteEditServlet"})
public class DetalleComprobanteEditServlet extends HttpServlet {

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

    System.out.println("Entrando a Detalle Comprobante Edit Servlet");
    System.out.println(request.getParameter("editId"));
    System.out.println(request.getParameter("editCantidad"));
    System.out.println(request.getParameter("editPrecio"));
    System.out.println(request.getParameter("editSubtotal"));
    System.out.println(request.getParameter("editIgv"));
    System.out.println(request.getParameter("editTotal"));
    System.out.println(request.getParameter("editComprobanteId"));
    System.out.println(request.getParameter("editServicioId"));
//    System.out.println(request.getParameter("editPrendaId"));
    System.out.println(request.getParameter("editEstado"));
    try {
//      Inicialización de objetos
      DetalleComprobanteJpaController jpac_obj_detC = new DetalleComprobanteJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      ComprobanteJpaController jpac_obj_comprobante = new ComprobanteJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      ServicioJpaController jpac_obj_servicio = new ServicioJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
//      PrendaJpaController jpac_obj_prenda = new PrendaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));

      DetalleComprobante oldObject_detC;
      Comprobante mi_obj_comprobante;
      Servicio mi_obj_servicio;
//      Prenda mi_obj_prenda;

//      Lo relacionado a la fecha
      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);

//      Necesitamos una lista de los Direcciones
//      DireccionJpaController jpac_xa_lista_de_Direcciones = new DireccionJpaController();
//      List<Direccion> mi_lista_de_Direcciones = new ArrayList<>();
//      mi_lista_de_Direcciones = jpac_xa_lista_de_Direcciones.findDireccionEntities();
//      Obteniendo el objeto con foreign key en base al Id que nos da la vista
      mi_obj_comprobante = jpac_obj_comprobante.findComprobante(Long.valueOf(request.getParameter("editComprobanteId")));
      mi_obj_servicio = jpac_obj_servicio.findServicio(Long.valueOf(request.getParameter("editServicioId")));
//      mi_obj_prenda = jpac_obj_prenda.findPrenda(Long.valueOf(request.getParameter("editPrendaId")));

      //  Ahora necesitamos obtener el objeto a editar para chancar los nuevos valores encima
      oldObject_detC = jpac_obj_detC.findDetalleComprobante(Long.valueOf(request.getParameter("editId")));
      System.out.println("El Detalle de Comprobante obtenido es: " + oldObject_detC);

//      Comparando y asignando nuevos valores al objeto
      if(oldObject_detC.getCantidad() == null || !oldObject_detC.getCantidad().equals(Integer.valueOf(request.getParameter("editCantidad")))){
        oldObject_detC.setCantidad(Integer.valueOf(request.getParameter("editCantidad")));
      }
      if (oldObject_detC.getPrecio() == null || oldObject_detC.getPrecio().compareTo(Double.valueOf(request.getParameter("editPrecio"))) != 0) {
        oldObject_detC.setPrecio(Double.valueOf(request.getParameter("editPrecio")));
      }

      if (oldObject_detC.getSubtotal()== null || oldObject_detC.getSubtotal().compareTo(Double.valueOf(request.getParameter("editSubtotal"))) != 0) {
        oldObject_detC.setSubtotal(Double.valueOf(request.getParameter("editSubtotal")));
      }
      if (oldObject_detC.getIgv()== null || oldObject_detC.getIgv().compareTo(Double.valueOf(request.getParameter("editIgv"))) != 0) {
        oldObject_detC.setIgv(Double.valueOf(request.getParameter("editIgv")));
      }

      if (oldObject_detC.getTotal()== null || oldObject_detC.getTotal().compareTo(Double.valueOf(request.getParameter("editTotal"))) != 0) {
        oldObject_detC.setTotal(Double.valueOf(request.getParameter("editTotal")));
      }

      if (!oldObject_detC.getEstado().equals(request.getParameter("editEstado"))) {
        oldObject_detC.setEstado(request.getParameter("editEstado"));
      }

      if (!oldObject_detC.getComprobanteId().equals(mi_obj_comprobante)) {
        oldObject_detC.setComprobanteId(mi_obj_comprobante);
      }
      if (!oldObject_detC.getServicioId().equals(mi_obj_servicio)) {
        oldObject_detC.setServicioId(mi_obj_servicio);
      }
//      if (!oldObject_detF.getPrendaId().equals(mi_obj_prenda)) {
//        oldObject_detF.setPrendaId(mi_obj_prenda);
//      }
      oldObject_detC.setUpdatedAt(ts);
//      oldObject_distrito.setDireccionCollection(mi_lista_de_Direcciones);

      System.out.println("El Detalle de Comprobante actualizado es: "
          + oldObject_detC.getId() + " - " + oldObject_detC.getCantidad()+ " - " + oldObject_detC.getTotal()+ " - "
          + oldObject_detC.getEstado() + " - " + oldObject_detC.getServicioId().getDescripcion() + " - "
          + oldObject_detC.getCreatedAt() + " - " + oldObject_detC.getUpdatedAt());

      jpac_obj_detC.edit(oldObject_detC);

      DetalleComprobanteListServlet call = new DetalleComprobanteListServlet();
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
