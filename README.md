# TennisBoard

Ce projet simule le score d’un match de tennis en console. Il permet de saisir une séquence de points (A ou B) et d’afficher l’évolution du score selon les règles officielles (15, 30, 40, deuce, avantage, victoire).

## Lancer le projet

- Pour exécuter les tests :

    mvn test

- Pour utiliser l’application, lancez la classe `Main` ou adaptez-la selon vos besoins.

## Structure rapide
- `model` : joueurs, score, résultat
- `service` : logique de jeu
- `states` : gestion des états d’affichage
- `rules` : règles du tennis
- `test` : scénarios de tests JUnit
