import React, { Component } from 'react';
//import './App.css';
import {withRouter} from "react-router-dom";
class SearchBox extends Component {
    constructor(props){
        super(props)
        this.handleChange=this.handleChange.bind(this)
        
    }
    handleChange(e){
        
        this.props.history.push("/searchList/"+e.target.value);
    }
  render() {
      
    return (
      <input onChange={this.handleChange}></input>
      
    );
  }
}

export default withRouter(SearchBox);
