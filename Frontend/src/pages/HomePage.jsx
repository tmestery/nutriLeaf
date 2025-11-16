import {Link, useNavigate} from 'react-router-dom'
import Navbar from '../components/Navbar.jsx'
import '../styles/homepage.css'

export default function HomePage({userInfo}){
    const navigate = useNavigate()

    function startScan(event){
        navigate('/scan', {state: {file: event.target.files[0]}})
    }

    return(
        <>
        <Navbar />
        {userInfo.username ? 
        <h1 className="page-header">Welcome, {userInfo.username}!</h1> : 
        <h1 className="page-header">Welcome to RecipeBlast!</h1>}
        <hr />

        <div className="page-content">
            <div className="page-content-panel" id="history-panel">
                <h1 className="page-content-header">Recent Scans</h1>
            </div>
            <div className="page-content-panel" id="feature-panel">
                <h1 className="page-content-header">New Scan</h1>
                <form className="scan-upload-form">
                    <label>New Scan</label>
                    <input
                        type="file"
                        id="file-input"
                        name="scanner"
                        accept="image/*"
                        onChange={startScan}
                        multiple={false}
                    />
                </form>
            </div>
            <div className="page-content-panel" id="profile-panel">
                <h1 className="page-content-header">Your Profile</h1>
            </div>
        </div>
        </>
    )
}