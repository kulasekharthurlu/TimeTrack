import * as React from 'react';
import SwipeableDrawer from '@mui/material/SwipeableDrawer';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import GridViewRoundedIcon from '@mui/icons-material/GridViewRounded';
import AccessTimeFilledRoundedIcon from '@mui/icons-material/AccessTimeFilledRounded';
import PeopleAltIcon from '@mui/icons-material/PeopleAlt';
import { Link } from 'react-router-dom';

export default function SideBar(props) {


    return (
        <div>
            <React.Fragment >
                <SwipeableDrawer
                    open={props.open}
                    onClose={props.onHide}
                >
                    <List>
                        <Link to="/" >
                            <ListItem key={"dashBord"} disablePadding onClick={props.onHide}>
                                <ListItemButton>
                                    <ListItemIcon>
                                        <GridViewRoundedIcon />
                                    </ListItemIcon>
                                    <ListItemText primary={"DashBord"} />
                                </ListItemButton>
                            </ListItem>
                        </Link>
                        <Link to="/timelog" >
                            <ListItem key={"timeLog"} disablePadding onClick={props.onHide}>
                                <ListItemButton>
                                    <ListItemIcon>
                                        <AccessTimeFilledRoundedIcon />
                                    </ListItemIcon>
                                    <ListItemText primary={"TimeLog"} />
                                </ListItemButton>
                            </ListItem>
                        </Link>
                        <Link to="/emps" >
                            <ListItem key={"emloyees"} disablePadding>
                                <ListItemButton>
                                    <ListItemIcon>
                                        <PeopleAltIcon />
                                    </ListItemIcon>
                                    <ListItemText primary={"Employees"} />
                                </ListItemButton>
                            </ListItem>
                        </Link>
                    </List>
                </SwipeableDrawer>
            </React.Fragment>
        </div>
    );
}