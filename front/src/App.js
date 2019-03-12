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
      data:[]
    }
    this.handleTextChange=this.handleTextChange.bind(this)
    this.handleInfoInit=this.handleInfoInit.bind(this)
  }
  handleTextChange(value){
    if(value===""){
      this.setState({
        data:[]
      })
    }else{
      this.setState({isFetching: true})
      let url="http://localhost:18080/search?input="+value
      fetch(url)
        .then(response => response.json())
        .then(result => this.setState({data: result.data, isFetching: false}))
        .catch(e => console.log(e));
      
    }
  }
  handleInfoInit(){
    this.setState({
      data:[]
    })
  }
  render() {
    const InfoWithProps=(props)=>{
      return(
        <Info onInfoInit={this.handleInfoInit}/>
      )
    }
    return (
      <div>
        <SearchBox onTextChange={this.handleTextChange} isNeedClear={this.state.data===[]?true:false}/>
        <SearchList data={this.state.data}/>
        <Switch>
            <Route path="/info/:teacherId" render={InfoWithProps} />
        </Switch>
        
      </div>
    );
  }
}

export default App;
