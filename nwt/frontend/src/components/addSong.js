import React, { Component } from 'react';

class addSong extends Component {

  
  constructor(props) {
    super(props);
    this.state = {name: ''};
    this.state = {description: ''};
    this.state = {rating: 1};
    this.state = {length: 1};

    this.state = {songs: []};
    this.headers = [
			{ key: 'id', label: 'Id'},
      { key: 'name', label: 'Name' },
      { key: 'description', label: 'Description' },
      { key: 'rating', label: 'Rating' },
      { key: 'length', label: 'Length' },
		];
    this.handleChangeName = this.handleChangeName.bind(this);
    this.handleChangeDescription = this.handleChangeDescription.bind(this);
    this.handleChangeRating = this.handleChangeRating.bind(this);
    this.handleChangeLength = this.handleChangeLength.bind(this);

    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChangeName(event) {
    this.setState({name: event.target.value});
  }

  handleChangeDescription(event) {
    this.setState({description: event.target.value});
  }

  handleChangeRating(event) {
    this.setState({rating: event.target.value});
  }

  handleChangeLength(event) {
    this.setState({length: event.target.value});
  }

  handleSubmit(event) {
    console.log(this.state.name)
    console.log(this.state.description)
    console.log(this.state.rating)
    console.log(this.state.length)


	  event.preventDefault();
	  fetch('http://localhost:8082/songs', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
			body: JSON.stringify(
        {
          "name": this.state.name,
          "description": this.state.description,
          "rating": this.state.rating,
          "length": this.state.length
  }
      )
		}).then(response => {
				if(response.status === 200) {
          alert("New song saved successfully");
          
          fetch('http://localhost:8082/songs')
          .then(response => {
            return response.json();
          }).then(result => {
            console.log(result);
            this.setState({
              songs:result
            });
          });
				}
			});
  }

  deleteSong(id) {
      console.log(id);
      if(window.confirm("Are you sure want to delete?")) {
        fetch('http://localhost:8082/songs/' + id, {
          method : 'DELETE'//,
          //mode: 'no-cors',
          /*headers : {
            'Access-Control-Allow-Origin': '*',
            'Content-Type' : 'text/plain'
          }*/
        }).then(response => { 
            if(response.status === 200) {
              alert("Song deleted successfully");
              
              fetch('http://localhost:8082/songs')
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
                else
                {
                  this.setState({
                    songs:result
                  });
                }
              });
            } 
         });
      }
  }

  deleteAllSongs() {
    if(window.confirm("Are you sure want to delete all songs?")) {
      fetch('http://localhost:8082/songs/', {
        method : 'DELETE'//,
        //mode: 'no-cors',
        /*headers : {
          'Access-Control-Allow-Origin': '*',
          'Content-Type' : 'text/plain'
        }*/
      }).then(response => { 
          if(response.status === 200) {
            alert("Songs deleted successfully");
            
            fetch('http://localhost:8082/songs')
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
              else
              {
                this.setState({
                  songs:result
                });
              }
            });
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
          <li><a href='/admin' className="nav-link"> Home </a></li>
          <li><a href='/users' className="nav-link">Users</a></li>
            <li><a href='/addSinger' className="nav-link">Singers</a></li>
            <li><a href='/addSong' className="nav-link">Songs</a></li>
            <li><a href='/signOut' className="nav-link">Sign Out</a></li>
          </ul>
          </nav>
          <hr />
          <h2>Songs</h2>
          <form onSubmit={this.handleSubmit}>
              <div className="form-group">
              <label>Name</label>
              <input type="text" name="name"  className="form-control"  value={this.state.name} onChange={this.handleChangeName} required/>
              </div>
              <div className="form-group">
              <label>Description</label>
              <textarea name="description"  className="form-control"  value={this.state.description} onChange={this.handleChangeDescription} required/>
              </div>
              <div className="form-group">
              <label>Rating</label>
              <input type="number" name="rating"  className="form-control"  value={this.state.rating} onChange={this.handleChangeRating} min="1" max="5" required/>
              </div>
              <div className="form-group">
              <label>Length</label>
              <input type="number" name="length"  className="form-control"  value={this.state.length} onChange={this.handleChangeLength} min="1" required/>
              </div>
            <button type="submit" className="btn btn-primary">Registruj novog pjesmu</button>
          </form>
          <br></br>          <br></br>
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
                  <button className="btn btn-link" onClick={this.deleteSong.bind(this, item.id)}>Delete</button>
								  </td>
								</tr>
											)
							}.bind(this))
						}
					</tbody>
				</table>

        <br></br>

        <button className="btn btn-danger" onClick={this.deleteAllSongs.bind(this)}>Delete all songs</button>


        <br></br><br></br>

        </div>
    );
  }
}

export default addSong;