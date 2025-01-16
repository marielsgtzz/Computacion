import React, { useState, useContext } from "react";
import { Navigate } from "react-router-dom";
import UserContext from "../context/UserContext";
import logoClinica from "../img/logoClinica.png";

function Login() {
  const [usuarix, setUsuarix] = useState("");
  const [tipoU, setTipoUsuarix] = useState("");

  const { setUserInfo } = useContext(UserContext);

  function redirrecion(event) {
    event.preventDefault();
    const valorUsuarix = event.target.elements.username.value;
    setUsuarix(valorUsuarix);

    let tipo;
    if (valorUsuarix === "Jessica") {
      setTipoUsuarix("Admin");
      tipo = "Admin";
      setUsuarix("Jessica");
    } else if (
      valorUsuarix === "Mike" ||
      valorUsuarix === "Rachel" ||
      valorUsuarix === "Harold"
    ) {
      setTipoUsuarix("Est");
      tipo = "Est";
      setUsuarix("Mike");
    } else {
    }

    setUserInfo({
      nombre: `${valorUsuarix}`,
      tipo: `${tipo}`,
    });
  }

  if (tipoU === "Admin") {
    return <Navigate to={`/PortalAdmin/${usuarix}`} />;
  } else if (tipoU === "Est") {
    return <Navigate to={`/PortalEst/${usuarix}`} />;
  }

  return (
    <>
      <head>
        <link
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
          crossorigin="anonymous"
        />
        <link href="bootstrap.min.css" rel="stylesheet" />
        <link href="CSSGeneral.css" rel="stylesheet" />
      </head>
      <body>
        <img class="imgIndex" src={logoClinica} alt="Logo de la clinica" />
        <h1>Inicio de sesión</h1>
        <main className="form-signin">
          <form onSubmit={redirrecion}>
            <div className="form-floating">
              <input
                type="text"
                id="username"
                className="form-control"
                placeholder="Nombre de usuario"
                name="username"
                required
              />
              <br />
              <label htmlFor="username">Nombre de usuario:</label>
            </div>

            <div className="extra"></div>

            <div className="form-floating">
              <input
                type="password"
                id="password"
                className="form-control"
                placeholder="Contraseña"
                name="contraseña"
                required
              />
              <br />
              <label htmlFor="password">Contraseña:</label>
            </div>

            <div className="div">
              <input type="submit" className="button" value="Iniciar sesión" />
            </div>
            <p className="mt-5 mb-3 text-body-secondary">
              &copy; Derechos reservados Linces 2023
            </p>
          </form>
        </main>
      </body>
    </>
  );
}

export default Login;
