import React, { Component } from 'react';

import "./Config";
import 'element-theme-default';

import './MarkScore.css'
import {Link} from 'react-router-dom'
import Axios from 'axios';
import {Rate,Select} from 'element-react'
import { BallScaleRippleMultiple } from 'react-pretty-loading';
class MarkScore extends Component {
    constructor(props){
        super(props)
        this.state={
          rating:3,
        data:{
          courses:[]
        }
        }
    }
    componentDidMount(){
      
      this.setState({isFetching: true})
      let url=global.constants.baseUrl+"/getTeacherInfo?teacherId="+this.props.match.params.teacherId
      fetch(url)
        .then(response => response.json())
        .then(result => {
          if(result.status!==undefined){
            this.setState({data: {courses:[]}, isFetching: false})
          }else{
            this.setState({data: result, isFetching: false})
        }
      }
        )
        .catch(e => console.log(e));
    }
    handleRate(rating){
        this.setState({
            rating:rating
        })
        console.log(this.state)
    }
    handleButtonClick(){
        var data = {
          "teacherId": this.props.match.params.teacherId,
          "content": this.state.text,
          "starNum": this.state.rating,
          "openid": "aaa",
          "courseId": this.state.courseId};

        console.log(JSON.stringify(data))
        Axios.post(global.constants.baseUrl+"/makeComment",data,
        {headers:{
          "Content-Type":"application/json; charset=UTF-8"
        }})
        .then(function (response) {
          console.log(response);
        })
        .catch(function (error) {
          console.log(error);
        }); 
    }
    handleTextAreaChange(e){
      this.setState({
        text:e.target.value
      })
      
    }
    handleRadioChange(e){
      this.setState({
        courseId: e.target.value
      })
      
    }
  render() {
    console.log(this.state)
    return (
        
      <div className="markScore">
        <BallScaleRippleMultiple loading={this.state.isFetching } color="#6A005F" center/>
        <Select value={this.state.value} placeholder="请选择">
          {
            this.state.data.courses.map(el => {
              return <Select.Option key={el.courseId} label={el.courseName} value={el.courseId}  />
            })
          }
        </Select>
        {/* <div className="radio">
          <tr>
          {this.state.data.courses.map(p=>
            <td><input type="radio" value={p.courseId} onChange={this.handleRadioChange.bind(this)}></input>{p.courseName}</td>
          )}
           </tr>
        </div> */}
        <div className="rater" >
         <Rate onChange={this.handleRate.bind(this)} value={this.state.rating}/>
         </div>
        <textarea onChange={this.handleTextAreaChange.bind(this) } placeholder="留下你的上课体验~"></textarea>
        
        <div>
        <Link to={"/info/"+this.props.match.params.teacherId}>
        <button onClick={this.handleButtonClick.bind(this)}>提交</button>
        </Link> 
        </div>
        
    </div>
      
    );
  }
}

export default MarkScore;
