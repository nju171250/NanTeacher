import React, { Component } from 'react';
import './Comments.css';
import "./Config";

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
        .then(result => {this.setState({data: {comments:result}, isFetching: false});
        this.props.onCommentsInit(this.state.data.comments.length);
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
         {this.state.data.comments.map(p=>
            <div className="comment">
               <div className="left">
                 <p className="content">{p.commentContent}</p>
                 <p className="commentTime">发布于{p.commentTime}</p>
                 <p className="report">举报</p>
               </div>
               <div className="like">
                  <p>{p.likeNum}</p>
                </div>
            </div>
            )} 
      </div>
      
    );
  }
}

export default Comments;
