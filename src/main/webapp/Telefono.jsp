<%-- 
    Document   : Telefono
    Created on : 16 nov. 2022, 17:18:56
    Author     : desti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<t:template title="Listar Teléfonos">
  <jsp:attribute name="head_area">
  </jsp:attribute>
  <jsp:attribute name="body_area">


    <div class="col-md-12">
      <div class="card">
        <div class="card-header">
          <div class="d-flex align-items-center">
            <h4 class="card-title">Teléfonos</h4>
            <button class="btn btn-primary btn-round ml-auto" data-toggle="modal" data-target="#addRowModal">
              <i class="fa fa-plus"></i>
              Añadir Teléfono
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
                      Nuevo</span> 
                    <span class="fw-light">
                      Teléfono
                    </span>
                  </h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <form  action="TelefonoCreateServlet" method="post">
                    <div class="row">
                      <div class="col-sm-12">
                        <div class="form-group form-group-default">
                          <label>Número</label>
                          <input required name="addDescripcion" type="text" class="form-control">
                        </div>
                       
                        <div class="form-group form-group-default">
                          <label>Persona</label>
                          <select class="form-control" name="addPersonaId">
                            <c:forEach var="tempObjetoCreate" items="${mi_lista_de_personas}">
                              <option value="${tempObjetoCreate.id}">${tempObjetoCreate.nombres}</option>
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
                  <th>Persona</th>
                  <th>Estado</th>
                  <th>Creado</th>
                  <th>Modificado</th>
                  <th style="width: 10%">Acciones</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="tempObjeto" items="${mi_lista_de_telefonos }">
                  <tr>
                    <td>${tempObjeto.descripcion }</td>
                    <td>${tempObjeto.personaId.nombres}</td>
                    <td>${tempObjeto.estado}</td>
                    <td>${tempObjeto.createdAt }</td>
                    <td>${tempObjeto.updatedAt }</td>
                    <td>
                      <div class="form-button-action">
                        <button type="button" data-toggle="modal" class="btn btn-link btn-primary btn-lg"
                                data-target="#${tempObjeto.uniqueId}">
                          <i class="fa fa-edit"></i>
                        </button>
                        <button type="button" data-toggle="modal" class="btn btn-link btn-danger"
                                data-target="#${tempObjeto.id}${tempObjeto.uniqueId}">
                          <i class="fa fa-times"></i>
                        </button>
                      </div>
                    </td>
                  </tr>



                  <!-- Modal Eliminar -->
                <div class="modal fade" id="${tempObjeto.id}${tempObjeto.uniqueId}" tabindex="-1" role="dialog" aria-hidden="true">
                  <div class="modal-dialog" role="document">
                    <div class="modal-content">
                      <div class="modal-header no-bd">
                        <h5 class="modal-title">
                          <span class="fw-light">¿Está relamente seguro de querer</span>
                          <span class="fw-mediumbold"> eliminar </span>
                          <span class="fw-light">este teléfono?</span>
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                      <div class="modal-body">
                        <form  action="TelefonoDestroyServlet" method="post">
                          <div class="row">
                            <div class="col-sm-12">
                              <div class="form-group form-group-default">
                                <label>Id</label>
                                <input name="destroyId" type="text" class="form-control" value="${tempObjeto.id }" readonly>
                              </div>
                              <div class="form-group form-group-default">
                                <label>Número</label>
                                <input name="destroyObject" type="text" class="form-control" value="${tempObjeto.descripcion }" readonly>
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
                <div class="modal fade" id="${tempObjeto.uniqueId}" tabindex="-1" role="dialog" aria-hidden="true">
                  <div class="modal-dialog" role="document">
                    <div class="modal-content">
                      <div class="modal-header no-bd">
                        <h5 class="modal-title">
                          <span class="fw-mediumbold">
                            Editar</span> 
                          <span class="fw-light">
                            Número
                          </span>
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                      <div class="modal-body">
                        <form  action="TelefonoEditServlet" method="post">
                          <div class="row">
                            <div class="col-sm-12">
                              <div class="form-group form-group-default">
                                <label>Id</label>
                                <input name="editId" type="text" class="form-control" value="${tempObjeto.id }" readonly>
                              </div>
                              <div class="form-group form-group-default">
                                <label>Número</label>
                                <input required name="editDescripcion" type="text" class="form-control" value="${tempObjeto.descripcion }">
                              </div>
                              
                              <div class="form-group form-group-default">
                                <label>Persona</label>
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

    <!--   Core JS Files   -->
    <script src="../assets/js/core/jquery.3.2.1.min.js"></script>
    <!-- Datatables -->
    <script src="../assets/js/plugin/datatables/datatables.min.js"></script>
    <script>
      // Add Row
      $('#add-row').DataTable({
        "pageLength": 5,
      });
    </script>

  </jsp:attribute>
</t:template>