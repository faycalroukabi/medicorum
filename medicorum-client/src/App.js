import Home from "./components/home/Home";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import Register from "./components/register/Register";
import Chat from "./components/layout/chat/Chat";
import NewDoctor from "./components/register/hpregister/NewDoctor";
import NewMember from "./components/register/membregister/NewMember";


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
        <Route exact path="/register">
          <Register/>
        </Route>
        <Route exact path="/registerhp">
          <NewDoctor/>
        </Route>
        <Route exact path="/registermember">
          <NewMember/>
        </Route>
        <Route exact path="/chat">
          <Chat/>
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
