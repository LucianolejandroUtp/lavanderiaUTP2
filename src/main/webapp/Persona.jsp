<%-- 
    Document   : listPersona
    Created on : 9 nov. 2022, 10:32:06
    Author     : desti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<t:template title="Listar Personas">
  <jsp:attribute name="head_area">
  </jsp:attribute>
  <jsp:attribute name="body_area">



    <div class="col-md-12">
      <div class="card">
        <div class="card-header">
          <div class="d-flex align-items-center">
            <h4 class="card-title">Personas</h4>
            <button class="btn btn-primary btn-round ml-auto" data-toggle="modal" data-target="#addRowModal">
              <i class="fa fa-plus"></i>
              Añadir Persona
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
                      Persona
                    </span>
                  </h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <form  action="PersonaCreateServlet" method="post">
                    <div class="row">
                      <div class="col-sm-12">
                        <div class="form-group form-group-default">
                          <label>Nombres</label>
                          <input required name="addNombres" id="addNombres" type="text" class="form-control" placeholder="Llene">
                        </div>
                        <div class="form-group form-group-default">
                          <label>Apellidos</label>
                          <input required name="addApellidos" id="addApellidos" type="text" class="form-control" placeholder="Llene">
                        </div>
                        <div class="form-group form-group-default">
                          <label>DNI</label>
                          <input required name="addDni" id="addDni" type="text" class="form-control" placeholder="Llene">
                        </div>
                        <div class="form-group">
                          <label>Email</label>
                          <input required name="addEmail" id="addEmail" type="email" class="form-control" placeholder="Llene">
                        </div>
                        <div class="form-group">
                          <label>Password</label>
                          <input required name="addPassword" id="addPassword" type="password" class="form-control" placeholder="Llene">
                        </div>

                        <div class="form-group form-group-default">
                          <label>Tipo de Persona</label>
                          <select class="form-control" name="addTdPersonaId">
                            <c:forEach var="tempObjetoCreate" items="${mi_lista_de_TdP }">
                              <option value="${tempObjetoCreate.id }">${tempObjetoCreate.descripcion }</option>
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
            <table id="add-row" class="display table table-striped table-hover" cellspacing="0" width="100%">
              <thead>
                <tr>
                  <th>Nombres</th>
                  <th>Apellidos</th>
                  <th>DNI</th>
                  <th>Email</th>
                  <th>Password</th>
                  <th>Estado</th>
                  <th>Tipo de Persona</th>
                  <th>Creado</th>
                  <th>Modificado</th>
                  <th style="width: 10%">Acciones</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="tempObjeto" items="${mi_lista_de_personas }">
                  <tr>
                    <td>${tempObjeto.nombres }</td>
                    <td>${tempObjeto.apellidos}</td>
                    <td>${tempObjeto.dni}</td>
                    <td>${tempObjeto.email}</td>
                    <td>******</td>
                    <td>${tempObjeto.estado}</td>
                    <td>${tempObjeto.tipoPersonaId.descripcion}</td>
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
                          <span class="fw-light">esta Persona?</span>
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                      <div class="modal-body">
                        <form  action="PersonaDestroyServlet" method="post">
                          <div class="row">
                            <div class="col-sm-12">
                              <div class="form-group form-group-default">
                                <label>Id</label>
                                <input name="destroyId" type="text" class="form-control" value="${tempObjeto.id }" readonly>
                              </div>
                              <div class="form-group form-group-default">
                                <label>Nombres</label>
                                <input type="text" class="form-control" value="${tempObjeto.nombres }" readonly>
                              </div>
                              <div class="form-group form-group-default">
                                <label>Apellidos</label>
                                <input type="text" class="form-control" value="${tempObjeto.apellidos }" readonly>
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
                            Persona
                          </span>
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                      <div class="modal-body">
                        <form  action="PersonaEditServlet" method="post">
                          <div class="row">
                            <div class="col-sm-12">
                              <div class="form-group form-group-default">
                                <label>Id</label>
                                <input name="editId" type="text" class="form-control" value="${tempObjeto.id }" readonly>
                              </div>
                              <div class="form-group form-group-default">
                                <label>Nombres</label>
                                <input required name="editNombres" type="text" class="form-control" value="${tempObjeto.nombres }">
                              </div>
                              <div class="form-group form-group-default">
                                <label>Apellidos</label>
                                <input required name="editApellidos" type="text" class="form-control" value="${tempObjeto.apellidos }">
                              </div>
                              <div class="form-group form-group-default">
                                <label>DNI</label>
                                <input required name="editDni" type="text" class="form-control" value="${tempObjeto.dni }">
                              </div>
                              <div class="form-group form-group-default">
                                <label>Email</label>
                                <input required name="editEmail" type="text" class="form-control" value="${tempObjeto.email }">
                              </div>
                              <div class="form-group form-group-default">
                                <label>Password</label>
                                <input required name="editPassword" type="text" class="form-control" value="${tempObjeto.password }">
                              </div>
                              <div class="form-group form-group-default">
                                <label>Tipo de Persona</label>
                                <select class="form-control" name="editTdPersonaId">
                                  <c:forEach var="tempObjetoEdit" items="${mi_lista_de_TdP }">
                                    <option value="${tempObjetoEdit.id }">${tempObjetoEdit.descripcion }</option>
                                  </c:forEach>
                                </select>
                              </div>
                              <div class="form-group form-group-default">
                                <label>Estado</label>
                                <select class="form-control" name="editEstado">
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