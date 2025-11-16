import {useState, useEffect} from 'react'
import {useNavigate} from 'react-router-dom'
import Navbar from '../components/Navbar'
import '../styles/homepage.css'

export default function HistoryPage({userInfo}){
    const [scanList, setScanList] = useState([])
    const [filtered, setFiltered] = useState([])
    const navigate = useNavigate()

    useEffect(()=>{
        if(!userInfo.username){
            navigate('/auth/login')
        }
        getScans()
    }, [])

    async function getScans(){
        try {
            const response = await fetch('http://food/items/'+userInfo.username) 
            const result = await response.json()
            setScanList(result)
            setFiltered(result)
        } catch (error) {
            console.log(error.message)
        }
    }

    function handleChange(e){
        e.preventDefault()
        let searchTerm = e.target.value.toLowerCase();
        setFiltered(scanList.filter((scan) => {
            let lowerName = scan.name.toLowerCase()
            return lowerName.includes(searchTerm)
        }));
    }

    return(
    <>
    <Navbar />
    {userInfo.username ? 
    <h1 className="page-header">Welcome, {userInfo.username}!</h1> : 
    <h1 className="page-header">Welcome to RecipeBlast!</h1>}
    <input type="text" name="searchbar" placeholder="Search for some scans" onChange={handleChange} />
    <hr />

    <div className="page-content">
        {scanList.map((scan) => {
            return (
                <button onClick={() => navigate('/scan/result', {state : {scanResult: scan}})}>{scan.product_name}</button>
            )
        })}
    </div>
    </>
    )
}