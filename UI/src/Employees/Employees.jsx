
import axios from 'axios'
import React, { useEffect, useState } from 'react'
import AppConstants from '../Utils/AppConstants'
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import AppBar from "../Home/AppBar"
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import EmployeeModel from './EmployeeModel';
import Button from '@mui/material/Button';
import { Block } from '@mui/icons-material';
import { blue } from '@mui/material/colors';
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
export default function Employees() {

    const [employees, setEmployees] = useState()
    const [empId, setEmpId] = useState(0);
    const [openEmployeeModel, setOpenEmployeeModel] = useState(false);


    const deleteEmployee = (id) => {

        axios.delete("http://localhost:2023/api/employee/v1/delete/" + id)
            .then((res) => {
                if (res.status == 200) {
                    // const updatesEmployees = employees.filter(emp => emp.empId != empId)
                    setEmployees(employees.filter(emp => emp.empId != id));
                }

            })
            .catch(() => { console.log("error") });
    }
    useEffect(() => {
        axios.get(`${AppConstants.BaseURL}/api/employee/v1/getall`).
            then(res => {
                if (res.status == 200) {
                    setEmployees(res.data.data)
                    console.log("employees : ", employees)
                }
            })
    }, [])



    return (
        <div>
            < AppBar />
            <div >
                <Button style={{ float: 'right' }}>
                    <  AddCircleOutlineIcon onClick={() => { setOpenEmployeeModel(true) }} />
                </Button>
            </div>
            <EmployeeModel open={openEmployeeModel} onClose={() => setOpenEmployeeModel(false)} />
            <div style={{ height: 400, width: '100%' }}>
                <TableContainer component={Paper}>
                    <Table sx={{ minWidth: 650 }} aria-label="simple table">
                        <TableHead >
                            <TableRow style={{ backgroundColor: 'black' }}>
                                <TableCell align="left" style={{ color: 'White' }}>Id</TableCell>
                                <TableCell align="left" style={{ color: 'White' }}>FirstName</TableCell>
                                <TableCell align="left" style={{ color: 'White' }}>LastName</TableCell>
                                <TableCell align="left" style={{ color: 'White' }}>Email</TableCell>
                                <TableCell align="left" style={{ color: 'White' }}>PhoneNumber</TableCell>
                                <TableCell align="left" style={{ color: 'White' }}>DOB</TableCell>
                                <TableCell align="center" colSpan={2} style={{ color: 'White' }}>Action</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {employees !== undefined && employees.map((row) => (
                                <TableRow
                                    key={row.empId}
                                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                >
                                    <TableCell align="left">{row.empId}</TableCell>
                                    <TableCell align="left">{row.empFirstName} </TableCell>
                                    <TableCell align="left">{row.empLastName}</TableCell>
                                    <TableCell align="left">{row.empEmail}</TableCell>
                                    <TableCell align="left">{row.empPhoneNumber}</TableCell>
                                    <TableCell align="left">{row.empDOB}</TableCell>
                                    <TableCell align="left"><Button onClick={e => { deleteEmployee(row.empId); setEmpId(row.empId); }} > <DeleteIcon /></Button></TableCell>
                                    <TableCell align="left"> <Button onClick={() => { setOpenEmployeeModel(true) }}><EditIcon /></Button></TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </div>
        </div>
    )
}
