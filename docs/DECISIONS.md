# Décisions de conception — À valider en équipe

Avant d'implémenter, l'équipe doit se mettre d'accord sur ces points. Une fois validé, cocher la case et noter la date.

---

## 1. Booking vs Reservation

**Problème :** Deux classes modélisent la même chose (une réservation de chambre).

| | `Reservation` | `Booking` |
|---|---------------|-----------|
| ID | `int` | `String` |
| Client | objet `Client` | `String customerName` |
| Dates | `java.util.Date` | `java.time.LocalDate` |
| Chambre | ❌ absent | `Room` |
| Nombre de personnes | ❌ absent | `guestCount` |

**Décision recommandée :**  
✅ **Garder uniquement `Reservation`** et y ajouter les attributs manquants de `Booking` :
- `Room room`
- `int guestCount`
- `ReservationStatus status` (enum à créer : `PENDING`, `CONFIRMED`, `CHECKED_IN`, `CHECKED_OUT`, `CANCELLED`)

✅ **Supprimer `Booking.java`** après migration (dans une PR dédiée).

- [ ] Validé par l'équipe le : _______________

---

## 2. Type de date

**Décision recommandée :** Utiliser **`java.time.LocalDate`** pour `dateDebut` et `dateFin` dans `Reservation` (plus moderne, plus simple pour comparer des dates).

- [ ] Validé par l'équipe le : _______________

---

## 3. Client dans Room

**Problème actuel :** `Room` contient un attribut `Client client` (occupant actuel).

**Décision recommandée :**
- Remplacer `Client client` par un **statut de chambre** : enum `RoomStatus` (`AVAILABLE`, `OCCUPIED`, `MAINTENANCE`, `RESERVED`).
- L'occupant actuel se déduit de la réservation en cours (`Reservation` avec statut `CHECKED_IN`), pas stocké directement dans `Room`.

Cela évite les incohérences (client dans Room ≠ client dans Reservation).

- [ ] Validé par l'équipe le : _______________

---

## 4. Identifiants

**Décision recommandée :**
- Tous les IDs numériques : `int id` (auto-incrémenté côté logique métier dans `Hotel`).
- Pas de mélange `int` / `String` pour les IDs.

- [ ] Validé par l'équipe le : _______________

---

## 5. Enum ReservationStatus (à créer)

```java
public enum ReservationStatus {
    PENDING,      // En attente de confirmation
    CONFIRMED,    // Confirmée
    CHECKED_IN,   // Client arrivé (check-in effectué)
    CHECKED_OUT,  // Client parti (check-out effectué)
    CANCELLED     // Annulée
}
```

- [ ] Validé par l'équipe le : _______________

---

## 6. Enum RoomStatus (à créer)

```java
public enum RoomStatus {
    AVAILABLE,    // Libre
    OCCUPIED,     // Occupée (check-in en cours)
    RESERVED,     // Réservée (pas encore check-in)
    MAINTENANCE   // En travaux / indisponible
}
```

- [ ] Validé par l'équipe le : _______________

---

## 7. Calcul du prix

**Décision recommandée :**
- Prix de base : `Room.price` (prix par nuit).
- Suppléments : `RoomOption` avec un `supplementPrice`.
- Prix total d'une réservation = `(nombre de nuits × prix chambre) + somme des options`.

La méthode `Reservation.calculerPrixTotal()` encapsule ce calcul.

- [ ] Validé par l'équipe le : _______________

---

## Comment enregistrer une décision

1. Discuter en réunion ou sur GitHub (issue ou commentaire PR).
2. Cocher la case ci-dessus.
3. Mettre à jour la fiche de classe concernée si nécessaire.
