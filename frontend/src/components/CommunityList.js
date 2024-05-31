import React, { useState, useEffect } from 'react';

function CommunityList() {
    const [communities, setCommunities] = useState([]);

    useEffect(() => {
        const fetchCommunities = async () => {
            try {
                const response = await fetch('http://localhost:8080/communities'); // Update this URL if needed
                if (response.ok) {
                    const data = await response.json();
                    setCommunities(data);
                    console.log(data);
                } else {
                    console.error('Failed to fetch communities');
                }
            } catch (error) {
                console.error('Error:', error);
            }
        };

        fetchCommunities();
    }, []);

    return (
        <div className="communities-list">
            <h3>All Communities</h3>
            <ul>
                {communities.map(community => (
                    <li key={community.communityId}>
                        Community ID: {community.communityId}, Name: {community.communityName}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default CommunityList;
