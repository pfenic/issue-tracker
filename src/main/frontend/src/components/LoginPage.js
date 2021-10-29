import Card from "react-bootstrap/Card";
import Form from "react-bootstrap/Form";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import Button from "react-bootstrap/Button";
import { useState, useEffect } from "react";
import { login, setLoggedIn } from "../actions/authActions";
import { connect } from "react-redux";
import PropTypes from "prop-types";

const LoginPage = (props) => {
  const [validated, setValidated] = useState(false);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [remember, setRemember] = useState(false);

  const setLoggedIn = props.setLoggedIn;

  useEffect(() => {
      const tryLoadProfile = async () => {
        try {
          const res = await fetch("/api/v1/profile/self", {
            redirect: "error"
          })
          const data = await res.json()

          setLoggedIn();

          // TODO save user
          console.log(data)
        } catch (err) {
          console.log(err);
        }
      };


      tryLoadProfile();
  }, [setLoggedIn]);

  const handleSubmit = (event) => {
    event.preventDefault();

    const form = event.currentTarget;

    setValidated(true);
    if (form.checkValidity() === false) {
      event.stopPropagation(); // TODO is this necessary?
      return;
    }

    const loginData = {
      username: email,
      password: password,
      remember: remember,
    };

    props.login(loginData);
  };

  return (
    <Card bg="dark" text="light" className="p-1" style={{ width: "18rem" }}>
      <Card.Header as="h4">Login</Card.Header>
      <Card.Body>
        {props.loginFailed ? (
          <p
            className="bg-danger py-1 text-center text-dark"
            style={{
              borderRadius: "3%",
              marginLeft: "1px",
              marginRight: "1px",
            }}
          >
            Invalid username or password
          </p>
        ) : (
          <></>
        )}
        <Form noValidate validated={validated} onSubmit={handleSubmit}>
          <FloatingLabel
            label="Email address"
            controlId="loginEmail"
            className="text-dark mb-3"
          >
            <Form.Control
              type="email"
              placeholder="your@email.com"
              value={email}
              name="username"
              onChange={(event) => {
                setEmail(event.target.value);
              }}
              required
            />
          </FloatingLabel>
          <FloatingLabel
            label="Password"
            controlId="loginPassword"
            className="text-dark mb-3"
          >
            <Form.Control
              type="password"
              placeholder="Password"
              value={password}
              name="password"
              onChange={(event) => {
                setPassword(event.target.value);
              }}
              required
            />
          </FloatingLabel>
          <Form.Group className="mb-3">
            <Form.Check
              label="Stay logged in"
              checked={remember}
              value={remember}
              onChange={(event) => {
                setRemember(event.currentTarget.checked);
              }}
            />
          </Form.Group>
          <Button type="submit" size="lg" className="w-100">
            Login
          </Button>
        </Form>
      </Card.Body>
    </Card>
  );
};

LoginPage.propTypes = {
  login: PropTypes.func.isRequired,
  setLoggedIn: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
  loggedIn: state.auth.loggedIn,
  loginFailed: state.auth.loginFailed
});

export default connect(mapStateToProps, { login, setLoggedIn })(LoginPage);
