// components/CommunitiesForm.js

import React, { useState } from 'react';

function CommunitiesForm() {
    const [formData, setFormData] = useState({
        userId: '',
        communityName: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(formData)
        };

        try {
            const response = await fetch('http://localhost:8080/communities', requestOptions);
            if (!response.ok) {
                throw new Error('Failed to create community');
            }
            alert('Community created successfully!');
            setFormData({ userId: '', communityName: '' }); // Reset form data
        } catch (error) {
            console.error('Error:', error);
            alert('Failed to create community');
        }
    };

    return (
        <div className = "CommunitiesForm">
            <h2>Create Community</h2>

            <form onSubmit={handleSubmit}>
                <label htmlFor="userId">User ID:</label>
                <input type="text" id="userId" name="userId" value={formData.userId} onChange={handleChange} required /><br />

                <label htmlFor="communityName">Community Name:</label>
                <input type="text" id="communityName" name="communityName" value={formData.communityName} onChange={handleChange} required /><br />

                <button type="submit">Create Community</button>
            </form>
        </div>
    );
}

export default CommunitiesForm;
