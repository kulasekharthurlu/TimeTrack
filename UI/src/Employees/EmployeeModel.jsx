import React, { useState, useEffect, useContext } from "react";
import Button from "@mui/material/Button";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";
import TextField from "@mui/material/TextField";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";

import "./Employee.scss";

import EmployeeContext from "../Context/Employee/EmployeeContext";

export default function EmployeeModel(props) {
  const { saveEmployee, updateEmployee } = useContext(EmployeeContext);

  const [data, setData] = useState({
    empId: "",
    empFirstName: "",
    empLastName: "",
    empEmail: "",
    empPhoneNumber: "",
    empDOB: "",
    empGender: "",
  });

  const [errors, setErrors] = useState({
    empFirstName: "",
    empLastName: "",
    empEmail: "",
    empDOB: "",
    empGender: "",
  });

  const clearErrors = () => {
    setErrors({
      empFirstName: "",
      empLastName: "",
      empEmail: "",
      empDOB: "",
      empGender: "",
    });
  };

  useEffect(() => {
    if (props.editEmpData !== undefined && props.editEmpData !== null) {
      setData({
        empId: props.editEmpData.empId,
        empFirstName: props.editEmpData.empFirstName,
        empLastName: props.editEmpData.empLastName,
        empEmail: props.editEmpData.empEmail,
        empPhoneNumber: props.editEmpData.empPhoneNumber,
        empDOB: props.editEmpData.empDOB,
        empGender: props.editEmpData.empGender,
      });
      clearErrors();
    } else {
      setData({
        empFirstName: "",
        empLastName: "",
        empEmail: "",
        empPhoneNumber: "",
        empDOB: "",
        empGender: "",
      });
      clearErrors();
    }
  }, [props.editEmpData]);

  const addData = (e) => {
    setData({ ...data, [e.target.name]: e.target.value + "" });
    setErrors({ ...errors, [e.target.name]: "" });
  };

  const saveEmployeeData = () => {
    if (validateForm()) {
      if (data.empId !== "" && data.empId !== undefined) {
        updateEmployee(data);
        props.onClose();
      } else {
        data.roles = [{ roleName: "EMPLOYEE" }];
        saveEmployee(data);
        props.onClose();
      }
    }
  };

  const validateForm = () => {
    let isValid = true;
    if (data.empFirstName == "") {
      setErrors((prevErrors) => ({
        ...prevErrors,
        empFirstName: "Please enter first name",
      }));
      isValid = false;
    }
    if (data.empLastName == "") {
      setErrors((prevErrors) => ({
        ...prevErrors,
        empLastName: "Please enter last name",
      }));
      isValid = false;
    }
    if (data.empEmail == "") {
      setErrors((prevErrors) => ({
        ...prevErrors,
        empEmail: "Please enter email",
      }));
      isValid = false;
    }
    if (data.empPhoneNumber == "") {
      setErrors((prevErrors) => ({
        ...prevErrors,
        empPhoneNumber: "Please enter phonenumber",
      }));
      isValid = false;
    }
    if (data.empDOB == "") {
      setErrors((prevErrors) => ({
        ...prevErrors,
        empDOB: "Please select DOB",
      }));
      isValid = false;
    }
    return isValid;
  };
  return (
    <>
      <div className="empModel">
        <Dialog
          open={props.open}
          onClose={props.onClose}
          aria-labelledby="responsive-dialog-title"
        >
          <DialogTitle id="responsive-dialog-title">
            {props.editEmpData !== undefined && props.editEmpData !== null
              ? "Update"
              : "Add"}{" "}
            Employee
          </DialogTitle>
          <DialogContent>
            <FormControl sx={{ m: 1, width: "25ch" }} variant="outlined">
              <TextField
                id="outlined-basic"
                label="FirstName"
                variant="outlined"
                name="empFirstName"
                value={data.empFirstName}
                error={!!errors.empFirstName}
                helperText={errors.empFirstName}
                onChange={(e) => addData(e)}
              />
            </FormControl>
            <FormControl sx={{ m: 1, width: "25ch" }} variant="outlined">
              <TextField
                id="outlined-basic"
                label="LastName"
                variant="outlined"
                name="empLastName"
                value={data.empLastName}
                error={!!errors.empLastName}
                helperText={errors.empLastName}
                onChange={(e) => addData(e)}
              />
            </FormControl>
            <FormControl sx={{ m: 1, width: "25ch" }} variant="outlined">
              <TextField
                id="outlined-basic"
                label="Email"
                variant="outlined"
                name="empEmail"
                value={data.empEmail}
                error={!!errors.empEmail}
                helperText={errors.empEmail}
                onChange={(e) => addData(e)}
              />
            </FormControl>
            {/* <FormControl sx={{ m: 1, width: '25ch' }} variant="outlined">
                        <InputLabel htmlFor="outlined-adornment-password">Password</InputLabel>
                        <OutlinedInput
                            id="outlined-adornment-password"
                            type={showPassword ? 'text' : 'password'}
                            endAdornment={
                                <InputAdornment position="end">
                                    <IconButton
                                        aria-label="toggle password visibility"
                                        onClick={handleClickShowPassword}
                                        onMouseDown={handleMouseDownPassword}
                                        edge="end"
                                    >
                                        {showPassword ? <VisibilityOff /> : <Visibility />}
                                    </IconButton>
                                </InputAdornment>
                            }
                            label="Password"
                            onChange={(e) => addData(e)}
                            name="empPassword"
                        />
                    </FormControl> */}
            <FormControl sx={{ m: 1, width: "25ch" }} variant="outlined">
              <TextField
                id="outlined-basic"
                label="PhoneNumber"
                variant="outlined"
                name="empPhoneNumber"
                value={data.empPhoneNumber}
                error={!!errors.empPhoneNumber}
                helperText={errors.empPhoneNumber}
                onChange={(e) => addData(e)}
              />
            </FormControl>
            <FormControl sx={{ m: 1, width: "25ch" }} variant="outlined">
              <TextField
                id="outlined-basic"
                type="date"
                name="empDOB"
                onChange={(e) => addData(e)}
                label="DateOfBirth"
                value={data.empDOB}
                error={!!errors.empDOB}
                helperText={errors.empDOB}
                InputLabelProps={{ shrink: true }}
              />
            </FormControl>
            <FormControl sx={{ m: 1, width: "25ch" }} variant="outlined">
              <TextField
                id="outlined-select-gender"
                select
                label="Select Gender"
                onChange={(e) => addData(e)}
                name="empGender"
                defaultValue="MALE"
                value={data.empGender}
                InputLabelProps={{ shrink: true }}
              >
                {data.empGender !== "" ? (
                  <MenuItem key={data.empGender} value={data.empGender}>
                    {data.empGender}
                  </MenuItem>
                ) : (
                  [
                    { value: "MALE", label: "MALE" },
                    { value: "FEMALE", label: "FEMALE" },
                  ].map((option) => (
                    <MenuItem key={option.label} value={option.label}>
                      {option.label}
                    </MenuItem>
                  ))
                )}
              </TextField>
            </FormControl>
          </DialogContent>
          <DialogActions>
            <Button autoFocus onClick={props.onClose}>
              Cancel
            </Button>
            <Button
              onClick={() => {
                saveEmployeeData();
              }}
              autoFocus
            >
              {props.editEmpData !== undefined && props.editEmpData !== null
                ? "Update"
                : "Save"}
            </Button>
          </DialogActions>
        </Dialog>
      </div>
    </>
  );
}
