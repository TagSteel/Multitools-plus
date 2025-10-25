# MultiTools Plus

![Minecraft Version](https://img.shields.io/badge/Minecraft-1.20.1-brightgreen)
![Forge Version](https://img.shields.io/badge/Forge-47.4.10-orange)
![Version](https://img.shields.io/badge/Version-1.0--SNAPSHOT-blue)

A Minecraft Forge mod that adds powerful and versatile multifunction tools, combining the capabilities of multiple tools into a single item!

## 📋 Table of Contents

- [About](#-about)
- [Features](#-features)
- [Added Items](#-added-items)
- [Configuration](#-configuration)
- [Installation](#-installation)
- [Development](#-development)
- [License](#-license)

## 🎮 About

**MultiTools Plus** is a Minecraft mod that revolutionizes your gaming experience by introducing smart multifunction tools. No more need to carry a pickaxe, axe, shovel, and sword separately! With MultiTools, a single tool can accomplish all these tasks.

## ✨ Features

### 🔄 Mode System
- **5 different modes**: Pickaxe, Axe, Shovel, Sword, and Special
- **Easy switching**: Shift + Right Click to change mode
- **Clear display**: Current mode is displayed in the item tooltip

### 🎯 Special Abilities
- **Auto-Smelt**: The Ultimate MultiTool can automatically smelt mined ores
- **Ore Detection**: Detects ores within a configurable radius
- **Enhanced Durability**: Each MultiTool tier offers superior durability
- **Fire Resistance**: The Ultimate MultiTool is fire resistant (like netherite)

### ⚙️ Customizable Configuration
Adjust the mod to your liking through the configuration file:
- Durability of each tool
- Enable/disable auto-smelt
- Auto-smelt activation chance
- Ore detection radius

## 🛠️ Added Items

### Basic MultiTool
- **Crafting**: Iron
- **Durability**: 750 (configurable)
- **Description**: Your first MultiTool! Combines the basic functionalities of iron tools.
- **Modes**: Pickaxe, Axe, Shovel, Sword

### Advanced MultiTool
- **Crafting**: Diamond
- **Durability**: 1500 (configurable)
- **Description**: Enhanced version with the power of diamond.
- **Modes**: Pickaxe, Axe, Shovel, Sword, Special

### Ultimate MultiTool
- **Crafting**: Netherite
- **Durability**: 3000 (configurable)
- **Description**: The ultimate tool! Fire resistant with special abilities.
- **Modes**: Pickaxe, Axe, Shovel, Sword, Special
- **Special Abilities**:
  - Auto-Smelt (75% chance by default)
  - Ore Detection (5 block radius by default)
  - Fire and lava resistance

## ⚙️ Configuration

The mod generates a configuration file at `config/multitoolsplus-common.toml` after the first launch.

### Available Options:

```toml
# Basic MultiTool Durability
basic_multitool_durability = 750

# Advanced MultiTool Durability
advanced_multitool_durability = 1500

# Ultimate MultiTool Durability
ultimate_multitool_durability = 3000

# Enable Ultimate MultiTool auto-smelt feature
enable_auto_smelt = true

# Auto-smelt activation chance (0.0 - 1.0)
auto_smelt_chance = 0.75

# Ultimate MultiTool ore detector radius
ore_detector_radius = 5
```

## 📥 Installation

### For Players

1. **Download and install** [Minecraft Forge 1.20.1](https://files.minecraftforge.net/) (version 47.4.10 or higher)
2. **Download** the MultiTools Plus mod `.jar` file
3. **Place** the `.jar` file in your Minecraft installation's `mods` folder
4. **Launch** Minecraft with the Forge profile
5. **Enjoy** your new MultiTools!

### Prerequisites

- Minecraft Java Edition 1.20.1
- Minecraft Forge 47.4.10 or higher
- Java 17 or higher

## 🔧 Development

### Environment Setup

```bash
# Clone the repository
git clone https://github.com/your-name/Multitools-plus.git
cd Multitools-plus

# Setup Forge environment (Windows)
gradlew genIntellijRuns

# Build the mod
gradlew build
```

### Project Structure

```
src/main/java/com/tagsteel/modtest/
├── Modtest.java                    # Main mod class
├── BaseMultitoolItem.java          # Base class for all MultiTools
├── BasicMultitoolItem.java         # Basic MultiTool
├── AdvancedMultitoolItem.java      # Advanced MultiTool
├── UltimateMultitoolItem.java      # Ultimate MultiTool
├── MultiToolEventHandler.java      # Event handling
└── MultitoolsConfig.java           # Mod configuration
```

### Building the Mod

The compiled JAR file can be found in `build/libs/` after running:

```bash
gradlew build
```

### Testing the Mod

```bash
# Launch test client
gradlew runClient

# Launch test server
gradlew runServer
```

## 🎨 Textures

Item textures should be placed in:
```
src/main/resources/assets/multitoolsplus/textures/item/
```

Required files (16x16 pixels):
- `basic_multitool.png`
- `advanced_multitool.png`
- `ultimate_multitool.png`

See [TEXTURE_GUIDE.md](TEXTURE_GUIDE.md) for more details on creating textures.

## 🤝 Contributing

Contributions are welcome! Feel free to:

1. Fork the project
2. Create a branch for your feature (`git checkout -b feature/NewFeature`)
3. Commit your changes (`git commit -m 'Add new feature'`)
4. Push to the branch (`git push origin feature/NewFeature`)
5. Open a Pull Request

## 📝 TODO / Future Improvements

- [ ] Add custom sounds for mode switching
- [ ] Create special animations for the Ultimate MultiTool
- [ ] Add more special modes
- [ ] Multilingual support (EN, FR, etc.)
- [ ] Compatibility with other popular mods

## 📄 License

**All Rights Reserved** - © 2025 TagSteel

This project is currently under "All Rights Reserved" license. For any use, please contact the author.

## 👤 Author

**TagSteel**

## 🙏 Acknowledgments

- Thanks to the Minecraft Forge team for their excellent modding framework
- Thanks to the Minecraft community for their support and feedback

---

**Note**: This mod is under development (SNAPSHOT version). Bugs may be present. Feel free to report any issues on the project's issues page!

