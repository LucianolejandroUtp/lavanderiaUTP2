<%-- 
    Document   : Create
    Created on : 21 oct. 2022, 20:33:02
    Author     : desti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@page import="com.servlets.DistritoCreateServlet" %>

<t:template title="Crear Distrito">
    <jsp:attribute name="head_area">

    </jsp:attribute>
    <jsp:attribute name="body_area">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title">Basic</h4>
                    </div>
                    <div class="card-body">
                        <div class ="row">
                            <div class="col-md-12">
                                <form action="../DistritoCreateServlet" method="post">
                                    <div class="form-group form-inline">

                                        <div class="form-group form-inline">
                                            <label for="inlineinput" class="col-md-3 col-form-label">Nombre</label>
                                            <div class="col-md-9 p-0">
                                                <input name="distrito" type="text" class="form-control input-full" id="inlineinput" placeholder="Ingresa un distrito">
                                            </div>
                                        </div>
                                        <div class="col-md-3 col-form-label">
                                            <button type="submit" class="btn btn-primary">Guardar</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</t:template>
