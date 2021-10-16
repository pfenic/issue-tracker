import Card from "react-bootstrap/Card";
import Form from "react-bootstrap/Form";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import Button from "react-bootstrap/Button";
import { useState } from "react";
import { Redirect } from "react-router";

const LoginPage = ({ onLogin, loggedIn }) => {
  const [validated, setValidated] = useState(false);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [remember, setRemember] = useState(false);

  const handleSubmit = (event) => {
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.stopPropagation();
    }

    console.log(email);
    console.log(password);
    console.log(remember);
    event.preventDefault();
    setValidated(true);

    onLogin();
  };

  return (
    <>
      {loggedIn ? (
        <Redirect to="/" />
      ) : (
        <Card bg="dark" text="light" className="p-1" style={{ width: "18rem" }}>
          <Card.Header as="h4">Login</Card.Header>
          <Card.Body>
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
      )}
    </>
  );
};

export default LoginPage;
