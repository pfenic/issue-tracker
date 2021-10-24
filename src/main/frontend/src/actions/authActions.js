import { LOGIN, LOGOUT } from "./types";

export const login = (loginData) => async (dispatch) => {
  const res = await fetch("/api/login", {
    method: "POST",

    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
    },

    body: new URLSearchParams(loginData),
  });

  const status = await res.status;

  if (status === 200) {
    dispatch({
      type: LOGIN,
      payload: true,
    });
  } else if (status === 401) {
    // TODO dispatch action
    // Set Login to false?
    dispatch({
      type: LOGIN,
      payload: false,
    });
  } else {
    // TODO handle other status
  }
};

export const logout = () => async (dispatch) => {
  const res = await fetch("/api/logout", {
    method: "POST",
  });

  const status = await res.status;

  if (status === 200) {
    dispatch({
      type: LOGOUT,
      payload: true,
    });
  } else {
    // TODO dispatch action
    // Set Login to false?
    // TODO handle other status
  }
};
