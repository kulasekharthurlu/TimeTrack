import React, { useState } from "react";

import { Container, Typography, TextField, Button, Grid } from "@mui/material";
import { Link, useNavigate } from "react-router-dom";
import EmployeeApiCall from "../ApiCalls/EmployeeApi";
import AppConstants from "../Utils/AppConstants";

const Login = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    EmployeeApiCall.loginApi({ email: email, password: password })
      .then((res) => {
        if (res.status == 202) {
          localStorage.setItem("isAuthenticated", true);
          localStorage.setItem("loginDate", new Date().toDateString());
          let ref = res.data.AuthToken;
          AppConstants.LOCAL_FORAGE.setItem("token", res.data.AuthToken);
          navigate("/dashboard");
        }
      })
      .catch((err) => {
        console.log("Authentication failed");
      });

    // Perform login logic using the email and password values
    // For example, make an API call to authenticate the user
    console.log("Email:", email);
    console.log("Password:", password);
  };

  return (
    <Container maxWidth="xs" sx={{ mt: 4, mb: 4 }}>
      <Typography variant="h4" align="center" gutterBottom>
        Login
      </Typography>
      <form onSubmit={handleSubmit}>
        <Grid container spacing={2}>
          <Grid item xs={12}>
            <TextField
              label="Email"
              type="email"
              fullWidth
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              label="Password"
              type="password"
              fullWidth
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </Grid>
          <Grid item xs={12}>
            <Button type="submit" variant="contained" fullWidth>
              Login
            </Button>
            <Link to="/signup">don't have account?</Link>

            <Link style={{ marginLeft: 100 }} to="/login">
              already have account?
            </Link>
          </Grid>
        </Grid>
      </form>
    </Container>
  );
};

export default Login;
