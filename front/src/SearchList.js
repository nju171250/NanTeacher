import React, { Component } from 'react';
//import './App.css';
import './SearchList.css'
import SearchItem from './SearchItem'
import {Link} from "react-router-dom"
class SearchList extends Component {
    constructor(props){
        super(props)
        this.fetchQuotes=this.fetchQuotes.bind(this)
        
    }
    fetchQuotes(){
      this.setState({isFetching: true})
      let url="http://localhost:18080/search?input="+this.props.searchContent
      fetch(url)
        .then(response => response.json())
        .then(result => this.setState({data: result.data, isFetching: false}))
        .catch(e => console.log(e));
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
