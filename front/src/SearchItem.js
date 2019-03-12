import React, { Component } from 'react';
//import './App.css';


class SearchItem extends Component {
    constructor(props){
        super(props)
    }
  render() {
      
    return (
      <div className="SearchList">
         <p>{this.props.name}</p>
         <p>{this.props.department}</p>
         <p>{this.props.score}</p>
      </div>
      
    );
  }
}

export default SearchItem;
