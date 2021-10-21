import { BrowserRouter as Router, Route, Redirect} from "react-router-dom";
import { LinkContainer } from "react-router-bootstrap";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import Container from "react-bootstrap/Container";
import LoginPage from "./components/LoginPage";
import Button from "react-bootstrap/Button";
import SignUpPage from "./components/SignUpPage";
import HomePage from "./components/HomePage"
import { useState } from "react"

// rafce
      // TODO fix navbar 'fixed' height
function App() {
    const [loggedIn, setLoggedIn] = useState(false);

    const logout = () => {
      setLoggedIn(false);
      console.log('loggout');
    };

    const login = () => {
      setLoggedIn(true);
      console.log('login');
      <Redirect to="/" />
    };

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
                {loggedIn ? (
                  <Nav.Link>
                    <Button variant="danger" onClick={logout}>
                      Log out
                    </Button>
                  </Nav.Link>
                ) : (
                  <LinkContainer to="/signup">
                    <Nav.Link>
                      <Button variant="success">Sign up</Button>
                    </Nav.Link>
                  </LinkContainer>
                )}
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
          <Route path="/" exact>
            {loggedIn ? <HomePage /> : <Redirect to="/login" />}
          </Route>
          <Route path="/login" exact>
            <LoginPage onLogin={login} loggedIn={loggedIn} />
          </Route>
          <Route path="/signup" exact component={SignUpPage} />
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

export default App;
