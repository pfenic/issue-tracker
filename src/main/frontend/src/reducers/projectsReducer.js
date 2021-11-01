import { ADD_PROJECT, UPDATE } from "../actions/types";

const initialState = {
  projects: [],
};

const projectsReducer = (state = initialState, action) => {
  switch (action.type) {
    case UPDATE:
      if (action.payload.projects) {
        return {
          ...state,
          projects: action.payload.projects,
        };
      } else {
        return state;
      }
      case ADD_PROJECT:
          console.log(state);
          return {
              ...state,
              projects: [...state.projects, action.payload]
          }
    default:
      return state;
  }
};

export default projectsReducer;

