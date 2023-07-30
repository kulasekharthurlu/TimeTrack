import axios from "axios";
import AppConstants from "../Utils/AppConstants";

const GetAuthToken = async () => {
  let token = "";
  await AppConstants.LOCAL_FORAGE.getItem("token")
    .then((value) => {
      token = value;
    })
    .catch(function (err) {
      console.log(err);
    });
  return token;
};

const EmployeeApiCall = {
  getAllEmployeess: async (pageNumber, pageSize) => {
    const token = await GetAuthToken();
    return axios.get(
      `${AppConstants.BaseURL}/api/employee/v1/getall?pageNumber=${pageNumber}&pageSize=${pageSize}`
      // {
      //   headers: {

      //     Authorization: `Bearer ${token}`,
      //   },
      // }
    );
  },
  loginApi: async (data) => {
    return axios.post(`${AppConstants.BaseURL}/api/employee/v1/login`, data);
  },
  getEmployeeById: async (id) => {
    const token = await GetAuthToken();
    return axios.get(`${AppConstants.BaseURL}/api/employee/v1/get?id=${id}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  },
  saveEmployee: async (data) => {
    const token = await GetAuthToken();
    return axios.post(`${AppConstants.BaseURL}/api/employee/v1/signup`, data, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  },
  getEmployeesByFilters: async (data) => {
    const token = await GetAuthToken();
    return axios.post(
      `${AppConstants.BaseURL}/api/employee/v1/get-employees-by-filters`,
      data,
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );
  },
  updateEmployee: async (data) => {
    return axios.put(`${AppConstants.BaseURL}/api/employee/v1/update`, data, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  },
  deleEmployee: async (id) => {
    const token = await GetAuthToken();
    return axios.delete(
      `${AppConstants.BaseURL}/api/employee/v1/delete?employeeId=${id}`,
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );
  },
};

export default EmployeeApiCall;
