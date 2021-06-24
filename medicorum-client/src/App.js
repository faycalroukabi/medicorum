import Home from "./components/home/Home";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import Register from "./components/register/Register";
import Chat from "./components/layout/chat/Chat";

function App() {
  document.body.style.background = "#0D1117";
  return (
    <Router>
      <Switch>
        <Route exact path="/">
          <Home />
        </Route>
        <Route exact path="/register">
          <Register/>
        </Route>
        <Route exact path="/chat">
          <Chat/>
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
