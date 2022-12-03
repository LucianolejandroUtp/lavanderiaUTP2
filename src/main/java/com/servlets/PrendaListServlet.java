/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.PersonaJpaController;
import com.dao.PrendaJpaController;
import com.dao.ServicioJpaController;
import com.dao.TipoDePrendaJpaController;
import com.dto.Persona;
import com.dto.Prenda;
import com.dto.Servicio;
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

/**
 *
 * @author desti
 */
@WebServlet(name = "PrendaListServlet", urlPatterns = {"/PrendaListServlet"})
public class PrendaListServlet extends HttpServlet {

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
    
    System.out.println("Entrando a Prenda List Servlet");
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU");
    
    try {
      PrendaJpaController jpac_obj_prenda = new PrendaJpaController(emf);
      TipoDePrendaJpaController jpac_obj_TdPrenda = new TipoDePrendaJpaController(emf);
      PersonaJpaController jpac_obj_persona = new PersonaJpaController(emf);
      ServicioJpaController jpac_obj_servicio = new ServicioJpaController(emf);
      List<Prenda> mi_lista_de_prendas = new ArrayList<>();
      List<TipoDePrenda> mi_lista_de_TdPrendas = new ArrayList<>();
      List<Persona> mi_lista_de_personas = new ArrayList<>();
      List<Servicio> mi_lista_de_servicios = new ArrayList<>();

//      System.out.println(jpacontroller_object.findDistritoEntities());
      mi_lista_de_prendas = jpac_obj_prenda.findPrendaEntities();
      mi_lista_de_TdPrendas = jpac_obj_TdPrenda.findTipoDePrendaEntities();
      mi_lista_de_personas =jpac_obj_persona.findPersonaEntities();
      mi_lista_de_servicios =jpac_obj_servicio.findServicioEntities();

      for (Prenda temp1 : mi_lista_de_prendas) {
        System.out.println(temp1.getId() +" - "+ temp1.getColor()+" - "+ temp1.getTipoDePrendaId().getDescripcion()
            +" - "+temp1.getPersonaIdCliente().getDni()+" - "+temp1.getPersonaIdEmpleado().getDni());
      }
      for (TipoDePrenda temp2 : mi_lista_de_TdPrendas) {
        System.out.println(temp2.getId() + " - " + temp2.getDescripcion());
      }
      for (Persona temp3 : mi_lista_de_personas) {
        System.out.println(temp3.getId() + " - " + temp3.getNombres());
      }
      for (Servicio temp4 : mi_lista_de_servicios) {
        System.out.println(temp4.getId() + " - " + temp4.getDescripcion());
      }

      request.setAttribute("mi_lista_de_prendas", mi_lista_de_prendas);
      request.setAttribute("mi_lista_de_TdPrendas", mi_lista_de_TdPrendas);
      request.setAttribute("mi_lista_de_personas", mi_lista_de_personas);
      request.setAttribute("mi_lista_de_servicios", mi_lista_de_servicios);
      request.getRequestDispatcher("Prenda.jsp").forward(request, response);

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
