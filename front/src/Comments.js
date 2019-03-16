import React, { Component } from 'react';
import './Comments.css';
import "./Config";
import Axios from 'axios';
import _ from 'lodash';
import Cookies from 'universal-cookie';
import { BallScaleRippleMultiple } from 'react-pretty-loading';
const cookies = new Cookies();
class Comments extends Component {
    constructor(props){
        super(props)
        this.state={
          data:{
              comments:[]
          }
        }
    }
    
    fetchFavouriteSituation(commentsId){
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
        var merge=_.map(this.state.data.comments,function(item){
           var res=_.merge(item,_.find(favouriteInfos,{commentId:item.commentId}));
           console.log(res)
           return res;
        });
        console.log(merge)
        this.setState({
          data:{
            comments:merge
          }
        })
      })
      .catch(function (error) {
        console.log(error);
      }); 
      
      
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
        'background-color': 'red'
      }
      console.log(favouriteSituation+" "+type)
      if(favouriteSituation==type){
        return css;
      }
      else{
        return {};
      }
    }
    componentDidMount(){
      this.setState({isFetching: true})
      let url=global.constants.baseUrl+"/getCommentInfo?teacherId="+this.props.teacherId
      fetch(url,{
        headers:{
          Authorization:cookies.get('token')
        }
      })
        .then(response => response.json())
        .then(result => {
          if(result.status!==undefined){
            this.setState({data: {comments:[]}, isFetching: false})
            this.props.onCommentsInit(0);
          }else{
            this.setState({data: {comments:result}, isFetching: false})
            this.props.onCommentsInit(this.state.data.comments.length);
            this.fetchFavouriteSituation(result.map(p=>
              p.commentId
            ));
          }
          })
        .catch(e => console.log(e));

    }
  render() {
      console.log(this.state)
    return (
      <div className="Comments">
         <div className="header">
         {this.state.data.comments.length}条评论
         </div>
         <BallScaleRippleMultiple loading={this.state.isFetching } color="#6A005F" center/>
         {this.state.data.comments.map(p=>
            <div className="comment">
               <p className="content">{p.commentContent}</p>
               <div className="foot">
                  <p className="commentTime">发布于{p.commentTime===null||p.commentTime.length<10?p.commentTime:p.commentTime.substring(0,10)}</p>
                  <p className="report">举报</p>
                  <div className="right">
                   <div className="like" onClick={this.thumbsUp.bind(this,p.commentId,1)} style={this.getLikeCssStyle(p.favouriteSituation,true)}>
                     <i className="el-icon-caret-top"/>
                     <p className="likeNum">赞同{p.likeNum}</p>
                    </div>
                   <div className="dislike" onClick={this.thumbsUp.bind(this,p.commentId,0)} style={this.getLikeCssStyle(p.favouriteSituation,false)}>
                    <i className="el-icon-caret-bottom"/>
                   </div>
                   </div>
               </div>
            </div>
            )} 
      </div>
      
    );
  }
}

export default Comments;
