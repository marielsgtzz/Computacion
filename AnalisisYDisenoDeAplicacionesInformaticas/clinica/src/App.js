import React, { useState } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import UserContext from "./context/UserContext";
import CasoContext from "./context/CasoContext";
import Login from "./componentes/LogIn";
import PortalAdmin from "./componentes/PortalAdmin";
import PortalEst from "./componentes/PortalEst";
import CrearCaso from "./componentes/CrearCaso";
import BuscarCaso from "./componentes/BuscarCaso";
import DetallesCaso from "./componentes/DetallesCaso";
import AgregarTarea from "./componentes/AgregarTarea";
import InfoCaso from "./componentes/InfoCaso";

function App() {
  //Para mantener la información de quien ingresó al portal
  const [usuarix, setUsuarix] = useState({
    nombre: "",
    tipo: "",
  });

  //Mockup Base de Datos casos para poder ver el flujo del manejo de datos
  const [casosInfo, setCasosInfo] = useState([
    {
      nombre: "Ana Sánchez vs. marido",
      iniciales: "A.S.",
      descripcionDelCaso:
        "Mujer de 35 años que ha sido víctima de violencia doméstica por parte de su esposo durante los últimos cinco años. Su esposo la ha agredido física y psicológicamente en varias ocasiones, incluso la ha amenazado de muerte. Ana ha intentado dejar a su esposo varias veces, pero siempre ha sido intimidada o amenazada para que se quede con él. Finalmente, Ana decidió buscar ayuda legal y presentó una denuncia por violencia doméstica contra su esposo.",
      tipoDeCaso: "ViolenciaDomestica",
      estado: "InvestigacionTribunales",
      fecha: "01/04/2023",
      tareas: [
        {
          titulo: "Investigacion",
          estudiante: "Mike",
          fecha: "01/04/2023",
          estatus: "InvestigacionTribunales",
          nota: "Antecedentes marido",
        },
      ],
    },
    {
      nombre: "Laura Rodríguez vs. trabajo por embarazo",
      iniciales: "M.G.",
      descripcionDelCaso:
        "Laura Rodríguez es una mujer de 40 años que ha sido despedida de su trabajo después de quedar embarazada. Laura había trabajado en la empresa por más de 5 años y había sido promovida a un puesto de gerencia. Sin embargo, después de informar a su supervisor sobre su embarazo, comenzó a recibir un trato hostil. Finalmente, fue despedida por supuesto 'bajo rendimiento' en su desempeño laboral.",
      tipoDeCaso: "Discriminacion",
      estado: "InvestigacionTribunales",
      fecha: "05/01/2021",
      tareas: [
        {
          titulo: "Casos similares",
          estudiante: "Rachel",
          fecha: "04/03/2023",
          estatus: "InvestigacionAbogadxs",
          nota: "Analisis de casos anteriores de la empresa",
        },
      ],
    },
    // {
    //   nombre: "María González vs. trabajo",
    //   iniciales: "M.G.",
    //   descripcionDelCaso:
    //     "María González es una mujer trans de 26 años que ha sido víctima de acoso sexual en su lugar de trabajo. Desde que se unió a la empresa hace un año, ha sido objeto de comentarios y tocamientos inapropiados por parte de su supervisor. María ha intentado evitar a su supervisor y ha reportado su comportamiento al departamento de recursos humanos, pero no ha recibido una respuesta adecuada.",
    //   tipoDeCaso: "Acoso",
    //   estado: "InicioDemanda",
    //   fecha: "15/03/2022",
    //   tareas: [
    //     {
    //       titulo: "Ministerio Público",
    //       estudiante: "Mike",
    //       fecha: "04/06/2023",
    //       estatus: "InvestigacionTribunales",
    //       nota: "Realizar estructura amparo",
    //     },
    //   ],
    // },
    {
      nombre: "Laura Gomez vs. trabajo",
      iniciales: "L.G.C.",
      descripcionDelCaso:
        "Laura es una mujer transgénero que ha sufrido violencia física y verbal por parte de las autoridades policiales debido a su identidad de género.",
      tipoDeCaso: "Discriminacion",
      estado: "SinPresentar",
      fecha: "15/06/2020",
      tareas: [
        {
          titulo: "Ministerio Público",
          estudiante: "Mike",
          fecha: "02/05/2023",
          estatus: "InicioDemanda",
          nota: "Realizar estructura demanda",
        },
      ],
    },
    {
      nombre: "Diego, defensor DH",
      iniciales: "D.R.",
      descripcionDelCaso:
        "Diego es un defensor de derechos humanos que ha sido objeto de amenazas y hostigamiento por parte de grupos paramilitares debido a su activismo en favor de la protección del medio ambiente.",
      tipoDeCaso: "Acoso",
      estado: "SinPresentar",
      fecha: "03/03/2023",
      tareas: [],
    },
    {
      nombre: "Alan vs. policia",
      iniciales: "A.R.M.",
      descripcionDelCaso:
        "Alan es una persona de origen afrodescendiente que ha sufrido discriminación y violencia racial por parte de la policía local en distintas ocasiones.",
      tipoDeCaso: "Discriminacion",
      estado: "InvestigacionTribunales",
      fecha: "21/02/2023",
      tareas: [],
    },
  ]);

  return (
    <UserContext.Provider
      value={{ userInfo: usuarix, setUserInfo: setUsuarix }}
    >
      <CasoContext.Provider
        value={{ casos: casosInfo, setCasos: setCasosInfo }}
      >
        <Router>
          <Routes>
            <Route path="/" element={<Login />} />
            <Route path="/PortalAdmin/:id" element={<PortalAdmin />} />
            <Route path="/PortalEst/:id" element={<PortalEst />} />
            <Route path="/NuevoCaso" element={<CrearCaso />} />
            <Route path="/BuscarCaso" element={<BuscarCaso />} />
            <Route path="/AgregarTareas/:id" element={<AgregarTarea />} />
            <Route path="/DetallesCaso/:id" element={<DetallesCaso />} />
            <Route path="/InfoCaso" element={<InfoCaso />} />
          </Routes>
        </Router>
      </CasoContext.Provider>
    </UserContext.Provider>
  );
}

export default App;
