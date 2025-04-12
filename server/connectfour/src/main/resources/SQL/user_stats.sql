CREATE TABLE user_stats (
    user_id INT PRIMARY KEY,
    wins INT DEFAULT 0,
    losses INT DEFAULT 0,
    win_ratio DOUBLE DEFAULT 0.0,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);
