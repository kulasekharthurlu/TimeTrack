import React, { useEffect, useState, useContext } from "react";
import DeleteIcon from "@mui/icons-material/Delete";
import EditIcon from "@mui/icons-material/Edit";
import AppBar from "../Home/AppBar";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import EmployeeModel from "./EmployeeModel";
import Button from "@mui/material/Button";
import AddCircleOutlineIcon from "@mui/icons-material/AddCircleOutline";
import EmployeeContext from "../Context/Employee/EmployeeContext.js";
import axios from "axios";

export default function Employees() {
  let { employees, getAllEmployees, deleteEmployee, getEmployeesByFilters } =
    useContext(EmployeeContext);
  const [empData, setEmpData] = useState({});
  const [filterEmpData, setFilterEmpData] = useState({});
  const [openEmployeeModel, setOpenEmployeeModel] = useState(false);

  const handleChange = (e) => {
    setFilterEmpData({ ...filterEmpData, [e.target.name]: e.target.value });
  };

  const getByFilters = (filterEmpData) => {
    getEmployeesByFilters(filterEmpData);
  };

  const deleteEmployeeById = (id) => {
    deleteEmployee(id);
  };

  useEffect(() => {
    getAllEmployees(0, 25);
  }, []);

  return (
    <div>
      <AppBar />
      <div>
        <Button style={{ float: "right" }}>
          <AddCircleOutlineIcon
            onClick={() => {
              setOpenEmployeeModel(true);
              setEmpData(null);
            }}
          />
        </Button>
      </div>
      <EmployeeModel
        open={openEmployeeModel}
        onClose={() => setOpenEmployeeModel(false)}
        editEmpData={empData}
      />
      <div style={{ marginTop: 10 }}>
        FirstName:{" "}
        <input type="text" onChange={handleChange} name="empFirstName" />
        LastName:{" "}
        <input type="text" onChange={handleChange} name="empLastName" />
        PhoneNumber:{" "}
        <input type="text" onChange={handleChange} name="empPhoneNumber" />
        Genger: <input type="text" onChange={handleChange} name="empGender" />
        <button onClick={getByFilters}>filter</button>
      </div>
      <div style={{ height: 400, width: "100%" }}>
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 650 }} aria-label="simple table">
            <TableHead>
              <TableRow style={{ backgroundColor: "blue" }}>
                <TableCell align="left" style={{ color: "White" }}>
                  Id
                </TableCell>
                <TableCell align="left" style={{ color: "White" }}>
                  FirstName
                </TableCell>
                <TableCell align="left" style={{ color: "White" }}>
                  LastName
                </TableCell>
                <TableCell align="left" style={{ color: "White" }}>
                  Gender
                </TableCell>
                <TableCell align="left" style={{ color: "White" }}>
                  Email
                </TableCell>
                <TableCell align="left" style={{ color: "White" }}>
                  PhoneNumber
                </TableCell>
                <TableCell align="left" style={{ color: "White" }}>
                  DOB
                </TableCell>
                <TableCell
                  align="center"
                  colSpan={2}
                  style={{ color: "White" }}
                >
                  Actions
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {employees.map((row) => (
                <TableRow
                  key={row.empId}
                  sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                >
                  <TableCell align="left">{row.empId}</TableCell>
                  <TableCell align="left">{row.empFirstName} </TableCell>
                  <TableCell align="left">{row.empLastName}</TableCell>
                  <TableCell align="left">{row.empGender}</TableCell>
                  <TableCell align="left">{row.empEmail}</TableCell>
                  <TableCell align="left">{row.empPhoneNumber}</TableCell>
                  <TableCell align="left">{row.empDOB}</TableCell>
                  <TableCell align="left">
                    <Button
                      onClick={() => {
                        deleteEmployeeById(row.empId);
                      }}
                    >
                      <DeleteIcon color="error" />
                    </Button>
                  </TableCell>
                  <TableCell align="left">
                    <Button
                      onClick={() => {
                        setOpenEmployeeModel(true);
                        setEmpData(row);
                      }}
                    >
                      <EditIcon color="primary" />
                    </Button>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </div>
    </div>
  );
}
