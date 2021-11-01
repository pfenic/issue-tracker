import Card from "react-bootstrap/Card";
import Form from "react-bootstrap/Form";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import Button from "react-bootstrap/Button";
import { useState } from "react";
import { useHistory } from "react-router";

const SignUpPage = () => {
  const [validated, setValidated] = useState(false);
  const [isValid, setIsValid] = useState(false);
  const [hasFocus, setHasFocus] = useState(false);
  const [projectName, setProjectName] = useState("");
  const [description, setDescription] = useState("");

  const history = useHistory();

  const handleSubmit = (event) => {
    event.preventDefault();

    const form = event.currentTarget;
    const valid = form.checkValidity() && isValid

    setValidated(true);
    setIsValid(valid)

    if (!valid) {
      event.stopPropagation(); // TODO: is this necessary?
      return;
    }

    console.log(projectName);
    console.log(description);

    const createProject = async () => {
      const res = await fetch("/api/v1/project", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          name: projectName,
          description: description,
        }),
      });

      const status = await res.status;
      const data = await res.json();
      console.log(data);

      if (status === 200) {
          // TODO: add to state
          // TODO: go to created project's page
        goBack()
      } else {
        const data = await res.json()
        // TODO: better notification
        alert(data.message)
      }
    };

    createProject();
  };

  const goBack = () => {
    history.push("/projects");
  };

  // TODO: maybe use it for suggestions if I make names unique
  /*
  const fetchName = async (name) => {
    if (name === "") {
      return;
    }
    try {
      const res = await fetch(
        "/api/v1/project?" +
          new URLSearchParams({
            name: name,
          })
      );
      const data = await res.json();
      console.log(data);
    } catch (err) {}
  };
  */
  const handlelNameChange = (name) => {
    const nameNotEmpty = name !== "";

    setValidated(nameNotEmpty);
    setProjectName(name);

    const checkName = async () => {
      try {
        const res = await fetch(`/api/v1/project/${name}/exists`);
        const data = await res.json();
        setIsValid(data === false)
      } catch (err) {}
    };

    if (nameNotEmpty) {
      checkName();
    }
  };

  return (
    <div className="container">
      <Card bg="dark" text="light" className="p-1 vw-lg-100">
        <Card.Header as="h4">Create a new project</Card.Header>
        <Card.Body>
          <Form noValidate onSubmit={handleSubmit}>
            <FloatingLabel
              label="Project name"
              controlId="newProjectName"
              className="text-dark mb-3"
            >
              <Form.Control
                type="text"
                placeholder="project"
                value={projectName}
                onFocus={() => {
                  setHasFocus(true);
                }}
                onBlur={() => {
                  setHasFocus(false);
                }
                }
                onChange={(event) => {
                  handlelNameChange(event.target.value);
                }}
                isValid={validated && isValid}
                isInvalid={validated && !isValid}
                autoComplete="off"
                required
              />
              <Form.Control.Feedback tooltip type="valid" hidden={!hasFocus}>
                <b>{projectName}</b> is available.
              </Form.Control.Feedback>
              <Form.Control.Feedback tooltip type="invalid">
                {projectName === "" ? (
                  <>Please choose a name for the project.</>
                ) : (
                  <>
                    A project with the name <b>{projectName}</b> already exists.
                  </>
                )}
              </Form.Control.Feedback>
            </FloatingLabel>
            <FloatingLabel
              label="Description (optional)"
              controlId="newProjectDescription"
              className="text-dark mb-3"
            >
              <Form.Control
                type="text"
                placeholder="foo bar"
                value={description}
                onChange={(event) => {
                  setDescription(event.target.value);
                }}
                autoComplete="off"
              />
            </FloatingLabel>
            <Button
              variant="success"
              type="submit"
              size="lg"
              className="w-100 mb-3"
            >
              Create project
            </Button>
          </Form>
          <Button
            variant="outline-danger"
            size="lg"
            className="w-100"
            onClick={goBack}
          >
            Cancel
          </Button>
        </Card.Body>
      </Card>
    </div>
  );
};

export default SignUpPage;

