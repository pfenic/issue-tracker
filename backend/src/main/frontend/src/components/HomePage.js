import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import Stack from "react-bootstrap/Stack";
import ListGroup from "react-bootstrap/ListGroup";

const HomePage = () => {
    return (
      <div className="container-sm">
        <Card bg="dark" text="light" className="p-1 vw-lg-100">
          <Card.Header>
            <Stack direction="horizontal" gap={3}>
              <h4>Projects</h4>
              <Button variant="success" className="ms-auto">
                Create Project
              </Button>
            </Stack>
          </Card.Header>
          <Card.Body>
            <Card bg="secondary" text="dark" className="p-1 vw-lg-100">
              <ListGroup variant="flush">
                <ListGroup.Item action variant="dark">
                  Titel
                </ListGroup.Item>
                <ListGroup.Item action variant="secondary">
                  Titel
                </ListGroup.Item>
              </ListGroup>
            </Card>
          </Card.Body>
        </Card>
      </div>
    );
}

export default HomePage
