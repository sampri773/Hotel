# Énumération `RoomStatus` (à créer)

> **Package :** `com.hotel`  
> **Fichier à créer :** `src/main/java/com/hotel/RoomStatus.java`  
> **Rôle :** Indiquer l'état de disponibilité d'une chambre.

---

## Valeurs

| Constante | Signification | Qui change le statut |
|-----------|---------------|----------------------|
| `AVAILABLE` | Chambre libre, réservable | Défaut à la création ; après check-out ; après annulation |
| `RESERVED` | Réservée mais client pas encore arrivé | Lors de `creerReservation` |
| `OCCUPIED` | Client présent (check-in effectué) | Lors de `checkIn` |
| `MAINTENANCE` | Indisponible (travaux, nettoyage profond) | Gérant via `mettreChambreEnMaintenance` |

---

## Diagramme de transitions

```
AVAILABLE ←→ MAINTENANCE  (gérant)
    ↓
RESERVED  (nouvelle réservation)
    ↓
OCCUPIED  (check-in)
    ↓
AVAILABLE (check-out)

RESERVED → AVAILABLE (annulation)
```

---

## Méthodes suggérées

| Méthode | Description |
|---------|-------------|
| `getLabel()` | Libellé français : "Disponible", "Occupée", etc. |
| `estReservable()` | true seulement si AVAILABLE |
| `estOccupeeOuReservee()` | true si OCCUPIED ou RESERVED |

---

## Assignation équipe

**Membre 2** — créer en même temps que la refonte de `Room`

## Branche Git

`feature/room-bed-options-status`
