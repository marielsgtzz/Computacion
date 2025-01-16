import { createContext } from "react";

const CasoContext = createContext({
  casos: [
    {
      nombre: "",
      iniciales: "",
      descripcionDelCaso: "",
      tipoDeCaso: "",
      estado: "",
      fecha: "",
      tareas: [
        {
          titulo: "",
          estudiante: "",
          fecha: "",
          estatus: "",
          nota: "",
        },
      ],
    },
  ],
  setCasos: () => {},
});

export default CasoContext;
