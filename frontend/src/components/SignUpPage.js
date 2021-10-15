import Card from "react-bootstrap/Card";
import Form from "react-bootstrap/Form";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import Button from "react-bootstrap/Button";
import Stack from "react-bootstrap/Stack";
import { useState } from "react";
import { LinkContainer } from "react-router-bootstrap";

const SignUpPage = () => {
    const [validated, setValidated] = useState(false);
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (event) => {
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
            event.stopPropagation();
        }

        console.log(firstName)
        console.log(lastName)
        console.log(email)
        console.log(password)
        event.preventDefault();
        setValidated(true);
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
          <Stack direction="horizontal" gap={3}>
            <Button variant="success" type="submit" size="lg" className="w-100">
              Sign up
            </Button>
            <LinkContainer to="/">
              <Button variant="outline-danger" size="lg" className="w-100">
                Cancel
              </Button>
            </LinkContainer>
          </Stack>
        </Form>
      </Card.Body>
    </Card>
  );
};

export default SignUpPage;
