import React, { Component } from 'react';
//import './App.css';
import './SearchList.css'
import SearchItem from './SearchItem'
import {Link} from "react-router-dom"
class SearchList extends Component {
    constructor(props){
        super(props)
        
        
    }
    
  
  render() {
    
    return (
        this.props.data.map(p=>
        <Link to={"info/"+p.teacherId}><SearchItem data={p}/></Link>
        
        )
    );
  }
}

export default SearchList;
