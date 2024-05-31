# Gen Dev Project for Check24: Betting Website

Welcome to the Gen Dev Project for Check24! Right of the bat i get to say that i really enjoyed working on this project. This project involves developing a betting platform and a webpage as a frontend. 

This README provides an overview of the project, setup instructions, and essential information to help you understand and contribute effectively.

## Table of Contents

1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Technologies Used](#technologies-used)
4. [Setup Instructions](#setup-instructions)
5. [Usage](#usage)
6. [Demo Video](#demo-video)

## Project Overview

In this Project I created a Java backend using Spring Boot.

For Connectivity i use Http for the initial setup and MQtt for real time updates it should scale well.

## Features

- **User Registration and Login**: Insecure sign-up and login functionalities.
- **Real-time Updates**: Live updates and notifications for ongoing events.
- **User Dashboard**: Personalized dashboard for managing bets.
- **Admin Panel**: Admins get a set of Admin Api's + DB Access.

## Technologies Used

- **Frontend**: React.js, JavaScript, CSS
- **Backend**: Java Spring Boot
- **Database**: Postgresql
- **Real-time Updates**: HTTP, MQTT

## Setup Instructions

### Prerequisites

- Node.js (>=14.x)
- Pg Admin 4 (alternatively any other db should okay as well)
- Git
- Java JDK 17
- Gradle

### Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/your-username/check24-betting-platform.git

   change db connectivity in /backend/src/ressources/application.configurations

   cd frontend
   npm install
   npm start 

   on a seperate shell
   cd backend 
   ./gradlew build
   ./gradlew bootRun

## Usage

1. Access the web application at `http://localhost:3000`.
2. Register a new account or log in with existing credentials.
3. Explore the available betting options and place your bets.
4. Manage your account and bets through the user dashboard.

## Demo Video

Watch the demo video to see the project in action:

### The final video:

[![Demo Video](https://img.youtube.com/vi/4oRhoeDlh9c/0.jpg)](https://youtu.be/4oRhoeDlh9c)

Thank you for your interest in the Gen Dev Project for Check24! I hope you find this platform useful and look forward to your response.



### For your entertainment:

[![Demo Video](https://img.youtube.com/vi/qfoWjVnujvU/0.jpg)](https://youtu.be/qfoWjVnujvU)