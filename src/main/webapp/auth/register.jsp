<%-- 
    Document   : register
    Created on : 24 set. 2022, 04:55:41
    Author     : desti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>LavanderíaUTP | Página de registro</title>

    <!-- Google Font: Source Sans Pro -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="../assets/auth/plugins/fontawesome-free/css/all.min.css">
    <!-- icheck bootstrap -->
    <link rel="stylesheet" href="../assets/auth/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="../assets/auth/dist/css/adminlte.min.css">
  </head>
  <body class="hold-transition register-page">
    <div class="register-box">
<!--      <div class="register-logo">
        <a href="#">Lavandería<b>UTP</b></a>
      </div>-->

      <div class="card">
        <div class="card-header text-center" style="background-color: cornflowerblue;">
          <a href="../index.jsp" class="logo">
            <img src="../assets/img/Lavanderia.svg" alt="navbar brand" class="navbar-brand">
          </a>
        </div>
        <div class="card-body register-card-body">
          <p class="login-box-msg">Registrar un nuevo miembro</p>

          <form action="../PersonaCreateServlet" method="post">
            <div class="input-group mb-3">
              <input required name="addNombres" type="text" class="form-control" placeholder="Nombres">
              <div class="input-group-append">
                <div class="input-group-text">
                  <span class="fas fa-user"></span>
                </div>
              </div>
            </div>
            <div class="input-group mb-3">
              <input required name="addEmail" type="email" class="form-control" placeholder="Email">
              <div class="input-group-append">
                <div class="input-group-text">
                  <span class="fas fa-envelope"></span>
                </div>
              </div>
            </div>
            <div class="input-group mb-3">
              <input required name="addPassword" type="password" class="form-control" placeholder="Contraseña">
              <div class="input-group-append">
                <div class="input-group-text">
                  <span class="fas fa-lock"></span>
                </div>
              </div>
            </div>
            <div class="input-group mb-3">
              <input type="password" class="form-control" placeholder="Repita la contraseña">
              <div class="input-group-append">
                <div class="input-group-text">
                  <span class="fas fa-lock"></span>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-8">
                <div class="icheck-primary">
                  <input type="checkbox" id="agreeTerms" name="terms" value="agree">
                  <label for="agreeTerms">
                    Acepto los <a href="#">términos y condiciones</a>
                  </label>
                </div>
              </div>
              <!-- /.col -->
              <div class="col-4">
                <button type="submit" class="btn btn-primary btn-block">Registrar</button>
              </div>
              <!-- /.col -->
            </div>
          </form>


          <a href="login.jsp" class="text-center">Ya tengo una cuenta</a>
        </div>
        <!-- /.form-box -->
      </div><!-- /.card -->
    </div>
    <!-- /.register-box -->

    <!-- jQuery -->
    <script src="../assets/auth/plugins/jquery/jquery.min.js"></script>
    <!-- Bootstrap 4 -->
    <script src="../assets/auth/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- AdminLTE App -->
    <script src="../assets/auth/dist/js/adminlte.min.js"></script>
  </body>
</html>
