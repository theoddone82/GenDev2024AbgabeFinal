import React, { useState, useEffect } from 'react';
import mqtt from 'mqtt';

function MqttListener() {
    
    const [message, setMessage] = useState('No messages received yet');

    useEffect(() => {
        // Connect to the MQTT broker
        const client = mqtt.connect('tcp://localhost:1883');

        client.on('connect', () => {
            console.log('Connected to MQTT broker');
            client.subscribe('leaderboard', (err) => {
                if (!err) {
                    console.log('Subscribed to topic: leaderboard');
                }
            });
        });

        client.on('message', (topic, message) => {
            setMessage(message.toString());
            console.log(`Received message: ${message.toString()}`);
        });

        // Clean up the subscription on component unmount
        return () => {
            client.end();
        };
    }, []);
}

export default MqttListener;
