-- Sélection de la base
USE biblio;

-- ============================
-- CREATION DES TABLES
-- ============================

CREATE TABLE genre (
  id INT AUTO_INCREMENT PRIMARY KEY,
  libelle VARCHAR(255) NOT NULL
);

CREATE TABLE editeur (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(255) NOT NULL,
  pays VARCHAR(255)
);

CREATE TABLE collection (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(255) NOT NULL
);

CREATE TABLE auteur (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(255) NOT NULL,
  prenom VARCHAR(255),
  nationalite VARCHAR(255)
);

CREATE TABLE livre (
  id INT AUTO_INCREMENT PRIMARY KEY,
  titre VARCHAR(255) NOT NULL,
  resume TEXT,
  annee INT,
  auteur_id INT,
  editeur_id INT,
  collection_id INT,
  genre_id INT,
  FOREIGN KEY (auteur_id) REFERENCES auteur(id),
  FOREIGN KEY (editeur_id) REFERENCES editeur(id),
  FOREIGN KEY (collection_id) REFERENCES collection(id),
  FOREIGN KEY (genre_id) REFERENCES genre(id)
);

-- ============================
-- INSERT GENRES
-- ============================
INSERT INTO genre (libelle) VALUES 
('Fantasy'),
('Science-Fiction'),
('Policier');

-- ============================
-- INSERT EDITEURS
-- ============================
INSERT INTO editeur (nom, pays) VALUES
('Gallimard', 'France'),
('HarperCollins', 'USA');

-- ============================
-- INSERT COLLECTIONS
-- ============================
INSERT INTO collection (nom) VALUES
('Folio'),
('Pavillons');

-- ============================
-- INSERT AUTEURS
-- ============================
INSERT INTO auteur (nom, prenom, nationalite) VALUES
('Tolkien', 'John', 'Britannique'),
('Asimov', 'Isaac', 'Américaine'),
('Christie', 'Agatha', 'Britannique');

-- ============================
-- INSERT LIVRES
-- ============================
INSERT INTO livre (titre, resume, annee, auteur_id, editeur_id, collection_id, genre_id) VALUES
('Le Seigneur des Anneaux', 'Une aventure épique en Terre du Milieu.', 1954, 1, 1, 1, 1),
('Fondation', 'L''effondrement d''un empire galactique.', 1951, 2, 2, 2, 2),
('Le Crime de l''Orient-Express', 'Un meurtre mystérieux dans un train.', 1934, 3, 1, 1, 3);
