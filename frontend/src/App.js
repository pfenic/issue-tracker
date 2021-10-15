import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import { LinkContainer } from "react-router-bootstrap";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import Container from "react-bootstrap/Container";
import LoginPage from "./components/LoginPage";
import Button from "react-bootstrap/Button";

// rafce
      // TODO fix navbar 'fixed' height
function App() {
  return (
    <Router>
      <div style={{ height: "100vh" }}>
        <Navbar variant="dark" bg="dark" expand="sm" style={{ height: "4rem" }}>
          <Container>
            <LinkContainer to="/">
              <Navbar.Brand>Issue Tracker</Navbar.Brand>
            </LinkContainer>
            <Navbar.Toggle />
            <Navbar.Collapse>
              <Nav className="ms-auto">
                <LinkContainer to="/">
                  <Nav.Link>
                    <Button variant="success">Sign up</Button>
                  </Nav.Link>
                </LinkContainer>
              </Nav>
            </Navbar.Collapse>
          </Container>
        </Navbar>

        <div
          className="bg-secondary p-5"
          style={{
            display: "flex",
            minHeight: "calc(100% - 13rem)",
            flexDirection: "column",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <Route path="/" exact component={LoginPage} />
        </div>

        <footer
          className="bg-dark text-light p-5 text-center"
          style={{ height: "9rem" }}
        >
          <Container>
            <p className="lead">Copyright &copy; 2021 Nicolas Pfeiffer</p>
          </Container>
        </footer>
      </div>
    </Router>
  );
}
      //</Container>

// style={{ minHeight: '100vh'}}
/*
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
        */

export default App;
