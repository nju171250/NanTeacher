import React, { Component } from 'react';
//import './App.css';
import {Route,Switch,Link} from 'react-router-dom'
import SearchList from './SearchList';
import Info from './Info'
import SearchBox from './SearchBox'
class App extends Component {
  render() {
    return (
      <div>
        <SearchBox/>
        
        <Switch>
            
            <Route path="/info/:teacherId" component={Info}/>
        </Switch>
        
      </div>
    );
  }
}

export default App;
