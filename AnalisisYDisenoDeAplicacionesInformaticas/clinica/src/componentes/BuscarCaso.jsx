import React, { useState, useContext, useEffect } from "react";
import { Link, Navigate } from "react-router-dom";
import UserContext from "../context/UserContext";
import CasosContext from "../context/CasoContext";
import "../css/CSSGeneral.css";

const BuscarCaso = () => {
  //Contextos
  const { userInfo } = useContext(UserContext);
  const { casos } = useContext(CasosContext);

  //Estados para manejar la redirección de páginas
  const [usuarix, setUsuarix] = useState("");
  const [tipo, setTipo] = useState("");
  const [redireccion, setRedireccion] = useState(false);

  //Estados para filtrar
  const [filtroNombre, setFiltroNombre] = useState("");
  const [filtroTipo, setFiltroTipo] = useState("");
  const [filtroIniciales, setFiltroIniciales] = useState("");
  const [filtroEstado, setFiltroEstado] = useState("");

  //Función para filtrar
  const showResultados = () => {
    const listaResultados = [];

    for (let i = 0; i < casos.length; i++) {
      if (
        filtroNombre === "" ||
        casos[i].nombre.toUpperCase().includes(filtroNombre.toUpperCase())
      ) {
        if (filtroTipo === "" || filtroTipo === casos[i].tipoDeCaso) {
          if (filtroEstado === "" || filtroEstado === casos[i].estado) {
            listaResultados.push(
              <Link to={`/DetallesCaso/${casos[i].iniciales}`}>
                <div class="grid-item">{casos[i].nombre}</div>
              </Link>
            );
          }
        }
      }
    }

    return listaResultados;
  };

  //cambiarlo a algo onClick
  useEffect(() => {
    showResultados();
  }, [filtroEstado, filtroIniciales, filtroTipo]);

  useEffect(() => {
    setUsuarix(userInfo.nombre);
  });

  function aPortal() {
    setRedireccion(true);
    setUsuarix(userInfo.name);
    setTipo(userInfo.tipo);
  }

  // if (redireccion) {
  //   return <Navigate to={`/Portal${tipo}/${usuarix}`} />;
  // }

  return (
    <>
      <head>
        <link href="../css/CSSGeneral.css" rel="stylesheet" />
      </head>
      <body>
        <h2>Buscar Caso</h2>
        <div>
          <form>
            <input
              type="text"
              class="textBoxBuscar"
              name="search"
              placeholder="Buscar..."
              onChange={(e) => setFiltroNombre(e.target.value)}
            />
            <button type="submit">Resetear</button>
          </form>
        </div>
        <br />
        <div>
          <label for="tipoDelito">Tipo Delito:</label>
          <select
            class="selectsBuscar"
            id="tipoDelitoBusquedaCaso"
            name="tipoDelito"
            onChange={(e) => setFiltroTipo(e.target.value)}
          >
            <option value=""></option>
            <option value="ViolenciaDomestica">Violencia doméstica</option>
            <option value="Discriminacion">Discriminación</option>
            <option value="Acoso">Acoso</option>
          </select>
          {/* <label for="nombreIn">Nombre/iniciales:</label>
          <select
            class="selectsBuscar"
            id="nombreInBusquedaCaso"
            name="nombreIn"
            onChange={(e) => setFiltroIniciales(e.target.value)}
          >
            <option value=""></option>
            <option value="opcion1">Opcion 1</option>
            <option value="opcion2">Opcion 2</option>
            <option value="opcion3">Opcion 3</option>
          </select> */}
          <label for="estado">Estado:</label>
          <select
            class="selectsBuscar"
            id="estadoBusquedaCaso"
            name="estado"
            onChange={(e) => setFiltroEstado(e.target.value)}
          >
            <option value=""></option>
            <option value="InvestigacionAbogadxs">
              Investigación abogadxs
            </option>
            <option value="SinPresentar">Sin presentar</option>
            <option value="InicioDemanda">Inicio de la demanda</option>
            <option value="InvestigacionTribunales">
              Investigación tribunales
            </option>
          </select>
        </div>
        <br />
        <div class="grid-container">
          <h2>Lista de resultados:</h2>
          {showResultados()}
        </div>
        <br />
        <div class="divCerrarSesion">
          <Link
            to={
              userInfo.tipo === "Est"
                ? `/PortalEst/${userInfo.nombre}`
                : `/PortalAdmin/${userInfo.nombre}`
            }
          >
            <button class="cerrar_sesion_button" onClick={aPortal}>
              Regresar
            </button>
          </Link>
        </div>

        <p class="mt-5 mb-3 text-body-secondary">
          &copy; Derechos reservados Linces 2023
        </p>
      </body>
    </>
  );
};

export default BuscarCaso;
