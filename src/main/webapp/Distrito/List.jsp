<%-- 
    Document   : Listar
    Created on : 22 oct. 2022, 01:34:37
    Author     : desti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.servlets.DistritoCreateServlet" %>
<%@page import="com.servlets.DistritoListServlet" %>

<t:template title="Listar Distritos">
  <jsp:attribute name="head_area">

  </jsp:attribute>
  <jsp:attribute name="body_area">

    <div class="col-md-12">
      <div class="card">
        <div class="card-header">
          <div class="d-flex align-items-center">
            <h4 class="card-title">Distritos</h4>
            <button class="btn btn-primary btn-round ml-auto" data-toggle="modal" data-target="#addRowModal">
              <i class="fa fa-plus"></i>
              Añadir Distrito
            </button>
          </div>
        </div>
        <div class="card-body">
          <!-- Modal Crear Distrito-->
          <div class="modal fade" id="addRowModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header no-bd">
                  <h5 class="modal-title">
                    <span class="fw-mediumbold">
                      Nuevo</span> 
                    <span class="fw-light">
                      Distrito
                    </span>
                  </h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <form  action="DistritoCreateServlet" method="post">
                    <div class="row">
                      <div class="col-sm-12">
                        <div class="form-group form-group-default">
                          <label>Nombre</label>
                          <input name="distrito" id="addName" type="text" class="form-control" placeholder="Llene el distrito">
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
                  <th>Provincia</th>
                  <th>Fecha</th>
                  <th style="width: 10%">Acciones</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="tempDistritos" items="${mi_lista_de_distritos }">
                  <tr>
                    <td>${tempDistritos.distrito }</td>
                    <td>${tempDistritos.estado}</td>
                    <td>${tempDistritos.departamentoId.departamento}</td>
                    
                    <td>${tempDistritos.createdAt }</td>
                    <td>
                      <div class="form-button-action">
                        <button type="button" data-toggle="tooltip" title="" class="btn btn-link btn-primary btn-lg" data-original-title="Edit Task">
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