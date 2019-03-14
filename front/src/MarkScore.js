import React, { Component } from 'react';

import "./Config";
import Rater from 'react-rater'
import 'react-rater/lib/react-rater.css'
import './MarkScore.css'
import {Link} from 'react-router-dom'
import Axios from 'axios';
class MarkScore extends Component {
    constructor(props){
        super(props)
        this.state={
          rating:3
        }
    }
    handleRate({rating}){
        this.setState({
            rating:rating
        })
    }
    handleButtonClick(){
      
      
        var data = {teacherId: this.props.match.params.teacherId,content:this.state.text,starNum:this.state.rating,openId:"aaa",courseId:"1"};
        
        console.log(JSON.stringify(data))
        Axios.post(global.constants.baseUrl+"/makeComment",data)
        .then(function (response) {
          console.log(response);
        })
        .catch(function (error) {
          console.log(error);
        });
        // fetch(global.constants.baseUrl+"/makeComment", {
        //    method: 'POST',
        //    body: JSON.stringify(data),
        //    headers: {
        //     "Access-Control-Allow-Origin": "*",
        //    'Content-Type': 'application/json',
        //    "Access-Control-Allow-Credentials": "true",
        //    "Access-Control-Allow-Methods": "GET,HEAD,OPTIONS,POST,PUT",
        //    "Access-Control-Allow-Headers": "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"
          
        //   }
        //    }).then(res => res.json())
        //    .catch(error => console.error('Error:', error))
        //    .then(response => console.log('Success:', response));   
    }
    handleTextAreaChange(e){
      this.setState({
        text:e.target.value
      })
      
    }
  render() {
    console.log(this.state)
    return (
        
      <div className="markScore">
        <div>
         <Rater rating={this.state.rating} onRate={this.handleRate.bind(this)}/>
         </div>
        <textarea onChange={this.handleTextAreaChange.bind(this) } placeholder="留下你的上课体验~"></textarea>
        {/* <Link to={"/info/"+this.props.match.params.teacherId}>
        <button onClick={this.handleButtonClick.bind(this)}>提交</button>
        </Link> */}
        <div>
        <button onClick={this.handleButtonClick.bind(this)}>提交</button>
        </div>
        
    </div>
      
    );
  }
}

export default MarkScore;