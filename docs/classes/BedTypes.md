# Énumération `BedTypes`

> **Package :** `com.hotel`  
> **Fichier :** `src/main/java/com/hotel/BedTypes.java`  
> **Rôle :** Définir les types de lits disponibles dans une chambre.

---

## Valeurs actuelles

| Constante | Signification | Capacité (personnes) |
|-----------|---------------|----------------------|
| `SIMPLE` | Lit une place | 1 |
| `DOUBLE` | Lit deux places | 2 |
| `CUSTOMIZED` | Lit personnalisé / lit king-size / sofa-bed | 2 (par défaut) |

---

## Méthodes à ajouter (recommandé pour une enum « riche »)

En Java, on peut enrichir l'enum avec des attributs et méthodes :

| Méthode / attribut | Type | Description |
|--------------------|------|-------------|
| `getLabel()` | `String` | Libellé affichable en français |
| `getCapacite()` | `int` | Nombre de personnes |
| `fromLabel(String)` | `BedTypes` | Retrouver l'enum depuis un libellé (optionnel) |

### Implémentation suggérée

```java
public enum BedTypes {
    SIMPLE("Lit simple", 1),
    DOUBLE("Lit double", 2),
    CUSTOMIZED("Lit personnalisé", 2);

    private final String label;
    private final int capacite;

    BedTypes(String label, int capacite) {
        this.label = label;
        this.capacite = capacite;
    }

    public String getLabel() { return label; }
    public int getCapacite() { return capacite; }
}
```

---

## Utilisation

- Dans `Bed.bedTypes` pour typer chaque lit
- Dans `Bed.getCapacite()` → délègue à `bedTypes.getCapacite()`
- Dans `Room.getCapacite()` → somme des capacités de tous les lits

---

## Assignation équipe

**Membre 2**

## Branche Git

Inclus dans `feature/room-bed-options-status`
