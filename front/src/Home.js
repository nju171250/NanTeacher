import React, { Component } from 'react';
//import './App.css';
import {Link} from 'react-router-dom'


class Home extends Component {
    constructor(props){
        super(props)
    }
    
  render() {
    
    return (
        <div className="home">
        <div className="title">热门老师</div>
        <Link to="/info/556"><p className="hotTeacher">陈道蓄</p></Link> 
        <Link to="/info/470"><p className="hotTeacher">胡昊</p></Link> 
        <Link to="/info/567"><p className="hotTeacher">刘钦</p></Link> 
        <Link to="/info/475"><p className="hotTeacher">陶先平</p></Link> 
        <Link to="/info/555"><p className="hotTeacher">王浩然</p></Link> 
        <Link to="/info/488"><p className="hotTeacher">黄书剑</p></Link> 
       </div>
    );
  }
}

export default Home;
