import React, { Component } from 'react';
import {
  Redirect
 } from 'react-router-dom';

class User extends Component {
  state = {
    redirect: false,
    pom: ""

  }
  constructor(props) {
    super(props);
    this.state = {username: ''};
    this.state = {password: ''};
    this.headers = [

    ];
    this.handleChangeUsername = this.handleChangeUsername.bind(this);
    this.handleChangePassword = this.handleChangePassword.bind(this);
    this.handleSubmit = this.onSubmit.bind(this);
  }

  handleChangeUsername(event) {
    this.setState({username: event.target.value});
  }

  handleChangePassword(event) {
    this.setState({password: event.target.value});
  }

  onSubmit(event){
     event.preventDefault();
    fetch('http://localhost:8082/login', {
    method: 'POST',
    redirect: 'follow',
    headers: {
      'Content-Type': 'application/json'
    },
          body: JSON.stringify({
                  username: this.state.username,
                  password: this.state.password
          })
      }).then(response => {
        this.setState({pom: response.url.substr(22)})
              if(response.status === 200) {
                  this.setState({ redirect: true })
                }
              else if(response.status === 401) alert("Pogrešan username ili password");

          });
  }

  render() {

    const { redirect } = this.state;
    const { pom } = this.state;

     if (redirect) {
       if(pom==='admin'){
       return <Redirect to='/admin' />;}
       else
       return <Redirect to='/user' />;

     }

     

    return (
      
        <div>
                    <nav className="navbar navbar-expand-lg navbar-light bg-light">
          <ul className="navbar-nav mr-auto">
          <li><a href='/user' className="nav-link"> Home </a></li>
            <li><a href='/login' className="nav-link">Sign Out</a></li>
          </ul>
          </nav>
          <hr />

          <h2>User page</h2>
          
        </div>
    );
  }
}

export default User;