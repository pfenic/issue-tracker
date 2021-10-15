import Card from "react-bootstrap/Card";
import Form from "react-bootstrap/Form";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import Button from "react-bootstrap/Button";
import { useState } from "react";

const LoginPage = () => {
    const [validated, setValidated] = useState(false);

    const handleSubmit = (event) => {
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
            event.stopPropagation();
        }

        console.log(form[0].value)
        console.log(form[1].value)
        event.preventDefault();
        setValidated(true);
    };

  return (
    <Card bg="dark" text="light" className="p-1" style={{ width: "18rem" }}>
      <Card.Header as="h4">Login</Card.Header>
      <Card.Body>
        <Form noValidate validated={validated} onSubmit={handleSubmit}>
          <FloatingLabel
            label="Email address"
            controlId="loginEmail"
            className="text-dark mb-3"
          >
            <Form.Control type="email" placeholder="your@email.com" required />
          </FloatingLabel>
          <FloatingLabel
            label="Password"
            controlId="loginPassword"
            className="text-dark mb-3"
          >
            <Form.Control type="password" placeholder="Password" required />
          </FloatingLabel>
          <Button type="submit">Login</Button>
        </Form>
      </Card.Body>
    </Card>
  );
};

export default LoginPage;
