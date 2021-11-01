import { UPDATE } from "./types";

export const update = (data) => (dispatch) => {
  dispatch({
    type: UPDATE,
    payload: data,
  });
};