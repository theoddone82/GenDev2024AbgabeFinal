import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './forStyle.css';

function Login() {
    const [username, setUsername] = useState('');
    const navigate = useNavigate();

    const handleLogin = async () => {
        const response = await fetch(`http://localhost:8080/users/login?username=${username}`, {
            method: 'POST'
        });

        if (response.ok) {
            const responseData = await response.json();
            const userId = responseData.id;
            // Redirect to the dashboard with the username as a parameter
            navigate(`/dashboard?username=${username}&userId=${userId}`);
                } else {
            alert('Login failed');
        }
    };

    return (
        <div className="login-container">
            <h2>Login</h2>
            <input
                type="text"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                placeholder="Username"
            />
            <button onClick={handleLogin}>Login</button>
        </div>
    );
}

export default Login;
