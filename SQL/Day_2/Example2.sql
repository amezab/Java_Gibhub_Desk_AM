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
 FOREIGN KEY (AgencyId)
 REFERENCES Agency(AgencyId)
);
