import React, { Component } from 'react';
import './Info.css';
import Comments from "./Comments"
import {Link} from "react-router-dom"
import "./Config"
import { BallScaleRippleMultiple } from 'react-pretty-loading';
import { Tabs, Collapse } from 'element-react';
import 'element-theme-default';
import Axios from 'axios';

import Cookies from 'universal-cookie';
const cookies = new Cookies();
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
          },
          comments:[]
        }
    }
    componentDidMount(){
      
      this.props.onInfoInit();
      this.getComments();
      this.setState({isFetching: true})
      let url=global.constants.baseUrl+"/getTeacherInfo?teacherId="+this.props.props.match.params.teacherId
      fetch(url,{
        headers:{
          Authorization:cookies.get('token')
        }
      })
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
    getComments(){
      var this_ = this
      var url = global.constants.baseUrl + "/getCommentInfo?teacherId=" + this.props.props.match.params.teacherId
      Axios.get(url).then(response =>{
        this_.setState({comments: response.data})
        console.log(this_.state.comments)
      })
    }
    acomment(comment, p){
      console.log(comment)
      if(comment.courseId == p.courseId)
      return(
      <div class="commentDetail">
        <div class="commentContent">{comment.commentContent}</div>
        <div class="commentTime">发布于{comment.commentTime===null||comment.commentTime.length<10?comment.commentTime:comment.commentTime.substring(0,10)}</div>
      </div>
      )
      else
        return null
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
          <Tabs activeName="1" onTabClick={ (tab) => console.log(tab.props.name) }>
            <Tabs.Pane label="最新评论" name="1">
              {this.state.comments.map(comment=>
                  <div class="newcommentDetail">
                    <div class="commentContent">{comment.commentContent}</div>
                    <div class="commentTime">发布于{comment.commentTime===null||comment.commentTime.length<10?comment.commentTime:comment.commentTime.substring(0,10)}</div>
                  </div>
              )}
            </Tabs.Pane>
            <Tabs.Pane label="所教课程" name="2">
              <Collapse value={this.state.data.activeName}>
                {this.state.data.courses.map(p=>
                  <Collapse.Item title={p.courseName} name={p.courseName}>
                    {this.state.comments.map(comment=>
                      // {comment.courseId == p.courseId &&
                        // <div class="commentDetail">
                        //   <div class="commentContent">{comment.commentContent}</div>
                        //   <div class="commentTime">发布于{comment.commentTime===null||comment.commentTime.length<10?comment.commentTime:comment.commentTime.substring(0,10)}</div>
                        // </div>
                        this.acomment(comment, p)
                      // }
                    )}
                  </Collapse.Item>
                )} 
              </Collapse>
            </Tabs.Pane>
          </Tabs>
          {/* <div className="courses">
            <p className="title">所教课程</p>
            {this.state.data.courses.map(p=>
                 <p className="course">{p.courseName}</p>
              )} 
          </div> */}
          {/* <Comments teacherId={this.props.props.match.params.teacherId} onCommentsInit={this.handleCommentsInit.bind(this)}/> */}
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
