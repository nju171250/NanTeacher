import React, { Component } from 'react';
//import './App.css';

class Info extends Component {
    constructor(props){
        super(props)
    }
    componentDidMount(){
      this.props.onInfoInit()
    }
  render() {
      
    return (
      <p>info: </p>
      
    );
  }
}

export default Info;
