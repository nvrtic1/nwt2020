import React, { Component } from 'react';
import {
  Redirect
 } from 'react-router-dom';

class Login extends Component {
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
    fetch('http://localhost:8083/login', {
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
        console.log(response);
        this.setState({pom: response.url.substr(22)})
              if(response.status === 200) {
                localStorage.username=this.state.username;
                  this.setState({ redirect: true })
                }
              else if(response.status === 401)
              {
                alert("Pogrešan username ili password");
              }

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
          <li><a href='/' className="nav-link">Home</a></li>
            <li><a href='/login' className="nav-link">Login</a></li>
            <li><a href='/registration' className="nav-link">Registration</a></li>


          </ul>
          </nav>
          <hr />
          <h2>Login</h2>
        <br></br>
        <br></br>


      <div className="main">
         <div className="col-md-6 col-sm-12">
            <div className="login-form">
            <form onSubmit={this.handleSubmit}>
                  <div className="form-group">
                     <label>Username</label>
                     <input type="text" name="username" className="form-control" placeholder="Username" value={this.state.username} onChange={this.handleChangeUsername} required/>
                  </div>
                  <div className="form-group">
                     <label>Password</label>
                     <input type="password" name="password" className="form-control" placeholder="Password" value={this.state.password} onChange={this.handleChangePassword} required/>
                  </div>
                  <button type="submit" className="btn btn-primary">Login</button>
               </form>
            </div>
         </div>
      </div>

        </div>
    );
  }
}

export default Login;