import { LOGIN, LOGOUT } from "../actions/types";

const initialState = {
    loggedIn: false,
    loginFailed: false
};

const authReducer = (state = initialState, action) => {
    switch (action.type) {
        case LOGIN:
            return {
                ...state,
                loggedIn: action.payload,
                loginFailed: !action.payload
            };
        case LOGOUT:
            return {
                ...state,
                loggedIn: !action.payload
            };
        default:
            return state;
    }
};

export default authReducer;