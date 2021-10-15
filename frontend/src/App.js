import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import { LinkContainer } from "react-router-bootstrap";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import Button from "react-bootstrap/Button";
import Footer from "react-bootstrap/ModalFooter";
import Container from "react-bootstrap/Container";

// rafce
          //<Navbar.Collapse id="navmenu"></Navbar.Collapse>
function App() {
  return (
    <Router>
      <Navbar variant="dark" bg="dark" expand="sm">
        <Container>
          <LinkContainer to="/">
            <Navbar.Brand>Issue Tracker</Navbar.Brand>
          </LinkContainer>
          <Navbar.Toggle />
          <Navbar.Collapse>
            <Nav className="ms-auto">
              <LinkContainer to="/">
                <Nav.Link>Sign up</Nav.Link>
              </LinkContainer>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>

      <section style={{ minHeight: "70vh" }} className="bg-secondary p-5">
        <div style={{ height: "70vh" }} className="container">
          <Route
            path="/"
            exact
            render={(props) => (
              <>
                <div className="">
                  <LinkContainer to="/test">
                    <Button className="btn-lg btn-danger">Foo</Button>
                  </LinkContainer>
                </div>
              </>
            )}
          />
          <Route
            path="/test"
            exact
            render={(props) => (
              <LinkContainer to="/">
                <Button>Foo</Button>
              </LinkContainer>
            )}
          />
        </div>
      </section>
      <Footer className="bg-dark text-light p-5 text-center">
        <div className="container">
          <p className="lead">Copyright &copy; 2021 Nicolas Pfeiffer</p>
        </div>
      </Footer>
    </Router>
  );
}

// style={{ minHeight: '100vh'}}

export default App;
