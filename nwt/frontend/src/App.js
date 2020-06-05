import React, { Component } from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import addSinger from './components/addSinger';
import addSong from './components/addSong';
import Users from './components/Users';

import SignOut from './components/SignOut';

import Songs from './components/Songs';
import buySong from './components/buySong';


import Login from './components/Login';
import Registration from './components/Registration';
import Home from './components/Home';
import Admin from './components/Admin';
import User from './components/User';

import changePassword from './components/changePassword';



class App extends Component {
  render() {
    return (
    <Router>
        <div>

          <Switch>
          <Route exact path='/' component={Home} />
              <Route path='/addSinger' component={addSinger} />
              <Route path='/addSong' component={addSong} />
              <Route path='/users' component={Users} />
              <Route path='/signOut' component={SignOut} />

              <Route path='/changePassword' component={changePassword} />

              <Route path='/songs' component={Songs} />
              <Route path='/buySong' component={buySong} />


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