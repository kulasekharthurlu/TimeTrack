import * as React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import useMediaQuery from '@mui/material/useMediaQuery';
import { useTheme } from '@mui/material/styles';
import TextField from '@mui/material/TextField';
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';
import OutlinedInput from '@mui/material/OutlinedInput';
import InputAdornment from '@mui/material/InputAdornment';
import IconButton from '@mui/material/IconButton';
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import MenuItem from '@mui/material/MenuItem';

import "./Employee.scss"
import axios from 'axios';

export default function EmployeeModel(props) {
    const [open, setOpen] = React.useState(false);
    const theme = useTheme();
    const [showPassword, setShowPassword] = React.useState(false);
    const fullScreen = useMediaQuery(theme.breakpoints.down('md'));

    const handleClickShowPassword = () => setShowPassword((show) => !show);
    const handleMouseDownPassword = (event) => {
        event.preventDefault();
    };
    const [data, setData] = React.useState({});

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };
    const currencies = [
        {
            value: 'Male',
            label: 'Male',
        },
        {
            value: 'Female',
            label: 'Female',
        },
        {
            value: 'Others',
            label: 'Others',
        }
    ];
    const addData = (e) => {
        setData({ ...data, [e.target.name]: e.target.value + "" });
        console.log(data);
    }

    const saveEmployee = () => {
        axios.post('http://localhost:2023/api/employee/v1/save', data)
            .then(res => {
                console.log(res);
            })
    }

    return (
        <div>

            <Dialog
                fullScreen={fullScreen}
                open={props.open}
                onClose={props.onClose}
                aria-labelledby="responsive-dialog-title"
            >
                <DialogTitle id="responsive-dialog-title">
                    {"Edit Employee"}
                </DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        Employee Form Model
                    </DialogContentText>
                    <FormControl sx={{ m: 1, width: '25ch' }} variant="outlined">
                        <TextField id="outlined-basic" label="FirstName" variant="outlined" name="empFirstName" onChange={(e) => addData(e)} />
                    </FormControl>
                    <FormControl sx={{ m: 1, width: '25ch' }} variant="outlined">
                        <TextField id="outlined-basic" label="LastName" variant="outlined" name="empLastName" onChange={(e) => addData(e)} />
                    </FormControl>
                    <FormControl sx={{ m: 1, width: '25ch' }} variant="outlined">
                        <TextField id="outlined-basic" label="Email" variant="outlined" name="empEmail" onChange={(e) => addData(e)} />
                    </FormControl>
                    <FormControl sx={{ m: 1, width: '25ch' }} variant="outlined">
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
                    </FormControl>
                    <FormControl sx={{ m: 1, width: '25ch' }} variant="outlined">
                        <TextField id="outlined-basic" label="PhoneNumber" variant="outlined" name="empPhoneNumber" onChange={(e) => addData(e)} />
                    </FormControl>
                    <FormControl sx={{ m: 1, width: '25ch' }} variant="outlined">
                        <TextField id="outlined-basic" type="date" name="empDOB" onChange={(e) => addData(e)} label="DateOfBirth" InputLabelProps={{ shrink: true }} />
                    </FormControl>
                    <FormControl sx={{ m: 1, width: '25ch' }} variant="outlined">
                        <TextField
                            id="outlined-select-gender"
                            select
                            label="Select Gender"
                            onChange={(e) => addData(e)}
                            name="empGender"
                            defaultValue=""
                            InputLabelProps={{ shrink: true }}
                        >
                            {currencies.map((option) => (
                                <MenuItem key={option.value} value={option.value}>
                                    {option.label}
                                </MenuItem>
                            ))}
                        </TextField>
                    </FormControl>
                </DialogContent>
                <DialogActions>

                    <Button autoFocus onClick={props.onClose}>
                        Cancel
                    </Button>
                    <Button onClick={() => { saveEmployee(); props.onClose() }} autoFocus>
                        Save
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}