/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.DepartamentoJpaController;
import com.dao.DistritoJpaController;
import com.dao.PersonaJpaController;
import com.dao.VehiculoJpaController;
import com.dto.Departamento;
import com.dto.Distrito;
import com.dto.Persona;
import com.dto.Vehiculo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Acer
 */
@WebServlet(name = "VehiculoListServlet", urlPatterns = {"/VehiculoListServlet"})
public class VehiculoListServlet extends HttpServlet {

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
        System.out.println("Entrando al List Servlet");
    try {
      VehiculoJpaController jpac_object_vehiculo = new VehiculoJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      PersonaJpaController jpac_object_personas = new PersonaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      List<Vehiculo> mi_lista_de_vehiculo = new ArrayList<>();
      List<Persona> mi_lista_de_personas = new ArrayList<>();

//      System.out.println(jpacontroller_object.findDistritoEntities());
      mi_lista_de_vehiculo = jpac_object_vehiculo.findVehiculoEntities();
      mi_lista_de_personas = jpac_object_personas.findPersonaEntities();

      for (Vehiculo dis : mi_lista_de_vehiculo) {
        System.out.println(dis.getId() + " - " + dis.getPlaca()+ " - " + dis.getMarca()+ " - " + dis.getPersonaId().getNombres());
      }
      for (Persona dep : mi_lista_de_personas) {
        System.out.println(dep.getId() + " - " + dep.getNombres() + " - " + dep.getApellidos());
      }

      
      request.setAttribute("mi_lista_de_vehiculos", mi_lista_de_vehiculo);
      request.setAttribute("mi_lista_de_personas", mi_lista_de_personas);
      request.getRequestDispatcher("Vehiculo.jsp").forward(request, response);

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
