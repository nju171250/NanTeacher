import React, { Component } from 'react';
import './Info.css';
import Comments from "./Comments"
import {Link} from "react-router-dom"
import "./Config"
class Info extends Component {
    constructor(props){
        super(props)
        this.state={
          data:{
            courses:[]
          }
        }
    }
    componentDidMount(){
      this.props.onInfoInit();
      this.setState({isFetching: true})
      let url=global.constants.baseUrl+"/getTeacherInfo?teacherId="+this.props.props.match.params.teacherId
      fetch(url)
        .then(response => response.json())
        .then(result => this.setState({data: result.data, isFetching: false}))
        .catch(e => console.log(e));
    }
  render() {
      console.log(this.props)
    return (
      <div className="Info">
          <div className="basicInfo">
            <div className="left">
              <p className="name">{this.state.data.name}</p>
              <p className="collegeName">{this.state.data.collegeName}</p>
              <p className="department">{this.state.data.department}</p>
            
            </div>
            <div className="right">
               <p className="score">{new Number(this.state.data.score).toFixed(2)}</p>
               <p className="participantNum">{this.state.data.participantNum}个人参与评分</p>
            </div>
          </div>
          <div className="courses">
            <p className="title">所教课程</p>
            {this.state.data.courses.map(p=>
                 <p className="course">{p.name}</p>
              )}
          </div>
          <Comments teacherId={this.props.props.match.params.teacherId}/>
          <Link to={"/markScore/"+this.props.props.match.params.teacherId}>
          <div className="markScore">
            评分
          </div>
          </Link>
      </div>
      
    );
  }
}

export default Info;
