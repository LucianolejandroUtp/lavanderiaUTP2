/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.CategoriaJpaController;
import com.dao.ComprobanteJpaController;
import com.dao.PersonaJpaController;
import com.dao.ServicioJpaController;
import com.dto.Categoria;
import com.dto.Comprobante;
import com.dto.Persona;
import com.dto.Servicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "ComprobanteEditServlet", urlPatterns = {"/ComprobanteEditServlet"})
public class ComprobanteEditServlet extends HttpServlet {

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
    System.out.println(request.getParameter("editNumero"));
    System.out.println(request.getParameter("editSerie"));
    System.out.println(request.getParameter("editTipo"));
    System.out.println(request.getParameter("editFecha"));
    System.out.println(request.getParameter("editHora"));
    System.out.println(request.getParameter("editPersonaId"));
    System.out.println(request.getParameter("editEstado"));
    try {
//      Inicialización de objetos
      ComprobanteJpaController jpac_obj_comprobante = new ComprobanteJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      PersonaJpaController jpac_obj_persona = new PersonaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      Comprobante oldObject_comprobante;
      Persona mi_persona;

//      Lo relacionado a la fecha
      SimpleDateFormat sdf_fecha = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat sdf_hora = new SimpleDateFormat("HH:mm");
      
      Date fecha = sdf_fecha.parse(String.valueOf(request.getParameter("editFecha")));
      Date hora = sdf_hora.parse(String.valueOf(request.getParameter("editHora")));
      
      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);

//      Necesitamos una lista de los Direcciones
//      DireccionJpaController jpac_xa_lista_de_Direcciones = new DireccionJpaController();
//      List<Direccion> mi_lista_de_Direcciones = new ArrayList<>();
//      mi_lista_de_Direcciones = jpac_xa_lista_de_Direcciones.findDireccionEntities();

//      Obteniendo el objeto con foreign key en base al Id que nos da la vista
      mi_persona = jpac_obj_persona.findPersona(Long.valueOf(request.getParameter("editPersonaId")));

      //  Ahora necesitamos obtener el objeto a editar para chancar los nuevos valores encima
      oldObject_comprobante = jpac_obj_comprobante.findComprobante(Long.valueOf(request.getParameter("editId")));
      System.out.println("El Comprobante obtenido es: " + oldObject_comprobante);

      
//      if(oldObject_servicio.getDescripcion().isBlank()){
//        System.out.println("Descripción vacía o con espacios en blanco");
//      }
      
//      Comparando y asignando nuevos valores al objeto
      if (oldObject_comprobante.getNumero()== null || !oldObject_comprobante.getNumero().equals(request.getParameter("editNumero"))) {
        oldObject_comprobante.setNumero(request.getParameter("editNumero"));
      }
      if (oldObject_comprobante.getSerie()== null || !oldObject_comprobante.getSerie().equals(request.getParameter("editSerie"))) {
        oldObject_comprobante.setSerie(request.getParameter("editSerie"));
      }
      if (oldObject_comprobante.getTipo()== null || !oldObject_comprobante.getTipo().equals(request.getParameter("editTipo"))) {
        oldObject_comprobante.setTipo(request.getParameter("editTipo"));
      }
      
      //Comparando fecha y hora por separado
      if(oldObject_comprobante.getFecha() == null || oldObject_comprobante.getFecha().compareTo(fecha) != 0){
        oldObject_comprobante.setFecha(fecha);
      }
      if(oldObject_comprobante.getHora() == null || oldObject_comprobante.getHora().compareTo(hora) != 0){
        oldObject_comprobante.setHora(hora);
      }
      
      
      if (!oldObject_comprobante.getPersonaId().equals(mi_persona)) {
        oldObject_comprobante.setPersonaId(mi_persona);
      }
      if (oldObject_comprobante.getEstado() == null || !oldObject_comprobante.getEstado().equals(request.getParameter("editEstado"))) {
        oldObject_comprobante.setEstado(request.getParameter("editEstado"));
      }
      oldObject_comprobante.setUpdatedAt(ts);
//      oldObject_distrito.setDireccionCollection(mi_lista_de_Direcciones);

      System.out.println("La Factura actualizada es: "
          + oldObject_comprobante.getId() + " - " + oldObject_comprobante.getNumero()+ " - "
          + oldObject_comprobante.getEstado() + " - " + oldObject_comprobante.getPersonaId().getNombres()+ " - "
          + oldObject_comprobante.getCreatedAt() + " - " + oldObject_comprobante.getUpdatedAt() + " - "
          + oldObject_comprobante.getDetalleComprobanteCollection());

      jpac_obj_comprobante.edit(oldObject_comprobante);

      ComprobanteListServlet call = new ComprobanteListServlet();
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
