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
                      Nuevo</span> 
                    <span class="fw-light">
                      Citas
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
                          <input required name="addFecha" id="addFecha" type="text" class="form-control" placeholder="Ingrese Fecha en recojo">
                        </div>
                        <div class="form-group form-group-default">
                          <label>Hora Cita HH:MM:ss</label>
                          <input required name="addHora" id="addHora" type="text" class="form-control" placeholder="Ingrese hora de recojo">
                        </div>

                        <div class="form-group form-group-default">
                          <label>Placa Vehiculos</label>
                          <select class="form-control" name="addPlacaId" id="addPlacaId">
                            <c:forEach var="tempObjetoDepa" items="${mi_lista_de_vehiculos }">

                              <option value="${tempObjetoDepa.id}">${tempObjetoDepa.placa }</option>

                            </c:forEach>

                          </select>
                        </div>

                        <div class="form-group form-group-default">
                          <label>Nombre Cliente</label>
                          <select class="form-control" name="addNombreId" id="addNombreId">
                            <c:forEach var="tempObjetoDepa" items="${mi_lista_de_personas }">
                              <c:if test="${tempObjetoDepa.tipoPersonaId.descripcion.equals('Cliente')}">  
                                <option value="${tempObjetoDepa.id}">${tempObjetoDepa.nombres }</option>
                              </c:if>
                              <c:if test="${tempObjetoDepa.tipoPersonaId.descripcion.equals('cliente')}">  
                                <option value="${tempObjetoDepa.id}">${tempObjetoDepa.nombres }</option>
                              </c:if>     

                            </c:forEach>

                          </select>
                        </div>

                        <div class="form-group form-group-default">
                          <label>Apellido Cliente</label>
                          <select class="form-control" name="addApellidoId" id="addApellidoId">
                            <c:forEach var="tempObjetoDepa" items="${mi_lista_de_personas }">
                              <c:if test="${tempObjetoDepa.tipoPersonaId.descripcion.equals('Cliente')}">  
                                <option value="${tempObjetoDepa.id}">${tempObjetoDepa.apellidos }</option>
                              </c:if>
                              <c:if test="${tempObjetoDepa.tipoPersonaId.descripcion.equals('cliente')}">  
                                <option value="${tempObjetoDepa.id}">${tempObjetoDepa.apellidos }</option>
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
                        <form  action="DistritoDestroyServlet" method="post">
                          <div class="row">
                            <div class="col-sm-12">
                              <div class="form-group form-group-default">
                                <label>Id</label>
                                <input name="destroyId" id="destroyId" type="text" class="form-control" value="${tempObjeto.id }" readonly>
                              </div>
                              <div class="form-group form-group-default">
                                <label>Nombre</label>
                                <input name="destroy_objeto" id="destroy_distrito_departamento" type="text" class="form-control" value="${tempObjeto.estado }" readonly>
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
                        <form  action="DistritoEditServlet" method="post">
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
                                <label>Select</label>

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

