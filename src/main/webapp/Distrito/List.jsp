<%-- 
    Document   : Listar
    Created on : 22 oct. 2022, 01:34:37
    Author     : desti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@page import="com.servlets.DistritoCreateServlet" %>
<%@page import="com.servlets.DistritoListServlet" %>

<t:template title="Listar Distritos">
  <jsp:attribute name="head_area">

  </jsp:attribute>
  <jsp:attribute name="body_area">


    <div>
      <jsp:include page="../DistritoListServlet" />
    </div>
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
          <!-- Modal -->
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
                  <div class ="row">
                    <div class="col-md-12">
                      <form action="../DistritoCreateServlet" method="post">
                        <div class="form-group form-inline">
                          <div class="form-group form-inline">
                            <label for="inlineinput" class="col-md-3 col-form-label">Nombre</label>
                            <div class="col-md-9 p-0">
                              <input name="distrito2" type="text" class="form-control input-full" id="inlineinput" placeholder="Ingresa un distrito">
                            </div>
                          </div>
                          <div class="col-md-3 col-form-label">
                            <button type="submit" class="btn btn-primary">Guardar</button>
                          </div>
                        </div>
                      </form>
                    </div>
                  </div>

                  <p class="small">Crear un nuevo distrito</p>
                  <form  action="../DistritoCreateServlet" method="post">
                    <div class="row">
                      <div class="col-sm-12">
                        <div class="form-group form-group-default">
                          <label>Nombre</label>
                          <input name="distrito" id="addName" type="text" class="form-control" placeholder="llene el distrito">
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
                  <th>Name</th>
                  <th>Position</th>
                  <th>Office</th>
                  <th style="width: 10%">Action</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Tiger Nixon</td>
                  <td>System Architect</td>
                  <td>Edinburgh</td>
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
                <tr>
                  <td>Garrett Winters</td>
                  <td>Accountant</td>
                  <td>Tokyo</td>
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
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>


  </jsp:attribute>
</t:template>