# Classe `RoomOption`

> **Package :** `com.hotel`  
> **Fichier :** `src/main/java/com/hotel/RoomOption.java`  
> **Rôle :** Représenter une option ou un supplément payant pour une chambre.  
> **Acteurs :** Gérant (création/gestion) + Client (sélection).

---

## Description

Une `RoomOption` est un service additionnel associé à une chambre ou à une réservation :
- Petit-déjeuner
- Parking
- Wifi premium
- Lit bébé
- Accès spa
- etc.

Le gérant **crée et associe** les options aux chambres.  
Le client **choisit** des options lors de la réservation.

---

## Attributs

| Attribut | Type | Description | Obligatoire | Exemple |
|----------|------|-------------|-------------|---------|
| `id` | `int` | Identifiant unique | Oui | `1` |
| `name` | `String` | Nom de l'option | Oui | `"Petit-déjeuner"` |
| `description` | `String` | Description détaillée | Non | `"Buffet continental 7h-10h"` |
| `supplementPrice` | `double` | Prix du supplément | Oui | `12.0` |
| `parNuit` | `boolean` | true = facturé par nuit ; false = forfait unique | Oui | `true` |

### Validations

- `name` : non null, non vide
- `supplementPrice` ≥ 0 (0 = option gratuite)
- `id` > 0

---

## Constructeurs

| Constructeur | Paramètres | Description |
|--------------|------------|-------------|
| Principal | `(int id, String name, double supplementPrice)` | parNuit = true par défaut |
| Complet | `(int id, String name, String description, double supplementPrice, boolean parNuit)` | Tous attributs |

---

## Méthodes — Côté GÉRANT

| Méthode | Signature | Description | Retour |
|---------|-----------|-------------|--------|
| `setName` | `void setName(String name)` | Modifie le nom | void |
| `setDescription` | `void setDescription(String description)` | Modifie la description | void |
| `setSupplementPrice` | `void setSupplementPrice(double price)` | Modifie le tarif (≥ 0) | void |
| `setParNuit` | `void setParNuit(boolean parNuit)` | Change le mode de facturation | void |
| `desactiver` | `void desactiver()` | Optionnel : marquer inactive (ajouter `boolean active`) | void |

---

## Méthodes — Côté CLIENT

| Méthode | Signature | Description | Retour |
|---------|-----------|-------------|--------|
| `calculerCout` | `double calculerCout(long nombreNuits)` | Coût pour la durée du séjour | double |
| `getResume` | `String getResume()` | "Petit-déjeuner — 12€/nuit" ou "Parking — 15€ (forfait)" | String |
| `estGratuite` | `boolean estGratuite()` | supplementPrice == 0 | boolean |

### Implémentation `calculerCout(nombreNuits)`

```
si parNuit == true  → supplementPrice × nombreNuits
sinon               → supplementPrice (forfait unique)
```

---

## Getters

- `getId()`, `getName()`, `getDescription()`, `getSupplementPrice()`, `isParNuit()`

---

## Exemples d'options prédéfinies (données de test)

| id | name | supplementPrice | parNuit |
|----|------|-----------------|---------|
| 1 | Petit-déjeuner | 12.0 | true |
| 2 | Parking couvert | 15.0 | false |
| 3 | Wifi premium | 5.0 | true |
| 4 | Lit bébé | 0.0 | false |

---

## Exemple d'utilisation

```java
RoomOption petitDej = new RoomOption(1, "Petit-déjeuner", 12.0);
RoomOption parking = new RoomOption(2, "Parking couvert", 15.0, false);

petitDej.calculerCout(4);  // 12 × 4 = 48.0
parking.calculerCout(4);   // 15.0 (forfait)

chambre.ajouterOption(petitDej);
reservation.ajouterOption(petitDej);
```

---

## Relations

- **Associée à :** `Room.options` (options proposées par la chambre)
- **Sélectionnée dans :** `Reservation.optionsChoisies`

---

## Assignation équipe

**Membre 2** — avec `Room`, `Bed`

## Branche Git suggérée

`feature/room-bed-options-status`
