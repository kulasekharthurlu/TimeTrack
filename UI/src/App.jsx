import React from 'react'
import AppBar from "./Home/AppBar"
import {
    BrowserRouter as Router,
    Routes,
    Route
} from 'react-router-dom';
import TimeLog from './TimeLog/TimeLog';
import DashBoard from './Dashbord/DashBoard';
import Employees from './Employees/Employees';


export default function App() {
    return (
        <div>
            <Router>
                <Routes>
                    <Route exact path="/" element={<DashBoard />} />
                    <Route exact path="/timelog" element={<TimeLog />} />
                    <Route exact path="/emps" element={<Employees />} />
                </Routes>
            </Router>
        </div>
    )
}
