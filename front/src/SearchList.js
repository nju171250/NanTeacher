import React, { Component } from 'react';
//import './App.css';
import './SearchList.css'
import SearchItem from './SearchItem'
import {Link,withRouter} from "react-router-dom"

class SearchList extends Component {
    constructor(props){
        super(props)
        
        
    }
    handleItemClick(teacherId){
      this.props.history.push("/info/"+teacherId);
      setTimeout(() => {
        window.location.reload();
    }, 0);
    }
  
  render() {
    console.log(this.props)
    return (
        this.props.data.map(p=>
        <SearchItem data={p} onClick={this.handleItemClick.bind(this)}/>
        
        )
    );
  }
}

export default withRouter(SearchList);
