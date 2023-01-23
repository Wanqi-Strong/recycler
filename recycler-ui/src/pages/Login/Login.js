import React,{ useRef } from "react";
import { useNavigate } from "react-router-dom";
import './login.css'

function Login(){
    const usernameRef= useRef();
    const pwdRef  = useRef();
    const navigate = useNavigate();

    async function handlerSubmit(e){
        e.preventDefault();
        let form = {
            email:usernameRef.current.value.trim(),
            password:pwdRef.current.value.trim(),
        };
        if(!form.email||!form.password){
            console.log("incomplete form");
        }else{
            let res = await React.$req.post(React.$api.userLogin,form);
            console.log(res);
            if(res.success){
                React.$utils.setSessionStorage("userInfo",res.data.data);
                navigateTo("/home");
            }
        }
    }

    function navigateTo(url){
        try{
            navigate(url);
        }catch(e){
            throw e;
        }
    }

    return(
        <div className="container flex flex_ver flex_center_all">
            <div className="welcomeTitle">Welcome, Recycler</div>
            <form onSubmit={handlerSubmit}>
                <div className="labelBox">
                    <label htmlFor="true">Email</label>
                    <input type="text" required id="email" ref={usernameRef} />
                </div>
                <div className="labelBox">
                    <label htmlFor="true">Password</label>
                    <input type="password" required id="pwd" ref={pwdRef} />
                </div>
                <div className="btnBox flex flex_space-between">
                    <button className="btn btn-primary" onClick={handlerSubmit}>Login</button>
                    <button className="btn btn-primary" onClick={navigateTo("/register")}>Register</button>
                </div>
            </form>
        </div>
    );
}
export default Login;