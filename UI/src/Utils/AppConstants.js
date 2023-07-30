import localforage from "localforage"

const AppConstants = {
  BaseURL: "http://localhost:2023",
  LOCAL_FORAGE: localforage.createInstance({name :"TimeTrack"})
};

export default AppConstants