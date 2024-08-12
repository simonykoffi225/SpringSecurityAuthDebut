-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 12 août 2024 à 11:38
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bibliotheque`
--

-- --------------------------------------------------------

--
-- Structure de la table `dbuser`
--

CREATE TABLE `dbuser` (
  `id` int(11) NOT NULL,
  `username` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `role` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `dbuser`
--

INSERT INTO `dbuser` (`id`, `username`, `password`, `role`) VALUES
(1, 'dbuser', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.', 'USER'),
(2, 'dbadmin', '$2y$10$kp1V7UYDEWn17WSK16UcmOnFd1mPFVF6UkLrOOCGtf24HOYt8p1iC', 'ADMIN');

-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

CREATE TABLE `livre` (
  `id` bigint(20) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `auteur` varchar(255) NOT NULL,
  `resume` text DEFAULT NULL,
  `quantite` int(11) NOT NULL,
  `quantite_initiale` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `livre`
--

INSERT INTO `livre` (`id`, `titre`, `auteur`, `resume`, `quantite`, `quantite_initiale`) VALUES
(2, '1984', 'George Orwell', 'Un roman dystopique décrivant une société totalitaire sous surveillance constante, où la pensée indépendante est persécutée et l\'histoire est continuellement réécrite par le Parti.', 5, 8),
(3, 'Pride and Prejudice', 'Jane Austen', 'Une comédie romantique qui explore les thèmes du mariage, de la classe sociale, et des préjugés à travers l\'histoire de l\'indépendante Elizabeth Bennet et de l\'orgueilleux Mr. Darcy.', 6, 6),
(4, 'The Great Gatsby', 'F. Scott Fitzgerald', 'Une critique de la société américaine des années 1920, centrée sur l\'énigmatique Jay Gatsby et ses efforts pour retrouver son ancienne amante, Daisy Buchanan.', 4, 4),
(5, 'To Kill a Mockingbird', 'Harper Lee', 'Un regard poignant sur le racisme et l\'injustice dans le sud des États-Unis à travers les yeux d\'une jeune fille, Scout Finch, dont le père, Atticus Finch, défend un homme noir accusé à tort.', 7, 7),
(6, 'Moby-Dick', 'Herman Melville', 'L\'épopée de la chasse obsessionnelle du capitaine Ahab après la baleine blanche, Moby Dick, et les conséquences tragiques de cette quête.', 3, 3),
(7, 'The Catcher in the Rye', 'J.D. Salinger', 'L\'histoire de Holden Caulfield, un adolescent désabusé qui se promène dans New York après avoir été expulsé de son école, en quête de sens et de réconfort.', 5, 5),
(8, 'Brave New World', 'Aldous Huxley', 'Une dystopie où la société est organisée autour du confort et du bonheur artificiels, et où l\'individualité est supprimée pour maintenir l\'ordre et la stabilité.', 4, 4),
(9, 'The Hobbit', 'J.R.R. Tolkien', 'Une aventure fantastique où Bilbo Baggins, un hobbit, se lance dans une quête épique pour aider un groupe de nains à récupérer leur royaume volé par un dragon.', 10, 10),
(10, 'The Da Vinci Code', 'Dan Brown', 'Un thriller où le professeur Robert Langdon enquête sur un meurtre au Louvre et découvre une conspiration religieuse cachée depuis des siècles.', 6, 6),
(14, 'simon', 'George Orwell', 'Un roman dystopique décrivant une société totalitaire sous surveillance constante, où la pensée indépendante est persécutée et l\'histoire est continuellement réécrite par le Parti.', 8, 8);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `nom`, `password`, `role`) VALUES
(1, 'jojo', '$2a$10$v.1rc3H944bmicQARkSk5eN/NhDcyO06GP1yq8F1wJ2eT.dF0vuwG', 'USER'),
(2, 'paul', '$2a$10$LtEmJ32SsrsdIDb1Ab05XOo6xpvqh5JnfHLqh7/o8ng/S4vcYcgM.', 'USER');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `dbuser`
--
ALTER TABLE `dbuser`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `livre`
--
ALTER TABLE `livre`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `dbuser`
--
ALTER TABLE `dbuser`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `livre`
--
ALTER TABLE `livre`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
