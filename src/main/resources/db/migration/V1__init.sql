-- Create Cars Table
CREATE TABLE cars (
    id SERIAL PRIMARY KEY,
    make VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    trim VARCHAR(50),
    year INT NOT NULL,
    engine VARCHAR(100) NOT NULL,
    horsepower INT NOT NULL,
    weight INT NOT NULL,
    torque INT,
    drivetrain VARCHAR(50),
    color VARCHAR(20),
    image_url TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Users Table (Basic version)
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- Index for fast lookup on cars
CREATE INDEX idx_cars_make_model_year ON cars (make, model, year);

-- Create Races Table
CREATE TABLE races (
    id SERIAL PRIMARY KEY,
    car1_id INT NOT NULL REFERENCES cars(id) ON DELETE CASCADE,
    car2_id INT NOT NULL REFERENCES cars(id) ON DELETE CASCADE,
    status VARCHAR(20) DEFAULT 'pending' CHECK (status IN ('pending', 'used')),
    selected_for_date DATE UNIQUE,
    winner_id INT REFERENCES cars(id) ON DELETE CASCADE,
    approved_by INT REFERENCES users(id) ON DELETE SET NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Submission Table
CREATE TABLE submissions (
    id SERIAL PRIMARY KEY,
    video_url TEXT NOT NULL,
    race_id INT REFERENCES races(id) ON DELETE CASCADE,
    storage_type VARCHAR(20) NOT NULL DEFAULT 'youtube' CHECK (storage_type IN ('youtube', 'vimeo', 'other')),
    submitted_by INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'pending' CHECK (status IN ('pending', 'approved', 'rejected'))

);

-- Create Race Votes Table (Voting for daily race selection)
CREATE TABLE race_votes (
    id SERIAL PRIMARY KEY,
    race_id INT NOT NULL REFERENCES races(id) ON DELETE CASCADE,
    user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    voted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE race_guess (
    id SERIAL PRIMARY KEY,
    race_id INT NOT NULL REFERENCES races(id) ON DELETE CASCADE,
    user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    guessed_winner INT NOT NULL REFERENCES cars(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);