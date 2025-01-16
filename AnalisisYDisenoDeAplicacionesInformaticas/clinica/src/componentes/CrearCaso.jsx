import React, { useState, useContext } from "react";
import { Link, Navigate } from "react-router-dom";
import UserContext from "../context/UserContext";
import CasosContext from "../context/CasoContext";
import "../css/CSSGeneral.css";
import { useEffect } from "react";

const CrearCaso = () => {
  //Contextos
  const { userInfo } = useContext(UserContext);
  const { casos, setCasos } = useContext(CasosContext);

  //Estados para manejar la redirección de páginas
  const [usuarix, setUsuarix] = useState("");
  const [redireccion, setRedireccion] = useState("");

  //Estados (atributos) para creación de caso
  const [nombreCaso, setNombreCaso] = useState("");
  const [iniciales, setIniciales] = useState("");
  const [descripcionCaso, setDescripcionCaso] = useState("");
  const [tipoCaso, setTipoCaso] = useState("");
  const [estado, setEstado] = useState("");
  const [fecha, setFecha] = useState("");

  //Función para crear caso
  const createCasoNuevo = () => {
    if (
      nombreCaso !== "" &&
      iniciales !== "" &&
      descripcionCaso !== "" &&
      tipoCaso !== "" &&
      estado !== "" &&
      fecha !== ""
    ) {
      setRedireccion("CrearCaso");
      if (casos[0].nombre === "") {
        setCasos([
          {
            nombre: nombreCaso,
            iniciales: iniciales,
            descripcionDelCaso: descripcionCaso,
            tipoDeCaso: tipoCaso,
            estado: estado,
            fecha: fecha,
            tareas: [],
          },
        ]);
      } else {
        const newCasos = casos;
        newCasos.push({
          nombre: nombreCaso,
          iniciales: iniciales,
          descripcionDelCaso: descripcionCaso,
          tipoDeCaso: tipoCaso,
          estado: estado,
          fecha: fecha,
          tareas: [],
        });
        setCasos(newCasos);
      }
    } else {
      alert("Favor de llenar todos los campos del caso.");
    }
  };

  const aPortal = () => {
    // setUsuarix(userInfo.name);
    // setRedireccion("RegresarPortal");
  };

  useEffect(() => {
    setUsuarix(userInfo.nombre);
  });

  if (redireccion === "RegresarPortal") {
    // return <Navigate to={`/PortalAdmin/${usuarix}`} />;
  } else if (redireccion === "CrearCaso") {
    return <Navigate to={`/DetallesCaso/${iniciales}`} />;
  }

  return (
    <>
      <body>
        <h2>Crear Caso</h2>

        <form className="formCrearCaso">
          <div>
            <label htmlFor="nombre">Nombre:</label>
          </div>
          <div>
            <input
              type="text"
              className="selectsCrear"
              id="nombreCrearCaso"
              name="nombre"
              onChange={(e) => setNombreCaso(e.target.value)}
            />
          </div>

          <div>
            <label htmlFor="iniciales">Iniciales:</label>
          </div>
          <div>
            <input
              type="text"
              className="selectsCrear"
              id="inicialesCrearCaso"
              name="iniciales"
              onChange={(e) => setIniciales(e.target.value)}
            />
          </div>

          <div>
            <label htmlFor="descripcion">Descripción del Caso:</label>
          </div>
          <div>
            <input
              type="text"
              className="selectsCrear"
              id="descripcionCrearCaso"
              name="descripcion"
              onChange={(e) => setDescripcionCaso(e.target.value)}
            />
          </div>

          <div>
            <label htmlFor="tipo">Tipo de Caso:</label>
          </div>
          <div>
            <select
              className="selectsCrear"
              id="tipoCrearCaso"
              name="tipo"
              value={tipoCaso}
              onChange={(e) => setTipoCaso(e.target.value)}
            >
              <option value=""></option>
              <option value="ViolenciaDomestica">Violencia doméstica</option>
              <option value="Discriminacion">Discriminación</option>
              <option value="Acoso">Acoso</option>
            </select>
          </div>

          <div>
            <label htmlFor="estado">Estado:</label>
          </div>
          <div>
            <select
              className="selectsCrear"
              id="estadoCrearCaso"
              name="estado"
              value={estado}
              onChange={(e) => setEstado(e.target.value)}
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

          <div>
            <label htmlFor="fecha">Fecha:</label>
          </div>
          <div>
            <input
              type="date"
              className="selectsCrear"
              id="fechaCrearCaso"
              name="fecha"
              onChange={(e) => setFecha(e.target.value)}
            />
          </div>
        </form>
        <br />
        <button onClick={createCasoNuevo}>Crear Caso</button>

        <div className="divCerrarSesion">
          <Link to={`/PortalAdmin/${userInfo.nombre}`}>
            <button className="cerrar_sesion_button" onClick={aPortal}>
              Regresar
            </button>
          </Link>
        </div>
        <p className="mt-5 mb-3 text-body-secondary">
          &copy; Derechos reservados Linces 2023
        </p>
      </body>
    </>
  );
};

export default CrearCaso;
