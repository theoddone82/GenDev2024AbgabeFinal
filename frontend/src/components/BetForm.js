import React, { useState } from 'react';

function BetForm() {
    const params = new URLSearchParams(window.location.search);
    const userId = params.get('userId');
    const [matchupId, setMatchupId] = useState('');
    const [goalsBetTeam1, setGoalsBetTeam1] = useState('');
    const [goalsBetTeam2, setGoalsBetTeam2] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/bets', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    userId: parseInt(userId),
                    matchupId: parseInt(matchupId),
                    goalsBetTeam1: parseInt(goalsBetTeam1),
                    goalsBetTeam2: parseInt(goalsBetTeam2)
                })
            });
            if (response.ok) {
                alert('Bet created successfully!');
            } else {
                console.error('Failed to create bet');
                alert('Failed to create bet');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Failed to create bet');
        }
    };

    return (
        <div className="bet-form">
            <h3>Create New Bet</h3>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="matchupId">Matchup ID:</label>
                    <input
                        type="text"
                        id="matchupId"
                        value={matchupId}
                        onChange={(e) => setMatchupId(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="goalsBetTeam1">Goals Bet for Team 1:</label>
                    <input
                        type="text"
                        id="goalsBetTeam1"
                        value={goalsBetTeam1}
                        onChange={(e) => setGoalsBetTeam1(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="goalsBetTeam2">Goals Bet for Team 2:</label>
                    <input
                        type="text"
                        id="goalsBetTeam2"
                        value={goalsBetTeam2}
                        onChange={(e) => setGoalsBetTeam2(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">Submit</button>
            </form>
        </div>
    );
}

export default BetForm;
