import Card from "react-bootstrap/Card";
import Form from "react-bootstrap/Form";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import Button from "react-bootstrap/Button";
import { Redirect } from "react-router";
import { useState, useEffect } from "react";

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
    return
    event.preventDefault();

    setValidated(true);

    const signUp = async () => {
      const res = await fetch("http://localhost:8080/login", {
        //const res = await fetch("/login", {
        method: "POST",
        
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },

        body: new URLSearchParams({
          username: email,
          password: password,
        }),
      });
      const data = await res.status;

      console.log(data);
      console.log(res)
    };

    signUp();

    //onLogin();
  };

  useEffect(() => {
      const fetchTasks = async () => {
          const res = await fetch("http://localhost:5000/posts")
          const data = await res.json()

          console.log(data)
      };

      fetchTasks();
  }, []);

  return (
    <>
      {loggedIn ? (
        <Redirect to="/" />
      ) : (
        <Card bg="dark" text="light" className="p-1" style={{ width: "18rem" }}>
          <Card.Header as="h4">Login</Card.Header>
          <Card.Body>
            <Form noValidate validated={validated} onSubmit={handleSubmit} method="post" action="/api/login">
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
      )}
    </>
  );
};

export default LoginPage;
