import React, { Component } from 'react';

import "./Config";
import 'element-theme-default';

import './MarkScore.css'
import {Link,withRouter} from 'react-router-dom'
import Axios from 'axios';
import {Rate, Select, Input, Button,Message} from 'element-react'
import { BallScaleRippleMultiple } from 'react-pretty-loading';

import Cookies from 'universal-cookie';
const cookies = new Cookies();
class MarkScore extends Component {
    constructor(props){
        super(props)
        this.state={
          rating:3,
        data:{
          courses:[]
        },
        text:"",
        courseId:""
        }
    }
    componentDidMount(){
      
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
            this.setState({data: {courses:[]}, isFetching: false})
          }else{
            this.setState({data: result, isFetching: false})
        }
      }
        )
        .catch(e => console.log(e));
    }
    handleRate(ratin){
        this.setState({
            rating:ratin
        })
        console.log(this.state)
    }
    handleButtonClick(){
      if(this.state.courseId===""){
        Message("请选择所评价课程~")
         return;
      }
      if(this.state.text===""){
        Message("请输入评价内容~")
        return;
      }
        var data = {
          "teacherId": this.props.props.match.params.teacherId,
          "content": this.state.text,
          "starNum": this.state.rating,
          "openid": "aaa",
          "courseId": this.state.courseId};

        console.log(JSON.stringify(data))
        Axios.post(global.constants.baseUrl+"/makeComment",data,
        {headers:{
          "Content-Type":"application/json; charset=UTF-8",
          "Authorization":cookies.get('token')
        }})
        .then(function (response) {
          console.log(response);
        })
        .catch(function (error) {
          console.log(error);
        }); 
        this.props.history.push("/info/"+this.props.props.match.params.teacherId);
        setTimeout(() => {
          window.location.reload();
      }, 0);
    }
    handleTextAreaChange(e){
      this.setState({
        text:e
      })
      
    }
    handleSelectChange(e){
      this.setState({
        courseId: e
      })
      
    }
  render() {
    console.log(this.state)
    const css={
      "marginRight":"24px"
    }
    const InputStyle={
      "marginTop":"10px"
    }
    return (
        
      <div className="markScore">
        <BallScaleRippleMultiple loading={this.state.isFetching } color="#6A005F" center/>
        <div class="commentCourse">
          <div class="selectCourse">
            <span style={css}>评价课程</span>
            <Select value={this.state.value} placeholder="请选择" onChange={this.handleSelectChange.bind(this)}>
              {
                this.state.data.courses.map(el => {
                  return <Select.Option key={el.courseId} label={el.courseName} value={el.courseId}  />
                })
              }
            </Select>
          </div>
          {/* <div className="radio">
            <tr>
            {this.state.data.courses.map(p=>
              <td><input type="radio" value={p.courseId} onChange={this.handleRadioChange.bind(this)}></input>{p.courseName}</td>
            )}
            </tr>
          </div> */}
          <div className="rater" >
            <span style={css}>评价课程</span>
            <Rate onChange={this.handleRate.bind(this)} value={this.state.rating}/>
          </div>
        </div>
        <Input
          style={InputStyle}
          type="textarea"
          autosize={{ minRows: 3, maxRows: 4}}
          placeholder="留下你的上课体验吧~~" 
          onChange={this.handleTextAreaChange.bind(this)}
          onSelect={null}
        />
        
        
        
        <div>
        
        <Button plain={true} type="success" onClick={this.handleButtonClick.bind(this)}>提交</Button>
        
        </div>
        
    </div>
      
    );
  }
}

export default withRouter(MarkScore);
