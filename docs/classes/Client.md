# Classe `Client`

> **Package :** `com.hotel`  
> **Fichier :** `src/main/java/com/hotel/Client.java`  
> **Rôle :** Représenter un client de l'hôtel et ses actions côté « utilisateur ».  
> **Acteur principal :** Client (avec délégation vers `Hotel` pour la logique centrale).

---

## Description

Le `Client` est une personne qui :
- s'enregistre à l'hôtel,
- consulte les chambres disponibles,
- effectue, modifie ou annule des réservations,
- consulte l'historique de ses séjours.

**Important en POO :** `Client` ne modifie pas directement les listes de `Hotel`. Il **demande** à `Hotel` d'exécuter les opérations (composition : `Client` reçoit une référence à `Hotel` ou les appels passent par `Hotel`).

---

## Attributs

| Attribut | Type | Description | Obligatoire | Exemple |
|----------|------|-------------|-------------|---------|
| `id` | `int` | Identifiant unique | Oui | `42` |
| `nom` | `String` | Nom de famille | Oui | `"Dupont"` |
| `prenom` | `String` | Prénom | Oui | `"Jean"` |
| `email` | `String` | E-mail (identifiant de contact, unique) | Oui | `"jean.dupont@mail.fr"` |
| `telephone` | `String` | Numéro de téléphone | Oui | `"0612345678"` |

### Validations

- `nom`, `prenom` : non null, non vides, longueur ≥ 2
- `email` : non null, contient `@`, format basique valide
- `telephone` : non null, chiffres uniquement (10 chiffres FR) ou format international

---

## Constructeurs

| Constructeur | Paramètres | Description |
|--------------|------------|-------------|
| Principal | `(int id, String nom, String prenom, String email, String telephone)` | Client complet |
| Sans ID | `(String nom, String prenom, String email, String telephone)` | ID assigné par Hotel à l'enregistrement |

---

## Méthodes — Côté CLIENT (actions du client)

### Consultation

| Méthode | Signature | Description | Retour |
|---------|-----------|-------------|--------|
| `rechercherChambresDisponibles` | `List<Room> rechercherChambresDisponibles(Hotel hotel, LocalDate debut, LocalDate fin, int nbPersonnes)` | Délègue à `hotel.rechercherChambresDisponibles(...)` | `List<Room>` |
| `consulterPrixSejour` | `double consulterPrixSejour(Room room, LocalDate debut, LocalDate fin, List<RoomOption> optionsChoisies)` | Estime le coût total d'un séjour | `double` |
| `getNomComplet` | `String getNomComplet()` | Retourne "Prénom Nom" | `String` |

### Réservations

| Méthode | Signature | Description | Retour |
|---------|-----------|-------------|--------|
| `reserver` | `Reservation reserver(Hotel hotel, int roomId, LocalDate debut, LocalDate fin, int guestCount)` | Crée une réservation via l'hôtel | `Reservation` |
| `annulerReservation` | `void annulerReservation(Hotel hotel, int reservationId)` | Annule SA réservation (vérifie ownership) | void |
| `modifierDatesReservation` | `Reservation modifierDatesReservation(Hotel hotel, int reservationId, LocalDate nouveauDebut, LocalDate nouvelleFin)` | Change les dates si chambre toujours dispo | `Reservation` |
| `getMesReservations` | `List<Reservation> getMesReservations(Hotel hotel)` | Toutes ses réservations | `List<Reservation>` |
| `getReservationEnCours` | `Reservation getReservationEnCours(Hotel hotel)` | Réservation CHECKED_IN actuelle, ou null | `Reservation` ou null |
| `getProchaineReservation` | `Reservation getProchaineReservation(Hotel hotel)` | Prochaine CONFIRMED à venir | `Reservation` ou null |

### Profil

| Méthode | Signature | Description | Retour |
|---------|-----------|-------------|--------|
| `mettreAJourTelephone` | `void mettreAJourTelephone(String nouveauTelephone)` | Met à jour le téléphone | void |
| `mettreAJourEmail` | `void mettreAJourEmail(String nouvelEmail)` | Met à jour l'e-mail | void |

---

## Méthodes — Côté GÉRANT (via Hotel, info Client)

Le gérant utilise surtout `Hotel.enregistrerClient()` et `Hotel.getClientParId()`.  
Sur `Client` directement, le gérant peut consulter :

| Méthode | Signature | Description | Retour |
|---------|-----------|-------------|--------|
| `getHistoriqueReservations` | `List<Reservation> getHistoriqueReservations(Hotel hotel)` | Toutes réservations passées et futures | `List<Reservation>` |
| `estClientFidele` | `boolean estClientFidele(Hotel hotel)` | true si ≥ 3 séjours CHECKED_OUT | boolean |
| `getNombreSejours` | `int getNombreSejours(Hotel hotel)` | Compte des CHECKED_OUT | int |

---

## Méthodes utilitaires (privées)

| Méthode | Signature | Description |
|---------|-----------|-------------|
| `verifierOwnership` | `boolean verifierOwnership(Hotel hotel, int reservationId)` | true si la réservation appartient à ce client |
| `validerEmail` | `void validerEmail(String email)` | lève exception si format invalide |

---

## Getters / Setters

**Getters (obligatoires) :**
- `getId()`, `getNom()`, `getPrenom()`, `getEmail()`, `getTelephone()`

**Setters (avec validation) :**
- `setNom()`, `setPrenom()`, `setEmail()`, `setTelephone()`

---

## Exemple d'utilisation — Parcours client complet

```java
// 1. Enregistrement (souvent fait par le gérant ou à l'inscription)
Client client = hotel.enregistrerClient("Dupont", "Marie", "marie@mail.fr", "0698765432");

// 2. Client cherche une chambre pour 2 personnes
List<Room> dispo = client.rechercherChambresDisponibles(
    hotel,
    LocalDate.of(2026, 8, 1),
    LocalDate.of(2026, 8, 5),
    2
);

// 3. Client consulte le prix
double prix = client.consulterPrixSejour(dispo.get(0), 
    LocalDate.of(2026, 8, 1), LocalDate.of(2026, 8, 5), List.of());

// 4. Client réserve
Reservation res = client.reserver(hotel, dispo.get(0).getId(),
    LocalDate.of(2026, 8, 1), LocalDate.of(2026, 8, 5), 2);

// 5. Client consulte ses réservations
List<Reservation> mesRes = client.getMesReservations(hotel);

// 6. Client annule si besoin
client.annulerReservation(hotel, res.getId());
```

---

## Relations

- **Enregistré dans :** `Hotel.clients`
- **Lié à :** `Reservation.client` (plusieurs réservations possibles)

---

## Assignation équipe

**Membre 3** — avec tests `ClientTest`

## Branche Git suggérée

`feature/client-actions-reservation`
