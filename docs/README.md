# Documentation du projet Hôtel — POO

> **Objectif :** Organiser le travail de l'équipe (4 personnes) autour d'un système de gestion hôtelière en programmation orientée objet (Java).

---

## Contenu de cette documentation

| Fichier | Description |
|---------|-------------|
| [ARCHITECTURE.md](./ARCHITECTURE.md) | Vue d'ensemble, relations entre classes, diagrammes |
| [DECISIONS.md](./DECISIONS.md) | Choix de conception à valider en équipe (Booking vs Reservation, dates, etc.) |
| [classes/Hotel.md](./classes/Hotel.md) | Classe `Hotel` — cœur du système côté gérant |
| [classes/Room.md](./classes/Room.md) | Classe `Room` — chambres |
| [classes/Bed.md](./classes/Bed.md) | Classe `Bed` — lits dans une chambre |
| [classes/Client.md](./classes/Client.md) | Classe `Client` — clients de l'hôtel |
| [classes/Reservation.md](./classes/Reservation.md) | Classe `Reservation` — réservations |
| [classes/RoomOption.md](./classes/RoomOption.md) | Classe `RoomOption` — options/suppléments |
| [classes/BedTypes.md](./classes/BedTypes.md) | Énumération `BedTypes` |
| [classes/Rate.md](./classes/Rate.md) | Énumération `Rate` — étoiles de l'hôtel |
| [git/WORKFLOW-GIT.md](./git/WORKFLOW-GIT.md) | Branches, commits, push, pull, pull requests |
| [git/REPARTITION-EQUIPE.md](./git/REPARTITION-EQUIPE.md) | Répartition des tâches entre les 4 membres |

---

## État actuel du code (`src/main/java/com/hotel/`)

| Classe | État | Fichier doc |
|--------|------|-------------|
| `Hotel` | Attributs + Lombok | [Hotel.md](./classes/Hotel.md) |
| `Room` | Attributs + Lombok | [Room.md](./classes/Room.md) |
| `Bed` | Attributs + Lombok | [Bed.md](./classes/Bed.md) |
| `Client` | Classe vide | [Client.md](./classes/Client.md) |
| `Reservation` | Attributs + Lombok (à corriger : accolade en trop) | [Reservation.md](./classes/Reservation.md) |
| `Booking` | Attributs sans méthodes — **à fusionner avec Reservation** | Voir [DECISIONS.md](./DECISIONS.md) |
| `RoomOption` | Classe vide | [RoomOption.md](./classes/RoomOption.md) |
| `BedTypes` | Enum complète | [BedTypes.md](./classes/BedTypes.md) |
| `Rate` | Enum complète | [Rate.md](./classes/Rate.md) |

---

## Rôles métier à garder en tête

Chaque méthode documentée est pensée depuis **deux points de vue** :

1. **Le gérant de l'hôtel** — gère chambres, clients, réservations, tarifs, check-in/check-out.
2. **Le client** — consulte, réserve, modifie ou annule ses séjours.

Les méthodes « côté gérant » vivent principalement dans `Hotel`.  
Les méthodes « côté client » vivent principalement dans `Client` et `Reservation`.

---

## Ordre de lecture recommandé

1. Lire [DECISIONS.md](./DECISIONS.md) et valider les choix en équipe.
2. Lire [ARCHITECTURE.md](./ARCHITECTURE.md) pour comprendre les liens entre classes.
3. Chaque membre lit sa/les fiche(s) de classe assignée(s) dans [REPARTITION-EQUIPE.md](./git/REPARTITION-EQUIPE.md).
4. Suivre [WORKFLOW-GIT.md](./git/WORKFLOW-GIT.md) avant toute modification de code.

---

## Package et conventions

- **Package :** `com.hotel`
- **Emplacement des classes :** `src/main/java/com/hotel/`
- **Tests :** `src/test/java/com/hotel/` (à créer)
- **Outil :** Lombok (`@Getter`, `@AllArgsConstructor`, `@Setter` si besoin)
- **Dates :** utiliser `java.time.LocalDate` partout (voir [DECISIONS.md](./DECISIONS.md))
