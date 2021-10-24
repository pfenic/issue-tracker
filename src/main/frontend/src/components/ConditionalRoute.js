import { Route, Redirect } from "react-router-dom";

const ConditionalRoute = ({ component: Component, condition, redirectPath, ...rest }) => {
  return (
    <Route
      {...rest}
      render={(props) => {
        if (condition) {
          return <Component {...props} />;
        } else {
          return (
            <Redirect
              to={{
                pathname: redirectPath,
                state: {
                  from: props.location,
                },
              }}
            />
          );
        }
      }}
    />
  );
};

export default ConditionalRoute;

