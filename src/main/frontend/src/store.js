import { createStore, applyMiddleware, compose } from "redux";
import thunk from "redux-thunk";
import rootReducer from "./reducers";

const initalState = {};
const middleware = [thunk];

const enhancer = [
    applyMiddleware(...middleware)
];

if (process.env.NODE_ENV === "development" && window.__REDUX_DEVTOOLS_EXTENSION__) {
  enhancer.push(window.__REDUX_DEVTOOLS_EXTENSION__());
}

const store = createStore(rootReducer, initalState, compose(...enhancer));

export default store;
