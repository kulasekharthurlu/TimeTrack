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
            <Route exact path="/" element={<Login />} />
            <Route exact path="/login" element={<Login />} />
            <Route exact path="/signup" element={<SignUp />} />

            <Route
              element={<PrivateRoute component={DashBoard} />}
              path="/DashBoard"
            />
            <Route
              element={<PrivateRoute component={TimeLog} />}
              path="/timeLog"
            />
            <Route
              element={<PrivateRoute component={Employees} />}
              path="/emps"
            />
          </Routes>
        </EmployeeState>
      </Router>
    </div>
  );
}
