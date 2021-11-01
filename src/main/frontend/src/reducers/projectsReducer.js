import { UPDATE } from "../actions/types";

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
    default:
      return state;
  }
};

export default projectsReducer;

