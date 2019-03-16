import React, { Component } from 'react';
import './Comments.css';
import "./Config";
import { BallScaleRippleMultiple } from 'react-pretty-loading';
class Comments extends Component {
    constructor(props){
        super(props)
        this.state={
          data:{
              comments:[]
          }
        }
    }
    componentDidMount(){
      this.setState({isFetching: true})
      let url=global.constants.baseUrl+"/getCommentInfo?teacherId="+this.props.teacherId
      fetch(url)
        .then(response => response.json())
        .then(result => {
          if(result.status!==undefined){
            this.setState({data: {comments:[]}, isFetching: false})
            this.props.onCommentsInit(0);
          }else{
            this.setState({data: {comments:result}, isFetching: false})
            this.props.onCommentsInit(this.state.data.comments.length);
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
                   <div className="like">
                     <i className="el-icon-caret-top"/>
                     <p className="likeNum">赞同{p.likeNum}</p>
                    </div>
                   <div className="dislike">
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
