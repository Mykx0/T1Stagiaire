---
config:
  layout: elk
---
classDiagram
direction BT
class Utilisateur {
    -Long id
    -String nom
    -string prenom
    -String courriel
    -String motPasse
}

    class Etudiant {
        -Enum discipline
        -Int matricule
    }

    class Employeur {
        -Compagnie compagnie
    }

    class Compagnie {
        -Long id
        -String nomCompagnie
        -String ville
    }

    class Professeur {
        -Enum Discipline
    }

    class GestionaireStage {
    }

    class discipline {
    }

    <<Interfce>> Utilisateur
    <<Enumeration>> discipline

    Etudiant --> Utilisateur : extends
    Professeur --> Utilisateur : extends
    Employeur --> Utilisateur : extends
    GestionaireStage --> Utilisateur : extends
    Etudiant --> discipline
    Professeur --> discipline
    Employeur "*" --> "1" Compagnie