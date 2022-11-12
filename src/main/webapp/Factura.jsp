<%-- 
    Document   : Factura
    Created on : 12 nov. 2022, 05:11:00
    Author     : desti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<t:template title="Listar Facturas">
  <jsp:attribute name="head_area">
  </jsp:attribute>
  <jsp:attribute name="body_area">


    <div class="col-md-12">
      <div class="card">
        <div class="card-header">
          <div class="d-flex align-items-center">
            <h4 class="card-title">Facturas</h4>
            <button class="btn btn-primary btn-round ml-auto" data-toggle="modal" data-target="#addRowModal">
              <i class="fa fa-plus"></i>
              Añadir Factura
            </button>
          </div>
        </div>
        <div class="card-body">

          <!-- Modal Crear -->
          <div class="modal fade" id="addRowModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header no-bd">
                  <h5 class="modal-title">
                    <span class="fw-mediumbold">
                      Nueva</span> 
                    <span class="fw-light">
                      Factura
                    </span>
                  </h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <form  action="FacturaCreateServlet" method="post">
                    <div class="row">
                      <div class="col-sm-12">
                        <div class="form-group form-group-default">
                          <label>Número</label>
                          <input name="addNumero" type="text" class="form-control"">
                        </div>
                        <div class="form-group form-group-default">
                          <label>Serie</label>
                          <input name="addSerie" type="text" class="form-control">
                        </div>
                        <div class="form-group form-group-default">
                          <label>Tipo</label>
                          <input name="addTipo" type="text" class="form-control">
                        </div>
                        <div class="form-group form-group-default">
                          <label>Fecha</label>
                          <input name="addFecha" type="date" class="form-control">
                        </div>
                        <div class="form-group form-group-default">
                          <label>Hora</label>
                          <input name="addHora" type="time" class="form-control" step="1">
                        </div>

                        <div class="form-group form-group-default">
                          <label>Persona</label>
                          <select class="form-control" name="addPersonaId">
                            <c:forEach var="tempObjetoCreate" items="${mi_lista_de_personas }">
                              <option value="${tempObjetoCreate.id }">${tempObjetoCreate.nombres }</option>
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
                  <th>Número</th>
                  <th>Serie</th>
                  <th>Tipo</th>
                  <th>Fecha</th>
                  <th>Hora</th>
                  <th>Persona</th>
                  <th>Estado</th>
                  <th>Creado</th>
                  <th>Modificado</th>
                  <th style="width: 10%">Acciones</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="tempObjeto" items="${mi_lista_de_facturas }">
                  <tr>
                    <td>${tempObjeto.numero }</td>
                    <td>${tempObjeto.serie}</td>
                    <td>${tempObjeto.tipo}</td>
                    <td>${tempObjeto.fecha}</td>
                    <td>${tempObjeto.hora}</td>
                    <td>${tempObjeto.personaId.nombres}</td>
                    <td>${tempObjeto.estado}</td>
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
                          <span class="fw-light">esta factura?</span>
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                      <div class="modal-body">
                        <form  action="FacturaDestroyServlet" method="post">
                          <div class="row">
                            <div class="col-sm-12">
                              <div class="form-group form-group-default">
                                <label>Id</label>
                                <input name="destroyId" id="destroyId" type="text" class="form-control" value="${tempObjeto.id }" readonly>
                              </div>
                              <div class="form-group form-group-default">
                                <label>Número</label>
                                <input name="destroyObject" id="destroyObject" type="text" class="form-control" value="${tempObjeto.numero }" readonly>
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
                            Factura
                          </span>
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                      <div class="modal-body">
                        <form  action="FacturaEditServlet" method="post">
                          <div class="row">
                            <div class="col-sm-12">
                              <div class="form-group form-group-default">
                                <label>Id</label>
                                <input name="editId" type="text" class="form-control" value="${tempObjeto.id }" readonly>
                              </div>
                              <div class="form-group form-group-default">
                                <label>Número</label>
                                <input name="editNumero" type="number" class="form-control" value="${tempObjeto.numero }">
                              </div>
                              <div class="form-group form-group-default">
                                <label>Serie</label>
                                <input name="editSerie" type="text" class="form-control" value="${tempObjeto.serie }">
                              </div>
                              <div class="form-group form-group-default">
                                <label>Tipo</label>
                                <input name="editTipo" type="number" class="form-control" value="${tempObjeto.tipo }">
                              </div>
                              <div class="form-group form-group-default">
                                <label>Fecha</label>
                                <input name="editFecha" type="number" class="form-control" value="${tempObjeto.fecha }">
                              </div>
                              <div class="form-group form-group-default">
                                <label>Hora</label>
                                <input name="editHora" type="number" class="form-control" value="${tempObjeto.hora }">
                              </div>
                              <div class="form-group form-group-default">
                                <label>Categoría</label>
                                <select class="form-control" name="editPersonaId">
                                  <c:forEach var="tempObjetoEdit" items="${mi_lista_de_personas }">
                                    <option value="${tempObjetoEdit.id }">${tempObjetoEdit.nombres }</option>
                                  </c:forEach>
                                </select>
                              </div>
                              <div class="form-group form-group-default">
                                <label>Estado</label>

                                <select class="form-control" name="editEstado" id="editEstado">
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