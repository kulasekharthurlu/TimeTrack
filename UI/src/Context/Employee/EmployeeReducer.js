import EmployeeActions from "./EmployeeActions";

const EmployeeReducer = (state, action) => {
  switch (action.type) {
    case EmployeeActions.SAVE_EMPLOYEE:
      return {
        ...state,
        employees: [...state.employees, action.payload],
      };
    case EmployeeActions.UPDATE_EMPLOYEE:
      let updatedList = state.employees.map((item) =>
        item.empId === action.payload.empId ? action.payload : item
      );
      return {
        ...state,
        employees: updatedList,
      };
    case EmployeeActions.DELETE_EMPLOYEE:
      return {
        ...state,
        employees: state.employees.filter(
          (employee) => employee.empId !== action.payload
        ),
      };
    case EmployeeActions.GET_EMPLOYEE: {
      return {
        ...state,
        users: state.employees.filter((emp) => emp.empId === action.payload),
      };
    }
    case EmployeeActions.GET_ALL_EMPLOYEES: {
      return {
        ...state,
        employees: action.payload || [],
      };
    }
    case EmployeeActions.GET_EMPLOYEES_BY_FILTERS: {
      return {
        ...state,
        employees: action.payload || [],
      };
    }
    default:
      return state;
  }
};

export default EmployeeReducer;
