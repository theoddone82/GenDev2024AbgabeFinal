import React, { useState, useEffect } from 'react';

function PlayerRankingsBottomUp() {
    const [rankings, setRankings] = useState([]);
    const [worstRankings, setWorstRankings] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [worstPage, setWorstPage] = useState(1);
    const itemsPerPage = 10;
    const [worstPlayerIndex, setWorstPlayerIndex] = useState(0);


    useEffect(() => {
        const fetchPlayerRankings = async () => {
            try {
                const response = await fetch('http://localhost:8080/users/sorted');
                if (response.ok) {
                    const data = await response.json();
                    setRankings(data.content);
                } else {
                    console.error('Failed to fetch player rankings');
                }
            } catch (error) {
                console.error('Error:', error);
            }
        };

        const fetchWorstPlayerRankings = async () => {
            try {
                const response = await fetch('http://localhost:8080/users/sortedAsc'); // Assuming this API endpoint returns worst users
                if (response.ok) {
                    const data = await response.json();
                    setWorstRankings(data.content);
                } else {
                    console.error('Failed to fetch worst player rankings');
                }
            } catch (error) {
                console.error('Error:', error);
            }
        };
        const fetchWorstPlayerIndex = async () => {
            try {
                const response = await fetch('http://localhost:8080/users/worst');
                if (response.ok) {
                    const data = await response.json();
                    setWorstPlayerIndex(data - 1); // Adjust to 0-based index
                } else {
                    console.error('Failed to fetch worst player index');
                }
            } catch (error) {
                console.error('Error:', error);
            }
        };

        // Fetch data initially
        fetchPlayerRankings();
        fetchWorstPlayerRankings();
        fetchWorstPlayerIndex();
        // Fetch data every 10 seconds
        const interval = setInterval(() => {
            fetchPlayerRankings();
            fetchWorstPlayerRankings();
        }, 10000); // 10000 milliseconds = 10 seconds

        // Cleanup function to clear the interval when component unmounts
        return () => clearInterval(interval);
    }, []);

    const totalPages = Math.ceil(rankings.length / itemsPerPage);
    const worstTotalPages = Math.ceil(worstRankings.length / itemsPerPage);

    const handlePageChange = (page) => {
        if (page >= 1 && page <= totalPages) {
            setCurrentPage(page);
        }
    };

    const handleWorstPageChange = (page) => {
        if (page >= 1 && page <= worstTotalPages) {
            setWorstPage(page);
        }
    };

    const startIndex = (currentPage - 1) * itemsPerPage;
    const worstStartIndex = (worstPage - 1) * itemsPerPage;
    const currentRankings = rankings.slice(startIndex, startIndex + itemsPerPage);
    const currentWorstRankings = worstRankings.slice(worstStartIndex, worstStartIndex + itemsPerPage);

    return (
        <div className="player-rankings">
            <h3>Player Rankings</h3>
            <ol start={startIndex + 1}>
                {currentRankings.map(user => (
                    <li key={user.userId}>
                        <span>{user.username}</span>
                        <span>Score: {user.score}</span>
                    </li>
                ))}
            </ol>
            <div className="pagination">
                <button onClick={() => handlePageChange(currentPage - 1)} disabled={currentPage === 1}>
                    &lt; Previous
                </button>
                <span>Page {currentPage}</span>
                <button onClick={() => handlePageChange(currentPage + 1)} disabled={currentPage === totalPages}>
                    Next &gt;
                </button>
            </div>
            
            <h3>Worst Player Rankings</h3>
            <h4>Worst of all number {worstPlayerIndex} </h4>
            <ol start={worstStartIndex + 1}>
                {currentWorstRankings.map(user => (
                    <li key={user.userId}>
                        <span>{user.username}</span>
                        <span>Score: {user.score}</span>
                    </li>
                ))}
            </ol>
            <div className="pagination">
                <button onClick={() => handleWorstPageChange(worstPage - 1)} disabled={worstPage === 1}>
                    &lt; Previous
                </button>
                <span>Page {worstPage}</span>
                <button onClick={() => handleWorstPageChange(worstPage + 1)} disabled={worstPage === worstTotalPages}>
                    Next &gt;
                </button>
            </div>
        </div>
    );
}

export default PlayerRankingsBottomUp;
