import React, { Component } from 'react';
//import './App.css';
import './SearchList.css'
import SearchItem from './SearchItem'
import {Link,withRouter} from "react-router-dom"
import {Menu, Table} from "element-react"

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
      <div>
        {this.props.data.map((p, index)=>
          <div class="searchResult" onClick={this.handleItemClick.bind(this, p.teacherId)}>
            {p.teacherName}
            <span class="teacherScore">{p.teacherScore}</span>
          <div class="department">
            {p.teacherDepartment}
          </div>
          </div>
        )}
      </div>
        // this.props.data.map(p=>
        // <SearchItem data={p} onClick={this.handleItemClick.bind(this)}/>
        
        // )
    );
  }
}

export default withRouter(SearchList);
