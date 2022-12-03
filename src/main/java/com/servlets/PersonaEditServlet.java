/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.PersonaJpaController;
import com.dao.TipoPersonaJpaController;
import com.dto.Persona;
import com.dto.TipoPersona;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 *
 * @author desti
 */
@WebServlet(name = "PersonaEditServlet", urlPatterns = {"/PersonaEditServlet"})
public class PersonaEditServlet extends HttpServlet {

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
    
    System.out.println("Entrando a Persona Edit Servlet");
    System.out.println(request.getParameter("editId"));
    System.out.println(request.getParameter("editNombres"));
    System.out.println(request.getParameter("editApellidos"));
    System.out.println(request.getParameter("editDni"));
    System.out.println(request.getParameter("editEmail"));
    System.out.println(request.getParameter("editPassword"));
    System.out.println(request.getParameter("editTdPersonaId"));
    System.out.println(request.getParameter("editEstado"));
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU");
    try {
//      Inicialización de objetos
      PersonaJpaController jpac_obj_persona = new PersonaJpaController(emf);
      TipoPersonaJpaController jpac_obj_TdPersona = new TipoPersonaJpaController(emf);
      Persona oldObject_persona;
      TipoPersona mi_TdPersona;
      BasicPasswordEncryptor passEnc = new BasicPasswordEncryptor();

//      Lo relacionado a la fecha
      
      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);

//      Necesitamos una lista de los Direcciones
//      DireccionJpaController jpac_xa_lista_de_Direcciones = new DireccionJpaController();
//      List<Direccion> mi_lista_de_Direcciones = new ArrayList<>();
//      mi_lista_de_Direcciones = jpac_xa_lista_de_Direcciones.findDireccionEntities();

//      Obteniendo el objeto con foreign key en base al Id que nos da la vista
      mi_TdPersona = jpac_obj_TdPersona.findTipoPersona(Long.valueOf(request.getParameter("editTdPersonaId")));

      //  Ahora necesitamos obtener el objeto a editar para chancar los nuevos valores encima
      oldObject_persona = jpac_obj_persona.findPersona(Long.valueOf(request.getParameter("editId")));
      System.out.println("La Persona obtenida es: " + oldObject_persona);

//      Comparando y asignando nuevos valores al objeto
      if (oldObject_persona.getNombres()== null || !oldObject_persona.getNombres().equals(request.getParameter("editNombres"))) {
        oldObject_persona.setNombres(request.getParameter("editNombres"));
      }
      if (oldObject_persona.getApellidos()== null || !oldObject_persona.getApellidos().equals(request.getParameter("editApellidos"))) {
        oldObject_persona.setApellidos(request.getParameter("editApellidos"));
      }
      if (oldObject_persona.getDni()== null || !oldObject_persona.getDni().equals(request.getParameter("editDni"))) {
        oldObject_persona.setDni(request.getParameter("editDni"));
      }
      if (oldObject_persona.getEmail()== null || !oldObject_persona.getEmail().equals(request.getParameter("editEmail"))) {
        oldObject_persona.setEmail(request.getParameter("editEmail"));
      }
      if (oldObject_persona.getPassword()== null || !passEnc.checkPassword(request.getParameter("editPassword"), oldObject_persona.getPassword())) {
        oldObject_persona.setPassword(passEnc.encryptPassword(request.getParameter("editPassword")));
      }
      
      
      if (!oldObject_persona.getTipoPersonaId().equals(mi_TdPersona)) {
        oldObject_persona.setTipoPersonaId(mi_TdPersona);
      }
      if (oldObject_persona.getEstado() == null || !oldObject_persona.getEstado().equals(request.getParameter("editEstado"))) {
        oldObject_persona.setEstado(request.getParameter("editEstado"));
      }
      oldObject_persona.setUpdatedAt(ts);
//      oldObject_distrito.setDireccionCollection(mi_lista_de_Direcciones);

      System.out.println("La Persona actualizada es: "
          + oldObject_persona.getId() + " - " + oldObject_persona.getNombres()+ " - "
          + oldObject_persona.getEstado() + " - " + oldObject_persona.getTipoPersonaId().getDescripcion()+ " - "
          + oldObject_persona.getCreatedAt() + " - " + oldObject_persona.getUpdatedAt() + " - "
          + oldObject_persona.getTelefonoCollection());

      jpac_obj_persona.edit(oldObject_persona);

      PersonaListServlet call = new PersonaListServlet();
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
