import { connect } from "react-redux";
import ConditionalRoute from "./ConditionalRoute";

const ProtectedRoute = ({ component: Component, loggedIn, ...rest }) => {
  return (
    <ConditionalRoute
      {...rest}
      condition={loggedIn}
      redirectPath="/login"
      component={Component}
    />
  );
};

const mapStateToProps = state => ({
  loggedIn: state.auth.loggedIn
})

export default connect(mapStateToProps, null)(ProtectedRoute);
