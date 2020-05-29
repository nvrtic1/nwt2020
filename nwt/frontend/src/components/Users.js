import React, { Component } from 'react';
import {
  Redirect
 } from 'react-router-dom';

class Users extends Component {

  state = {
    redirect: false,
  }


  
  constructor(props) {
    super(props);
    this.state = {firstname: ''};
    this.state = {lastname: ''};
    this.state = {email: 1};

    this.state = {users: []};
    this.headers = [
			{ key: 'id', label: 'Id'},
      { key: 'firstname', label: 'Firstname' },
      { key: 'lastname', label: 'Lastname' },
      { key: 'email', label: 'Email' },
      { key: 'rola', label: 'Rola' },
		];

  }



  deleteUser(id) {
      console.log(id);
      if(window.confirm("Are you sure want to delete?")) {
        fetch('http://localhost:8083/delete/users?id=' + id, {
          method : 'DELETE',
          redirect: 'follow'
          //mode: 'no-cors',
          /*headers : {
            'Access-Control-Allow-Origin': '*',
            'Content-Type' : 'text/plain'
          }*/
        }).then(response => { 
            if(response.status === 200) {
              alert("User deleted successfully");
              
              fetch('http://localhost:8083/users')
              .then(response => {
                return response.json();
              }).then(result => {
                console.log(result);
                if(result.length===0)
                {
                  this.setState({ redirect: true })
                }
                else
                {
                  this.setState({
                    users:result
                  });
                }
              });
            } 
         });
      }
  }

  changeRoleUser(id, firstname, lastname, email, password, rola) {
    console.log(id);
    console.log(firstname);
    console.log(rola);


    if(rola === "ROLE_USER")
    {
      rola="ROLE_ADMIN"
    }
    else if(rola === "ROLE_ADMIN")
    {
      rola="ROLE_USER"
    }

    if(window.confirm("Are you sure want to change role?")) {
      fetch('http://localhost:8083/update?id=' + id, {
        method : 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(
          {
            "firstName": firstname,
            "lastName": lastname,
            "email": email,
            "password": password,
            "roles": [
                {
                    "name": rola
                }
            ]

    }
        )
        //mode: 'no-cors',
        /*headers : {
          'Access-Control-Allow-Origin': '*',
          'Content-Type' : 'text/plain'
        }*/
      }).then(response => { 
          if(response.status === 200) {
            alert("Role change successfully");
            fetch('http://localhost:8083/users')
            .then(response => {
              return response.json();
            }).then(result => {
              console.log(result);
              if(result.status===404)
              {
                this.setState({
                  users:[]
                });
              }
              else
              {
                this.setState({
                  users:result
                });
              }
            });
          } 
       });
    }
}

  

  componentDidMount() {
    fetch('http://localhost:8083/users', {method: 'GET'})
			.then(response => {
				return response.json();
			}).then(result => {
        console.log(result);
        if(result.status===404)
      {
				this.setState({
					users:[]
				});
      }
      else{
				this.setState({
					users:result
				});
      }

			});
  }

  render() {

    const { redirect } = this.state;

    console.log(redirect);
    if (redirect) {
      return <Redirect to='/' />;}

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
          <h2>List All Users</h2>
          <br></br><br></br>
          <table className="table table-striped">
					<thead>
						<tr>
						{
							this.headers.map(function(h) {
								return (
									<th key = {h.key}>{h.label}</th>
								)
							})
						}
						</tr>
					</thead>
					<tbody>
						{
							this.state.users.map(function(item, key) {
							return (
								<tr key = {key}>
								  <td>{item.id}</td>
								  <td>{item.firstName}</td>
                  <td>{item.lastName}</td>
                  <td>{item.email}</td>
                  <td>{item.roles[0].name.toString().substring(5)}</td>
                  <td><button className="btn btn-link" onClick={this.changeRoleUser.bind(this, item.id, item.firstName, item.lastName, item.email, item.password, item.roles[0].name)}>Promijeni rolu</button></td>
                  <td><button className="btn btn-dark" onClick={this.deleteUser.bind(this, item.id)}>Delete</button></td>
								</tr>
											)
							}.bind(this))
						}
					</tbody>
				</table>

        <br></br>

        </div>
    );
  }
}

export default Users;