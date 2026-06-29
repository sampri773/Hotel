# Classe `Hotel`

> **Package :** `com.hotel`  
> **Fichier :** `src/main/java/com/hotel/Hotel.java`  
> **Rôle :** Agrégat racine — point central de gestion de l'hôtel.  
> **Acteur principal :** Gérant de l'hôtel (la plupart des méthodes).

---

## Description

La classe `Hotel` représente l'établissement dans son ensemble. Elle centralise :
- les informations de l'hôtel (nom, adresse, contact, étoiles),
- la liste des chambres,
- la liste des clients enregistrés,
- la liste des réservations.

**En POO**, `Hotel` est le **coordinateur** : elle délègue aux autres objets (`Room`, `Client`, `Reservation`) mais c'est elle qui applique les règles métier globales.

---

## Attributs

| Attribut | Type | Description | Obligatoire | Exemple |
|----------|------|-------------|-------------|---------|
| `id` | `int` | Identifiant unique de l'hôtel | Oui | `1` |
| `name` | `String` | Nom commercial de l'hôtel | Oui | `"Hôtel du Parc"` |
| `phone` | `String` | Numéro de téléphone | Oui | `"01 23 45 67 89"` |
 | `mail` | `String` | Adresse e-mail de contact | Oui | `"contact@hotelduparc.fr"` |
| `address` | `String` | Adresse postale complète | Oui | `"12 rue de la Paix, Paris"` |
| `rate` | `Rate` | Classification par étoiles (1 à 5) | Oui | `Rate.FOUR` |
| `rooms` | `List<Room>` | Toutes les chambres de l'hôtel | Oui | liste mutable |
| `clients` | `List<Client>` | Clients enregistrés | Oui | liste mutable |
| `reservations` | `List<Reservation>` | Toutes les réservations | Oui | liste mutable |

### Validations sur les attributs (à implémenter dans le constructeur ou setters)

- `name` : non null, non vide, longueur ≥ 2 caractères.
- `phone` : non null, format valide (chiffres, espaces, `+` autorisés).
- `mail` : non null, contient `@`.
- `address` : non null, non vide.
- `rate` : non null.
- Les listes : initialisées vides dans le constructeur si null passé.

---

## Constructeurs

| Constructeur | Paramètres | Description |
|--------------|------------|-------------|
| Constructeur principal | `(int id, String name, String phone, String mail, String address, Rate rate)` | Crée un hôtel avec listes vides |
| Constructeur complet | Tous les attributs y compris les listes | Pour rechargement / tests |

---

## Méthodes — Côté GÉRANT

### Gestion des chambres

| Méthode | Signature | Description | Retour |
|---------|-----------|-------------|--------|
| `ajouterChambre` | `void ajouterChambre(Room room)` | Ajoute une chambre à l'hôtel. Vérifie que l'ID n'existe pas déjà. | void — lève exception si ID dupliqué |
| `supprimerChambre` | `void supprimerChambre(int roomId)` | Supprime une chambre. Refuse si une réservation active existe. | void |
| `getChambreParId` | `Room getChambreParId(int roomId)` | Retourne une chambre par son ID. | `Room` ou exception si introuvable |
| `getToutesLesChambres` | `List<Room> getToutesLesChambres()` | Liste toutes les chambres. | `List<Room>` |
| `mettreChambreEnMaintenance` | `void mettreChambreEnMaintenance(int roomId)` | Passe une chambre en statut `MAINTENANCE`. Refuse si occupée. | void |
| `retirerChambreDeMaintenance` | `void retirerChambreDeMaintenance(int roomId)` | Repasse une chambre en `AVAILABLE`. | void |
| `modifierPrixChambre` | `void modifierPrixChambre(int roomId, double nouveauPrix)` | Met à jour le prix par nuit d'une chambre. | void — refuse si prix ≤ 0 |

### Gestion des clients

| Méthode | Signature | Description | Retour |
|---------|-----------|-------------|--------|
| `enregistrerClient` | `Client enregistrerClient(String nom, String prenom, String email, String telephone)` | Crée et enregistre un nouveau client. Génère un ID auto-incrémenté. | `Client` créé |
| `supprimerClient` | `void supprimerClient(int clientId)` | Supprime un client. Refuse s'il a des réservations actives. | void |
| `getClientParId` | `Client getClientParId(int clientId)` | Retourne un client par ID. | `Client` |
| `getClientParEmail` | `Client getClientParEmail(String email)` | Recherche un client par e-mail. | `Client` ou null |
| `getTousLesClients` | `List<Client> getTousLesClients()` | Liste tous les clients. | `List<Client>` |

### Gestion des réservations (gérant)

| Méthode | Signature | Description | Retour |
|---------|-----------|-------------|--------|
| `creerReservation` | `Reservation creerReservation(int clientId, int roomId, LocalDate dateDebut, LocalDate dateFin, int guestCount)` | Crée une réservation pour un client. Vérifie disponibilité, capacité, dates. | `Reservation` |
| `annulerReservation` | `void annulerReservation(int reservationId)` | Annule une réservation (gérant peut annuler n'importe laquelle). | void |
| `confirmerReservation` | `void confirmerReservation(int reservationId)` | Passe le statut de `PENDING` à `CONFIRMED`. | void |
| `checkIn` | `void checkIn(int reservationId)` | Enregistre l'arrivée du client. Passe chambre en `OCCUPIED`. | void |
| `checkOut` | `void checkOut(int reservationId)` | Enregistre le départ. Passe chambre en `AVAILABLE`. Retourne facture. | `double` (montant total) |
| `getReservationParId` | `Reservation getReservationParId(int reservationId)` | Retourne une réservation par ID. | `Reservation` |
| `getToutesLesReservations` | `List<Reservation> getToutesLesReservations()` | Liste toutes les réservations. | `List<Reservation>` |
| `getReservationsActives` | `List<Reservation> getReservationsActives()` | Réservations CONFIRMED ou CHECKED_IN. | `List<Reservation>` |
| `getReservationsParClient` | `List<Reservation> getReservationsParClient(int clientId)` | Toutes les réservations d'un client. | `List<Reservation>` |

### Consultation et statistiques (gérant)

| Méthode | Signature | Description | Retour |
|---------|-----------|-------------|--------|
| `getChambresDisponibles` | `List<Room> getChambresDisponibles(LocalDate dateDebut, LocalDate dateFin)` | Chambres libres sur la période (pas de conflit, status ≠ MAINTENANCE). | `List<Room>` |
| `getTauxOccupation` | `double getTauxOccupation(LocalDate date)` | % de chambres occupées ou réservées à une date. | `double` entre 0.0 et 1.0 |
| `getChiffreAffaires` | `double getChiffreAffaires(LocalDate dateDebut, LocalDate dateFin)` | Somme des prix des réservations CHECKED_OUT sur la période. | `double` |
| `getNombreClients` | `int getNombreClients()` | Nombre total de clients enregistrés. | `int` |
| `getNombreChambres` | `int getNombreChambres()` | Nombre total de chambres. | `int` |

---

## Méthodes — Côté CLIENT (appelées via Hotel)

Le client interagit souvent via `Client`, mais `Hotel` expose aussi :

| Méthode | Signature | Description | Acteur |
|---------|-----------|-------------|--------|
| `rechercherChambresDisponibles` | `List<Room> rechercherChambresDisponibles(LocalDate debut, LocalDate fin, int nbPersonnes)` | Filtre chambres disponibles avec capacité suffisante. | Client |
| `consulterTarifChambre` | `double consulterTarifChambre(int roomId, LocalDate debut, LocalDate fin)` | Prix total estimé pour une chambre sur une période. | Client |

---

## Méthodes privées (helpers internes)

| Méthode | Signature | Description |
|---------|-----------|-------------|
| `genererIdClient` | `int genererIdClient()` | Prochain ID client disponible |
| `genererIdReservation` | `int genererIdReservation()` | Prochain ID réservation disponible |
| `genererIdChambre` | `int genererIdChambre()` | Prochain ID chambre disponible |
| `verifierDisponibilite` | `boolean verifierDisponibilite(int roomId, LocalDate debut, LocalDate fin)` | true si pas de chevauchement avec réservations actives |
| `verifierDatesValides` | `void verifierDatesValides(LocalDate debut, LocalDate fin)` | lève exception si fin ≤ debut ou dates passées |
| `trouverReservationsActivesPourChambre` | `List<Reservation> trouverReservationsActivesPourChambre(int roomId)` | réservations non annulées/check-out pour une chambre |

---

## Exceptions à définir (optionnel mais recommandé)

| Exception | Quand |
|-----------|-------|
| `ChambreNonDisponibleException` | Réservation sur chambre occupée |
| `ClientNonTrouveException` | ID client invalide |
| `ChambreNonTrouveeException` | ID chambre invalide |
| `ReservationNonTrouveeException` | ID réservation invalide |
| `DatesInvalidesException` | dateFin ≤ dateDebut |
| `CapaciteDepasseeException` | guestCount > capacité chambre |
| `OperationNonAutoriseeException` | check-in sur réservation annulée, etc. |

---

## Exemple d'utilisation (pseudo-code)

```java
Hotel hotel = new Hotel(1, "Hôtel du Parc", "0123456789",
    "contact@hotel.fr", "12 rue de la Paix", Rate.FOUR);

// Gérant ajoute une chambre
Room chambre101 = new Room(101, 89.99, RoomStatus.AVAILABLE, lits, options);
hotel.ajouterChambre(chambre101);

// Gérant enregistre un client
Client client = hotel.enregistrerClient("Dupont", "Jean", "jean@mail.fr", "0612345678");

// Client réserve (via Hotel ou Client.creerReservation qui appelle Hotel)
Reservation res = hotel.creerReservation(
    client.getId(), 101,
    LocalDate.of(2026, 7, 1),
    LocalDate.of(2026, 7, 5),
    2
);

// Gérant check-in le jour J
hotel.checkIn(res.getId());

// Gérant check-out à la fin
double facture = hotel.checkOut(res.getId());
```

---

## Dépendances

- `Room`, `Client`, `Reservation`, `Rate`
- `RoomStatus`, `ReservationStatus` (enums à créer)
- `java.time.LocalDate`
- `java.util.List`, `java.util.ArrayList`

---

## Assignation équipe

Voir [REPARTITION-EQUIPE.md](../git/REPARTITION-EQUIPE.md) — **Membre 1** (lead architecture).

## Branche Git suggérée

`feature/hotel-gestion-chambres-clients-reservations`
