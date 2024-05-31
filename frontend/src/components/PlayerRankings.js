import React, { useState, useEffect } from 'react';

function PlayerRankings() {
    const [rankings, setRankings] = useState([]);
    const [currentPage, setCurrentPage] = useState(1); // State to track the current page
    const itemsPerPage = 10; // Number of items per page

    useEffect(() => {
        const fetchPlayerRankings = async () => {
            try {
                const response = await fetch('http://localhost:8080/users/sorted');
                if (response.ok) {
                    const data = await response.json();
                    setRankings(data.content); // Assuming the rankings are in the 'content' property of the response
                } else {
                    console.error('Failed to fetch player rankings');
                }
            } catch (error) {
                console.error('Error:', error);
            }
        };

        fetchPlayerRankings();
    }, []);

    const totalPages = Math.ceil(rankings.length / itemsPerPage);

    const handlePageChange = (page) => {
        setCurrentPage(page);
    };

    const renderPageNumbers = () => {
        const pageNumbers = [];
        for (let i = 1; i <= totalPages; i++) {
            pageNumbers.push(
                <button
                    key={i}
                    onClick={() => handlePageChange(i)}
                    className={i === currentPage ? 'active' : ''}
                >
                    {i}
                </button>
            );
        }
        return pageNumbers;
    };

    const startIndex = (currentPage - 1) * itemsPerPage;
    const currentRankings = rankings.slice(startIndex, startIndex + itemsPerPage);

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
                {renderPageNumbers()}
            </div>
        </div>
    );
}

export default PlayerRankings;
