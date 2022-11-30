<%-- 
    Document   : Cita
    Created on : 11 nov. 2022, 21:51:04
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<t:template title="Listar Citas">
  <jsp:attribute name="head_area">
  </jsp:attribute>
  <jsp:attribute name="body_area">


    <div class="col-md-12">
      <div class="card">
        <div class="card-header">
          <div class="d-flex align-items-center">
            <h4 class="card-title">Citas Programadas</h4>
            <button class="btn btn-primary btn-round ml-auto" data-toggle="modal" data-target="#addRowModal">
              <i class="fa fa-plus"></i>
              Añadir Nueva Cita
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
                      Cita
                    </span>
                  </h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <form  action="CitaCreateServlet" method="post">
                    <div class="row">
                      <div class="col-sm-12">
                        <div class="form-group form-group-default">
                          <label>Fecha Cita  AA-MM-DD </label>
                          <input required name="addFecha" type="date" class="form-control">
                        </div>
                        <div class="form-group form-group-default">
                          <label>Hora Cita HH:MM:ss</label>
                          <input required name="addHora" type="time" class="form-control">
                        </div>

                        <div class="form-group form-group-default">
                          <label>Placa Vehiculos</label>
                          <select class="form-control" name="addPlacaId">
                            <c:forEach var="tempObjAdd" items="${mi_lista_de_vehiculos }">
                              <option value="${tempObjAdd.id}">${tempObjAdd.placa }</option>
                            </c:forEach>
                          </select>
                        </div>
                        <div class="form-group form-group-default">
                          <label>Cliente</label>
                          <select class="form-control" name="addNombreId">
                            <c:forEach var="tempObjAdd" items="${mi_lista_de_personas}">
                              <c:if test="${tempObjAdd.tipoPersonaId.descripcion.equalsIgnoreCase('Cliente')}">  
                                <option value="${tempObjAdd.id}">${tempObjAdd.nombres} - ${tempObjAdd.apellidos}</option>
                              </c:if>
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
                  <th>Fecha Cita</th>
                  <th>Hora Programada</th>
                  <th>Estado</th>
                  <th>Nombres</th>
                  <th>Apellidos</th>
                  <th>Placa</th>
                  <th>Creado</th>
                  <th>Modificado</th>
                  <th style="width: 10%">Acciones</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="tempObjeto" items="${mi_lista_de_citas }">
                  <tr>
                    <td>${tempObjeto.fecha }</td>
                    <td>${tempObjeto.hora}</td>
                    <td>${tempObjeto.estado}</td>
                    <td>${tempObjeto.personaId.nombres}</td>
                    <td>${tempObjeto.personaId.apellidos}</td>
                    <td>${tempObjeto.vehiculoId.placa}</td>
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
                          <span class="fw-light">esta Cita?</span>
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                      <div class="modal-body">
                        <form  action="CitaDestroyServlet" method="post">
                          <div class="row">
                            <div class="col-sm-12">
                              <div class="form-group form-group-default">
                                <label>Id</label>
                                <input name="destroyId" type="text" class="form-control" value="${tempObjeto.id }" readonly>
                              </div>
                              <div class="form-group form-group-default">
                                <label>Persona</label>
                                <input type="text" class="form-control" value="${tempObjeto.personaId.nombres} - ${tempObjeto.personaId.apellidos}" readonly>
                              </div>
                              <div class="form-group form-group-default">
                                <label>Vehículo</label>
                                <input type="text" class="form-control" value="${tempObjeto.vehiculoId.placa} - ${tempObjeto.vehiculoId.marca} - ${tempObjeto.vehiculoId.modelo}" readonly>
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
                            Cita
                          </span>
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                      <div class="modal-body">
                        <form  action="CitaEditServlet" method="post">
                          <div class="row">
                            <div class="col-sm-12">
                              <div class="form-group form-group-default">
                                <label>Id</label>
                                <input name="editId" id="editId" type="text" class="form-control" value="${tempObjeto.id }" readonly>
                              </div>
                              <div class="form-group form-group-default">
                                <label>Nombre</label>
                                <!-- value="tempObjeto.estado" determina si lista todo o solo el primero, y permitira abrir el modal +AñadirALGO -->
                                <input required name="editDescripcion" id="editDescripcion" type="text" class="form-control" value="${tempObjeto.estado }">
                              </div>
                              <div class="form-group form-group-default">
                                <label>Departamento</label>
                                <select class="form-control" name="editDepaId" id="editDepaId">
                                  <c:forEach var="tempObjetoDepa" items="${mi_lista_de_personas }">
                                    <option value="${tempObjetoDepa.id }">${tempObjetoDepa.nombres }</option>
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

