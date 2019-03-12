import React, { Component } from 'react';
//import './App.css';
import {Route,Switch,Link} from 'react-router-dom'
import SearchList from './SearchList';
import Info from './Info'
import SearchBox from './SearchBox'
class App extends Component {
  constructor(props){
    super(props)
    this.state={
      isEmpty:true,searchContent:""
    }
    this.handleTextChange=this.handleTextChange.bind(this)
  }
  handleTextChange(value){
    if(value==""){
      this.setState({
        isEmpty:true,searchContent:""
      })
    }else{
      this.setState({
        isEmpty:false,searchContent:value
      })
    }
  }
  render() {
    return (
      <div>
        <SearchBox onTextChange={this.handleTextChange}/>
        <SearchList searchContent={this.state.searchContent}/>
        <Switch>
            <Route path="/info/:teacherId" component={Info}/>
        </Switch>
        
      </div>
    );
  }
}

export default App;
