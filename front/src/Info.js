import React, { Component } from 'react';
import './Info.css';
import Comments from "./Comments"
import {Link} from "react-router-dom"
import "./Config"
import { BallScaleRippleMultiple } from 'react-pretty-loading';
import { Tabs, Collapse } from 'element-react';
import 'element-theme-default';
import Axios from 'axios';
import tan90 from './tan90.gif';
import _ from 'lodash';

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
          comments:[],
          activeName:[]
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
    fetchFavouriteSituation(commentsId){
      var this_ = this
      var favouriteInfos=[];
      var data = {
        openid: 'aaa',
        commentIds: commentsId
      };

      
      Axios.post(global.constants.baseUrl+"/getUserDoFavouriteSituation",data,
      {headers:{
        "Content-Type":"application/json; charset=UTF-8",
        
            "Authorization":cookies.get('token')
          
        
      }})
      .then(result=>{
        
        favouriteInfos=result.data;
        console.log(this.state.comments)
        var merge=_.map(this.state.comments,function(item){
           var res=_.merge(item,_.find(favouriteInfos,{commentId:item.commentId}));
           console.log(res)
           return res;
        });
        console.log(merge)
        this.setState({
          
            comments:merge
          
        })
      })
      .catch(function (error) {
        console.log(error);
      }); 
      
      
    }
    getComments(){
      var this_ = this
      var url = global.constants.baseUrl + "/getCommentInfo?teacherId=" + this.props.props.match.params.teacherId
      Axios.get(url,{
        headers:{
          Authorization:cookies.get('token')
        }
      }).then(response =>{
        this_.setState({comments: response.data})
        this_.fetchFavouriteSituation(this_.state.comments.map(p=>
          p.commentId
        ));
        console.log(this_.state.comments)
      })
    }
    handleLikeNum(num){
      if(num>999){
        return (num/1000.0).toFixed(1)+"k"
      }
      else{
        return num
      }
    }
    acomment(comment, p){
      if(comment.courseId === p.courseId){
        return(
        <div class="newcommentDetail">
          <div>
          <div class="commentContent">{comment.commentContent}</div>
          <div class="commentTime">发布于{comment.commentTime===null||comment.commentTime.length<10?comment.commentTime:comment.commentTime.substring(0,10)}</div>
          </div>
          <div className="right">
            <div className="like" onClick={this.thumbsUp.bind(this,comment.commentId,1)} style={this.getLikeCssStyle(comment.favouriteSituation,true)}>
              <i className="el-icon-caret-top"/>
              <p className="likeNum">赞同{this.handleLikeNum(comment.likeNum)}</p>
            </div>
            <div className="dislike" onClick={this.thumbsUp.bind(this,comment.commentId,0)} style={this.getLikeCssStyle(comment.favouriteSituation,false)}>
            <i className="el-icon-caret-bottom"/>
            </div>
          </div>
        </div>
        )
      }
    }
    getLikeText(favouriteSituation){
      if(favouriteSituation===true) return '已赞同'
      else return '赞同'
    }
    newestComment(){
      if(this.state.comments.length > 0)
        return(
          this.state.comments.map(comment=>
            <div class="newcommentDetail">
              <div>
                <div class="commentContent">{comment.commentContent}</div>
                <div class="commentTime">发布于{comment.commentTime===null||comment.commentTime.length<10?comment.commentTime:comment.commentTime.substring(0,10)}</div>
              </div>
              <div className="right">
                   <div className="like" onClick={this.thumbsUp.bind(this,comment.commentId,1)} style={this.getLikeCssStyle(comment.favouriteSituation,true)}>
                     <i className="el-icon-caret-top"/>
                     <span className="likeNum"> {this.getLikeText(comment.favouriteSituation)}{this.handleLikeNum(comment.likeNum)}</span>
                     
                    </div>
                   <div className="dislike" onClick={this.thumbsUp.bind(this,comment.commentId,0)} style={this.getLikeCssStyle(comment.favouriteSituation,false)}>
                    <i className="el-icon-caret-bottom"/>
                   </div>
              </div>
            </div>
        )
      )
      else 
        return(
          <div>
            <div className="notFound"><p>小南找不到该老师的评价</p><img src={tan90}/></div>
          </div>
        )
    }
    getCommentNumByCourseId(courseId){
      var n = 0;
      for(var i = 0; i < this.state.comments.length; i++){
        if(this.state.comments[i].courseId == courseId)
          n=n+1;
      }
      return n;
    }
    temp(p){
      if(this.getCommentNumByCourseId(p.courseId) > 0)
      return(
        this.state.comments.map(comment=>
        // {comment.courseId == p.courseId &&
          // <div class="commentDetail">
          //   <div class="commentContent">{comment.commentContent}</div>
          //   <div class="commentTime">发布于{comment.commentTime===null||comment.commentTime.length<10?comment.commentTime:comment.commentTime.substring(0,10)}</div>
          // </div>
          this.acomment(comment, p)
        // }
      ))
      else
        return(
          <div>
            <div className="notFound"><p>小南找不到该课程的评价</p><img src={tan90}/></div>
          </div>
        )
    }
    thumbsUp(commentId,likeIt){
      let url=global.constants.baseUrl+"/thumbsUp?commentId="+commentId+"&likeIt="+likeIt+"&openid=aaa"
      fetch(url,{
        headers:{
          Authorization:cookies.get('token')
        }
      })
        .then(result => {
           this.componentDidMount();
          })
        .catch(e => console.log(e));
    }
    getLikeCssStyle(favouriteSituation,type){
      //favouriteSituation: true赞过 false踩过 null未点击
      //type：true为赞 false为踩
      const css={
        "color": "rgb(121, 187, 255)"
      }
      console.log(favouriteSituation+" "+type)
      if(favouriteSituation==type){
        return css;
      }
      else{
        return {};
      }
    }
    handleCollapseChange(activeNames){
      this.setState({
        activeName:activeNames
      });
    }
  render() {
      // console.log(this.props)
      // console.log(this.state)
     
    return (
      
      <div className="Info">
         <div className="main">
          <BallScaleRippleMultiple loading={this.state.isFetching } color="#6A005F" center/>
          <div className="basicInfo">
            <div className="left">
              <p className="name">{this.state.data.teacher.teacherName}</p>
              <p className="collegeName">{this.state.data.teacher.teacherCollegename}</p>
              <p className="department">{this.state.data.teacher.teacherDepartment}</p>
            </div>
            <div className="right">
               <p className="score">{new Number(this.state.data.teacher.teacherScore).toFixed(2)}</p>
               <p className="participantNum">{this.state.comments.length}个人参与评分</p>
            </div>
          </div>
          <Tabs activeName="1" onTabClick={ (tab) => console.log(tab.props.name) }>
            <Tabs.Pane label="热门评论" name="1">
              {this.newestComment()}
              {/* {this.state.comments.map(comment=>
                  <div class="newcommentDetail">
                    <div class="commentContent">{comment.commentContent}</div>
                    <div class="commentTime">发布于{comment.commentTime===null||comment.commentTime.length<10?comment.commentTime:comment.commentTime.substring(0,10)}</div>
                    <div className="right">
                   <div className="like" onClick={this.thumbsUp.bind(this,comment.commentId,1)} style={this.getLikeCssStyle(comment.favouriteSituation,true)}>
                     <i className="el-icon-caret-top"/>
                     <p className="likeNum">赞同{comment.likeNum}</p>
                    </div>
                   <div className="dislike" onClick={this.thumbsUp.bind(this,comment.commentId,0)} style={this.getLikeCssStyle(comment.favouriteSituation,false)}>
                    <i className="el-icon-caret-bottom"/>
                   </div>
                   </div>
                  </div>
              )} */}
            </Tabs.Pane>
            <Tabs.Pane label="所教课程" name="2">
              <Collapse value={this.state.activeName} >
                {this.state.data.courses.map(p=>
                  <Collapse.Item title={p.courseName} name={p.courseName}>
                    {this.temp(p)}
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
          {/* <Comments token={window.token} teacherId={this.props.props.match.params.teacherId} onCommentsInit={this.handleCommentsInit.bind(this)}/> */}
          </div>
          
          <Link to={"/markScore/"+this.props.props.match.params.teacherId}>
          <div className="markScore">
            <i className="el-icon-edit"></i>
            评分
          </div>
          </Link>
      </div>      
    );
  }
}

export default Info;
