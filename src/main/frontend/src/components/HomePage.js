import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import Stack from "react-bootstrap/Stack";
import ListGroup from "react-bootstrap/ListGroup";
import { LinkContainer } from "react-router-bootstrap";
import { connect } from "react-redux";

const HomePage = (props) => {
  /*
  const [projects, setProjects] = useState([])

  useEffect(() => {
    const fetchProjects = async () => {
      const res = await fetch("/api/v1/profile/self")
      const data = await res.json();
      setProjects(data.projects)
    }

    fetchProjects()
  }, [])
  */

  return (
    <div className="container-sm">
      <Card bg="dark" text="light" className="p-1 vw-lg-100">
        <Card.Header>
          <Stack direction="horizontal" gap={3}>
            <h4>Projects</h4>
            <LinkContainer to="/projects/new">
              <Button variant="success" className="ms-auto">
                Create Project
              </Button>
            </LinkContainer>
          </Stack>
        </Card.Header>
        <Card.Body>
          <Card bg="secondary" text="dark" className="p-1 vw-lg-100">
            <ListGroup variant="flush">
              {props.projects.map((project) => (
                <ListGroup.Item key={project.id} action variant="dark">
                  {project.name}
                </ListGroup.Item>
              ))}
            </ListGroup>
          </Card>
        </Card.Body>
      </Card>
    </div>
  );
};

const mapStateToProps = (state) => ({
  projects: state.projects.projects,
});

export default connect(mapStateToProps, null)(HomePage);
