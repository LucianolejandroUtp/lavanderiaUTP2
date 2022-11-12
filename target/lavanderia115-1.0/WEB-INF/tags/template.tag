<%-- 
    Document   : template
    Created on : 12 oct. 2022, 23:36:18
    Author     : desti
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%--<%@taglib prefix="t" tagdir="/WEB-INF/tags/template.tag"%>--%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>
<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true" %>
<%@attribute name="body_area" fragment="true" %>

<%-- any content can be specified here e.g.: --%>
<html>
  <head>
    <jsp:invoke fragment="head_area"/>
    <title>${title}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Atlantis Lite - Bootstrap 4 Admin Dashboard</title>
    <meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport' />
    <link rel="icon" href="../assets/img/icon.ico" type="image/x-icon"/>

    <!-- Fonts and icons -->
    <script src="../assets/js/plugin/webfont/webfont.min.js"></script>
    <script>
      WebFont.load({
        google: {"families": ["Lato:300,400,700,900"]},
        custom: {"families": ["Flaticon", "Font Awesome 5 Solid", "Font Awesome 5 Regular", "Font Awesome 5 Brands", "simple-line-icons"], urls: ['../assets/css/fonts.min.css']},
        active: function () {
          sessionStorage.fonts = true;
        }
      });
    </script>

    <!-- CSS Files -->
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="../assets/css/atlantis.min.css">

  </head>
  <body>


    <div class="wrapper">
      <div class="main-header">

        <!-- Logo Header -->
        <div class="logo-header" data-background-color="blue">

          <a href="index.jsp" class="logo">
            <img src="../assets/img/Lavanderia.svg" alt="navbar brand" class="navbar-brand">
          </a>
          <button class="navbar-toggler sidenav-toggler ml-auto" type="button" data-toggle="collapse" data-target="collapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon">
              <i class="icon-menu"></i>
            </span>
          </button>
          <button class="topbar-toggler more"><i class="icon-options-vertical"></i></button>
          <div class="nav-toggle">
            <button class="btn btn-toggle toggle-sidebar">
              <i class="icon-menu"></i>
            </button>
          </div>
        </div>
        <!-- End Logo Header -->

        <!-- Navbar Header -->
        <nav class="navbar navbar-header navbar-expand-lg" data-background-color="blue2">

          <div class="container-fluid">
            <ul class="navbar-nav topbar-nav ml-md-auto align-items-center">
              <li class="nav-item dropdown hidden-caret">
                <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" aria-expanded="false">
                  <i class="fa icon-user"></i>
                </a>
                <ul class="dropdown-menu dropdown-user animated fadeIn">
                  <div class="dropdown-user-scroll scrollbar-outer">
                    <li>
                      <div class="user-box">
                        <!--<div class="avatar-lg"><img src="assets/img/profile.jpg" alt="image profile" class="avatar-img rounded"></div>-->
                        <div class="u-text">
                          <h4>Hizrian</h4>
                          <p class="text-muted">hello@example.com</p><a href="profile.html" class="btn btn-xs btn-secondary btn-sm">View Profile</a>
                        </div>
                      </div>
                    </li>
                    <li>
                      <div class="dropdown-divider"></div>
                      <a class="dropdown-item" href="#">Logout</a>
                    </li>
                  </div>
                </ul>
              </li>
            </ul>
          </div>
        </nav>
        <!-- End Navbar -->
      </div>

      <!-- Sidebar -->
      <div class="sidebar sidebar-style-2">			
        <div class="sidebar-wrapper scrollbar scrollbar-inner">
          <div class="sidebar-content">
              
              
            <ul class="nav nav-primary">
                
                
                
                
                
              <li class="nav-item">
                <a data-toggle="collapse" href="#submenu">
                  <i class="fas fa-bars"></i>
                  <p>Menu Levels</p>
                  <span class="caret"></span>
                </a>
                <div class="collapse" id="submenu">
                  <ul class="nav nav-collapse">
                    <li>
                      <a data-toggle="collapse" href="#subnav1">
                        <span class="sub-item">Level 1</span>
                        <span class="caret"></span>
                      </a>
                      <div class="collapse" id="subnav1">
                        <ul class="nav nav-collapse subnav">
                          <li>
                            <a href="#">
                              <span class="sub-item">Level 2</span>
                            </a>
                          </li>
                          <li>
                            <a href="#">
                              <span class="sub-item">Level 2</span>
                            </a>
                          </li>
                        </ul>
                      </div>
                    </li>
                    <li>
                      <a data-toggle="collapse" href="#subnav2">
                        <span class="sub-item">Level 1</span>
                        <span class="caret"></span>
                      </a>
                      <div class="collapse" id="subnav2">
                        <ul class="nav nav-collapse subnav">
                          <li>
                            <a href="#">
                              <span class="sub-item">Level 2</span>
                            </a>
                          </li>
                        </ul>
                      </div>
                    </li>
                    <li>
                      <a href="#">
                        <span class="sub-item">Level 1</span>
                      </a>
                    </li>
                  </ul>
                </div>
              </li>



              <li class="nav-item">
                <a data-toggle="collapse" href="#tables">
                  <i class="fas fa-table"></i>
                  <p>Tablas</p>
                  <span class="caret"></span>
                </a>
                <div class="collapse" id="tables">
                  <ul class="nav nav-collapse">
                    <li>
                      <a href="../Distrito/Create.jsp">
                        <span class="sub-item">Distrito</span>
                      </a>
                    </li>
                    <li>
                      <a href="tables/datatables.html">
                        <span class="sub-item">Datatables</span>
                      </a>
                    </li>
                  </ul>
                </div>
              </li>

              
              
              <li class="nav-item">
                <!--<a href="../Distrito/List.jsp">-->
                <a href="../DepartamentoListServlet">
                  <i class="fas fa-map-marked-alt"></i>
                  <p>Departamentos</p>
                  <!--<span class="badge badge-success">4</span>-->
                </a>
              </li>

              <li class="nav-item">
                <a href="../DistritoListServlet">
                  <i class="fas fa-user"></i>
                  <p>-------</p>
                  <!--<span class="badge badge-success">4</span>-->
                </a>
              </li>
              
              <li class="nav-item">
                <!--<a href="../Distrito/List.jsp">-->
                <a href="../DistritoListServlet">
                  <i class="fas fa-car"></i>
                  <p>Conductores</p>
                  <!--<span class="badge badge-success">4</span>-->
                </a>
              </li>
              
              
              <li class="nav-item">
                <!--<a href="../Distrito/List.jsp">-->
                <a href="../DistritoListServlet">
                  <i class="fas fa-clock"></i>
                  <p>Servicios Programados</p>
                  <!--<span class="badge badge-success">4</span>-->
                </a>
              </li>
              
              
              <li class="nav-item">
                <!--<a href="../Distrito/List.jsp">-->
                <a href="../CitaListServlet">
                  <i class="fas fa-handshake"></i>
                  <p>Citas Programadas</p>
                  <!--<span class="badge badge-success">4</span>-->
                </a>
              </li>
              
              
              <li class="nav-item">
                <!--<a href="../Distrito/List.jsp">-->
                <a href="../DistritoListServlet">
                  <i class="fas fa-map-marked-alt"></i>
                  <p>Distritos</p>
                  <!--<span class="badge badge-success">4</span>-->
                </a>
              </li>
              
              <li class="nav-item">
                <!--<a href="../Distrito/List.jsp">-->
                <a href="../VehiculoListServlet">
                  <i class="fas fa-car"></i>
                  <p>Vehiculos</p>
                  <!--<span class="badge badge-success">4</span>-->
                </a>
              </li>
              
<!--<<<<<<< Updated upstream-->
              <li class="nav-item">
                <a href="../TdPListServlet">
                  <i class="fas fa-map-marked-alt"></i>
                  <p>Tipo de Persona</p>
                  <!--<span class="badge badge-success">4</span>-->
                </a>
              </li>
              
              <li class="nav-item">
                <a href="../PersonaListServlet">
                  <i class="fas fa-user"></i>
                  <p>Personas</p>
                  <!--<span class="badge badge-success">4</span>-->
                </a>
              </li>
              
              <li class="nav-item">
                <a href="../CategoriaListServlet">
                  <i class="fas fa-map-marked-alt"></i>
                  <p>Categoría</p>
                  <!--<span class="badge badge-success">4</span>-->
                </a>
              </li>
<!--=======-->
              
              
              
              

<!-->>>>>>> Stashed changes-->
            </ul>
          </div>
        </div>
      </div>
      <!-- End Sidebar -->

      <!--Content DashBoard-->

      <div class="main-panel">
        <div class="content">
          <div class="page-inner">

            <div class="page-category">










              <jsp:invoke fragment="body_area"/>










            </div>
          </div>
        </div>
        <footer class="footer">
          <div class="container-fluid">

            <div class="copyright ml-auto">
              2022-III, made with <i class="fa fa-heart heart text-danger"></i> by <a href="#">Grupo Integrador</a>
            </div>				
          </div>
        </footer>
      </div>
    </div>


    <!--   Core JS Files   -->
    <script src="../assets/js/core/jquery.3.2.1.min.js"></script>
    <script src="../assets/js/core/popper.min.js"></script>
    <script src="../assets/js/core/bootstrap.min.js"></script>
    <!-- jQuery UI -->
    <script src="../assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
    <script src="../assets/js/plugin/jquery-ui-touch-punch/jquery.ui.touch-punch.min.js"></script>
    <!-- jQuery Scrollbar -->
    <script src="../assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
<!--     Chart JS 
    <script src="../assets/js/plugin/chart.js/chart.min.js"></script>
     jQuery Sparkline 
    <script src="../assets/js/plugin/jquery.sparkline/jquery.sparkline.min.js"></script>
     Chart Circle 
    <script src="../assets/js/plugin/chart-circle/circles.min.js"></script>-->
    <!-- Datatables -->
    <script src="../assets/js/plugin/datatables/datatables.min.js"></script>
<!--     Bootstrap Notify 
    <script src="../assets/js/plugin/bootstrap-notify/bootstrap-notify.min.js"></script>
     jQuery Vector Maps 
    <script src="../assets/js/plugin/jqvmap/jquery.vmap.min.js"></script>
    <script src="../assets/js/plugin/jqvmap/maps/jquery.vmap.world.js"></script>
     Sweet Alert 
    <script src="../assets/js/plugin/sweetalert/sweetalert.min.js"></script>-->
    <!-- Atlantis JS -->
    <script src="../assets/js/atlantis.min.js"></script>
  </body>
</html>