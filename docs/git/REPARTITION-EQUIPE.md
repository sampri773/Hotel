# Répartition de l'équipe — 4 personnes

> Chaque membre a une **responsabilité principale**, mais **tous** participent aux relectures de PR et aux décisions ([DECISIONS.md](../DECISIONS.md)).

---

## Vue d'ensemble

| Membre | Rôle | Classes principales | Branche principale |
|--------|------|---------------------|-------------------|
| **Membre 1** | Lead / Architecture | `Hotel`, `Rate`, `Main` | `feature/hotel-gestion-complete` |
| **Membre 2** | Chambres & équipements | `Room`, `Bed`, `BedTypes`, `RoomOption`, `RoomStatus` | `feature/room-bed-options-status` |
| **Membre 3** | Clients | `Client` | `feature/client-actions-reservation` |
| **Membre 4** | Réservations | `Reservation`, `ReservationStatus`, suppression `Booking` | `feature/reservation-cycle-vie-prix` |

---

## Membre 1 — Lead & Hotel

### Responsabilités

- Coordonner les décisions ([DECISIONS.md](../DECISIONS.md))
- Valider les PR des autres membres (relecteur principal)
- Implémenter la classe `Hotel` (agrégat racine)
- Enrichir l'enum `Rate`
- Corriger `Main.java` pour démonstration du système
- Corriger les erreurs de compilation globales

### Fiches à lire

- [Hotel.md](../classes/Hotel.md)
- [Rate.md](../classes/Rate.md)
- [ARCHITECTURE.md](../ARCHITECTURE.md)
- [WORKFLOW-GIT.md](./WORKFLOW-GIT.md)

### Tâches détaillées

| # | Tâche | Fichiers | Méthodes clés |
|---|-------|----------|---------------|
| 1.1 | Valider DECISIONS.md avec l'équipe | `docs/DECISIONS.md` | — |
| 1.2 | Implémenter attributs Hotel (déjà faits) + initialisation listes | `Hotel.java` | constructeur |
| 1.3 | Gestion chambres | `Hotel.java` | `ajouterChambre`, `supprimerChambre`, `getChambreParId`, `getChambresDisponibles` |
| 1.4 | Gestion clients | `Hotel.java` | `enregistrerClient`, `getClientParId`, `getTousLesClients` |
| 1.5 | Gestion réservations | `Hotel.java` | `creerReservation`, `annulerReservation`, `checkIn`, `checkOut` |
| 1.6 | Statistiques gérant | `Hotel.java` | `getTauxOccupation`, `getChiffreAffaires` |
| 1.7 | Enrichir Rate | `Rate.java` | `getEtoiles`, `getLabel` |
| 1.8 | Main démo | `Main.java` | scénario gérant + client |
| 1.9 | Tests Hotel | `HotelTest.java` | tests création, réservation, check-in/out |

### Ordre de travail

1. Attendre que M2, M3, M4 aient livré leurs classes de base (PR mergées).
2. Implémenter `Hotel` qui **utilise** leurs classes.
3. PR finale `feature/hotel-gestion-complete`.

### Relecteur pour ses PR

Membre 2 ou Membre 4

---

## Membre 2 — Chambres & équipements

### Responsabilités

- Refondre `Room` (remplacer `Client` par `RoomStatus`)
- Implémenter `Bed`, enrichir `BedTypes`
- Créer et implémenter `RoomOption`
- Créer l'enum `RoomStatus`

### Fiches à lire

- [Room.md](../classes/Room.md)
- [Bed.md](../classes/Bed.md)
- [BedTypes.md](../classes/BedTypes.md)
- [RoomOption.md](../classes/RoomOption.md)
- [RoomStatus.md](../classes/RoomStatus.md)

### Tâches détaillées

| # | Tâche | Fichiers | Méthodes clés |
|---|-------|----------|---------------|
| 2.1 | Créer RoomStatus | `RoomStatus.java` | enum + `getLabel`, `estReservable` |
| 2.2 | Enrichir BedTypes | `BedTypes.java` | `getLabel`, `getCapacite` |
| 2.3 | Implémenter Bed | `Bed.java` | `getCapacite`, `getLabel` |
| 2.4 | Implémenter RoomOption | `RoomOption.java` | `calculerCout`, `getResume` |
| 2.5 | Refondre Room | `Room.java` | `getCapacite`, `estDisponible`, `calculerPrixEstime`, `afficherResume` |
| 2.6 | Tests | `RoomTest.java`, `BedTest.java` | capacité, statuts |

### Ordre de travail

1. PR 1 : enums `RoomStatus` + `BedTypes` enrichi
2. PR 2 : `Bed`, `RoomOption`, `Room` refonte

### Relecteur pour ses PR

Membre 1

---

## Membre 3 — Clients

### Responsabilités

- Implémenter la classe `Client` (actuellement vide)
- Méthodes côté client (consultation, réservation, annulation)
- Tests unitaires Client

### Fiches à lire

- [Client.md](../classes/Client.md)
- [ARCHITECTURE.md](../ARCHITECTURE.md) — flux client

### Tâches détaillées

| # | Tâche | Fichiers | Méthodes clés |
|---|-------|----------|---------------|
| 3.1 | Attributs Client | `Client.java` | id, nom, prenom, email, telephone |
| 3.2 | Constructeurs + getters/setters | `Client.java` | validation email/téléphone |
| 3.3 | Consultation | `Client.java` | `rechercherChambresDisponibles`, `consulterPrixSejour`, `getNomComplet` |
| 3.4 | Réservations client | `Client.java` | `reserver`, `annulerReservation`, `getMesReservations` |
| 3.5 | Profil | `Client.java` | `mettreAJourTelephone`, `mettreAJourEmail` |
| 3.6 | Tests | `ClientTest.java` | reserver, annuler, getMesReservations |

### Ordre de travail

1. Peut commencer **en parallèle** de M2 (Client ne dépend pas de RoomStatus final pour les attributs de base).
2. Pour `reserver()` : attendre que `Hotel.creerReservation` existe (M1) **ou** mocker Hotel dans les tests.

### Relecteur pour ses PR

Membre 4

---

## Membre 4 — Réservations

### Responsabilités

- Refondre `Reservation` (dates LocalDate, room, guestCount, status)
- Créer `ReservationStatus`
- Supprimer `Booking.java` après fusion
- Corriger l'erreur de syntaxe dans `Reservation.java` (accolade en trop)

### Fiches à lire

- [Reservation.md](../classes/Reservation.md)
- [ReservationStatus.md](../classes/ReservationStatus.md)
- [DECISIONS.md](../DECISIONS.md)

### Tâches détaillées

| # | Tâche | Fichiers | Méthodes clés |
|---|-------|----------|---------------|
| 4.1 | Créer ReservationStatus | `ReservationStatus.java` | enum + helpers |
| 4.2 | Fix syntaxe Reservation | `Reservation.java` | supprimer `}` en trop |
| 4.3 | Refondre attributs | `Reservation.java` | room, guestCount, status, LocalDate |
| 4.4 | Cycle de vie | `Reservation.java` | `confirmer`, `effectuerCheckIn`, `effectuerCheckOut`, `annuler` |
| 4.5 | Calcul prix | `Reservation.java` | `calculerPrixTotal`, `getNombreNuits`, `chevauchePeriode` |
| 4.6 | Supprimer Booking | supprimer `Booking.java` | PR chore séparée |
| 4.7 | Tests | `ReservationTest.java` | prix, chevauchement, annulation |

### Ordre de travail

1. PR 1 : `ReservationStatus` + fix syntaxe + refonte Reservation
2. PR 2 (après merge PR1) : `chore/supprimer-booking`

### Relecteur pour ses PR

Membre 3

---

## Planning suggéré (4 semaines)

### Semaine 1 — Fondations

| Jour | Membre 1 | Membre 2 | Membre 3 | Membre 4 |
|------|----------|----------|----------|----------|
| Lun | Valider DECISIONS | RoomStatus + BedTypes | Attributs Client | ReservationStatus |
| Mar | Review PRs | Bed + RoomOption | Constructeurs Client | Fix Reservation syntaxe |
| Mer | Review PRs | Room refonte | Méthodes consultation | Attributs Reservation |
| Jeu | Préparer Hotel | Tests Room/Bed | Méthodes reserver | chevauchePeriode, prix |
| Ven | Merge enums | PR room-bed-options | PR client-actions | PR reservation |

### Semaine 2 — Intégration Hotel

| Jour | Membre 1 | Membre 2 | Membre 3 | Membre 4 |
|------|----------|----------|----------|----------|
| Lun-Ven | Hotel complet | Support / review | Support / review | Supprimer Booking + tests |

### Semaine 3 — Main & tests intégration

| Tous | Main démo, tests bout en bout, corrections bugs |

### Semaine 4 — Finition & soutenance

| Tous | Documentation finale, préparation démo, relecture globale |

---

## Matrice de relecture PR

| Auteur PR | Relecteur 1 | Relecteur 2 (backup) |
|-----------|-------------|----------------------|
| Membre 1 | Membre 2 | Membre 4 |
| Membre 2 | Membre 1 | Membre 3 |
| Membre 3 | Membre 4 | Membre 1 |
| Membre 4 | Membre 3 | Membre 2 |

**Règle :** ne pas merger sa propre PR.

---

## Communication recommandée

| Canal | Usage |
|-------|-------|
| **GitHub Issues** | Une issue par tâche (ex. "Implémenter Client.reserver") |
| **GitHub PR comments** | Relectures, questions techniques |
| **Groupe équipe** (Discord/WhatsApp) | Coordination rapide, annonce de merge |
| **Réunion hebdo** | 30 min : avancement, blocages, DECISIONS |

---

## Template GitHub Issue (copier-coller)

```markdown
## Tâche
Implémenter [Classe].[méthode]

## Responsable
Membre X

## Fiche de référence
docs/classes/[Classe].md

## Critères d'acceptation
- [ ] Méthode implémentée selon la fiche
- [ ] Validations en place
- [ ] Test unitaire ajouté
- [ ] mvn compile OK
- [ ] PR créée et approuvée

## Branche
feature/...
```

---

## Dépendances entre membres

```
Membre 2 (Room, enums)  ──┐
Membre 3 (Client)       ──┼──> Membre 1 (Hotel assemble tout)
Membre 4 (Reservation)  ──┘
```

**Membre 1** attend les merges de 2, 3 et 4 avant d'implémenter les méthodes Hotel qui manipulent Room, Client et Reservation.

**Membre 3 et 4** peuvent travailler en parallèle (peu de dépendances directes).

**Membre 2** doit livrer `RoomStatus` et `Room.getCapacite()` avant que M4 finalise les validations `guestCount`.
