# MultiTools Plus

![Minecraft Version](https://img.shields.io/badge/Minecraft-1.20.1-brightgreen)
![Forge Version](https://img.shields.io/badge/Forge-47.4.10-orange)
![Version](https://img.shields.io/badge/Version-1.0--SNAPSHOT-blue)

Un mod Minecraft Forge qui ajoute des outils multifonctions puissants et polyvalents, combinant les capacités de plusieurs outils en un seul item !

## 📋 Table des matières

- [À propos](#-à-propos)
- [Fonctionnalités](#-fonctionnalités)
- [Items ajoutés](#-items-ajoutés)
- [Configuration](#-configuration)
- [Installation](#-installation)
- [Développement](#-développement)
- [Licence](#-licence)

## 🎮 À propos

**MultiTools Plus** est un mod Minecraft qui révolutionne votre expérience de jeu en introduisant des outils multifonctions intelligents. Fini le besoin de transporter une pioche, une hache, une pelle et une épée séparément ! Avec les MultiTools, un seul outil peut accomplir toutes ces tâches.

## ✨ Fonctionnalités

### 🔄 Système de modes
- **5 modes différents** : Pioche, Hache, Pelle, Épée, et Spécial
- **Changement facile** : Shift + Clic droit pour changer de mode
- **Affichage clair** : Le mode actuel est affiché dans l'infobulle de l'item

### 🎯 Capacités spéciales
- **Auto-Smelt** : L'Ultimate MultiTool peut automatiquement fondre les minerais minés
- **Détection de minerais** : Détecte les minerais dans un rayon configurable
- **Durabilité améliorée** : Chaque niveau de MultiTool offre une durabilité supérieure
- **Résistance au feu** : L'Ultimate MultiTool est résistant au feu (comme la netherite)

### ⚙️ Configuration personnalisable
Ajustez le mod à votre guise grâce au fichier de configuration :
- Durabilité de chaque outil
- Activation/désactivation de l'auto-smelt
- Chance d'activation de l'auto-smelt
- Rayon de détection des minerais

## 🛠️ Items ajoutés

### Basic MultiTool
- **Craft** : Fer
- **Durabilité** : 750 (configurable)
- **Description** : Votre premier MultiTool ! Combine les fonctionnalités de base des outils en fer.
- **Modes** : Pioche, Hache, Pelle, Épée

### Advanced MultiTool
- **Craft** : Diamant
- **Durabilité** : 1500 (configurable)
- **Description** : Version améliorée avec la puissance du diamant.
- **Modes** : Pioche, Hache, Pelle, Épée, Spécial

### Ultimate MultiTool
- **Craft** : Netherite
- **Durabilité** : 3000 (configurable)
- **Description** : L'outil ultime ! Résistant au feu avec des capacités spéciales.
- **Modes** : Pioche, Hache, Pelle, Épée, Spécial
- **Capacités spéciales** :
  - Auto-Smelt (75% de chance par défaut)
  - Détection de minerais (rayon de 5 blocs par défaut)
  - Résistance au feu et à la lave

## ⚙️ Configuration

Le mod génère un fichier de configuration à `config/multitoolsplus-common.toml` après le premier lancement.

### Options disponibles :

```toml
# Durabilité du Basic MultiTool
basic_multitool_durability = 750

# Durabilité de l'Advanced MultiTool
advanced_multitool_durability = 1500

# Durabilité de l'Ultimate MultiTool
ultimate_multitool_durability = 3000

# Activer la fonction auto-smelt de l'Ultimate MultiTool
enable_auto_smelt = true

# Chance d'activation de l'auto-smelt (0.0 - 1.0)
auto_smelt_chance = 0.75

# Rayon de détection des minerais de l'Ultimate MultiTool
ore_detector_radius = 5
```

## 📥 Installation

### Pour les joueurs

1. **Téléchargez et installez** [Minecraft Forge 1.20.1](https://files.minecraftforge.net/) (version 47.4.10 ou supérieure)
2. **Téléchargez** le fichier `.jar` du mod MultiTools Plus
3. **Placez** le fichier `.jar` dans le dossier `mods` de votre installation Minecraft
4. **Lancez** Minecraft avec le profil Forge
5. **Profitez** de vos nouveaux MultiTools !

### Prérequis

- Minecraft Java Edition 1.20.1
- Minecraft Forge 47.4.10 ou supérieur
- Java 17 ou supérieur

## 🔧 Développement

### Configuration de l'environnement

```bash
# Cloner le dépôt
git clone https://github.com/votre-nom/Multitools-plus.git
cd Multitools-plus

# Configurer l'environnement Forge (Windows)
gradlew genIntellijRuns

# Compiler le mod
gradlew build
```

### Structure du projet

```
src/main/java/com/tagsteel/modtest/
├── Modtest.java                    # Classe principale du mod
├── BaseMultitoolItem.java          # Classe de base pour tous les MultiTools
├── BasicMultitoolItem.java         # Basic MultiTool
├── AdvancedMultitoolItem.java      # Advanced MultiTool
├── UltimateMultitoolItem.java      # Ultimate MultiTool
├── MultiToolEventHandler.java      # Gestion des événements
└── MultitoolsConfig.java           # Configuration du mod
```

### Compiler le mod

Le fichier JAR compilé se trouve dans `build/libs/` après exécution de :

```bash
gradlew build
```

### Tester le mod

```bash
# Lancer le client de test
gradlew runClient

# Lancer le serveur de test
gradlew runServer
```

## 🎨 Textures

Les textures des items doivent être placées dans :
```
src/main/resources/assets/multitoolsplus/textures/item/
```

Fichiers nécessaires (16x16 pixels) :
- `basic_multitool.png`
- `advanced_multitool.png`
- `ultimate_multitool.png`

Consultez `TEXTURE_GUIDE.md` pour plus de détails sur la création des textures.

## 🤝 Contribution

Les contributions sont les bienvenues ! N'hésitez pas à :

1. Fork le projet
2. Créer une branche pour votre fonctionnalité (`git checkout -b feature/NouvelleFonctionnalite`)
3. Commit vos changements (`git commit -m 'Ajout d'une nouvelle fonctionnalité'`)
4. Push vers la branche (`git push origin feature/NouvelleFonctionnalite`)
5. Ouvrir une Pull Request

## 📝 TODO / Améliorations futures

- [ ] Ajouter des sons personnalisés pour le changement de mode
- [ ] Créer des animations spéciales pour l'Ultimate MultiTool
- [ ] Ajouter plus de modes spéciaux
- [ ] Support multilingue (EN, FR, etc.)
- [ ] Compatibilité avec d'autres mods populaires

## 📄 Licence

**All Rights Reserved** - © 2025 TagSteel

Ce projet est actuellement sous licence "All Rights Reserved". Pour toute utilisation, veuillez contacter l'auteur.

## 👤 Auteur

**TagSteel**

## 🙏 Remerciements

- Merci à l'équipe Minecraft Forge pour leur excellent framework de modding
- Merci à la communauté Minecraft pour leur soutien et leurs retours

---

**Note** : Ce mod est en cours de développement (version SNAPSHOT). Des bugs peuvent être présents. N'hésitez pas à signaler tout problème sur la page des issues du projet !

