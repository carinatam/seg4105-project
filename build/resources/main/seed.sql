INSERT INTO Users(username, password)
VALUES
  ('admin', 'admin'),
  ('doctor', 'doctor'),
  ('receptionist', 'receptionist'),
  ('patient', 'patient');

INSERT INTO Employee(firstName, lastName, gender, dateOfBirth, address, phone, email, salary, role, username)
VALUES 
  ('John', 'Smith', 'Male', '1950-05-05', '123 Main St', '123-456-7890', 'johnSmith@gmail.com', 300000, 'Doctor', 'doctor'),
  ('Jane', 'Doe', 'Female', '1960-06-06', '456 Main St', '123-456-7890', 'janeDoe@gmail.com', 100000, 'Receptionist', 'receptionist'),
  ('Admin', 'Flem', 'Male', '1970-07-07', '789 Main St', '123-456-7890', 'admin@gmail.com', 200000, 'Admin', 'admin');

INSERT INTO Patient(firstName, lastName, gender, dateOfBirth, address, phone, email, username)
VALUES
  ('Hani', 'Ahn', 'Female', '1992-05-01', 'Seoul, South Korea', '633-345-1234', 'hani@gmail.com', 'patient');

INSERT INTO Appointment(appointmentID, appointmentDate, appointmentTime, appointmentStatus, employeeUsername, patientUsername)
VALUES
  (1, '2020-05-01', '10:00:00', 'Pending', 'doctor', 'patient');

INSERT INTO Prescription(prescriptionID, prescriptionDate, prescription, appointmentID)
VALUES
  (1, '2020-05-01', 'Sertraline, take 1 pill a day', 1);

INSERT INTO Payment(paymentID, paymentDate, paymentTime, paymentAmount, appointmentID)
VALUES
  (1, '2020-05-01', '10:00:00', 100, 1);

INSERT INTO DoctorAvailability(day, startTime, endTime, doctorUsername)
VALUES
  ('Monday', '09:00:00', '17:00:00', 'doctor'),
  ('Tuesday', '09:00:00', '17:00:00', 'doctor'),
  ('Wednesday', '09:00:00', '17:00:00', 'doctor'),
  ('Thursday', '09:00:00', '17:00:00', 'doctor'),
  ('Friday', '09:00:00', '17:00:00', 'doctor');


