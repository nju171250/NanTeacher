import React, { Component } from 'react';
//import './App.css';
import {withRouter} from "react-router-dom";
import './SearchBox.css'
class SearchBox extends Component {
    constructor(props){
        super(props)
        this.handleChange=this.handleChange.bind(this)
        
    }
    handleChange(e){
      this.props.onTextChange(e.target.value)
    }
  render() {
      
    return (
      <input onChange={this.handleChange} placeholder="" ></input>
      
    );
  }
}

export default withRouter(SearchBox);
