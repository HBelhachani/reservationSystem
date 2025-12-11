INSERT INTO aeroport (id, code, ville) VALUES
('1', 'YUL', 'Montréal'),
('2', 'YYZ', 'Toronto'),
('3', 'CDG', 'Paris'),
('4', 'YVR', 'Vancouver'),
('5', 'JFK', 'New York'),
('6', 'LHR', 'London'),
('7', 'DXB', 'Dubai');

INSERT INTO operateur (id, code, nom) VALUES
('1', 'AC', 'Air Canada'),
('2', 'AF', 'Air France'),
('3', 'WS', 'WestJet'),
('4', 'EK', 'Emirates'),
('5', 'BA', 'British Airways');

INSERT INTO vol (id, numero, origine_id, destination_id, duree) VALUES
('101', 'AC870', '1', '3', 420),
('102', 'AC401', '1', '2', 80),
('103', 'WS123', '2', '4', 300),
('104', 'AF344', '3', '1', 435),
('105', 'AC765', '1', '5', 75),
('106', 'EK500', '7', '6', 420),
('107', 'BA150', '6', '1', 420);

INSERT INTO offre (id, prix, date_depart, vol_id, operateur_id) VALUES
('1001', 850.00, '2025-12-15T19:30', '101', '1'),
('1002', 890.00, '2025-12-22T13:10', '104', '2'),
('1003', 120.00, '2025-12-10T08:15', '102', '1'),
('1004', 320.00, '2025-12-11T09:45', '103', '3'),
('1005', 150.00, '2025-12-12T07:00', '105', '1'),
('1006', 1200.00, '2025-12-20T12:00', '106', '4'),
('1007', 780.50, '2025-12-25T15:30', '107', '5');
