CREATE TABLE user_stats (
    username VARCHAR(255) PRIMARY KEY,  -- username is now the primary key
    wins INT DEFAULT 0,
    losses INT DEFAULT 0,
    win_ratio DOUBLE DEFAULT 0,
    FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE  -- Foreign Key to users table
);