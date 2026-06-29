# Workflow Git & GitHub — Guide complet pour l'équipe

> **Équipe :** 4 personnes  
> **Dépôt :** GitHub (remote `origin`)  
> **Branche principale :** `main` (ou `master` — utiliser celle qui existe sur le remote)

---

## 1. Principes fondamentaux

| Règle | Explication |
|-------|-------------|
| **Ne jamais coder directement sur `main`** | Toute modification passe par une branche + Pull Request |
| **Une branche = une tâche** | Une fonctionnalité ou un correctif isolé |
| **Pull Request obligatoire** | Au moins **1 relecture** d'un autre membre avant merge |
| **Commits fréquents et clairs** | Petits commits avec messages explicites en français ou anglais |
| **Synchroniser souvent** | `git pull` sur `main` avant de créer une branche |

---

## 2. Structure des branches

```
main                          ← code stable, toujours compilable
 │
 ├── feature/nom-tache         ← nouvelles fonctionnalités
 ├── fix/nom-bug              ← corrections de bugs
 ├── chore/nom-nettoyage       ← refactor, suppression, config
 └── docs/nom-doc              ← documentation uniquement
```

### Convention de nommage

```
feature/hotel-gestion-reservations
feature/client-actions-reservation
feature/room-bed-options-status
fix/reservation-accolade-syntaxe
chore/supprimer-booking-fusion-reservation
docs/specifications-classes
```

**Format :** `type/description-courte-en-minuscules-avec-tirets`

---

## 3. Configuration initiale (une seule fois par personne)

### 3.1 Cloner le dépôt

```bash
git clone https://github.com/VOTRE-ORG/VOTRE-REPO.git
cd Hotel
```

### 3.2 Vérifier la branche par défaut

```bash
git branch -a
git checkout main
```

### 3.3 Configurer le remote (si besoin)

```bash
git remote -v
# origin  https://github.com/VOTRE-ORG/VOTRE-REPO.git (fetch)
# origin  https://github.com/VOTRE-ORG/VOTRE-REPO.git (push)
```

---

## 4. Workflow complet — Étape par étape

### Étape A — Avant de commencer une tâche

```bash
# 1. Se placer sur main
git checkout main

# 2. Récupérer les dernières modifications du remote
git pull origin main

# 3. Créer une nouvelle branche depuis main à jour
git checkout -b feature/nom-de-votre-tache
```

**Exemple concret :**
```bash
git checkout main
git pull origin main
git checkout -b feature/client-actions-reservation
```

---

### Étape B — Travailler et committer

```bash
# Voir les fichiers modifiés
git status

# Voir les différences
git diff

# Ajouter les fichiers concernés (pas git add . aveuglément)
git add src/main/java/com/hotel/Client.java
git add src/test/java/com/hotel/ClientTest.java

# Commit avec message clair
git commit -m "feat(client): ajouter attributs et méthode reserver"
```

#### Format des messages de commit (recommandé)

```
type(scope): description courte

Types :
  feat     → nouvelle fonctionnalité
  fix      → correction de bug
  refactor → restructuration sans changement de comportement
  test     → ajout/modification de tests
  docs     → documentation
  chore    → maintenance (suppression Booking, config, etc.)
```

**Exemples :**
```
feat(hotel): implémenter creerReservation et checkIn
feat(room): remplacer Client par RoomStatus
fix(reservation): corriger accolade en trop
test(client): ajouter tests pour annulerReservation
docs: ajouter spécifications classes POO
chore: supprimer Booking.java après fusion avec Reservation
```

---

### Étape C — Pousser la branche sur GitHub (push)

```bash
# Premier push de la branche (crée la branche sur le remote)
git push -u origin feature/nom-de-votre-tache
```

**Pushes suivants (après nouveaux commits) :**
```bash
git push
```

---

### Étape D — Créer une Pull Request (PR)

#### Via GitHub (interface web)

1. Aller sur le dépôt GitHub après le push.
2. Cliquer **"Compare & pull request"** (bannière jaune).
3. Remplir :
   - **Title :** `feat(client): actions réservation côté client`
   - **Description :** voir modèle ci-dessous
   - **Base :** `main` ← **Compare :** votre branche
4. Assigner **1 relecteur** minimum (un autre membre de l'équipe).
5. Cliquer **"Create pull request"**.

#### Via GitHub CLI (optionnel)

```bash
gh pr create --title "feat(client): actions réservation côté client" --body "## Summary
- Ajout attributs Client (nom, prenom, email, telephone)
- Méthodes reserver, annulerReservation, getMesReservations

## Test plan
- [ ] mvn test passe
- [ ] ClientTest.reserver() OK
- [ ] Relecture par Membre 1"
```

---

### Modèle de description Pull Request

```markdown
## Summary
- Point 1 : ce qui a été fait
- Point 2 : ce qui a été fait

## Fichiers modifiés
- `Client.java` — attributs + méthodes client
- `ClientTest.java` — tests unitaires

## Test plan
- [ ] Le projet compile : `mvn compile`
- [ ] Les tests passent : `mvn test`
- [ ] Comportement vérifié manuellement dans Main (si applicable)

## Liens
- Fiche classe : docs/classes/Client.md
- Issue / tâche : (numéro si applicable)

## Relecteur assigné
@nom-du-relecteur
```

---

### Étape E — Relecture de code (code review)

**Relecteur :**
1. Ouvrir la PR sur GitHub.
2. Onglet **"Files changed"** — lire chaque modification.
3. Laisser des commentaires si problème ou question.
4. Si OK : cliquer **"Approve"**.
5. Si modifications demandées : **"Request changes"**.

**Auteur de la PR :**
1. Corriger sur la **même branche**.
2. Committer et pousser : `git push`
3. La PR se met à jour automatiquement.
4. Redemander une relecture.

---

### Étape F — Merger la Pull Request

**Qui merge :** le relecteur approuveur ou le lead (Membre 1), après au moins 1 approbation.

1. Sur GitHub, bouton **"Merge pull request"**.
2. Choisir **"Squash and merge"** (recommandé pour garder `main` propre) ou **"Create a merge commit"**.
3. Confirmer le merge.
4. **Supprimer la branche** sur GitHub (bouton proposé après merge).

---

### Étape G — Mettre à jour sa copie locale après merge

**Tous les membres** doivent synchroniser après chaque merge sur `main` :

```bash
git checkout main
git pull origin main
```

**Si vous étiez sur une autre branche en cours :**
```bash
git checkout feature/autre-tache
git merge main
# ou : git rebase main  (si l'équipe maîtrise rebase)
```

---

## 5. Commandes Git — Référence rapide

| Action | Commande |
|--------|----------|
| Voir l'état | `git status` |
| Voir l'historique | `git log --oneline -10` |
| Changer de branche | `git checkout nom-branche` |
| Créer + changer branche | `git checkout -b feature/ma-tache` |
| Récupérer remote | `git fetch origin` |
| Mettre à jour main | `git pull origin main` |
| Ajouter fichiers | `git add fichier.java` |
| Committer | `git commit -m "message"` |
| Pousser | `git push -u origin ma-branche` |
| Voir branches | `git branch -a` |
| Annuler modifs non commitées | `git restore fichier.java` |
| Voir diff | `git diff` |

---

## 6. Résolution de conflits

Quand `git pull` ou `git merge main` affiche un conflit :

```bash
# 1. Git marque les fichiers en conflit
git status

# 2. Ouvrir le fichier — chercher :
#    <<<<<<< HEAD
#    votre code
#    =======
#    code de main
#    >>>>>>> main

# 3. Choisir la bonne version ou combiner les deux
# 4. Supprimer les marqueurs <<<<<< ======= >>>>>>>

# 5. Marquer comme résolu
git add fichier-resolu.java
git commit -m "fix: résoudre conflit merge main dans Client.java"
git push
```

**En cas de conflit complexe :** appeler un coéquipier ou le lead (Membre 1).

---

## 7. Branches à créer pour ce projet (planning)

| Ordre | Branche | Responsable | PR vers |
|-------|---------|-------------|---------|
| 1 | `docs/specifications-classes` | Tous | main |
| 2 | `feature/enums-status-rate-bedtypes` | M2 + M4 | main |
| 3 | `feature/room-bed-options-status` | M2 | main |
| 4 | `feature/client-actions-reservation` | M3 | main |
| 5 | `feature/reservation-cycle-vie-prix` | M4 | main |
| 6 | `feature/hotel-gestion-complete` | M1 | main |
| 7 | `chore/supprimer-booking-fusion-reservation` | M4 | main |
| 8 | `fix/reservation-syntaxe-et-main` | M1 | main |
| 9 | `test/tests-unitaires-complets` | Tous | main |

**Important :** les branches 3–6 peuvent partiellement se chevaucher. Commencer par les enums (branche 2), puis paralléliser Room/Client/Reservation, et finir par Hotel qui assemble tout.

---

## 8. Règles d'équipe (4 personnes)

| Règle | Détail |
|-------|--------|
| **1 PR = 1 relecteur minimum** | Ne pas merger sa propre PR sans approbation |
| **Pas de push force sur main** | Interdit : `git push --force origin main` |
| **Communication** | Prévenir sur le canal équipe avant de modifier une classe partagée |
| **Main toujours compilable** | Ne merger que si `mvn compile` OK |
| **Durée de vie des branches** | Merger et supprimer sous 1 semaine max |
| **Conflits** | Résoudre ensemble si toucher Hotel + Reservation |

---

## 9. Scénario type — Journée de travail

```bash
# Matin — synchronisation
git checkout main
git pull origin main

# Démarrage tâche
git checkout -b feature/reservation-cycle-vie-prix

# ... coder ...

# Commit intermédiaire
git add src/main/java/com/hotel/Reservation.java
git commit -m "feat(reservation): ajouter status et calculerPrixTotal"

# Push fin de matinée
git push -u origin feature/reservation-cycle-vie-prix

# Après-midi — un coéquipier a mergé sur main
git checkout main
git pull origin main
git checkout feature/reservation-cycle-vie-prix
git merge main   # intégrer les changements récents

# ... finir le code, tests ...

git add .
git commit -m "test(reservation): ajouter ReservationTest"
git push

# Créer PR sur GitHub → relecture Membre 1 → merge
```

---

## 10. Vérifications avant chaque PR

Checklist à cocher dans la description de PR :

- [ ] `mvn compile` — compilation OK
- [ ] `mvn test` — tests OK (ou N/A si pas encore de tests)
- [ ] Code conforme à la fiche `docs/classes/*.md`
- [ ] Pas de code commenté inutile
- [ ] Pas de `System.out.println` de debug oubliés
- [ ] Relecteur assigné

---

## 11. En cas de problème

| Problème | Solution |
|----------|----------|
| Commit sur mauvaise branche | `git stash` → changer branche → `git stash pop` |
| Message commit incorrect | `git commit --amend -m "nouveau message"` (si pas encore pushé) |
| Branche en retard sur main | `git merge main` depuis votre branche |
| PR trop grosse | Diviser en 2 PR plus petites |
| Fichier commité par erreur | Demander aide au lead avant `git reset` |

---

## 12. Schéma visuel du flux

```
[Développeur]                    [GitHub]
     |                               |
     | git pull origin main          |
     |<------------------------------|
     |                               |
     | git checkout -b feature/xxx   |
     | ... code ...                  |
     | git commit                    |
     | git push -u origin feature/xxx|
     |------------------------------>|
     |                               | PR créée
     |                               | Relecture
     |                               | Approve
     |                               | Merge → main
     | git checkout main             |
     | git pull origin main          |
     |<------------------------------|
```
