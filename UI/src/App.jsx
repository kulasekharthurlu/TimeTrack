import React, { useState } from "react";
import AppBar from "./Home/AppBar";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import TimeLog from "./TimeLog/TimeLog";
import DashBoard from "./Dashbord/DashBoard";
import Employees from "./Employees/Employees";
import EmployeeState from "./Context/Employee/EmployeeState";
import Login from "./Login/Login";
import SignUp from "./Login/SignUp";
import PrivateRoute from "./Routs/PrivateRoute";

export default function App() {
  return (
    <div>
      <Router>
        <EmployeeState>
          <Routes>
            <Route exact path="/" element={<DashBoard />} />
            <Route exact path="/login" element={<Login />} />
            <Route exact path="/signup" element={<SignUp />} />
            <Route exact path="/timelog" element={<TimeLog />} />
            <Route exact path="/emps" element={<Employees />} />
          </Routes>
        </EmployeeState>
      </Router>
    </div>
  );
}
