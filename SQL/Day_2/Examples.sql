DROP DATABASE IF EXISTS FieldAgent;
CREATE DATABASE FieldAgent;
USE FieldAgent;
-- Agency table
CREATE TABLE Agency (
 AgencyId INT PRIMARY KEY AUTO_INCREMENT,
 ShortName VARCHAR(25) NOT NULL,
 LongName VARCHAR(250) NOT NULL
);
-- Location table
CREATE TABLE `Location` (
 LocationId INT PRIMARY KEY AUTO_INCREMENT,
 `Name` VARCHAR(25) NOT NULL,
 `Address` VARCHAR(125) NOT NULL,
 City VARCHAR(50) NOT NULL,
 CountryCode VARCHAR(5) NOT NULL,
 PostalCode VARCHAR(15) NOT NULL,
 AgencyId INT NOT NULL,
 CONSTRAINT fk_Location_AgencyId
 FOREIGN KEY (AgencyId)
 REFERENCES Agency(AgencyId)
);
-- Agent table
CREATE TABLE Agent (
 AgentId INT PRIMARY KEY AUTO_INCREMENT,
 FirstName VARCHAR(25) NOT NULL,
 MiddleName VARCHAR(25) NULL,
 LastName VARCHAR(25) NOT NULL,
  DOB DATE NULL,
 HeightInInches INT NOT NULL
);
-- SecurityClearance table
CREATE TABLE SecurityClearance (
 SecurityClearanceId INT PRIMARY KEY AUTO_INCREMENT,
 `Name` VARCHAR(50) NOT NULL
);
-- AgencyAgent bridge table
CREATE TABLE AgencyAgent (
 AgencyId INT NOT NULL,
 AgentId INT NOT NULL,
 Identifier VARCHAR(50) NOT NULL,
 ActivationDate DATE NOT NULL,
 IsActive BIT NOT NULL DEFAULT 1,
 SecurityClearanceId INT NOT NULL,
 CONSTRAINT pk_AgencyAgent PRIMARY KEY (AgencyId, AgentId),
 CONSTRAINT fk_AgencyAgent_AgencyId
 FOREIGN KEY (AgencyId) REFERENCES Agency(AgencyId),
 CONSTRAINT fk_AgencyAgent_AgentId
 FOREIGN KEY (AgentId) REFERENCES Agent(AgentId),
 CONSTRAINT fk_AgencyAgent_SecurityClearanceId
 FOREIGN KEY (SecurityClearanceId) REFERENCES
SecurityClearance(SecurityClearanceId),
 CONSTRAINT uq_AgencyAgent_Identifier_AgencyId
 UNIQUE (Identifier, AgencyId)
 );
-- Alias table
CREATE TABLE Alias (
AliasId INT PRIMARY KEY AUTO_INCREMENT,
 `Name` VARCHAR(125) NOT NULL,
 Persona VARCHAR(2048) NULL,
 AgentId INT NOT NULL,
 CONSTRAINT fk_Alias_AgentId
 FOREIGN KEY (AgentId) REFERENCES Agent(AgentId)
);
-- Mission table
CREATE TABLE Mission (
 MissionId INT PRIMARY KEY AUTO_INCREMENT,
 CodeName VARCHAR(50) NOT NULL,
 Notes BLOB,
 StartDate DATE NOT NULL,
 ProjectedEndDate DATE NOT NULL,
 ActualEndDate DATE NULL,
 OperationalCost DECIMAL(10,2) NOT NULL,
 AgencyId INT NOT NULL,
 CONSTRAINT fk_Mission_AgencyId
 FOREIGN KEY (AgencyId) REFERENCES Agency(AgencyId)
);
-- MissionAgent bridge table
CREATE TABLE MissionAgent (
 MissionId INT NOT NULL,
 AgentId INT NOT NULL,
 CONSTRAINT pk_MissionAgent PRIMARY KEY (MissionId, AgentId),
 CONSTRAINT fk_MissionAgent_MissionId
 FOREIGN KEY (MissionId) REFERENCES Mission(MissionId),
 CONSTRAINT fk_MissionAgent_AgentId
 FOREIGN KEY (AgentId) REFERENCES Agent(AgentId)
);


