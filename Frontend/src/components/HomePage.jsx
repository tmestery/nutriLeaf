import {Link} from 'react-router-dom'

export default function HomePage(){
    return(
        <>
        <h1>Welcome to RecipeBlast!</h1>
        <Link to="/auth/login">Login</Link>
        <Link to="/auth/signup">Signup</Link>
        </>
    )
}