<%-- 
    Document   : Prenda
    Created on : 11 nov. 2022, 19:06:49
    Author     : desti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<t:template title="Listar Servicios">
  <jsp:attribute name="head_area">
  </jsp:attribute>
  <jsp:attribute name="body_area">


    <div class="col-md-12">
      <div class="card">
        <div class="card-header">
          <div class="d-flex align-items-center">
            <h4 class="card-title">Prendas</h4>
            <button class="btn btn-primary btn-round ml-auto" data-toggle="modal" data-target="#addRowModal">
              <i class="fa fa-plus"></i>
              Añadir Prenda
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
                      Prenda
                    </span>
                  </h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <form  action="PrendaCreateServlet" method="post">
                    <div class="row">
                      <div class="col-sm-12">
                        <div class="form-group form-group-default">
                          <label>Cantidad</label>
                          <input required name="addCantidad" type="number" class="form-control" placeholder="Llene la cantidad">
                        </div>
                        <div class="form-group form-group-default">
                          <label>Color</label>
                          <input required name="addColor" type="text" class="form-control" placeholder="Llene el color">
                        </div>
                        <div class="form-group form-group-default">
                          <label>Marca</label>
                          <input required name="addMarca" type="text" class="form-control" placeholder="Llene la marca">
                        </div>
                        <div class="form-group form-group-default">
                          <label>Estado de prenda</label>
                          <input required name="addEstadoDePrenda" type="text" class="form-control" placeholder="Llene el estado de la prenda">
                        </div>
                        <div class="form-group form-group-default">
                          <label>Peso</label>
                          <input required name="addPeso" type="number" class="form-control" placeholder="Llene el peso">
                        </div>
                        <div class="form-group form-group-default">
                          <label>Observación</label>
                          <input required name="addObservacion" type="text" class="form-control" placeholder="Llene la observación">
                        </div>

                        <div class="form-group form-group-default">
                          <label>Tipo de Prenda</label>
                          <select class="form-control" name="addTdPrendaId" id="addTdPrendaId">
                            <c:forEach var="tempObjetoCreate" items="${mi_lista_de_TdPrendas }">
                              <option value="${tempObjetoCreate.id }">${tempObjetoCreate.descripcion }</option>
                            </c:forEach>
                          </select>
                        </div>
                        <div class="form-group form-group-default">
                          <label>Persona</label>
                          <select class="form-control" name="addPersonaId" id="addPersonaId">
                            <c:forEach var="tempObjetoCreate2" items="${mi_lista_de_personas }">
                              <option value="${tempObjetoCreate2.id }">${tempObjetoCreate2.nombres }</option>
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
                  <th>Cantidad</th>
                  <th>Color</th>
                  <th>Marca</th>
                  <th>Estado de Prenda</th>
                  <th>Peso</th>
                  <th>Observación</th>
                  <th>Estado</th>
                  <th>Peso</th>
                  <th>Tipo de Prenda</th>
                  <th>Persona</th>
                  <th>Modificado</th>
                  <th style="width: 10%">Acciones</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="tempObjeto" items="${mi_lista_de_prendas }">
                  <tr>
                    <td>${tempObjeto.cantidad }</td>
                    <td>${tempObjeto.color}</td>
                    <td>${tempObjeto.marca}</td>
                    <td>${tempObjeto.estadoDePrenda}</td>
                    <td>${tempObjeto.peso}</td>
                    <td>${tempObjeto.observacion}</td>
                    <td>${tempObjeto.estado}</td>
                    <td>${tempObjeto.tipoDePrendaId.descripcion}</td>
                    <td>${tempObjeto.personaId.nombres}</td>
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
                          <span class="fw-light">este servicio?</span>
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                      <div class="modal-body">
                        <form  action="PrendaDestroyServlet" method="post">
                          <div class="row">
                            <div class="col-sm-12">
                              <div class="form-group form-group-default">
                                <label>Id</label>
                                <input name="destroyId" id="destroyId" type="text" class="form-control" value="${tempObjeto.id }" readonly>
                              </div>
                              <div class="form-group form-group-default">
                                <label>Nombre</label>
                                <input name="destroyObject" id="destroyObject" type="text" class="form-control" value="${tempObjeto.marca }" readonly>
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
                            Servicio
                          </span>
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                      <div class="modal-body">
                        <form  action="PrendaEditServlet" method="post">
                          <div class="row">
                            <div class="col-sm-12">
                              <div class="form-group form-group-default">
                                <label>Id</label>
                                <input name="editId" type="text" class="form-control" value="${tempObjeto.id }" readonly>
                              </div>
                              <div class="form-group form-group-default">
                                <label>Cantidad</label>
                                <input required name="editCantidad" type="number" class="form-control" value="${tempObjeto.cantidad }">
                              </div>
                              <div class="form-group form-group-default">
                                <label>Color</label>
                                <input required name="editColor" type="text" class="form-control" value="${tempObjeto.color }">
                              </div>
                              <div class="form-group form-group-default">
                                <label>Marca</label>
                                <input required name="editMarca" type="text" class="form-control" value="${tempObjeto.marca }">
                              </div>
                              <div class="form-group form-group-default">
                                <label>Estado Prenda</label>
                                <input required name="editEstadoDePrenda" type="text" class="form-control" value="${tempObjeto.estadoDePrenda }">
                              </div>
                              <div class="form-group form-group-default">
                                <label>Peso</label>
                                <input required name="editPeso" type="number" class="form-control" value="${tempObjeto.peso }">
                              </div>
                              <div class="form-group form-group-default">
                                <label>Observación</label>
                                <input required name="editObservacion" type="text" class="form-control" value="${tempObjeto.observacion }">
                              </div>
                              <div class="form-group form-group-default">
                                <label>Tipo de Prenda</label>
                                <select class="form-control" name="editTdPrendaId">
                                  <c:forEach var="tempObjetoEdit" items="${mi_lista_de_TdPrendas }">
                                    <option value="${tempObjetoEdit.id }">${tempObjetoEdit.descripcion }</option>
                                  </c:forEach>
                                </select>
                              </div>
                              <div class="form-group form-group-default">
                                <label>Persona</label>
                                <select class="form-control" name="editPersonaId">
                                  <c:forEach var="tempObjetoEdit2" items="${mi_lista_de_personas }">
                                    <option value="${tempObjetoEdit2.id }">${tempObjetoEdit2.nombres }</option>
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