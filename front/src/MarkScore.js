import React, { Component } from 'react';

import "./Config";
import Rater from 'react-rater'
import 'react-rater/lib/react-rater.css'
import './MarkScore.css'
import {Link} from 'react-router-dom'
class MarkScore extends Component {
    constructor(props){
        super(props)
        this.state={}
    }
    handleRate({rating}){
        this.setState({
            rating:rating
        })
    }
    handleButtonClick(){
      
        var data = {username: this.props.match.params.teacherId};

        fetch(global.constants.baseUrl+"/makeComment", {
  method: 'POST', // or 'PUT'
  body: JSON.stringify(data), // data can be `string` or {object}!
  headers: new Headers({
    'Content-Type': 'application/json'
  })
   }).then(res => res.json())
.catch(error => console.error('Error:', error))
.then(response => console.log('Success:', response));   
    }
  render() {
    console.log(this.state)
    return (
        
      <div className="markScore">
        <div>
         <Rater rating={this.state.rating} onRate={this.handleRate.bind(this)}/>
         </div>
        <textarea></textarea>
        <Link to={"/info/"+this.props.match.params.teacherId}>
        <button onClick={this.handleButtonClick.bind(this)}>提交</button>
        </Link>
    </div>
      
    );
  }
}

export default MarkScore;
