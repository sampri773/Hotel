# Classe `Reservation`

> **Package :** `com.hotel`  
> **Fichier :** `src/main/java/com/hotel/Reservation.java`  
> **Rôle :** Représenter une réservation de chambre pour un séjour donné.  
> **Acteurs :** Gérant + Client.

---

## Description

Une `Reservation` lie un **Client**, une **Room** et une **période** (date début / date fin). Elle possède un **statut** qui évolue dans le cycle de vie du séjour.

**Note :** Cette classe remplace `Booking.java` (voir [DECISIONS.md](../DECISIONS.md)). Tous les attributs utiles de `Booking` sont intégrés ici.

---

## Attributs

| Attribut | Type | Description | Obligatoire | Exemple |
|----------|------|-------------|-------------|---------|
| `id` | `int` | Identifiant unique de la réservation | Oui | `1001` |
| `client` | `Client` | Client qui réserve | Oui | ref Client |
| `room` | `Room` | Chambre réservée | Oui | ref Room |
| `dateDebut` | `LocalDate` | Date d'arrivée (check-in prévu) | Oui | `2026-07-01` |
| `dateFin` | `LocalDate` | Date de départ (check-out prévu) | Oui | `2026-07-05` |
| `guestCount` | `int` | Nombre de personnes | Oui | `2` |
| `status` | `ReservationStatus` | État de la réservation | Oui | `CONFIRMED` |
| `optionsChoisies` | `List<RoomOption>` | Options sélectionnées par le client | Non | petit-déj, parking |

### Changements par rapport au code actuel

| Avant | Après |
|-------|-------|
| `Date dateDebut/dateFin` | `LocalDate dateDebut/dateFin` |
| Pas de `room` | Ajouter `Room room` |
| Pas de `guestCount` | Ajouter `int guestCount` |
| Pas de `status` | Ajouter `ReservationStatus status` |

### Validations

- `dateFin` > `dateDebut` (au moins 1 nuit)
- `dateDebut` ≥ aujourd'hui (à la création)
- `guestCount` > 0 et ≤ `room.getCapacite()`
- `client`, `room`, `status` non null

---

## Constructeurs

| Constructeur | Paramètres | Description |
|--------------|------------|-------------|
| Création | `(int id, Client client, Room room, LocalDate dateDebut, LocalDate dateFin, int guestCount)` | status = CONFIRMED par défaut |
| Complet | Tous attributs y compris status et options | Pour rechargement |

---

## Cycle de vie (ReservationStatus)

```
PENDING → CONFIRMED → CHECKED_IN → CHECKED_OUT
   ↓           ↓
CANCELLED   CANCELLED
```

| Transition | Qui | Condition |
|------------|-----|-----------|
| → CONFIRMED | Gérant | Depuis PENDING |
| → CHECKED_IN | Gérant | Date ≥ dateDebut, status CONFIRMED |
| → CHECKED_OUT | Gérant | status CHECKED_IN |
| → CANCELLED | Client ou Gérant | status PENDING ou CONFIRMED uniquement |

---

## Méthodes — Côté GÉRANT

| Méthode | Signature | Description | Retour |
|---------|-----------|-------------|--------|
| `confirmer` | `void confirmer()` | PENDING → CONFIRMED | void |
| `effectuerCheckIn` | `void effectuerCheckIn()` | CONFIRMED → CHECKED_IN | void |
| `effectuerCheckOut` | `void effectuerCheckOut()` | CHECKED_IN → CHECKED_OUT | void |
| `annuler` | `void annuler()` | → CANCELLED (si autorisé) | void |
| `modifierDates` | `void modifierDates(LocalDate nouveauDebut, LocalDate nouvelleFin)` | Change les dates si autorisé | void |
| `changerChambre` | `void changerChambre(Room nouvelleRoom)` | Change la chambre (si dispo) | void |
| `ajouterOption` | `void ajouterOption(RoomOption option)` | Ajoute une option à la facture | void |
| `retirerOption` | `void retirerOption(int optionId)` | Retire une option | void |
| `getNombreNuits` | `long getNombreNuits()` | Durée du séjour en nuits | long |
| `calculerPrixTotal` | `double calculerPrixTotal()` | (prix chambre × nuits) + options | double |
| `estActive` | `boolean estActive()` | true si CONFIRMED ou CHECKED_IN | boolean |
| `estTerminee` | `boolean estTerminee()` | true si CHECKED_OUT ou CANCELLED | boolean |
| `peutEtreAnnulee` | `boolean peutEtreAnnulee()` | true si PENDING ou CONFIRMED | boolean |
| `chevauchePeriode` | `boolean chevauchePeriode(LocalDate debut, LocalDate fin)` | Utilisé pour vérifier conflits | boolean |

---

## Méthodes — Côté CLIENT

| Méthode | Signature | Description | Retour |
|---------|-----------|-------------|--------|
| `getResume` | `String getResume()` | Texte : "Ch. 101 du 01/07 au 05/07 — Confirmée" | String |
| `getPrixEstime` | `double getPrixEstime()` | Alias de calculerPrixTotal pour affichage | double |
| `getJoursRestants` | `long getJoursRestants()` | Jours avant dateDebut (si future) | long |
| `demanderAnnulation` | `void demanderAnnulation(Client demandeur)` | Vérifie que demandeur == client puis annule | void |

---

## Méthode clé : `calculerPrixTotal()`

```
prixTotal = (room.getPrice() × getNombreNuits()) + Σ(option.getSupplementPrice() × getNombreNuits())
```

**Note :** Si une option est facturée une seule fois (ex. frais de ménage), ajouter un attribut `boolean parNuit` dans `RoomOption`.

---

## Méthode clé : `chevauchePeriode()`

Deux périodes se chevauchent si :
```
NOT (finA <= debutB OR finB <= debutA)
```

Utilisée par `Hotel.verifierDisponibilite()` pour éviter double réservation.

---

## Getters

- `getId()`, `getClient()`, `getRoom()`, `getDateDebut()`, `getDateFin()`
- `getGuestCount()`, `getStatus()`, `getOptionsChoisies()`

---

## Exemple d'utilisation

```java
Reservation res = new Reservation(
    1001, client, chambre101,
    LocalDate.of(2026, 7, 1),
    LocalDate.of(2026, 7, 5),
    2
);

res.getNombreNuits();      // 4
res.calculerPrixTotal();   // 89.99 × 4 = 359.96 (+ options)

// Gérant le jour J
res.effectuerCheckIn();    // status = CHECKED_IN

// Gérant au départ
res.effectuerCheckOut();   // status = CHECKED_OUT
double facture = res.calculerPrixTotal();
```

---

## Relations

- **Contenue dans :** `Hotel.reservations`
- **Référence :** `Client`, `Room`, `List<RoomOption>`

---

## Assignation équipe

**Membre 4** — avec `ReservationStatus`, suppression de `Booking`

## Branche Git suggérée

`feature/reservation-cycle-vie-prix`

## PR de nettoyage associée

`chore/supprimer-booking-fusion-reservation`
