import React, { useState, useEffect } from 'react';

function CommunitiesList({ userId }) {
    const [communities, setCommunities] = useState([]);
    const [leaderboards, setLeaderboards] = useState({});

    useEffect(() => {
        const fetchCommunities = async () => {
            const params = new URLSearchParams(window.location.search);
            const userId = params.get('userId');
            try {
                const response = await fetch(`http://localhost:8080/communities/user/${userId}`);
                if (response.ok) {
                    const data = await response.json();
                    setCommunities(data);
                    // Fetch leaderboards for each community
                    data.forEach(community => fetchLeaderboard(community.communityId));
                } else {
                    console.error('Failed to fetch communities');
                }
            } catch (error) {
                console.error('Error:', error);
            }
        };

        const fetchLeaderboard = async (communityId) => {
            try {
                const response = await fetch(`http://localhost:8080/communities/${communityId}/userRankings`);
                if (response.ok) {
                    const data = await response.json();
                    setLeaderboards(prevLeaderboards => ({
                        ...prevLeaderboards,
                        [communityId]: data
                    }));
                } else {
                    console.error(`Failed to fetch leaderboard for community ${communityId}`);
                }
            } catch (error) {
                console.error('Error:', error);
            }
        };

        fetchCommunities();
    }, [userId]);  // Include userId as a dependency

    return (
        <div className="communities-list">
            <h3>Communities</h3>
            <ul>
                {communities.map(community => (
                    <li key={community.communityId}>
                        <div>
                            <strong>Community ID:</strong> {community.communityId}, <strong>Community Name:</strong> {community.communityName}
                        </div>
                        <div>
                            <strong>Leaderboard:</strong>
                            {leaderboards[community.communityId] ? (
                                <ul>
                                    {leaderboards[community.communityId].map(user => (
                                        <li key={user.userId}>
                                            {user.username} - {user.score} points
                                        </li>
                                    ))}
                                </ul>
                            ) : (
                                <p>Loading leaderboard...</p>
                            )}
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default CommunitiesList;
