import { LOGIN, LOGOUT, UPDATE } from "../actions/types";

const initialState = {
  loggedIn: false,
  loginFailed: false,
};

const authReducer = (state = initialState, action) => {
  switch (action.type) {
    case UPDATE:
      if (action.payload.login) {
        return {
          ...state,
          loggedIn: action.payload.login,
          loginFailed: false,
        };
      } else {
        return state;
      }
    case LOGIN:
      return {
        ...state,
        loggedIn: action.payload,
        loginFailed: !action.payload,
      };
    case LOGOUT:
      return {
        ...state,
        loggedIn: !action.payload,
        loginFailed: false,
      };
    default:
      return state;
  }
};

export default authReducer;
