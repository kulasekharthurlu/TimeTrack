import React, { useReducer, useMemo, useState } from "react";
import EmployeeContext from "./EmployeeContext";
import EmployeeReducer from "./EmployeeReducer";
import EmployeeActions from "./EmployeeActions";
import EmployeeApiCall from "../../ApiCalls/EmployeeApi";
import Notifyer from "../../Utils/Notifyer";

const EmployeeState = (props) => {
  const [responseMessage, setResponseMessage] = useState({
    message: "",
    notify: false,
    severity: "",
  });

  const initialState = {
    employees: [],
    timesheets: [],
  };

  const [state, dispatch] = useReducer(EmployeeReducer, initialState);

  const saveEmployee = (employee) => {
    EmployeeApiCall.saveEmployee(employee)
      .then((response) => {
        if (response.status === 200) {
          dispatch({
            type: EmployeeActions.SAVE_EMPLOYEE,
            payload: response.data.data,
          });
          setResponseMessage((prevData) => ({
            message: response.data.message,
            notify: true,
            severity: "success",
          }));
        }
      })
      .catch((error) => {
        setResponseMessage((prevData) => ({
          message: error.response.data.ErrorMessage,
          notify: true,
          severity: "error",
        }));
        console.error("Error While Saving Emp ", error);
      });
  };

  const getEmployeesByFilters = (employee) => {
    EmployeeApiCall.getEmployeesByFilters(employee)
      .then((response) => {
        if (response.status === 200) {
          dispatch({
            type: EmployeeActions.GET_EMPLOYEES_BY_FILTERS,
            payload: response.data.data,
          });
          setResponseMessage((prevData) => ({
            message: response.data.message,
            notify: true,
            severity: "success",
          }));
        }
      })
      .catch((error) => {
        setResponseMessage((prevData) => ({
          message: error.response.data.ErrorMessage,
          notify: true,
          severity: "error",
        }));
        console.error("Error While Saving Emp ", error);
      });
  };

  const updateEmployee = (employee) => {
    EmployeeApiCall.updateEmployee(employee)
      .then((response) => {
        if (response.status === 200) {
          dispatch({
            type: EmployeeActions.UPDATE_EMPLOYEE,
            payload: response.data.data,
          });
          setResponseMessage((prevData) => ({
            message: response.data.message,
            notify: true,
            severity: "success",
          }));
        }
      })
      .catch((error) => {
        setResponseMessage((prevData) => ({
          message: error.response.data.ErrorMessage,
          notify: true,
          severity: "error",
        }));
        console.error("Error While Updating Emp ", error);
      });
  };

  const deleteEmployee = (employeeId) => {
    EmployeeApiCall.deleEmployee(employeeId)
      .then((response) => {
        if (response.status === 200) {
          dispatch({
            type: EmployeeActions.DELETE_EMPLOYEE,
            payload: employeeId,
          });
          setResponseMessage((prevData) => ({
            ...prevData,
            message: response.data.message,
            notify: true,
            severity: "success",
          }));
        }
      })
      .catch((error) => {
        console.error("While deleting emp ", error);
      });
  };

  const getEmployee = (employeeId) => {
    dispatch({ type: EmployeeActions.GET_EMPLOYEE, payload: employeeId });
  };

  const getAllEmployees = (pageNumber, pageSize) => {
    EmployeeApiCall.getAllEmployeess(pageNumber, pageSize)
      .then((response) => {
        if (response.status === 200) {
          dispatch({
            type: EmployeeActions.GET_ALL_EMPLOYEES,
            payload: response.data.data,
          });
        }
      })
      .catch((error) => {
        console.error("While get all emps", error);
      });
  };

  const memoizedValue = useMemo(
    () => ({
      employees: state.employees,
      getEmployee,
      getEmployeesByFilters,
      saveEmployee,
      deleteEmployee,
      getAllEmployees,
      updateEmployee,
    }),
    [
      state.employees,
      getEmployee,
      saveEmployee,
      deleteEmployee,
      getAllEmployees,
      updateEmployee,
      getEmployeesByFilters,
    ]
  );

  return (
    <>
      <Notifyer
        severity={responseMessage.severity}
        open={responseMessage.notify}
        onClose={() => {
          setResponseMessage((prevData) => ({
            ...prevData,
            notify: false,
          }));
        }}
        message={responseMessage.message}
      />
      <EmployeeContext.Provider value={memoizedValue}>
        {props.children}
      </EmployeeContext.Provider>
    </>
  );
};

export default EmployeeState;
