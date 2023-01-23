import React,{ useRef } from "react";
import { useNavigate } from "react-router-dom";

function Register(){
    const nameRef= useRef();
    const emailRef = useRef();
    const pwdRef  = useRef();
    const pwd1Ref  = useRef();
    const navigate = useNavigate();

    function getPureValue(key){
        return key.current.value.trim();
    }
    async function handlerSubmit(e){
        e.preventDefault();
        if(getPureValue(pwdRef)!==getPureValue(pwd1Ref)){
            console.log("different password");
        }else{
            try{
                let form = {
                    username:getPureValue(nameRef),
                    email:getPureValue(emailRef),
                    password:getPureValue(pwdRef),
                };
                let res = await React.$req.post(React.$api.userRegister,form);
                console.log(res);
                if(res.sucess){
                    navigate("/login");
                }
            }catch(e){
                throw e;
            }
        }
    }
    return(
        <div className="container flex flex_ver flex_center_all">
            <div className="title">Start new recycler journey now</div>
            <form onSubmit={handlerSubmit}>
                <div className="labelBox">
                    <label htmlFor="true">Email</label>
                    <input type="text" required id="email" ref={emailRef} />
                </div>
                <div className="labelBox">
                    <label htmlFor="true">Name</label>
                    <input type="text" required id="name" ref={nameRef} />
                </div>
                <div className="labelBox">
                    <label htmlFor="true">Password</label>
                    <input type="password" required id="pwd" ref={pwdRef} />
                </div>
                <div className="labelBox">
                    <label htmlFor="true">Confirm</label>
                    <input type="password" required id="pwd1" ref={pwd1Ref} />
                </div>
                <div className="flex flex_center_all">
                    <button className="btn btn-primary" onClick={handlerSubmit}>Register</button>
                </div>
            </form>
        </div>
    );
}
export default Register;