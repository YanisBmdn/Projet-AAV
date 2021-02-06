# Projet AAV

## Description
<img src="SacADos.png" width:70%>
Ce projet avait pour objectif de développer plusieurs solutions au problème du <a href=https://fr.wikipedia.org/wiki/Probl%C3%A8me_du_sac_%C3%A0_dos>Sac à dos</a>.
Le principe du problème est le suivant : on dispose d'un sac avec une contenance maximale, et on souhaite le remplir avec des objets ayant un poids et une valeur. Nous avons donc à disposition 3 solutions, permettant de résoudre le problème du sac à dos plus ou moins optimalement.
Ce projet est avant tout à considérer dans une approche de complexité algorithmique.

## Solution Heuristique
Cette solution à pour but d'établir un rapport prix/poids pour chaque objet, et de placer dans le sac à dos les objets ayant le meilleure rapport.
A noter : on utilise un algorithme de tri rapide pour trier les objets selon leurs valeurs dans un soucis de complexité optimale.


## Solution Dynamique
Cette solution à pour but d'établir un tableau des solutions possibles en effectuant des appels récursifs à la méthode de résolution. La solution optimale se trouve dans la dernière case du tableau.


## Solution Séparation et évaluation
Cette solution à pour but permet de créer un arbre binaire de recherche pour trouver la solution optimale. On créé d'abord l'arbre en énonçant toutes les solutions possibles au problème, on parcours ensuite de manière préfixe l'arbre pour obtenir la meilleure solution.
