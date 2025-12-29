-- data seeding
INSERT INTO Patient_Data
(patient_name, birth_date, email, gender, blood_group)
VALUES
('Ananya Gupta', '1995-03-22', 'ananya.gupta@gmail.com', 'Female', 'A_POSITIVE'),
('Rohit Verma', '1990-11-10', 'rohit.verma@gmail.com', 'Male', 'O_POSITIVE'),
('Sneha Patel', '2000-07-05', 'sneha.patel@gmail.com', 'Female', 'B_POSITIVE'),
('Amit Singh', '1988-01-18', 'amit.singh@gmail.com', 'Male', 'AB_POSITIVE');

INSERT INTO doctor (name, specialization, email) VALUES
('Dr. Rohan Sharma', 'Cardiologist', 'rohan.sharma@hospital.com'),
('Dr. Meera Kapoor', 'Neurologist', 'meera.kapoor@hospital.com'),
('Dr. Aditya Verma', 'Orthopedic', 'aditya.verma@hospital.com'),
('Dr. Simran Kaur', 'Dermatologist', 'simran.kaur@hospital.com'),
('Dr. Raj Malhotra', 'Pediatrician', 'raj.malhotra@hospital.com');
