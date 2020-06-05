import React, { Component } from 'react';

class changePassword extends Component {

  
  constructor(props) {
    super(props);
    this.state = { opassword: '' };
    this.state = { npassword1: '' };
    this.state = { npassword2: '' };

    this.handleChangeOPassword = this.handleChangeOPassword.bind(this);
    this.handleChangeNPassword1 = this.handleChangeNPassword1.bind(this);
    this.handleChangeNPassword2 = this.handleChangeNPassword2.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChangeOPassword(event) {
    this.setState({opassword: event.target.value});
  }

  handleChangeNPassword1(event) {
    this.setState({npassword1: event.target.value});
  }

  handleChangeNPassword2(event) {
    this.setState({npassword2: event.target.value});
  }
  

  handleSubmit(event) {
      console.log(this.state.opassword);
      console.log(this.state.npassword1);
      console.log(this.state.npassword2);

      if(this.state.npassword1!==this.state.npassword2)
      {
        alert("Niste unijeli dva puta novu šifru ispravno.")
        return;
      }
      if(window.confirm("Are you sure want to change password?")) {
        fetch('http://localhost:8083/updatePass?email=' + localStorage.username  + '&spassword=' + this.state.opassword + '&npassword=' + this.state.npassword1, {
          method : 'PUT',
          headers: {
            'Content-Type': 'application/json',
          }
        }).then(response => { 
            if(response.status === 200) {
              alert("Password change successfully");

            }
            else alert("Desila se greska. Neispravna stara sifra."); 
         });
      }
      alert("Mijenjanje šifre u toku...")
  }

  render() {
    return (
      
        <div>
                              <nav className="navbar navbar-expand-lg navbar-light bg-light">
          <ul className="navbar-nav mr-auto">
          <li><a href='/user' className="nav-link"> Home </a></li>
          <li><a href='/songs' className="nav-link"> Songs </a></li>
          <li><a href='/buySong' className="nav-link"> BuySong </a></li>
          <li><a href='/changePassword' className="nav-link"> Change password </a></li>
          <li><a href='/signOut' className="nav-link">Sign Out</a></li>
          </ul>
          </nav>
          <hr />
          <h2>Change password</h2>
          <br></br><br></br>
          <form onSubmit={this.handleSubmit}>
          <div className="form-group">
            <label>Old password</label>
              <input type="password" name="opassword" className="form-control" value={this.state.opassword} onChange={this.handleChangeOPassword} required/>
              </div>
              <div className="form-group">
              <label>New password</label>
              <input type="password" name="npassword1" className="form-control" value={this.state.npassword1} onChange={this.handleChangeNPassword1} required/>
              </div>
              <div className="form-group">
              <label>New password</label>
              <input type="password" name="npassword2" className="form-control" value={this.state.npassword2} onChange={this.handleChangeNPassword2} required/>
              </div>
            <button type="submit" className="btn btn-primary">Promijeni šifru</button>
          </form>
          <br></br><br></br>
        </div>
    );
  }
}

export default changePassword;