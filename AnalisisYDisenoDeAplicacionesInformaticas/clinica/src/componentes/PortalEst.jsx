import React, { useState, useContext } from "react";
import { Navigate } from "react-router-dom";
import UserContext from "../context/UserContext";
import CasosContext from "../context/CasoContext";
import "../css/CSSGeneral.css";
import { useEffect } from "react";

const PortalEst = () => {
  const { userInfo } = useContext(UserContext);
  const { casos } = useContext(CasosContext);

  const [redireccion, setRedireccion] = useState("");

  function cerrarSesion() {
    const confirmed = window.confirm(
      "¿Estás seguro que quieres cerrar sesión?"
    );
    if (confirmed) {
      setRedireccion("CerrarSesion");
    }
  }

  const showResultados = () => {
    const listaTareas = [];
    for (let i = 0; i < casos.length; i++) {
      for (let j = 0; j < casos[i].tareas.length; j++) {
        if (casos[i].tareas[j].estudiante === userInfo.nombre) {
          listaTareas.push(<li>{casos[i].tareas[j].nota}</li>);
        }
      }
    }

    if (listaTareas.length === 0) {
      return "No hay tareas";
    } else {
      return listaTareas;
    }
    //return listaTareas;
  };

  function aBuscarCaso() {
    setRedireccion("BuscarCaso");
  }

  if (redireccion === "CerrarSesion") {
    return <Navigate to="/" />;
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
          <div>
            <button id="btnBuscarCasoPortalEstudiante" onClick={aBuscarCaso}>
              Buscar caso
            </button>
          </div>
          <h2>Lista de Tareas</h2>

          <div className="divLista">
            <ul id="listaTareasPortalEstudiante">{showResultados()}</ul>
          </div>

          <div className="divCerrarSesion">
            <button
              className="cerrar_sesion_button"
              id="btnCerrarSesionPortalEstudiante"
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
export default PortalEst;
