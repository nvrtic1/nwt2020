import React, { Component } from 'react';
import {
  Redirect
 } from 'react-router-dom';

class Home extends Component {
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
          <li><a href='/' className="nav-link">Home</a></li>
            <li><a href='/login' className="nav-link">Login</a></li>
            <li><a href='/registration' className="nav-link">Registration</a></li>


          </ul>
          </nav>
          <hr />
          <h2>Dobro došli na početnu stranicu.</h2>
          <div className="container">
  <div className="row">
    <div className="col-sm-6">
    <img src="pocetna.png" width="100%" height="80%" alt="Pocetna"></img>

    </div>
    <div className="col-sm-6">
      <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sit amet lectus ultricies, pharetra libero et, mattis eros. Nam ex est, lacinia id ligula ac, vehicula mollis ante. Vivamus sit amet tellus vestibulum, blandit lectus eget, luctus ipsum. Nam dui mauris, viverra id lorem sit amet, scelerisque dignissim erat. Sed consectetur placerat semper. Nullam quis dapibus libero. Aenean blandit suscipit magna, sed aliquam diam lacinia id.
      </p>
      <p>Ut posuere metus neque, in fringilla odio efficitur quis. Pellentesque pellentesque sed odio non ultrices. Vestibulum scelerisque nec lorem id pulvinar. Proin scelerisque euismod felis nec suscipit. Curabitur eu eros a mi tempor pharetra id varius velit. Quisque eleifend dictum felis eu pulvinar. Integer volutpat lorem a nunc porttitor, ut ornare arcu ultrices. Aenean nec molestie tortor. Nullam id est est. Nam tincidunt varius sapien id suscipit. Suspendisse in congue turpis. Vestibulum scelerisque mauris ut odio auctor imperdiet. Maecenas lobortis, justo ac mattis mattis, libero turpis dapibus erat, sed molestie sem ligula sit amet risus. Vestibulum non dui pulvinar, volutpat ex eu, aliquam sem. Pellentesque nulla turpis, rhoncus eget semper quis, bibendum ac tellus.
      </p>
      <p>Pellentesque tincidunt nunc eros, a porttitor urna vulputate a. Aliquam feugiat, elit a ultricies rhoncus, ipsum massa condimentum nunc, id aliquet elit est at mi. Nullam dignissim est aliquam orci efficitur, et rutrum augue mattis. In porta arcu eget elementum lobortis. Aenean aliquet suscipit nulla sit amet blandit. Proin faucibus tempus luctus. Ut quis odio nec risus gravida condimentum. Aenean vel dui lectus.
        </p>
    </div>
  </div>
</div>
<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque viverra posuere tortor, at lobortis nunc tempus sit amet. In at dui velit. Maecenas metus ligula, malesuada eu mi et, fermentum volutpat magna. Aliquam aliquet laoreet est eget ultrices. Donec rhoncus ultrices mi eget porttitor. Morbi ac orci vitae ex facilisis suscipit ut vel justo. Donec mattis nec nisl quis convallis. Etiam fringilla ipsum non eleifend condimentum. Nulla a vestibulum arcu.
</p><p>
Sed et varius quam. Praesent dignissim ex quis justo rhoncus tempus in ac elit. Aliquam velit velit, sagittis auctor massa in, rhoncus tincidunt lectus. Quisque condimentum venenatis leo eu aliquam. Proin condimentum bibendum justo, eu egestas justo. Curabitur eget lectus a elit consectetur tristique. Praesent congue sem at odio molestie, vitae hendrerit libero accumsan. Sed vitae ante tellus.</p>
         </div>
    );
  }
}

export default Home;