<%-- 
    Document   : listDistrito
    Created on : 3 nov. 2022, 23:48:52
    Author     : desti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@page import="com.servlets.DepartamentoCreateServlet" %>


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
                          <input name="distritoNombreCreate" id="addName" type="text" class="form-control" placeholder="Llene el distrito">
                        </div>

                        <div class="form-group form-group-default">
                          <label>Select</label>
                          <select class="form-control" name="distritoDepartamentoIdCreate" id="distritoDepartamentoIdCreate">
                            <c:forEach var="departamentoObjetoTemp" items="${mi_lista_de_departamentos }">
                              <option value="${departamentoObjetoTemp.id }">${departamentoObjetoTemp.descripcion }</option>
                            </c:forEach>

                          </select>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <button type="submit" class="btn btn-primary">Guardar</button>
                      </div>
                    </div>
                  </form>
                </div>

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
                  <th>Creado</th>
                  <th>Modificado</th>
                  <th style="width: 10%">Acciones</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="tempObjeto" items="${mi_lista_de_distritos }">
                  <tr>
                    <td>${tempObjeto.descripcion }</td>
                    <td>${tempObjeto.estado}</td>
                    <td>${tempObjeto.departamentoId.descripcion}</td>
                    <td>${tempObjeto.createdAt }</td>
                    <td>${tempObjeto.updatedAt }</td>
                    <td>
                      <div class="form-button-action">
                        <button data-target="#${tempObjeto.id }" type="button" data-toggle="modal" class="btn btn-link btn-primary btn-lg">
                          <i class="fa fa-edit"></i>
                        </button>
                        <button data-target="#${tempObjeto.id }${tempObjeto.id }" type="button" data-toggle="modal" class="btn btn-link btn-danger">
                          <i class="fa fa-times"></i>
                        </button>
                      </div>
                    </td>
                  </tr>



                  <!-- Modal Eliminar -->
                <div class="modal fade" id="${tempObjeto.id }${tempObjeto.id }" tabindex="-1" role="dialog" aria-hidden="true">
                  <div class="modal-dialog" role="document">
                    <div class="modal-content">
                      <div class="modal-header no-bd">
                        <h5 class="modal-title">
                          <span class="fw-light">¿Está relamente seguro de querer</span>
                          <span class="fw-mediumbold"> eliminar </span>
                          <span class="fw-light">este distrito?</span>
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                      <div class="modal-body">
                        <form  action="DistritoDestroyServlet" method="post">
                          <div class="row">
                            <div class="col-sm-12">
                              <div class="form-group form-group-default">
                                <label>Id</label>
                                <input name="destroy_distrito_id" id="destroy_distrito_id" type="text" class="form-control" value="${tempObjeto.id }" readonly>
                              </div>
                              <div class="form-group form-group-default">
                                <label>Nombre</label>
                                <input name="destroy_distrito_departamento" id="destroy_distrito_departamento" type="text" class="form-control" value="${tempObjeto.descripcion }" readonly>
                              </div>

                            </div>
                            <div class="col-md-6">
                              <button type="submit" class="btn btn-danger">Borrar</button>
                            </div>
                          </div>
                        </form>
                      </div>
                    </div>
                  </div>
                </div>

                <!-- Modal Editar -->
                <div class="modal fade" id="${tempObjeto.id }" tabindex="-1" role="dialog" aria-hidden="true">
                  <div class="modal-dialog" role="document">
                    <div class="modal-content">
                      <div class="modal-header no-bd">
                        <h5 class="modal-title">
                          <span class="fw-mediumbold">
                            Editar</span> 
                          <span class="fw-light">
                            Distrito
                          </span>
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                      <div class="modal-body">
                        <form  action="DistritoEditServlet" method="post">
                          <div class="row">
                            <div class="col-sm-12">
                              <div class="form-group form-group-default">
                                <label>Id</label>
                                <input name="edit_distrito_id" id="edit_distrito_id" type="text" class="form-control" value="${tempObjeto.id }" readonly>
                              </div>
                              <div class="form-group form-group-default">
                                <label>Nombre</label>
                                <input name="edit_distrito_descripcion" id="edit_distrito_descripcion" type="text" class="form-control" value="${tempObjeto.descripcion }">
                              </div>
                              <div class="form-group form-group-default">
                                <label>Departamento</label>
                                <select class="form-control" name="edit_distrito_depaId" id="edit_distrito_depaId">
                                  <c:forEach var="departamentoObjetoTemp" items="${mi_lista_de_departamentos }">
                                    <option value="${departamentoObjetoTemp.id }">${departamentoObjetoTemp.descripcion }</option>
                                  </c:forEach>
                                </select>
                              </div>
                              <div class="form-group form-group-default">
                                <label>Select</label>

                                <select class="form-control" name="edit_distrito_estado" id="edit_distrito_estado">
                                  <option value="activo">Activo</option>
                                  <option value="inactivo">Inactivo</option>
                                  <!--<option value="eliminado">Eliminado</option>-->
                                </select>
                              </div>
                            </div>
                            <div class="col-md-6">
                              <button type="submit" class="btn btn-primary">Guardar</button>
                            </div>
                          </div>
                        </form>
                      </div>

                    </div>
                  </div>
                </div>

              </c:forEach>

              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>



  </jsp:attribute>
</t:template>