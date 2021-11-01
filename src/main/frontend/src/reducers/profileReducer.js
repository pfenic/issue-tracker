import { UPDATE } from "../actions/types";

const initialState = {
  id: 0,
  firstName: "",
  lastName: "",
  email: "",
};

const profileReducer = (state = initialState, action) => {
  switch (action.type) {
    case UPDATE:
      if (action.payload.profile) {
        return {
          ...state,
          id: action.payload.profile.id,
          firstName: action.payload.profile.firstName,
          lastName: action.payload.profile.lastName,
          email: action.payload.profile.email,
        };
      } else {
        return state;
      }
    default:
      return state;
  }
};

export default profileReducer;
