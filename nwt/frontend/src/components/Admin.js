import React, { Component } from 'react';
import {
  Redirect
 } from 'react-router-dom';

class Admin extends Component {
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

    console.log(localStorage.username);



     if (localStorage.username==="undefined") {
      alert("Nemate pristup ovoj stranici");
       return <Redirect to='/' />;

     }

     

    return (
      
        <div>
                    <nav className="navbar navbar-expand-lg navbar-light bg-light">
          <ul className="navbar-nav mr-auto">
          <li><a href='/admin' className="nav-link"> Home </a></li>
          <li><a href='/users' className="nav-link">Users</a></li>
            <li><a href='/addSinger' className="nav-link">Singers</a></li>
            <li><a href='/addSong' className="nav-link">Songs</a></li>
            <li><a href='/signOut' className="nav-link">Sign Out</a></li>
          </ul>
          </nav>
          <hr />

          <h2>Admin page</h2>
          
        </div>
    );
  }
}

export default Admin;