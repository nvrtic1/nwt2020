import React, { Component } from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Login from './components/Login';
import Registration from './components/Registration';
import Home from './components/Home';
import Admin from './components/Admin';
import User from './components/User';



class App extends Component {
  render() {
    return (
    <Router>
        <div>

          <Switch>
          <Route exact path='/' component={Home} />
              <Route path='/login' component={Login} />
              <Route path='/registration' component={Registration} />
              <Route path='/admin' component={Admin} />
              <Route path='/user' component={User} />


          </Switch>
        </div>
      </Router>
    );
  }
}

export default App;