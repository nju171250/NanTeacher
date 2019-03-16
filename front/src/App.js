import React, { Component } from 'react';
import './App.css';
import {Route,Switch,Router} from 'react-router-dom'
import SearchList from './SearchList';
import Info from './Info'
import SearchBox from './SearchBox'
import MarkScore from './MarkScore'
import "./Config"
import Axios from 'axios';
import { BallScaleRippleMultiple } from 'react-pretty-loading';
import tan90 from './tan90.gif'
class App extends Component {
  constructor(props){
    super(props)
    this.state={
      isFetching:false,
      data:[],
      searchText:""
    }
    this.handleTextChange=this.handleTextChange.bind(this)
    this.handleInfoInit=this.handleInfoInit.bind(this)
  }
  componentDidMount(){
    this.setState({isFetching: true})
    var data={
      openid:"nanTeacher",
      password:"nanTeacher"
    }
    Axios.post(global.constants.baseUrl+"/login",data,
        {headers:{
          "Content-Type":"application/json; charset=UTF-8"
        }})
        .then(function (response) {
          this.setState({isFetching:false})
          console.log(response);
        })
        .catch(function (error) {
          
          console.log(error);
        }); 
        this.setState({isFetching:false})
  }
  handleTextChange(value){
    this.setState({
      searchText:value
    })
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
      data:[],
      searchText:""
    })

  }
  render() {
    const InfoWithProps=(props)=>{
      return(
        <Info onInfoInit={this.handleInfoInit} props={props}/>
      )
    }
    return (
      
      <div className="App">
        <SearchBox onTextChange={this.handleTextChange} searchText={this.state.searchText}/>
        <BallScaleRippleMultiple loading={this.state.isFetching } color="#6A005F" center/>
        {
          (this.state.searchText!=""&&this.state.searchText!=" "&&this.state.data.length===0&&this.state.isFetching===false)?<div className="notFound"><p>小南找不到了</p><img src={tan90}/></div>:<p></p>
        }
        <SearchList data={this.state.data}/>
        
          {!(this.state.searchText!=""&&this.state.searchText!=" ")?<Switch><Route path="/info/:teacherId" render={InfoWithProps} />
    <Route path="/markScore/:teacherId" component={MarkScore}/></Switch>:<div></div>}
            
        
        
      </div>
      
    );
  }
}

export default App;
