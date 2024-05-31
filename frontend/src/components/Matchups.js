import React, { useState, useEffect } from 'react';

function Matchups() {
    const [matchups, setMatchups] = useState([]);

    useEffect(() => {
        const fetchMatchups = async () => {
            try {
                const response = await fetch('http://localhost:8080/matchups');
                if (response.ok) {
                    const data = await response.json();
                    setMatchups(data);
                } else {
                    console.error('Failed to fetch matchups');
                }
            } catch (error) {
                console.error('Error:', error);
            }
        };

        fetchMatchups();
    }, []);

    return (
        <div className="matchups-box">
            <h3>Matchups</h3>
            <ul>
                {matchups.map(matchup => (
                    <li key={matchup.matchupId}>
                        matchupID = {matchup.matchupId}  Team1:
                        {matchup.team1} vs Team2: {matchup.team2} - {matchup.startTime} 
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default Matchups;
