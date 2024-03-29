import axios from "axios";
import { useContext, useEffect, useState } from "react";
import {
  Button,
  Container,
  Form,
  FormControl,
  FormLabel,
} from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { LOG_IN } from "../../actionTypes";
import { AuthContext } from "../../contexts";
import { BACKEND_URL } from "../../utils";
import styles from "./registration.module.scss";
import logo from "../../Images/logo.jpg";

function Registration() {
  const { auth, authDispatch } = useContext(AuthContext);
  const [state, setState] = useState({
    isLoading: false,
    first_Name: "",
    last_Name: "",
    email: "",
    password: "",
    password_confirmation: "",
    role: "USER",
  });

  const navigate = useNavigate();

  useEffect(() => {
    if (auth.user) {
      navigate("/");
    }
  });

  const onChangeHandler = (e) => {
    setState((prevSt) => {
      return {
        ...prevSt,
        [e.target.id]: e.target.value,
      };
    });
  };

  const onSubmitHandler = (e) => {
    e.preventDefault();
    setState({ ...state, isLoading: true });
    if (state.password !== state.password_confirmation) {
      toast("Password do not match!");
      setState({ ...state, isLoading: false });
      return;
    }
    let newUser = {
      firstName: state.first_Name,
      lastName: state.last_Name,
      email: state.email,
      password: state.password,
      role: "USER",
    };
    console.log("test user reg  :", newUser);

    axios
      .post(`${BACKEND_URL}/users/register`, newUser)
      .then((res) => {
        console.log("test registration : ", res.data);
        const { message, code } = res.data;
        if (code === "200") {
          toast.success(message);
          // authDispatch({
          //   type: LOG_IN,
          //   payload: data,
          // });

          setState({
            isLoading: false,
            first_name: "",
            last_name: "",
            email: "",
            password: "",
            password_confirmation: "",
          });
          navigate("/");
        } else {
          toast.error(message);
          setState({ ...state, isLoading: false });
        }
      })
      .catch((err) => {
        console.log(err);
        toast.error("Something Went Wrong!!");
        setState({ ...state, isLoading: false });
      });
  };

  return (
    <main
      // className="mx-auto d-flex justify-content-center align-items-center btn-dark"
      className={`${styles.main}`}>
      <section className="pb-5">
        <Link to="/">
          <img
            src={logo}
            alt="logo"
            style={{ height: "80px", width: "120px", cursor: "pointer" }}
          />
        </Link>
      </section>
      <section>
        <Form onSubmit={onSubmitHandler}>
          <h2>Créer Un Compte</h2>

          {/* ***************First Name************* */}
          <Form.Group className="my-3">
            <FormLabel>First Name</FormLabel>
            <FormControl
              type="text"
              className="mt-2 py-2 px-3"
              id="first_Name"
              onChange={onChangeHandler}
              placeholder="First Name"
              value={state.first_Name}
              disabled={state.isLoading}
            />
          </Form.Group>
          {/* ***************Last Name*************** */}
          <Form.Group className="my-3">
            <FormLabel>Last Name</FormLabel>
            <FormControl
              type="text"
              className="mt-2 py-2 px-3"
              id="last_Name"
              onChange={onChangeHandler}
              placeholder="Last Name"
              value={state.last_Name}
              disabled={state.isLoading}
            />
          </Form.Group>
          {/* ***************Email*************** */}
          <Form.Group className="my-3">
            <FormLabel>Email address</FormLabel>
            <FormControl
              type="email"
              className="mt-2 py-2 px-3"
              id="email"
              onChange={onChangeHandler}
              placeholder="name@example.com"
              value={state.email}
              disabled={state.isLoading}
            />
          </Form.Group>
          {/* ***************Password*************** */}
          <Form.Group className="my-3">
            <FormLabel>Password</FormLabel>
            <FormControl
              type="password"
              className="mt-2 py-2 px-3"
              id="password"
              onChange={onChangeHandler}
              placeholder="Enter Your Password"
              value={state.password}
              disabled={state.isLoading}
            />
          </Form.Group>

          {/* ***************confirm_Password*************** */}
          <Form.Group className="my-3">
            <FormLabel>Password Confirmation</FormLabel>
            <FormControl
              type="password"
              className="mt-2 py-2 px-3"
              id="password_confirmation"
              onChange={onChangeHandler}
              placeholder="Enter Your Password"
              value={state.password_confirmation}
              disabled={state.isLoading}
            />
          </Form.Group>

          {/* **********Button********** */}
          <div className="my-3">
            <span>
              Already Registered? Please <Link to="/login">Login</Link>
            </span>
          </div>

          <div className="Registration-group">
            <input
              type="submit"
              className="btn w-100 btn-info py-2"
              value={state.isLoading ? "Loading..." : "REGISTRATION"}
              disabled={state.isLoading}
            />
          </div>
        </Form>
      </section>
    </main>
  );
}

export default Registration;
