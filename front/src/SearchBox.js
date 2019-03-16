import React, { Component } from 'react';
//import './App.css';
import {withRouter} from "react-router-dom";
import './SearchBox.css'
import {Input} from "element-react"
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
      <div className="SearchBox">
        <Input icon="search" onChange={this.handleChange} placeholder="南老师" value={this.props.searchText}></Input>
        {/* <input onChange={this.handleChange} placeholder="  南老师" value={this.props.searchText}></input> */}
      </div>
      
      
    );
  }
}

export default withRouter(SearchBox);
