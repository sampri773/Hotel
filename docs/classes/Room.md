# Classe `Room`

> **Package :** `com.hotel`  
> **Fichier :** `src/main/java/com/hotel/Room.java`  
> **Rôle :** Représenter une chambre d'hôtel avec ses lits, options et tarif.  
> **Acteurs :** Gérant (gestion) + Client (consultation).

---

## Description

Une `Room` est une unité locative de l'hôtel. Elle possède :
- un identifiant et un prix par nuit,
- un ou plusieurs lits (`Bed`),
- des options payantes (`RoomOption`),
- un statut de disponibilité (`RoomStatus`).

**Changement par rapport au code actuel :** remplacer l'attribut `Client client` par `RoomStatus status` (voir [DECISIONS.md](../DECISIONS.md)).

---

## Attributs

| Attribut | Type | Description | Obligatoire | Exemple |
|----------|------|-------------|-------------|---------|
| `id` | `int` | Numéro de chambre (unique dans l'hôtel) | Oui | `101` |
| `price` | `double` | Prix par nuit en euros | Oui | `89.99` |
| `status` | `RoomStatus` | État actuel de la chambre | Oui | `RoomStatus.AVAILABLE` |
| `beds` | `List<Bed>` | Lits présents dans la chambre | Oui | au moins 1 lit |
| `options` | `List<RoomOption>` | Options disponibles pour cette chambre | Non | peut être vide |

### Attribut supprimé (décision équipe)

| Ancien attribut | Raison de suppression |
|-----------------|----------------------|
| `Client client` | L'occupant se déduit de la `Reservation` en cours (CHECKED_IN) |

### Validations

- `id` > 0
- `price` > 0
- `beds` : au moins un lit
- `status` : non null

---

## Constructeurs

| Constructeur | Paramètres | Description |
|--------------|------------|-------------|
| Principal | `(int id, double price, List<Bed> beds)` | status = AVAILABLE, options = liste vide |
| Complet | `(int id, double price, RoomStatus status, List<Bed> beds, List<RoomOption> options)` | Tous les attributs |

---

## Méthodes — Côté GÉRANT

| Méthode | Signature | Description | Retour |
|---------|-----------|-------------|--------|
| `ajouterLit` | `void ajouterLit(Bed bed)` | Ajoute un lit à la chambre | void |
| `supprimerLit` | `void supprimerLit(int bedId)` | Retire un lit (refuse si dernier lit) | void |
| `ajouterOption` | `void ajouterOption(RoomOption option)` | Associe une option à la chambre | void |
| `supprimerOption` | `void supprimerOption(int optionId)` | Retire une option | void |
| `setPrice` | `void setPrice(double price)` | Modifie le tarif (refuse si ≤ 0) | void |
| `setStatus` | `void setStatus(RoomStatus status)` | Change le statut manuellement | void |
| `mettreEnMaintenance` | `void mettreEnMaintenance()` | status → MAINTENANCE | void |
| `liberer` | `void liberer()` | status → AVAILABLE (après check-out ou annulation) | void |
| `marquerOccupee` | `void marquerOccupee()` | status → OCCUPIED (check-in) | void |
| `marquerReservee` | `void marquerReservee()` | status → RESERVED (nouvelle réservation) | void |
| `estDisponible` | `boolean estDisponible()` | true si status == AVAILABLE | boolean |
| `estEnMaintenance` | `boolean estEnMaintenance()` | true si status == MAINTENANCE | boolean |

---

## Méthodes — Côté CLIENT

| Méthode | Signature | Description | Retour |
|---------|-----------|-------------|--------|
| `getCapacite` | `int getCapacite()` | Nombre max de personnes (somme places des lits) | int |
| `getDescriptionLits` | `String getDescriptionLits()` | Texte lisible : "1 lit double, 1 lit simple" | String |
| `getPrixParNuit` | `double getPrixParNuit()` | Retourne le prix de base | double |
| `calculerPrixEstime` | `double calculerPrixEstime(LocalDate debut, LocalDate fin)` | Prix base × nb nuits (sans options) | double |
| `getOptionsDisponibles` | `List<RoomOption> getOptionsDisponibles()` | Liste des options de la chambre | List |
| `afficherResume` | `String afficherResume()` | Résumé pour affichage client : "Ch. 101 — 89€/nuit — 2 pers. — Disponible" | String |

---

## Méthodes — Calcul capacité (privées / internes)

| Méthode | Signature | Description |
|---------|-----------|-------------|
| `calculerCapaciteDepuisLits` | `int calculerCapaciteDepuisLits()` | SIMPLE=1, DOUBLE=2, CUSTOMIZED=2 (ou configurable) |

### Règle de capacité par type de lit

| BedTypes | Places |
|----------|--------|
| `SIMPLE` | 1 |
| `DOUBLE` | 2 |
| `CUSTOMIZED` | 2 (par défaut ; peut être paramétré) |

---

## Getters (Lombok ou manuels)

Tous les attributs doivent avoir un getter :
- `getId()`, `getPrice()`, `getStatus()`, `getBeds()`, `getOptions()`

---

## Exemple d'utilisation

```java
List<Bed> lits = new ArrayList<>();
lits.add(new Bed(1, BedTypes.DOUBLE));

Room chambre = new Room(101, 89.99, lits);
// chambre.status = AVAILABLE par défaut

chambre.ajouterOption(new RoomOption(1, "Petit-déjeuner", 12.0));

// Client consulte
System.out.println(chambre.afficherResume());
// "Ch. 101 — 89.99€/nuit — 2 pers. — Disponible"

double prix = chambre.calculerPrixEstime(
    LocalDate.of(2026, 7, 1),
    LocalDate.of(2026, 7, 4)
); // 89.99 × 3 nuits
```

---

## Relations

- **Contenue dans :** `Hotel.rooms`
- **Contient :** `List<Bed>`, `List<RoomOption>`
- **Référencée par :** `Reservation.room`

---

## Assignation équipe

**Membre 2** — avec `Bed`, `BedTypes`, `RoomOption`, `RoomStatus`

## Branche Git suggérée

`feature/room-bed-options-status`
