# Énumération `ReservationStatus` (à créer)

> **Package :** `com.hotel`  
> **Fichier à créer :** `src/main/java/com/hotel/ReservationStatus.java`  
> **Rôle :** Suivre le cycle de vie d'une réservation.

---

## Valeurs

| Constante | Signification |
|-----------|---------------|
| `PENDING` | En attente de confirmation (optionnel) |
| `CONFIRMED` | Réservation confirmée |
| `CHECKED_IN` | Client arrivé à l'hôtel |
| `CHECKED_OUT` | Séjour terminé |
| `CANCELLED` | Réservation annulée |

---

## Transitions autorisées

| De | Vers | Action |
|----|------|--------|
| PENDING | CONFIRMED | Gérant confirme |
| PENDING | CANCELLED | Annulation |
| CONFIRMED | CHECKED_IN | Check-in |
| CONFIRMED | CANCELLED | Annulation |
| CHECKED_IN | CHECKED_OUT | Check-out |
| CHECKED_OUT | — | État final |
| CANCELLED | — | État final |

---

## Méthodes suggérées

| Méthode | Description |
|---------|-------------|
| `getLabel()` | "Confirmée", "En cours", etc. |
| `estModifiable()` | true si PENDING ou CONFIRMED |
| `estAnnulable()` | true si PENDING ou CONFIRMED |
| `estActive()` | true si CONFIRMED ou CHECKED_IN |

---

## Assignation équipe

**Membre 4** — avec `Reservation`

## Branche Git

`feature/reservation-cycle-vie-prix`
