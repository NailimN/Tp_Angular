-- ============================
-- GENRES
-- ============================
INSERT INTO genre (libelle) VALUES 
('Fantasy'),
('Science-Fiction'),
('Policier');


-- ============================
-- EDITEURS
-- ============================
INSERT INTO editeur (nom, pays) VALUES
('Gallimard', 'France'),
('HarperCollins', 'USA');
-- e1 = 1, e2 = 2


-- ============================
-- COLLECTIONS
-- ============================
INSERT INTO collection (nom) VALUES
('Folio'),
('Pavillons');


-- ============================
-- AUTEURS
-- ============================
INSERT INTO auteur (nom, prenom, nationalite) VALUES
('Tolkien', 'John', 'Britannique'),
('Asimov', 'Isaac', 'Américaine'),
('Christie', 'Agatha', 'Britannique');


-- ============================
-- LIVRES
-- ============================
INSERT INTO livre (titre, resume, annee, auteur_id, editeur_id, collection_id, genre_id) VALUES
('Le Seigneur des Anneaux', 'Une aventure épique en Terre du Milieu.', 1954, 1, 1, 1, 1),
('Fondation', 'L''effondrement d''un empire galactique.', 1951, 2, 2, 2, 2),
('Le Crime de l''Orient-Express', 'Un meurtre mystérieux dans un train.', 1934, 3, 1, 1, 3);