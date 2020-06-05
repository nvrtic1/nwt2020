import React, { Component } from 'react';

class addSong extends Component {

  
  constructor(props) {
    super(props);


    this.state = {songs: []};
    this.headers = [
			{ key: 'id', label: 'Id'},
      { key: 'name', label: 'Name' },
      { key: 'description', label: 'Description' },
      { key: 'rating', label: 'Rating' },
      { key: 'length', label: 'Length' },
		];
  }




  buySong(name) {
      console.log(name);
      if(window.confirm("Are you sure want to buy?")) {
        fetch('http://localhost:8082/userSong', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(
            {
              "email": localStorage.username,
              "song": name
      }
          )
        }).then(response => { 
            if(response.status === 200) {
              alert("Song buy successfully");
            } 
         });
      }
  }

  componentDidMount() {
    fetch('http://localhost:8082/songs', {method: 'GET'})
			.then(response => {
				return response.json();
			}).then(result => {
        console.log(result);
        if(result.status===404)
      {
				this.setState({
					songs:[]
				});
      }
      else{
				this.setState({
					songs:result
				});
      }

			});
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
          
          <h2>List All Songs</h2>
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
							this.state.songs.map(function(item, key) {
							return (
								<tr key = {key}>
								  <td>{item.id}</td>
								  <td>{item.name}</td>
                  <td>{item.description}</td>
                  <td>{item.rating}</td>
                  <td>{item.length}</td>
                  <td>
                  <button className="btn btn-link" onClick={this.buySong.bind(this, item.name)}>Buy Song</button>
								  </td>
								</tr>
											)
							}.bind(this))
						}
					</tbody>
				</table>


        <br></br><br></br>

        </div>
    );
  }
}

export default addSong;