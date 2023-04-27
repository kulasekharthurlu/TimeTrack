import AppBar from "./AppBar"
import axios from "axios";
import React, { useState } from "react";

const EmployeeRegistration = () => {
    const [data, setData] = useState({
        empId: "",
        empFirstName: "",
        empLastName: "",
        empEmail: "",
        empPassword: "",
        empPhoneNumber: "",
        empDOB: "",
        empGender: ""
    });


    const saveEmployee = () => {
        axios.post("http://localhost:2023/api/employee/v1/save", { data })
            .then((res) => { console.log(res); })
            .catch(() => { console.log("error") })

    }
    const deleteEmployee = () => {
        axios.delete("http://localhost:2023/api/employee/v1/delete/" + data.empId)
            .then((res) => { console.log(res); })
            .catch(() => { console.log("error") });
    }
    const getEmployee = () => {
        axios.get("http://localhost:2023/api/employee/v1/get/" + data.empId)
            .then((res) => { console.log(res); })
            .catch(() => { console.log("error") })
    }



    const updateEmployee = () => {

        axios.put("http://localhost:2023/api/employee/v1/update", data)
            .then((res) => { console.log(res); })
            .catch(() => { console.log("error") })

    }


    const getAllEmployees = () => {
        axios.get("http://localhost:2023/api/employee/v1/getall")
            .then((res) => { console.log(res); })
            .catch(() => { console.log("error") })
    }

    const handleChange = (e) => {
        const { name, value } = e.target;
        console.log(data);
        setData({ ...data, [name]: value });
    }

    return (
        <div>
            <AppBar />
            <h1>Employee Registration</h1>
            Employee First Name : <input type="text" name="empFirstName" onChange={handleChange} />
            <br /><br />
            Employee Last Name : <input type="text" name="empLastName" onChange={handleChange} />
            <br /><br />
            Employee Email : <input type="text" name="empEmail" onChange={handleChange} />
            <br /><br />
            Employee Password : <input type="text" name="empPassword" onChange={handleChange} />
            <br /><br />
            Employee Phone Number : <input type="text" name="empPhoneNumber" onChange={handleChange} />
            <br /><br />
            Employee Date Of Birth : <input type="text" name="empDOB" onChange={handleChange} />
            <br /><br />
            Employee Gender : <input type="text" name="empGender" onChange={handleChange} />
            <br /><br />
            <button onClick={a => saveEmployee()} >SAVE</button><br /><br />


            <h2>delete employee</h2>
            Employee Id: <input type="text" name="empId" placeholder="empId" onChange={handleChange} />
            <button onClick={a => deleteEmployee()} >DELETE</button>


            <h1>Get Employee</h1>
            Enter EmployeeId: <input type="text" name="empId" placeholder="empId" onChange={handleChange} />
            <button onClick={e => getEmployee()} >GETEMPLOYEE</button>


            <h2>Update Employee</h2>
            Employee Id : <input type="text" name="empId" onChange={handleChange} />
            <br /><br />
            Employee First Name : <input type="text" name="empFirstName" onChange={handleChange} />
            <br /><br />
            Employee Last Name : <input type="text" name="empLastName" onChange={handleChange} />
            <br /><br />
            Employee Email : <input type="text" name="empEmail" onChange={handleChange} />
            <br /><br />
            Employee Password : <input type="text" name="empPassword" onChange={handleChange} />
            <br /><br />
            Employee Phone Number : <input type="text" name="empPhoneNumber" onChange={handleChange} />
            <br /><br />
            Employee Date Of Birth : <input type="text" name="empDOB" onChange={handleChange} />
            <br /><br />
            Employee Gender : <input type="text" name="empGender" onChange={handleChange} />
            <br /><br />
            <button onClick={e => updateEmployee()}>UPDATE Employee</button>


            <h1>Get All Employees </h1>
            <button onClick={e => getAllEmployees()}>GetAllEmployees</button>
        </div>
    );
}

export default EmployeeRegistration;