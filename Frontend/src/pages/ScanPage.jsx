import {useState, useEffect} from 'react'
import {useNavigate, useLocation, Link} from 'react-router-dom'

export default function ScanPage(){
    const [scanProgress, setScanProgress] = useState(0)
    const location = useLocation()
    const navigate = useNavigate()

    const {file} = location.state || {};
    
    return(
    <>
    <h1>{file.name}</h1>
    </>
    )
}