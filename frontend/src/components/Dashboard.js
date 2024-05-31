import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import PlayerRankings from './PlayerRankings';
import BetSidebar from './BetSidebar';
import Matchups from './Matchups';
import BetForm from './BetForm';
import './BetSidebar.css';
import './forStyle.css';
import JoinCommunityForm from './JoinCommunityForm';
import CommunitiesList from './CommunitiesList';
import PlayerRankingsBottomUp from './PlayerRankingBottomUp';
import MqttListener from './MqttListener';
import CommunityList from './CommunityList';

function useQuery() {
    return new URLSearchParams(useLocation().search);
}

function Dashboard() {
    const query = useQuery();
    const username = query.get('username');
    const userId = query.get('userId');

    const [position, setPosition] = useState(null);

    useEffect(() => {
        const fetchPosition = async () => {
            const url = `http://localhost:8080/users/${userId}/position`;
            const requestOptions = {
                method: 'GET',
            };

            try {
                const response = await fetch(url, requestOptions);
                if (response.ok) {
                    const data = await response.json();
                    setPosition(data);
                } else {
                    console.error('Failed to fetch position');
                }
            } catch (error) {
                console.error('Error:', error);
            }
        };

        if (userId) {
            fetchPosition();
        }
    }, [userId]);

    return (
        <div className="dashboard-container">
            <div className="main-content">
                <h2>Welcome, {username}, Your Position is {position !== null ? position : 'loading...'}!</h2>
                {/* Main content of the dashboard */}
            </div>
            <div className="sidebar">
                <div className="sidebar-content">
                    <BetSidebar />
                    <CommunityList />
                    <Matchups />
                    <JoinCommunityForm />
                    <CommunitiesList />
                    <PlayerRankingsBottomUp />
                    <MqttListener />
                    <BetForm />
                </div>
            </div>
        </div>
    );
}

export default Dashboard;
