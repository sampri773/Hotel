# Énumération `Rate`

> **Package :** `com.hotel`  
> **Fichier :** `src/main/java/com/hotel/Rate.java`  
> **Rôle :** Classification par étoiles de l'hôtel (norme hôtelière).

---

## Valeurs actuelles

| Constante | Étoiles | Description |
|-----------|---------|-------------|
| `ONE` | ★ | 1 étoile — hébergement basique |
| `TWO` | ★★ | 2 étoiles — confort standard |
| `THREE` | ★★★ | 3 étoiles — bon confort |
| `FOUR` | ★★★★ | 4 étoiles — haut standing |
| `FIVE` | ★★★★★ | 5 étoiles — luxe |

---

## Méthodes à ajouter (recommandé)

| Méthode | Type | Description |
|---------|------|-------------|
| `getEtoiles()` | `int` | Retourne 1 à 5 |
| `getLabel()` | `String` | "4 étoiles" |
| `getSymbole()` | `String` | "★★★★" (affichage) |

### Implémentation suggérée

```java
public enum Rate {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

    private final int etoiles;

    Rate(int etoiles) { this.etoiles = etoiles; }

    public int getEtoiles() { return etoiles; }
    public String getLabel() { return etoiles + " étoile(s)"; }
}
```

---

## Utilisation

- Attribut `Hotel.rate` — le gérant définit la catégorie de l'établissement
- Affichage côté client lors de la recherche d'hôtel
- Peut influencer le positionnement tarifaire (hors scope initial)

---

## Assignation équipe

**Membre 1** — avec `Hotel`

## Branche Git

Inclus dans `feature/hotel-gestion-chambres-clients-reservations`
