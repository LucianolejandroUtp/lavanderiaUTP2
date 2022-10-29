<%-- 
    Document   : listDepartamento
    Created on : 28 oct. 2022, 23:29:15
    Author     : desti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.servlets.DepartamentoCreateServlet" %>
<%@page import="com.servlets.DepartamentoListServlet" %>
<%@page import="com.servlets.DepartamentoEditServlet" %>


<t:template title="Listar Departamentos">
  <jsp:attribute name="head_area">

  </jsp:attribute>
  <jsp:attribute name="body_area">

    <div class="col-md-12">
      <div class="card">
        <div class="card-header">
          <div class="d-flex align-items-center">
            <h4 class="card-title">Departamentos</h4>
            <button class="btn btn-primary btn-round ml-auto" data-toggle="modal" data-target="#addRowModal">
              <i class="fa fa-plus"></i>
              Añadir departamento
            </button>
          </div>
        </div>
        <div class="card-body">
          <!-- Modal Crear Departamento-->
          <div class="modal fade" id="addRowModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header no-bd">
                  <h5 class="modal-title">
                    <span class="fw-mediumbold">
                      Nuevo</span> 
                    <span class="fw-light">
                      Departamento
                    </span>
                  </h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <form  action="DepartamentoCreateServlet" method="post">
                    <div class="row">
                      <div class="col-sm-12">
                        <div class="form-group form-group-default">
                          <label>Nombre</label>
                          <input name="departamento" id="addName" type="text" class="form-control" placeholder="Llene el departamento">
                        </div>
                      </div>
                      <div class="col-md-6">
                        <button type="submit" class="btn btn-primary">Guardar</button>
                      </div>
                    </div>
                  </form>
                </div>
                <!--                                <div class="modal-footer no-bd">
                                                    <button type="button" id="addRowButton" class="btn btn-primary">Add</button>
                                                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                                </div>-->
              </div>
            </div>
          </div>


          <!-- Modal Editar Departamento-->
          <div class="modal fade" id="editRowModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header no-bd">
                  <h5 class="modal-title">
                    <span class="fw-mediumbold">
                      Editar</span> 
                    <span class="fw-light">
                      Departamento
                    </span>
                  </h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <form  action="DepartamentoEditServlet" method="post">
                    <div class="row">
                      <div class="col-sm-12">
                        <div class="form-group form-group-default">
                          <label>Nombre</label>
                          <input name="departamento_edit" id="addName" type="text" class="form-control">
                        </div>
                      </div>
                      <div class="col-md-6">
                        <button type="submit" class="btn btn-primary">Guardar</button>
                      </div>
                    </div>
                  </form>
                </div>
                <!--                                <div class="modal-footer no-bd">
                                                    <button type="button" id="addRowButton" class="btn btn-primary">Add</button>
                                                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                                </div>-->
              </div>
            </div>
          </div>







          <div class="table-responsive">
            <table id="add-row" class="display table table-striped table-hover" >
              <thead>
                <tr>
                  <th>Nombre</th>
                  <th>Estado</th>
                  <th>Fecha</th>
                  <th style="width: 10%">Acciones</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="tempObjetos" items="${mi_lista_de_objetos }">

                  <tr>
                    <td>${tempObjetos.departamento }</td>
                    <td>${tempObjetos.estado}</td>

                    <td>${tempObjetos.createdAt }</td>
                    <td>
                      <div class="form-button-action">

                        <a href="DepartamentoEditServlet">
                          <i class="fa fa-plus"></i>
                        </a>
                        <button data-toggle="modal" class="btn btn-primary btn-round ml-auto" data-target="#editRowModal">
                          <i class="fa fa-plus"></i>
                        </button>
                        <button data-whatever="algo" type="button" data-toggle="modal" title="" class="btn btn-link btn-primary btn-lg" data-target="#editRowModal">
                          <i class="fa fa-edit"></i>
                        </button>
                        <button type="button" data-toggle="tooltip" title="" class="btn btn-link btn-danger" data-original-title="Remove">
                          <i class="fa fa-times"></i>
                        </button>
                      </div>
                    </td>
                  </tr>
                </c:forEach>

              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>


  </jsp:attribute>
</t:template>
