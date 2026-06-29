# Classe `Bed`

> **Package :** `com.hotel`  
> **Fichier :** `src/main/java/com/hotel/Bed.java`  
> **Rôle :** Représenter un lit physique dans une chambre.  
> **Acteurs :** Gérant (configuration) + Client (information).

---

## Description

Un `Bed` décrit un lit individuel dans une chambre. Le type de lit (`BedTypes`) détermine la capacité d'accueil pour le calcul du nombre de personnes autorisées dans la chambre.

Une chambre peut contenir **plusieurs lits** (ex. chambre familiale : 1 double + 2 simples).

---

## Attributs

| Attribut | Type | Description | Obligatoire | Exemple |
|----------|------|-------------|-------------|---------|
| `id` | `int` | Identifiant unique du lit (dans la chambre ou global) | Oui | `1` |
| `bedTypes` | `BedTypes` | Type de lit | Oui | `BedTypes.DOUBLE` |

### Validations

- `id` > 0
- `bedTypes` non null

---

## Constructeurs

| Constructeur | Paramètres | Description |
|--------------|------------|-------------|
| Principal | `(int id, BedTypes bedTypes)` | Crée un lit avec type défini |

---

## Méthodes

| Méthode | Signature | Description | Acteur | Retour |
|---------|-----------|-------------|--------|--------|
| `getCapacite` | `int getCapacite()` | Nombre de personnes que ce lit peut accueillir | Client + Gérant | int |
| `getLabel` | `String getLabel()` | Libellé lisible : "Lit double", "Lit simple" | Client | String |
| `setBedTypes` | `void setBedTypes(BedTypes bedTypes)` | Change le type (gérant reconfigure la chambre) | Gérant | void |
| `equals` | `boolean equals(Object o)` | Deux lits égaux si même id | — | boolean |
| `hashCode` | `int hashCode()` | Pour collections | — | int |

### Implémentation de `getCapacite()`

```
SIMPLE      → 1
DOUBLE      → 2
CUSTOMIZED  → 2 (valeur par défaut ; l'équipe peut ajouter un attribut capacity si besoin)
```

### Implémentation de `getLabel()`

```
SIMPLE      → "Lit simple"
DOUBLE      → "Lit double"
CUSTOMIZED  → "Lit personnalisé"
```

---

## Méthode optionnelle (si CUSTOMIZED doit être flexible)

| Méthode | Signature | Description |
|---------|-----------|-------------|
| `setCapacitePersonnalisee` | `void setCapacitePersonnalisee(int capacite)` | Uniquement si bedTypes == CUSTOMIZED |

Si l'équipe choisit cette option, ajouter un attribut `int customCapacity` (défaut -1 = utiliser règle enum).

---

## Getters

- `getId()` → `int`
- `getBedTypes()` → `BedTypes`

---

## Exemple d'utilisation

```java
Bed litDouble = new Bed(1, BedTypes.DOUBLE);
Bed litSimple = new Bed(2, BedTypes.SIMPLE);

litDouble.getCapacite();  // 2
litSimple.getCapacite();  // 1
litDouble.getLabel();     // "Lit double"

// Dans Room : capacité totale = 2 + 1 = 3 personnes
```

---

## Relations

- **Contenu dans :** `Room.beds` (List)
- **Utilise :** enum `BedTypes`

---

## Assignation équipe

**Membre 2** — avec `Room`, `BedTypes`, `RoomOption`

## Branche Git suggérée

`feature/room-bed-options-status`
