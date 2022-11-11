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
              Añadir Vehiculo
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
                      Vehiculo
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
                          <label>Placa</label>
                          <input name="addDescripcion" id="addName" type="text" class="form-control" placeholder="Ingrese la placa del vehiculo">
                        </div>
                          <div class="form-group form-group-default">
                          <label>Marca</label>
                          <input name="addDescripcion" id="addName" type="text" class="form-control" placeholder="Ingrese la Marca del vehiculo">
                        </div><!-- comment -->
                        <div class="form-group form-group-default">
                          <label>Modelo</label>
                          <input name="addDescripcion" id="addName" type="text" class="form-control" placeholder="Ingrese el Modelo del vehiculo">
                        </div>
                        <div class="form-group form-group-default">
                          <label>Nombre Conductor</label>
                          <input name="addDescripcion" id="addName" type="text" class="form-control" placeholder="Nombre del Conductor asignado">
                        </div>
                        <div class="form-group form-group-default">
                          <label>Apellido Conductor</label>
                          <input name="addDescripcion" id="addName" type="text" class="form-control" placeholder="Apellido del Conductor asignado">
                        </div>
                        

                        <div class="form-group form-group-default">
                          <label>Nombre Conductor</label>
                          <select class="form-control" name="addDepartamentoId" id="addDepartamentoId">
                            <c:forEach var="tempObjetoDepa" items="${mi_lista_de_personas }">
                              <option value="${tempObjetoDepa.id }">${tempObjetoDepa.descripcion }</option>
                            </c:forEach>

                          </select>
                        </div>
                        
                         <div class="form-group form-group-default">
                          <label>Apellido Conductor</label>
                          <select class="form-control" name="addDepartamentoId" id="addDepartamentoId">
                            <c:forEach var="tempObjetoDepa" items="${mi_lista_de_personas }">
                              <option value="${tempObjetoDepa.id }">${tempObjetoDepa.descripcion }</option>
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
                  <th>Nombre Persona</th>
                  <th>Apellidos Persona</th>
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
                          <span class="fw-light">esta persona?</span>
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
                                <input name="editDescripcion" id="editDescripcion" type="text" class="form-control" value="${tempObjeto.descripcion }">
                              </div>
                              <div class="form-group form-group-default">
                                <label>Departamento</label>
                                <select class="form-control" name="editDepaId" id="editDepaId">
                                  <c:forEach var="tempObjetoDepa" items="${mi_lista_de_personas }">
                                    <option value="${tempObjetoDepa.id }">${tempObjetoDepa.descripcion }</option>
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
