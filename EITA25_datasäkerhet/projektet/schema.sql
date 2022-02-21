PRAGMA foreign_keys=ON;

DROP TABLE IF EXISTS hospital_divisions;

DROP TABLE IF EXISTS individuals;
DROP TABLE IF EXISTS patients;
DROP TABLE IF EXISTS nurses;
DROP TABLE IF EXISTS doctors;
DROP TABLE IF EXISTS government_agencies;

DROP TABLE IF EXISTS medical_records;


CREATE TABLE hospital_divisions (
  division_id   TEXT UNIQUE, 
  division_name TEXT,

  PRIMARY KEY  (division_id)
);


CREATE TABLE individuals (
  ssn               TEXT UNIQUE,  
  individual_name   TEXT,
  email             TEXT UNIQUE,

  password          TEXT,

  PRIMARY KEY  (ssn)
);

CREATE TABLE patients (
  patient_id        TEXT UNIQUE, 
  ssn               TEXT,

  PRIMARY KEY  (patient_id),

  FOREIGN KEY (ssn) REFERENCES individuals(ssn)
);

CREATE TABLE nurses (
  nurse_id  TEXT UNIQUE, 
  ssn       TEXT,  
  division_id TEXT,

  PRIMARY KEY  (nurse_id),

  FOREIGN KEY (ssn) REFERENCES individuals(ssn),
  FOREIGN KEY (division_id) REFERENCES hospital_divisions(division_id)
);

CREATE TABLE doctors (
  doctor_id  TEXT UNIQUE, 
  ssn        TEXT,  
  division_id TEXT,

  PRIMARY KEY  (doctor_id),

  FOREIGN KEY (ssn) REFERENCES individuals(ssn),
  FOREIGN KEY (division_id) REFERENCES hospital_divisions(division_id)
);

CREATE TABLE government_agencies (
  agency_id  TEXT UNIQUE, 
  ssn        TEXT,  

  PRIMARY KEY  (agency_id),

  FOREIGN KEY (ssn) REFERENCES individuals(ssn)
);



CREATE TABLE medical_records (
  record_id    TEXT UNIQUE,

  patient_id   TEXT,
  doctor_id    TEXT,
  nurse_id     TEXT,

  division_id  TEXT,

  medical_data TEXT,

  PRIMARY KEY  (record_id),

  FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
  FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id),
  FOREIGN KEY (nurse_id) REFERENCES nurses(nurse_id),
  FOREIGN KEY (division_id) REFERENCES hospital_divisions(division_id)
);


INSERT INTO individuals (ssn, individual_name, email, password)
VALUES
  ("123456-0001",  "Alex Sibzamini",     "alex@mail.com",  "password123!"),
  ("123456-0002",  "Emma Potatisodlare", "emma@mail.com",  "password123!"),
  ("123456-0003",  "Alice Pot. Atis",    "alice@mail.com", "password123!"),
  ("123456-0004",  "Bob P. Tato",        "bob@mail.com",   "password123!"),
  ("123456-0005",  "Jonas Pommes",       "jonas@mail.com", "password123!"),
  
  ("123456-0007",  "James Bond",         "007@mail.com",   "password007!");


INSERT INTO patients (patient_id, ssn)
VALUES
  ("p001", "123456-0001");

INSERT INTO hospital_divisions (division_id, division_name)
VALUES
  ("d001", "Division 1"),
  ("d002", "Division 2");


INSERT INTO nurses (nurse_id, ssn, division_id)
VALUES
  ("n001", "123456-0002", "d001"),
  ("n002", "123456-0003", "d002");


INSERT INTO doctors (doctor_id, ssn, division_id)
VALUES
  ("dr001", "123456-0004", "d001"),
  ("dr002", "123456-0005", "d002");

INSERT INTO government_agencies (agency_id, ssn)
VALUES
  ("a007", "123456-0007");

INSERT INTO medical_records (record_id, patient_id, doctor_id, nurse_id, division_id, medical_data)
VALUES
  ("r001", "p001", "dr001", "n001", "d001", "Tummy hurts"),
  ("r002", "p001", "dr002", "n002", "d002", "Tummy still hurts");

