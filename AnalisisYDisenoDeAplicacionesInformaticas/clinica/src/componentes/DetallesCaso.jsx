import React, { useState, useContext } from "react";
import { useParams, Link } from "react-router-dom";
import UserContext from "../context/UserContext";
import CasosContext from "../context/CasoContext";
import "../css/CSSGeneral.css";
import { useEffect } from "react";

//Se llega mediante busqueda y/o crear caso (una vez que se haya instanciado uno nuevo)
const DetallesCaso = () => {
  //Contextos
  const { userInfo } = useContext(UserContext);
  const { casos } = useContext(CasosContext);

  //Jala el nombre del caso del url (que se manda de la pagina anterior)
  const params = useParams();

  //Para redirección
  // const [usuarix, setUsuarix] = useState("");

  const [nombreCaso, setNombreCaso] = useState("");
  const [descCaso, setDescCaso] = useState("");
  const [estatusCaso, setEstatusCaso] = useState("");

  useEffect(() => {
    for (let i = 0; i < casos.length; i++) {
      if (casos[i].iniciales == params.id) {
        setNombreCaso(casos[i].nombre);
        setEstatusCaso(casos[i].estado);
        setDescCaso(casos[i].descripcionDelCaso);
      }
    }
  });

  const createListaTareas = () => {
    console.log({ descCaso });
    let listaTareas = [];

    for (let i = 0; i < casos.length; i++) {
      if (casos[i].iniciales === params.id) {
        for (let j = 0; j < casos[i].tareas.length; j++) {
          listaTareas.push(
            <>
              <div>
                <div>
                  <label for="titulo">Título de la tarea:</label>
                </div>
                <input
                  class="selectsCrear"
                  type="text"
                  id="tituloTarea"
                  name="titulo"
                  readOnly
                  value={`${casos[i].tareas[j].titulo}`}
                />
              </div>
              <div>
                <div>
                  <label for="asignado">Asignado a:</label>
                </div>
                <input
                  class="selectsCrear"
                  type="text"
                  id="asignado"
                  name="asignado"
                  readOnly
                  value={`${casos[i].tareas[j].estudiante}`}
                />
              </div>
              <div>
                <div>
                  <label for="fecha">Fecha de entrega:</label>
                </div>
                <input
                  class="selectsCrear"
                  type="text"
                  id="fechaEntrega"
                  name="fecha"
                  readOnly
                  value={`${casos[i].tareas[j].fecha}`}
                />
              </div>
              <div>
                <div>
                  <label for="estatus">Estatus:</label>
                </div>
                <input
                  class="selectsCrear"
                  type="text"
                  id="estatus"
                  name="estatus"
                  readOnly
                  value={`${casos[i].tareas[j].estatus}`}
                />
              </div>
              <div>
                <div>
                  <label for="nota">Nota:</label>
                </div>
                <input
                  class="selectsCrear"
                  type="text"
                  id="nota"
                  name="nota"
                  readOnly
                  value={`${casos[i].tareas[j].nota}`}
                />
              </div>
            </>
          );
        }
      }
    }
    if (listaTareas.length === 0) {
      return "No hay tareas";
    } else {
      return listaTareas;
    }
  };

  return (
    <body>
      <header>
        <div>
          <h1>Detalles del caso</h1>
        </div>
      </header>

      <h2>Descripción del caso</h2>
      <p id="descCaso">{descCaso}</p>

      <div>
        <form>
          <label for="nombre-caso">Nombre del caso:</label>
          <input
            type="text"
            class="selectsBuscar"
            id="txtNombreCasoDetalles"
            name="nombre-caso"
            value={nombreCaso}
            disabled
          />

          <label for="estatus-caso">Estatus del caso:</label>
          <select id="ddEstatusCAaso" class="selectsBuscar" name="estatus-caso">
            <option
              selected={estatusCaso === "InvestigacionAbogadxs"}
              value="InvestigacionAbogadxs"
            >
              Investigación abogadxs
            </option>
            <option
              selected={estatusCaso === "SinPresentar"}
              value="SinPresentar"
            >
              Sin presentar
            </option>
            <option
              selected={estatusCaso === "InicioDemanda"}
              value="InicioDemanda"
            >
              Inicio de la demanda
            </option>
            <option
              selected={estatusCaso === "InvestigacionTribunales"}
              value="InvestigacionTribunales"
            >
              Investigación tribunales
            </option>
          </select>
        </form>
      </div>

      <div class="divTareasDetallesCaso">
        <div>
          <h2 class="h2Detalles">Tareas</h2>
        </div>

        <div class="divDetallesCaso">{createListaTareas()}</div>
      </div>

      <div class="divDetallesCasoBut">
        <div>
          <button id="btnVersionesFinales">Versiones finales</button>
        </div>
        <div>
          <button id="btnBorradorCorrecciones">Borrador y correcciones</button>
        </div>
        <div>
          <button id="btnInformacionCaso">Información del caso</button>
        </div>
      </div>

      <div class="divCerrarSesion">
        <Link to="/BuscarCaso">
          <button class="cerrar_sesion_button" type="button" id="btRegresa">
            Regresar
          </button>
        </Link>

        {userInfo.tipo === "Est" ? (
          " "
        ) : (
          <Link to={`/AgregarTareas/${params.id}`}>
            <button
              class="cerrar_sesion_button"
              type="button"
              id="btAgregaTarea"
            >
              Ir a Agregar Tarea
            </button>
          </Link>
        )}
      </div>

      <p class="mt-5 mb-3 text-body-secondary">
        &copy; Derechos reservados Linces 2023
      </p>
    </body>
  );
};

export default DetallesCaso;
