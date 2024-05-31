// components/CommunitiesForm.js

import React, { useState } from 'react';
function CommunitiesForm() {
    const [createFormData, setCreateFormData] = useState({
        userId: '',
        communityName: ''
    });

    const [joinFormData, setJoinFormData] = useState({
        
        communityId: ''
    });

    const handleCreateChange = (e) => {
        const { name, value } = e.target;
        setCreateFormData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleJoinChange = (e) => {
        const { name, value } = e.target;
        setJoinFormData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleCreateSubmit = async (e) => {
        e.preventDefault();

        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(createFormData)
        };

        try {
            const response = await fetch('http://localhost:8080/communities', requestOptions);
            if (!response.ok) {
                throw new Error('Failed to create community');
            }
            alert('Community created successfully!');
            setCreateFormData({ userId: '', communityName: '' }); // Reset form data
        } catch (error) {
            console.error('Error:', error);
            alert('Failed to create community');
        }
    };

    const handleJoinSubmit = async (e) => {
        e.preventDefault();
        const params = new URLSearchParams(window.location.search);
        const userId = params.get('userId');
        const {  communityId } = joinFormData;
        
        const url = `http://localhost:8080/communities/${communityId}/join/${userId}`;

        const requestOptions = {
            method: 'POST'
        };

        try {
            const response = await fetch(url, requestOptions);
            if (!response.ok) {
                throw new Error('Failed to join community');
            }
            alert('Successfully joined community!');
            setJoinFormData({ userId: '', communityId: '' }); // Reset form data
        } catch (error) {
            console.error('Error:', error);
            alert('Failed to join community');
        }
    };

    return (
        <div className="CommunitiesForm">
            <h2>Create Community</h2>
            <form onSubmit={handleCreateSubmit}>

                <label htmlFor="communityName">Community Name:</label>
                <input type="text" id="communityName" name="communityName" value={createFormData.communityName} onChange={handleCreateChange} required /><br />

                <button type="submit">Create Community</button>
            </form>

            <h2>Join Community</h2>
            <form onSubmit={handleJoinSubmit}>

                <label htmlFor="communityId">Community ID:</label>
                <input type="text" id="communityId" name="communityId" value={joinFormData.communityId} onChange={handleJoinChange} required /><br />

                <button type="submit">Join Community</button>
            </form>
        </div>
    );
}

export default CommunitiesForm;
