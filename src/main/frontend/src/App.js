import { Switch, Route, Redirect } from "react-router-dom";
import { LinkContainer } from "react-router-bootstrap";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import Container from "react-bootstrap/Container";
import LoginPage from "./components/LoginPage";
import Button from "react-bootstrap/Button";
import SignUpPage from "./components/SignUpPage";
import HomePage from "./components/HomePage";
import LogoutButton from "./components/LogoutButton";
import { connect } from "react-redux";

// rafce
// TODO fix navbar 'fixed' height
const App = (props) => {
  //useEffect(() => {
    /*
      const fetchTasks = async () => {
        try {
          const res = await fetch("http://localhost:5000/posts")
          const data = await res.json()

          console.log(data)
        } catch (err) {
          console.log(err);
        }
      };

      fetchTasks();
      */
  //}, []);

  return (
    <div style={{ height: "100vh" }}>
      <Navbar variant="dark" bg="dark" expand="sm" style={{ height: "4rem" }}>
        <Container>
          <LinkContainer to="/">
            <Navbar.Brand>Issue Tracker</Navbar.Brand>
          </LinkContainer>
          <Navbar.Toggle />
          <Navbar.Collapse>
            <Nav className="ms-auto">
              {props.loggedIn ? (
                <Nav.Link>
                  <LogoutButton />
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
        <Switch>
          <Route path="/" exact>
            {props.loggedIn ? <HomePage /> : <Redirect to="/login" />}
          </Route>
          <Route path="/login" exact component={LoginPage} />
          <Route path="/signup" exact component={SignUpPage} />
        </Switch>
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
  );
}

const mapStateToProps = state => ({
  loggedIn: state.auth.loggedIn
})

export default connect(mapStateToProps, null)(App);
