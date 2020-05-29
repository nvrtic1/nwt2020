import React, { Component } from 'react';

class addSinger extends Component {

  
  constructor(props) {
    super(props);
    this.state = {name: ''};
    this.state = {biography: ''};
    this.state = {singers: []};
    this.headers = [
			{ key: 'id', label: 'Id'},
      { key: 'name', label: 'Name' },
      { key: 'biography', label: 'Biography' },
      { key: 'birthday', label: 'Birthday' },
		];
    this.handleChangeName = this.handleChangeName.bind(this);
    this.handleChangeBiography = this.handleChangeBiography.bind(this);
    this.handleChangeBirthday = this.handleChangeBirthday.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChangeName(event) {
    this.setState({name: event.target.value});
  }

  handleChangeBiography(event) {
    this.setState({biography: event.target.value});
  }

  handleChangeBirthday(event) {
    this.setState({birthday: event.target.value});
  }

  handleSubmit(event) {
    console.log(this.state.name)
    console.log(this.state.biography)
    console.log(this.state.birthday)


	  event.preventDefault();
	  fetch('http://localhost:8081/singers', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
			body: JSON.stringify(
        {
          "name": this.state.name,
          "biography": this.state.biography,
          "birthday": this.state.birthday
  }
      )
		}).then(response => {
				if(response.status === 200) {
          alert("New singer saved successfully");
          
          fetch('http://localhost:8081/singers')
          .then(response => {
            return response.json();
          }).then(result => {
            console.log(result);
            this.setState({
              singers:result
            });
          });
				}
			});
  }

  deleteSinger(id) {
      console.log(id);
      if(window.confirm("Are you sure want to delete?")) {
        fetch('http://localhost:8081/singers/' + id, {
          method : 'DELETE'//,
          //mode: 'no-cors',
          /*headers : {
            'Access-Control-Allow-Origin': '*',
            'Content-Type' : 'text/plain'
          }*/
        }).then(response => { 
            if(response.status === 200) {
              alert("Singer deleted successfully");
              
              fetch('http://localhost:8081/singers')
              .then(response => {
                return response.json();
              }).then(result => {
                console.log(result);
                if(result.status===404)
                {
                  this.setState({
                    singers: []
                  });
                }
                else{
                this.setState({
                  singers:result
                });
              }
              });
            } 
         });
      }
  }

  deleteAllSingers() {
    if(window.confirm("Are you sure want to delete all singers?")) {
      fetch('http://localhost:8081/singers/', {
        method : 'DELETE'//,
        //mode: 'no-cors',
        /*headers : {
          'Access-Control-Allow-Origin': '*',
          'Content-Type' : 'text/plain'
        }*/
      }).then(response => { 
          if(response.status === 200) {
            alert("All singers deleted successfully");
            
            fetch('http://localhost:8081/singers')
            .then(response => {
              return response.json();
            }).then(result => {
              console.log(result);
              if(result.status===404)
              {
                this.setState({
                  singers:[]
                });
              }
              else
              {
                this.setState({
                  singers:result
                });
              }
            });
          } 
       });
    }
}

  componentDidMount() {
    fetch('http://localhost:8081/singers', {method: 'GET'})
			.then(response => {
				return response.json();
			}).then(result => {
        console.log(result);
        if(result.status===404) 
        {
          this.setState({
            singers:[]
          });
        }
        else{
          this.setState({
            singers:result
          });
        }

			});
  }

  render() {
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
          <h2>Singers</h2>
          <br></br><br></br>
          <form onSubmit={this.handleSubmit}>
          <div className="form-group">
            <label>Name</label>
              <input type="text" name="name" className="form-control" value={this.state.name} onChange={this.handleChangeName} required/>
              </div>
              <div className="form-group">
              <label>Biography</label>
              <textarea name="biography" className="form-control" value={this.state.biography} onChange={this.handleChangeBiography} required/>
              </div>
              <div className="form-group">
              <label>Birthday</label>
              <input type="date" name="birthday" className="form-control" value={this.state.birthday} onChange={this.handleChangeBirthday} required/>
              </div>
            <button type="submit" className="btn btn-primary">Registruj novog pjevača</button>
          </form>
          <br></br>          <br></br>
          <h2>List All Singers</h2>
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
							this.state.singers.map(function(item, key) {
							return (
								<tr key = {key}>
								  <td>{item.singerId}</td>
								  <td>{item.name}</td>
                  <td>{item.biography}</td>
                  <td>{(item.birthday).toString().substring(0,10)}</td>
                  <td>
                  <button className="btn btn-link" onClick={this.deleteSinger.bind(this, item.singerId)}>Delete</button>
								  </td>
								</tr>
											)
							}.bind(this))
						}
					</tbody>
				</table>

          <br></br>
        <button className="btn btn-danger" onClick={this.deleteAllSingers.bind(this)}>Delete all singers</button>

          <br></br><br></br>
        </div>
    );
  }
}

export default addSinger;