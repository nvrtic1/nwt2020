import React, { Component } from 'react';

class Registration extends Component {
  constructor(props) {
    super(props);
    this.state = {firstname: ''};
    this.state = {lastname: ''};
    this.state = {email: ''};
    this.state = {password: ''};

    this.headers = [

		];
    this.handleChangeFirstName = this.handleChangeFirstName.bind(this);
    this.handleChangeLastName = this.handleChangeLastName.bind(this);
    this.handleChangeEmail = this.handleChangeEmail.bind(this);
    this.handleChangePassword = this.handleChangePassword.bind(this);
    this.handleSubmit = this.onSubmit.bind(this);
  }

  handleChangeFirstName(event) {
    this.setState({firstname: event.target.value});
  }

  handleChangeLastName(event) {
    this.setState({lastname: event.target.value});
  }

  handleChangeEmail(event) {
    this.setState({email: event.target.value});
  }

  handleChangePassword(event) {
    this.setState({password: event.target.value});
  }

  onSubmit(event){
    event.preventDefault();
    fetch('http://localhost:8083/registration', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
          body: JSON.stringify({
                  firstName: this.state.firstname,
                  lastName: this.state.lastname,
                  email: this.state.email,
                  roles: [
                    {
                        name: "ROLE_USER"
                    }
                  ],
                  password: this.state.password

          })
      }).then(response => {
              if(response.status === 500) {
                  alert("Registracija nije uspješna");
              }
              else if(response.status === 400) {
                alert("Registracija nije uspješna");
            }
            else if(response.status === 201)
            {
              alert("Registracija je uspješna");
            }
            else if(response.status === 409)
            {
              alert("Registracija nije uspješna. Email već postoji.");
            }
          });
  }

  render() {
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
          <h2>Registration</h2>
          <br></br>
        <br></br>
            <div className="main">
         <div className="col-md-6 col-sm-12">
            <div className="login-form">
            <form onSubmit={this.handleSubmit}>
                  <div className="form-group">
                     <label>Firstname</label>
                     <input type="text" name="firstname" className="form-control" placeholder="Firstname" value={this.state.firstname} onChange={this.handleChangeFirstName}/>
                  </div>
                  <div className="form-group">
                     <label>Lastname</label>
                     <input type="text" name="lastname" className="form-control" placeholder="Lastname" value={this.state.lastname} onChange={this.handleChangeLastName}/>
                  </div>
                  <div className="form-group">
                     <label>Email</label>
                     <input type="text" name="email" className="form-control" placeholder="Email" value={this.state.email} onChange={this.handleChangeEmail}/>
                  </div>
                  <div className="form-group">
                     <label>Password</label>
                     <input type="password" name="password" className="form-control" placeholder="Password" value={this.state.password} onChange={this.handleChangePassword}/>
                  </div>
                  <button type="submit" className="btn btn-primary">Registation</button>
               </form>
            </div>
         </div>
      </div>
        </div>
    );
  }
}

export default Registration;