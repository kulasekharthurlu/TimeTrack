import * as React from "react";
import { useNavigate } from "react-router-dom";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import MenuIcon from "@mui/icons-material/Menu";
import IconButton from "@mui/material/IconButton";
import MenuItem from "@mui/material/MenuItem";
import Menu from "@mui/material/Menu";
import AppConstants from "../Utils/AppConstants";
import SideBar from "./SideBar";
import AccountCircle from "@mui/icons-material/AccountCircle";

export default function ButtonAppBar() {
  const navigate = useNavigate();
  const [openSideBar, setOpenSideBar] = React.useState(false);
  const [anchorEl, setAnchorEl] = React.useState(false);

  const handleClose = () => {
    setAnchorEl(false);
  };

  const handleLogOut = () => {
    localStorage.setItem("isAuthenticated", false);
    AppConstants.LOCAL_FORAGE.removeItem("token");
    navigate("/");
  };

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
            onClick={() => setOpenSideBar(true)}
          >
            <MenuIcon />
          </IconButton>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Time Track
          </Typography>

          <IconButton
            size="large"
            aria-label="account of current user"
            aria-controls="menu-appbar"
            aria-haspopup="true"
            onClick={() => setAnchorEl(true)}
            color="inherit"
          >
            <AccountCircle />
          </IconButton>
          <Menu
            id="menu-appbar"
            anchorEl={anchorEl}
            anchorOrigin={{
              vertical: "top",
              horizontal: "right",
            }}
            keepMounted
            transformOrigin={{
              vertical: "top",
              horizontal: "right",
            }}
            open={anchorEl}
            onClose={handleClose}
          >
            <MenuItem onClick={handleClose}>Profile</MenuItem>
            <MenuItem onClick={handleClose}>My account</MenuItem>
            <MenuItem onClick={handleLogOut}>LogOut</MenuItem>
          </Menu>
        </Toolbar>
      </AppBar>
      <SideBar open={openSideBar} onHide={() => setOpenSideBar(false)} />
    </Box>
  );
}
