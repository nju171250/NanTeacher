import React, { Component } from 'react';
//import './App.css';

class SearchList extends Component {
    constructor(props){
        super(props)
    }
  render() {
      
    return (
      <p>content: {this.props.match.params.searchContent}</p>
      
    );
  }
}

export default SearchList;
