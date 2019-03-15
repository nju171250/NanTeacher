import React, { Component } from 'react';
import './Info.css';
import Comments from "./Comments"
import {Link} from "react-router-dom"
import "./Config"
import { BallScaleRippleMultiple } from 'react-pretty-loading';
class Info extends Component {
    constructor(props){
        super(props)
        this.state={
          data:{
            teacher:{
              teacherName:"",
            teacherCollegename:"",
            teacherDepartment:"",
            teacherScore:0
            },
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
        .then(result => {
          if(result.status!==undefined){
            this.setState({isFetching: false})
          }else{
            this.setState({data: result, isFetching: false})
          }
          
        })
        .catch(e => console.log(e));
    }
    handleCommentsInit(commentNum){
      this.setState({
        commentNum: commentNum
      })
    }
  render() {
    
      console.log(this.props)
      console.log(this.state)
    return (
      <div className="Info">
          <BallScaleRippleMultiple loading={this.state.isFetching } color="#6A005F" center/>
          <div className="basicInfo">
            <div className="left">
              <p className="name">{this.state.data.teacher.teacherName}</p>
              <p className="collegeName">{this.state.data.teacher.teacherCollegename}</p>
              <p className="department">{this.state.data.teacher.teacherDepartment}</p>
            
            </div>
            <div className="right">
               <p className="score">{new Number(this.state.data.teacher.teacherScore).toFixed(2)}</p>
               <p className="participantNum">{this.state.commentNum}个人参与评分</p>
            </div>
          </div>
          <div className="courses">
            <p className="title">所教课程</p>
            {this.state.data.courses.map(p=>
                 <p className="course">{p.courseName}</p>
              )} 
          </div>
          <Comments teacherId={this.props.props.match.params.teacherId} onCommentsInit={this.handleCommentsInit.bind(this)}/>
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
