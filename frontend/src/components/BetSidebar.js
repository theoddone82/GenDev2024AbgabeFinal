import React, { useState, useEffect } from 'react';
import './BetSidebar.css'; // Adjust the path to the CSS file

const url = 'http://localhost:8080/bets'; // Update this URL if needed

function BetSidebar() {
    const [allBets, setAllBets] = useState([]);
    useEffect(() => {
        const fetchAllBets = async () => {
            try {
                const response = await fetch(url);
                if (response.ok) {
                    const data = await response.json();
                    setAllBets(data);
                } else {
                    console.error('Failed to fetch bets');
                }
            } catch (error) {
                console.error('Error:', error);
            }
        };

        fetchAllBets();
    }, []);

    return (
        <div className="bet-sidebar">
            <h3>All Bets</h3>
            <ul>
                {allBets.map(bet => (
                    <li key={bet.betId}>
                        Bet ID: {bet.betId}, Matchup ID: {bet.matchupId}, Goals Bet Team 1: {bet.goalsBetTeam1}, Goals Bet Team 2: {bet.goalsBetTeam2}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default BetSidebar;
