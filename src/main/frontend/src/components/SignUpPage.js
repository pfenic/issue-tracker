import Card from "react-bootstrap/Card";
import Form from "react-bootstrap/Form";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import Button from "react-bootstrap/Button";
import { useState } from "react";
import { useHistory } from "react-router";

const SignUpPage = () => {
  const [validated, setValidated] = useState(false);
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const history = useHistory();

  const handleSubmit = (event) => {
    event.preventDefault();

    const form = event.currentTarget;

    setValidated(true);
    if (form.checkValidity() === false) {
      event.stopPropagation(); // TODO: is this necessary?
      return;
    }

    console.log(firstName);
    console.log(lastName);
    console.log(email);
    console.log(password);

    const signUp = async () => {
      const res = await fetch("/api/v1/registration", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          firstName: firstName,
          lastName: lastName,
          email: email,
          password: password,
        }),
      });
      const data = await res.json();

      console.log(data);
    };

    signUp();
  };

  const cancel = () => {
    history.push("/");
  };

  return (
    <Card bg="dark" text="light" className="p-1" style={{ width: "18rem" }}>
      <Card.Header as="h4">Sign Up</Card.Header>
      <Card.Body>
        <Form noValidate validated={validated} onSubmit={handleSubmit}>
          <FloatingLabel
            label="First Name"
            controlId="signUpFirstName"
            className="text-dark mb-3"
          >
            <Form.Control
              type="text"
              placeholder="Luke"
              value={firstName}
              onChange={(event) => {
                setFirstName(event.target.value);
              }}
              required
            />
          </FloatingLabel>
          <FloatingLabel
            label="Last Name"
            controlId="signUpLastName"
            className="text-dark mb-3"
          >
            <Form.Control
              type="text"
              placeholder="Skywalker"
              value={lastName}
              onChange={(event) => {
                setLastName(event.target.value);
              }}
              required
            />
          </FloatingLabel>
          <FloatingLabel
            label="Email address"
            controlId="signUpEmail"
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
            controlId="signUpPassword"
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
          <Button
            variant="success"
            type="submit"
            size="lg"
            className="w-100 mb-3"
          >
            Sign up
          </Button>
        </Form>
        <Button
          variant="outline-danger"
          size="lg"
          className="w-100"
          onClick={cancel}
        >
          Cancel
        </Button>
      </Card.Body>
    </Card>
  );
};

export default SignUpPage;
