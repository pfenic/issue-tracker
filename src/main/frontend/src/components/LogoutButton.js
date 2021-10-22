import Button from "react-bootstrap/Button";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { logout } from "../actions/authActions";

const LogoutButton = (props) => {
  return (
    <Button variant="danger" onClick={props.logout}>
      Log out
    </Button>
  );
};

LogoutButton.prototypes = {
    logout: PropTypes.func.isRequired
}

export default connect(null, { logout })(LogoutButton);
