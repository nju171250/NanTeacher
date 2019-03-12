import React, { Component } from 'react';
//import './App.css';
import './SearchList.css'

class SearchList extends Component {
    constructor(props){
        super(props)
    }
  render() {
    
    return (
      <div className="SearchList">
         <p>{this.props.searchContent}</p>
      </div>
      
    );
  }
}

export default SearchList;
