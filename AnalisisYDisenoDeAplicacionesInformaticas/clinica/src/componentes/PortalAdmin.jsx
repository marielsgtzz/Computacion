import React, { useState, useContext } from "react";
import { Navigate } from "react-router-dom";
import UserContext from "../context/UserContext";

const PortalAdmin = () => {
  const { userInfo } = useContext(UserContext);
  const [redireccion, setRedireccion] = useState("");

  function cerrarSesion() {
    const confirmed = window.confirm(
      "¿Estás seguro que quieres cerrar sesión?"
    );
    if (confirmed) {
      setRedireccion("CerrarSesion");
    }
  }

  function aCrearCaso() {
    setRedireccion("CrearCaso");
  }

  function aBuscarCaso() {
    setRedireccion("BuscarCaso");
  }

  if (redireccion === "CerrarSesion") {
    return <Navigate to="/" />;
  } else if (redireccion === "CrearCaso") {
    return <Navigate to="/NuevoCaso" />;
  } else if (redireccion === "BuscarCaso") {
    return <Navigate to="/BuscarCaso" />;
  }

  return (
    <>
      <head>
        <link href="CSSGeneral.css" rel="stylesheet" />
      </head>
      <body>
        <header>
          <div className="div_header">
            <h1>Bienvenidx, {userInfo.nombre}</h1>
          </div>
        </header>

        <main>
          <button
            className="inline"
            id="btnCrearCasoPortalAdmin"
            onClick={aCrearCaso}
          >
            Crear caso
          </button>
          <button
            className="inline"
            id="btnBuscarCasoPortalAdmin"
            onClick={aBuscarCaso}
          >
            Buscar caso
          </button>
          <div className="divCerrarSesion">
            <button
              className="cerrar_sesion_button"
              id="btnCerrarSesionPortalAdmin"
              onClick={cerrarSesion}
            >
              Cerrar sesión
            </button>
          </div>

          <p className="mt-5 mb-3 text-body-secondary">
            &copy; Derechos reservados Linces 2023
          </p>
        </main>
      </body>
    </>
  );
};
export default PortalAdmin;
