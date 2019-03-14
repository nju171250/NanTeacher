import React, { Component } from 'react';
//import './App.css';
import './SearchItem.css'


class SearchItem extends Component {
    constructor(props){
        super(props)
    }
  render() {
    
    return (
      <div className="SearchItem">
         <div className="left">
         <p className="name">{this.props.data.teacherName}</p>
         <p className="department">{this.props.data.teacherDepartment}</p>
         </div>
         <p className="score">{this.props.data.teacherScore}</p>
      </div>
      
    );
  }
}

export default SearchItem;
