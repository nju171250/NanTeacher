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
import tan90 from './tan90.gif';

import Cookies from 'universal-cookie';
const cookies = new Cookies();
class App extends Component {
  constructor(props){
    super(props)
    this.state={
      isFetching:false,
      data:[],
      searchText:"",
      token:"aaa"
    }
    this.handleTextChange=this.handleTextChange.bind(this)
    this.handleInfoInit=this.handleInfoInit.bind(this)
    
  }
  componentDidMount(){
    var this_=this
    var token="";
    var data={
      openid:"nanTeacher",
      password:"njuTeacher"
    }
    Axios.post(global.constants.baseUrl+"/login",data,
        {headers:{
          "Content-Type":"application/json; charset=UTF-8"
        }})
        .then(function (response) {
          console.log(response)

          token=response.headers.authorization
          cookies.set('token',token,{path:'/'})
          this_.setState({
            token:token
          })
        })
        .catch(function (error) {
          if(error.response){
            if(error.response.status===401){
              Axios.post(global.constants.baseUrl+"/register",data,
        {headers:{
          "Content-Type":"application/json; charset=UTF-8"
        }})
        .then(function (response) {
          Axios.post(global.constants.baseUrl+"/login",data,
        {headers:{
          "Content-Type":"application/json; charset=UTF-8"
        }})
        .then(function (response) {
          console.log(response)
          token=response.headers.authorization
          cookies.set('token',token,{path:'/'})
          this_.setState({
            token:token
          })
        })
        })
        .catch(function (error) {
          console.log(error)
          
        }); 
            }
          }

        }); 
        
       
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
      fetch(url,{
        headers:{
          Authorization:this.state.token
        }
      })
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
    console.log(this.state.token)
    const InfoWithProps=(props)=>{
      return(
        <Info onInfoInit={this.handleInfoInit} token={this.state.token} props={props}/>
      )
    }
    const MarkScoreWithProps=(props)=>{
      return(
        <MarkScore token={this.state.token} props={props}/>
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
    <Route path="/markScore/:teacherId" render={MarkScoreWithProps}/></Switch>:<div></div>}
            
        
        
      </div>
      
    );
  }
}

export default App;
