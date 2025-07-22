-- Sample Data for Actors
INSERT INTO Actors (FirstName, LastName, DateOfBirth, Nationality) VALUES
('Vin', 'Diesel', '1967-07-18', 'American'),
('Gal', 'Gadot', '1985-04-30', 'Israeli'),
('Chris', 'Pine', '1980-08-26', 'American');

-- Sample Data for Movies
INSERT INTO Movies (Title, ReleaseYear, Rating, Genre, Director) VALUES
('The Fast and the Furious', 2009, 'PG-13', 'Action', 'Justin Lin'),
('Wonder Woman', 2017, 'PG-13', 'Action/Adventure', 'Patty Jenkins');

-- Sample Data for Credits
INSERT INTO Credits (MovieId, ActorId, CharacterName, Role) VALUES
(1, 1, 'Dominic Toretto', 'Lead Actor'),
(1, 2, 'Gisele', 'Supporting Actor'),
(2, 2, 'Diana/Wonder Woman', 'Lead Actor'),
(2, 3, 'Steve Trevor', 'Supporting Actor');