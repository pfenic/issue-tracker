import { BrowserRouter as Router, Route } from "react-router-dom";
import { LinkContainer } from 'react-router-bootstrap'
import Button from 'react-bootstrap/Button'

// rafce
function App() {
  return (
    <Router>
      <Route
        path="/"
        exact
        render={(props) => (
          <section style={{ minHeight: '100vh'}} className='bg-dark p-5'>
            <div className="bg-dark">
              <LinkContainer to="/test">
                <Button className="btn-lg btn-danger">Foo</Button>
              </LinkContainer>
            </div>
          </section>
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
    </Router>
  );
}

// style={{ minHeight: '100vh'}}

export default App;