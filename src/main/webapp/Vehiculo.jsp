<%-- 
    Document   : Vehiculo
    Created on : 11 nov. 2022, 11:11:19
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<t:template title="Listar Vehiculo">
  <jsp:attribute name="head_area">
  </jsp:attribute>
  <jsp:attribute name="body_area">


    <div class="col-md-12">
      <div class="card">
        <div class="card-header">
          <div class="d-flex align-items-center">
            <h4 class="card-title">Vehiculos</h4>
            <button class="btn btn-primary btn-round ml-auto" data-toggle="modal" data-target="#addRowModal">
              <i class="fa fa-plus"></i>
              Añadir Vehículo
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
                      Vehículo
                    </span>
                  </h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <form  action="VehiculoCreateServlet" method="post">
                    <div class="row">
                      <div class="col-sm-12">
                        <div class="form-group form-group-default">
                          <label>Placa</label>
                          <input required name="addPlaca" type="text" class="form-control" placeholder="Ingrese placa vehiculo">
                        </div>
                        <div class="form-group form-group-default">
                          <label>Marca</label>
                          <input required name="addMarca" type="text" class="form-control" placeholder="Ingrese Marca del vehiculo">
                        </div>
                        <div class="form-group form-group-default">
                          <label>Modelo</label>
                          <input required name="addModelo" type="text" class="form-control" placeholder="Ingrese el modelo del vehiculo">
                        </div>

                        <div class="form-group form-group-default">
                          <label>Nombre Conductor</label>
                          <select class="form-control" name="addNombreId">
                            <c:forEach var="tempObjPersona" items="${mi_lista_de_personas }">
                              <c:if test="${tempObjPersona.tipoPersonaId.descripcion.equalsIgnoreCase('chofer')}">  
                                <option value="${tempObjPersona.id}">${tempObjPersona.nombres} - ${tempObjPersona.apellidos}</option>
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
                  <th>Placa</th>
                  <th>Marca</th>
                  <th>Modelo</th>
                  <th>Estado</th>
                  <th>Nombre Conductor</th>
                  <th>Apellidos Conductor</th>
                  <th>Creado</th>
                  <th>Modificado</th>
                  <th style="width: 10%">Acciones</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="tempObjeto" items="${mi_lista_de_vehiculos }">
                  <tr>
                    <td>${tempObjeto.placa }</td>
                    <td>${tempObjeto.marca}</td>
                    <td>${tempObjeto.modelo}</td>
                    <td>${tempObjeto.estado}</td>
                    <td>${tempObjeto.personaId.nombres}</td>
                    <td>${tempObjeto.personaId.apellidos}</td>

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
                          <span class="fw-light">este Vehículo?</span>
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                      <div class="modal-body">
                        <form  action="VehiculoDestroyServlet" method="post">
                          <div class="row">
                            <div class="col-sm-12">
                              <div class="form-group form-group-default">
                                <label>Id</label>
                                <input name="destroyId" type="text" class="form-control" value="${tempObjeto.id }" readonly>
                              </div>
                              <div class="form-group form-group-default">
                                <label>Placa</label>
                                <input type="text" class="form-control" value="${tempObjeto.placa }" readonly>
                              </div>
                              <div class="form-group form-group-default">
                                <label>Marca</label>
                                <input type="text" class="form-control" value="${tempObjeto.marca }" readonly>
                              </div>
                              <div class="form-group form-group-default">
                                <label>Modelo</label>
                                <input type="text" class="form-control" value="${tempObjeto.modelo }" readonly>
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
                            Vehículo
                          </span>
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                      <div class="modal-body">
                        <form  action="VehiculoEditServlet" method="post">
                          <div class="row">
                            <div class="col-sm-12">
                              <div class="form-group form-group-default">
                                <label>Id</label>
                                <input name="editId" type="text" class="form-control" value="${tempObjeto.id }" readonly>
                              </div>
                              <div class="form-group form-group-default">
                                <label>Placa</label>
                                <input required name="editPlaca" type="text" class="form-control" value="${tempObjeto.placa }">
                              </div>
                              <div class="form-group form-group-default">
                                <label>Marca</label>
                                <input required name="editMarca" type="text" class="form-control" value="${tempObjeto.marca }">
                              </div>
                              <div class="form-group form-group-default">
                                <label>Modelo</label>
                                <input required name="editModelo" type="text" class="form-control" value="${tempObjeto.modelo }">
                              </div>
                              <div class="form-group form-group-default">
                                <label>Chofer</label>
                                <select class="form-control" name="editPersonaId">
                                  <c:forEach var="tempObj2" items="${mi_lista_de_personas }">
                                    <c:if test="${tempObj2.tipoPersonaId.descripcion.equalsIgnoreCase('chofer')}">  
                                      <option value="${tempObj2.id}">${tempObj2.nombres} - ${tempObj2.apellidos}</option>
                                    </c:if>
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
