import React, { Component } from 'react';

class Songs extends Component {

  
  constructor(props) {
    super(props);
    this.state = {name: ''};
    this.state = {description: ''};
    this.state = {rating: 1};
    this.state = {length: 1};

    this.state = {songs: []};
    this.headers = [
			{ key: 'id', label: 'Id'},
      { key: 'song', label: 'Song' },
      { key: 'player', label: 'Player' },
		];
  }

  componentDidMount() {
    fetch('http://localhost:8082/userSong/' + localStorage.username, {method: 'GET'})
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
          <br></br>
          <h2>List All My Songs</h2>
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
								  <td>{item.song}</td>
                  <td><img src="musicPlayer.png" width="150px" height="40px" alt="Pocetna"></img></td>
								</tr>
											)
							})
						}
					</tbody>
				</table>

        <br></br><br></br>

        </div>
    );
  }
}

export default Songs;