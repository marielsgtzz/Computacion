import { createContext } from "react";

const UserContext = createContext({
  userInfo: {
    nombre: "",
    tipo: "",
  },
  setUserInfo: () => {},
});

export default UserContext;
