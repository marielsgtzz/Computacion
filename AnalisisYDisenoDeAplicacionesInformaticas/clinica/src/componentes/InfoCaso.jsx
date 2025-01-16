import React from "react";
import "../css/CSSGeneral.css";

const InfoCaso = () => {
  return (
    <body>
      <header>
        <div class="div_header">
          <h1>Información del Caso</h1>
        </div>
      </header>

      <div>
        <button type="button" id="btSubirArchivo">
          Subir Archivo
        </button>
      </div>

      <div class="divAgregarDoc">
        <div>
          <a href="#">Documento 1</a>
        </div>
        <div>
          <button
            type="button"
            class="cerrar_sesion_button"
            id="btDescargaDoc1"
          >
            Descargar
          </button>
        </div>
        <div>
          <a href="#">Documento 2</a>
        </div>
        <div>
          <button
            type="button"
            class="cerrar_sesion_button"
            id="btDescargaDoc2"
          >
            Descargar
          </button>
        </div>
      </div>

      <div class="divCerrarSesion">
        <button class="cerrar_sesion_button" type="button" id="btRegresarCaso">
          Regresar al Caso
        </button>
        <button
          class="cerrar_sesion_button"
          type="button"
          id="btRegresarPortal"
        >
          Regresar al Portal
        </button>
      </div>
      <p class="mt-5 mb-3 text-body-secondary">
        &copy; Derechos reservados Linces 2023
      </p>
    </body>
  );
};

export default InfoCaso;
