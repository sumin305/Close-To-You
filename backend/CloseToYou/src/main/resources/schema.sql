CREATE TABLE IF NOT EXISTS closet_codes  (
      closet_code_id INT AUTO_INCREMENT PRIMARY KEY,
      closet_code VARCHAR(255) NOT NULL,
      is_used BOOLEAN DEFAULT FALSE
);
