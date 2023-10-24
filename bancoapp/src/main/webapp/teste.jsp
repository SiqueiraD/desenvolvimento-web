<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    />
		<style>
		#login{
			padding-top:1em;
		}
		</style>
    <title>Login</title>
  </head>
  <body>
    <nav class="navbar navbar-light bg-light">
      <a class="navbar-brand" href="#">
        <img
          src="/bancoapp/falcao.svg"
          width="30"
          height="30"
          class="d-inline-block align-top"
          alt=""
        />
        Banco Falcão
      </a>
    </nav>
    <div class="container">
      <div class="row">
        <div class="col-12 col-sm-3"></div>
        <div class="col-12 col-s-6">
          <form action="login" method="post" id="login">
            <div class="form-row">
              <div class="col-12">
                <label for="usuarioInput">Nome</label>
                <input
                  type="text"
                  class="form-control"
                  value="Balrog"
                  name="nome"
                  aria-describedby="emailHelp"
                  placeholder="Nome"
                />
              </div>
              <div class="col-12">
                <label for="exampleInputPassword1">Senha</label>
                <input
                  type="password"
                  class="form-control"
                  name="senha"
                  value="1234"
                  placeholder="Password"
                />
                <label
                  style="font-size: large; font-weight: bolder; color: red"
                >
                  <% 
									String erro = (String) request.getAttribute("erros"); 
									if (erro != null) { 
										out.println(erro); 
										out.println("<br />"); 
									}
                  %>
                </label>
              </div>
            </div>
            <div class="col">
              <input type="hidden" name="parametro" value="login" />
              <button type="submit" value="Logar" class="btn btn-primary">
                Entrar
              </button>
            </div>
          </form>
        </div>
      <div class="col-12 col-sm-3"></div>
      </div>
    </div>
  </body>
</html>
