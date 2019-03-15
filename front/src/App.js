import React, { Component } from 'react';
//import './App.css';
import {Route,Switch,Link} from 'react-router-dom'
import SearchList from './SearchList';
import Info from './Info'
import SearchBox from './SearchBox'
import MarkScore from './MarkScore'
import "./Config"
import { BallScaleRippleMultiple } from 'react-pretty-loading';
class App extends Component {
  constructor(props){
    super(props)
    this.state={
      data:[]
    }
    this.handleTextChange=this.handleTextChange.bind(this)
    this.handleInfoInit=this.handleInfoInit.bind(this)
  }
  componentDidMount(){
    this.setState({isFetching: true})
    let url=global.constants.baseUrl+"/login?openid=njuTeacher&password=njuTeacher"
    fetch(url)
      .then(response => response.json())
      .then(result => {
        console.log(result)
        this.setState({isFetching:false})
    }
      )
      .catch(e => console.log(e));
  }
  handleTextChange(value){
    console.log(value)
    if(value===""||value===" "){
      this.setState({
        data:[]
      })
    }else{
      this.setState({isFetching: true})
      let url=global.constants.baseUrl+"/search?input="+value
      fetch(url)
        .then(response => response.json())
        .then(result => {
          if(result.status!==undefined){
            this.setState({data: [], isFetching: false})
          }else{
            this.setState({data: result, isFetching: false})
          }
          
        })

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
        <Info onInfoInit={this.handleInfoInit} props={props}/>
      )
    }
    return (
      <div>
        <SearchBox onTextChange={this.handleTextChange} isNeedClear={this.state.data===[]?true:false}/>
        <BallScaleRippleMultiple loading={this.state.isFetching } color="#6A005F" center/>
        <SearchList data={this.state.data}/>
        <Switch>
            <Route path="/info/:teacherId" render={InfoWithProps} />
            <Route path="/markScore/:teacherId" component={MarkScore}/>
        </Switch>
        
      </div>
    );
  }
}

export default App;
