import React,{ useState ,useEffect  } from "react";
import './home.css'

function Home(){
    const [recordList, setRecordList] = useState([]);
    useEffect(() => {
        queryRecord();
      }, [])
    async function queryRecord(){
        console.log('queryRecord')
        let param = {
            userId:React.$utils.getSessionStorage('userInfo').id
        };
        if(param.userId){
            let res = await React.$req.post(React.$api.queryRecordByUser,param);
            if(res.success){
                setRecordList(res.data.data);
            }
        }
    }
    function buildRecordItem(recordList){
        return(
            recordList.map((item) => 
                <div className="recordItem" key={item.recordId}>
                    <span>{formatDate(item.createTime)}</span>
                    <span>{item.description}</span>
                    <span>sum:{item.recordDetails.length}</span>
                </div>)
        )
    }
    function formatDate(date){
        return date.substring(0,10)
    }
    return(
        <div className="container">
            <div className="recordTitle">Weekly Record</div>
            <div className="recordBox">
                {buildRecordItem(recordList)}
                </div>
        </div>
    );
}
export default Home;