import { combineReducers } from "redux";
import authReducer from "./authReducer";
import profileReducer from "./profileReducer";
import projectsReducer from "./projectsReducer";

export default combineReducers({
  auth: authReducer,
  profile: profileReducer,
  projects: projectsReducer,
});