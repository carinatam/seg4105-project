CREATE TYPE employeeRole AS ENUM ('Doctor', 'Receptionist', 'Admin');

CREATE TYPE appointmentStatus AS ENUM ('Pending', 'Confirmed', 'Cancelled');

CREATE TABLE Users (
  username VARCHAR(30) NOT NULL,
  password VARCHAR(256) NOT NULL,
  PRIMARY KEY (username)
);

CREATE TABLE Patient (
  firstName VARCHAR(256) NOT NULL,
  lastName VARCHAR(256) NOT NULL,
  gender VARCHAR(6) NOT NULL,
  dateOfBirth DATE NOT NULL,
  address VARCHAR(256) NOT NULL,
  phone VARCHAR(256) NOT NULL,
  email VARCHAR(256) NOT NULL,
  username VARCHAR(30) NOT NULL,
  PRIMARY KEY (username),
  FOREIGN KEY (username) REFERENCES Users(username)
);

CREATE TABLE Employee (
  firstName VARCHAR(256) NOT NULL,
  lastName VARCHAR(256) NOT NULL,
  gender VARCHAR(6) NOT NULL,
  dateOfBirth DATE NOT NULL,
  address VARCHAR(256) NOT NULL,
  phone VARCHAR(256) NOT NULL,
  email VARCHAR(256) NOT NULL,
  salary INT NOT NULL,
  role employeeRole NOT NULL,
  username VARCHAR(30) NOT NULL,
  PRIMARY KEY (username),
  FOREIGN KEY (username) REFERENCES Users(username)
);

CREATE TABLE Appointment (
  appointmentID INT NOT NULL,
  appointmentDate DATE NOT NULL,
  appointmentTime TIME NOT NULL,
  appointmentStatus appointmentStatus NOT NULL,
  employeeUsername VARCHAR(30) NOT NULL,
  patientUsername VARCHAR(30) NOT NULL,
  PRIMARY KEY (appointmentID),
  FOREIGN KEY (employeeUsername) REFERENCES Employee(username),
  FOREIGN KEY (patientUsername) REFERENCES Patient(username)
);

CREATE TABLE Prescription (
  prescriptionID INT NOT NULL,
  prescriptionDate DATE NOT NULL,
  prescription VARCHAR(256) NOT NULL,
  appointmentID INT NOT NULL,
  PRIMARY KEY (prescriptionID),
  FOREIGN KEY (appointmentID) REFERENCES Appointment(appointmentID)
);

CREATE TABLE Payment (
  paymentID INT NOT NULL,
  paymentDate DATE NOT NULL,
  paymentTime TIME NOT NULL,
  paymentAmount FLOAT NOT NULL,
  appointmentID INT NOT NULL,
  PRIMARY KEY (paymentID),
  FOREIGN KEY (appointmentID) REFERENCES Appointment(appointmentID)
);

CREATE TABLE DoctorAvailability (
  day VARCHAR(9) NOT NULL,
  startTime TIME NOT NULL,
  endTime TIME NOT NULL,
  doctorUsername VARCHAR(30) NOT NULL,
  FOREIGN KEY (doctorUsername) REFERENCES Employee(username)
);