INSERT INTO user ( first_name, email, password_hash, phone, created_at,role)
VALUES
       ( 'ohara', 'a@a.com', '$2a$10$PwiM9Z7TdRjSOrOHlpVTz.Xwpmaa/f/tqTN.eeXOXvSXHzT9G6Ha.', '1234567890', NOW(),'ADMINISTRATEUR'),
        ( 'Bob', 'b@b.com', '$2a$10$PwiM9Z7TdRjSOrOHlpVTz.Xwpmaa/f/tqTN.eeXOXvSXHzT9G6Ha.', '0987654321', NOW(),'RESERVATION'),
         ( 'Charlie', 'c@c.com', '$2a$10$PwiM9Z7TdRjSOrOHlpVTz.Xwpmaa/f/tqTN.eeXOXvSXHzT9G6Ha.', '1112223333', NOW(),'UTILISATEUR'),
          ( 'Diana', 'diana@example.com', '$2a$10$PwiM9Z7TdRjSOrOHlpVTz.Xwpmaa/f/tqTN.eeXOXvSXHzT9G6Ha.', '4445556666', NOW(),'UTILISATEUR'),
           ( 'Eve', 'eve@example.com', '$2a$10$PwiM9Z7TdRjSOrOHlpVTz.Xwpmaa/f/tqTN.eeXOXvSXHzT9G6Ha.', '7778889999', NOW(),'UTILISATEUR');



INSERT INTO event (title, description, start_date, end_date, location, max_capacity, organizer_id, deleted,subject)
VALUES
    ('Metz - Reims', 'Match amical', NOW(), DATE_ADD(NOW(), INTERVAL 90 MINUTE), 'Paris', 100, 1, false,'match amical entre les deux clubs au couleurs rouges'),
    ('Metz - Lens', 'Match amical', NOW(), DATE_ADD(NOW(), INTERVAL 90 MINUTE), 'Lyon', 200, 2, false, 'les clubs rivaux se retrouvent en match amical'),
    ('Metz - Monaco', 'Ligue 1', NOW(), DATE_ADD(NOW(), INTERVAL 90 MINUTE), 'Marseille', 50, 3, false,'Metz affronte le Monaco pour la 5 eme journée de Ligue 1, venez les soutenir face aux champions d europe'),
    ('Metz - PSG', 'Coupe de france', '2025-06-23T06:56:23', DATE_ADD('2025-06-23T06:56:23', INTERVAL 90 MINUTE), 'Nice', 150, 1, false,null),
    ('Metz - Lyon', 'Coupe de france', '2025-02-23T06:56:23', DATE_ADD('2025-02-23T06:56:23', INTERVAL 90 MINUTE), 'Nice', 150, 1, false,null),
    ('Metz - Marseille', 'Ligue 1', '2025-03-23T06:56:23', DATE_ADD('2025-03-23T06:56:23', INTERVAL 90 MINUTE), 'Toulouse', 300, 1, false,null),
    ('Metz - Bordeaux', 'Ligue 2', '2024-09-14T19:00:00', DATE_ADD('2024-09-14T19:00:00', INTERVAL 90 MINUTE), 'Stade Saint-Symphorien', 25000, 2, false,null),
    ('Metz - Paris FC', 'Coupe de la Ligue', NOW(), DATE_ADD(NOW(), INTERVAL 90 MINUTE), 'Stade Saint-Symphorien', 18000, 3, false,null),
    ('Metz - AS Nancy', 'Derby Lorrain', '2024-10-05T20:00:00', DATE_ADD('2024-10-05T20:00:00', INTERVAL 90 MINUTE), 'Stade Marcel Picot', 15000, 1, false,null),
    ('Metz - Stade Rennais', 'Ligue 1', '2025-04-12T17:00:00', DATE_ADD('2025-04-12T17:00:00', INTERVAL 90 MINUTE), 'Roazhon Park', 22000, 2, false,null),
    ('Metz - OGC Nice', 'Match amical', '2024-07-20T18:30:00', DATE_ADD('2024-07-20T18:30:00', INTERVAL 90 MINUTE), 'Allianz Riviera', 8000, 1, false,null),
    ('Metz - Strasbourg', 'Derby de l\'Est', '2024-11-23T19:45:00', DATE_ADD('2024-11-23T19:45:00', INTERVAL 90 MINUTE), 'Stade de la Meinau', 24000, 1, false,null),
    ('Metz - LOSC Lille', 'Ligue 1', '2025-01-15T20:00:00', DATE_ADD('2025-01-15T20:00:00', INTERVAL 90 MINUTE), 'Stade Pierre-Mauroy', 45000, 2, false,null),
    ('Metz - FC Nantes', 'Coupe de France', '2024-12-04T21:00:00', DATE_ADD('2024-12-04T21:00:00', INTERVAL 90 MINUTE), 'Stade de la Beaujoire', 32000, 1, false,null),
    ('Metz - Angers SCO', 'Championnat National', NOW(), DATE_ADD(NOW(), INTERVAL 90 MINUTE), 'Stade Raymond Kopa', 12000, 2, false,null),
    ('Metz - AS Saint-Étienne', 'Ligue 2', '2024-08-30T19:00:00', DATE_ADD('2024-08-30T19:00:00', INTERVAL 90 MINUTE), 'Stade Geoffroy-Guichard', 38000, 2, false,null),
    ('Metz - RC Lens', 'Trophée des Champions', '2025-07-01T20:30:00', DATE_ADD('2025-07-01T20:30:00', INTERVAL 90 MINUTE), 'Stade Bollaert-Delelis', 28000, 1, false,null),
    ('Metz - Toulouse FC', 'Match de gala', '2024-06-15T17:30:00', DATE_ADD('2024-06-15T17:30:00', INTERVAL 90 MINUTE), 'Stade Saint-Symphorien', 20000, 1, false,null),
    ('Metz - Olympique Lyonnais', 'Europa League', '2025-02-28T18:00:00', DATE_ADD('2025-02-28T18:00:00', INTERVAL 90 MINUTE), 'Groupama Stadium', 55000, 1, false,null),
    ('Metz - Stade Brestois', 'Tournoi des jeunes', NOW(), DATE_ADD(NOW(), INTERVAL 90 MINUTE), 'Stade Francis-Le Blé', 8000, 1, false,null),
    ('Metz - Clermont Foot', 'Barrage Ligue 1', '2025-05-24T20:45:00', DATE_ADD('2025-05-24T20:45:00', INTERVAL 90 MINUTE), 'Stade Gabriel Montpied', 13000, 2, false,null),
    ('Metz - Nantes', 'Ligue 1', NOW(), DATE_ADD(NOW(), INTERVAL 90 MINUTE), 'Bordeaux', 120, 2, false,null),
    ('Metz - Strasbourg', 'Match amical', NOW(), DATE_ADD(NOW(), INTERVAL 90 MINUTE), 'Paris', 80, 1, false,null),
    ('Metz - Rennes', 'Coupe de france', '2025-05-10T14:00:00', DATE_ADD('2025-05-10T14:00:00', INTERVAL 90 MINUTE), 'Lille', 90, 3, false,null),
    ('Metz - Toulouse', 'Ligue 1', '2025-04-15T18:30:00', DATE_ADD('2025-04-15T18:30:00', INTERVAL 90 MINUTE), 'Nice', 110, 1, false,null),
    ('Metz - Brest', 'Match amical', '2025-07-01T16:00:00', DATE_ADD('2025-07-01T16:00:00', INTERVAL 90 MINUTE), 'Lyon', 75, 2, false,null),
    ('Metz - Montpellier', 'Coupe de france', '2025-02-01T20:00:00', DATE_ADD('2025-02-01T20:00:00', INTERVAL 90 MINUTE), 'Marseille', 130, 3, false,null),
    ('Metz - Auxerre', 'Ligue 1', '2025-05-22T19:00:00', DATE_ADD('2025-05-22T19:00:00', INTERVAL 90 MINUTE), 'Bordeaux', 95, 1, false,null),
    ('Metz - Lorient', 'Match amical', NOW(), DATE_ADD(NOW(), INTERVAL 90 MINUTE), 'Paris', 100, 1, false,null),
    ('Metz - Clermont', 'Ligue 1', '2025-03-12T17:00:00', DATE_ADD('2025-03-12T17:00:00', INTERVAL 90 MINUTE), 'Nice', 140, 2, false,null),
    ('Metz - Le Havre', 'Coupe de france', '2025-01-18T15:00:00', DATE_ADD('2025-01-18T15:00:00', INTERVAL 90 MINUTE), 'Toulouse', 85, 1, false,null),
    ('Metz - Dijon', 'Match amical', '2025-06-05T19:30:00', DATE_ADD('2025-06-05T19:30:00', INTERVAL 90 MINUTE), 'Strasbourg', 60, 2, false,null),
    ('Metz - Nancy', 'Ligue 1', NOW(), DATE_ADD(NOW(), INTERVAL 90 MINUTE), 'Reims', 125, 3, false,null),
    ('Metz - Ajaccio', 'Coupe de france', '2025-04-01T18:00:00', DATE_ADD('2025-04-01T18:00:00', INTERVAL 90 MINUTE), 'Nantes', 70, 1, false,null),
    ('Metz - Angers', 'Match amical', '2025-07-10T20:00:00', DATE_ADD('2025-07-10T20:00:00', INTERVAL 90 MINUTE), 'Lille', 100, 1, false,null),
    ('Metz - Saint-Étienne', 'Ligue 1', '2025-05-30T20:00:00', DATE_ADD('2025-05-30T20:00:00', INTERVAL 90 MINUTE), 'Paris', 110, 3, false,null);






INSERT INTO seat_category ( name, capacity, price)
VALUES
    ( 'VIP', 500,50.99),
    ( 'Standard',2000,49.99),
    ( 'Premium', 2500,39.99),
    ( 'Économique', 3000,29.99),
    ( 'Famille', 10000,25.99);

INSERT INTO seat_category (name, capacity, price, event_id)
SELECT
    sc.name,
    sc.capacity,
    sc.price,
    e.id
FROM seat_category sc
         CROSS JOIN event e;



INSERT INTO reservation ( quantity, reservation_date, is_paid, payment_id, user_id, event_id, qr_code)
VALUES
    ( 1,NOW(),false,null,1,2,'eyJhbGciOiJIUzI1NiJ9.eyJyZXNlcnZhdGlvbklkIjo2LCJ1c2VySWQiOjEsImV2ZW50SWQiOjJ9.jR5CEI_lN1zh_sCPcsnTWg9rQ0myiuBLaxoqeqHS21M'),
    ( 1, NOW(), false,null , 1, 2,'eyJhbGciOiJIUzI1NiJ9.eyJyZXNlcnZhdGlvbklkIjo3LCJ1c2VySWQiOjEsImV2ZW50SWQiOjN9.axZt7grKf5AgGs8X48-TAsTrspdqTEcr66StynVySlA'),
    ( 2, NOW(), false, null, 2, 1,'eyJhbGciOiJIUzI1NiJ9.eyJyZXNlcnZhdGlvbklkIjo4LCJ1c2VySWQiOjIsImV2ZW50SWQiOjF9.TWi_TyU3IpYU6eww2Qe86Kx3JkFyKXjt4EVD1vbbAY8'),
    ( 2, NOW(), false, null , 2, 2,'eyJhbGciOiJIUzI1NiJ9.eyJyZXNlcnZhdGlvbklkIjo5LCJ1c2VySWQiOjIsImV2ZW50SWQiOjJ9.gASmKuByY3hGBO0czNJihI2fqA5DvuBBHLF9-zpboNg'),
    ( 1, NOW(), false, null , 2, 3,'eyJhbGciOiJIUzI1NiJ9.eyJyZXNlcnZhdGlvbklkIjoxMCwidXNlcklkIjoyLCJldmVudElkIjozfQ.OgHGz8rNc8iWzq76DwAYtbT2C8AI9DL674mixkFonfY'),
    ( 2, NOW(), false, null , 2, 3,'eyJhbGciOiJIUzI1NiJ9.eyJyZXNlcnZhdGlvbklkIjoxMSwidXNlcklkIjoyLCJldmVudElkIjozfQ.y9m41-VnV7cXP1ZmguyGXajkJ1_Ze-HCXYh2kfqwJP4'),
    ( 2, NOW(), false, null , 2, 17,'eyJhbGciOiJIUzI1NiJ9.eyJyZXNlcnZhdGlvbklkIjoxMiwidXNlcklkIjoyLCJldmVudElkIjoxN30.OgL2eFrHTsQpCcIdbCoWKuEclilpZMWeh9sVnmSOOzY'),
    ( 2, NOW(), false, null , 2, 17,'eyJhbGciOiJIUzI1NiJ9.eyJyZXNlcnZhdGlvbklkIjoxMywidXNlcklkIjoyLCJldmVudElkIjoxN30.z0OR0svZ9WYcOV8kZjEB576TvNUrqzdqqBMzNi-NsPc'),
    ( 2, NOW(), false, null , 3, 1,'eyJhbGciOiJIUzI1NiJ9.eyJyZXNlcnZhdGlvbklkIjoxNCwidXNlcklkIjozLCJldmVudElkIjoxfQ.qQScRqJad5ITZfOw33rVSBj3rnCsk67gBqbKjp9-AdU'),
    ( 2, NOW(), false, null , 3, 1,'eyJhbGciOiJIUzI1NiJ9.eyJyZXNlcnZhdGlvbklkIjoxNSwidXNlcklkIjozLCJldmVudElkIjoxfQ.MrOsN7iGDZ7auWwqTBm7VfF_4wxMHwp7FYrRugFP9o4'),
    ( 2, NOW(), false, null , 3, 4,'eyJhbGciOiJIUzI1NiJ9.eyJyZXNlcnZhdGlvbklkIjoxNiwidXNlcklkIjozLCJldmVudElkIjo0fQ.HgJHhaOoTi-BUKwX9Woz0Yu9yXTtMDC8L9ejYQCNhAw'),
    ( 2, NOW(), false, null , 3, 4,'eyJhbGciOiJIUzI1NiJ9.eyJyZXNlcnZhdGlvbklkIjoxNywidXNlcklkIjozLCJldmVudElkIjo0fQ.gfwvPZsr_EkCahqBwTRz8-h8JiFrGi-1rdw4e299yhc');

INSERT INTO player ( first_name, last_name, number) VALUES
                                                                     ('Jean',     'Dupont',      7),
                                                                     ('Alice',    'Martin',      10),
                                                                     ('Luc',      'Bernard',     4),
                                                                     ('Sophie',   'Durand',      12),
                                                                     ('Nicolas',  'Leroy',       3),
                                                                     ('Camille',  'Faure',       9),
                                                                     ('Antoine',  'Rousseau',    11),
                                                                     ('Clara',    'Petit',       5),
                                                                     ('Thomas',   'Garnier',     8),
                                                                     ('Marion',   'Chevalier',   2),
                                                                     ('Yann',     'Blanc',       6),
                                                                     ('Inès',     'Leclerc',     13),
                                                                     ('Paul',     'Moreau',      14),
                                                                     ('Emma',     'Girard',      1),
                                                                     ('Hugo',     'Marchand',    15);


INSERT INTO vote ( vote_date, event_id, user_id,score,comment,player_id)
VALUES
    ( NOW(), 1, 1,5,'moyen',1),
    ( NOW(), 2, 2,7,'bien',5),
    ( NOW(), 3, 3,8,'très bien',10),
    ( NOW(), 4, 4,3,'nul',4),
    ( NOW(), 5, 5,5,'passable',3);

INSERT INTO rating ( score, comment, rating_date, event_id, user_id)
VALUES
    ( 5, 'Super événement !', NOW(), 1, 1),
    ( 4, 'Très bon moment', NOW(), 2, 2),
    ( 3, 'Correct', NOW(), 3, 3),
    ( 2, 'Un peu décevant', NOW(), 4, 4),
    ( 5, 'Excellent !', NOW(), 5, 5);


INSERT INTO notification ( message, type, is_read, created_at, user_id)
VALUES
    ( 'Votre réservation est confirmée', 'info', false, NOW(), 1),
    ( 'Nouvel événement disponible', 'alert', false, NOW(), 2),
    ( 'Mise à jour du programme', 'info', true, NOW(), 3),
    ( 'Nouveau message reçu', 'message', false, NOW(), 4),
    ( 'Votre vote a été comptabilisé', 'info', true, NOW(), 5);

INSERT INTO friendship ( status, user1_id, user2_id)
VALUES
    ( 'accepted', 1, 2),
    ( 'pending', 2, 3),
    ( 'accepted', 3, 4),
    ( 'blocked', 4, 5),
    ( 'accepted', 1, 5);

INSERT INTO log ( date, action, user_id)
VALUES
    ( NOW(), 'User login', 1),
    ( NOW(), 'Create reservation', 2),
    ( NOW(), 'Vote submitted', 3),
    ( NOW(), 'Event created', 4),
    ( NOW(), 'Rating posted', 5);


INSERT INTO attendance ( check_in_time, user_id, event_id)
VALUES
    ( NOW() - INTERVAL 10 MINUTE, 1, 1),
    ( NOW() - INTERVAL 10 MINUTE, 1, 2),
    ( NOW() - INTERVAL 10 MINUTE, 1, 3),
    ( NOW() - INTERVAL 10 MINUTE, 1, 4),
    ( NOW() - INTERVAL 10 MINUTE, 1, 5),
    ( NOW() - INTERVAL 10 MINUTE, 1, 6),
    ( NOW() - INTERVAL 10 MINUTE, 1, 7),
    ( NOW() - INTERVAL 10 MINUTE, 1, 8),
    ( NOW() - INTERVAL 10 MINUTE, 1, 9),
    ( NOW() - INTERVAL 10 MINUTE, 1, 10),
    ( NOW() - INTERVAL 15 MINUTE, 2, 2),
    ( NOW() - INTERVAL 20 MINUTE, 3, 3),
    ( NOW() - INTERVAL 25 MINUTE, 4, 4),
    ( NOW() - INTERVAL 30 MINUTE, 5, 5);


INSERT INTO event_player (event_id, player_id)
SELECT e.id, p.id
FROM event AS e
         CROSS JOIN player AS p;